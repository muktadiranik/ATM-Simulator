import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class changePIN extends JFrame {
    private JPanel mainPanel;
    private JButton backButton;
    private JButton changeButton;
    private JPasswordField passwordFieldNewPIN;
    private JPasswordField passwordFieldReNewPIN;

    public changePIN(String cardNum) {
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                JFrame transaction = new transaction(cardNum);
                transaction.setSize(600, 600);
                transaction.setVisible(true);
            }
        });

        changeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newPIN = passwordFieldNewPIN.getText();
                String reNewPIN = passwordFieldReNewPIN.getText();
                try {
                    try {
                        int intNewPIN = Integer.parseInt(newPIN);
                        int intReNewPIN = Integer.parseInt(reNewPIN);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Entered integer values");
                    }

                    if (!newPIN.equals(reNewPIN)) {
                        JOptionPane.showMessageDialog(null, "Entered PIN does not match");
                        return;
                    }

                    if ((passwordFieldNewPIN.equals("")) || (passwordFieldReNewPIN.equals(""))) {
                        JOptionPane.showMessageDialog(null, "Fill up all fields");
                    }

                    connection c = new connection();
                    String changeRegistrationTableQuery = "update registration set pin = '" + newPIN + "' where cardNum = '" + cardNum + "' ";
                    c.s.executeUpdate(changeRegistrationTableQuery);
                    JOptionPane.showMessageDialog(null, "PIN changed successfully");
                    setVisible(false);
                    JFrame transaction = new transaction(cardNum);
                    transaction.setSize(600, 600);
                    transaction.setVisible(true);

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.toString());
                }
            }
        });

    }

    public static void main(String[] args) {
        JFrame mainFrame = new changePIN("");
        mainFrame.setVisible(true);
    }
}

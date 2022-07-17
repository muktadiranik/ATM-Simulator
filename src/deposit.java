import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class deposit extends JFrame {
    private JTextField textFieldAmount;
    private JButton depositButton;
    private JPanel mainPanel;
    private JButton cancelButton;

    public deposit(String cardNum) {
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame transaction = new transaction(cardNum);
                transaction.setSize(600, 600);
                transaction.setVisible(true);
                setVisible(false);
            }
        });
        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String amount = textFieldAmount.getText().toString();
                connection c = new connection();
                if (amount.equals("")) {
                    JOptionPane.showMessageDialog(null, "Enter a valid amount");
                }
                try {
                    int intAmount = Integer.parseInt(amount);
                    Date date = new Date();
                    String insertDataQuery = "insert into account values('" + cardNum + "', '" + date + "', 'Deposit', '" + amount + "')";
                    c.s.executeUpdate(insertDataQuery);
                    JOptionPane.showMessageDialog(null, amount + " deposited successfully");
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
        JFrame mainFrame = new deposit("");
        mainFrame.setVisible(true);
    }
}

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Date;

public class fastCash extends JFrame {
    private JButton a100Button;
    private JButton a200Button;
    private JButton a500Button;
    private JButton a1000Button;
    private JButton a5000Button;
    private JButton a10000Button;
    private JButton backButton;
    private JPanel mainPanel;

    public fastCash(String cardNum) {
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();


        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame transaction = new transaction(cardNum);
                transaction.setSize(600, 600);
                transaction.setVisible(true);
                setVisible(false);
            }
        });
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String amount = ((JButton) e.getSource()).getText();
                try {
                    connection c = new connection();
                    ResultSet result = c.s.executeQuery("select * from account where cardNum = '" + cardNum + "'");
                    int balance = 0;
                    while (result.next()) {
                        if (result.getString("mode").equals("Deposit")) {
                            balance += Integer.parseInt(result.getString("amount"));
                        } else {
                            balance -= Integer.parseInt(result.getString("amount"));
                        }
                    }

                    if (balance < Integer.parseInt(amount)) {
                        JOptionPane.showMessageDialog(null, "Insufficient balance");
                        return;
                    }

                    Date date = new Date();
                    c.s.executeUpdate("insert into account values('" + cardNum + "', '" + date + "', 'Withdrawal', '" + amount + "')");
                    JOptionPane.showMessageDialog(null, amount + " Debited successfully");

                    setVisible(false);
                    JFrame transaction = new transaction(cardNum);
                    transaction.setSize(600, 600);
                    transaction.setVisible(true);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.toString());
                }
            }
        };
        a100Button.addActionListener(listener);
        a200Button.addActionListener(listener);
        a500Button.addActionListener(listener);
        a1000Button.addActionListener(listener);
        a5000Button.addActionListener(listener);
        a10000Button.addActionListener(listener);
    }

    public static void main(String[] args) {
        JFrame mainFrame = new fastCash("");
        mainFrame.setVisible(true);
    }
}

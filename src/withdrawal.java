import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Date;

public class withdrawal extends JFrame {
    private JTextField textFieldAmount;
    private JButton withdrawButton;
    private JButton cancelButton;
    private JPanel mainPanel;

    public withdrawal(String cardNum) {
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connection c = new connection();
                try {

                    String amount = textFieldAmount.getText().toString();
                    Date date = new Date();
                    if (amount.equals("")) {
                        JOptionPane.showMessageDialog(null, "Enter valid amount");
                    } else {
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

                        c.s.executeUpdate("insert into account values('" + cardNum + "', '" + date + "', 'Withdrawal', '" + amount + "')");
                        JOptionPane.showMessageDialog(null, amount + " debited successfully");

                        JFrame transaction = new transaction(cardNum);
                        transaction.setSize(600, 600);
                        transaction.setVisible(true);
                        setVisible(false);
                    }

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.toString());
                }
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame transaction = new transaction(cardNum);
                transaction.setSize(600, 600);
                transaction.setVisible(true);
                setVisible(false);
            }
        });
    }

    public static void main(String[] args) {
        JFrame mainFrame = new withdrawal("");
        mainFrame.setVisible(true);
    }
}

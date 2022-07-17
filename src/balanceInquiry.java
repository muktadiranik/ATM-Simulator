import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class balanceInquiry extends JFrame {
    private JButton backButton;
    private JLabel labelBalance;
    private JPanel mainPanel;

    public balanceInquiry(String cardNum) {
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        int balance = 0;
        try {
            connection c = new connection();
            ResultSet result = c.s.executeQuery("select * from account where cardNum = '" + cardNum + "'");
            while (result.next()) {
                if (result.getString("mode").equals("Deposit")) {
                    balance += Integer.parseInt(result.getString("amount"));
                } else {
                    balance -= Integer.parseInt(result.getString("amount"));
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        } finally {
            labelBalance.setText(String.valueOf(balance));
        }
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                JFrame transaction = new transaction(cardNum);
                transaction.setSize(600, 600);
                transaction.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        JFrame mainFrame = new balanceInquiry("");
        mainFrame.setVisible(true);
    }
}

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class miniStatement extends JFrame {
    private JButton backButton;
    private JPanel panel;
    private JPanel mainPanel;
    private JLabel labelCardNum;
    private JLabel labelTotalBalance;
    private JTable tableTransactionDetails;
    private JScrollPane scrollPane;

    public miniStatement(String cardNum) {
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

        try {
            connection c = new connection();
            ResultSet result = c.s.executeQuery("select * from account where cardNum = '" + cardNum + "'");
            while (result.next()) {
                labelCardNum.setText("Card: " + result.getString("cardNum"));
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        try {
            int balance = 0;
            connection c = new connection();
            String[] tableHeader = {"Date", "Mode", "Amount"};
            ResultSet result = c.s.executeQuery("SELECT * FROM account where cardNum = '" + cardNum + "'");
            DefaultTableModel model = (DefaultTableModel) tableTransactionDetails.getModel();
            model.setColumnIdentifiers(tableHeader);

            tableTransactionDetails.setRowHeight(40);
            tableTransactionDetails.getColumnModel().getColumn(0).setPreferredWidth(200);

            while (result.next()) {
                model.addRow(new Object[]{result.getString("date"), result.getString("mode"), result.getString("amount")});

                if (result.getString("mode").equals("Deposit")) {
                    balance += Integer.parseInt(result.getString("amount"));
                } else {
                    balance -= Integer.parseInt(result.getString("amount"));
                }
            }
            labelTotalBalance.setText("Total balance: " + balance);
        } catch (Exception ex) {
            System.out.println(ex.toString());

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
        JFrame mainFrame = new miniStatement("");
        mainFrame.setVisible(true);
    }


}

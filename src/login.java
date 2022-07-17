import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class login extends JFrame {
    private JPanel mainPanel;
    private JTextField textFieldCardNum;
    private JButton clearButton;
    private JButton loginButton;
    private JButton registrationButton;
    private JPasswordField passwordPIN;

    public login() {

        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        registrationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame registration = new registration();
                registration.setSize(1400, 800);
                registration.setVisible(true);
                setVisible(false);
            }
        });
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cardNum = textFieldCardNum.getText().toString();
                String pin = passwordPIN.getText().toString();
                connection c = new connection();
                String authenticateUser = "select * from registration where cardNum = '" + cardNum + "' and pin = '" + pin + "'";
                try {
                    ResultSet result = c.s.executeQuery(authenticateUser);
                    if (result.next()) {
                        setVisible(false);
                        JFrame transaction = new transaction(cardNum);
                        transaction.setSize(600, 600);
                        transaction.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Incorrect card number or PIN");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textFieldCardNum.setText("");
                passwordPIN.setText("");
            }
        });
    }

    public static void main(String[] args) {
        JFrame mainFrame = new login();
        mainFrame.setSize(600, 400);
        mainFrame.setVisible(true);
    }
}

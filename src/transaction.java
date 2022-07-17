import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class transaction extends JFrame {
    private JButton depositButton;
    private JButton cashWithdrawalButton;
    private JButton fastCashButton;
    private JButton miniStatementButton;
    private JButton PINChangeButton;
    private JButton balanceInquiryButton;
    private JButton exitButton;
    private JPanel mainPanel;

    public transaction(String cardNum) {
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame deposit = new deposit(cardNum);
                deposit.setSize(400, 400);
                deposit.setVisible(true);
                setVisible(false);
            }
        });
        cashWithdrawalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame withdrawal = new withdrawal(cardNum);
                withdrawal.setSize(400, 400);
                withdrawal.setVisible(true);
                setVisible(false);
            }
        });
        fastCashButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame fastCash = new fastCash(cardNum);
                fastCash.setSize(400, 600);
                fastCash.setVisible(true);
                setVisible(false);
            }
        });
        miniStatementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame miniStatement = new miniStatement(cardNum);
                miniStatement.setSize(600, 600);
                miniStatement.setVisible(true);
                setVisible(false);
            }
        });
        PINChangeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame changePIN = new changePIN(cardNum);
                changePIN.setSize(600, 600);
                changePIN.setVisible(true);
                setVisible(false);
            }
        });
        balanceInquiryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame balanceInquiry = new balanceInquiry(cardNum);
                balanceInquiry.setSize(600, 400);
                balanceInquiry.setVisible(true);
                setVisible(false);
            }
        });
    }

    public static void main(String[] args) {
        JFrame mainFrame = new transaction("");
        mainFrame.setVisible(true);
    }
}

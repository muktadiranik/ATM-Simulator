import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Random;
import java.util.Vector;

public class registration extends JFrame {
    private JTextField textFieldName;
    private JTextField textFieldFathersName;
    private JTextField textFieldDoB;
    private JRadioButton maleRadioButton;
    private JRadioButton femaleRadioButton;
    private JTextField textFieldEmail;
    private JRadioButton marriedRadioButton;
    private JRadioButton unmarriedRadioButton;
    private JTextField textFieldAddress;
    private JTextField textFieldCity;
    private JTextField textFieldZIP;
    private JTextField textFieldDivision;
    private JComboBox comboBoxReligion;
    private JComboBox comboBoxCategory;
    private JComboBox comboBoxEducation;
    private JTextField textFieldIncome;
    private JComboBox comboBoxOccupation;
    private JTextField textFieldNIDNum;
    private JTextField textFieldPassportNum;
    private JRadioButton yesRadioButton;
    private JRadioButton noRadioButton;
    private JRadioButton yesRadioButton1;
    private JRadioButton noRadioButton1;
    private JRadioButton savingsAccountRadioButton;
    private JRadioButton fixedDepositAccountRadioButton;
    private JRadioButton currentAccountRadioButton;
    private JRadioButton recurringDepositAccountRadioButton;
    private JCheckBox ATMCardCheckBox;
    private JCheckBox internetBankingCheckBox;
    private JCheckBox mobileBankingCheckBox;
    private JCheckBox emailAlertsCheckBox;
    private JCheckBox chequeBookCheckBox;
    private JCheckBox eStatementCheckBox;
    private JCheckBox iHerebyDeclareThatCheckBox;
    private JButton submitButton;
    private JButton cancelButton;
    private JPanel mainPanel;
    private JLabel labelFormNum;

    public registration() {
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

        String[] religion = {"Muslim", "Hindu", "Christian", "Buddha", "Others"};
        for (String i : religion) {
            this.comboBoxReligion.addItem(i);
        }

        String[] category = {"General", "Freedom Fighter", "Others"};
        for (String i : category) {
            this.comboBoxCategory.addItem(i);
        }

        String[] education = {"SSC", "HSC", "Graduate", "Post Graduate", "MPhil", "PhD"};
        for (String i : education) {
            this.comboBoxEducation.addItem(i);
        }

        String[] occupation = {"Salaried", "Self Employed", "Student", "Retired", "Business", "Others"};
        for (String i : occupation) {
            this.comboBoxOccupation.addItem(i);
        }

        ButtonGroup genderRadioButton = new ButtonGroup();
        genderRadioButton.add(this.maleRadioButton);
        genderRadioButton.add(this.femaleRadioButton);

        ButtonGroup maritalStatusRadioButton = new ButtonGroup();
        maritalStatusRadioButton.add(this.marriedRadioButton);
        maritalStatusRadioButton.add(this.unmarriedRadioButton);

        ButtonGroup seniorCitizenRadioButton = new ButtonGroup();
        seniorCitizenRadioButton.add(this.yesRadioButton);
        seniorCitizenRadioButton.add(this.noRadioButton);

        ButtonGroup existingAccountRadioButton = new ButtonGroup();
        existingAccountRadioButton.add(this.yesRadioButton1);
        existingAccountRadioButton.add(this.noRadioButton1);

        ButtonGroup accountTypeRadioButton = new ButtonGroup();
        accountTypeRadioButton.add(savingsAccountRadioButton);
        accountTypeRadioButton.add(fixedDepositAccountRadioButton);
        accountTypeRadioButton.add(currentAccountRadioButton);
        accountTypeRadioButton.add(recurringDepositAccountRadioButton);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String formNum = labelFormNum.getText();
                String name = textFieldName.getText();
                String fathersName = textFieldFathersName.getText();
                String dob = textFieldDoB.getText();
                String gender = null;
                if (maleRadioButton.isSelected()) {
                    gender = maleRadioButton.getText();
                } else if (femaleRadioButton.isSelected()) {
                    gender = femaleRadioButton.getText();
                }

                String email = textFieldEmail.getText();
                String maritalStatus = null;
                if (marriedRadioButton.isSelected()) {
                    maritalStatus = marriedRadioButton.getText();
                } else if (unmarriedRadioButton.isSelected()) {
                    maritalStatus = unmarriedRadioButton.getText();
                }

                String address = textFieldAddress.getText();
                String city = textFieldCity.getText();
                String zip = textFieldZIP.getText();
                String division = textFieldDivision.getText();
                String religion = (String) comboBoxReligion.getSelectedItem();
                String category = (String) comboBoxCategory.getSelectedItem();
                String income = textFieldIncome.getText();
                String education = (String) comboBoxEducation.getSelectedItem();
                String occupation = (String) comboBoxOccupation.getSelectedItem();
                String nid = textFieldNIDNum.getText();
                String passport = textFieldPassportNum.getText();

                String seniorCitizen = null;
                if (yesRadioButton.isSelected()) {
                    seniorCitizen = yesRadioButton.getText();
                } else if (noRadioButton.isSelected()) {
                    seniorCitizen = noRadioButton.getText();
                }

                String existingAccount = null;
                if (yesRadioButton1.isSelected()) {
                    existingAccount = yesRadioButton1.getText();
                } else if (noRadioButton1.isSelected()) {
                    existingAccount = noRadioButton1.getText();
                }

                String accountType = null;
                if (savingsAccountRadioButton.isSelected()) {
                    accountType = savingsAccountRadioButton.getText();
                } else if (fixedDepositAccountRadioButton.isSelected()) {
                    accountType = fixedDepositAccountRadioButton.getText();
                } else if (currentAccountRadioButton.isSelected()) {
                    accountType = currentAccountRadioButton.getText();
                } else if (recurringDepositAccountRadioButton.isSelected()) {
                    accountType = recurringDepositAccountRadioButton.getText();
                }


                String services = "";
                if (ATMCardCheckBox.isSelected()) {
                    services = services + ", " + ATMCardCheckBox.getText();
                }
                if (internetBankingCheckBox.isSelected()) {
                    services = services + ", " + internetBankingCheckBox.getText();
                }
                if (mobileBankingCheckBox.isSelected()) {
                    services = services + ", " + mobileBankingCheckBox.getText();
                }
                if (emailAlertsCheckBox.isSelected()) {
                    services = services + ", " + emailAlertsCheckBox.getText();
                }
                if (chequeBookCheckBox.isSelected()) {
                    services = services + ", " + chequeBookCheckBox.getText();
                }
                if (eStatementCheckBox.isSelected()) {
                    services = services + ", " + eStatementCheckBox.getText();
                }

                connection c = new connection();
                Random rand = new Random();
                int min = 1000;
                int max = 9999;
                int randPIN = (int) (Math.random() * (max - min + 1) + min);
                String pin = "" + Math.abs(randPIN);

                try {
                    String queryInsertData = "insert into registration (formNum, name, fathersName, dob, gender, email, maritalStatus, address, city, zip, division, religion, category, income, education, occupation, nid, passport, seniorCitizen, existingAccount, accountType, pin, services) values('" + formNum + "','" + name + "','" + fathersName + "','" + dob + "','" + gender + "','" + email + "','" + maritalStatus + "','" + address + "','" + city + "','" + zip + "','" + division + "','" + religion + "','" + category + "','" + income + "','" + education + "','" + occupation + "','" + nid + "','" + passport + "','" + seniorCitizen + "','" + existingAccount + "','" + accountType + "','" + pin + "','" + services + "')";
                    c.s.executeUpdate(queryInsertData);
                } catch (SQLException ex) {
                    System.out.println(ex);
                }

                Vector<Integer> cardNums = new Vector<Integer>();
                String q = "select cardNum from registration";
                try {
                    ResultSet result = c.s.executeQuery(q);
                    while (result.next()) {
                        cardNums.add(result.getInt("cardNum"));
                    }
                } catch (Exception ex) {
                    System.out.println(ex);
                }

                System.out.println(Collections.max(cardNums));
                JOptionPane.showMessageDialog(null, Collections.max(cardNums) + "\n" + pin);

                JFrame login = new login();
                login.setSize(600, 400);
                login.setVisible(true);
                setVisible(false);

            }
        });
    }


    public static void main(String[] args) {
        JFrame mainFrame = new registration();
        mainFrame.setSize(1400, 800);
        mainFrame.setVisible(true);
    }
}


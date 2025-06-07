package org.example.ui.employee;

import net.miginfocom.swing.MigLayout;
import org.example.controller.BankController;
import org.example.interfaces.Resettable;
import org.example.model.enums.AccountType;
import org.example.model.enums.UserType;

import javax.swing.*;
import java.awt.*;

public class CreateAccountPanel extends JPanel implements Resettable {
    private JTextField customerId;
    private JComboBox<AccountType> accountTypeDropdown;
    private JTextField amountField;
    private JButton createAccountButton;
    private JButton backToMainMenuButton;

    private BankController bankController;
    private EmployeeMainFrame employeeMainFrame;

    public CreateAccountPanel(EmployeeMainFrame frame, BankController bankController) {
        this.employeeMainFrame = frame;
        this.bankController = bankController;

        setLayout(new MigLayout("insets 30", "[][grow,fill]", "[]30[]30[]"));

        JLabel customerIdLabel = new JLabel("Customer ID : ");
        customerIdLabel.setFont(new Font("Tahoma", Font.BOLD, 12));

        customerId = new JTextField();
        customerId.setFont(new Font("SANS", Font.BOLD, 12));

        JLabel userTypeLabel = new JLabel("Account Type : ");
        userTypeLabel.setFont(new Font("SANS", Font.BOLD, 12));

        accountTypeDropdown = new JComboBox<>();
        accountTypeDropdown.setFont(new Font("SANS", Font.BOLD, 12));

        JLabel amountLabel = new JLabel("Amount : ");
        amountLabel.setFont(new Font("SANS", Font.BOLD, 12));

        amountField = new JTextField();
        amountField.setFont(new Font("SANS", Font.BOLD, 12));

        createAccountButton = new JButton("Create Account");
        createAccountButton.setFont(new Font("SANS", Font.BOLD, 12));

        backToMainMenuButton = new JButton("Back to Main Menu");
        backToMainMenuButton.setFont(new Font("SANS", Font.BOLD, 12));

        add(customerIdLabel);
        add(customerId,"wrap, height 40!, grow");

        add(userTypeLabel);
        add(accountTypeDropdown, "wrap, height 40!, grow");

        add(amountLabel);
        add(amountField, "wrap, height 40!, grow");

        add(new JLabel());
        add(createAccountButton, "wrap, height 40!, grow");

        add(new JLabel());
        add(backToMainMenuButton, "wrap, height 40!, grow");

        refreshAccountList();

        createAccountButton.addActionListener(e -> {
            int customerID = Integer.parseInt(customerId.getText());
            AccountType accountType = (AccountType) accountTypeDropdown.getSelectedItem();
            int amount = Integer.parseInt(amountField.getText());
            boolean status = bankController.handleCreateAccount(customerID,accountType,amount);
            if(status){
                JOptionPane.showMessageDialog(null, "Account created successfully");
            }else {
                JOptionPane.showMessageDialog(null, "Account creation failed");
            }
        });

        backToMainMenuButton.addActionListener(e -> {
           bankController.resetFieldsAndNavigateToMenu(this,frame);
        });
    }



    public void refreshAccountList() {
        accountTypeDropdown.removeAllItems();
        accountTypeDropdown.setModel(new DefaultComboBoxModel<>(AccountType.values()));
    }

    @Override
    public void resetFields() {
        customerId.setText("");
        amountField.setText("");

        accountTypeDropdown.removeAllItems();
        refreshAccountList();

        accountTypeDropdown.revalidate();
        accountTypeDropdown.repaint();
    }
}

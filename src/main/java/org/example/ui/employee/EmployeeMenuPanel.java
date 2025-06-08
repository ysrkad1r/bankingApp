package org.example.ui.employee;

import org.example.controller.BankController;
import org.example.interfaces.Logoutable;

import javax.swing.*;
import java.awt.*;

public class EmployeeMenuPanel extends JPanel implements Logoutable {

    public EmployeeMenuPanel(EmployeeMainFrame frame, BankController bankController) {

        setLayout(new GridLayout(6,1));
        JButton addCustomerButton = new JButton("Add Customer");
        JButton deleteCustomerButton = new JButton("Delete Customer");
        JButton createAccountButton = new JButton("Create Account");
        JButton deleteAccountButton = new JButton("Delete Account");
        JButton listAccountsButton = new JButton("List Accounts");
        JButton logoutButton = new JButton("Logout");


        add(addCustomerButton);
        add(deleteCustomerButton);
        add(createAccountButton);
        add(deleteAccountButton);
        add(listAccountsButton);
        add(logoutButton);


        createAccountButton.addActionListener(e -> {
            frame.showPanel("createAccountPanel");
        });


        deleteAccountButton.addActionListener(e -> {
           frame.showPanel("deleteAccountPanel");
        });


        addCustomerButton.addActionListener(e -> {
           frame.showPanel("addCustomerPanel");
        });


        deleteCustomerButton.addActionListener(e -> {
            frame.showPanel("deleteCustomerPanel");
        });


        logoutButton.addActionListener(e -> {
            bankController.handleLogout((JFrame) frame);
            frame.showPanel("loginPanel");
        });


        listAccountsButton.addActionListener(e -> {
            JTextField customerIdField = new JTextField();
            Object[] message = {
                    "Enter Customer ID:", customerIdField
            };
            int option = JOptionPane.showConfirmDialog(null, message, "Find Accounts", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                try {
                    int customerId = Integer.parseInt(customerIdField.getText().trim());
                    frame.showUserAccountsForEmployeePanel.refreshTableData(customerId);
                    frame.showPanel("showUserAccountsForEmployeePanel");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid numeric Customer ID.");
                }
            }
        });
    }
}

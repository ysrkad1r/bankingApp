package org.example.ui.employee;

import org.example.controller.BankController;
import org.example.interfaces.MainFrameView;

import javax.swing.*;
import java.awt.*;

public class EmployeeMenuPanel extends JPanel {
    public EmployeeMenuPanel(MainFrameView frame, BankController bankController) {
        setLayout(new GridLayout(5,1));
        JButton addCustomerButton = new JButton("Add Customer");
        JButton deleteCustomerButton = new JButton("Delete Customer");
        JButton createAccountButton = new JButton("Create Account");
        JButton deleteAccountButton = new JButton("Delete Account");
        JButton listAccountsButton = new JButton("List Accounts");

        add(addCustomerButton);
        add(deleteCustomerButton);
        add(createAccountButton);
        add(deleteAccountButton);
        add(listAccountsButton);
    }
}

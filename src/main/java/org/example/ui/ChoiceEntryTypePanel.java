package org.example.ui;

import org.example.controller.BankController;

import javax.swing.*;
import java.awt.*;

public class ChoiceEntryTypePanel extends JPanel {
    private JButton employeeLoginButton;
    private JButton customerLoginButton;

    public ChoiceEntryTypePanel(MainFrame frame , BankController bankController) {
        setLayout(new GridLayout(2,1));

        employeeLoginButton = new JButton("Employee Login");
        employeeLoginButton.setFont(new Font("Sans", Font.BOLD, 16));

        customerLoginButton = new JButton("Customer Login");
        customerLoginButton.setFont(new Font("Sans", Font.BOLD, 16));

        add(employeeLoginButton);
        add(customerLoginButton);

    }
}

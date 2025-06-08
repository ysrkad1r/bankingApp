package org.example.ui.common;

import org.example.controller.BankController;
import org.example.interfaces.MainFrameView;
import org.example.ui.customer.MainFrame;

import javax.swing.*;
import java.awt.*;

public class ChoiceEntryTypePanel extends JPanel {
    private JButton employeeLoginButton;
    private JButton customerLoginButton;
    //private JButton adminLoginButton;

    public ChoiceEntryTypePanel(MainFrameView frame , BankController bankController) {
        setLayout(new GridLayout(2,1));

        employeeLoginButton = new JButton("Employee Login");
        employeeLoginButton.setFont(new Font("Sans", Font.BOLD, 16));

        customerLoginButton = new JButton("Customer Login");
        customerLoginButton.setFont(new Font("Sans", Font.BOLD, 16));

        //adminLoginButton = new JButton("Admin Login");
        //adminLoginButton.setFont(new Font("Sans", Font.BOLD, 16));

        add(employeeLoginButton);
        add(customerLoginButton);
        //add(adminLoginButton);

        employeeLoginButton.addActionListener(e -> {
           frame.getLoginPanel().setEntryType("employee");
           frame.showPanel("login");
        });

        customerLoginButton.addActionListener(e -> {
            frame.getLoginPanel().setEntryType("customer");
            frame.showPanel("login");
        });

        //adminLoginButton.addActionListener(e -> {
        //   frame.getLoginPanel().setEntryType("admin");
        //   frame.showPanel("login");
        //});

    }
}

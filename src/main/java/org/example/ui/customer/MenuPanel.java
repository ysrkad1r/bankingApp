package org.example.ui.customer;

import org.example.controller.BankController;
import org.example.interfaces.Logoutable;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel implements Logoutable {
    public MenuPanel(MainFrame frame, BankController bankController) {
        setLayout(new GridLayout(5,1));
        JButton depositButton = new JButton("DEPOSIT MONEY");
        JButton withdrawButton = new JButton("WITHDRAW MONEY");
        JButton transferButton = new JButton("TRANSFER MONEY");
        JButton listAccountsButton = new JButton("LIST ACCOUNTS");
        JButton logoutButton = new JButton("LOGOUT");


        add(depositButton);
        add(withdrawButton);
        add(transferButton);
        add(listAccountsButton);
        add(logoutButton);


        logoutButton.addActionListener(e -> {
            bankController.handleLogout(this,frame);
            frame.showPanel("login");
        });


        depositButton.addActionListener(e -> {
            frame.depositPanel.refreshAccountList();
            frame.showPanel("deposit");
        });


        withdrawButton.addActionListener(e -> {
            frame.withdrawPanel.refreshAccountList();
            frame.showPanel("withdraw");
        });


        transferButton.addActionListener(e -> {
            frame.transferPanel.refreshAccountList();
            frame.showPanel("transfer");
        });


        listAccountsButton.addActionListener(e -> {
            frame.showUserAccountsPanel.refreshTableData();
            frame.showPanel("showUserAccounts");
        });

    }
}

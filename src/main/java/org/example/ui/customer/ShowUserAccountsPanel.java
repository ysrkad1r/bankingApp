package org.example.ui.customer;

import org.example.controller.BankController;
import org.example.model.Account;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ShowUserAccountsPanel extends JPanel {
    private JTable accountTable;
    private JScrollPane scrollPane;
    private JButton backToMenuButton;
    private BankController bankController;

    public ShowUserAccountsPanel(MainFrame frame, BankController bankController) {
        this.bankController = bankController;
        setLayout(new BorderLayout());

        backToMenuButton = new JButton("Back to main menu");
        backToMenuButton.setPreferredSize(new Dimension(600,50));

        accountTable = new JTable();
        accountTable.setRowHeight(30);
        scrollPane = new JScrollPane(accountTable);

        add(scrollPane, BorderLayout.CENTER);
        add(backToMenuButton, BorderLayout.SOUTH);

        backToMenuButton.addActionListener(e -> {frame.showPanel("menu");});

        refreshTableData();
    }

    public void refreshTableData() {
        List<Account> accountList = bankController.getAccountsForCurrentCustomer();

        String[] columnNames = {"Account ID", "Customer ID", "Account type" , "Balance"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        for (Account account : accountList) {
            Object[] row = {
                    account.getId(),
                    account.getCustomerId(),
                    account.getType(),
                    account.getBalance()
            };
            tableModel.addRow(row);
        }

        accountTable.setModel(tableModel);
    }

}

package org.example.ui;

import net.miginfocom.swing.MigLayout;
import org.example.controller.BankController;
import org.example.interfaces.Resettable;
import org.example.model.Account;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class TransferPanel extends JPanel implements Resettable {
    private JComboBox senderAccountIdDropDown;
    private JTextField receiverAccountIdField;
    private JTextField amountField;
    private JButton transferButton;
    private JButton backToMenuButton;

    private BankController bankController;
    private MainFrame frame;

    public TransferPanel(MainFrame frame, BankController bankController) {
        this.frame = frame;
        this.bankController = bankController;

        setLayout(new MigLayout("insets 30", "[][grow,fill]", "[]30[]30[]"));

        JLabel senderAccountIdLabel = new JLabel("Sender account ID");
        senderAccountIdLabel.setFont(new Font("Arial", Font.BOLD, 18));

        senderAccountIdDropDown = new JComboBox<>();
        senderAccountIdDropDown.setFont(new Font("Arial", Font.BOLD, 18));

        JLabel receiverAccountIdLabel = new JLabel("Receiver account ID");
        receiverAccountIdLabel.setFont(new Font("Arial", Font.BOLD, 18));

        receiverAccountIdField = new JTextField();
        receiverAccountIdField.setFont(new Font("Arial", Font.BOLD, 18));

        JLabel amountLabel = new JLabel("Amount");
        amountLabel.setFont(new Font("Arial", Font.BOLD, 18));

        amountField = new JTextField();
        amountField.setFont(new Font("Arial", Font.BOLD, 18));

        transferButton = new JButton("Transfer");
        transferButton.setFont(new Font("Arial", Font.BOLD, 18));

        backToMenuButton = new JButton("Back to Menu");
        backToMenuButton.setFont(new Font("Arial", Font.BOLD, 18));

        add(senderAccountIdLabel);
        add(senderAccountIdDropDown, "height 40!, growx , wrap");

        add(receiverAccountIdLabel);
        add(receiverAccountIdField, "height 40!, growx , wrap");

        add(amountLabel);
        add(amountField, "height 40!, growx , wrap");

        add(new JLabel());
        add(transferButton, "height 40!, growx , wrap");

        add(new JLabel());
        add(backToMenuButton, "height 40!, growx , wrap");

        refreshAccountList();

        transferButton.addActionListener(e -> {
            Account selectedAccount = (Account) senderAccountIdDropDown.getSelectedItem();
            if (selectedAccount == null) {
                JOptionPane.showMessageDialog(this, "Please select an account.");
                return;
            }
            if (amountField.getText() == null) {
                JOptionPane.showMessageDialog(this, "Please enter a valid amount.");
                return;
            }
            bankController.handleMoneyTransfer(String.valueOf(selectedAccount.getId()),receiverAccountIdField.getText(),amountField.getText(), this);
        });

        backToMenuButton.addActionListener(e -> {
           bankController.resetFieldsAndNavigateToMenu(this, frame);
        });
    }

    public void refreshAccountList() {
        senderAccountIdDropDown.removeAllItems();
        List<Account> accounts = bankController.getAccountsForCurrentCustomer();
        if(accounts.isEmpty()) {
            senderAccountIdDropDown.addItem(null);
        } else {
            for (Account acc : accounts) {
                senderAccountIdDropDown.addItem(acc);
            }
        }
    }

    @Override
    public void resetFields(){
        senderAccountIdDropDown.removeAllItems();
        refreshAccountList();
        receiverAccountIdField.setText("");
        amountField.setText("");
    }
}

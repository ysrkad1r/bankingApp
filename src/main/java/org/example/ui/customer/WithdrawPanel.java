package org.example.ui.customer;

import net.miginfocom.swing.MigLayout;
import org.example.controller.BankController;
import org.example.interfaces.Resettable;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.text.NumberFormat;
import java.util.List;
import org.example.model.Account;

public class WithdrawPanel extends JPanel implements Resettable {
    private JFormattedTextField amountField;
    private JComboBox<Account> accountDropdown;

    private JButton withdrawButton;
    private JButton backToMenuButton;

    private BankController bankController;
    private MainFrame frame;

    public WithdrawPanel(MainFrame frame , BankController bankController) {
        this.frame = frame;
        this.bankController = bankController;

        NumberFormat format = NumberFormat.getIntegerInstance();
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Double.class);
        format.setGroupingUsed(false);

        setLayout(new MigLayout("insets 30", "[][grow,fill]", "[]30[]30[]"));

        JLabel withdrawLabel = new JLabel("Withdraw : ");
        withdrawLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        amountField = new JFormattedTextField(formatter);
        amountField.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));

        JLabel idLabel = new JLabel("Account ID : ");
        idLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));

        accountDropdown = new JComboBox<>();
        accountDropdown.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));

        withdrawButton = new JButton("Deposit");
        withdrawButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));

        backToMenuButton = new JButton("Back to Menu");
        backToMenuButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));

        add(withdrawLabel);
        add(amountField, "height 40!, width 320!, wrap");

        add(idLabel);
        add(accountDropdown, "height 40!, width 320!, wrap");

        add(new JLabel());
        add(withdrawButton, "height 40!, width 320!, wrap");

        add(new JLabel());
        add(backToMenuButton, "height 40!, width 320!, wrap");

        // Hesap listesini dropdowna doldur
        refreshAccountList();

        withdrawButton.addActionListener(e -> {
            Account selectedAccount = (Account) accountDropdown.getSelectedItem();
            if (selectedAccount == null) {
                JOptionPane.showMessageDialog(this, "Please select an account.");
                return;
            }
            if (amountField.getValue() == null) {
                JOptionPane.showMessageDialog(this, "Please enter a valid amount.");
                return;
            }
            bankController.handleWithdrawMoney(String.valueOf(selectedAccount.getId()), amountField.getText(), this);
        });

        backToMenuButton.addActionListener(e -> {
            bankController.resetFieldsAndNavigateToMenu(this, frame);
        });
    }

    public void refreshAccountList() {
        accountDropdown.removeAllItems();
        List<Account> accounts = bankController.getAccountsForCurrentCustomer();
        if(accounts.isEmpty()) {
            accountDropdown.addItem(null);  // Boş görünmemesi için boş bir eleman eklenebilir
        } else {
            for (Account acc : accounts) {
                accountDropdown.addItem(acc);
            }
        }
    }

    @Override
    public void resetFields() {
        amountField.setValue(null);
        amountField.setText("");
        amountField.revalidate();
        amountField.repaint();

        accountDropdown.removeAllItems();
        refreshAccountList();

        accountDropdown.revalidate();
        accountDropdown.repaint();
    }
}

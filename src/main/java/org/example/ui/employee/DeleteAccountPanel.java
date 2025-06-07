package org.example.ui.employee;

import net.miginfocom.swing.MigLayout;
import org.example.controller.BankController;
import org.example.interfaces.Resettable;

import javax.swing.*;
import java.awt.*;

public class DeleteAccountPanel extends JPanel implements Resettable {
    private JTextField accountIdField;
    private JButton deleteAccountButton;
    private JButton backToMainMenuButton;

    public DeleteAccountPanel(EmployeeMainFrame frame, BankController bankController) {
        setLayout(new MigLayout("insets 30", "[][grow,fill]", "[]30[]30[]"));

        JLabel accountIdLabel = new JLabel("Account ID : ");
        accountIdLabel.setFont(new Font("SANS_SERIF", Font.BOLD, 20));

        accountIdField = new JTextField();
        accountIdField.setFont(new Font("SANS_SERIF", Font.BOLD, 20));

        deleteAccountButton = new JButton("Delete Account");
        deleteAccountButton.setFont(new Font("SANS_SERIF", Font.BOLD, 20));

        backToMainMenuButton = new JButton("Back to Main Menu");
        backToMainMenuButton.setFont(new Font("SANS_SERIF", Font.BOLD, 20));

        add(accountIdLabel);
        add(accountIdField,"wrap,height 40!,grow");

        add(new JLabel());
        add(deleteAccountButton,"wrap,height 40!,grow");

        add(new JLabel());
        add(backToMainMenuButton,"wrap,height 40!,grow");

        deleteAccountButton.addActionListener(e -> {
            int accountId = Integer.parseInt(accountIdField.getText());
            boolean statusOfDeletion = bankController.deleteAccount(accountId);
            if (statusOfDeletion) {
                JOptionPane.showMessageDialog(frame, "Account deleted");
            }else {
                JOptionPane.showMessageDialog(frame, "Account not deleted");
            }
        });

        backToMainMenuButton.addActionListener(e -> {
            bankController.resetFieldsAndNavigateToMenu(this,frame);
        });

    }

    @Override
    public void resetFields() {
        accountIdField.setText("");
    }
}

package org.example.ui.employee;

import net.miginfocom.swing.MigLayout;
import org.example.controller.BankController;
import org.example.interfaces.Resettable;
import org.example.model.Employee;

import javax.swing.*;
import java.awt.*;

public class DeleteCustomerPanel extends JPanel implements Resettable {
    private JTextField accountIdToBeDeleted;
    private JButton deleteButton;
    private JButton backToMainMenuButton;

    public DeleteCustomerPanel(EmployeeMainFrame frame, BankController bankController) {
        setLayout(new MigLayout("insets 30", "[][grow,fill]", "[]30[]30[]"));
        JLabel accountIdLabel = new JLabel("Customer ID : ");
        accountIdLabel.setFont(new Font("SANS", Font.BOLD, 12));

        accountIdToBeDeleted = new JTextField();
        accountIdToBeDeleted.setFont(new Font("SANS", Font.BOLD, 12));

        deleteButton = new JButton("Delete");
        deleteButton.setFont(new Font("SANS", Font.BOLD, 12));

        backToMainMenuButton = new JButton("Back to Main Menu");
        backToMainMenuButton.setFont(new Font("SANS", Font.BOLD, 12));

        add(accountIdLabel);
        add(accountIdToBeDeleted,"grow, height 40!, wrap");

        add(new JLabel());
        add(deleteButton,"grow, height 40!, wrap");

        add(new JLabel());
        add(backToMainMenuButton,"grow, height 40!, wrap");

        deleteButton.addActionListener(e -> {
            try {
                int customerId = Integer.parseInt(accountIdToBeDeleted.getText());
                boolean statusOfDeletion = bankController.deleteCustomer(customerId);
                if (statusOfDeletion) {
                    JOptionPane.showMessageDialog(frame, "Customer deleted successfully!");
                }else {
                    JOptionPane.showMessageDialog(frame, "Customer could not be deleted!");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Customer could not be deleted!");
            }
        });

        backToMainMenuButton.addActionListener(e -> {
            bankController.resetFieldsAndNavigateToMenu(this,frame);
        });
    }

    @Override
    public void resetFields(){
        accountIdToBeDeleted.setText("");
    }
}

package org.example.ui.employee;

import net.miginfocom.swing.MigLayout;
import org.example.controller.BankController;
import org.example.interfaces.MainFrameView;
import org.example.interfaces.Resettable;
import org.example.model.Customer;

import javax.swing.*;
import java.awt.*;

public class AddCustomerPanel extends JPanel implements Resettable {
    private JTextField fullNameField;
    private JTextField emailField;
    private JPasswordField passwordField;

    private JButton addCustomerButton;
    private JButton backToMainMenuButton;

    public AddCustomerPanel(MainFrameView frame, BankController bankController) {
        setLayout(new MigLayout("insets 30", "[][grow,fill]", "[]30[]30[]"));
        JLabel fullNameLabel = new JLabel("Full Name : ");
        fullNameLabel.setFont(new Font("Times New Roman", Font.BOLD, 12));

        fullNameField = new JTextField();
        fullNameField.setFont(new Font("Times New Roman", Font.BOLD, 12));

        JLabel emailLabel = new JLabel("Email : ");
        emailLabel.setFont(new Font("Times New Roman", Font.BOLD, 12));

        emailField = new JTextField();
        emailField.setFont(new Font("Times New Roman", Font.BOLD, 12));

        JLabel passwordLabel = new JLabel("Password : ");
        passwordLabel.setFont(new Font("Times New Roman", Font.BOLD, 12));

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Times New Roman", Font.BOLD, 12));

        addCustomerButton = new JButton("Add Customer");
        addCustomerButton.setFont(new Font("Times New Roman", Font.BOLD, 12));

        backToMainMenuButton = new JButton("Back to Main Menu");
        backToMainMenuButton.setFont(new Font("Times New Roman", Font.BOLD, 12));

        add(fullNameLabel);
        add(fullNameField, "height 40! , wrap , grow");

        add(emailLabel);
        add(emailField, "height 40! , wrap , grow");

        add(passwordLabel);
        add(passwordField, "height 40! , wrap , grow");

        add(new JLabel());
        add(addCustomerButton, "height 40! , wrap , grow");

        add(new JLabel());
        add(backToMainMenuButton, "height 40! , wrap , grow");

        addCustomerButton.addActionListener(e -> {
            String fullName = fullNameField.getText();
            String email = emailField.getText();
            String password = String.valueOf(passwordField.getPassword());
            Customer customer = new Customer(fullName, email, password);
            boolean status = bankController.handleRegisterCustomer(customer);
            if (status) {
                JOptionPane.showMessageDialog(AddCustomerPanel.this, "Customer Added Successfully!");
            }else {
                JOptionPane.showMessageDialog(AddCustomerPanel.this, "Customer could not be added!");
            }
        });

        backToMainMenuButton.addActionListener(e -> {
            bankController.resetFieldsAndNavigateToMenu(this,frame);
        });
    }


    @Override
    public void resetFields() {
        fullNameField.setText("");
        emailField.setText("");
        passwordField.setText("");
    }
}

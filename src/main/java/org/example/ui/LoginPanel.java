package org.example.ui;

import net.miginfocom.swing.MigLayout;
import org.example.controller.BankController;
import org.example.model.Account;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class LoginPanel extends JPanel {
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton;
    public ShowUserAccountsPanel showUserAccountsPanel;

    public LoginPanel(MainFrame frame , BankController controller) {
        setLayout(new MigLayout("insets 30", "[][grow,fill]", "[]20[]20[]"));

        JLabel emailLabel = new JLabel("Email: ");
        emailLabel.setFont(emailLabel.getFont().deriveFont(Font.BOLD,16));
        emailField = new JTextField(20);
        emailField.setFont(new Font(Font.SERIF, Font.PLAIN, 16));

        JLabel passwordLabel = new JLabel("Password: ");
        passwordLabel.setFont(passwordLabel.getFont().deriveFont(Font.BOLD,16));
        passwordField = new JPasswordField(20);

        loginButton = new JButton("Login");

        add(emailLabel);
        add(emailField, "growx, height 40!, wrap");

        add(passwordLabel);
        add(passwordField, "growx, height 40!, wrap");

        add(new JLabel());
        add(loginButton, "right, height 30!, wrap");

        loginButton.addActionListener(e -> {
            boolean status = controller.handleLogin(getEmail(),getPassword(),this, frame);
            if (status) {
                frame.showUserAccountsView();
            }
        });
    }

    public String getEmail() {
        return emailField.getText();
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    public JButton getLoginButton() {
        return loginButton;
    }

    public void resetFields(){
        emailField.setText("");
        passwordField.setText("");
    }

}

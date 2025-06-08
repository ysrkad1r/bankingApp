package org.example.ui.common;

import net.miginfocom.swing.MigLayout;
import org.example.controller.BankController;
import org.example.interfaces.CustomerFrameView;
import org.example.interfaces.MainFrameView;
import org.example.ui.customer.ShowUserAccountsPanel;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {
    private final JTextField emailField;
    private final JPasswordField passwordField;
    private final JButton loginButton;
    public ShowUserAccountsPanel showUserAccountsPanel;
    private String entryType;

    public LoginPanel(MainFrameView frame , BankController controller) {
        setLayout(new MigLayout("insets 30", "[][grow,fill]", "[]20[]20[]"));

        JLabel emailLabel = new JLabel("Email: ");
        emailLabel.setFont(emailLabel.getFont().deriveFont(Font.BOLD,16));
        emailField = new JTextField(20);
        emailField.setBorder(BorderFactory.createCompoundBorder(
                emailField.getBorder(),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        emailField.setFont(new Font(Font.SERIF, Font.PLAIN, 16));

        JLabel passwordLabel = new JLabel("Password: ");
        passwordLabel.setFont(passwordLabel.getFont().deriveFont(Font.BOLD,16));
        passwordField = new JPasswordField(20);
        passwordField.setBorder(BorderFactory.createCompoundBorder(
           passwordField.getBorder(),
           BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));

        loginButton = new JButton("Login");
        loginButton.setFont(loginButton.getFont().deriveFont(Font.BOLD,16));

        JButton backToChoiceEntryTypeButton = new JButton("Back To Choice Entry Type ");
        backToChoiceEntryTypeButton.setFont(new Font(Font.SERIF, Font.BOLD, 16));

        add(emailLabel);
        add(emailField, "growx, height 40!, wrap");

        add(passwordLabel);
        add(passwordField, "growx, height 40!, wrap");

        add(new JLabel());
        add(loginButton, "right, height 30!, wrap");

        add(new JLabel());
        add(backToChoiceEntryTypeButton,"right, height 30!, wrap");

        loginButton.addActionListener(e -> {
            boolean status = controller.handleLogin(getEmail(),getPassword(),this, frame, entryType);
            if (status) {
                if ("customer".equals(entryType) && frame instanceof CustomerFrameView) {
                    ((CustomerFrameView) frame).showUserAccountsView();
                }else {
                    //sadece employeeler icin yapilmasi gereken islemleri bu asamada
                }
            }
        });

        backToChoiceEntryTypeButton.addActionListener(e -> {
           resetFields();
           frame.showPanel("entryType");
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

    public String getEntryType() {
        return entryType;
    }

    public void setEntryType(String entryType) {
        this.entryType = entryType;
    }

    public void resetFields(){
        emailField.setText("");
        passwordField.setText("");
    }

}

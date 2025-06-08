package org.example.ui.employee;

import org.example.controller.BankController;
import org.example.interfaces.EmployeeFrameView;
import org.example.ui.common.ChoiceEntryTypePanel;
import org.example.ui.common.LoginPanel;

import javax.swing.*;
import java.awt.*;

public class EmployeeMainFrame extends JFrame implements EmployeeFrameView {
    BankController bankController = new BankController();

    public ChoiceEntryTypePanel choiceEntryTypePanel = new ChoiceEntryTypePanel(this,bankController);
    public LoginPanel loginPanel = new LoginPanel(this, bankController);
    public EmployeeMenuPanel employeeMenuPanel = new EmployeeMenuPanel(this,bankController);
    public AddCustomerPanel addCustomerPanel = new AddCustomerPanel(this, bankController);
    public DeleteCustomerPanel deleteCustomerPanel = new DeleteCustomerPanel(this, bankController);
    public CreateAccountPanel createAccountPanel = new CreateAccountPanel(this, bankController);
    public DeleteAccountPanel deleteAccountPanel = new DeleteAccountPanel(this, bankController);
    public ShowUserAccountsForEmployeePanel showUserAccountsForEmployeePanel = new ShowUserAccountsForEmployeePanel(this,bankController);


    public EmployeeMainFrame() {
        setTitle("Employee");
        setSize(600, 420);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        setLayout(new CardLayout(10, 10));
        add(employeeMenuPanel,"employeeMenuPanel");
        add(addCustomerPanel, "addCustomerPanel");
        add(deleteCustomerPanel, "deleteCustomerPanel");
        add(createAccountPanel, "createAccountPanel");
        add(deleteAccountPanel, "deleteAccountPanel");
        add(showUserAccountsForEmployeePanel, "showUserAccountsForEmployeePanel");

    }

    public LoginPanel getLoginPanel(){
        return loginPanel;
    }


    public void showPanel(String name) {
        CardLayout cl = (CardLayout) getContentPane().getLayout();
        cl.show(getContentPane(), name);
    }
}

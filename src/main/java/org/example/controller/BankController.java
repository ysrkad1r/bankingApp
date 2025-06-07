package org.example.controller;

import org.example.interfaces.Logoutable;
import org.example.interfaces.MainFrameView;
import org.example.interfaces.Resettable;
import org.example.interfaces.User;
import org.example.model.Account;
import org.example.model.Customer;
import org.example.model.Employee;
import org.example.model.enums.AccountType;
import org.example.model.enums.UserType;
import org.example.service.AccountService;
import org.example.service.CustomerService;
import org.example.service.EmployeeService;
import org.example.session.SessionManager;
import org.example.ui.common.LoginPanel;
import org.example.ui.customer.*;
import org.example.ui.employee.EmployeeMainFrame;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class BankController {
    private final CustomerService customerService = new CustomerService();
    private final AccountService accountService = new AccountService();
    private final EmployeeService employeeService = new EmployeeService();


    public void resetFieldsAndNavigateToMenu(Resettable panel, MainFrameView frame) {
        panel.resetFields();
        User user = SessionManager.getInstance().getLoggedInUser();
        if (user.getUserType() == UserType.EMPLOYEE) {
            frame.showPanel("employeeMenuPanel");
        }else {
            frame.showPanel("menu");
        }
    }


    public boolean handleLogin(String email, String password, LoginPanel parentComponent, MainFrameView frame, String entryType) {
        boolean statusOfLogin = false;
        if (entryType.equals("customer")) {
            statusOfLogin = customerService.loginCustomer(email, password);
        }else if(entryType.equals("employee")) {
            statusOfLogin = employeeService.loginEmployee(email, password);
        }
        if (statusOfLogin) {
            JOptionPane.showMessageDialog(parentComponent, "Login Successful");
            if (entryType.equals("customer")) {
                SessionManager.getInstance().login(customerService.getCustomerDAO().getCustomerByEmail(email));
                frame.showPanel("menu");
            }else{
                SessionManager.getInstance().login(employeeService.getEmployeeDao().getEmployeeByEmail(email));
                //eski pencereyi oldur
                if (frame instanceof JFrame) {
                    ((JFrame) frame).dispose();
                }

                // Yeni pencereyi başlat
                SwingUtilities.invokeLater(() -> {
                    EmployeeMainFrame employeeMainFrame = new EmployeeMainFrame();
                    employeeMainFrame.setVisible(true);
                });
                frame.showPanel("employeeMenuPanel");
            }
            parentComponent.resetFields();
        } else {
            JOptionPane.showMessageDialog(parentComponent, "Login Failed, invalid email or password");
            parentComponent.resetFields();
        }
        return statusOfLogin;
    }


    public void handleLogout(Logoutable parentComponent, MainFrameView frame) {
        JOptionPane.showMessageDialog((Component) parentComponent, "LogOut Successful!");
        SessionManager.getInstance().logOut();
        frame.getLoginPanel().resetFields();
    }


    public boolean handleDepositMoney(String rawAccountId, String rawAmountOfMoney, DepositPanel parentComponent) {
        boolean statusOfDeposit = false;
        if (rawAccountId.isEmpty() || rawAmountOfMoney.isEmpty()){
            JOptionPane.showMessageDialog(parentComponent,"Please enter a valid account ID and amount to deposit!");
            return statusOfDeposit;
        }

        int accountId = Integer.parseInt(rawAccountId);
        double amountOfMoney = Double.parseDouble(rawAmountOfMoney);

        try {
            if (amountOfMoney >= 0 &&  accountId > 0){
                statusOfDeposit = accountService.depositMoney(accountId, amountOfMoney);
                if (statusOfDeposit){
                    JOptionPane.showMessageDialog(parentComponent,"Deposit successful!");
                }else {
                    JOptionPane.showMessageDialog(parentComponent,"Deposit failed!");
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(parentComponent,"Please enter a valid account ID! Try again!");
        }
        parentComponent.resetFields();
        return statusOfDeposit;
    }


    public boolean handleWithdrawMoney(String rawAccountId, String rawAmountOfMoney, WithdrawPanel parentComponent ){
        boolean statusOfWithdraw = false;
        if (rawAccountId.isEmpty() || rawAmountOfMoney.isEmpty()){
            JOptionPane.showMessageDialog(parentComponent,"Please enter a valid account ID and amount to withdraw!");
            return statusOfWithdraw;
        }

        int accountId = Integer.parseInt(rawAccountId);
        double amountOfMoney = Double.parseDouble(rawAmountOfMoney);

        try {
            if (amountOfMoney >= 0 &&  accountId > 0){
                statusOfWithdraw = accountService.withdrawMoney(accountId, amountOfMoney);
                if (statusOfWithdraw){
                    JOptionPane.showMessageDialog(parentComponent,"Withdraw successful!");
                }else {
                    JOptionPane.showMessageDialog(parentComponent,"Withdraw failed!");
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(parentComponent,"Please enter a valid account ID! Try again!");
        }
        parentComponent.resetFields();
        return statusOfWithdraw;
    }


    public void handleMoneyTransfer(String rawSenderAccountId, String rawReceiverAccountId, String rawAmountMoney, TransferPanel parentComponent) {
        boolean statusOfTransfer = false;
        if (rawAmountMoney.isEmpty() || rawSenderAccountId.isEmpty() || rawReceiverAccountId.isEmpty() ){
            JOptionPane.showMessageDialog(parentComponent,"Please enter valid Account id's and money amount to transfer!");
        }

        int senderAccountId = Integer.parseInt(rawSenderAccountId);
        int receiverAccountId = Integer.parseInt(rawReceiverAccountId);
        double amountOfMoney = Double.parseDouble(rawAmountMoney);

        if (amountOfMoney >= 0 &&  senderAccountId > 0 && receiverAccountId > 0){
            statusOfTransfer = accountService.transferMoney(senderAccountId, receiverAccountId, amountOfMoney);
            if (statusOfTransfer){
                JOptionPane.showMessageDialog(parentComponent,"Transfer successful!");
            }else {
                JOptionPane.showMessageDialog(parentComponent,"Transfer failed!");
            }
        }
        parentComponent.resetFields();
    }


    public List<Account> getAccountsForCurrentCustomer() {
        try {
            int currentCustomerId = 1;
            if (SessionManager.getInstance().isLoggedIn()){
                currentCustomerId = SessionManager.getInstance().getLoggedInUser().getId();
            }
            List<Account> accountList = accountService.getAccountDAO().getAccountById(currentCustomerId);
            System.out.println(currentCustomerId);
            return accountList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public boolean handleCreateAccount(int customerId, AccountType accountType, double amount) {
        boolean statusOfInserting = accountService.createAccount(customerId, accountType, amount);
        if (statusOfInserting){
            System.out.println("Account created successfully");
        }else{
            System.out.println("Account creation failed");
        }
        return statusOfInserting;
    }


    //it will be unnecessary maybe it will be checked
    public void handleRegisterCustomer(Customer customer) {
        boolean statusOfInserting = customerService.registerCustomer(customer.getFullName(),customer.getEmail(),customer.getPassword());
        if (statusOfInserting){
            System.out.println("Customer registered successfully");
        }else {
            System.out.println("Customer registration failed");
        }
    }

    public void deleteCustomer(int customerId) {
        customerService.deleteCustomer(customerId);
    }

    public boolean deleteAccount(int accountId) {
        return accountService.deleteAccount(accountId);
    }

    //it will be unnecessary maybe it will be checked
    public void handleRegisterEmployee(Employee employee) {
        boolean statusOfInserting = employeeService.registerEmployee(employee.getName(),employee.getEmail(),employee.getPassword(),employee.getEmployeeType());
        if (statusOfInserting){
            System.out.println("Employee registered successfully");
        }else {
            System.out.println("Employee registration failed");
        }
    }


    // Customer girisi yapildiysa customer veritabaninda , Employee girisi
    // secildiyse employee veritabaninda checking yapilmasini saglayan method bu olacan
    public void setEntryTypeChoice(){

    }
}
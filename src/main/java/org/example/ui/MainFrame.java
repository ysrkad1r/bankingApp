package org.example.ui;

import org.example.controller.BankController;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    BankController bankController = new BankController();

    public ChoiceEntryTypePanel choiceEntryTypePanel = new ChoiceEntryTypePanel(this, bankController);
    public LoginPanel loginPanel = new LoginPanel(this, bankController);
    public MenuPanel menuPanel = new MenuPanel(this, bankController);
    public DepositPanel depositPanel = new DepositPanel(this, bankController);
    public WithdrawPanel withdrawPanel = new WithdrawPanel(this, bankController);
    public TransferPanel transferPanel = new TransferPanel(this, bankController);
    public ShowUserAccountsPanel showUserAccountsPanel;


    public MainFrame() {

        setTitle("Banking System");
        setSize(600, 420);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        setLayout(new CardLayout());
        getContentPane().add(choiceEntryTypePanel, "entryType");
        getContentPane().add(loginPanel, "login");
        getContentPane().add(menuPanel, "menu");
        getContentPane().add(depositPanel, "deposit");
        getContentPane().add(withdrawPanel, "withdraw");
        getContentPane().add(transferPanel, "transfer");

        showPanel("entryType");
        //showPanel("login");
    }

    public void showUserAccountsView(){
        if (showUserAccountsPanel == null) {
            showUserAccountsPanel = new ShowUserAccountsPanel(this, bankController);
            this.add(showUserAccountsPanel, "showUserAccounts"); // Bu hatalıysa:
            // this.getContentPane().add(showUserAccountsPanel, "showUserAccounts"); // Bu doğrudur
        } else {
            showUserAccountsPanel.refreshTableData();
        }
    }

    public void showPanel(String name) {
        CardLayout cl = (CardLayout) getContentPane().getLayout();
        cl.show(getContentPane(), name);
    }

    public LoginPanel getLoginPanel() {
        return loginPanel;
    }

}

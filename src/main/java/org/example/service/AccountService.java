package org.example.service;

import org.example.model.Account;
import org.example.model.Customer;
import org.example.model.enums.AccountType;
import org.example.model.enums.TransactionStatusType;
import org.example.model.enums.TransactionType;
import org.example.repository.AccountDAO;

public class AccountService {
    private AccountDAO accountDAO;
    private TransactionService transactionService;


    public AccountService() {
        this.accountDAO = new AccountDAO();
        this.transactionService = new TransactionService();
    }


    public boolean createAccount(int customerId, AccountType accountType, double amount) {
        Account newAccount = new Account(customerId, accountType, amount);
        boolean statusOfInsertingAccount = false;
        if(accountDAO.getAccountById(newAccount.getCustomerId()).isEmpty()) {
            accountDAO.insertAccount(newAccount);
        }else {
            for (Account account : accountDAO.getAccountById(newAccount.getCustomerId())) {
                System.out.println(account);
            }
            statusOfInsertingAccount = accountDAO.insertAccount(newAccount);
        }
        return statusOfInsertingAccount;
    }


    public boolean depositMoney(int accountId, double amount) {
        boolean success = accountDAO.depositBalance(accountId, amount);
        if(success) {
            this.transactionService.recordTransaction(null,accountId,TransactionType.DEPOSIT, amount, TransactionStatusType.SUCCESS);
        }else {
            this.transactionService.recordTransaction(null,accountId,TransactionType.DEPOSIT, amount, TransactionStatusType.FAILURE);
        }
        return success;
    }


    public boolean withdrawMoney(int accountId, double amount) {
        boolean status = accountDAO.withdrawBalance(accountId, amount);
        if(status) {
            this.transactionService.recordTransaction(null,accountId,TransactionType.WITHDRAW, amount,TransactionStatusType.SUCCESS);
        }else {
            this.transactionService.recordTransaction(null,accountId,TransactionType.WITHDRAW, amount,TransactionStatusType.FAILURE);
        }
        return status;
    }


    public boolean transferMoney(int fromAccountId, int toAccountId, double amount) {
        boolean withdrawFromSenderStatus = accountDAO.withdrawBalance(fromAccountId, amount);
        boolean depositFromSenderStatus = accountDAO.depositBalance(toAccountId, amount);
        boolean statusOfTransfer = withdrawFromSenderStatus && depositFromSenderStatus;
        if (statusOfTransfer) {
            this.transactionService.recordTransaction(fromAccountId,toAccountId, TransactionType.TRANSFER, amount, TransactionStatusType.SUCCESS);
        }else {
            this.transactionService.recordTransaction(fromAccountId,toAccountId, TransactionType.TRANSFER, amount, TransactionStatusType.FAILURE);
        }
        return statusOfTransfer;
    }

    public boolean deleteAccount(int accountId) {
        return accountDAO.deleteAccount(accountId);
    }


    public AccountDAO getAccountDAO() {
        return accountDAO;
    }

}
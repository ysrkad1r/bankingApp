package org.example.model;

import org.example.model.enums.AccountType;

public class Account {
    private int id;
    private int customerId;
    private AccountType type;
    private double balance;

    public Account(int customerId, AccountType type, double balance) {
        setCustomerId(customerId);
        setType(type);
        setBalance(balance);
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object account) {
        if (account == null) {
            return false;
        } else if (!account.getClass().equals(this.getClass())) {
            return false;
        }else {
            Account ac = (Account) account; //type casting
            return (this.id == ac.getId() && this.customerId == ac.getCustomerId() && this.type == ac.getType());
        }
    }

    @Override
    public String toString() {
        return "ID: " + id + " | " + type + " | Balance: " + balance;
    }
}
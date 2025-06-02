package org.example.model;

import org.example.model.enums.TransactionStatusType;
import org.example.model.enums.TransactionType;
import java.time.LocalDateTime;

public class Transaction {
    private int id;
    private Integer senderAccountId;
    private Integer receiverAccountId;
    private TransactionType type;
    private double amount;
    private LocalDateTime timestamp;
    private TransactionStatusType statusType;

    public Transaction(Integer accountId, Integer receiverAccId,  TransactionType type, double amount, LocalDateTime timestamp, TransactionStatusType statusType) {
        this.setSenderAccountId(accountId);
        this.setReceiverAccountId(receiverAccId);
        this.setType(type);
        this.setAmount(amount);
        this.setTimestamp(timestamp);
        this.setStatusType(statusType);
    }

    public Integer getReceiverAccountId() {
        return receiverAccountId;
    }

    public void setReceiverAccountId(Integer receiverAccId) {
        this.receiverAccountId = receiverAccId;
    }

    public Integer getSenderAccountId() {
        return senderAccountId;
    }

    public void setSenderAccountId(Integer senderAcctId) {
        this.senderAccountId = senderAcctId;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public TransactionStatusType getStatusType() {
        return statusType;
    }

    public void setStatusType(TransactionStatusType statusType) {
        this.statusType = statusType;
    }

    @Override
    public String toString() {
        return ("senderAccountId: " + senderAccountId + " receiverAccountId : " + receiverAccountId + ", type: " + type + ", amount: " + amount + ", timestamp: " + timestamp + ", statusType: " + statusType);
    }
}
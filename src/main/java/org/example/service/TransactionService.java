package org.example.service;

import org.example.model.Transaction;
import org.example.model.enums.TransactionStatusType;
import org.example.model.enums.TransactionType;
import org.example.repository.TransactionDAO;

import java.time.LocalDateTime;

public class TransactionService {
    private TransactionDAO transactionDAO;
    public TransactionService() {
        transactionDAO = new TransactionDAO();
    }

    public void recordTransaction(Integer senderAccountId, Integer receiverAccountId, TransactionType type, double amount, TransactionStatusType statusType) {
        LocalDateTime timeStamp = LocalDateTime.now();

        Transaction transaction = new Transaction(senderAccountId, receiverAccountId ,type, amount, timeStamp, statusType);
        transactionDAO.saveTransaction(transaction);
    }
}
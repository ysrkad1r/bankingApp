package org.example.repository;

import org.example.model.Transaction;
import org.example.util.DBConnection;

import java.sql.*;

public class TransactionDAO {
    Connection conn;
    public void saveTransaction(Transaction tx) {
        try {
            conn = DBConnection.getConnection();
            if (conn != null) {
                String sql = "INSERT INTO transactions (senderAccountId, receiverAccountId, type, amount, timeStamp, status) VALUES (?,?,?,?,?,?)";
                PreparedStatement ps = conn.prepareStatement(sql);
                if (tx.getSenderAccountId() != null) {
                    ps.setInt(1, tx.getSenderAccountId());
                } else {
                    ps.setNull(1, Types.INTEGER);
                }
                if (tx.getReceiverAccountId() != null) {
                    ps.setInt(2, tx.getReceiverAccountId());
                } else {
                    ps.setNull(2, Types.INTEGER);
                }
                ps.setString(3, tx.getType().name());
                ps.setDouble(4, tx.getAmount());
                ps.setTimestamp(5, Timestamp.valueOf(tx.getTimestamp()));
                ps.setString(6,tx.getStatusType().name());
                ps.executeUpdate();
                System.out.println("Transaction saved");
                ps.close();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }finally {
            DBConnection.closeConnection(conn);
        }
    }
}
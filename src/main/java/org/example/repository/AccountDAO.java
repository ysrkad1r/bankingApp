package org.example.repository;

import org.example.model.Account;
import org.example.model.enums.AccountType;
import org.example.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO {

    public boolean insertAccount(Account account) {
        Connection conn = null;

        try {
            conn = DBConnection.getConnection();
            if (conn != null) {
                String sql = "INSERT INTO accounts (customer_id, type, balance) VALUES (?,?,?)";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, account.getCustomerId());
                pstmt.setString(2, account.getType().name());
                pstmt.setDouble(3, account.getBalance());

                int rowsAffected = pstmt.executeUpdate();

                pstmt.close();
                DBConnection.closeConnection(conn);

                if (rowsAffected > 0) {
                    System.out.println("Inserted account " + account.getCustomerId() + " " + account.getType() + " " + account.getBalance());
                    return true;
                }
            }

        }catch (SQLException e){
            e.printStackTrace();
        } finally {
            DBConnection.closeConnection(conn);
        }

        return false;
    }


    //it checks db if there is any account returns this account info but if it does not exist it returns null
    public List<Account> getAccountById(int customerId) {
        List<Account> accounts = new ArrayList<>();
        Connection conn = null;
        Account account = null;

        try {
            conn = DBConnection.getConnection();
            if (conn != null) {
                String sql = "SELECT * FROM accounts WHERE customer_id = ?";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, customerId);
                ResultSet rs = pstmt.executeQuery();

                while (rs.next()) {
                    int accountId = rs.getInt("id");
                    int customerIdFromDb = rs.getInt("customer_id");
                    String type = rs.getString("type");
                    double balance = rs.getDouble("balance");

                    AccountType accountType = AccountType.valueOf(type.toUpperCase());

                    account = new Account(customerIdFromDb, accountType, balance);
                    account.setId(accountId);
                    accounts.add(account);
                }
            }

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DBConnection.closeConnection(conn);
        }
        return accounts;
    }


    public boolean depositBalance(int accountId, double amount) {
        Connection conn = null;
        boolean success = false;

        try {
            conn = DBConnection.getConnection();
            if (conn != null) {
                String sql = "UPDATE accounts SET balance = balance + ? WHERE id = ?";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setDouble(1, amount);
                pstmt.setInt(2, accountId);
                int rowsAffected = pstmt.executeUpdate();
                pstmt.close();

                if (rowsAffected > 0) {
                    System.out.println("Updated account " + accountId + " amount " + amount);
                    success = true;
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DBConnection.closeConnection(conn);
        }
        return success;
    }


    public boolean withdrawBalance(int accountId, double amount) {
        Connection conn = null;
        boolean status = false;
        
        try {
            conn = DBConnection.getConnection();
            if (conn != null) {
                String sql = "SELECT balance FROM accounts WHERE id = ?";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, accountId);
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    double balance = rs.getDouble("balance");
                    if (balance >= amount) {
                        String stmtSQL = "UPDATE accounts SET balance = ? WHERE id = ?";
                        PreparedStatement pstmt2 = conn.prepareStatement(stmtSQL);
                        pstmt2.setDouble(1, balance - amount);
                        pstmt2.setInt(2, accountId);
                        pstmt2.executeUpdate();
                        pstmt2.close();
                        System.out.println("Updated account " + accountId + " amount " + amount);
                        return true;
                    }else {
                        System.out.println("Not enough balance for account " + accountId);
                        return false;
                    }
                }

                pstmt.executeQuery();
                pstmt.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DBConnection.closeConnection(conn);
        }
        return status;
    }
}
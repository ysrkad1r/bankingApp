package org.example.repository;

import org.example.model.Customer;
import org.example.util.DBConnection;

import java.sql.*;

public class CustomerDAO {

    //it selects customer by email and create new Customer object
    public Customer getCustomerByEmail(String emailToBeChecked) {
        Connection conn = null;
        Customer customer = null;

        try {
            conn = DBConnection.getConnection();

        }catch (SQLException e){
            e.printStackTrace();
        }

        if (conn != null) {
            try {
                String sql = "SELECT id, full_name, email, password FROM customers WHERE email = ?";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, emailToBeChecked);
                ResultSet rs = pstmt.executeQuery();

                while (rs.next()) {
                    int id = rs.getInt("id");
                    String fullName = rs.getString("full_name");
                    String email = rs.getString("email");
                    String password = rs.getString("password");

                    customer = new Customer(fullName, email, password);
                    customer.setId(id);
                }

                rs.close();
                pstmt.close();

            }catch (SQLException e) {
                e.printStackTrace();

            } finally {
                DBConnection.closeConnection(conn);
            }

        }
        return customer;
    }


    //it inserts a Customer object to the db
    public void saveCustomer(Customer customer) {
        Connection conn = null;

        try {
            conn = DBConnection.getConnection();
        }catch (SQLException e){
            e.printStackTrace();
        }

        if (conn != null) {
            try {
                String sql = "INSERT INTO customers (full_name, email, password) VALUES (?,?,?)";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, customer.getFullName());
                pstmt.setString(2, customer.getEmail());
                pstmt.setString(3, customer.getPassword());
                pstmt.executeUpdate();
                System.out.println("User created Successfully ! ");
                pstmt.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                DBConnection.closeConnection(conn);
            }
        }
    }

}
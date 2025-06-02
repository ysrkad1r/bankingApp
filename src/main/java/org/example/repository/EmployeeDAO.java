package org.example.repository;

import org.example.model.Employee;
import org.example.model.enums.EmployeeType;
import org.example.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDAO {
    public Employee getEmployeeByEmail(String emailToBeChecked){
        Connection conn = null;
        Employee employee = null;

        try {
            conn = DBConnection.getConnection();

        }catch (SQLException e){
            e.printStackTrace();
        }

        if (conn != null) {
            try {
                String sql = "SELECT id, fullName, email, passwordHash, role FROM employees WHERE email = ?";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, emailToBeChecked);
                ResultSet rs = pstmt.executeQuery();

                while (rs.next()) {
                    int id = rs.getInt("id");
                    String fullName = rs.getString("fullName");
                    String email = rs.getString("email");
                    String password = rs.getString("passwordHash");
                    String role = rs.getString("role");
                    EmployeeType employeeType = EmployeeType.valueOf(role);

                    employee = new Employee(fullName, email, password, employeeType);
                    employee.setId(id);
                }

                rs.close();
                pstmt.close();

            }catch (SQLException e) {
                e.printStackTrace();

            } finally {
                DBConnection.closeConnection(conn);
            }

        }
        return employee;
    }


    public void insertEmployee(Employee employee) {
        Connection conn = null;

        try {
            conn = DBConnection.getConnection();
        }catch (SQLException e){
            e.printStackTrace();
        }

        if (conn != null) {
            try {
                String sql = "INSERT INTO employees (fullName, email, passwordHash, role) VALUES (?,?,?,?)";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, employee.getName());
                pstmt.setString(2, employee.getEmail());
                pstmt.setString(3, employee.getPassword());
                pstmt.setString(4,employee.getEmployeeType().name());
                pstmt.executeUpdate();
                System.out.println("Employee created Successfully ! ");
                pstmt.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                DBConnection.closeConnection(conn);
            }
        }
    }

}

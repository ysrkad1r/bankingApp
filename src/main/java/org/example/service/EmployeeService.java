package org.example.service;

import org.example.model.Employee;
import org.example.model.enums.EmployeeType;
import org.example.repository.EmployeeDAO;
import org.example.session.SessionManager;
import org.example.util.PasswordUtil;

public class EmployeeService {
    private EmployeeDAO employeeDAO;


    public EmployeeService() {
        this.employeeDAO = new EmployeeDAO();
    }


    public boolean registerEmployee(String name, String email, String password, EmployeeType employeeType) {
        if (email.isEmpty() || password.isEmpty() || employeeType == null || name.isEmpty() ) {
            return false;}
        if(employeeDAO.getEmployeeByEmail(email) != null) {
            System.out.println("Employee with email " + email + " already exists");
            return false;
        }else {
            String hashedPassword = PasswordUtil.hashPassword(password);
            Employee newEmployee = new Employee(name, email, hashedPassword, employeeType);
            employeeDAO.insertEmployee(newEmployee);
            return true;
        }
    }


    public boolean loginEmployee(String email, String password) {
        if (email == null || email.isEmpty()) {return false;}

        Employee employee = employeeDAO.getEmployeeByEmail(email);
        if (employee == null) {return false;}

        if (PasswordUtil.checkPassword(password, employee.getPassword())) {
            SessionManager employeeSession = SessionManager.getInstance();
            employeeSession.setLoggedInUser(employee);
            return true;
        }
        return false;
    }

    public EmployeeDAO getEmployeeDao() {
        return employeeDAO;
    }
}

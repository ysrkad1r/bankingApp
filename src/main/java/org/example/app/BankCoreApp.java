package org.example.app;

import org.example.ui.customer.MainFrame;
import javax.swing.*;

public class BankCoreApp {
    public static void main(String[] args) {
        //EmployeeService employeeService = new EmployeeService();
        //Employee newEmployee = employeeService.getEmployeeDao().getEmployeeByEmail("kadir123@bankmail.com");
        //employeeService.registerEmployee("kadir", "kadir123@bankmail.com","password123", EmployeeType.STAFF);
        //System.out.println(newEmployee);
        SwingUtilities.invokeLater(() -> {
            MainFrame view = new MainFrame();
            view.setVisible(true);    // Görüntüle
        });
    }

}
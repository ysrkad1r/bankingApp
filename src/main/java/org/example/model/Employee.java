package org.example.model;

import org.example.interfaces.User;
import org.example.model.enums.EmployeeType;

public class Employee implements User {
    private int id;
    private String name;
    private String email;
    private String password;
    private EmployeeType employeeType;

    public Employee(String name, String email, String password, EmployeeType employeeType) {
        this.setName(name);
        this.setEmail(email);
        this.setPassword(password);
        this.setEmployeeType(employeeType);
    }

    public EmployeeType getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(EmployeeType employeeType) {
        this.employeeType = employeeType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object employee) {
        if (employee == null) {
            return false;
        } else if (!employee.getClass().equals(this.getClass())) {
            return false;
        }else {
            Employee employee1 = (Employee) employee; //type casting
            return (this.id == employee1.getId() && this.getName().equals(employee1.getName()) && this.getEmail().equals(employee1.getEmail()) && this.getPassword().equals(employee1.getPassword()) && this.getEmployeeType().equals(employee1.getEmployeeType()));
        }
    }

    @Override
    public String toString() {
        return "Id : " + this.getId() + " name : " + this.getName() + " email : " + this.getEmail() + " password : " + this.getPassword() + " type : " + this.getEmployeeType();
    }
}

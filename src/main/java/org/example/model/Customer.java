package org.example.model;

import org.example.interfaces.User;
import org.example.model.enums.UserType;

public class Customer implements User {
    private int id;
    private String fullName;
    private String email;
    private String password;

    public Customer(String fullName, String email, String password) {
        setFullName(fullName);
        setEmail(email);
        setPassword(password);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public UserType getUserType() {
        return UserType.CUSTOMER;
    }

    @Override
    public boolean equals(Object customer) {
        if (customer == null) {
            return false;
        } else if (!customer.getClass().equals(this.getClass())) {
            return false;
        }else {
            Customer cstm = (Customer) customer; //type casting
            return (this.id == cstm.getId() && this.fullName.equals(cstm.getFullName()) && this.email.equals(cstm.getEmail()));
        }
    }

    @Override
    public String toString() {
        return "-->> Customer id = " + id + ", fullName = " + fullName + ", email = " + email + ", password = " + password;
    }
}
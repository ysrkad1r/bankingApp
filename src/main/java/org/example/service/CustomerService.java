package org.example.service;

import org.example.model.Customer;
import org.example.repository.CustomerDAO;
import org.example.util.PasswordUtil;
import org.example.session.SessionManager;

public class CustomerService {
    private final CustomerDAO customerDAO;

    //Constructor method.
    public CustomerService() {
        this.customerDAO = new CustomerDAO();
    }

    //this method provides register operation for first registery.
    public boolean registerCustomer(String name, String email, String password) {
        if (email == null || email.isEmpty()) {return false;} // if email is not available return false
        if (customerDAO.getCustomerByEmail(email) != null) {return false;} // if email exists in db return false

        password = PasswordUtil.hashPassword(password); // this line encrypts password in BCrypt algoritm
        Customer newCustomer = new Customer(name, email, password);
        customerDAO.saveCustomer(newCustomer); // this method inserts customer to the db
        return true;
    }

    //this method provides a validation for user which already exists in db.
    public boolean loginCustomer(String email, String password) {
        if (email == null || email.isEmpty()) {return false;}

        Customer customer = customerDAO.getCustomerByEmail(email);
        if (customer == null) {return false;}

        if (PasswordUtil.checkPassword(password, customer.getPassword())) {
            SessionManager.getInstance().login(customer);
            return true;
        }
        return false;
    }

    public void deleteCustomer(int customerId) {
        boolean statusOdDeletion = customerDAO.deleteCustomer(customerId);
        if (statusOdDeletion) {
            System.out.println("Customer deleted successfully");
        }else {
            System.out.println("Customer could not be deleted");
        }
    }

    //getter method for DAO for testing not neccessary.
    public CustomerDAO getCustomerDAO() {
        return customerDAO;
    }
}
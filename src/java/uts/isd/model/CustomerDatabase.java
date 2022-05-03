package uts.isd.model;
import java.util.*;

public class CustomerDatabase {
    private Map<String, Customer> customers = new HashMap<String, Customer>();

    public void AddCustomer(Customer customer) {
        customers.put(customer.getUsername(), customer);
    }

    public Customer GetCustomer(String username, String password) {
        return CheckCustomer(username) && customers.get(username).ComparePassword(password) ? customers.get(username) : null;
    }

    public boolean CheckCustomer(String username) {
        return customers.containsKey(username);
    }
}

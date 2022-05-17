package uts.isd.model.dao;

import uts.isd.model.Customer;
import uts.isd.model.Item;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DBManager {
    private final Statement st;

    public DBManager(Connection conn) throws SQLException {
        st = conn.createStatement();
    }

    public Item findItem(String item)
        throws SQLException
    {
        String fetch = "select * from SHOPPING where ITEM = '" + item +"'";
        ResultSet rs = st.executeQuery(fetch);

        while (rs.next()) {
            float price = rs.getFloat(2);              
            String type = rs.getString(4);
            int stock = rs.getInt(5);
            return new Item(item, price, type, stock);
        }
        return null;
    }

    public ArrayList<Item> fetchItems(String sort) throws SQLException {
        String fetch = "select * from SHOPPING";
        fetch += sort == null ? "" : " ORDER BY " + sort;
        
        ResultSet rs = st.executeQuery(fetch);
        ArrayList<Item> temp = new ArrayList<>();

        while(rs.next()) {
            float price = rs.getFloat(2);
            String item = rs.getString(3);
            String type = rs.getString(4);
            int stock = rs.getInt(5);
            
            temp.add(new Item(item, price, type, stock));
        }
        return temp;
    }
    
    private Customer customerFromResult(ResultSet rs)
        throws SQLException
    {
        int customerID = rs.getInt("customerID");
        String email = rs.getString("email");
        String password = rs.getString("password");
        String firstName = rs.getString("firstName");
        String lastName = rs.getString("lastName");
        String dob = rs.getString("dob");
        String phone = rs.getString("phone");
        String street = rs.getString("street");
        String city = rs.getString("city");
        String state = rs.getString("state");
        String postcode = rs.getString("postcode");
        boolean isStaff = rs.getBoolean("isStaff");
        boolean isActive = rs.getBoolean("isActive");
        return new Customer(customerID, email, password, firstName, lastName, dob, phone, street, city, state, postcode, isStaff, isActive);
    }

    public Customer findCustomer(String email, String password)
        throws SQLException
    {
        return findCustomer("select * from Customers where EMAIL = '" + email + "' and PASSWORD = '" + password + "'");
    }
    
    public Customer findCustomer(int ID)
        throws SQLException
    {
        return findCustomer("select * from Customers where CustomerID = " + ID);
    }
    
    private Customer findCustomer(String fetch)
        throws SQLException
    {
        ResultSet rs = st.executeQuery(fetch);

        while (rs.next()) {
            return customerFromResult(rs);
        }
        return null;
    }
    
    public boolean isCustomerStaff(String email)
        throws SQLException
    {
        String fetch = "select * from Customers where EMAIL = '" + email + "'";
        ResultSet rs = st.executeQuery(fetch);

        while (rs.next()) {
            return rs.getBoolean(12);
        
        }
        return false;
    }

    private String appendParamterToString(String string, String parameter) {
        return string + ", '" + parameter + "'";
    }

    public Customer addCustomer(Customer customer)
        throws SQLException
    {
        String command = "INSERT INTO Customers (email, password, firstName, lastName, dob, phone, street, city, state, postcode) VALUES ('" + customer.getEmail() + "'";
        command = appendParamterToString(command, customer.getPassword());
        command = appendParamterToString(command, customer.getFirstName());
        command = appendParamterToString(command, customer.getLastName());
        command = appendParamterToString(command, customer.getDob());
        command = appendParamterToString(command, customer.getPhone());
        command = appendParamterToString(command, customer.getStreet());
        command = appendParamterToString(command, customer.getCity());
        command = appendParamterToString(command, customer.getState());
        command = appendParamterToString(command, customer.getPostcode());
        command += ")";
        st.executeUpdate(command);
        
        return findCustomer(customer.getEmail(), customer.getPassword());
    }

    private String appendParamToString(String string, String parameter, String specificParameter) {
        return string + ", "+ specificParameter + " = '" + parameter + "'";
    }

    public void updateItemDetails(String name, String type, float price, int stock)
        throws SQLException
    {
        String command = "UPDATE SHOPPING SET ITEM = '" + name  + "' ";
        command = appendParamToString(command, type, "Type");
        command += ", Price=" + price + ", stock=" + stock + " WHERE ITEM = '" + name + "'";
        System.out.println(command);
        st.executeUpdate(command);
    }

    public void updateCustomerDetails(String email, String firstName, String lastName, String dob, String phone, String street, String city, String state, String postcode)
        throws SQLException
    {
        String command = "UPDATE Customers SET FirstName = '" + firstName + "' ";
        command = appendParamToString(command, lastName, "LastName");
        command = appendParamToString(command, dob, "DOB");
        command = appendParamToString(command, phone, "Phone");
        command = appendParamToString(command, street, "Street");
        command = appendParamToString(command, city, "City");
        command = appendParamToString(command, state, "State");
        command = appendParamToString(command, postcode, "Postcode");
        command += "WHERE EMAIL = '" + email + "'";
        st.executeUpdate(command);
    }

    public void deleteItem(String item_name) throws SQLException {
        st.executeUpdate("DELETE FROM SHOPPING WHERE ITEM = '" + item_name +"'");
    }

    public void deleteCustomer(int ID) throws SQLException {
        st.executeUpdate("DELETE FROM Customers where CustomerID = " + ID);
    }

    public ArrayList<Customer> fetchCustomers() throws SQLException {
        String fetch = "select * from Customers";
        ResultSet rs = st.executeQuery(fetch);
        ArrayList<Customer> temp = new ArrayList<>();

        while (rs.next()) {
            if (rs.getBoolean("isStaff")) {
                continue;
            }
            temp.add(customerFromResult(rs));
        }

        return temp;
    }

    public boolean checkCustomer(String email)
        throws SQLException
    {
        String fetch = "select * from customers where EMAIL = '" + email + "'";
        ResultSet rs = st.executeQuery(fetch);

        while (rs.next()) {
            if (email.equals(rs.getString(2))) {
                return true;
            }
        }
        return false;
    }
}

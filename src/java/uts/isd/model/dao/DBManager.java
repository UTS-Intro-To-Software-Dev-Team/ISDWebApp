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

    private Item itemFromResult(ResultSet rs)
        throws SQLException
    {
        int id = rs.getInt("itemID");
        float price = rs.getFloat("price");
        String item = rs.getString("item");
        String type = rs.getString("type");
        int stock = rs.getInt("stock");
        return new Item(id, item, price, type, stock);
    }
    
    private Item itemFromResult(String fetch)
        throws SQLException
    {
        ResultSet rs = st.executeQuery(fetch);        
        return rs.next() ? itemFromResult(rs) : null;
    }
    
    public Item findItem(int id)
        throws SQLException
    {
        return itemFromResult("select * from SHOPPING where ITEMID = " + id);
    }
    
    public Item findItem(String itemName)
        throws SQLException
    {
        return itemFromResult("select * from SHOPPING where ITEM = '" + itemName + "'");
    }

    public ArrayList<Item> fetchItems(String sort) throws SQLException {
        String fetch = "select * from SHOPPING";
        fetch += sort == null ? "" : " ORDER BY " + sort;
        
        ResultSet rs = st.executeQuery(fetch);
        ArrayList<Item> temp = new ArrayList<>();

        while(rs.next()) {
            temp.add(itemFromResult(rs));
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
        return rs.next() ? customerFromResult(rs) : null;
    }

    private String appendParamterToString(String string, String parameter) {
        return string + ", '" + parameter + "'";
    }

    public Customer addCustomer(Customer customer)
        throws SQLException
    {
        String command = "INSERT INTO Customers (email, password, firstName, lastName, dob, phone, street, city, state, postcode, isActive) VALUES ('" + customer.getEmail() + "'";
        command = appendParamterToString(command, customer.getPassword());
        command = appendParamterToString(command, customer.getFirstName());
        command = appendParamterToString(command, customer.getLastName());
        command = appendParamterToString(command, customer.getDob());
        command = appendParamterToString(command, customer.getPhone());
        command = appendParamterToString(command, customer.getStreet());
        command = appendParamterToString(command, customer.getCity());
        command = appendParamterToString(command, customer.getState());
        command = appendParamterToString(command, customer.getPostcode());
        command += ", true)";
        System.out.println(command);
        st.executeUpdate(command);
        
        return findCustomer(customer.getEmail(), customer.getPassword());
    }
    
    public void addItem(Item item)
        throws SQLException
    {
        String command = "INSERT INTO SHOPPING (item, price, type, stock) values ('" + item.getItem() + "'";
        command += ", " + item.getPrice() + ", '" + item.getType() + "', " + item.getStock() + ")";
        st.executeUpdate(command);
    }

    private String appendParamToString(String string, String parameter, String specificParameter) {
        return string + ", "+ specificParameter + " = '" + parameter + "'";
    }

    public void updateItemDetails(int itemID, String name, String type, float price, int stock)
        throws SQLException
    {
        String command = "UPDATE SHOPPING SET ITEM = '" + name + "'";
        command = appendParamToString(command, type, "Type");
        command += ", Price=" + price + ", stock=" + stock + " WHERE ITEMID = " + itemID;
        st.executeUpdate(command);
    }

    public void updateCustomerDetails(String email, String firstName, String lastName, String dob, String phone, String street, String city, String state, String postcode, boolean isActive)
        throws SQLException
    {
        String command = "UPDATE Customers SET FirstName = '" + firstName + "'";
        command = appendParamToString(command, lastName, "LastName");
        command = appendParamToString(command, dob, "DOB");
        command = appendParamToString(command, phone, "Phone");
        command = appendParamToString(command, street, "Street");
        command = appendParamToString(command, city, "City");
        command = appendParamToString(command, state, "State");
        command = appendParamToString(command, postcode, "Postcode");
        command += ", isActive = " + isActive + " WHERE EMAIL = '" + email + "'";
        st.executeUpdate(command);
    }

    public void deleteItem(int ID) throws SQLException {
        st.executeUpdate("DELETE FROM SHOPPING WHERE ITEMID = " + ID);
    }

    public void deleteCustomer(int ID) throws SQLException {
        st.executeUpdate("DELETE FROM Customers where CustomerID = " + ID);
    }

    public ArrayList<Customer> fetchCustomers(String sort) throws SQLException {
        String fetch = "select * from Customers";
        fetch += sort == null ? "" : " ORDER BY " + sort;
        
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
        return rs.next() && email.equals(rs.getString(2));
    }
}

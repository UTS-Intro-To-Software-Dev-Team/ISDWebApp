package uts.isd.model.dao;

import uts.isd.model.*;

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

    private String appendParam(String parameter) {
        return ", '" + parameter + "'";
    }

    public Customer addCustomer(Customer customer)
        throws SQLException
    {
        String command = "INSERT INTO Customers (email, password, firstName, lastName, dob, phone, street, city, state, postcode, isActive) VALUES ('" + customer.getEmail() + "'";
        command += appendParam(customer.getPassword());
        command += appendParam(customer.getFirstName());
        command += appendParam(customer.getLastName());
        command += appendParam(customer.getDob());
        command += appendParam(customer.getPhone());
        command += appendParam(customer.getStreet());
        command += appendParam(customer.getCity());
        command += appendParam(customer.getState());
        command += appendParam(customer.getPostcode());
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
    
    public void addPayment(Payment payment, Customer customer)
        throws SQLException
    {
        String command = "INSERT INTO Payment (customerID, paymentMethod, cardNumber, fullName, expiryDate, Cvv) values (" + customer.getCustomerID();
        command += appendParam(payment.getPaymentMethod());
        command += appendParam(payment.getCardNumber());
        command += appendParam(payment.getFullName());
        command += appendParam(payment.getExpiryDate());
        command += appendParam(payment.getCvv());
        command += ")";
        st.executeUpdate(command);
    }
    
    public void addShipment(Shipment shipment, Customer customer)
        throws SQLException
    {
        String command = "INSERT INTO shipments (customerID, shipmentMethod) values (" + customer.getCustomerID();
        command += appendParam(shipment.getShipmentMethod());
        command += ")";
        st.executeUpdate(command);
    }

    private String appendParam(String parameter, String specificParameter) {
        return ", " + specificParameter + " = '" + parameter + "'";
    }

    public void updateItemDetails(int itemID, String name, String type, float price, int stock)
        throws SQLException
    {
        String command = "UPDATE SHOPPING SET ITEM = '" + name + "'";
        command += appendParam(type, "Type");
        command += ", Price=" + price + ", stock=" + stock + " WHERE ITEMID = " + itemID;
        st.executeUpdate(command);
    }
    
    public void updatePaymentDetails(int paymentID, Payment payment)
        throws SQLException
    {
        String command = "UPDATE Payment SET paymentMethod = '" + payment.getPaymentMethod() + "'";
        command += appendParam(payment.getCardNumber(), "cardNumber");
        command += appendParam(payment.getFullName(), "fullName");
        command += appendParam(payment.getExpiryDate(), "expiryDate");
        command += appendParam(payment.getCvv(), "cvv");
        command += " WHERE PaymentID = " + paymentID;
        st.executeUpdate(command);
    }
    
    public void updateShipmentDetails(int shipmentID, Shipment shipment)
        throws SQLException
    {
        String command = "UPDATE Shipments SET shipmentMethod = '" + shipment.getShipmentMethod() + "'";
        command += " WHERE shipmentID = " + shipmentID;
        st.executeUpdate(command);
    }

    public void updateCustomerDetails(String email, String firstName, String lastName, String dob, String phone, String street, String city, String state, String postcode, boolean isActive)
        throws SQLException
    {
        String command = "UPDATE Customers SET FirstName = '" + firstName + "'";
        command += appendParam(lastName, "LastName");
        command += appendParam(dob, "DOB");
        command += appendParam(phone, "Phone");
        command += appendParam(street, "Street");
        command += appendParam(city, "City");
        command += appendParam(state, "State");
        command += appendParam(postcode, "Postcode");
        command += ", isActive = " + isActive + " WHERE EMAIL = '" + email + "'";
        st.executeUpdate(command);
    }

    public void deleteItem(int ID) throws SQLException {
        st.executeUpdate("DELETE FROM Shopping WHERE itemID = " + ID);
    }

    public void deleteCustomer(int ID) throws SQLException {
        st.executeUpdate("DELETE FROM Customers where customerID = " + ID);
    }
    
    public void deletePayment(int ID) throws SQLException {
        st.executeUpdate("DELETE FROM Payment where paymentID = " + ID);
    }
    
    public void deleteShipment(int ID) throws SQLException {
        st.executeUpdate("DELETE FROM shipments where shipmentID = " + ID);
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
    
    private Shipment shipmentFromResult(ResultSet rs)
        throws SQLException
    {
        int shipmentID = rs.getInt("shipmentID");
        String shipmentMethod = rs.getString("shipmentMethod");
        return new Shipment(shipmentID, shipmentMethod);
    }
    
    public Shipment findShipment(int id) throws SQLException {
        String fetch = "select * from Shipments WHERE shipmentID = " + id;
        ResultSet rs = st.executeQuery(fetch);
        return rs.next() ? shipmentFromResult(rs) : null;
    }
    
    private Payment paymentFromResult(ResultSet rs)
        throws SQLException
    {
        int paymentID = rs.getInt("paymentID");
        String paymentMethod = rs.getString("paymentMethod");
        String cardNumber = rs.getString("cardNumber");
        String fullName = rs.getString("fullName");
        String expiryDate = rs.getString("expiryDate");
        String CVV = rs.getString("cvv");
        return new Payment(paymentID, paymentMethod, cardNumber, fullName, expiryDate, CVV);
    }
    
    public Payment findPayment(int id) throws SQLException {
        String fetch = "select * from Payment WHERE paymentID = " + id;
        ResultSet rs = st.executeQuery(fetch);
        return rs.next() ? paymentFromResult(rs) : null;
    }
    
    public ArrayList<Payment> fetchPaymentMethods(int id) throws SQLException {
        String fetch = "select * from Payment WHERE customerid=" + id;
        
        ResultSet rs = st.executeQuery(fetch);
        ArrayList<Payment> temp = new ArrayList<>();
        while(rs.next()) {
            temp.add(paymentFromResult(rs));
        }
        return temp;
    }
    
    public ArrayList<Shipment> fetchShipmentMethods(int customerId) throws SQLException {
        String fetch = "select * from Shipments WHERE customerid=" + customerId;
        
        ResultSet rs = st.executeQuery(fetch);
        ArrayList<Shipment> temp = new ArrayList<>();
        while(rs.next()) {
            temp.add(shipmentFromResult(rs));
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
    
    public boolean checkPayment(int customerId, String paymentMethod)
        throws SQLException
    {
        String fetch = "select * from payment where customerID = " + customerId + " and paymentMethod = '" + paymentMethod + "'";
        ResultSet rs = st.executeQuery(fetch);
        return rs.next() && paymentMethod.equals(rs.getString(3));
    }
    
    public boolean checkShipment(int customerId, String shipmentMethod)
        throws SQLException
    {
        String fetch = "select * from shipments where customerID = " + customerId + " and shipmentMethod = '" + shipmentMethod + "'";
        ResultSet rs = st.executeQuery(fetch);
        return rs.next() && shipmentMethod.equals(rs.getString(3));
    }
    
}

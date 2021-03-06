package uts.isd.model.dao;

import uts.isd.model.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
        String fetch = "select * from SHOPPING " + sort;
        
        ResultSet rs = st.executeQuery(fetch);
        ArrayList<Item> temp = new ArrayList<>();

        while(rs.next()) {
            temp.add(itemFromResult(rs));
        }
        return temp;
    }
    
    private AccessLogs accessLogsFromResult(ResultSet rs)
        throws SQLException
    {
        int id = rs.getInt("accessLogID");
        String date = rs.getString("date");
        String activity = rs.getString("activity");
        return new AccessLogs(id, date, activity);
    }
    
    public void addAccessLog(Customer customer, String activity)
        throws SQLException
    {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        String command = "INSERT INTO AccessLogs (customerID, Date, Activity) values (" + customer.getCustomerID();
        command += appendParam(dtf.format(LocalDateTime.now()));
        command += appendParam(activity);
        command += ")";
        st.executeUpdate(command);
    }
    
    public ArrayList<AccessLogs> fetchAccessLogs(int customerID, String sort) throws SQLException {
        String fetch = "select * from AccessLogs WHERE customerid=" + customerID + sort;
        
        ResultSet rs = st.executeQuery(fetch);
        ArrayList<AccessLogs> temp = new ArrayList<>();
        while(rs.next()) {
            temp.add(accessLogsFromResult(rs));
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

    public void addOrder(Order order, Customer customer)
        throws SQLException
    {
        String command = "INSERT INTO orders (customerID, itemName, paymentMethod, shippingMethod, status, quantity, totalPrice, date, street, city, state, postcode) values (" + customer.getCustomerID();
        command += appendParam(order.getItemName());
        command += appendParam(order.getPaymentMethod());
        command += appendParam(order.getShipmentMethod());
        command += appendParam(order.getStatus());
        command += appendParam(order.getQuantity());
        command += appendParam(order.getTotalPrice());
        command += appendParam(order.getDate());
        command += appendParam(order.getStreet());
        command += appendParam(order.getCity());
        command += appendParam(order.getState());
        command += appendParam(order.getPostcode());
        command += ")";
        st.executeUpdate(command);
    }
    
    private String appendParam(String parameter, String specificParameter) {
        return ", " + specificParameter + " = '" + parameter + "'";
    }
    
    private String appendParam(double parameter) {
        return ", " + parameter;
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

    public void updateCustomerDetails(int customerID, Customer customer, boolean isActive)
        throws SQLException
    {
        String command = "UPDATE Customers SET FirstName = '" + customer.getFirstName() + "'";
        command += appendParam(customer.getLastName(), "LastName");
        command += appendParam(customer.getDob(), "DOB");
        command += appendParam(customer.getPhone(), "Phone");
        command += appendParam(customer.getStreet(), "Street");
        command += appendParam(customer.getCity(), "City");
        command += appendParam(customer.getState(), "State");
        command += appendParam(customer.getPostcode(), "Postcode");
        command += ", isActive = " + isActive + " WHERE EMAIL = '" + customer.getEmail() + "'";
        st.executeUpdate(command);
    }
    
    public void updateCustomerDetails(int customerID, Customer customer)
        throws SQLException
    {
        updateCustomerDetails(customerID, customer, customer.isIsActive());
    }

    public void deleteItem(int ID) throws SQLException {
        st.executeUpdate("DELETE FROM Shopping WHERE itemID = " + ID);
    }

    public void deleteCustomer(int ID) throws SQLException {
        st.executeUpdate("DELETE FROM customers where customerID = " + ID);
    }
    
    public void deletePayment(int ID) throws SQLException {
        st.executeUpdate("DELETE FROM Payment where paymentID = " + ID);
    }
    
    public void deleteShipment(int ID) throws SQLException {
        st.executeUpdate("DELETE FROM shipments where shipmentID = " + ID);
    }

    public ArrayList<Customer> fetchCustomers(String sort) throws SQLException {
        String fetch = "select * from Customers " + sort;
        
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
    
    public ArrayList<Payment> fetchPaymentMethods(int id, String sort) throws SQLException {
        String fetch = "select * from Payment WHERE customerid=" + id + sort;
        
        ResultSet rs = st.executeQuery(fetch);
        ArrayList<Payment> temp = new ArrayList<>();
        while(rs.next()) {
            temp.add(paymentFromResult(rs));
        }
        return temp;
    }
    
    public ArrayList<Shipment> fetchShipmentMethods(int customerId, String sort) throws SQLException {
        String fetch = "select * from Shipments WHERE customerid=" + customerId + sort;
        
        ResultSet rs = st.executeQuery(fetch);
        ArrayList<Shipment> temp = new ArrayList<>();
        while(rs.next()) {
            temp.add(shipmentFromResult(rs));
        }
        return temp;
    }
    
    private Order orderFromResult(ResultSet rs)
        throws SQLException
    {
        int orderID = rs.getInt("orderID");
        String itemName = rs.getString("itemName");
        String paymentMethod = rs.getString("paymentMethod");
        String shipmentMethod = rs.getString("shippingMethod");
        String status = rs.getString("status");
        int quantity = rs.getInt("quantity");
        float totalPrice = rs.getFloat("totalPrice");
        String date = rs.getString("date");
        String street = rs.getString("street");
        String city = rs.getString("city");
        String state = rs.getString("state");
        String postcode = rs.getString("postcode");
        return new Order(orderID, itemName, paymentMethod, shipmentMethod, status, quantity, totalPrice, date, street, city, state, postcode);
    }
    
    public ArrayList<Order> fetchOrders(int customerId, String sort)
        throws SQLException
    {
        String fetch = "select * from orders WHERE customerid = " + customerId + sort;
        
        ResultSet rs = st.executeQuery(fetch);
        ArrayList<Order> temp = new ArrayList<>();
        while(rs.next()) {
            temp.add(orderFromResult(rs));
        }
        return temp;
    }
    
    private boolean doesStringExist(String comparer, String columnName, String query)
        throws SQLException
    {
        ResultSet rs = st.executeQuery(query);
        return rs.next() && comparer.equals(rs.getString(columnName));
    }

    public boolean doesCustomerExist(String email)
        throws SQLException
    {
        return doesStringExist(email, "email", "select * from customers where EMAIL = '" + email + "'");
    }
    
    public boolean doesPaymentExist(int customerId, String paymentMethod)
        throws SQLException
    {
        return doesStringExist(paymentMethod, "paymentMethod", "select * from payment where customerID = " + customerId + " and paymentMethod = '" + paymentMethod + "'");
    }
    
    public boolean doesShipmentExist(int customerId, String shipmentMethod)
        throws SQLException
    {
        return doesStringExist(shipmentMethod, "shipmentMethod", "select * from shipments where customerID = " + customerId + " and shipmentMethod = '" + shipmentMethod + "'");
    }
    
}

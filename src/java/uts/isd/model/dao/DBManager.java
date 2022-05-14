package uts.isd.model.dao;

import uts.isd.model.Customer;
import uts.isd.model.Item;
import uts.isd.model.Shipment;

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

    public Shipment findMethod(String selectedMethod)
        throws SQLException
    {
        String fetch = "select * from Shipment_Methods WHERE shipmentMethodName = '" + selectedMethod + "'";
        ResultSet rs = st.executeQuery(fetch);
        while (rs.next()){
            String method = rs.getString(1);
            return new Shipment(method);
        }
        return null;
    }

    public Shipment findShipment(String selectedShipmentId, String selectedOrderId) throws SQLException
    {
        String fetch = "select * from Order_Shipment WHERE orderId = '" + selectedOrderId + "' and shipmentId='" + selectedShipmentId + "'";
        ResultSet rs = st.executeQuery(fetch);
        while (rs.next()){
            String shippingId = rs.getString(1);
            String shippingDate = rs.getString(2);
            String method = rs.getString(3);
            String street = rs.getString(4);
            String city = rs.getString(5);
            String state = rs.getString(6);
            String postCode = rs.getString(7);
            String orderId = rs.getString(8);
            return new Shipment(shippingId, shippingDate, street, city, state, postCode, method, orderId);
        }
        return null;
    }

    public ArrayList<Shipment> fetchMethods() throws SQLException
    {
        String fetch = "select * from Shipment_Methods";
        ResultSet rs = st.executeQuery(fetch);
        ArrayList<Shipment> temp = new ArrayList<>();

        while (rs.next()) {
            String method = rs.getString(2);
            System.out.println(method);
            temp.add(new Shipment(method));
        }

        return temp;
    }

   public ArrayList<Shipment> fetchShipment(String selectedOrderId) throws SQLException
   {
        String fetch = "select * from Order_Shipment where orderId = '" + selectedOrderId + "'";
        ResultSet rs = st.executeQuery(fetch);
        ArrayList<Shipment> temp = new ArrayList<>();

        while (rs.next()){
            String shippingId = rs.getString(1);
            String shippingDate = rs.getString(2);
            String method = rs.getString(3);
            String street = rs.getString(4);
            String city = rs.getString(5);
            String state = rs.getString(6);
            String postCode = rs.getString(7);
            String orderId = rs.getString(8);
            temp.add(new Shipment(shippingId, shippingDate, street, city, state, postCode, method, orderId));
        }
        return temp;
   }

   public void addShipment(String shipmentId, String shipmentDate, String street, String city, String state, String postCode, String method, String orderId) throws SQLException
   {
        String command = "INSERT INTO ISDUSER.Order_Shipment VALUES ('" + shipmentId;
        command = appendParamterToString(command, shipmentDate);
        command = appendParamterToString(command, street);
        command = appendParamterToString(command, city);
        command = appendParamterToString(command, state);
        command = appendParamterToString(command, postCode);
        command = appendParamterToString(command, orderId);
        command += "')";
        System.out.println(command);
        st.executeUpdate(command);
   }

    public Item findItem(String item, float price, String type, int stock)
        throws SQLException
    {
        String fetch = "select * from SHOPPING where ITEM = '" + item +"'";
        ResultSet rs = st.executeQuery(fetch);

        while (rs.next()) {
            if (item.equals(rs.getString(1))) {
                price = rs.getFloat(2);              
                type = rs.getString(3);
                stock = rs.getInt(4);
                return new Item(item, price, type, stock);
            }
        }
        return null;
    }

    public ArrayList<Item>  fetchItems() throws SQLException {
        String fetch = "select * from SHOPPING";
        ResultSet rs = st.executeQuery(fetch);
        ArrayList<Item> temp = new ArrayList<>();

        while(rs.next()) {
            float price = rs.getFloat(1);
            String item = rs.getString(2);
            String type = rs.getString(3);
            int stock = rs.getInt(4);
            
            temp.add(new Item(item, price, type, stock));
        }
        return temp;
    }

    public Customer findCustomer(String email, String password)
        throws SQLException
    {
        String fetch = "select * from Customers where EMAIL = '" + email + "'";
        ResultSet rs = st.executeQuery(fetch);

        while (rs.next()) {
            if (email.equals(rs.getString(2)) && password.equals(rs.getString(3))) {
                String firstName = rs.getString(4);
                String lastName = rs.getString(5);
                String dob = rs.getString(6);
                String phone = rs.getString(7);
                String street = rs.getString(8);
                String city = rs.getString(9);
                String state = rs.getString(10);
                String postcode = rs.getString(11);
                return new Customer(email, password, firstName, lastName, dob, phone, street, city, state, postcode);
            }
        }
        return null;
    }

    private String appendParamterToString(String string, String parameter) {
        return string + "', '" + parameter;
    }

    public void addCustomer(Customer customer)
        throws SQLException
    {
        String command = "INSERT INTO Customers (email, password, firstName, lastName, dob, phone, street, city, state, postcode) VALUES ('" + customer.getEmail();
        command = appendParamterToString(command, customer.getPassword());
        command = appendParamterToString(command, customer.getFirstName());
        command = appendParamterToString(command, customer.getLastName());
        command = appendParamterToString(command, customer.getDob());
        command = appendParamterToString(command, customer.getPhone());
        command = appendParamterToString(command, customer.getStreet());
        command = appendParamterToString(command, customer.getCity());
        command = appendParamterToString(command, customer.getState());
        command = appendParamterToString(command, customer.getPostcode());
        command += "')";
        System.out.println(command);
        st.executeUpdate(command);
    }

    private String appendParamToString(String string, String parameter, String specificParameter) {
        return string + "', "+ specificParameter + " = '" + parameter;
    }

    public void updateCustomerDetails(String email, String firstName, String lastName, String dob, String phone, String street, String city, String state, String postcode)
        throws SQLException
    {
        String command = "UPDATE Customers SET FirstName = '" + firstName;
        command = appendParamToString(command, lastName, "LastName");
        command = appendParamToString(command, dob, "DOB");
        command = appendParamToString(command, phone, "Phone");
        command = appendParamToString(command, street, "Street");
        command = appendParamToString(command, city, "City");
        command = appendParamToString(command, state, "State");
        command = appendParamToString(command, postcode, "Postcode");
        command += "' WHERE EMAIL = '" + email + "'";
        st.executeUpdate(command);
    }

    public void deleteCustomer(String email) throws SQLException {
        st.executeUpdate("DELETE FROM Customers WHERE EMAIL = '" + email + "'");
    }

    public ArrayList<Customer> fetchCustomers() throws SQLException {
        String fetch = "select * from Customers";
        ResultSet rs = st.executeQuery(fetch);
        ArrayList<Customer> temp = new ArrayList<>();

        while (rs.next()) {
            String email = rs.getString(1);
            String password = rs.getString(2);
            String firstName = rs.getString(3);
            String lastName = rs.getString(4);
            String dob = rs.getString(5);
            String street = rs.getString(6);
            String city = rs.getString(7);
            String state = rs.getString(8);
            String postcode = rs.getString(9);
            //temp.add(new Customer(email, password, firstName, lastName, dob, street, city, state, postcode));
        }

        for (Customer cus : temp) {
            System.out.println(cus.getFirstName());
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

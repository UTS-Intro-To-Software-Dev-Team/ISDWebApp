package uts.isd.model.dao;

import uts.isd.model.Customer;

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

    public Customer findCustomer(String email, String password)
        throws SQLException
    {
        String fetch = "select * from Customers where EMAIL = '" + email + "' and PASSWORD = '" + password + "'";
        ResultSet rs = st.executeQuery(fetch);

        while (rs.next()) {
            if (email.equals(rs.getString(1)) && password.equals(rs.getString(2))) {
                String firstName = rs.getString(3);
                String lastName = rs.getString(4);
                String dob = rs.getString(5);
                String street = rs.getString(6);
                String city = rs.getString(7);
                String state = rs.getString(8);
                String postcode = rs.getString(9);
                return new Customer(email, password, firstName, lastName, dob, street, city, state, postcode);
            }
        }
        return null;
    }

    private String appendParamterToString(String string, String parameter) {
        return string + "', '" + parameter;
    }

    public void addCustomer(String email, String password, String firstName, String lastName, String dob, String street, String city, String state, String postcode)
        throws SQLException
    {
        String command = "INSERT INTO ISDUSER.Customers VALUES ('" + email;
        command = appendParamterToString(command, password);
        command = appendParamterToString(command, firstName);
        command = appendParamterToString(command, lastName);
        command = appendParamterToString(command, dob);
        command = appendParamterToString(command, street);
        command = appendParamterToString(command, city);
        command = appendParamterToString(command, state);
        command = appendParamterToString(command, postcode);
        command += "')";
        System.out.println(command);
        st.executeUpdate(command);
    }

    private String appendParamterToString(String string, String parameter, String specificParameter) {
        return string + "', "+ specificParameter + " = '" + parameter;
    }

    public void updateCustomerDetails(String email, String password, String firstName, String lastName, String dob, String street, String city, String state, String postcode)
        throws SQLException
    {
        String command = "UPDATE ISDUSER.Customers SET FirstName = '" + firstName;
        command = appendParamterToString(command, lastName, "LastName");
        command = appendParamterToString(command, dob, "DOB");
        command = appendParamterToString(command, street, "Street");
        command = appendParamterToString(command, city, "city");
        command = appendParamterToString(command, state, "state");
        command = appendParamterToString(command, postcode, "postcode");
        command += "' WHERE EMAIL = '" + email + "' and PASSWORD = '" + password + "'";
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
            temp.add(new Customer(email, password, firstName, lastName, dob, street, city, state, postcode));
        }
        
        for (Customer cus : temp) {
            System.out.println(cus.getFirstName());
        }
        
        return temp;
    }

    public boolean checkCustomer(String email, String password)
        throws SQLException
    {
        String fetch = "select * from Students where EMAIL = '" + email + "' and password = '" + password + "'";
        ResultSet rs = st.executeQuery(fetch);

        while (rs.next()) {
            if (email.equals(rs.getString(2)) && password.equals(rs.getString(3))) {
                return true;
            }
        }
        return false;
    }
}

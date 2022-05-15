package uts.isd.controller;

import java.io.IOException;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.logging.Level;
import java.util.logging.Logger;

import uts.isd.model.Customer;
import uts.isd.model.Shipment;
import uts.isd.model.dao.DBManager;

public class DBServlet extends HttpServlet {
    private HttpSession session;
    private DBManager manager;
    private String redirect;
    private String forward;
    private Validator validator;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        session = request.getSession();
        manager = (DBManager)session.getAttribute("manager");
        validator = new Validator(session);

        redirect = null;
        forward = (String)session.getAttribute("pageName");
        switch(forward) {
            case "register.jsp" -> RegisterServlet(request, response);
            case "login.jsp" -> LoginServlet(request, response);
            case "edit.jsp" -> EditServlet(request, response);
            case "adminEdit.jsp" -> AdminEditServlet(request, response);
            case "userManagement.jsp" -> UserManagementServlet(request, response);
            case "shoppingPage.jsp" -> ShoppingServlet(request, response);
            case "shipmentPage.jsp" -> ShipmentServlet(request, response);
            case "createShipment.jsp" -> CreateShipmentServlet(request, response);
            default -> System.out.println("Unknown page: " + session.getAttribute("pageName"));
        }

        if (redirect == null) {
            request.getRequestDispatcher(forward).forward(request, response);
        } else {
            response.sendRedirect(redirect);
        }
    }

    private void RegisterServlet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String dob = request.getParameter("dob");
        String phone = request.getParameter("phone");
        String street = request.getParameter("street");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        String postcode = request.getParameter("postcode");

        try {
            if (!invalidDataCheck(email, password, firstName, lastName, dob, phone, postcode)) {
                Customer customer = new Customer(email, password, firstName, lastName, dob, phone, street, city, state, postcode);
                manager.addCustomer(customer);
                session.setAttribute("customer", customer);
                redirect = "homePage.jsp";
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void LoginServlet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            Customer customer = manager.findCustomer(email, password);
            if (customer != null) {
                session.setAttribute("customer", customer);
                redirect = "homePage.jsp";
            } else {
                session.setAttribute("emailErr", manager.checkCustomer(email) ? null : "Email does not exist.");
                session.setAttribute("passErr", "Password is incorrect.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void EditServlet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        Customer customer = (Customer)session.getAttribute("customer");
        String email = customer.getEmail();
        String password = customer.getPassword();
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String dob = request.getParameter("dob");
        String phone = request.getParameter("phone");
        String street = request.getParameter("street");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        String postcode = request.getParameter("postcode");

        try {
            if (!invalidDataCheck(firstName, lastName, dob, phone, postcode)) {
                manager.updateCustomerDetails(email, firstName, lastName, dob, phone, street, city, state, postcode);
                session.setAttribute("customer", manager.findCustomer(email, password));
                redirect = "edit.jsp";
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void ShoppingServlet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        String sort = (String)session.getAttribute("sort");
        
        switch(request.getParameter("button")) {
            case "sortType" -> {
                if (sort == null || !sort.equals("TYPE ASC")) {
                    session.setAttribute("sort", "TYPE ASC");
                } else {
                    session.setAttribute("sort", "TYPE DESC");
                }
            }

            case "sortName" -> {
                if (sort == null || !sort.equals("ITEM ASC")) {
                    session.setAttribute("sort", "ITEM ASC");
                } else {
                    session.setAttribute("sort", "ITEM DESC");
                }
            }

            case "order" -> {
                session.setAttribute("itemName", request.getParameter("itemName"));
                Customer customer = (Customer)session.getAttribute("customer");
                if (customer == null) {
                    redirect = "login.jsp";
                }
                if (session.getAttribute("itemName") != null) {
                    redirect = "orderItem.jsp";
                }
            }
        }
    }

    private boolean invalidDataCheck(String email, String password, String firstName, String lastName, String dob, String phone, String postcode)
            throws SQLException
    {
        boolean hasFailed = false;
        String message = "Email format is incorrect. An example: JohnSmith@Mail.com";
        hasFailed = dataCheck(hasFailed, validator.validatePattern("email", email), "emailErr", message);

        message = "Password must contain:";
        message += "<br>1. At least one digit [0-9].";
        message += "<br>2. At least one lowercase Latin character [a-z].";
        message += "<br>3. At least one uppercase Latin character [A-Z].";
        message += "<br>4. at least one special character like ! @ # & ( ).";
        message += "<br>5. A length of at least 8 characters.";
        hasFailed = dataCheck(hasFailed, validator.validatePattern("password", password), "passErr", message);

        hasFailed = hasFailed || invalidDataCheck(firstName, lastName, dob, phone, postcode);

        message = "Customer already exists.";
        hasFailed = dataCheck(hasFailed, !manager.checkCustomer(email), "existErr", message);

        return hasFailed;
    }

    private boolean invalidDataCheck(String firstName, String lastName, String dob, String phone, String postcode)
            throws SQLException
    {
        boolean hasFailed = false;
        String message = "This should only contain letters and start with a capitalised letter.";
        hasFailed = dataCheck(hasFailed, validator.validatePattern("name", firstName), "firstNameErr", message);
        hasFailed = dataCheck(hasFailed, validator.validatePattern("name", lastName), "lastNameErr", message);

        message = "Minimum age is 13.";
        hasFailed = dataCheck(hasFailed, validator.validatePattern("age", dob), "dateErr", message);

        message = "Not a valid phone number.";
        hasFailed = dataCheck(hasFailed, validator.validatePattern("phone", phone), "phoneErr", message);

        message = "Postcode must be a 4 digit number.";
        hasFailed = dataCheck(hasFailed, validator.validatePattern("postcode", postcode), "postcodeErr", message);

        return hasFailed;
    }

    private boolean dataCheck(boolean hasFailed, boolean valid, String attribute, String message) {
        session.setAttribute(attribute, valid ? null : message);
        return hasFailed || !valid;
    }

    private void ShipmentServlet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        String method = request.getParameter("method");
        String shipmentId = request.getParameter("shipmentId");
        String orderId = request.getParameter("orderId");

        try{
            Shipment shipmentMethod = manager.findMethod(method);
            session.setAttribute("method", shipmentMethod);

            //Shipment shipment = manager.findShipment(shipmentId, orderId);
            //session.setAttribute("shipment", shipment);
        }
        catch (SQLException ex) {
            Logger.getLogger(ConnServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void CreateShipmentServlet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {

        String shipmentId = request.getParameter("shipmentId");
        String shipmentDate = request.getParameter("date");
        String street = request.getParameter("street");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        String postcode = request.getParameter("postcode");
        String method = request.getParameter("method");
        String orderId = request.getParameter("orderId");

        redirect = "createShipment";
        try {
            //if (!invalidDataCheck(email, password, firstName, lastName, dob, postcode)) {
                manager.addShipment(shipmentId, shipmentDate, street, city, state, postcode, method, orderId);
                session.setAttribute("shipment", new Shipment(shipmentId, shipmentDate, street, city, state, postcode, method, orderId));
                redirect = "shipmentPage";
            //}
        } catch (SQLException ex) {
            Logger.getLogger(ConnServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void UserManagementServlet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String email = request.getParameter("customerEmail");
        
        try {
            switch (request.getParameter("button")){
                case "edit" -> {
                    session.setAttribute("customer2", manager.findCustomer(email));
                    redirect = "adminEdit.jsp";
                }

                case "delete" -> {
                        if (email != null){
                            manager.deleteCustomer(email);
                        }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void AdminEditServlet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        Customer customer = (Customer)session.getAttribute("customer2");
        String email = customer.getEmail();
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String dob = request.getParameter("dob");
        String phone = request.getParameter("phone");
        String street = request.getParameter("street");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        String postcode = request.getParameter("postcode");

        try {
            if (!invalidDataCheck(firstName, lastName, dob, phone, postcode)) {
                manager.updateCustomerDetails(email, firstName, lastName, dob, phone, street, city, state, postcode);
                session.setAttribute("customer2", null);
                redirect = "userManagement.jsp";
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

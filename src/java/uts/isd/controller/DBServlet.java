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
import uts.isd.model.dao.DBManager;

public class DBServlet extends HttpServlet {
    private HttpSession session;
    private DBManager manager;
    private String redirect;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        session = request.getSession();
        manager = (DBManager)session.getAttribute("manager");

        redirect = "homePage";
        switch((String)session.getAttribute("pageName")) {
            case "register.jsp" -> RegisterServlet(request, response);
            case "login.jsp" -> LoginServlet(request, response);
            case "edit.jsp" -> EditServlet(request, response);
            default -> System.out.println("Unknown page: " + session.getAttribute("pageName"));
        }
        
        request.getRequestDispatcher(redirect + ".jsp").forward(request, response);
    }

    private void RegisterServlet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String dob = request.getParameter("dob");
        String street = request.getParameter("street");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        String postcode = request.getParameter("postcode");

        redirect = "register";
        try {
            if (!invalidDataCheck(email, password, firstName, lastName, dob, postcode)) {
                manager.addCustomer(email, password, firstName, lastName, dob, street, city, state, postcode);
                session.setAttribute("customer", new Customer(email, password, firstName, lastName, dob, street, city, state, postcode));
                redirect = "homePage";
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private boolean invalidDataCheck(String email, String password, String firstName, String lastName, String dob, String postcode) throws SQLException {
        Validator validator = new Validator(session);

        boolean hasFailed = false;
        String message = "Email format is incorrect. An example: JohnSmith@Mail.com";
        hasFailed = dataCheck(hasFailed, validator.validateEmail(email), "emailErr", message);

        message = "Password must contain:";
        message += "<br>1. At least one digit [0-9].";
        message += "<br>2. At least one lowercase Latin character [a-z].";
        message += "<br>3. At least one uppercase Latin character [A-Z].";
        message += "<br>4. at least one special character like ! @ # & ( ).";
        message += "<br>5. A length of at least 8 characters.";
        hasFailed = dataCheck(hasFailed, validator.validatePassword(password), "passErr", message);

        message = "This should only contain letters and start with a capitalised letter.";
        hasFailed = dataCheck(hasFailed, validator.validateName(firstName), "firstNameErr", message);
        hasFailed = dataCheck(hasFailed, validator.validateName(lastName), "lastNameErr", message);

        message = "Minimum age is 13.";
        hasFailed = dataCheck(hasFailed, validator.validateAge(dob), "dateErr", message);

        message = "Postcode must be a 4 digit number.";
        hasFailed = dataCheck(hasFailed, validator.validatePostCode(postcode), "postcodeErr", message);

        message = "Customer already exists.";
        hasFailed = dataCheck(hasFailed, manager.findCustomer(email, password) == null, "existErr", message);

        return hasFailed;
    }

    private boolean dataCheck(boolean hasFailed, boolean valid, String attribute, String message) {
        session.setAttribute(attribute, valid ? null : message);
        return hasFailed || !valid;
    }

    private void LoginServlet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Validator validator = new Validator(session);

        redirect = "login";
        try {
            Customer customer = manager.findCustomer(email, password);
            if (customer != null) {
                session.setAttribute("customer", customer);
                redirect = "homePage";
            } else {
                session.setAttribute("emailErr", validator.validateEmail(email) ? null : "Email does not exist.");
                session.setAttribute("passErr", validator.validatePassword(password) ? null : "Password is incorrect.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void EditServlet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String dob = request.getParameter("dob");
        String street = request.getParameter("street");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        String postcode = request.getParameter("postcode");
        
        Customer customer = null;
        redirect = "edit";
        try {
            customer = manager.findCustomer(email, password);
            if (customer != null) {
                manager.updateCustomerDetails(email, password, firstName, lastName, dob, street, city, state, postcode);
                customer = manager.findCustomer(email, password);
                session.setAttribute("customer", customer);
            } else {
                session.setAttribute("existErr", "Customer does not exist in the Database!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

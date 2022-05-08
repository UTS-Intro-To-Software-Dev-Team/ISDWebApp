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

public class RegisterServlet extends HttpServlet {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String dob;
    private String street;
    private String city;
    private String state;
    private String postcode;

    private HttpSession session;
    private DBManager manager;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        session = request.getSession();
        manager = (DBManager)session.getAttribute("manager");

        email = request.getParameter("email");
        password = request.getParameter("password");
        firstName = request.getParameter("firstName");
        lastName = request.getParameter("lastName");
        dob = request.getParameter("dob");
        street = request.getParameter("street");
        city = request.getParameter("city");
        state = request.getParameter("state");
        postcode = request.getParameter("postcode");

        String redirect = "register";
        try {
            if (!invalidDataCheck()) {
                manager.addCustomer(email, password, firstName, lastName, dob, street, city, state, postcode);
                session.setAttribute("customer", new Customer(email, password, firstName, lastName, dob, street, city, state, postcode));
                redirect = "homePage";
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.getRequestDispatcher(redirect + ".jsp").include(request, response);
    }

    private boolean invalidDataCheck() throws SQLException {
        Validator validator = new Validator(session);

        boolean hasFailed = false;
        String message = "Email format is incorrect. An example: JohnSmith@Mail.com";
        hasFailed = dataCheck(hasFailed, validator.validateEmail(email), "emailErr", message);

        message = "Password must contain:<br>1. At least one digit [0-9].<br>2. at least one lowercase Latin character [a-z].<br>3. at least one uppercase Latin character [A-Z].<br>4. at least one special character like ! @ # & ( ).<br>5. a length of at least 8 characters.";
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
}

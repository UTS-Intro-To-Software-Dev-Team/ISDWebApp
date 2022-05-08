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

public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        DBManager manager = (DBManager)session.getAttribute("manager");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Validator validator = new Validator(session);

        String redirect = "login";
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
        request.getRequestDispatcher(redirect + ".jsp").include(request, response);
    }
}

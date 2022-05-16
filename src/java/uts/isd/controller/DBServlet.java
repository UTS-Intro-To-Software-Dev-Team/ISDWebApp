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
import uts.isd.model.Item;
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
            case "itemEdit.jsp" -> ItemEditServlet(request, response);
            case "addItem.jsp" -> AddItemServlet(request, response);
            case "itemManagement.jsp" -> ItemManagementServlet(request, response);
            case "userManagement.jsp" -> UserManagementServlet(request, response);
            case "shoppingPage.jsp" -> ShoppingServlet(request, response);
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
                session.setAttribute("customer", manager.addCustomer(customer));
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
            if (customer != null && customer.isIsActive()) {
                session.setAttribute("customer", customer);
                redirect = "homePage.jsp";
            } else if (!manager.checkCustomer(email)) {
                session.setAttribute("emailErr", "Email does not exist.");
            } else if (customer != null && !customer.isIsActive()) {
                session.setAttribute("emailErr", "The current accout is inactive.");
            } else {
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
                manager.updateCustomerDetails(email, firstName, lastName, dob, phone, street, city, state, postcode, customer.isIsActive());
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
                session.setAttribute("itemID", request.getParameter("itemID"));
                redirect = "orderItem.jsp";
            }
        }
    }

    private boolean invalidDataCheck(String email, String password, String firstName, String lastName, String dob, String phone, String postcode)
            throws SQLException
    {
        boolean hasFailed = false;
        String message = "Email format is incorrect. An example: JohnSmith@Mail.com";
        hasFailed = DataCheck(hasFailed, validator.validatePattern("email", email), "emailErr", message);

        message = "Password must contain:";
        message += "<br>1. At least one digit [0-9].";
        message += "<br>2. At least one lowercase Latin character [a-z].";
        message += "<br>3. At least one uppercase Latin character [A-Z].";
        message += "<br>4. at least one special character like ! @ # & ( ).";
        message += "<br>5. A length of at least 8 characters.";
        hasFailed = DataCheck(hasFailed, validator.validatePattern("password", password), "passErr", message);

        hasFailed = hasFailed || invalidDataCheck(firstName, lastName, dob, phone, postcode);

        message = "Customer already exists.";
        hasFailed = DataCheck(hasFailed, !manager.checkCustomer(email), "existErr", message);

        return hasFailed;
    }

    private boolean invalidDataCheck(String firstName, String lastName, String dob, String phone, String postcode)
            throws SQLException
    {
        boolean hasFailed = false;
        String message = "This should only contain letters and start with a capitalised letter.";
        hasFailed = DataCheck(hasFailed, validator.validatePattern("name", firstName), "firstNameErr", message);
        hasFailed = DataCheck(hasFailed, validator.validatePattern("name", lastName), "lastNameErr", message);

        message = "Minimum age is 13.";
        hasFailed = DataCheck(hasFailed, validator.validatePattern("age", dob), "dateErr", message);

        message = "Not a valid phone number.";
        hasFailed = DataCheck(hasFailed, validator.validatePattern("phone", phone), "phoneErr", message);

        message = "Postcode must be a 4 digit number.";
        hasFailed = DataCheck(hasFailed, validator.validatePattern("postcode", postcode), "postcodeErr", message);

        return hasFailed;
    }

    private boolean DataCheck(boolean hasFailed, boolean valid, String attribute, String message) {
        session.setAttribute(attribute, valid ? null : message);
        return hasFailed || !valid;
    }

    private void ItemManagementServlet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        String ID = request.getParameter("itemID");        
        int itemID = ID == null ? -1 : Integer.parseInt(ID);
        String sort = (String)session.getAttribute("sort");
        
        try {
            switch (request.getParameter("button")){
                case "edit" -> {
                    session.setAttribute("item", manager.findItem(itemID));
                    redirect = "itemEdit.jsp";
                }
                
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

                case "delete" -> {
                    manager.deleteItem(itemID);
                }
                
                case "add" -> redirect = "addItem.jsp";
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void UserManagementServlet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String ID = request.getParameter("customerID");
        int customerID = ID == null ? -1 : Integer.parseInt(ID);
        String sort = (String)session.getAttribute("sort");
        
        try {
            switch (request.getParameter("button")){
                case "edit" -> {
                    session.setAttribute("customer2", manager.findCustomer(customerID));
                    redirect = "adminEdit.jsp";
                }
                
                case "sortNumber" -> {
                    if (sort == null || !sort.equals("phone ASC")) {
                        session.setAttribute("sort", "phone ASC");
                    } else {
                        session.setAttribute("sort", "phone DESC");
                    }
                }

                case "sortFirstName" -> {
                    if (sort == null || !sort.equals("firstName ASC")) {
                        session.setAttribute("sort", "firstName ASC");
                    } else {
                        session.setAttribute("sort", "firstName DESC");
                    }
                }
                
                case "sortLastName" -> {
                    if (sort == null || !sort.equals("lastName ASC")) {
                        session.setAttribute("sort", "lastName ASC");
                    } else {
                        session.setAttribute("sort", "lastName DESC");
                    }
                }

                case "delete" -> {
                    manager.deleteCustomer(customerID);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void ItemEditServlet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        Item item = (Item)session.getAttribute("item");
        String item_name = request.getParameter("itemName");
        float price = Float.parseFloat(request.getParameter("itemPrice"));
        String type = request.getParameter("itemType");
        int stock = Integer.parseInt(request.getParameter("itemStock"));

        try {
            if (!invalidItemDataCheck(item_name, type, price, stock)) {
                manager.updateItemDetails(item.getItemID(), item_name, type, price, stock);
                session.setAttribute("item", null);
                redirect = "itemManagement.jsp";
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private boolean invalidItemDataCheck(String item_name, String type, float price, int stock)
        throws SQLException
    {
        boolean hasFailed = false;
        String message = "First letter of each word must be either a capital letter or a number.";
        hasFailed = DataCheck(hasFailed, validator.validatePattern("item", item_name), "nameErr", message);
        
        message = "This should only contain letters and start with a capitalised letter.";
        hasFailed = DataCheck(hasFailed, validator.validatePattern("name", type), "typeErr", message);
        
        message = "Invalid price.";
        hasFailed = DataCheck(hasFailed, price >= 0, "priceErr", message);
        
        message = "Invalid stock number.";
        hasFailed = DataCheck(hasFailed, stock >= 0, "stockErr", message);
        
        return hasFailed;
    }
    
    private boolean invalidItemDataCheck(Item item)
        throws SQLException
    {
        boolean hasFailed = false;
        String message = "This item already exists.";
        hasFailed = DataCheck(hasFailed, manager.findItem(item.getItem()) == null, "existsErr", message);
        hasFailed = hasFailed || invalidItemDataCheck(item.getItem(), item.getType(), item.getPrice(), item.getStock());
        return hasFailed;
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
        boolean isActive = request.getParameter("isActive") != null;

        try {
            if (!invalidDataCheck(firstName, lastName, dob, phone, postcode)) {
                manager.updateCustomerDetails(email, firstName, lastName, dob, phone, street, city, state, postcode, isActive);
                session.setAttribute("customer2", null);
                redirect = "userManagement.jsp";
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void AddItemServlet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String item_name = request.getParameter("itemName");
        float price = Float.parseFloat(request.getParameter("itemPrice"));
        String type = request.getParameter("itemType");
        int stock = Integer.parseInt(request.getParameter("itemStock"));
        
        Item item = new Item(item_name, price, type, stock);
        try {
            if (!invalidItemDataCheck(item)) {
                manager.addItem(item);
                redirect = "itemManagement.jsp";
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

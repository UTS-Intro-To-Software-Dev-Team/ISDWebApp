package uts.isd.controller;

import java.io.IOException;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

import uts.isd.model.*;
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
        try {
            switch(forward) {
                case "register.jsp" -> RegisterServlet(request);
                case "login.jsp" -> LoginServlet(request);

                case "accountPage.jsp" -> AccountPageServlet(request);
                case "edit.jsp" -> EditServlet(request);

                case "paymentMethods.jsp" -> PaymentMethodsServlet(request);
                case "paymentAdd.jsp" -> PaymentAddServlet(request);
                case "paymentEdit.jsp" -> PaymentEditServlet(request);

                case "shipmentMethods.jsp" -> ShipmentMethodsServlet(request);
                case "shipmentAdd.jsp" -> ShipmentAddServlet(request);
                case "shipmentEdit.jsp" -> ShipmentEditServlet(request);

                case "userManagement.jsp" -> UserManagementServlet(request);
                case "userManagementEdit.jsp" -> UserManagmentEditServlet(request);

                case "itemManagement.jsp" -> ItemManagementServlet(request);
                case "itemAdd.jsp" -> ItemAddServlet(request);
                case "itemEdit.jsp" -> ItemEditServlet(request);

                case "shoppingPage.jsp" -> ShoppingServlet(request);
                case "orderItem.jsp" -> OrderItemServlet(request);
                case "orderPurchase.jsp" -> OrderPurchaseServlet(request);

                default -> System.out.println("Unknown page: " + forward);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (redirect == null) {
            request.getRequestDispatcher(forward).forward(request, response);
        } else {
            response.sendRedirect(redirect);
        }
    }

    //
    //LOGIN/REGISTER SERVLETS
    //
    private void RegisterServlet(HttpServletRequest request)
        throws ServletException, IOException, SQLException
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

        if (!invalidRegisterCheck(email, password, firstName, lastName, dob, phone, postcode)) {
            Customer customer = new Customer(email, password, firstName, lastName, dob, phone, street, city, state, postcode);
            session.setAttribute("customer", manager.addCustomer(customer));
            redirect = "homePage.jsp";
        }
    }

    private void LoginServlet(HttpServletRequest request)
        throws ServletException, IOException, SQLException
    {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Customer customer = manager.findCustomer(email, password);
        if (customer != null && customer.isIsActive()) {
            session.setAttribute("customer", customer);
            redirect = "homePage.jsp";
        } else if (!manager.doesCustomerExist(email)) {
            session.setAttribute("emailErr", "Email does not exist.");
        } else if (customer != null && !customer.isIsActive()) {
            session.setAttribute("emailErr", "The current accout is inactive.");
        } else {
            session.setAttribute("passErr", "Password is incorrect.");
        }
    }
    
    //
    //ACCOUNT SERVLETS
    //
    private void AccountPageServlet(HttpServletRequest request)
        throws ServletException, IOException, SQLException
    {
        switch(request.getParameter("button")) {
            case "edit" -> redirect = "edit.jsp";
            case "paymentMethods" -> redirect = "paymentMethods.jsp";
            case "shipmentMethods" -> redirect = "shipmentMethods.jsp";
            case "orderHistory" -> redirect = "orderHistory.jsp";
        }
    }
    
    private void EditServlet(HttpServletRequest request)
    throws ServletException, IOException, SQLException
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

        if (!invalidRegisterCheck(firstName, lastName, dob, phone, postcode)) {
            manager.updateCustomerDetails(email, firstName, lastName, dob, phone, street, city, state, postcode, customer.isIsActive());
            session.setAttribute("customer", manager.findCustomer(email, password));
            redirect = "edit.jsp";
        }
    }
    
    //
    //PAYMENT SERVLETS
    //
    private void PaymentMethodsServlet(HttpServletRequest request)
        throws ServletException, IOException, SQLException
    {
        String ID = request.getParameter("paymentID");        
        int paymentID = ID == null ? -1 : Integer.parseInt(ID);
        
        switch (request.getParameter("button")){
            case "edit" -> {
                session.setAttribute("payment", manager.findPayment(paymentID));
                redirect = "paymentEdit.jsp";
            }

            case "delete" -> {
                manager.deletePayment(paymentID);
            }

            case "add" -> redirect = "paymentAdd.jsp";
        }
    }
    
    private void PaymentAddEdit(HttpServletRequest request, boolean isEdit)
        throws ServletException, IOException, SQLException
    {
        Customer customer = (Customer)session.getAttribute("customer");
        Payment payment = (Payment)session.getAttribute("payment");
        String paymentMethod = request.getParameter("paymentMethod");
        String cardNumber = request.getParameter("cardNumber");
        String fullName = request.getParameter("fullName");
        String expiryDate = request.getParameter("expiryDate");
        String cvv = request.getParameter("cvv");
        Payment temp = new Payment(paymentMethod, cardNumber, fullName, expiryDate, cvv);
        if (!invalidPaymentCheck(customer, temp, !(isEdit && payment.getPaymentMethod().equals(paymentMethod))))
        {
            if (isEdit) {
                manager.updatePaymentDetails(payment.getPaymentID(), temp);
            } else {
                manager.addPayment(temp, customer);
            }
            redirect = "paymentMethods.jsp";
        }
    }
    
    private void PaymentAddServlet(HttpServletRequest request)
        throws ServletException, IOException, SQLException
    {
        PaymentAddEdit(request, false);
    }
    
    private void PaymentEditServlet(HttpServletRequest request)
        throws ServletException, IOException, SQLException
    {
        PaymentAddEdit(request, true);
    }
    
    //
    //SHIPMENT SERVLETS
    //
    private void ShipmentMethodsServlet(HttpServletRequest request)
        throws ServletException, IOException, SQLException
    {
        String ID = request.getParameter("shipmentID");        
        int shipmentID = ID == null ? -1 : Integer.parseInt(ID);
        
        switch (request.getParameter("button")){
            case "edit" -> {
                session.setAttribute("shipment", manager.findShipment(shipmentID));
                redirect = "shipmentEdit.jsp";
            }

            case "delete" -> {
                manager.deleteShipment(shipmentID);
            }

            case "add" -> redirect = "shipmentAdd.jsp";
        }
    }
    
    private void ShipmentAddEditServlet(HttpServletRequest request, boolean isEdit)
        throws ServletException, IOException, SQLException
    {
        Customer customer = (Customer)session.getAttribute("customer");
        Shipment shipment = (Shipment)session.getAttribute("shipment");
        String shipmentMethod = request.getParameter("shipmentMethod");
        Shipment temp = new Shipment(shipmentMethod);
        if (!invalidShipmentCheck(customer, temp, !(isEdit && shipment.getShipmentMethod().equals(shipmentMethod))))
        {
            if (isEdit) {
                manager.updateShipmentDetails(shipment.getShipmentID(), temp);
            } else {
                manager.addShipment(temp, customer);
            }
            redirect = "shipmentMethods.jsp";
        }
    }
    
    private void ShipmentAddServlet(HttpServletRequest request)
            throws ServletException, IOException, SQLException
    {
        ShipmentAddEditServlet(request, false);
    }
    
    private void ShipmentEditServlet(HttpServletRequest request)
            throws ServletException, IOException, SQLException
    {
        ShipmentAddEditServlet(request, true);
    }
    
    //
    //USER MANAGEMENT SERVLETS
    //
    private void UserManagementServlet(HttpServletRequest request)
            throws ServletException, IOException, SQLException
    {
        String ID = request.getParameter("customerID");
        int customerID = ID == null ? -1 : Integer.parseInt(ID);
        String sort = (String)session.getAttribute("sort");
        
        switch (request.getParameter("button")){
            case "edit" -> {
                session.setAttribute("customer2", manager.findCustomer(customerID));
                redirect = "userManagementEdit.jsp";
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
    }
    
    private void UserManagmentEditServlet(HttpServletRequest request)
            throws ServletException, IOException, SQLException
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

        if (!invalidRegisterCheck(firstName, lastName, dob, phone, postcode)) {
            manager.updateCustomerDetails(email, firstName, lastName, dob, phone, street, city, state, postcode, isActive);
            session.setAttribute("customer2", null);
            redirect = "userManagement.jsp";
        }
    }
    
    //
    //ITEM MANAGEMENT SERVLETS
    //
    private void ItemManagementServlet(HttpServletRequest request)
        throws ServletException, IOException, SQLException
    {
        String ID = request.getParameter("itemID");        
        int itemID = ID == null ? -1 : Integer.parseInt(ID);
        String sort = (String)session.getAttribute("sort");
        
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

            case "add" -> redirect = "itemAdd.jsp";
        }
    }
    
    private void ItemAddServlet(HttpServletRequest request)
            throws ServletException, IOException, SQLException
    {
        String item_name = request.getParameter("itemName");
        float price = Float.parseFloat(request.getParameter("itemPrice"));
        String type = request.getParameter("itemType");
        int stock = Integer.parseInt(request.getParameter("itemStock"));
        
        Item item = new Item(item_name, price, type, stock);
        if (!invalidItemDataCheck(item)) {
            manager.addItem(item);
            redirect = "itemManagement.jsp";
        }
    }
        
    private void ItemEditServlet(HttpServletRequest request)
            throws ServletException, IOException, SQLException
    {
        Item item = (Item)session.getAttribute("item");
        String item_name = request.getParameter("itemName");
        float price = Float.parseFloat(request.getParameter("itemPrice"));
        String type = request.getParameter("itemType");
        int stock = Integer.parseInt(request.getParameter("itemStock"));

        if (!invalidItemDataCheck(item_name, type, price, stock)) {
            manager.updateItemDetails(item.getItemID(), item_name, type, price, stock);
            session.setAttribute("item", null);
            redirect = "itemManagement.jsp";
        }
    }
    
    //
    //SHOPPING/ORDER SERVLETS
    //
    private void ShoppingServlet(HttpServletRequest request)
        throws ServletException, IOException, SQLException
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
    
    private void OrderItemServlet(HttpServletRequest request)
        throws ServletException, IOException, SQLException
    {
        switch(request.getParameter("button")) {
            case "purchase" -> {
                Item item = (Item)session.getAttribute("item");
                int quantity = Integer.parseInt(request.getParameter("quantity"));
                String street = request.getParameter("street");
                String city = request.getParameter("city");
                String state = request.getParameter("state");
                String postcode = request.getParameter("postcode");
                Payment payment = manager.findPayment(Integer.parseInt(request.getParameter("paymentMethodID")));
                Shipment shipment = manager.findShipment(Integer.parseInt(request.getParameter("shipmentMethodID")));;
                session.setAttribute(
                    "order", new Order(
                        item.getItem(), payment.getPaymentMethod(), shipment.getShipmentMethod(), 
                        "Being delivered", quantity, quantity * item.getPrice(), LocalDate.now() + "",
                        street, city, state, postcode
                    )
                );
                redirect = "orderPurchase.jsp";
            }
            case "paymentMethod" -> redirect = "paymentMethods.jsp";
            case "shipmentMethod" -> redirect = "shipmentMethods.jsp";
        }
    }
    
    private void OrderPurchaseServlet(HttpServletRequest request)
        throws ServletException, IOException, SQLException
    {
        switch(request.getParameter("button")) {
            case "yes" -> {
                manager.addOrder((Order)session.getAttribute("order"), (Customer)session.getAttribute("customer"));
                session.setAttribute("item", null);
                redirect = "orderHistory.jsp";
            }
            case "no" -> redirect = "shoppingPage.jsp";
        }
    }
    
    //
    //MISC METHODS
    //
    private boolean invalidPaymentCheck(Customer customer, Payment payment, boolean checkExist)
        throws SQLException
    {
        boolean hasFailed = false;
        String message = "Payment method already exists.";
        if (checkExist) {
            hasFailed = DataCheck(hasFailed, !manager.doesPaymentExist(customer.getCustomerID(), payment.getPaymentMethod()), "existErr", message);
        }
        
        message = "First letter of each word must be either a capital letter or a number.";
        hasFailed = DataCheck(hasFailed, validator.validatePattern("spacedCamel", payment.getPaymentMethod()), "paymentMethodErr", message);
        
        message = "Card number should only contain a 16 digit number.";
        hasFailed = DataCheck(hasFailed, validator.validatePattern("cardNumber", payment.getCardNumber()), "cardNumberErr", message);

        message = "Not a proper full name. An example: John Titor";
        hasFailed = DataCheck(hasFailed, validator.validatePattern("fullName", payment.getFullName()), "fullNameErr", message);
        
        message = "The date is already expired.";
        hasFailed = DataCheck(hasFailed, validator.validatePattern("expiryDate", payment.getExpiryDate()), "expiryDateErr", message);
        
        message = "Cvv is 3 digits long.";
        hasFailed = DataCheck(hasFailed, validator.validatePattern("cvv", payment.getCvv()), "cvvErr", message);
        
        return hasFailed;
    }

    private boolean invalidRegisterCheck(String email, String password, String firstName, String lastName, String dob, String phone, String postcode)
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

        hasFailed = hasFailed || invalidRegisterCheck(firstName, lastName, dob, phone, postcode);

        message = "Customer already exists.";
        hasFailed = DataCheck(hasFailed, !manager.doesCustomerExist(email), "existErr", message);

        return hasFailed;
    }

    private boolean invalidRegisterCheck(String firstName, String lastName, String dob, String phone, String postcode)
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
    
    private boolean invalidShipmentCheck(Customer customer, Shipment shipment, boolean checkExist)
        throws SQLException
    {
        boolean hasFailed = false;
        
        String message = "Shipment method already exists.";
        if (checkExist) {
            hasFailed = DataCheck(hasFailed, !manager.doesShipmentExist(customer.getCustomerID(), shipment.getShipmentMethod()), "existErr", message);
        }
        
        message = "First letter of each word must be either a capital letter or a number.";
        hasFailed = DataCheck(hasFailed, validator.validatePattern("spacedCamel", shipment.getShipmentMethod()), "shipmentMethodErr", message);
        
        return hasFailed;
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
}

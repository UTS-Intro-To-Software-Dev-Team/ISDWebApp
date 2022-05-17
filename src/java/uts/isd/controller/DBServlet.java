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
    //ACCOUNT SERVLETS
    //
    private void RegisterServlet(HttpServletRequest request)
        throws ServletException, IOException, SQLException
    {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        RegisterEdit(request, email, password, true);
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
    
    private void RegisterEdit(HttpServletRequest request, String email, String password, boolean isAdd)
        throws ServletException, IOException, SQLException
    {
        Customer customer = (Customer)session.getAttribute("customer");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String dob = request.getParameter("dob");
        String phone = request.getParameter("phone");
        String street = request.getParameter("street");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        String postcode = request.getParameter("postcode");
        Customer temp = new Customer(email, password, firstName, lastName, dob, phone, street, city, state, postcode);
        
        if (RegisterCheck(temp, isAdd)) {
            if (isAdd) {
                session.setAttribute("customer", manager.addCustomer(temp));
                redirect = "homePage.jsp";
            } else {
                manager.updateCustomerDetails(customer.getCustomerID(), temp);
                session.setAttribute("customer", manager.findCustomer(customer.getCustomerID()));
                redirect = "accountPage.jsp";
            }
        }
    }
    
    private void EditServlet(HttpServletRequest request)
        throws ServletException, IOException, SQLException
    {
        Customer customer = (Customer)session.getAttribute("customer");
        String email = customer.getEmail();
        String password = customer.getPassword();
        RegisterEdit(request, email, password, false);
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
    
    private void PaymentAddEdit(HttpServletRequest request, boolean isAdd)
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
        if (PaymentCheck(customer, temp, isAdd || !payment.getPaymentMethod().equals(paymentMethod)))
        {
            if (isAdd) {
                manager.addPayment(temp, customer);
            } else {
                manager.updatePaymentDetails(payment.getPaymentID(), temp);
            }
            redirect = "paymentMethods.jsp";
        }
    }
    
    private void PaymentAddServlet(HttpServletRequest request)
        throws ServletException, IOException, SQLException
    {
        PaymentAddEdit(request, true);
    }
    
    private void PaymentEditServlet(HttpServletRequest request)
        throws ServletException, IOException, SQLException
    {
        PaymentAddEdit(request, false);
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
    
    private void ShipmentAddEdit(HttpServletRequest request, boolean isAdd)
        throws ServletException, IOException, SQLException
    {
        Customer customer = (Customer)session.getAttribute("customer");
        Shipment shipment = (Shipment)session.getAttribute("shipment");
        String shipmentMethod = request.getParameter("shipmentMethod");
        Shipment temp = new Shipment(shipmentMethod);
        if (ShipmentCheck(customer, temp, isAdd || !shipment.getShipmentMethod().equals(shipmentMethod)))
        {
            if (isAdd) {
                manager.addShipment(temp, customer);
            } else {
                manager.updateShipmentDetails(shipment.getShipmentID(), temp);
            }
            redirect = "shipmentMethods.jsp";
        }
    }
    
    private void ShipmentAddServlet(HttpServletRequest request)
            throws ServletException, IOException, SQLException
    {
        ShipmentAddEdit(request, true);
    }
    
    private void ShipmentEditServlet(HttpServletRequest request)
            throws ServletException, IOException, SQLException
    {
        ShipmentAddEdit(request, false);
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
        Customer temp = new Customer(email, "", firstName, lastName, dob, phone, street, city, state, postcode);

        if (RegisterCheck(temp, false)) {
            manager.updateCustomerDetails(customer.getCustomerID(), temp, isActive);
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
    
    private void ItemAddEdit(HttpServletRequest request, boolean isAdd)
            throws ServletException, IOException, SQLException
    {
        if (request.getParameter("button").equals("cancel")) {
            redirect = "itemManagement.jsp";
            return;
        }
        
        Item item = (Item)session.getAttribute("item");
        
        String item_name = request.getParameter("itemName");
        float price = Float.parseFloat(request.getParameter("itemPrice"));
        String type = request.getParameter("itemType");
        int stock = Integer.parseInt(request.getParameter("itemStock"));
        Item temp = new Item(item_name, price, type, stock);
        
        if (ItemDataCheck(temp, isAdd || !item.getItem().equals(item_name))) {
            if (isAdd) {
                manager.addItem(temp);
            } else {
                manager.updateItemDetails(item.getItemID(), item_name, type, price, stock);
            }
            session.setAttribute("item", null);
            redirect = "itemManagement.jsp";
        }
    }
    
    private void ItemAddServlet(HttpServletRequest request)
            throws ServletException, IOException, SQLException
    {
        ItemAddEdit(request, true);
    }
        
    private void ItemEditServlet(HttpServletRequest request)
            throws ServletException, IOException, SQLException
    {
        ItemAddEdit(request, false);
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
                Shipment shipment = manager.findShipment(Integer.parseInt(request.getParameter("shipmentMethodID")));
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
            case "cancel" -> redirect = "shoppingPage.jsp";
        }
    }
    
    private void OrderPurchaseServlet(HttpServletRequest request)
        throws ServletException, IOException, SQLException
    {
        switch(request.getParameter("button")) {
            case "yes" -> {
                Order order = (Order)session.getAttribute("order");
                manager.addOrder(order, (Customer)session.getAttribute("customer"));
                Item item = (Item)session.getAttribute("item");
                manager.updateItemDetails(item.getItemID(), item.getItem(), item.getType(), item.getPrice(), item.getStock() - order.getQuantity());
                session.setAttribute("item", null);
                session.setAttribute("order", null);
                redirect = "orderHistory.jsp";
            }
            case "no" -> redirect = "shoppingPage.jsp";
        }
    }
    
    //
    //MISC METHODS
    //
    private boolean DataCheck(boolean hasPassed, boolean valid, String attribute, String message) {
        session.setAttribute(attribute, valid ? null : message);
        return hasPassed && valid;
    }
        
    private boolean PaymentCheck(Customer customer, Payment payment, boolean checkExist)
        throws SQLException
    {
        boolean hasPassed = true;
        String message = "Payment method already exists.";
        if (checkExist) {
            hasPassed = DataCheck(hasPassed, !manager.doesPaymentExist(customer.getCustomerID(), payment.getPaymentMethod()), "existErr", message);
        }
        
        message = "First letter of each word must be either a capital letter or a number.";
        hasPassed = DataCheck(hasPassed, validator.validatePattern("spacedCamel", payment.getPaymentMethod()), "paymentMethodErr", message);
        
        message = "Card number should only contain a 16 digit number.";
        hasPassed = DataCheck(hasPassed, validator.validatePattern("cardNumber", payment.getCardNumber()), "cardNumberErr", message);

        message = "Not a proper full name. An example: John Titor";
        hasPassed = DataCheck(hasPassed, validator.validatePattern("fullName", payment.getFullName()), "fullNameErr", message);
        
        message = "The date is already expired.";
        hasPassed = DataCheck(hasPassed, validator.validatePattern("expiryDate", payment.getExpiryDate()), "expiryDateErr", message);
        
        message = "Cvv is 3 digits long.";
        hasPassed = DataCheck(hasPassed, validator.validatePattern("cvv", payment.getCvv()), "cvvErr", message);
        
        return hasPassed;
    }

    private boolean RegisterCheck(Customer customer, boolean newCheck)
            throws SQLException
    {
        boolean hasPassed = true;
        String message = "Email format is incorrect. An example: JohnSmith@Mail.com";
        if (newCheck) {
            hasPassed = DataCheck(hasPassed, validator.validatePattern("email", customer.getEmail()), "emailErr", message);

            message = "Password must contain:";
            message += "<br>1. At least one digit [0-9].";
            message += "<br>2. At least one lowercase Latin character [a-z].";
            message += "<br>3. At least one uppercase Latin character [A-Z].";
            message += "<br>4. at least one special character like ! @ # & ( ).";
            message += "<br>5. A length of at least 8 characters.";
            hasPassed = DataCheck(hasPassed, validator.validatePattern("password", customer.getPassword()), "passErr", message);

            message = "Customer already exists.";
            hasPassed = DataCheck(hasPassed, !manager.doesCustomerExist(customer.getEmail()), "existErr", message);
        }
        
        message = "This should only contain letters and start with a capitalised letter.";
        hasPassed = DataCheck(hasPassed, validator.validatePattern("name", customer.getFirstName()), "firstNameErr", message);
        hasPassed = DataCheck(hasPassed, validator.validatePattern("name", customer.getLastName()), "lastNameErr", message);

        message = "Minimum age is 13.";
        hasPassed = DataCheck(hasPassed, validator.validatePattern("age", customer.getDob()), "dateErr", message);

        message = "Not a valid phone number.";
        hasPassed = DataCheck(hasPassed, validator.validatePattern("phone", customer.getPhone()), "phoneErr", message);

        message = "Postcode must be a 4 digit number.";
        hasPassed = DataCheck(hasPassed, validator.validatePattern("postcode", customer.getPostcode()), "postcodeErr", message);

        return hasPassed;
    }
    
    private boolean ShipmentCheck(Customer customer, Shipment shipment, boolean checkExist)
        throws SQLException
    {
        boolean hasPassed = true;
        
        String message = "Shipment method already exists.";
        if (checkExist) {
            hasPassed = DataCheck(hasPassed, !manager.doesShipmentExist(customer.getCustomerID(), shipment.getShipmentMethod()), "existErr", message);
        }
        
        message = "First letter of each word must be either a capital letter or a number.";
        hasPassed = DataCheck(hasPassed, validator.validatePattern("spacedCamel", shipment.getShipmentMethod()), "shipmentMethodErr", message);
        
        return hasPassed;
    }
    
    private boolean ItemDataCheck(Item item, boolean existCheck)
        throws SQLException
    {
        boolean hasPassed = true;
        String message = "This item already exists.";
        if (existCheck) {
            hasPassed = DataCheck(hasPassed, manager.findItem(item.getItem()) == null, "existsErr", message);
        }
        
        message = "First letter of each word must be either a capital letter or a number.";
        hasPassed = DataCheck(hasPassed, validator.validatePattern("item", item.getItem()), "nameErr", message);
        
        message = "This should only contain letters and start with a capitalised letter.";
        hasPassed = DataCheck(hasPassed, validator.validatePattern("name", item.getType()), "typeErr", message);
        
        message = "Invalid price.";
        hasPassed = DataCheck(hasPassed, item.getPrice() >= 0, "priceErr", message);
        
        message = "Invalid stock number.";
        hasPassed = DataCheck(hasPassed, item.getStock() >= 0, "stockErr", message);
        return hasPassed;
    }
}

<%@page import="uts.isd.model.*" %>
<%@page import="uts.isd.model.dao.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="PageComponents/CommonMeta.jsp"/>
        <title>Edit Payment Method</title>
    </head>
    
    <%
        Customer customer = (Customer)session.getAttribute("customer");
        Payment payment = (Payment)session.getAttribute("payment");
        DBManager manager = (DBManager)session.getAttribute("manager");
        if (customer == null || manager == null || payment == null) {
            response.sendRedirect("login.jsp");
            return;
        }
    %>
    <jsp:include page="PageComponents/JSPHeader.jsp"/>

    <body>
        <%
            String existErr = (String) session.getAttribute("existErr");
            String paymentMethodErr = (String) session.getAttribute("paymentMethodErr");
            String cardNumberErr = (String) session.getAttribute("cardNumberErr");
            String fullNameErr = (String) session.getAttribute("fullNameErr");
            String expiryDateErr = (String) session.getAttribute("expiryDateErr");
            String cvvErr = (String) session.getAttribute("cvvErr");
            
            String paymentMethod = request.getParameter("paymentMethod");
            paymentMethod = paymentMethod != null ? paymentMethod : payment.getPaymentMethod();
            
            String cardNumber = request.getParameter("cardNumber");;
            cardNumber = cardNumber != null ? cardNumber : payment.getCardNumber();
            
            String fullName = request.getParameter("fullName");;
            fullName = fullName != null ? fullName : payment.getFullName();
            
            String expiryDate = request.getParameter("expiryDate");
            expiryDate = expiryDate != null ? expiryDate : payment.getExpiryDate();
            
            String cvv = request.getParameter("cvv");
            cvv = cvv != null ? cvv : payment.getCvv();
        %>
        
        <h1 class="align-center spaced-letters blue">EDIT PAYMENT METHOD</h1>
        <form action="DBServlet" method="POST">
            <table class="align-center form-table">
                <% if (existErr != null) { %>
                    <tr><td><b><%= existErr %></b></td></tr>
                <% } %>
                
                <tr><td><label for="paymentMethod"><b>Payment Method:</b></label></td></tr>
                <tr><td><input type="text" id="paymentMethod" name="paymentMethod" placeholder="Enter method name" value="<%= paymentMethod %>" required pattern="^[A-Z0-9][A-Za-z0-9]*( [A-Z0-9][A-Za-z0-9]+)*$" title="First letter of each word must be either a capital letter or a number."></td></tr>
                <% if (paymentMethodErr != null) { %>
                    <tr><td><b><%= paymentMethodErr %></b></td></tr>
                <% } %>
                
                <tr><td><label for="cardNumber"><b>Card Number:</b></label></td></tr>
                <tr><td><input type="text" id="cardNumber" name="cardNumber" placeholder="Enter card number" value="<%= cardNumber %>" required minLength="16" maxLength="16" pattern="[0-9]*{16}" title="Should contain a 16-digit number."></td></tr>
                <% if (cardNumberErr != null) { %>
                    <tr><td><b><%= cardNumberErr %></b></td></tr>
                <% } %>
                
                <tr><td><label for="fullName"><b>Full Name:</b></label></td></tr>
                <tr><td><input type="text" id="fullName" name="fullName" placeholder="Enter card owner's full name" value="<%= fullName %>" required pattern="^[A-Z][a-z]*( [A-Z][a-z]+)+$" title="The full name of the card owner for example: John Titor"></td></tr>
                <% if (fullNameErr != null) { %>
                    <tr><td><b><%= fullNameErr %></b></td></tr>
                <% } %>
                
                <tr><td><label for="expiryDate"><b>Expiry Date:</b></label></td></tr>
                <tr><td><input type="date" id="expiryDate" name="expiryDate" placeholder="Enter card expiry date" value="<%= expiryDate %>" required></td></tr>
                <% if (expiryDateErr != null) { %>
                    <tr><td><b><%= expiryDateErr %></b></td></tr>
                <% } %>
                
                <tr><td><label for="cvv"><b>Cvv: </b></label><input type="text" id="cvv" name="cvv" placeholder="###" value="<%= cvv %>" required minLength="3" maxLength="3" title='Should be three numbers long.' style="width: 3%; text-align: center;"></td></tr>
                <% if (cvvErr != null) { %>
                    <tr><td><b><%= cvvErr %></b></td></tr>
                <% } %>
                
                <tr><td><button type="submit"><b>Update Method</b></button></td></tr>
            </table>
        </form>
    </body>
    <jsp:include page="PageComponents/JSPFooter.jsp"/>
</html>

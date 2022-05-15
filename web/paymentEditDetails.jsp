<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uts.isd.model.Payment"%>

<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="PageComponents/CommonMeta.jsp"/>
        <title>Edit Payment Details</title>
    </head>
    
    <jsp:include page="PageComponents/JSPHeader.jsp"/>
    
    <body>
        
        <%
            String fullName = request.getParameter("fullName");
            String cardNumber = request.getParameter("cardNumber");
            String cvv = request.getParameter("cvv");
            String expiryDate = request.getParameter("expiryDate");
            String method = request.getParameter("method");
     
            String fullNameErr = (String) session.getAttribute("firstNameErr");
            String cardNumberErr = (String) session.getAttribute("cardNumberErr");
            String cvvErr = (String) session.getAttribute("cvvErr");
            String dateErr = (String) session.getAttribute("dateErr");
        %>
        
        <h1>Edit Payment Details</h1>

        <form action="DBServlet" method="POST">
            <input type="hidden" name="paymentEditDetails" value="true">

            <table class="align-center form-table">
                
                <tr><td><label for="fullName"><b>Full Name:</b></label></td></tr>
                <tr><td><input type="text" name="fullName" placeholder="Enter Full Name" value="${payment.fullName}" required pattern="[A-Z][a-z]* [A-Z][a-z]*" title="This can only contain letters and the first letters must be capitalised."></td></tr>
                <% if (fullNameErr != null) { %>
                    <tr><td><b><%= fullNameErr %></b></td></tr>
                <% } %>
                
                <tr><td><label for="cardNumber"><b>Card Number:</b></label></td></tr>
                <tr><td><input type="text" name="cardNumber" placeholder="0000000000000000" value="${payment.cardNumber}" required minlength="16" maxlength="16"></td></tr>
                <% if (cardNumberErr != null) { %>
                    <tr><td><b><%= cardNumberErr %></b></td></tr>
                <% } %>
                
                <tr><td><label for="expiryDate"><b>Expiry Date:</b></label></td></tr>
                <tr><td><input type="month" name="expiryDate" min="2022-05" value="${payment.expiryDate}" required pattern="\d{2}-\d{4}"></td></tr>
                <% if (dateErr != null) { %>
                    <tr><td><b><%= dateErr %></b></td></tr>
                <% } %>
                
                <tr><td><label for="cvv"><b>CVV:</b></label></td></tr>
                <tr><td><input type="text" name="cvv" placeholder="000" value="${payment.cvv}" required minlength="3" maxlength="3"></td></tr>
                <% if (cvvErr != null) { %>
                    <tr><td><b><%= cvvErr %></b></td></tr>
                <% } %>
                
                <tr><td><label for="Payment Method"><b>Payment Method:</b></label></td></tr>
                <tr><td>
                    <select name="method" required selected value="${payment.method}">
                        <option value="Credit Card">Credit Card</option>
                        <option value="Debit Card">Debit Card</option>
                    </select>
                </td></tr>

                <tr><td><button type="submit" value="paymentEditDetails"><b>Update</b></button></td></tr>
                <tr><td><a class= "button" style="center" href="homePage.jsp">Cancel</a></td></tr>
            </table>
        </form>
    </body>
    <jsp:include page="PageComponents/JSPFooter.jsp"/>
</html>

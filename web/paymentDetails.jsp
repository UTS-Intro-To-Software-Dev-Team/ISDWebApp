<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="PageComponents/CommonMeta.jsp"/>
        <title>Payment Details</title>
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
        
        <h1>Payment Details</h1>

        <form action="homePage.jsp" method="POST">
            <input type="hidden" name="paymentDetails" value="true">

            <table class="align-center form-table">
                
                <tr><td><label for="fullName"><b>Full Name:</b></label></td></tr>
                <tr><td><input type="text" name="fullName" placeholder="Enter Full Name" value="<%= fullName != null ? fullName : "" %>" required pattern="[A-Z][a-z]* [A-Z][a-z]*" title="First letter of first and last names must be a letter and capitalised."></td></tr>
                <% if (fullNameErr != null) { %>
                    <tr><td><b><%= fullNameErr %></b></td></tr>
                <% } %>
                
                <tr><td><label for="cardNumber"><b>Card Number:</b></label></td></tr>
                <tr><td><input type="text" name="cardNumber" placeholder="0000000000000000" value="<%= cardNumber != null ? cardNumber : "" %>" required minlength="16" maxlength="16"></td></tr>
                <% if (cardNumberErr != null) { %>
                    <tr><td><b><%= cardNumberErr %></b></td></tr>
                <% } %>
                
                <tr><td><label for="expiryDate"><b>Expiry Date:</b></label></td></tr>
                <tr><td><input type="month" name="expiryDate" min="2022-05" value="<%= expiryDate != null ? expiryDate : "" %>" required pattern="\d{2}-\d{4}"></td></tr>
                <% if (dateErr != null) { %>
                    <tr><td><b><%= dateErr %></b></td></tr>
                <% } %>
                
                <tr><td><label for="cvv"><b>CVV:</b></label></td></tr>
                <tr><td><input type="text" name="cvv" placeholder="000" value="<%= cvv != null ? cvv : "" %>" required minlength="3" maxlength="3"></td></tr>
                <% if (cvvErr != null) { %>
                    <tr><td><b><%= cvvErr %></b></td></tr>
                <% } %>
                
                <tr><td><label for="Payment Method"><b>Payment Method:</b></label></td></tr>
                <tr><td>
                    <select name="method" required selected value="<%= method != null ? method : "" %>">
                        <option value="Credit Card">Credit Card</option>
                        <option value="Debit Card">Debit Card</option>
                    </select>
                </td></tr>

                <tr><td><button type="submit" value="PaymentDetails"><b>Submit</b></button></td></tr>
                <tr><td><a class= "button" style="center" href="homePage.jsp">Cancel</a></td></tr>
            </table>
        </form>
    </body>
    <jsp:include page="PageComponents/JSPFooter.jsp"/>
</html>

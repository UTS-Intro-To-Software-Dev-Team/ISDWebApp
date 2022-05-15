<%@page import="uts.isd.model.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="PageComponents/CommonMeta.jsp"/>
        <title>Payment Selection</title>
    </head>
    
    <jsp:include page="PageComponents/JSPHeader.jsp"/>
    
    <body>
        <h1>Payment Selection</h1>
        <%
            DBManager manager = (DBManager)session.getAttribute("manager");
        %>
        <form method="POST" action="DBServlet">
            <table class="align-center form-table">
                <tr>
                    <th>Full Name</th>
                    <th>Card Number</th>
                    <th>Expiry Date</th>
                    <th>CVV</th>
                    <th>Payment Method</th>

                    <th>Select</th>
                </tr>

                <% for (Customer customer : manager.fetchCustomers()) {%>
                <tr>
                    <td><%= payment.getFullName() %></td>  
                    <td><%= payment.getCardNumber() %></td>
                    <td><%= payment.getExpiryDate() %></td>
                    <td><%= payment.getCvv() %></td>
                    <td><%= payment.getMethod() %></td>
                    
                    <td> <input type="radio" name="cardNumber" value="<%=payment.getCardNumber()%>"> </td>
                </tr>
                <% } %>
            </table>
            <button name="button" value="edit" type="submit">Edit</button>
            <button name="button" value="delete" type="submit">Delete</button> 
        </form>
    </body>
    <jsp:include page="PageComponents/JSPFooter.jsp"/>
</html>

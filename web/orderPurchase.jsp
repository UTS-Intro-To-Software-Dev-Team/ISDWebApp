<%@page import="uts.isd.model.*" %>
<%@page import="uts.isd.model.dao.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="PageComponents/CommonMeta.jsp"/>
        <title>Purchasing</title>
    </head>
    
    <%
        Customer customer = (Customer)session.getAttribute("customer");
        DBManager manager = (DBManager)session.getAttribute("manager");
        if (customer == null || manager == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        
        Order order = (Order)session.getAttribute("order");
        if (order == null) {
            response.sendRedirect("shoppingPage.jsp");
            return;
        }
    %>
    <jsp:include page="PageComponents/JSPHeader.jsp"/>

    <body>
        <center>
            <h1 class="spaced-letters blue">FINALISING ORDER</h1>
            <table>
                <tr><td>You are about to purchase <%= order.getQuantity() %> <%= order.getItemName() %></td></tr>
                <tr><td>The total price is: $<%= order.getTotalPrice() %></td></tr>
                <tr><td>Your payment method is: <%= order.getPaymentMethod() %></td></tr>
                <tr><td>Your shipment method is: <%= order.getShipmentMethod() %></td></tr>
                <tr><td>The delivery address is: <%= order.getAddress() %></td></tr>
            </table>
            <h1>DO YOU CONFIRM?</h1>
            <form method="POST" action="DBServlet">
                <button type="submit" name="button" value="yes">Yes</button>
                &emsp;
                <button type="submit" name="button" value="no">no</button>
            </form>
        </center>
    </body>
    

    <jsp:include page="PageComponents/JSPFooter.jsp"/>
</html>

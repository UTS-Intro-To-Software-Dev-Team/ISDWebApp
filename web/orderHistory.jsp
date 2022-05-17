<%@page import="uts.isd.model.*" %>
<%@page import="uts.isd.model.dao.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="PageComponents/CommonMeta.jsp"/>
        <title>Order History</title>
    </head>
    
    <%
        Customer customer = (Customer)session.getAttribute("customer");
        DBManager manager = (DBManager)session.getAttribute("manager");
        if (customer == null || manager == null) {
            response.sendRedirect("login.jsp");
            return;
        }
    %>
    <jsp:include page="PageComponents/JSPHeader.jsp"/>

    <body>
        <h1>Order History</h1>
        <center>
            <table class="align-center">
                <tr>
                    <th>Item Name</th>
                    <th>Payment Method</th>
                    <th>Shipment Method</th>
                    <th>Status</th>
                    <th>Quantity</th>
                    <th>Total Price</th>
                    <th>Purchase Date</th>
                    <th>Destination Address</th>
                </tr>

                <% for (Order order : manager.fetchOrders(customer.getCustomerID())) {%>
                    <tr>
                        <td><%= order.getItemName() %></td>
                        <td><%= order.getPaymentMethod() %></td>
                        <td><%= order.getShipmentMethod() %></td>
                        <td><%= order.getStatus() %></td>
                        <td><%= order.getQuantity() %></td>
                        <td>$<%= order.getTotalPrice() %></td>
                        <td><%= order.getDate() %></td>
                        <td><%= order.getAddress() %></td>
                    </tr>
                <% } %>
            </table>
        </center>
    </body>
    

    <jsp:include page="PageComponents/JSPFooter.jsp"/>
</html>

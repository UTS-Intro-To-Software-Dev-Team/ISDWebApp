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
        
        String sortA = request.getParameter("sortA");
        String sortB = request.getParameter("sortB");
        String sort = " AND CAST(orderID AS CHAR(99)) LIKE '%" + (sortA != null ? sortA : "_") + "%' AND Date LIKE '%" + (sortB != null ? sortB : "_") + "%'";
    %>
    <jsp:include page="PageComponents/JSPHeader.jsp"/>

    <body>
        <center>
            <h1 class="spaced-letters blue">ORDER HISTORY</h1>
            <form method="POST" action="DBServlet">
                <center>
                    <p>
                        <label for="sortA">ID: </label><input type="text" name="sortA" id="sortA" placeholder="Search by ID" value="<%= (sortA != null ? sortA : "") %>">
                        &emsp;
                        <label for="sortB">Date: </label><input type="text" name="sortB" id="sortB" placeholder="Search by date" value="<%= (sortB != null ? sortB : "") %>">
                        &emsp;
                        <button name="button" formnovalidate value="search" type="submit">Search</button>
                    </p>
                </center>
            </form>
            <table class="align-center">
                <tr>
                    <th>ID</th>
                    <th>Item Name</th>
                    <th>Payment Method</th>
                    <th>Shipment Method</th>
                    <th>Status</th>
                    <th>Quantity</th>
                    <th>Total Price</th>
                    <th>Purchase Date</th>
                    <th>Destination Address</th>
                </tr>

                <% for (Order order : manager.fetchOrders(customer.getCustomerID(), sort)) {%>
                    <tr>
                        <td><%= order.getOrderID() %></td>
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

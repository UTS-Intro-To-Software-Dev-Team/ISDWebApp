<%@page import="uts.isd.model.*" %>
<%@page import="uts.isd.model.dao.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="PageComponents/CommonMeta.jsp"/>
        <title>Shipment Methods</title>
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
        String sort = " AND CAST(shipmentID AS CHAR(99)) LIKE '%" + (sortA != null ? sortA : "_") + "%' AND shipmentMethod LIKE '%" + (sortB != null ? sortB : "_") + "%'";
    %>
    <jsp:include page="PageComponents/JSPHeader.jsp"/>

    <body>
        <h1>Shipment Methods</h1>
        <form method="POST" action="DBServlet">
            <center>
                <label for="sortA">ID: </label><input type="text" name="sortA" id="sortA" placeholder="Search by ID" value="<%= (sortA != null ? sortA : "") %>">
                &emsp;
                <label for="sortB">Shipment Method: </label><input type="text" name="sortB" id="sortB" placeholder="Search by shipment method" value="<%= (sortB != null ? sortB : "") %>">
                &emsp;
                <button name="button" formnovalidate value="search" type="submit">Search</button>
                &emsp;
                <button name="button" formnovalidate value="add" type="submit">Add new method</button>
                &emsp;
                <button name="button" value="edit" type="submit">Edit</button>
                &emsp;
                <button name="button" value="delete" type="submit">Delete</button>
            </center>
            <table class="align-center">
                <tr>
                    <th>ID</th>
                    <th>Shipment Methods</th>
                    <th>Select</th>
                </tr>

                <% for (Shipment shipment : manager.fetchShipmentMethods(customer.getCustomerID(), sort)) {%>
                    <tr>
                        <td><label for="<%= shipment.getShipmentMethod()%>"><%= shipment.getShipmentID() %></label></td>
                        <td><label for="<%= shipment.getShipmentMethod()%>"><%= shipment.getShipmentMethod() %></label></td>
                        <td> <input type="radio" id="<%= shipment.getShipmentMethod()%>" name="shipmentID" value="<%=shipment.getShipmentID()%>" required> </td>
                    </tr>
                <% } %>
            </table>
        </form>
    </body>
    <jsp:include page="PageComponents/JSPFooter.jsp"/>
</html>

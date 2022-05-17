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
    %>
    <jsp:include page="PageComponents/JSPHeader.jsp"/>

    <body>
        <h1>Shipment Methods</h1>
        <form method="POST" action="DBServlet">
            <center>
                <button name="button" formnovalidate value="add" type="submit">Add new method</button>
                <button name="button" value="edit" type="submit">Edit</button>
                <button name="button" value="delete" type="submit">Delete</button>
            </center>
            <table class="align-center">
                <tr>
                    <th>Shipment Methods</th>
                    <th>Select</th>
                </tr>

                <% for (Shipment shipment : manager.fetchShipmentMethods(customer.getCustomerID())) {%>
                    <tr>
                        <td><label for="<%= shipment.getShipmentMethod()%>"><%= shipment.getShipmentMethod() %></label></td>
                        <td> <input type="radio" id="<%= shipment.getShipmentMethod()%>" name="shipmentID" value="<%=shipment.getShipmentID()%>" required> </td>
                    </tr>
                <% } %>
            </table>
        </form>
    </body>
    <jsp:include page="PageComponents/JSPFooter.jsp"/>
</html>

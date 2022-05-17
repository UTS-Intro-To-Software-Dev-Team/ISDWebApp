<%@page import="uts.isd.model.*" %>
<%@page import="uts.isd.model.dao.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="PageComponents/CommonMeta.jsp"/>
        <title>Edit Shipment Method</title>
    </head>
    
    <%
        Customer customer = (Customer)session.getAttribute("customer");
        Shipment shipment = (Shipment)session.getAttribute("shipment");
        DBManager manager = (DBManager)session.getAttribute("manager");
        if (customer == null || manager == null || shipment == null) {
            response.sendRedirect("login.jsp");
            return;
        }
    %>
    <jsp:include page="PageComponents/JSPHeader.jsp"/>

    <body>
        <%
            String existErr = (String) session.getAttribute("existErr");
            String shipmentMethod = request.getParameter("shipmentMethod");
            shipmentMethod = shipmentMethod != null ? shipmentMethod : shipment.getShipmentMethod();
        %>
        
        <h1 class="align-center spaced-letters blue">EDIT SHIPMENT METHOD</h1>
        <form action="DBServlet" method="POST">
            <table class="align-center form-table">
                <% if (existErr != null) { %>
                    <tr><td><b><%= existErr %></b></td></tr>
                <% } %>
                
                <tr><td><label for="shipmentMethod"><b>Payment Method:</b></label></td></tr>
                <tr><td><input type="text" id="shipmentMethod" name="shipmentMethod" placeholder="Enter method name" value="<%= shipmentMethod %>" required pattern="^[A-Z0-9][A-Za-z0-9]*( [A-Z0-9][A-Za-z0-9]+)*$" title="First letter of each word must be either a capital letter or a number."></td></tr>
                
                <tr><td><button type="submit" name="button" value="shipmentAddEdit"><b>Update Method</b></button></td></tr>
                <tr><td><button type="submit" name="button" formnovalidate value="shipmentCancel">Cancel</button></td></tr>
            </table>
        </form>
    </body>
    <jsp:include page="PageComponents/JSPFooter.jsp"/>
</html>

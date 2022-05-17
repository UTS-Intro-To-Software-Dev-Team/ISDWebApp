<%@page import="uts.isd.model.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="PageComponents/CommonMeta.jsp"/>
        <title>Add Shipment Method</title>
    </head>
    
    <%
        Customer customer = (Customer)session.getAttribute("customer");
        if (customer == null) {
            response.sendRedirect("login.jsp");
            return;
        }
    %>
    <jsp:include page="PageComponents/JSPHeader.jsp"/>

    <body>
        <%
            String existErr = (String) session.getAttribute("existErr");
            String shipmentMethodErr = (String) session.getAttribute("shipmentMethodErr");
            
            String shipmentMethod = request.getParameter("shipmentMethod");
            shipmentMethod = shipmentMethod != null ? shipmentMethod : "";
        %>
        
        <h1 class="align-center spaced-letters blue">CREATE SHIPMENT METHOD</h1>
        <form action="DBServlet" method="POST">
            <table class="align-center form-table">
                <% if (existErr != null) { %>
                    <tr><td><b><%= existErr %></b></td></tr>
                <% } %>
                
                <tr><td><label for="shipmentMethod"><b>Shipment Method:</b></label></td></tr>
                <tr><td><input type="text" id="shipmentMethod" name="shipmentMethod" placeholder="Enter method name" value="<%= shipmentMethod %>" required pattern="^[A-Z0-9][A-Za-z0-9]*( [A-Z0-9][A-Za-z0-9]+)*$" title="First letter of each word must be either a capital letter or a number."></td></tr>
                <% if (shipmentMethodErr != null) { %>
                    <tr><td><b><%= shipmentMethodErr %></b></td></tr>
                <% } %>
                
                <tr><td><button type="submit" name="button" value="shipmentAddEdit"><b>Create Shipment Method</b></button></td></tr>
                <tr><td><button type="submit" name="button" formnovalidate value="shipmentCancel">Cancel</button></td></tr>
            </table>
        </form>
    </body>
    <jsp:include page="PageComponents/JSPFooter.jsp"/>
</html>

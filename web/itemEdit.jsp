<%@page import="uts.isd.model.*"%>
<%@page import="uts.isd.model.dao.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="PageComponents/CommonMeta.jsp"/>

        <title>Item Edit</title>
    </head>
    
    <%
        Customer customer = (Customer)session.getAttribute("customer");
        Item item = (Item)session.getAttribute("item");
        DBManager manager = (DBManager)session.getAttribute("manager");
        if (customer == null || manager == null || item == null || !customer.isIsStaff()) {
            response.sendRedirect(customer != null ? "homePage.jsp" : "login.jsp");
            return;
        }
    %>
    <jsp:include page="PageComponents/JSPHeader.jsp"/>
    
    <body>
        <%
            String nameErr = (String)session.getAttribute("nameErr");
            String priceErr = (String)session.getAttribute("priceErr");
            String typeErr = (String)session.getAttribute("typeErr");
            String stockErr = (String)session.getAttribute("stockErr");
        %>

        <h1 class="align-center spaced-letters blue">EDIT ITEM DETAILS - <%= item.getItem().toUpperCase()%></h1>
        <form class="align-center" action="DBServlet" method="POST">
            <table class="align-center form-table">
                <tr><td><label for="itemName"><b>Item Name:</b></label></td></tr>
                <tr><td><input type="text" name="itemName" id="itemName" placeholder="Enter item name" value="<%= item.getItem()%>" required pattern="^[A-Z0-9][A-Za-z0-9]*( [A-Z0-9][A-Za-z0-9]+)*$" title="First letter of each word must be either a capital letter or a number."></td></tr>
                <% if (nameErr != null) { %>
                    <tr><td><b><%= nameErr %></b></td></tr>
                <% } %>
                
                <tr><td><label for="itemPrice"><b>Item Price:</b></label></td></tr>
                <tr><td><input type="number" name="itemPrice" id="itemPrice" placeholder="Enter item price" value="<%= item.getPrice()%>" required min="0" step="0.01"></td></tr>
                <% if (priceErr != null) { %>
                    <tr><td><b><%= priceErr %></b></td></tr>
                <% } %>

                <tr><td><label for="itemType"><b>Item Type:</b></label></td></tr>
                <tr><td><input type="text" name="itemType" id="itemType" placeholder="Enter item type" value="<%= item.getType()%>" required pattern="^[A-Z][a-z]*$"%></td></tr>
                <% if (typeErr != null) { %>
                    <tr><td><b><%= typeErr %></b></td></tr>
                <% } %>

                <tr><td><label for="itemStock"><b>Item Stock:</b></label></td></tr>
                <tr><td><input type="number" name="itemStock" id="itemStock" placeholder="Enter item stock" value="<%= item.getStock()%>" required min="0"></td></tr>
                <% if (stockErr != null) { %>
                    <tr><td><b><%= stockErr %></b></td></tr>
                <% } %>
                
                <tr><td><button type="submit" name="button" value="itemAddEdit"><b>Update Catalogue</b></button></td></tr>
                <tr><td><button type="submit" name="button" formnovalidate value="itemCancel">Cancel</button></td></tr>
            </table>
        </form>
    </body>
    <jsp:include page="PageComponents/JSPFooter.jsp"/>
</html>

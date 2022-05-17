<%@page import="uts.isd.model.*"%>
<%@page import="uts.isd.model.dao.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="PageComponents/CommonMeta.jsp"/>

        <title>Item Edit Page</title>
    </head>
    
    <%
        Customer customer = (Customer)session.getAttribute("customer");
        Item item = (Item)session.getAttribute("item1");
        DBManager manager = (DBManager)session.getAttribute("manager");
        if (customer == null || manager == null || !manager.isCustomerStaff(customer.getEmail())) {
            response.sendRedirect("homePage.jsp");
            return;
        }
    %>
    <jsp:include page="PageComponents/JSPHeader.jsp"/>
    
    <body>
        <%
            item = (Item)session.getAttribute("item");
        %>

        <h1 class="align-center">Edit Item Details - <%= item.getItem()%></h1>
        <form class="align-center" action="DBServlet" method="POST">
            <table class="align-center form-table">
                <tr><td><label for="itemPrice"><b>Item Price:</b></label></td></tr>
                <tr><td><input type="text" name="itemPrice" placeholder="Enter item price" value="<%= item.getPrice()%>" required></td></tr>

                <tr><td><label for="itemType"><b>Item Type:</b></label></td></tr>
                <tr><td><input type="number" name="itemType" placeholder="Enter item type" value="<%= item.getType()%>" required pattern="^[A-Z][a-z]*$"%></td></tr>

                <tr><td><label for="itemStock"><b>Item stock:</b></label></td></tr>
                <tr><td><input type="number" name="itemStock" placeholder="Enter item stock" value="<%= item.getStock()%>"></td></tr>
                <tr><td><button type="submit" name="button" value="edit"><b>Update Catalogue</b></button></td></tr>
            </table>
        </form>
    </body>
    <jsp:include page="PageComponents/JSPFooter.jsp"/>
</html>

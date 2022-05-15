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
            item = (Item)session.getAttribute("item1");
        %>

        <h1 class="align-center">Edit Item Details - Admin</h1>
        <form class="align-center" action="DBServlet" method="POST">
            <table class="align-center form-table">
                <tr><td><label for="itemName"><b>Item Name:</b></label></td></tr>
                <tr><td><input type="text" name="itemName" placeholder="Enter item name" value="<%= item.getItem() %>" required pattern="^[A-Z][a-z]*$" title="This should only contain letters and start with a capitalised letter."></td></tr>

                <tr><td><label for="itemPrice"><b>Item Price:</b></label></td></tr>
                <tr><td><input type="text" name="itemPrice" placeholder="Enter item price" value="<%= item.getPrice()%>" required></td></tr>

                <tr><td><label for="itemType"><b>Item Type:</b></label></td></tr>
                <tr><td><input type="text" name="itemType" placeholder="Enter item type" value="<%= item.getType()%>" required pattern="^[A-Z][a-z]*$"%></td></tr>

                <tr><td><label for="itemStock"><b>Item stock:</b></label></td></tr>
                <tr><td><input type="text" name="itemStock" placeholder="Enter item stock" value="<%= item.getStock()%>"></td></tr>

                <tr><td><button type="submit" name="button" value="edit"><b>Update Catalogue</b></button></td></tr>
            </table>
        </form>
    </body>
    <jsp:include page="PageComponents/JSPFooter.jsp"/>
</html>

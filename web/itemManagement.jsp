<%@page import="uts.isd.model.*" %>
<%@page import="uts.isd.model.dao.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="PageComponents/CommonMeta.jsp"/>
        <title>Item Management</title>
    </head>
    
    <%
        Customer customer = (Customer)session.getAttribute("customer");
        DBManager manager = (DBManager)session.getAttribute("manager");
        String sort = (String)session.getAttribute("sort");
        if (customer == null || manager == null || !customer.isIsStaff()) {
            response.sendRedirect(customer != null ? "homePage.jsp" : "login.jsp");
            return;
        }
    %>
    <jsp:include page="PageComponents/JSPHeader.jsp"/>

    <body>
        <center><h1 class="spaced-letters blue">ITEM MANAGEMENT</h1></center>
        <form method="POST" action="DBServlet">
            <center>
                <button name="button" formnovalidate value="sortName" type="submit">Sort by name</button>
                <button name="button" formnovalidate value="sortType" type="submit">Sort by type</button>
                <button name="button" formnovalidate value="add" type="submit">Add new item</button>
                <button name="button" value="edit" type="submit">Edit</button>
                <button name="button" value="delete" type="submit">Delete</button>
            </center>
            <table class="align-center form-table">
                <tr>
                    <th>Item</th>
                    <th>Price</th>
                    <th>Type</th>
                    <th>Stock</th>

                    <th>Select</th>
                </tr>

                <% for (Item item : manager.fetchItems(sort)) {%>
                    <tr>
                        <td><label for="<%=item.getItem()%>"><%= item.getItem() %></label></td>  
                        <td><label for="<%=item.getItem()%>">$<%= item.getPrice() %></label></td>
                        <td><label for="<%=item.getItem()%>"><%= item.getType() %></label></td>
                        <td><label for="<%=item.getItem()%>"><%= item.getStock() %></label></td>

                        <td> <input type="radio" name="itemID" id="<%=item.getItem()%>" value="<%=item.getItemID()%>" required> </td>
                    </tr>
                <% } %>
            </table>
        </form>
    </body>
    <jsp:include page="PageComponents/JSPFooter.jsp"/>
</html>

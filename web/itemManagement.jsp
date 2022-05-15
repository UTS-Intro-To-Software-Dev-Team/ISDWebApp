<%@page import="uts.isd.model.*" %>
<%@page import="uts.isd.model.dao.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="PageComponents/CommonMeta.jsp"/>
        <title>Item Management Page</title>
    </head>
    
    <%
        Customer cust = (Customer)session.getAttribute("customer");
        DBManager manager = (DBManager)session.getAttribute("manager");
        String sort = (String)session.getAttribute("sort");
        if (cust == null || manager == null || !manager.isCustomerStaff(cust.getEmail())) {
            response.sendRedirect("homePage.jsp");
            return;
        }
    %>
    <jsp:include page="PageComponents/JSPHeader.jsp"/>

    <body>
        <h1>Item Management</h1>
        <form method="POST" action="DBServlet">
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
                        <td><%= item.getItem() %></td>  
                        <td>$<%= item.getPrice() %></td>
                        <td><%= item.getType() %></td>
                        <td><%= item.getStock() %></td>

                        <td> <input type="radio" name="itemName" value="<%=item.getItem()%>"> </td>
                    </tr>
                <% } %>
            </table>
            <button name="button" value="edit" type="submit">Edit</button>
            <button name="button" value="delete" type="submit">Delete</button> 
        </form>
    </body>
    <jsp:include page="PageComponents/JSPFooter.jsp"/>
</html>

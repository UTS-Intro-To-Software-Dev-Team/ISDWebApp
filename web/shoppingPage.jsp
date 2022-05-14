<%@page import="uts.isd.model.*" %>
<%@page import="uts.isd.model.dao.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="PageComponents/CommonMeta.jsp"/>
        <title>Shopping Page</title>
    </head>

    <jsp:include page="PageComponents/JSPHeader.jsp"/>

    <body>
        <%
            DBManager manager = (DBManager)session.getAttribute("manager");
            String sort = (String)session.getAttribute("sort");
        %>
        <form method="POST" action="DBServlet">            
            <table class="align-center form-table">
                <tr>
                    <th>Item Name</th>
                    <th>Price</th>                  
                    <th>Type</th>
                    <th>Stock</th>
                    <th>Select</th>
                </tr>

                <% for (Item item : manager.fetchItems(sort)) {%>
                    <tr>
                        <td><%= item.getItem() %></td>  
                        <td><%= item.getPrice() %></td>
                        <td><%= item.getType() %></td>
                        <td><%= item.getStock() %></td>
                        <td> <input type="radio" name="itemName" value="<%=item.getItem()%>"> </td>
                    </tr>
                <% } %>
            </table>
            <button name="button" value="sortType" type="submit">Sort by type</button>
            <button name="button" value="sortName" type="submit">Sort by name</button>
            <button name="button" value="order" type="submit">Order</button>
        </form>
    </body>

    <jsp:include page="PageComponents/JSPFooter.jsp"/>
</html>

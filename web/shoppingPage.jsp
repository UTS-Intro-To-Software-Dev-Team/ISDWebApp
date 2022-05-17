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
            <center>
                <button name="button" formnovalidate value="sortName" type="submit">Sort by name</button>
                <button name="button" formnovalidate value="sortType" type="submit">Sort by type</button>
                <button name="button" value="order" type="submit">Order</button>
            </center>
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
                        <td><label for="<%=item.getItem()%>"><%= item.getItem() %></label></td>  
                        <td><label for="<%=item.getItem()%>">$<%= item.getPrice() %></label></td>
                        <td><label for="<%=item.getItem()%>"><%= item.getType() %></label></td>
                        <td><label for="<%=item.getItem()%>"><%= item.getStock() %></label></td>
                        <% if (item.getStock() > 0) { %>
                            <td> <input type="radio" name="itemID" id="<%=item.getItem()%>" value="<%=item.getItemID()%>" required> </td>
                        <% } %>
                    </tr>
                <% } %>
            </table>
        </form>
    </body>

    <jsp:include page="PageComponents/JSPFooter.jsp"/>
</html>

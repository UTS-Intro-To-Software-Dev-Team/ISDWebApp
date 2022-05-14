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
        %>
        <h1>Welcome!</h1>
        <table class="align-center form-table">
                <tr>
                    <th>Item Name</th>
                    <th>Price</th>                  
                    <th>Type</th>
                    <th>Stock</th>
                </tr>

                <% for (Item item : manager.fetchItems()) {%>
                <tr>
                    <td><%= item.getItem() %></td>  
                    <td><%= item.getPrice() %></td>
                    <td><%= item.getType() %></td>
                    <td><%= item.getStock() %></td>
                </tr>
                <% } %>
            </table>
    </body>

    <jsp:include page="PageComponents/JSPFooter.jsp"/>
</html>

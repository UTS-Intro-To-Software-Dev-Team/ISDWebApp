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
            String sortName = request.getParameter("sortName");
            String sortType = request.getParameter("sortType");
            String sort = "WHERE item LIKE '%" + (sortName != null ? sortName : "_") + "%' AND type LIKE '%" + (sortType != null ? sortType : "_") + "%'";
        %>
        <form method="POST" action="DBServlet">
            <center>
                <p>
                    <label for="sortName">Name: </label><input type="text" name="sortName" id="sortName" placeholder="Search by name" value="<%= (sortName != null ? sortName : "") %>">
                    &emsp;
                    <label for="sortType">Type: </label><input type="text" name="sortType" id="sortType" placeholder="Search by type" value="<%= (sortType != null ? sortType : "") %>">
                    &emsp;
                    <button name="button" formnovalidate value="search" type="submit">Search</button>
                    &emsp;
                    <button name="button" value="order" type="submit">Order</button>
                </p>
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

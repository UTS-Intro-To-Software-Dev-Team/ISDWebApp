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
        if (customer == null || manager == null || !customer.isIsStaff()) {
            response.sendRedirect(customer != null ? "homePage.jsp" : "login.jsp");
            return;
        }
        
        String sortName = request.getParameter("sortName");
        String sortType = request.getParameter("sortType");
        String sort = "WHERE item LIKE '%" + (sortName != null ? sortName : "_") + "%' AND type LIKE '%" + (sortType != null ? sortType : "_") + "%'";
    %>
    <jsp:include page="PageComponents/JSPHeader.jsp"/>

    <body>
        <center><h1 class="spaced-letters blue">ITEM MANAGEMENT</h1></center>
        <form method="POST" action="DBServlet">
            <center>
                <p>
                    <label for="sortName">Name: </label><input type="text" name="sortName" id="sortName" placeholder="Search by name" value="<%= (sortName != null ? sortName : "") %>">
                    &emsp;
                    <label for="sortType">Type: </label><input type="text" name="sortType" id="sortType" placeholder="Search by type" value="<%= (sortType != null ? sortType : "") %>">
                    &emsp;
                    <button name="button" formnovalidate value="search" type="submit">Search</button>
                    &emsp;
                    <button name="button" formnovalidate value="add" type="submit">Add new item</button>
                    &emsp;
                    <button name="button" value="edit" type="submit">Edit</button>
                    &emsp;
                    <button name="button" value="delete" type="submit">Delete</button>
                </p>
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

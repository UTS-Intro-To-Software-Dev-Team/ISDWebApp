<%@page import="uts.isd.model.*" %>
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
            Item item = (Item)session.getAttribute("item");
            Customer customer = (Customer)session.getAttribute("customer");
        %>
        <h1>Welcome!</h1>
        <% if (item != null) { %>
            <p>List of items: <%= item.getItem() %></p>
            
        <% } %>
    </body>

    <jsp:include page="PageComponents/JSPFooter.jsp"/>
</html>

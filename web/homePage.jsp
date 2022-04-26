<%@page import="uts.isd.model.Customer" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/PageComponents/CommonMeta.jsp"/>
        <title>Home Page</title>
    </head>

    <%
        Customer customer = (Customer)session.getAttribute("customer");
        if (customer == null && request.getParameter("register") != null) {
            customer = new Customer(
                request.getParameter("firstname"),
                request.getParameter("lastname"),
                request.getParameter("email"),
                request.getParameter("username"),
                request.getParameter("password")
            );
            session.setAttribute("customer", customer);
        }
    %>

    <body>
        <jsp:include page="PageComponents/JSPHeader.jsp"/>

        <% if (customer != null) { %>
            <h1>Welcome <%= customer.getUsername() %>!</h1>
        <% } else { %>
            <h1>Welcome!</h1>
        <% } %>

        <% if (request.getParameter("register") != null) { %>
            <p>Hello newly registered customer!</p>
        <% } %>

        <% if (request.getParameter("login") != null) { %>
            <p>Welcome back registered customer!</p>
        <% } %>
    </body>
</html>

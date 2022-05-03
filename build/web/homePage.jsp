<%@page import="uts.isd.model.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="PageComponents/CommonMeta.jsp"/>
        <title>Home Page</title>
    </head>

    <%
        boolean isRegistered = request.getParameter("register") != null;
        boolean isLoggedIn = request.getParameter("login") != null;

        CustomerDatabase cdb = (CustomerDatabase)session.getAttribute("Customer Database");

        Customer customer;
        if (isRegistered) {
            customer = new Customer(
                request.getParameter("firstname"),
                request.getParameter("lastname"),
                request.getParameter("email"),
                request.getParameter("username"),
                request.getParameter("password")
            );
            session.setAttribute("customer", customer);
            cdb.AddCustomer(customer);
        } else if (isLoggedIn) {
            customer = cdb.GetCustomer(request.getParameter("username"), request.getParameter("password"));
        } else {
            customer = (Customer)session.getAttribute("customer");
        }
    %>

    <body>
        <jsp:include page="PageComponents/JSPHeader.jsp"/>
        <% if (customer != null) { %>
            <h1>Welcome <%= customer.getUsername() %>!</h1>
        <% } else { %>
            <h1>Welcome!</h1>
        <% } %>

        <% if (isRegistered) { %>
            <p>Hello newly registered customer!</p>
        <% } %>

        <% if (isLoggedIn) { %>
            <p>Welcome back registered customer!</p>
        <% } %>
    </body>
</html>

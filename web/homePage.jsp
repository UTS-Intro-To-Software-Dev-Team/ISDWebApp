<%@page import="uts.isd.model.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="PageComponents/CommonMeta.jsp"/>
        <title>Home Page</title>
    </head>

    <jsp:include page="PageComponents/JSPHeader.jsp"/>

    <body>
        <%
            Customer customer = (Customer)session.getAttribute("customer");
        %>
        <a href="shoppingPage.jsp"><button type="submit"><b>Shopping</b></button></a>
        <h1>Welcome!</h1>
        <% if (customer != null) { %>
            <p>ID: <%= customer.getCustomerID() %></p>
            <p>Email: <%= customer.getEmail() %></p>
            <p>Password: <%= customer.getPassword() %></p>
            <p>First name: <%= customer.getFirstName() %></p>
            <p>Last name: <%= customer.getLastName() %></p>
            <p>DOB: <%= customer.getDob() %></p>
            <p>Phone: <%= customer.getPhone() %></p>
            <p>Street: <%= customer.getStreet() %></p>
            <p>City: <%= customer.getCity() %></p>
            <p>State: <%= customer.getState() %></p>
            <p>Postcode: <%= customer.getPostcode() %></p>
            <p>Is Staff? <%= customer.isIsStaff() %></p>
            <p>Is Active? <%= customer.isIsActive() %></p>
        <% } %>
    </body>
    <jsp:include page="PageComponents/JSPFooter.jsp"/>
</html>

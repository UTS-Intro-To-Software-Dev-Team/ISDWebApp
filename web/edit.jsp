<%-- 
    Document   : edit.jsp
    Created on : May 7, 2022, 1:02:03 PM
    Author     : willi
--%>

<%@page import="uts.isd.model.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="PageComponents/CommonMeta.jsp"/>
        <title>Edit Page</title>
    </head>
    <body>
        <%
            Customer customer = (Customer)session.getAttribute("customer");
            String updated = request.getParameter("updated");
        %>
        <h1 class="align-center">Edit User Details <span><%= (updated !=null) ? "Update was successful":""%></span></h1>
        <form class="align-center" action="edit.jsp" method="POST">

            <label for="firstName">First Name:</label>
            <br>
            <input type="text" name="First Name" value="${customer.firstName}">
            <br>
            <br>
            
            <label  for="lastName">Last Name:</label>
            <br>
            <input type="text" name="Last Name" value="${customer.lastName}">
            <br>
            <br>
            
            <label for="email">Email:</label>
            <br>
            <input type="text" name="email" value="${customer.email}">
            <br>
            <br>
            
            <label for="username">Username:</label>
            <br>
            <input type="text" name="username" value="${customer.username}">
            <br>
            <br>
            
            <label for="password">Password:</label>
            <br>
            <input type="password" name="password" value="${customer.password}">
            <br>
            <br>
            
            <input type="submit" value="Update">
            <input type="hidden" name="updated" value="updated">
            
        </form>
            <%
                String firstName = request.getParameter("firstName");
                String lastName = request.getParameter("lastName");
                String email = request.getParameter("email");
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                
                customer = new Customer(firstName, lastName, email, username, password);
                session.setAttribute("customer", customer);
            %>
    </body>
</html>

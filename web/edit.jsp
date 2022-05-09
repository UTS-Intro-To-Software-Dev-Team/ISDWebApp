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
            Customer customer = (Customer)session.getAttribute("Customer");
            String updated = (String)session.getAttribute("Updated");
        %>
        <h1 class="align-center">Edit User Details <span><%= (updated !=null) ? "Update was successful":""%></span></h1>
        <form class="align-center" action="DBServlet" method="POST">

            <label for="email">Email:</label>
            <br>
            <input type="text" name="email" value="${customer.email}">
            <br>
            <br>
            
            <label for="password">Password:</label>
            <br>
            <input type="password" name="password" value="${customer.password}">
            <br>
            <br>
            
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
            
            <label for="dob">Date of Birth:</label>
            <br>
            <input type="text" name="dob" value="${customer.dob}">
            <br>
            <br>
            
            <label for="street">Street:</label>
            <br>
            <input type="text" name="street" value="${customer.street}">
            <br>
            <br>
            
            <label for="city">City:</label>
            <br>
            <input type="text" name="city" value="${customer.city}">
            <br>
            <br>
            
            <label for="state">State:</label>
            <br>
            <input type="text" name="state" value="${customer.state}">
            <br>
            <br>
            
            <label for="postcode">Postcode:</label>
            <br>
            <input type="text" name="postcode" value="${customer.postcode}">
            <br>
            <br>
            
            <button> <a href="homePage.jsp"> Cancel </a> </button>
            <input type="submit" value="Update">
            <input type="hidden" name="updated" value="updated">
            
        </form>
            <%
                String email = request.getParameter("email");
                String password = request.getParameter("password");
                String firstName = request.getParameter("firstName");
                String lastName = request.getParameter("lastName");
                String dob = request.getParameter("dob");
                String street = request.getParameter("street");
                String city = request.getParameter("city");
                String state = request.getParameter("state");
                String postcode = request.getParameter("postcode");
                
                
                customer = new Customer(email, password, firstName, lastName,  dob, street, city, state, postcode);
                session.setAttribute("customer", customer);
            %>
    </body>
</html>

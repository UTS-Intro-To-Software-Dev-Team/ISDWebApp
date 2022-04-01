<%--
    Document   : register
    Created on : 23 Mar 2022, 11:04:43 am
    Author     : zelno
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="PageComponents/CommonMeta.jsp"/>
        <title>Register Page</title>
    </head>

    <body>
        <jsp:include page="PageComponents/JSPHeader.jsp"/>

        <h1 class="align-center">CREATE ACCOUNT</h1>

        <form action="login.jsp" method="POST">
            <table class="align-center form-table">
                <tr><td><label for="firstname">First Name:</label></td></tr>
                <tr><td><input type="text" name="firstname" placeholder="Enter first name" required></td></tr>

                <tr><td><label for="lastname">Last Name:</label></td></tr>
                <tr><td><input type="text" name="lastname" placeholder="Enter last name" required></td></tr>

                <tr><td><label for="email">Email:</label></td></tr>
                <tr><td><input type="email" name="email" placeholder="Enter email" required></td></tr>

                <tr><td><label for="password">Password:</label></td></tr>
                <tr><td><input type="password" name="password" placeholder="Enter password" required></td></tr>

                <tr><td><button type="submit" value="Register"><b>Register</b></button></td></tr>
            </table>
        </form>
    </body>
</html>

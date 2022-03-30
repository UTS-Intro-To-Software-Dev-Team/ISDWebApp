<%--
    Document   : register
    Created on : 23 Mar 2022, 11:04:43 am
    Author     : zelno
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="Discordo.css">
        <title>Register Page</title>
    </head>

    <jsp:include page="JSPHeader.jsp"/>
    
    <body>
        <form class="align-center" action="/ISDWebApp/login.jsp" method="POST">
            <h1>CREATE ACCOUNT</h1>

            <label  for="firstname">First Name:</label>
            <br>
            <input type="text" name="firstname" placeholder="Jack">
            <br>

            <label for="lastname">Last Name:</label>
            <br>
            <input type="text" name="lastname" placeholder="Frost">
            <br>

            <label for="email">Email:</label>
            <br>
            <input type="email" name="email" placeholder="12345678@student.uts.edu.au">
            <br>

            <label for="password">Password:</label>
            <br>
            <input type="password" name="password">
            <br>

            <button type="submit" value="Register"><b>Register</b></button>
        </form>
    </body>
</html>

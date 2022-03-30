<%--
    Document   : login
    Created on : 23 Mar 2022, 11:02:14 am
    Author     : zelno
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="Discordo.css">
        <title>Login Page</title>
    </head>

    <jsp:include page="JSPHeader.jsp"/>

    <body>
        <div class="align-center">
            <label for="email">Username/Email:</label>
            <br>
            <input type="email" name="email" placeholder="12345678@student.uts.edu.au">
            <br>

            <label for="password">Password:</label>
            <br>
            <input type="password" name="password">
            <br>

            <button type="button"><b>Submit</b></button>
        </div>
    </body>
</html>

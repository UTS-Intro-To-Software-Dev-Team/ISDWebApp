<%--
    Document   : logout
    Created on : 23 Mar 2022, 11:04:34 am
    Author     : zelno
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="Discordo.css">
        <title>Logout Page</title>
    </head>

    <jsp:include page="JSPHeader.jsp"/>
    
    <body>
        <form class="align-center" action="/ISDWebApp/login.jsp" method="POST">
            <h1>YOU HAVE BEEN LOGGED OUT</h1>
            <button type="submit"><b>Return to Login</b></button>
        </form>
    </body>
</html>

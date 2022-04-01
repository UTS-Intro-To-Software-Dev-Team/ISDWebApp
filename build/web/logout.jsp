<%--
    Document   : logout
    Created on : 23 Mar 2022, 11:04:34 am
    Author     : zelno
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="PageComponents/CommonMeta.jsp"/>
        <title>Logout Page</title>
    </head>

    <jsp:include page="PageComponents/JSPHeader.jsp"/>

    <body>
        <div class="align-center">
            <h1>YOU HAVE BEEN LOGGED OUT</h1>
            <a href="login.jsp"><button type="submit"><b>Return to Login</b></button></a>
        </div>
    </body>
</html>

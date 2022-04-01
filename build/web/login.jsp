<%--
    Document   : login
    Created on : 23 Mar 2022, 11:02:14 am
    Author     : zelno
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="PageComponents/CommonMeta.jsp"/>
        <title>Login Page</title>
    </head>

    <body>
        <jsp:include page="PageComponents/JSPHeader.jsp"/>
        <form action="homePage.jsp" method="POST">
            <table class="align-center form-table">
                <tr><td><label for="email">Username/Email:</label></td></tr>
                <tr><td><input type="email" name="email" placeholder="Enter email" required></td></tr>

                <tr><td><label for="password">Password:</label></td></tr>
                <tr><td><input type="password" name="password" placeholder="Enter password" required></td></tr>

                <tr><td><button type="button"><b>Submit</b></button></td></tr>
            </table>
        </form>
    </body>
</html>

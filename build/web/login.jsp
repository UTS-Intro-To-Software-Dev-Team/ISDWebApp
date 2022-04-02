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
                <input type="hidden" name="login" value="true">
            <table class="align-center form-table">
                <tr><td><label for="username"><b>Username:</b></label></td></tr>
                <tr><td><input type="text" name="username" placeholder="Enter username" required></td></tr>

                <tr><td><label for="password"><b>Password:</b></label></td></tr>
                <tr><td><input type="password" name="password" placeholder="Enter password" required></td></tr>

                <tr><td><button type="submit" value="login"><b>Login</b></button></td></tr>
            </table>
        </form>
    </body>
</html>

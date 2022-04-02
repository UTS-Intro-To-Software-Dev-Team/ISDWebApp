<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="PageComponents/CommonMeta.jsp"/>
        <title>Register Page</title>
    </head>

    <body>
        <jsp:include page="PageComponents/JSPHeader.jsp"/>

        <h1 class="align-center spaced-letters blue">CREATE ACCOUNT</h1>

        <form action="homePage.jsp" method="POST">
            <input type="hidden" name="register" value="true">

            <table class="align-center form-table">
                <tr><td><label for="firstname"><b>First Name:</b></label></td></tr>
                <tr><td><input type="text" name="firstname" placeholder="Enter first name" required></td></tr>

                <tr><td><label for="lastname"><b>Last Name:</b></label></td></tr>
                <tr><td><input type="text" name="lastname" placeholder="Enter last name" required></td></tr>

                <tr><td><label for="email"><b>Email:</b></label></td></tr>
                <tr><td><input type="email" name="email" placeholder="Enter email" required></td></tr>

                <tr><td><label for="username"><b>Username:</b></label></td></tr>
                <tr><td><input type="text" name="username" placeholder="Enter username" required></td></tr>

                <tr><td><label for="password"><b>Password:</b></label></td></tr>
                <tr><td><input type="password" name="password" placeholder="Enter password" required></td></tr>

                <tr><td><button type="submit" value="Register"><b>Register</b></button></td></tr>
            </table>
        </form>
    </body>
</html>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="PageComponents/CommonMeta.jsp"/>
        <title>Logout Page</title>
    </head>

    <jsp:include page="PageComponents/JSPHeader.jsp"/>

    <body>
        <div class="align-center spaced-letters">
            <h1>YOU HAVE BEEN LOGGED OUT</h1>
            <a href="login.jsp"><button type="submit"><b>Return to Login</b></button></a>
            <% session.invalidate(); %>
        </div>
    </body>
</html>

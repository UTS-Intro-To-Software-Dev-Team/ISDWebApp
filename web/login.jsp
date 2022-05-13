<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="PageComponents/CommonMeta.jsp"/>
        <title>Login Page</title>
    </head>

    <jsp:include page="PageComponents/JSPHeader.jsp"/>

    <body>
        <%
            String emailErr = (String) session.getAttribute("emailErr");
            String passErr = (String) session.getAttribute("passErr");
            String email = request.getParameter("email");
        %>

        <form action="DBServlet" method="POST">
            <input type="hidden" name="login" value="true">

            <table class="align-center form-table">
                <tr><td><label for="email"><b>Email:</b></label></td></tr>
                <tr><td><input type="email" name="email" placeholder="Enter username" required value="<%= email != null ? email : "" %>"></td></tr>
                <% if (emailErr != null) { %>
                    <tr><td><b><%= emailErr %></b></td></tr>
                <% } %>

                <tr><td><label for="password"><b>Password:</b></label></td></tr>
                <tr><td><input type="password" name="password" placeholder="Enter password" required minlength="8"></td></tr>
                <% if (passErr != null) { %>
                    <tr><td><b><%= passErr %></b></td></tr>
                <% } %>

                <tr><td><button type="submit"><b>Login</b></button></td></tr>
            </table>
        </form>
    </body>
</html>

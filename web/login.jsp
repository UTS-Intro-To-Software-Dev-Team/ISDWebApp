<%@page import="uts.isd.model.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="PageComponents/CommonMeta.jsp"/>
        <title>Login</title>
    </head>

    <%
        if ((Customer)session.getAttribute("customer") != null) {
            response.sendRedirect("homePage.jsp");
            return;
        }
    %>
    <jsp:include page="PageComponents/JSPHeader.jsp"/>

    <body>
        <%
            String emailErr = (String) session.getAttribute("emailErr");
            String passErr = (String) session.getAttribute("passErr");
            
            String email = request.getParameter("email");
            email = email != null ? email : "";
        %>
        
        <h1 class="align-center spaced-letters blue">LOGIN</h1>
        <form action="DBServlet" method="POST">
            <table class="align-center form-table">
                <tr><td><label for="email"><b>Email:</b></label></td></tr>
                <tr><td><input type="email" name="email" id="email" placeholder="Enter username" required value="<%= email %>"></td></tr>
                <% if (emailErr != null) { %>
                    <tr><td><b><%= emailErr %></b></td></tr>
                <% } %>

                <tr><td><label for="password"><b>Password:</b></label></td></tr>
                <tr><td><input type="password" name="password" id="password" placeholder="Enter password" required></td></tr>
                <% if (passErr != null) { %>
                    <tr><td><b><%= passErr %></b></td></tr>
                <% } %>

                <tr><td><button type="submit"><b>Login</b></button></td></tr>
            </table>
        </form>
    </body>
    <jsp:include page="PageComponents/JSPFooter.jsp"/>
</html>

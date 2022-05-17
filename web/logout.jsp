<%@page import="uts.isd.model.*" %>
<%@page import="uts.isd.model.dao.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="PageComponents/CommonMeta.jsp"/>
        <title>Logout</title>
    </head>
    
    <%
        Customer customer = (Customer)session.getAttribute("customer");
        DBManager manager = (DBManager)session.getAttribute("manager");
        if (customer == null || manager == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        
        manager.addAccessLog(customer, "Logged out");
    %>

    <jsp:include page="PageComponents/JSPHeader.jsp"/>

    <body>
        <div class="align-center spaced-letters">
            <h1>YOU HAVE BEEN LOGGED OUT</h1>
            <a href="login.jsp"><button type="submit"><b>Return to Login</b></button></a>
            <% session.invalidate(); %>
        </div>
    </body>
</html>

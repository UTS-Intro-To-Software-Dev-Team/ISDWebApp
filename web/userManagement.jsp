<%@page import="uts.isd.model.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="PageComponents/CommonMeta.jsp"/>
        <title>User Management Page</title>
    </head>
    
    <jsp:include page="PageComponents/JSPHeader.jsp"/>
    
    <body>
        <h1>User Management</h1>
        <%
            Customer customer = (Customer)session.getAttribute("customer");
        %>
        <table>
            <tr>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email</th>
                <th>Password</th>
                <th>Date of Birth</th>
                <th>Street</th>
                <th>City</th>
                <th>State</th>
                <th>Postcode</th>
            </tr>
            <tr>
                <td>${customer.firstName}</td>
                <td>${customer.LastName}</td>
            </tr>
        </table>
    </body>
</html>

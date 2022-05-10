<%@page import="uts.isd.model.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="PageComponents/CommonMeta.jsp"/>
        <title>Shipment Page</title>
    </head>

    <jsp:include page="PageComponents/JSPHeader.jsp"/>

    <body>
        <%
            Customer customer = (Customer)session.getAttribute("customer");
        %>
        <h1>Shipment Methods</h1>
        <% if (customer != null) { %>
            <%-- add logic to get the methods list --%>
        <% } %>
    </body>

    <jsp:include page="PageComponents/JSPFooter.jsp"/>
</html>

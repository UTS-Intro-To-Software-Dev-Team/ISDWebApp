<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="PageComponents/CommonMeta.jsp"/>
        <title>Shipment Page</title>
    </head>
    
    <%
        if (session.getAttribute("customer") == null) {
            response.sendRedirect("login.jsp");
        }
    %>
    <jsp:include page="PageComponents/JSPHeader.jsp"/>
    
    <body>
        <h1>Hello World!</h1>
    </body>
    
    <jsp:include page="PageComponents/JSPFooter.jsp"/>
</html>

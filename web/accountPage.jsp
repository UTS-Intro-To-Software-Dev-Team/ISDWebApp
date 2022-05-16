<%@page import="uts.isd.model.*" %>
<%@page import="uts.isd.model.dao.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="PageComponents/CommonMeta.jsp"/>
        <title>My account</title>
    </head>

    <%
        Customer customer = (Customer)session.getAttribute("customer");
        DBManager manager = (DBManager)session.getAttribute("manager");
        if (customer == null || manager == null) {
            response.sendRedirect("login.jsp");
            return;
        }
    %>
    <jsp:include page="PageComponents/JSPHeader.jsp"/>
    <body>
        <form method="POST" action="DBServlet">
            <button name="button" value="edit" type="submit">Edit Account Details</button>
            <button name="button" value="paymentMethods" type="submit">View Payment Methods</button>
            <button name="button" value="shipmentMethods" type="submit">View Shipment Methods</button>
            <button name="button" value="orderHistory" type="submit">View Order History</button>
        </form>
    </body>
</html>

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
        <center>
            <a href="edit.jsp"><button>Edit Account Details</button></a>
            <a href="paymentMethods.jsp"><button>View Payment Methods</button></a>
            <a href="shipmentMethods.jsp"><button>View Shipment Methods</button></a>
            <a href="orderHistory.jsp"><button>View Order History</button></a>
        </center>
    </body>
</html>

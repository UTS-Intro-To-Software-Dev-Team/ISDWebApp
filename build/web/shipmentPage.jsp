<%@page import="uts.isd.model.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList" %>
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
            Shipment method = (Shipment)session.getAttribute("method");
            
            ArrayList<Shipment> methodsList = (ArrayList<Shipment>)session.getAttribute("methodsList");
        %>
        <h1>Shipment Methods</h1>
        <% if (customer != null) { %>
            <%-- add logic to get the methods list --%>
           <% if(method != null) { %>
                <p>Your current shipping method is <%= method.getMethod() %>/p>
            <% } %>
            <%--print out the shipping methods using a loop--%>
           <% for(Shipment shippingMethod : methodsList) {%> 
                <p><%=shippingMethod.getMethod()%></p>
            <%  } %>
           
        <% } %>
    </body>

    <jsp:include page="PageComponents/JSPFooter.jsp"/>
</html>

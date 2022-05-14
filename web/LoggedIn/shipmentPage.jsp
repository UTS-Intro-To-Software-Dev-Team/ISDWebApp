<%@page import="uts.isd.model.*" %>
<%@page import="uts.isd.model.dao.*" %>

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
            String shippingMethod = request.getParameter("method");
            String shippingDate = request.getParameter("date");
            String shippingStreet = request.getParameter("street");
            String shippingCcity = request.getParameter("city");
            String shippingState = request.getParameter("state");
            String shippingPostcode = request.getParameter("postcode");        
            String orderId = request.getParameter('orderId');
                    
            Customer customer = (Customer)session.getAttribute("customer");
            Shipment method = (Shipment)session.getAttribute("method");
            
            DBManager manager = (DBManager)session.getAttribute("manager");
            
            ArrayList<Shipment> shipmentList = manager.fetchShipment(orderId);
        %>
        
        
        
        <h1>Shipment</h1>
       
        <ul>
            <li><a href="createShipmentPage.jsp">Add Shipment Details</a></li>
            <li><a>Update Shipment Details</a></li>
            <li><a href="searchShipment.jsp">Search Shipment Details</a></li>
        </ul>
        
        
        <h2>Shipment History</h2>
        
        <table>
            <% if (customer != null) { %>
                  <%-- add logic to get the methods list --%>
         < <% if(method != null) { %> 
                <p>Your current shipping method is <%= method.getMethod() %>/p>
            <% } %> 
            <%--print out the shipping methods using a loop--%>
            <%if(shipmentList != null) { %>
             <% for(Shipment item : shipmentList) { %> 
             <tr>
                 <td><%=item.getShipmentDate()%></td>
                 <td><%=item.getMethod()%></td>
             </tr>
                <%  } %> 
           <% } %>
        <% } %>  
        </table>
     
       
  </body>

    <jsp:include page="PageComponents/JSPFooter.jsp"/>
</html>

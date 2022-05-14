<%@page import="uts.isd.model.*" %>
<%@page import="uts.isd.model.dao.*" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="PageComponents/CommonMeta.jsp"/>
        <title>Update Shipment Page</title>
    </head>

    <jsp:include page="PageComponents/JSPHeader.jsp"/>

    <body>
        <%              
            Customer customer = (Customer)session.getAttribute("customer");
            Shipment shipment = (Shipment)session.getAttribute("shipment");
            
            DBManager manager = (DBManager)session.getAttribute("manager");
        %>
        
        
        
        <h1>Shipment</h1>
        <h2>Update Shipping Details</h2>
        
        <form action="DBServlet">
            <table class="align-center form-table">
                <tr><td><label for="method">Shipping Method</label></td></tr>
                <tr><td><input type="text" name="method" placeholder="Enter Shipping Method" value="${shipment.getMethod()}"/></td></tr>
                <tr><td><label for="date">Shipping Date</label></td></tr>
                <tr><td><input type="date" name="method" value="${shipment.getShipmentDate()}"/></td></tr>
                 <tr><td><label for="street">Street</label></td></tr>
                <tr><td><input type="text" name="street" value="${shipment.getStreet()}"/></td></tr>
                <tr><td><label for="city">City</label></td></tr>
                <tr><td><input type="text" name="city" value="${shipment.getCity()}"/></td></tr>
                
                <tr><td><label for="state"><b>State:</b></label></td></tr>
                <tr><td>
                  <select name="state" value="${shipment.getState()}" required selected value="<%-- <%= state != null ? state : "" %> --%>">
                      <option value="NSW">New South Wales</option>
                      <option value="QLD">Queensland</option>
                      <option value="SA">South Australia</option>
                      <option value="TAS">Tasmania</option>
                      <option value="VIC">Victoria</option>
                      <option value="WA">Western Australia</option>
                  </select>
                </td></tr>
                <tr><td><label for="postcode">Postcode</label></td></tr>
                <tr><td><input type="text" name="postcode" value="${shipment.getPostCode()}"/></td></tr>
                <tr><td><button type="submit"><b>Update Shipment Details</b></button></td></tr>
            </table>
        </form>
    </body>

    <jsp:include page="PageComponents/JSPFooter.jsp"/>
</html>

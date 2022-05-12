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
            String shippingMethod = request.getParameter("method");
            String shippingDate = request.getParameter("date");
            String shippingStreet = request.getParameter("street");
            String shippingCcity = request.getParameter("city");
            String shippingState = request.getParameter("state");
            String shippingPostcode = request.getParameter("postcode");        
                        
            Customer customer = (Customer)session.getAttribute("customer");
            Shipment method = (Shipment)session.getAttribute("method");
            
            ArrayList<Shipment> methods = (ArrayList<Shipment>)session.getAttribute("methodsList");
        %>
        
        
        
        <h1>Shipment</h1>
        <h2>Create Shipping Details</h2>
        
        <form action="DBServlet">
            <table class="align-center form-table">
                <tr><td><label for="method">Shipping Method</label></td></tr>
                <tr><td><input type="text" name="method" placeholder="Enter Shipping Method"/></td></tr>
                <tr><td><label for="date">Shipping Date</label></td></tr>
                <tr><td><input type="date" name="method""/></td></tr>
                 <tr><td><label for="street">Street</label></td></tr>
                <tr><td><input type="text" name="street""/></td></tr>
                <tr><td><label for="city">City</label></td></tr>
                <tr><td><input type="text" name="city""/></td></tr>
                
                <tr><td><label for="state"><b>State:</b></label></td></tr>
                <tr><td>
                  <select name="state" required selected value="<%-- <%= state != null ? state : "" %> --%>">
                      <option value="NSW">New South Wales</option>
                      <option value="QLD">Queensland</option>
                      <option value="SA">South Australia</option>
                      <option value="TAS">Tasmania</option>
                      <option value="VIC">Victoria</option>
                      <option value="WA">Western Australia</option>
                  </select>
                </td></tr>
                <tr><td><label for="postcode">Postcode</label></td></tr>
                <tr><td><input type="text" name="postcode""/></td></tr>
                <tr><td><button type="submit"><b>Create Shipment Details</b></button></td></tr>
            </table>
        </form>>
        <h2>Shipment Methods</h2>
        <% if (customer != null) { %>
            <%-- add logic to get the methods list --%>
           <% if(method != null) { %>
                <p>Your current shipping method is <%= method.getMethod() %>/p>
            <% } %>
            <%--print out the shipping methods using a loop--%>
            <%if(methods != null) { %>
             <% for(Shipment meth : methods) { %> 
                    <p><%=meth.getMethod()%></p>
                <%  } %>
           <% } %>
        <% } %>
    </body>

    <jsp:include page="PageComponents/JSPFooter.jsp"/>
</html>

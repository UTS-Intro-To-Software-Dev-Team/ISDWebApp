<%-- 
    Document   : searchShipment
    Created on : 14 May 2022, 9:37:17 am
    Author     : christopheraverkos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Shipment</title>
    </head>
    <body>
        
        <% 
            Customer customer = session.getAttribute("customer");
            Shipment shipment = session.getAttribute("searchShipment");
            String shipmentId = getParameter('shipmendId');
        %>
      <form action="DBServlet">
            <table class="align-center form-table">
                <tr><td><label for="method">Shipment ID</label></td></tr>
                <tr><td><input type="text" name="shipmentId" placeholder="Enter Shipment Id"/></td></tr>       
              
                <tr><td><button type="submit"><b>Create Shipment Details</b></button></td></tr>
            </table>
        </form>>
        
        <% if(searchShipment != null){ %>
            <b><p>Shipment Details</p></b>
            <table>
                <thead>
                    <tr>
                        <th><b>Shipment ID</b></th>
                        <th><b>Shipment Date</b></th>
                        <th><b>Shipment Method</b></th>
                        <th><b>Street</b></th>
                        <th><b>City</b></th>
                        <th><b>State</b></th>
                        <th><b>Postcode</b></th>
                    </tr>
                </thead>
               
                <tbody> 
                    <tr>
                        <td><p><%=searchShipment.getShippingId()%></p></td>
                        <td><p><%=searchShipment.getShippingDate()%></p></td>
                        <td><p><%=searchShipment.getMethod()%></p></td>
                        <td><p><%=searchShipment.getStreet()()%></p></td>
                        <td><p><%=searchShipment.getCity()%></p></td>
                        <td><p><%=searchShipment.getState()%></p></td>
                        <td><p><%=searchShipment.getPostCode()%></p></td>
                    </tr> 
                </tbody>
               
                
            </table> 
                
        <%  } else { %>
            <span><%=(searchPayment != null ? searchPayment : "")%></span>
        <% } %>
    </body>
</html>

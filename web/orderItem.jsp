<%@page import="uts.isd.model.*" %>
<%@page import="uts.isd.model.dao.*" %>
<%@page import="java.util.ArrayList" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="PageComponents/CommonMeta.jsp"/>
        <title>Ordering Page</title>
    </head>
    
    <%
        Customer customer = (Customer)session.getAttribute("customer");
        DBManager manager = (DBManager)session.getAttribute("manager");
        if (customer == null || manager == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        
        Item item = manager.findItem(Integer.parseInt((String)session.getAttribute("itemID")));
        if (item == null) {
            response.sendRedirect("shoppingPage.jsp");
            return;
        }
        session.setAttribute("item", item);
        
        ArrayList<Payment> paymentMethods = manager.fetchPaymentMethods(customer.getCustomerID());
        ArrayList<Shipment> shipmentMethods = manager.fetchShipmentMethods(customer.getCustomerID());
        
        String postcodeErr = (String) session.getAttribute("postcodeErr");
        String paymentMethod = request.getParameter("paymentMethod");
        String shipmentMethod = request.getParameter("shipmentMethod");
    %>
    
    <jsp:include page="PageComponents/JSPHeader.jsp"/>
    
    <body>
        <center>
            <form method="POST" action="DBServlet">
                <h1><%= item.getItem() %></h1>
                <table class="testTable">
                    <tr>
                        <td><b>Unit price:</b></td>
                        <td>$<%= item.getPrice() %></td>
                    </tr>

                    <tr>
                        <td><b>Stock:</b></td>
                        <td><%= item.getStock() %></td>
                    </tr>

                    <tr>
                        <td>Quantity:</td>
                        <td><input type="number" name="quantity" placeholder="1 - <%= item.getStock() %>" value="1" required min="1" max="<%= item.getStock() %>"></td>
                    </tr>

                    <tr>
                        <td><label for="street"><b>Destination Street:</b></label></td>
                        <td><input type="text" name="street" placeholder="Enter street" value="${customer.street}" required></td>
                    </tr>

                    <tr>
                        <td><label for="city"><b>Destination City:</b></label></td>
                        <td><input type="text" name="city" placeholder="Enter city" value="${customer.city}" required></td>
                    </tr>

                    <tr>
                        <td><label for="state"><b>Destination State:</b></label></td>
                        <td>
                            <select name="state" required style="width: 100%;">
                                <option selected hidden>${customer.state}</option>
                                <option value="NSW">New South Wales</option>
                                <option value="QLD">Queensland</option>
                                <option value="SA">South Australia</option>
                                <option value="TAS">Tasmania</option>
                                <option value="VIC">Victoria</option>
                                <option value="WA">Western Australia</option>
                            </select>
                        </td>
                    </tr>

                    <tr>
                        <td><label for="postcode"><b>Destination Postcode:</b></label></td>
                        <td><input type="number" name="postcode" placeholder="####" style="width: 50px;" value="${customer.postcode}" required pattern="^[0-9]{4}$" minlength="4" maxlength="4" title="Postcode must be a 4 digit number."></td>
                    </tr>
                    <% if (postcodeErr != null) { %>
                        <tr><td><b><%= postcodeErr %></b></td></tr>
                    <% } %>

                    <tr>
                        <td><label for="paymentMethodID"><b>Payment Method:<b></label></td>
                        <td>
                            <select name="paymentMethodID" id="paymentMethodID" required style="width: 100%;">
                                <% if (paymentMethod != null) { %>
                                    <option selected hidden><%= paymentMethod %></option>
                                <% } else { %>
                                    <option hidden value="">Choose a method.</option>
                                <% } %>
                                
                                <% for(Payment method : paymentMethods) { %>
                                    <option value="<%= method.getPaymentID()%>"><%=method.getPaymentMethod()%></option>
                                <% } %>
                            </select>
                        </td>
                     </tr>
                     
                    <tr>
                        <td><label for="shipmentMethodID"><b>Shipment Method:<b></label></td>
                        <td>
                            <select name="shipmentMethodID" id="shipmentMethodID" required style="width: 100%;">
                                <% if (shipmentMethod != null) { %>
                                    <option selected hidden><%= shipmentMethod %></option>
                                <% } else { %>
                                    <option hidden value="">Choose a method.</option>
                                <% } %>
                                
                                <% for(Shipment method : shipmentMethods) { %>
                                    <option value="<%= method.getShipmentID()%>"><%=method.getShipmentMethod()%></option>
                                <% } %>
                            </select>
                        </td>
                     </tr>
                </table>
                <button type="submit" name="button" value="cancel" formnovalidate>Cancel</button>
                <button type="submit" name="button" value="paymentMethod" formnovalidate>View payment methods</button>
                <button type="submit" name="button" value="shipmentMethod" formnovalidate>View shipment methods</button>
                <button type="submit" name="button" value="purchase">Proceed to purchase</button>
            </form>
        </center> 
    </body>
    
    <jsp:include page="PageComponents/JSPFooter.jsp"/>
</html>

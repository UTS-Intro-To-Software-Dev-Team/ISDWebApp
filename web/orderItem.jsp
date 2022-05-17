<%@page import="uts.isd.model.*" %>
<%@page import="uts.isd.model.dao.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="PageComponents/CommonMeta.jsp"/>
        <title>Shipment Page</title>
    </head>
    
    <%
        Customer customer = (Customer)session.getAttribute("customer");
        DBManager manager = (DBManager)session.getAttribute("manager");
        if (customer == null || manager == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        
        Item item = manager.findItem((String)session.getAttribute("itemName"));
        if (item == null) {
            response.sendRedirect("shoppingPage.jsp");
            return;
        }
        String postcodeErr = (String) session.getAttribute("postcodeErr");
    %>
    
    <jsp:include page="PageComponents/JSPHeader.jsp"/>
    
    <body>
        <form method="POST" action="DBServlet">
            <h1 style="text-align: center"><%= item.getItem() %></h1>
            <table class="form-table align-center">
                <tr>
                    <td>Unit price:</td>
                    <td>$<%= item.getPrice() %></td>
                </tr>

                <tr>
                    <td>Stock:</td>
                    <td><%= item.getStock() %></td>
                </tr>
                
                <tr>
                    <td>Quantity:</td>
                    <td><input type="number" name="quantity" placeholder="1 - <%= item.getStock() %>" value="1" required min="1" max="<%= item.getStock() %>"></td>
                </tr>

                <tr>
                    <td><label for="street"><b>Street:</b></label></td>
                    <td><input type="text" name="street" placeholder="Enter street" value="${customer.street}" required></td>
                </tr>

                <tr>
                    <td><label for="city"><b>City:</b></label></td>
                    <td><input type="text" name="city" placeholder="Enter city" value="${customer.city}" required></td>
                </tr>

                <tr>
                    <td><label for="state"><b>State:</b></label></td>
                    <td>
                        <select name="state" required selected value="${customer.state}">
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
                    <td><label for="postcode"><b>Postcode:</b></label></td>
                    <td><input type="number" name="postcode" placeholder="####" style="width: 50px; text-align: center;" value="${customer.postcode}" required pattern="^[0-9]{4}$" minlength="4" maxlength="4" title="Postcode must be a 4 digit number."></td>
                </tr>
                <% if (postcodeErr != null) { %>
                    <tr><td><b><%= postcodeErr %></b></td></tr>
                <% } %>
            </table>
        </form>
    </body>
    
    <jsp:include page="PageComponents/JSPFooter.jsp"/>
</html>

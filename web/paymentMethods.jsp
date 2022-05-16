<%@page import="uts.isd.model.*" %>
<%@page import="uts.isd.model.dao.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="PageComponents/CommonMeta.jsp"/>
        <title>Payment Methods</title>
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
        <h1>Payment Methods</h1>
        <form method="POST" action="DBServlet">
            <div style="display: flex; justify-content: center;">
                <button name="button" value="add" type="submit">Add new method</button>
        </form>
        <form method="POST" action="DBServlet">
                <button name="button" value="edit" type="submit">Edit</button>
                <button name="button" value="delete" type="submit">Delete</button>
            </div>
            
            <table class="align-center form-table" style="width: 60%;">
                <tr>
                    <th>Payment Methods</th>
                    <th>Card Numbers</th>
                    <th>Full Name</th>
                    <th>Expiry Date</th>
                    <th>Cvv</th>

                    <th>Select</th>
                </tr>

                <% for (Payment payment : manager.fetchPaymentMethods(customer.getCustomerID())) {%>
                    <tr>
                        <td><%= payment.getPaymentMethod() %></td>  
                        <td><%= payment.getCardNumber() %></td>
                        <td><%= payment.getFullName() %></td>
                        <td><%= payment.getExpiryDate() %></td>
                        <td><%= payment.getCvv() %></td>

                        <td> <input type="radio" name="paymentID" value="<%=payment.getPaymentID()%>" required> </td>
                    </tr>
                <% } %>
            </table>
        </form>
    </body>
    <jsp:include page="PageComponents/JSPFooter.jsp"/>
</html>

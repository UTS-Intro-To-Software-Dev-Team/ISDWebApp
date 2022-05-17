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
        
        String sortA = request.getParameter("sortA");
        String sortB = request.getParameter("sortB");
        String sort = " AND CAST(paymentID AS CHAR(99)) LIKE '%" + (sortA != null ? sortA : "_") + "%' AND expiryDate LIKE '%" + (sortB != null ? sortB : "_") + "%'";
    %>
    <jsp:include page="PageComponents/JSPHeader.jsp"/>

    <body>
        <h1>Payment Methods</h1>
        <form method="POST" action="DBServlet">
            <center>
                <p>
                    <label for="sortA">ID: </label><input type="text" name="sortA" id="sortA" placeholder="Search by ID" value="<%= (sortA != null ? sortA : "") %>">
                    &emsp;
                    <label for="sortB">Date: </label><input type="text" name="sortB" id="sortB" placeholder="Search by expiry date" value="<%= (sortB != null ? sortB : "") %>">
                    &emsp;
                    <button name="button" formnovalidate value="search" type="submit">Search</button>
                    &emsp;
                    <button name="button" formnovalidate value="add" type="submit">Add new method</button>
                    &emsp;
                    <button name="button" value="edit" type="submit">Edit</button>
                    &emsp;
                    <button name="button" value="delete" type="submit">Delete</button>
                </p>
            </center>
            <table class="align-center form-table" style="width: 60%;">
                <tr>
                    <th>ID</th>
                    <th>Payment Methods</th>
                    <th>Card Numbers</th>
                    <th>Full Name</th>
                    <th>Expiry Date</th>
                    <th>Cvv</th>

                    <th>Select</th>
                </tr>

                <% for (Payment payment : manager.fetchPaymentMethods(customer.getCustomerID(), sort)) {%>
                    <tr>
                        <td><label for="<%= payment.getPaymentMethod() %>"><%= payment.getPaymentID() %></label></td>  
                        <td><label for="<%= payment.getPaymentMethod() %>"><%= payment.getPaymentMethod() %></label></td>  
                        <td><label for="<%= payment.getPaymentMethod() %>"><%= payment.getCardNumber() %></label></td>
                        <td><label for="<%= payment.getPaymentMethod() %>"><%= payment.getFullName() %></label></td>
                        <td><label for="<%= payment.getPaymentMethod() %>"><%= payment.getExpiryDate() %></label></td>
                        <td><label for="<%= payment.getPaymentMethod() %>"><%= payment.getCvv() %></label></td>
                        
                        <td> <input type="radio" name="paymentID" id="<%=payment.getPaymentMethod()%>" value="<%=payment.getPaymentID()%>" required> </td>
                    </tr>
                <% } %>
            </table>
        </form>
    </body>
    <jsp:include page="PageComponents/JSPFooter.jsp"/>
</html>

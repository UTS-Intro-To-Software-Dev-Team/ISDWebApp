<%@page import="uts.isd.model.*" %>
<%@page import="uts.isd.model.dao.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="PageComponents/CommonMeta.jsp"/>
        <title>User Management Page</title>
    </head>
    
    <%
        Customer cust = (Customer)session.getAttribute("customer");
        DBManager manager = (DBManager)session.getAttribute("manager");
        if (cust == null || manager == null || !manager.isCustomerStaff(cust.getEmail())) {
            response.sendRedirect("homePage.jsp");
            return;
        }
    %>
    <jsp:include page="PageComponents/JSPHeader.jsp"/>

    <body>
        <h1>User Management</h1>
        <form method="POST" action="DBServlet">
            <div style="display: flex; justify-content: center;">
                <button name="button" value="edit" type="submit">Edit</button>
                <button name="button" value="delete" type="submit">Delete</button> 
            </div>
            <table class="align-center form-table">
                <tr>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Email</th>
                    <th>Password</th>
                    <th>Date of Birth</th>
                    <th>Phone</th>
                    <th>Street</th>
                    <th>City</th>
                    <th>State</th>
                    <th>Postcode</th>

                    <th>Select</th>
                </tr>

                <% for (Customer customer : manager.fetchCustomers()) {%>
                    <tr>
                        <td><%= customer.getFirstName() %></td>  
                        <td><%= customer.getLastName() %></td>
                        <td><%= customer.getEmail() %></td>
                        <td><%= customer.getPassword() %></td>
                        <td><%= customer.getDob() %></td>
                        <td><%= customer.getPhone() %></td>
                        <td><%= customer.getStreet() %></td>
                        <td><%= customer.getCity() %></td>
                        <td><%= customer.getState() %></td>
                        <td><%= customer.getPostcode() %></td>

                        <td> <input type="radio" name="customerID" value="<%=customer.getCustomerID()%>"> </td>
                    </tr>
                <% } %>
            </table>
        </form>
    </body>
    <jsp:include page="PageComponents/JSPFooter.jsp"/>
</html>

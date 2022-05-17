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
        if (cust == null || manager == null || !cust.isIsStaff()) {
            response.sendRedirect(cust != null ? "homePage.jsp" : "login.jsp");
            return;
        }
        
        String sortA = request.getParameter("sortA");
        String sortB = request.getParameter("sortB");
        String sortC = request.getParameter("sortC");
        String sort = "WHERE firstName LIKE '%" + (sortA != null ? sortA : "_") + "%' AND lastName LIKE '%" + (sortB != null ? sortB : "_") + "%' AND phone LIKE '%" + (sortC != null ? sortC : "_") + "%'";
    %>
    <jsp:include page="PageComponents/JSPHeader.jsp"/>

    <body>
        <center><h1 class="spaced-letters blue">USER MANAGEMENT</h1></center>
        <form method="POST" action="DBServlet">
            <center>
                <label for="sortA">First Name: </label><input type="text" name="sortA" id="sortA" placeholder="Search by first name" value="<%= (sortA != null ? sortA : "") %>">
                &emsp;
                <label for="sortB">Last Name: </label><input type="text" name="sortB" id="sortB" placeholder="Search by last name" value="<%= (sortB != null ? sortB : "") %>">
                &emsp;
                <label for="sortC">Phone: </label><input type="text" name="sortC" id="sortC" placeholder="Search by phone" value="<%= (sortC != null ? sortC : "") %>">
                <p></p>
                <button name="button" formnovalidate value="search" type="submit">Search</button>
                &emsp;
                <button name="button" value="edit" type="submit">Edit</button>
                &emsp;
                <button name="button" value="delete" type="submit">Delete</button> 
            </center>
            <table class="align-center form-table" style="width: 100%;">
                <tr>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Email</th>
                    <th>Date of Birth</th>
                    <th>Phone</th>
                    <th>Street</th>
                    <th>City</th>
                    <th>State</th>
                    <th>Postcode</th>
                    <th>Account Active</th>

                    <th>Select</th>
                </tr>

                <% for (Customer customer : manager.fetchCustomers(sort)) {%>
                    <tr>
                        <td><label for="<%=customer.getEmail()%>"><%= customer.getFirstName() %></label></td>  
                        <td><label for="<%=customer.getEmail()%>"><%= customer.getLastName() %></label></td>
                        <td><label for="<%=customer.getEmail()%>"><%= customer.getEmail() %></label></td>
                        <td><label for="<%=customer.getEmail()%>"><%= customer.getDob() %></label></td>
                        <td><label for="<%=customer.getEmail()%>"><%= customer.getPhone() %></label></td>
                        <td><label for="<%=customer.getEmail()%>"><%= customer.getStreet() %></label></td>
                        <td><label for="<%=customer.getEmail()%>"><%= customer.getCity() %></label></td>
                        <td><label for="<%=customer.getEmail()%>"><%= customer.getState() %></label></td>
                        <td><label for="<%=customer.getEmail()%>"><%= customer.getPostcode() %></label></td>
                        <td><label for="<%=customer.getEmail()%>"><%= customer.isIsActive() %></label></td>

                        <td> <input type="radio" name="customerID" id="<%=customer.getEmail()%>" value="<%=customer.getCustomerID()%>" required> </td>
                    </tr>
                <% } %>
            </table>
        </form>
    </body>
    <jsp:include page="PageComponents/JSPFooter.jsp"/>
</html>

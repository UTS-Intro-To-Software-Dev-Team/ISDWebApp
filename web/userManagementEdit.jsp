<%@page import="uts.isd.model.Customer"%>
<%@page import="uts.isd.model.dao.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="PageComponents/CommonMeta.jsp"/>

        <title>Edit Page - Admin</title>
    </head>
    
    <%
        Customer cust = (Customer)session.getAttribute("customer");
        Customer customer = (Customer)session.getAttribute("customer2");
        DBManager manager = (DBManager)session.getAttribute("manager");
        if (cust == null || manager == null || customer == null || !cust.isIsStaff()) {
            response.sendRedirect(cust != null ? "homePage.jsp" : "login.jsp");
            return;
        }
    %>
    <jsp:include page="PageComponents/JSPHeader.jsp"/>
    
    <body>
        <%
            String emailErr = (String) session.getAttribute("emailErr");
            String passErr = (String) session.getAttribute("passErr");
            String existErr = (String) session.getAttribute("existErr");
            String firstNameErr = (String) session.getAttribute("firstNameErr");
            String lastNameErr = (String) session.getAttribute("lastNameErr");
            String dateErr = (String) session.getAttribute("dateErr");
            String phoneErr = (String) session.getAttribute("phoneErr");
            String postcodeErr = (String) session.getAttribute("postcodeErr");
            customer = (Customer)session.getAttribute("customer2");
        %>

        <h1 class="align-center spaced-letters blue">EDIT USER DETAILS - ADMIN</h1>
        <form class="align-center" action="DBServlet" method="POST">
            <table class="align-center form-table">
                <tr><td><label for="firstName"><b>First Name:</b></label></td></tr>
                <tr><td><input type="text" id="firstName" name="firstName" placeholder="Enter first name" value="<%= customer.getFirstName() %>" required pattern="^[A-Z][a-z]*$" title="This should only contain letters and start with a capitalised letter."></td></tr>
                <% if (firstNameErr != null) { %>
                    <tr><td><b><%= firstNameErr %></b></td></tr>
                <% } %>

                <tr><td><label for="lastName"><b>Last Name:</b></label></td></tr>
                <tr><td><input type="text" id="lastName" name="lastName" placeholder="Enter last name" value="<%= customer.getLastName() %>" required pattern="^[A-Z][a-z]*$" title="This should only contain letters and start with a capitalised letter."></td></tr>
                <% if (lastNameErr != null) { %>
                    <tr><td><b><%= lastNameErr %></b></td></tr>
                <% } %>

                <tr><td><label for="dob"><b>Date Of Birth:</b></label></td></tr>
                <tr><td><input type="date" id="dob" name="dob" value="<%= customer.getDob() %>" required></td></tr>
                <% if (dateErr != null) { %>
                    <tr><td><b><%= dateErr %></b></td></tr>
                <% } %>

                <tr><td><label for="phone"><b>Phone Number:</b></label></td></tr>
                <tr><td><input type="number" id="phone" name="phone" placeholder="Enter phone number" value="<%= customer.getPhone() %>" required pattern="^[0-9]{3,15}$" title="Not a valid phone number."></td></tr>
                <% if (phoneErr != null) { %>
                    <tr><td><b><%= phoneErr %></b></td></tr>
                <% } %>

                <tr><td><label for="street"><b>Street:</b></label></td></tr>
                <tr><td><input type="text" id="street" name="street" placeholder="Enter street" value="<%= customer.getStreet() %>" required></td></tr>

                <tr><td><label for="city"><b>City:</b></label></td></tr>
                <tr><td><input type="text" id="city" name="city" placeholder="Enter city" value="<%= customer.getCity() %>" required></td></tr>

                <tr><td><label for="state"><b>State:</b></label></td></tr>
                <tr><td>
                    <select id="state" name="state" required>
                        <option selected hidden>${customer.state}</option>
                        <option value="NSW">New South Wales</option>
                        <option value="QLD">Queensland</option>
                        <option value="SA">South Australia</option>
                        <option value="TAS">Tasmania</option>
                        <option value="VIC">Victoria</option>
                        <option value="WA">Western Australia</option>
                    </select>
                </td></tr>

                <tr><td><label for="postcode"><b>Postcode: </b><input type="text" id="postcode" name="postcode" placeholder="####" style="width: 5%; text-align: center;" value="<%= customer.getPostcode() %>" required pattern="^[0-9]{4}$" minlength="4" maxlength="4" title="Postcode must be a 4 digit number."></td></tr>
                <% if (postcodeErr != null) { %>
                    <tr><td><b><%= postcodeErr %></b></td></tr>
                <% } %>
                
                <tr>
                    <td>
                        <label for="isActive"><b>Account active?</b></label>
                        <% if (customer.isIsActive()) { %>
                            <input type="checkbox" id="isActive" name="isActive" style="width: 20px;" checked>
                        <% } else { %>
                            <input type="checkbox" id="isActive" name="isActive" style="width: 20px;">
                        <% } %>
                    </td>
                </tr>
                
                <tr><td><button type="submit" name="button" value="userManagementEdit"><b>Update Details</b></button></td></tr>
                <tr><td><button type="submit" name="button" value="userManagementEditCancel">Cancel</button></td></tr>
            </table>
        </form>
    </body>
    <jsp:include page="PageComponents/JSPFooter.jsp"/>
</html>

<%@page import="uts.isd.model.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="PageComponents/CommonMeta.jsp"/>
        <title>Register Page</title>
    </head>

    <jsp:include page="PageComponents/JSPHeader.jsp"/>

    <body>
        <%
            String emailErr = (String) session.getAttribute("emailErr");
            String passErr = (String) session.getAttribute("passErr");
            String existErr = (String) session.getAttribute("existErr");
            String firstNameErr = (String) session.getAttribute("firstNameErr");
            String lastNameErr = (String) session.getAttribute("lastNameErr");
            String dateErr = (String) session.getAttribute("dateErr");
            String postcodeErr = (String) session.getAttribute("postcodeErr");

            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String email = request.getParameter("email");
            String dob = request.getParameter("dob");
            String street = request.getParameter("street");
            String city = request.getParameter("city");
            String state = request.getParameter("state");
            String postcode = request.getParameter("postcode");
        %>
        <h1 class="align-center spaced-letters blue">CREATE ACCOUNT</h1>
        <form action="DBServlet" method="POST">
            <table class="align-center form-table">
                <% if (existErr != null) { %>
                    <tr><td><b><%= existErr %></b></td></tr>
                <% } %>

                <tr><td><label for="firstName"><b>First Name:</b></label></td></tr>
                <tr><td><input type="text" name="firstName" placeholder="Enter first name" value="<%= firstName != null ? firstName : "" %>" required pattern="[A-Z][a-z]*" title="First character must be a letter and capitalised."></td></tr>
                <% if (firstNameErr != null) { %>
                    <tr><td><b><%= firstNameErr %></b></td></tr>
                <% } %>

                <tr><td><label for="lastName"><b>Last Name:</b></label></td></tr>
                <tr><td><input type="text" name="lastName" placeholder="Enter last name" value="<%= lastName != null ? lastName : "" %>" required pattern="[A-Z][a-z]*" title="First character must be a letter and capitalised."></td></tr>
                <% if (lastNameErr != null) { %>
                    <tr><td><b><%= lastNameErr %></b></td></tr>
                <% } %>

                <tr><td><label for="email"><b>Email:</b></label></td></tr>
                <tr><td><input type="email" name="email" placeholder="Enter email" value="<%= email != null ? email : "" %>" required></td></tr>
                <% if (emailErr != null) { %>
                    <tr><td><b><%= emailErr %></b></td></tr>
                <% } %>

                <tr><td><label for="password"><b>Password:</b></label></td></tr>
                <tr><td><input type="password" name="password" placeholder="Enter password" required minlength="8"></td></tr>
                <% if (passErr != null) { %>
                    <tr><td><b><%= passErr %></b></td></tr>
                <% } %>

                <tr><td><label for="dob"><b>Date Of Birth:</b></label></td></tr>
                <tr><td><input type="date" name="dob" value="<%= dob != null ? dob : "" %>" required></td></tr>
                <% if (dateErr != null) { %>
                    <tr><td><b><%= dateErr %></b></td></tr>
                <% } %>

                <tr><td><label for="street"><b>Street:</b></label></td></tr>
                <tr><td><input type="text" name="street" value="<%= street != null ? street : "" %>" required></td></tr>

                <tr><td><label for="city"><b>City:</b></label></td></tr>
                <tr><td><input type="text" name="city" value="<%= city != null ? city : "" %>" required></td></tr>

                <tr><td><label for="state"><b>State:</b></label></td></tr>
                <tr><td>
                    <select name="state" required selected value="<%= state != null ? state : "" %>">
                        <option value="NSW">New South Wales</option>
                        <option value="QLD">Queensland</option>
                        <option value="SA">South Australia</option>
                        <option value="TAS">Tasmania</option>
                        <option value="VIC">Victoria</option>
                        <option value="WA">Western Australia</option>
                    </select>
                </td></tr>

                <tr><td><label for="postcode"><b>Postcode:</b></label></td></tr>
                <tr><td><input type="text" name="postcode" style="width: 5%; text-align: center;" value="<%= postcode != null ? postcode : "" %>" required minlength="4" maxlength="4"></td></tr>
                <% if (postcodeErr != null) { %>
                    <tr><td><b><%= postcodeErr %></b></td></tr>
                <% } %>

                <tr><td><button type="submit"><b>Register Now</b></button></td></tr>
            </table>
        </form>
    </body>
</html>

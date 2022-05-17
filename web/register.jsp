<%@page import="uts.isd.model.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="PageComponents/CommonMeta.jsp"/>
        <title>Register Page</title>
    </head>

    <%
    if ((Customer)session.getAttribute("customer") != null) {
        response.sendRedirect("homePage.jsp");
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

            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String email = request.getParameter("email");
            String dob = request.getParameter("dob");
            String phone = request.getParameter("phone");
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
                <tr><td><input type="text" name="firstName" id="firstName" placeholder="Enter first name" value="<%= firstName != null ? firstName : "" %>" required pattern="^[A-Z][a-z]*$" title="This should only contain letters and start with a capitalised letter."></td></tr>
                <% if (firstNameErr != null) { %>
                    <tr><td><b><%= firstNameErr %></b></td></tr>
                <% } %>

                <tr><td><label for="lastName"><b>Last Name:</b></label></td></tr>
                <tr><td><input type="text" name="lastName" id="lastName" placeholder="Enter last name" value="<%= lastName != null ? lastName : "" %>" required pattern="^[A-Z][a-z]*$" title="This should only contain letters and start with a capitalised letter."></td></tr>
                <% if (lastNameErr != null) { %>
                    <tr><td><b><%= lastNameErr %></b></td></tr>
                <% } %>

                <tr><td><label for="email"><b>Email:</b></label></td></tr>
                <tr><td><input type="email" name="email" id="email" placeholder="Enter email" value="<%= email != null ? email : "" %>" required></td></tr>
                <% if (emailErr != null) { %>
                    <tr><td><b><%= emailErr %></b></td></tr>
                <% } %>

                <tr><td><label for="password"><b>Password:</b></label></td></tr>
                <tr><td><input type="password" name="password" id="password" placeholder="Enter password" required minlength="8"></td></tr>
                <% if (passErr != null) { %>
                    <tr><td><b><%= passErr %></b></td></tr>
                <% } %>

                <tr><td><label for="dob"><b>Date Of Birth:</b></label></td></tr>
                <tr><td><input type="date" name="dob" id="dob" value="<%= dob != null ? dob : "" %>" required></td></tr>
                <% if (dateErr != null) { %>
                    <tr><td><b><%= dateErr %></b></td></tr>
                <% } %>
                
                <tr><td><label for="phone"><b>Phone Number:</b></label></td></tr>
                <tr><td><input type="number" name="phone" id="phone" placeholder="Enter phone number" value="<%= phone != null ? phone : "" %>" required pattern="^[0-9]{3,15}$" title="Not a valid phone number."></td></tr>
                <% if (phoneErr != null) { %>
                    <tr><td><b><%= phoneErr %></b></td></tr>
                <% } %>

                <tr><td><label for="street"><b>Street:</b></label></td></tr>
                <tr><td><input type="text" name="street" id="street" placeholder="Enter street" value="<%= street != null ? street : "" %>" required></td></tr>

                <tr><td><label for="city"><b>City:</b></label></td></tr>
                <tr><td><input type="text" name="city" id="city" placeholder="Enter city" value="<%= city != null ? city : "" %>" required></td></tr>

                <tr><td><label for="state"><b>State:</b></label></td></tr>
                <tr><td>
                    <select name="state" id="state" required>
                        <% if (state != null) { %>
                            <option selected hidden><%= state %></option>
                        <% } else { %>
                            <option hidden value="">Choose a state.</option>
                        <% }%>
                        
                        <option value="NSW">New South Wales</option>
                        <option value="QLD">Queensland</option>
                        <option value="SA">South Australia</option>
                        <option value="TAS">Tasmania</option>
                        <option value="VIC">Victoria</option>
                        <option value="WA">Western Australia</option>
                    </select>
                </td></tr>

                <tr><td><label for="postcode"><b>Postcode: </b></label><input type="text" id="postcode" name="postcode" placeholder="####" style="width: 5%; text-align: center;" value="<%= postcode != null ? postcode : "" %>" required pattern="^[0-9]{4}$" minlength="4" maxlength="4" title="Postcode must be a 4 digit number."></td></tr>
                <% if (postcodeErr != null) { %>
                    <tr><td><b><%= postcodeErr %></b></td></tr>
                <% } %>

                <tr><td><button type="submit" name="button" value="registerEdit"><b>Register Now</b></button></td></tr>
            </table>
        </form>
    </body>
    <jsp:include page="PageComponents/JSPFooter.jsp"/>
</html>

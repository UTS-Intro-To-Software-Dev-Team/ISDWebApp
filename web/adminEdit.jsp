<%@page import="uts.isd.model.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="PageComponents/CommonMeta.jsp"/>

        <title>Edit Page</title>
    </head>
    <jsp:include page="PageComponents/JSPHeader.jsp"/>
    <body>
        <%
            Customer customer = (Customer)session.getAttribute("customer2");
        %>

        <h1 class="align-center">Edit User Details as Admin</h1>
        <form class="align-center" action="DBServlet" method="POST">
            <table class="align-center form-table">
                <tr><td><label for="firstName"><b>First Name:</b></label></td></tr>
                <tr><td><input type="text" name="firstName" value="${customer.firstName}" required pattern="^[A-Z][a-z]*$" title="This should only contain letters and start with a capitalised letter."></td></tr>

                <tr><td><label for="lastName"><b>Last Name:</b></label></td></tr>
                <tr><td><input type="text" name="lastName" value="${customer.lastName}" required pattern="^[A-Z][a-z]*$" title="This should only contain letters and start with a capitalised letter."></td></tr>
                
                <tr><td><label for="dob"><b>Date Of Birth:</b></label></td></tr>
                <tr><td><input type="date" name="dob" value="${customer.dob}" required></td></tr>
                
                <tr><td><label for="phone"><b>Phone Number:</b></label></td></tr>
                <tr><td><input type="text" name="phone" value="${customer.phone}" required pattern="^[0-9]{3,15}$" title="Not a valid phone number."></td></tr>

                <tr><td><label for="street"><b>Street:</b></label></td></tr>
                <tr><td><input type="text" name="street" value="${customer.street}" required></td></tr>

                <tr><td><label for="city"><b>City:</b></label></td></tr>
                <tr><td><input type="text" name="city" value="${customer.city}" required></td></tr>

                <tr><td><label for="state"><b>State:</b></label></td></tr>
                <tr><td>
                    <select name="state" required selected value="">
                        <option value="NSW">New South Wales</option>
                        <option value="QLD">Queensland</option>
                        <option value="SA">South Australia</option>
                        <option value="TAS">Tasmania</option>
                        <option value="VIC">Victoria</option>
                        <option value="WA">Western Australia</option>
                    </select>
                </td></tr>

                <tr><td><label for="postcode"><b>Postcode:</b></label></td></tr>
                <tr><td><input type="text" name="postcode" style="width: 5%; text-align: center;" value="${customer.postcode}" required pattern="^[0-9]{4}$" minlength="4" maxlength="4" title="Postcode must be a 4 digit number."></td></tr>

                <tr><td><button name="button" value="adminEdit" type="submit">Update Details</button></td></tr>

            </table>
        </form>
    </body>
    <jsp:include page="PageComponents/JSPFooter.jsp"/>
</html>

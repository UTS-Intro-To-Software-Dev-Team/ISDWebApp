<%@page import="uts.isd.model.*" %>
<%@page import="uts.isd.model.dao.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="PageComponents/CommonMeta.jsp"/>
        <title>My Access Logs</title>
    </head>

    <%
        Customer customer = (Customer)session.getAttribute("customer");
        DBManager manager = (DBManager)session.getAttribute("manager");
        if (customer == null || manager == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        
        String sortA = request.getParameter("sortA");
        String sort = " AND Date LIKE '%" + (sortA != null ? sortA : "_") + "%'";
    %>
    <jsp:include page="PageComponents/JSPHeader.jsp"/>
    <body>
        <center>
            <h1 class="spaced-letters blue">ACCESS LOGS</h1>
            <form method="POST" action="DBServlet">
                <center>
                    <p>
                        <label for="sortA">Date: </label><input type="text" name="sortA" id="sortA" placeholder="Search by Date" value="<%= (sortA != null ? sortA : "") %>">
                        &emsp;
                        <button name="button" formnovalidate value="search" type="submit">Search</button>
                    </p>
                </center>
            </form>
            <table class="align-center">
                <tr>
                    <th>Date</th>
                    <th>Activity</th>
                </tr>

                <% for (AccessLogs logs : manager.fetchAccessLogs(customer.getCustomerID(), sort)) {%>
                    <tr>
                        <td><%= logs.getDate() %></td>
                        <td><%= logs.getActivity() %></td>
                    </tr>
                <% } %>
            </table>
        </center>
    </body>
</html>

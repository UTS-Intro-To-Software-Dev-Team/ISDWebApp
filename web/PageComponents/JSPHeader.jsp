<%@page import="uts.isd.model.*" %>
<head>
    <link rel="stylesheet" href="CSS/Header.css">
</head>

<div id="header">
    <jsp:include page="/ConnServlet" flush="true"/>
    <%
        String uri = request.getRequestURI();
        String pageName = uri.substring(uri.lastIndexOf("/") + 1);
        session.setAttribute("pageName", pageName);

        String[][] links = session.getAttribute("customer") == null || pageName.equals("logout.jsp") ?
        new String[][] { //If logged out
            {"register.jsp", "Register"},
            {"login.jsp", "Login"},

        } : new String[][] { //If logged in
            {"edit.jsp", "Edit Account Details"},
            {"orderHistory.jsp", "Order History"},
            {"shipmentPage.jsp", "Shipment Details"},
            {"logout.jsp", "Logout"},
        };
    %>
    <div>
        <% if (!pageName.equals("homePage.jsp")) { %>
            <a href="homePage.jsp"><label class="link"><b>IoTBay</b></label></a>
        <% } else { %>
            <label><b>IoTBay</b></label>
        <% } %>
    </div>

    <div id="header-links">
        <table>
            <tr>
                <% for (String[] link : links) { %>
                    <td>
                        <% if (!pageName.equals(link[0])) { %>
                            <a href="<%= link[0] %>"><%= link[1] %></a>
                        <% } else { %>
                            <p><b><%= link[1] %></b></p>
                        <% } %>
                    </td>
                <% } %>
            </tr>
        </table>
    </div>
</div>

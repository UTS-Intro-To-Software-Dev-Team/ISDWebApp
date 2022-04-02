<head>
    <link rel="stylesheet" href="CSS/Header.css">
</head>

<div id="header">
    <div>
        <a href="homePage.jsp"><label><b>IoTBay</b></label></a>
    </div>

    <%
        String uri = request.getRequestURI();
        String pageName = uri.substring(uri.lastIndexOf("/")+1);
    %>

    <div id="header-links">
        <table>
            <tr>
                <% if (session.getAttribute("customer") == null || pageName.equals("logout.jsp")) { %>
                    <td><a title="Login" href="login.jsp">Login</a></td>
                    <td><a title="Register" href="register.jsp">Register</a></td>
                <% } else { %>
                    <td><a title="Logout" href="logout.jsp">Logout</a></td>
                <% } %>
            </tr>
        </table>
    </div>
</div>

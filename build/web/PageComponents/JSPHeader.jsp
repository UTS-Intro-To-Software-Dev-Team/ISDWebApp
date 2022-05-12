<%@page import="uts.isd.model.*" %>
<head>
    <link rel="stylesheet" href="CSS/Header.css">
</head>

<div id="header">
    <jsp:include page="/ConnServlet" flush="true" />
    <%
        String uri = request.getRequestURI();
        String pageName = uri.substring(uri.lastIndexOf("/") + 1);
        session.setAttribute("pageName", pageName);
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
                <% if (session.getAttribute("customer") == null || pageName.equals("logout.jsp")) { %>
                    <td>
                        <% if (!pageName.equals("login.jsp")) { %>
                            <a title="Login" href="login.jsp">Login</a>
                        <% } else { %>
                            <p><b>Login</b></p>
                        <% } %>
                    </td>

                    <td>
                        <% if (!pageName.equals("register.jsp")) { %>
                            <a title="Register" href="register.jsp">Register</a>
                        <% } else { %>
                            <p><b>Register</b></p>
                        <% } %>
                    </td>                
                  
                <% } else { %>
                  <td>
                      <% if(!pageName.equals("shipmentPage.jsp")) { %>
                         <a title="Shipment" href="shipmentPage.jsp">Shipment Details</a> 
                       <% } else { %>
                        <p><b>Shipment</b></p>
                       <% } %>
                   </td>
                    <td><a title="Logout" href="logout.jsp">Logout</a></td>
                <% } %>
            </tr>
        </table>
    </div>
</div>

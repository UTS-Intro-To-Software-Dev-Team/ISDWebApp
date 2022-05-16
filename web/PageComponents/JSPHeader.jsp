<%@page import="uts.isd.model.*" %>
<%@page import="uts.isd.model.dao.*" %>
<%@page import="java.util.ArrayList" %>

<head>
    <link rel="stylesheet" href="CSS/Header.css">
</head>

<div id="header">
    <jsp:include page="/ConnServlet" flush="true"/>
    <%
        String uri = request.getRequestURI();
        String pageName = uri.substring(uri.lastIndexOf("/") + 1);
        session.setAttribute("pageName", pageName);

        DBManager manager = (DBManager)session.getAttribute("manager");
        Customer customer = (Customer)session.getAttribute("customer");
        
        ArrayList<String[]> links = new ArrayList<String[]>();
        if (customer == null || pageName.equals("logout.jsp")) { //Logged out
            links.add(new String[] {"register.jsp", "Register"});
            links.add(new String[] {"login.jsp", "Login"});
        } else { //Logged in
            if (customer.isIsStaff()) {
                links.add(new String[] {"userManagement.jsp", "Customer Management"});
                links.add(new String[] {"itemManagement.jsp", "Item Management"});
            }
            
            links.add(new String[] {"accountPage.jsp", "My Account"});
            links.add(new String[] {"logout.jsp", "Logout"});
        }
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

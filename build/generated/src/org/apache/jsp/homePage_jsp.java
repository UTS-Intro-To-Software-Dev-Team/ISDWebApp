package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import uts.isd.model.*;

public final class homePage_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "PageComponents/CommonMeta.jsp", out, false);
      out.write("\n");
      out.write("        <title>Home Page</title>\n");
      out.write("    </head>\n");
      out.write("\n");
      out.write("    ");

        boolean isRegistered = request.getParameter("register") != null;
        boolean isLoggedIn = request.getParameter("login") != null;

        CustomerDatabase cdb = (CustomerDatabase)session.getAttribute("Customer Database");

        Customer customer;
        if (isRegistered) {
            customer = new Customer(
                request.getParameter("firstname"),
                request.getParameter("lastname"),
                request.getParameter("email"),
                request.getParameter("username"),
                request.getParameter("password")
            );
            session.setAttribute("customer", customer);
            cdb.AddCustomer(customer);
        } else if (isLoggedIn) {
            customer = cdb.GetCustomer(request.getParameter("username"), request.getParameter("password"));
        } else {
            customer = (Customer)session.getAttribute("customer");
        }
    
      out.write("\n");
      out.write("\n");
      out.write("    <body>\n");
      out.write("        ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "PageComponents/JSPHeader.jsp", out, false);
      out.write("\n");
      out.write("        ");
 if (customer != null) { 
      out.write("\n");
      out.write("            <h1>Welcome ");
      out.print( customer.getUsername() );
      out.write("!</h1>\n");
      out.write("        ");
 } else { 
      out.write("\n");
      out.write("            <h1>Welcome!</h1>\n");
      out.write("        ");
 } 
      out.write("\n");
      out.write("\n");
      out.write("        ");
 if (isRegistered) { 
      out.write("\n");
      out.write("            <p>Hello newly registered customer!</p>\n");
      out.write("        ");
 } 
      out.write("\n");
      out.write("\n");
      out.write("        ");
 if (isLoggedIn) { 
      out.write("\n");
      out.write("            <p>Welcome back registered customer!</p>\n");
      out.write("        ");
 } 
      out.write("\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}

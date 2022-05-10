package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import uts.isd.model.Customer;

public final class edit_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("    <head>\r\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
      out.write("        ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "PageComponents/CommonMeta.jsp", out, false);
      out.write("\r\n");
      out.write("        <title>Edit Page</title>\r\n");
      out.write("    </head>\r\n");
      out.write("    <body>\r\n");
      out.write("        ");

            Customer customer = (Customer)session.getAttribute("Customer");
            String updated = (String)session.getAttribute("Updated");
        
      out.write("\r\n");
      out.write("        \r\n");
      out.write("        <h1 class=\"align-center\">Edit User Details <span>");
      out.print( (updated != null ? "Update was successful" : ""));
      out.write("</span></h1>\r\n");
      out.write("        <form class=\"align-center\" action=\"DBServlet\" method=\"POST\">\r\n");
      out.write("\r\n");
      out.write("            <label for=\"email\">Email:</label>\r\n");
      out.write("            <br>\r\n");
      out.write("            <input type=\"text\" name=\"email\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${customer.email}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\">\r\n");
      out.write("            <br>\r\n");
      out.write("            <br>\r\n");
      out.write("            \r\n");
      out.write("            <label for=\"password\">Password:</label>\r\n");
      out.write("            <br>\r\n");
      out.write("            <input type=\"password\" name=\"password\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${customer.password}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\">\r\n");
      out.write("            <br>\r\n");
      out.write("            <br>\r\n");
      out.write("            \r\n");
      out.write("            <label for=\"firstName\">First Name:</label>\r\n");
      out.write("            <br>\r\n");
      out.write("            <input type=\"text\" name=\"First Name\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${customer.firstName}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\">\r\n");
      out.write("            <br>\r\n");
      out.write("            <br>\r\n");
      out.write("            \r\n");
      out.write("            <label  for=\"lastName\">Last Name:</label>\r\n");
      out.write("            <br>\r\n");
      out.write("            <input type=\"text\" name=\"Last Name\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${customer.lastName}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\">\r\n");
      out.write("            <br>\r\n");
      out.write("            <br>\r\n");
      out.write("            \r\n");
      out.write("            <label for=\"dob\">Date of Birth:</label>\r\n");
      out.write("            <br>\r\n");
      out.write("            <input type=\"date\" name=\"dob\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${customer.dob}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\">\r\n");
      out.write("            <br>\r\n");
      out.write("            <br>\r\n");
      out.write("            \r\n");
      out.write("            <label for=\"street\">Street:</label>\r\n");
      out.write("            <br>\r\n");
      out.write("            <input type=\"text\" name=\"street\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${customer.street}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\">\r\n");
      out.write("            <br>\r\n");
      out.write("            <br>\r\n");
      out.write("            \r\n");
      out.write("            <label for=\"city\">City:</label>\r\n");
      out.write("            <br>\r\n");
      out.write("            <input type=\"text\" name=\"city\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${customer.city}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\">\r\n");
      out.write("            <br>\r\n");
      out.write("            <br>\r\n");
      out.write("            \r\n");
      out.write("            <label for=\"state\">State:</label>\r\n");
      out.write("            <br>\r\n");
      out.write("            <input type=\"text\" name=\"state\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${customer.state}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\">\r\n");
      out.write("            <br>\r\n");
      out.write("            <br>\r\n");
      out.write("            \r\n");
      out.write("            <label for=\"postcode\">Postcode:</label>\r\n");
      out.write("            <br>\r\n");
      out.write("            <input type=\"text\" name=\"postcode\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${customer.postcode}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\">\r\n");
      out.write("            <br>\r\n");
      out.write("            <br>\r\n");
      out.write("            \r\n");
      out.write("            <input type=\"submit\" value=\"Update\">\r\n");
      out.write("            \r\n");
      out.write("            \r\n");
      out.write("        </form>\r\n");
      out.write("            ");

                String email = request.getParameter("email");
                String password = request.getParameter("password");
                String firstName = request.getParameter("firstName");
                String lastName = request.getParameter("lastName");
                String dob = request.getParameter("dob");
                String street = request.getParameter("street");
                String city = request.getParameter("city");
                String state = request.getParameter("state");
                String postcode = request.getParameter("postcode");
                
                
                customer = new Customer(email, password, firstName, lastName,  dob, street, city, state, postcode);
                session.setAttribute("customer", customer);
            
      out.write("\r\n");
      out.write("    </body>\r\n");
      out.write("</html>\r\n");
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

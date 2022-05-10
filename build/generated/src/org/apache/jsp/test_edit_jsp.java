package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import uts.isd.model.Customer;

public final class test_edit_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Account Page</title>\n");
      out.write("        ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "PageComponents/CommonMeta.jsp", out, false);
      out.write("\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        ");

            Customer customer = (Customer)session.getAttribute("Customer");
            String updated = (String)session.getAttribute("updated");
        
      out.write("\n");
      out.write("        <h1 class=\"align-center\">Edit Student Information <span> ");
      out.print( (updated != null) ? "Update was successful" : "");
      out.write(" </span></h1>\n");
      out.write("        <form action=\"DBServlet\" method=\"POST\">\n");
      out.write("            <table class=\"align-center form-table\">\n");
      out.write("                \n");
      out.write("                <tr><td><label for=\"firstName\"><b>First Name:</b></label></td></tr>\n");
      out.write("                <tr><td><input type=\"text\" name=\"firstName\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${customer.firstName}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\"></td></tr>\n");
      out.write("                \n");
      out.write("\n");
      out.write("                <tr><td><label for=\"lastName\"><b>Last Name:</b></label></td></tr>\n");
      out.write("                <tr><td><input type=\"text\" name=\"lastName\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${customer.lastName}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\"></td></tr>\n");
      out.write("                \n");
      out.write("\n");
      out.write("                <tr><td><label for=\"email\"><b>Email:</b></label></td></tr>\n");
      out.write("                <tr><td><input type=\"email\" name=\"email\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${customer.email}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\"></td></tr>\n");
      out.write("                \n");
      out.write("\n");
      out.write("                <tr><td><label for=\"password\"><b>Password:</b></label></td></tr>\n");
      out.write("                <tr><td><input type=\"password\" name=\"password\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${customer.password}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\"></td></tr>\n");
      out.write("                \n");
      out.write("\n");
      out.write("                <tr><td><label for=\"dob\"><b>Date Of Birth:</b></label></td></tr>\n");
      out.write("                <tr><td><input type=\"date\" name=\"dob\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${customer.dob}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\"></td></tr>\n");
      out.write("                \n");
      out.write("\n");
      out.write("                <tr><td><label for=\"street\"><b>Street:</b></label></td></tr>\n");
      out.write("                <tr><td><input type=\"text\" name=\"street\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${customer.street}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\"></td></tr>\n");
      out.write("\n");
      out.write("                <tr><td><label for=\"city\"><b>City:</b></label></td></tr>\n");
      out.write("                <tr><td><input type=\"text\" name=\"city\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${customer.city}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\"></td></tr>\n");
      out.write("\n");
      out.write("                <tr><td><label for=\"state\"><b>State:</b></label></td></tr>\n");
      out.write("                <tr><td>\n");
      out.write("                    <select name=\"state\" required selected value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${customer.state}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\">\n");
      out.write("                        <option value=\"NSW\">New South Wales</option>\n");
      out.write("                        <option value=\"QLD\">Queensland</option>\n");
      out.write("                        <option value=\"SA\">South Australia</option>\n");
      out.write("                        <option value=\"TAS\">Tasmania</option>\n");
      out.write("                        <option value=\"VIC\">Victoria</option>\n");
      out.write("                        <option value=\"WA\">Western Australia</option>\n");
      out.write("                    </select>\n");
      out.write("                </td></tr>\n");
      out.write("\n");
      out.write("                <tr><td><label for=\"postcode\"><b>Postcode:</b></label></td></tr>\n");
      out.write("                <tr><td><input type=\"text\" name=\"postcode\" style=\"width: 20%; text-align: center;\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${customer.postcode}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\"></td></tr>\n");
      out.write("                \n");
      out.write("                <tr><td><a href=\"homePage.jsp\" class=\"button\">Main</a></tr></td>\n");
      out.write("                <tr><td><input class=\"button\" type=\"submit\" value=\"Update\"></tr></td>\n");
      out.write("                <input type=\"hidden\" name=\"updated\" value=\"updated\">\n");
      out.write("            </table>\n");
      out.write("        </form>\n");
      out.write("        ");

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
            String password = request.getParameter("password");
            String dob = request.getParameter("dob");
            String street = request.getParameter("street");
            String city = request.getParameter("city");
            String state = request.getParameter("state");
            String postcode = request.getParameter("postcode");
            
            customer = new Customer(email, password, firstName, lastName,  dob, street, city, state, postcode);
            session.setAttribute("customer", customer);
        
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

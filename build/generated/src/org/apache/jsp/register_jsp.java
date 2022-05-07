package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import uts.isd.model.*;

public final class register_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("        <title>Register Page</title>\n");
      out.write("    </head>\n");
      out.write("\n");
      out.write("    ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "PageComponents/JSPHeader.jsp", out, false);
      out.write("\n");
      out.write("\n");
      out.write("    <body>\n");
      out.write("        ");

            String emailErr = (String) session.getAttribute("emailErr");
            String passErr = (String) session.getAttribute("passErr");
            String existErr = (String) session.getAttribute("existErr");
            String firstNameErr = (String) session.getAttribute("firstNameErr");
            String lastNameErr = (String) session.getAttribute("lastNameErr");
            String dateErr = (String) session.getAttribute("dateErr");
            String postcodeErr = (String) session.getAttribute("postcodeErr");
        
      out.write("\n");
      out.write("        <h1 class=\"align-center spaced-letters blue\">CREATE ACCOUNT</h1>\n");
      out.write("        <form action=\"RegisterServlet\" method=\"POST\">\n");
      out.write("            <table class=\"align-center form-table\">\n");
      out.write("                ");
 if (existErr != null) { 
      out.write("\n");
      out.write("                    <tr><td><b>");
      out.print( existErr );
      out.write("</b></td></tr>\n");
      out.write("                ");
 } 
      out.write("\n");
      out.write("\n");
      out.write("                <tr><td><label for=\"firstName\"><b>First Name:</b></label></td></tr>\n");
      out.write("                <tr><td><input type=\"text\" name=\"firstName\" placeholder=\"Enter first name\" required pattern=\"[A-Z][a-z]*\" title=\"First character must be a letter and capitalised.\"></td></tr>\n");
      out.write("                ");
 if (firstNameErr != null) { 
      out.write("\n");
      out.write("                    <tr><td><b>");
      out.print( firstNameErr );
      out.write("</b></td></tr>\n");
      out.write("                ");
 } 
      out.write("\n");
      out.write("\n");
      out.write("                <tr><td><label for=\"lastName\"><b>Last Name:</b></label></td></tr>\n");
      out.write("                <tr><td><input type=\"text\" name=\"lastName\" placeholder=\"Enter last name\" required pattern=\"[A-Z][a-z]*\" title=\"First character must be a letter and capitalised.\"></td></tr>\n");
      out.write("                ");
 if (lastNameErr != null) { 
      out.write("\n");
      out.write("                    <tr><td><b>");
      out.print( lastNameErr );
      out.write("</b></td></tr>\n");
      out.write("                ");
 } 
      out.write("\n");
      out.write("\n");
      out.write("                <tr><td><label for=\"email\"><b>Email:</b></label></td></tr>\n");
      out.write("                <tr><td><input type=\"email\" name=\"email\" placeholder=\"Enter email\" required></td></tr>\n");
      out.write("                ");
 if (emailErr != null) { 
      out.write("\n");
      out.write("                    <tr><td><b>");
      out.print( emailErr );
      out.write("</b></td></tr>\n");
      out.write("                ");
 } 
      out.write("\n");
      out.write("\n");
      out.write("                <tr><td><label for=\"password\"><b>Password:</b></label></td></tr>\n");
      out.write("                <tr><td><input type=\"password\" name=\"password\" placeholder=\"Enter password\" required minlength=\"8\"></td></tr>\n");
      out.write("                ");
 if (passErr != null) { 
      out.write("\n");
      out.write("                    <tr><td><b>");
      out.print( passErr );
      out.write("</b></td></tr>\n");
      out.write("                ");
 } 
      out.write("\n");
      out.write("\n");
      out.write("                <tr><td><label for=\"dob\"><b>Date Of Birth:</b></label></td></tr>\n");
      out.write("                <tr><td><input type=\"date\" name=\"dob\" required></td></tr>\n");
      out.write("                ");
 if (dateErr != null) { 
      out.write("\n");
      out.write("                    <tr><td><b>");
      out.print( dateErr );
      out.write("</b></td></tr>\n");
      out.write("                ");
 } 
      out.write("\n");
      out.write("\n");
      out.write("                <tr><td><label for=\"street\"><b>Street:</b></label></td></tr>\n");
      out.write("                <tr><td><input type=\"text\" name=\"street\" required></td></tr>\n");
      out.write("\n");
      out.write("                <tr><td><label for=\"city\"><b>City:</b></label></td></tr>\n");
      out.write("                <tr><td><input type=\"text\" name=\"city\" required></td></tr>\n");
      out.write("\n");
      out.write("                <tr><td><label for=\"state\"><b>State:</b></label></td></tr>\n");
      out.write("                <tr><td>\n");
      out.write("                    <select name=\"state\" required selected>\n");
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
      out.write("                <tr><td><input type=\"text\" name=\"postcode\" style=\"width: 5%; text-align: center;\" required minlength=\"4\" maxlength=\"4\"></td></tr>\n");
      out.write("                ");
 if (postcodeErr != null) { 
      out.write("\n");
      out.write("                    <tr><td><b>");
      out.print( postcodeErr );
      out.write("</b></td></tr>\n");
      out.write("                ");
 } 
      out.write("\n");
      out.write("\n");
      out.write("                <tr><td><button type=\"submit\" value=\"Register\"><b>Register</b></button></td></tr>\n");
      out.write("            </table>\n");
      out.write("        </form>\n");
      out.write("    </body>\n");
      out.write("\n");
      out.write("    ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "PageComponents/JSPFooter.jsp", out, false);
      out.write("\n");
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

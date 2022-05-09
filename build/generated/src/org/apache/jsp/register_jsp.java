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

      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("    <head>\r\n");
      out.write("        ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "PageComponents/CommonMeta.jsp", out, false);
      out.write("\r\n");
      out.write("        <title>Register Page</title>\r\n");
      out.write("    </head>\r\n");
      out.write("\r\n");
      out.write("    ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "PageComponents/JSPHeader.jsp", out, false);
      out.write("\r\n");
      out.write("\r\n");
      out.write("    <body>\r\n");
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
        
      out.write("\r\n");
      out.write("        <h1 class=\"align-center spaced-letters blue\">CREATE ACCOUNT</h1>\r\n");
      out.write("        <form action=\"DBServlet\" method=\"POST\">\r\n");
      out.write("            <table class=\"align-center form-table\">\r\n");
      out.write("                ");
 if (existErr != null) { 
      out.write("\r\n");
      out.write("                    <tr><td><b>");
      out.print( existErr );
      out.write("</b></td></tr>\r\n");
      out.write("                ");
 } 
      out.write("\r\n");
      out.write("\r\n");
      out.write("                <tr><td><label for=\"firstName\"><b>First Name:</b></label></td></tr>\r\n");
      out.write("                <tr><td><input type=\"text\" name=\"firstName\" placeholder=\"Enter first name\" value=\"");
      out.print( firstName != null ? firstName : "" );
      out.write("\" required pattern=\"[A-Z][a-z]*\" title=\"First character must be a letter and capitalised.\"></td></tr>\r\n");
      out.write("                ");
 if (firstNameErr != null) { 
      out.write("\r\n");
      out.write("                    <tr><td><b>");
      out.print( firstNameErr );
      out.write("</b></td></tr>\r\n");
      out.write("                ");
 } 
      out.write("\r\n");
      out.write("\r\n");
      out.write("                <tr><td><label for=\"lastName\"><b>Last Name:</b></label></td></tr>\r\n");
      out.write("                <tr><td><input type=\"text\" name=\"lastName\" placeholder=\"Enter last name\" value=\"");
      out.print( lastName != null ? lastName : "" );
      out.write("\" required pattern=\"[A-Z][a-z]*\" title=\"First character must be a letter and capitalised.\"></td></tr>\r\n");
      out.write("                ");
 if (lastNameErr != null) { 
      out.write("\r\n");
      out.write("                    <tr><td><b>");
      out.print( lastNameErr );
      out.write("</b></td></tr>\r\n");
      out.write("                ");
 } 
      out.write("\r\n");
      out.write("\r\n");
      out.write("                <tr><td><label for=\"email\"><b>Email:</b></label></td></tr>\r\n");
      out.write("                <tr><td><input type=\"email\" name=\"email\" placeholder=\"Enter email\" value=\"");
      out.print( email != null ? email : "" );
      out.write("\" required></td></tr>\r\n");
      out.write("                ");
 if (emailErr != null) { 
      out.write("\r\n");
      out.write("                    <tr><td><b>");
      out.print( emailErr );
      out.write("</b></td></tr>\r\n");
      out.write("                ");
 } 
      out.write("\r\n");
      out.write("\r\n");
      out.write("                <tr><td><label for=\"password\"><b>Password:</b></label></td></tr>\r\n");
      out.write("                <tr><td><input type=\"password\" name=\"password\" placeholder=\"Enter password\" value=\"");
      out.print( password != null ? password : "" );
      out.write("\" required minlength=\"8\"></td></tr>\r\n");
      out.write("                ");
 if (passErr != null) { 
      out.write("\r\n");
      out.write("                    <tr><td><b>");
      out.print( passErr );
      out.write("</b></td></tr>\r\n");
      out.write("                ");
 } 
      out.write("\r\n");
      out.write("\r\n");
      out.write("                <tr><td><label for=\"dob\"><b>Date Of Birth:</b></label></td></tr>\r\n");
      out.write("                <tr><td><input type=\"date\" name=\"dob\" value=\"");
      out.print( dob != null ? dob : "" );
      out.write("\" required></td></tr>\r\n");
      out.write("                ");
 if (dateErr != null) { 
      out.write("\r\n");
      out.write("                    <tr><td><b>");
      out.print( dateErr );
      out.write("</b></td></tr>\r\n");
      out.write("                ");
 } 
      out.write("\r\n");
      out.write("\r\n");
      out.write("                <tr><td><label for=\"street\"><b>Street:</b></label></td></tr>\r\n");
      out.write("                <tr><td><input type=\"text\" name=\"street\" value=\"");
      out.print( street != null ? street : "" );
      out.write("\" required></td></tr>\r\n");
      out.write("\r\n");
      out.write("                <tr><td><label for=\"city\"><b>City:</b></label></td></tr>\r\n");
      out.write("                <tr><td><input type=\"text\" name=\"city\" value=\"");
      out.print( city != null ? city : "" );
      out.write("\" required></td></tr>\r\n");
      out.write("\r\n");
      out.write("                <tr><td><label for=\"state\"><b>State:</b></label></td></tr>\r\n");
      out.write("                <tr><td>\r\n");
      out.write("                    <select name=\"state\" required selected value=\"");
      out.print( state != null ? state : "" );
      out.write("\">\r\n");
      out.write("                        <option value=\"NSW\">New South Wales</option>\r\n");
      out.write("                        <option value=\"QLD\">Queensland</option>\r\n");
      out.write("                        <option value=\"SA\">South Australia</option>\r\n");
      out.write("                        <option value=\"TAS\">Tasmania</option>\r\n");
      out.write("                        <option value=\"VIC\">Victoria</option>\r\n");
      out.write("                        <option value=\"WA\">Western Australia</option>\r\n");
      out.write("                    </select>\r\n");
      out.write("                </td></tr>\r\n");
      out.write("\r\n");
      out.write("                <tr><td><label for=\"postcode\"><b>Postcode:</b></label></td></tr>\r\n");
      out.write("                <tr><td><input type=\"text\" name=\"postcode\" style=\"width: 5%; text-align: center;\" value=\"");
      out.print( postcode != null ? postcode : "" );
      out.write("\" required minlength=\"4\" maxlength=\"4\"></td></tr>\r\n");
      out.write("                ");
 if (postcodeErr != null) { 
      out.write("\r\n");
      out.write("                    <tr><td><b>");
      out.print( postcodeErr );
      out.write("</b></td></tr>\r\n");
      out.write("                ");
 } 
      out.write("\r\n");
      out.write("\r\n");
      out.write("                <tr><td><button type=\"submit\"><b>Register Now</b></button></td></tr>\r\n");
      out.write("            </table>\r\n");
      out.write("        </form>\r\n");
      out.write("    </body>\r\n");
      out.write("\r\n");
      out.write("    ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "PageComponents/JSPFooter.jsp", out, false);
      out.write("\r\n");
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

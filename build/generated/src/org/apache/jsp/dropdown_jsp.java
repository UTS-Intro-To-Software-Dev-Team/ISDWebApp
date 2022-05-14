package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class dropdown_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("package net.codejava.swing;\n");
      out.write("\n");
      out.write("import java.awt.FlowLayout;\n");
      out.write("import java.awt.HeadlessException;\n");
      out.write("import java.awt.event.ActionEvent;\n");
      out.write("import java.awt.event.ActionListener;\n");
      out.write("\n");
      out.write("import javax.swing.ImageIcon;\n");
      out.write("import javax.swing.JButton;\n");
      out.write("import javax.swing.JFrame;\n");
      out.write("import javax.swing.JMenu;\n");
      out.write("import javax.swing.JMenuBar;\n");
      out.write("import javax.swing.JMenuItem;\n");
      out.write("import javax.swing.JPopupMenu;\n");
      out.write("import javax.swing.JSeparator;\n");
      out.write("import javax.swing.JToolBar;\n");
      out.write("import javax.swing.SwingUtilities;\n");
      out.write("import javax.swing.UIManager;\n");
      out.write("\n");
      out.write("import org.openide.awt.DropDownButtonFactory;\n");
      out.write("\n");
      out.write("/**\n");
      out.write(" * This Swing program demonstrates how to create a drop down button.\n");
      out.write(" * @author www.codejava.net\n");
      out.write(" *\n");
      out.write(" */\n");
      out.write("public class DropDownButtonDemo extends JFrame implements ActionListener {\n");
      out.write("\n");
      out.write("\tpublic DropDownButtonDemo() throws HeadlessException {\n");
      out.write("\t\tsuper(\"CodeJava - Drop Down Button Demo\");\n");
      out.write("\t\t\n");
      out.write("\t\tcreateMenuBar();\n");
      out.write("\t\t\n");
      out.write("\t\tcreateToolbar();\n");
      out.write("\t\t\n");
      out.write("\t\tsetSize(360, 200);\n");
      out.write("\t\tsetDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);\n");
      out.write("\t}\n");
      out.write("\n");
      out.write("\tprivate void createToolbar() {\n");
      out.write("\t\tJToolBar toolbar = new JToolBar();\n");
      out.write("\t\tJButton buttonOpen = new JButton(new ImageIcon(\n");
      out.write("\t\t\t\tgetClass().getResource(\"/net/codejava/swing/images/open.png\")));\n");
      out.write("\t\ttoolbar.add(buttonOpen);\n");
      out.write("\t\ttoolbar.add(new JSeparator());\n");
      out.write("\t\t\n");
      out.write("\t\tJButton buttonSave = new JButton(new ImageIcon(\n");
      out.write("\t\t\t\tgetClass().getResource(\"/net/codejava/swing/images/save.png\")));\n");
      out.write("\t\ttoolbar.add(buttonSave);\n");
      out.write("\t\ttoolbar.add(new JSeparator());\n");
      out.write("\t\t\n");
      out.write("\t\tJButton dropDownButton = createDropDownButton();\n");
      out.write("\t\ttoolbar.add(dropDownButton);\n");
      out.write("\t\ttoolbar.add(new JSeparator());\n");
      out.write("\t\t\n");
      out.write("\t\tJButton buttonRun = new JButton(new ImageIcon(\n");
      out.write("\t\t\t\tgetClass().getResource(\"/net/codejava/swing/images/run.png\")));\n");
      out.write("\t\ttoolbar.add(buttonRun);\n");
      out.write("\t\t\n");
      out.write("\t\tsetLayout(new FlowLayout(FlowLayout.LEFT));\n");
      out.write("\t\tadd(toolbar);\n");
      out.write("\t}\n");
      out.write("\n");
      out.write("\tprivate JButton createDropDownButton() {\n");
      out.write("\t\tJPopupMenu popupMenu = createDropDownMenu();\n");
      out.write("\t\t\n");
      out.write("\t\tImageIcon icon = new ImageIcon(getClass().getResource(\"/net/codejava/swing/images/new.gif\"));\n");
      out.write("\t\t\n");
      out.write("\t\tJButton dropDownButton = DropDownButtonFactory.createDropDownButton(icon, popupMenu);\n");
      out.write("\t\tdropDownButton.addActionListener(this);\n");
      out.write("\t\t\n");
      out.write("\t\treturn dropDownButton;\n");
      out.write("\t}\n");
      out.write("\n");
      out.write("\tprivate JPopupMenu createDropDownMenu() {\n");
      out.write("\t\tJPopupMenu popupMenu = new JPopupMenu();\n");
      out.write("\t\t\n");
      out.write("\t\tJMenuItem menuItemCreateSpringProject = new JMenuItem(\"Spring Project\");\n");
      out.write("\t\tpopupMenu.add(menuItemCreateSpringProject);\n");
      out.write("\t\t\n");
      out.write("\t\tJMenuItem menuItemCreateHibernateProject = new JMenuItem(\"Hibernate Project\");\n");
      out.write("\t\tpopupMenu.add(menuItemCreateHibernateProject);\n");
      out.write("\t\t\n");
      out.write("\t\tJMenuItem menuItemCreateStrutsProject = new JMenuItem(\"Struts Project\");\n");
      out.write("\t\tpopupMenu.add(menuItemCreateStrutsProject);\n");
      out.write("\t\t\n");
      out.write("\t\tmenuItemCreateSpringProject.addActionListener(this);\n");
      out.write("\t\tmenuItemCreateHibernateProject.addActionListener(this);\n");
      out.write("\t\tmenuItemCreateStrutsProject.addActionListener(this);\n");
      out.write("\t\t\n");
      out.write("\t\treturn popupMenu;\n");
      out.write("\t}\n");
      out.write("\n");
      out.write("\tprivate void createMenuBar() {\n");
      out.write("\t\tJMenuBar menuBar = new JMenuBar();\n");
      out.write("\t\tsetJMenuBar(menuBar);\n");
      out.write("\t\t\n");
      out.write("\t\tJMenu menuFile = new JMenu(\"File\");\n");
      out.write("\t\tJMenuItem menuItemExit = new JMenuItem(\"Exit\");\n");
      out.write("\t\t\n");
      out.write("\t\tmenuFile.add(menuItemExit);\n");
      out.write("\t\t\n");
      out.write("\t\tmenuBar.add(menuFile);\n");
      out.write("\t}\n");
      out.write("\n");
      out.write("\tpublic static void main(String[] args) throws Exception {\n");
      out.write("\t\tUIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());\n");
      out.write("\t\tSwingUtilities.invokeLater(new Runnable() {\n");
      out.write("\t\t\t\n");
      out.write("\t\t\t@Override\n");
      out.write("\t\t\tpublic void run() {\n");
      out.write("\t\t\t\tnew DropDownButtonDemo().setVisible(true);\n");
      out.write("\t\t\t}\n");
      out.write("\t\t});\n");
      out.write("\t}\n");
      out.write("\n");
      out.write("\t@Override\n");
      out.write("\tpublic void actionPerformed(ActionEvent evt) {\n");
      out.write("\t\tObject source = evt.getSource();\n");
      out.write("\t\tif (source instanceof JMenuItem) {\n");
      out.write("\t\t\tJMenuItem clickedMenuItem = (JMenuItem) source;\n");
      out.write("\t\t\tString menuText = clickedMenuItem.getText();\n");
      out.write("\t\t\tSystem.out.println(\"Create a \" + menuText);\n");
      out.write("\t\t} else if (source instanceof JButton) {\n");
      out.write("\t\t\tSystem.out.println(\"Create a default project\");\n");
      out.write("\t\t}\n");
      out.write("\t}\n");
      out.write("\n");
      out.write("}\n");
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

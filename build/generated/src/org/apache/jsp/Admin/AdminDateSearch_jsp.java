package org.apache.jsp.Admin;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class AdminDateSearch_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(2);
    _jspx_dependants.add("/Admin/header.jsp");
    _jspx_dependants.add("/Admin/footer.jsp");
  }

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

      out.write('\n');
      out.write('\n');
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\" />\n");
      out.write("<title>:::SNOS PROJECT:::</title>\n");
      out.write("<meta name=\"description\" content=\"Security website\">\n");
      out.write("<meta name=\"keywords\" content=\"Security, Networks,monitoring\">\n");
      out.write("\n");
      out.write("<link rel=\"shortcut icon\"  href=\"resources/images/favicon.ico\" type=\"image/x-icon\" />\n");
      out.write("<link href=\"resources/css/style.css\" rel=\"stylesheet\" type=\"text/css\">\n");
      out.write("<link href=\"resources/css/loginCSS.css\" rel=\"stylesheet\" type=\"text/css\">\n");
      out.write("<link href=\"resources/Style/adminCss.css\" rel=\"stylesheet\" type=\"text/css\">\n");
      out.write("<style type=\"text/css\">\n");
      out.write("<!--\n");
      out.write(".style1 {color: #003366}\n");
      out.write("-->\n");
      out.write("</style>\n");
      out.write("<script src=\"resources/Script/admin_script.js\" type=\"text/javascript\"></script> \n");
      out.write("</head>\n");
      out.write("\n");
      out.write("<body>\n");
      out.write("<table width=\"75%\" height=\"100%\"  cellspacing=\"0\" cellpadding=\"0\" align=\"center\"  bgcolor=\"white\">\n");
      out.write("  <tr>\n");
      out.write("    <td><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n");
      out.write("      <tr>\n");
      out.write("        <td width=\"1\">\n");
      out.write("        <div style=\"position:absolute; width:400px; top:20px; background:url('resources/images/cn-bg.gif');\">\n");
      out.write("        <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n");
      out.write("  <tr>\n");
      out.write("    <td width=\"1\"><img  src=\"resources/images/logo.jpg\" alt=\"\" width=\"61\" height=\"61\" class=\"logo\"></td>\n");
      out.write("    <td class=\"company_name\">SECURITY NETWORK OPERATING SYSTEM</td>\n");
      out.write("  </tr>\n");
      out.write("</table>\n");
      out.write("        </div>\n");
      out.write("        <div  id=\"slogan\">\n");
      out.write("        SNOS<br/>\n");
      out.write("        <img src=\"resources/images/w17.gif\" width=\"17\" height=\"1\">...Securing Your World</div>\n");
      out.write("        <img  src=\"resources/images/bansnos.png\" alt=\"\" width=\"780\" height=\"196\"></td>\n");
      out.write("        <td class=\"hbg\">&nbsp;</td>\n");
      out.write("      </tr>\n");
      out.write("    </table></td>\n");
      out.write("  </tr>\n");
      out.write("  <tr>\n");
      out.write("    <td height=\"100%\" valign=\"top\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n");
      out.write("      <tr>\n");
      out.write("        <td width=\"188\" valign=\"top\">\n");
      out.write("        <div style=\"position:relative; top:0px; width:188px;\">\n");
      out.write("        <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n");
      out.write("  <tr>\n");
      out.write("    <td class=\"mbg\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n");
      out.write("      <tr>\n");
      out.write("        <td><p><img src=\"resources/images/spacer.gif\" alt=\"\" width=\"1\" height=\"14\"></p>\n");
      out.write("          <p>&nbsp;</p></td>\n");
      out.write("      </tr>\n");
      out.write("      <tr>\n");
      out.write("        <td class=\"menu\"><a href=\"index.jsp\">Home</a></td>\n");
      out.write("      </tr>\n");
      out.write("      <tr>\n");
      out.write("        <td height=\"1\"><img src=\"resources/images/m-sep0.gif\" alt=\"sep0\" width=\"188\" height=\"1\"></td>\n");
      out.write("      </tr>\n");
      out.write("      <tr>\n");
      out.write("        <td class=\"menu\"><a href=\"Administrator.jsp\">Add Admin</a></td>\n");
      out.write("      </tr>\n");
      out.write("      <tr>\n");
      out.write("        <td height=\"1\"><img src=\"resources/images/m-sep.gif\" alt=\"sep4\" width=\"188\" height=\"1\"></td>\n");
      out.write("      </tr>\n");
      out.write("      <tr>\n");
      out.write("        <td class=\"menu\"><a href=\"Sensor_registration.jsp\">Sensors</a></td>\n");
      out.write("      </tr>\n");
      out.write("      <tr>\n");
      out.write("        <td height=\"1\"><img src=\"resources/images/m-sep.gif\" alt=\"sep4\" width=\"188\" height=\"1\"></td>\n");
      out.write("      </tr>\n");
      out.write("      <tr>\n");
      out.write("        <td class=\"menu\"><a href=\"Snos_registration.jsp\">SNOS</a></td>\n");
      out.write("      </tr>\n");
      out.write("      <tr>\n");
      out.write("        <td height=\"1\"><img src=\"resources/images/m-sep.gif\" alt=\"sep4\" width=\"188\" height=\"1\"></td>\n");
      out.write("      </tr>\n");
      out.write("\n");
      out.write("      <tr>\n");
      out.write("        <td class=\"menu\"><a href=\"View_Registered_administrator.jsp\">View Admin</a></td>\n");
      out.write("      </tr>\n");
      out.write("      <tr>\n");
      out.write("        <td height=\"1\"><img src=\"resources/images/m-sep.gif\" alt=\"sep\" width=\"188\" height=\"1\"></td>\n");
      out.write("      </tr>\n");
      out.write("      <tr>\n");
      out.write("        <td class=\"menu\"><a href=\"View_Registered_users.jsp\">View users</a></td>\n");
      out.write("      </tr>\n");
      out.write("\n");
      out.write("      <tr>\n");
      out.write("        <td height=\"1\"><img src=\"resources/images/m-sep.gif\" alt=\"sep\" width=\"188\" height=\"1\"></td>\n");
      out.write("      </tr>\n");
      out.write("      <tr>\n");
      out.write("        <td class=\"menu\"><a href=\"View_Registered_sensor.jsp\">View Sensors</a></td>\n");
      out.write("      </tr>\n");
      out.write("\n");
      out.write("      <tr>\n");
      out.write("        <td height=\"1\"><img src=\"resources/images/m-sep.gif\" alt=\"sep\" width=\"188\" height=\"1\"></td>\n");
      out.write("      </tr>\n");
      out.write("      <tr>\n");
      out.write("        <td class=\"menu\"><a href=\"View_Registered_snos.jsp\">View SNOS</a></td>\n");
      out.write("      </tr>\n");
      out.write("     <tr>\n");
      out.write("        <td height=\"1\"><img src=\"resources/images/m-sep.gif\" alt=\"sep\" width=\"188\" height=\"1\"></td>\n");
      out.write("      </tr>\n");
      out.write("      <tr>\n");
      out.write("        <td class=\"menu\"><a href=\"AdminReport.jsp\">Generate Report</a></td>\n");
      out.write("      </tr>\n");
      out.write("\n");
      out.write("      <tr>\n");
      out.write("        <td><p><img src=\"resources/images/spacer.gif\" alt=\"\" width=\"1\" height=\"12\"></p>\n");
      out.write("          <p>&nbsp;</p>\n");
      out.write("          <p align=\"center\">Lets help<img src=\"resources/images/hep.jpg\" alt=\"help me\" width=\"100\" height=\"60\"></p></td>\n");
      out.write("      </tr>\n");
      out.write("    </table></td>\n");
      out.write("  </tr>\n");
      out.write("  <tr>\n");
      out.write("    <td align=\"right\" bgcolor=\"#FFFFFF\"><img src=\"resources/images/m-bottom.gif\" alt=\"\" width=\"156\" height=\"110\"></td>\n");
      out.write("  </tr>\n");
      out.write("</table>\n");
      out.write("\n");
      out.write("        </div>\n");
      out.write("        </td>");
      out.write("\n");
      out.write("\n");
      out.write("<td valign=\"top\">\n");
      out.write("        <div  id=\"SearchContainer\">\n");
      out.write("        <table width=\"450\"  cellspacing=\"0\" cellpadding=\"0\"  align=\"center\" class=\"border\">\n");
      out.write("\n");
      out.write("\n");
      out.write("<tr>\n");
      out.write("  <td height=\"20\" bgcolor=\"#00CC99\" align=\"center\" class=\"border\" colspan=\"3\"><font color=\"blue\" size=\"3\" face=\"Georgia, Times New Roman, Times, serif\">SMS Message Report Page</font>\n");
      out.write("</td></tr>\n");
      out.write("\n");
      out.write("<tr><td height=\"5\">\n");
      out.write("&nbsp;&nbsp;\n");
      out.write("</td></tr>\n");
      out.write("\n");
      out.write("<tr><td  width=\"100%\" align=\"center\" >\n");
      out.write("\n");
      out.write(" <fieldset>\n");
      out.write(" <legend><font color=\"blue\" size=\"3\" face=\"Georgia, Times New Roman, Times, serif\">* Enter Date to find Record</font></legend>\n");
      out.write("\n");
      out.write("\t\t\t<form   method=\"post\" action=\"../AdminRequestProcessing\" name=\"searchDateForm\" onsubmit=\" return ValidateAdminDateSearch();\">\n");
      out.write("\n");
      out.write("  <input type=\"text\" name=\"search_date\"  size=\"30\" maxlength=\"50\"  placeholder=\"Enter Date\" style=\"top: 40px;\"  />\n");
      out.write("\n");
      out.write("\n");
      out.write("<input type=\"submit\" name=\"findB_Date\" value=\"Find\" style=\"top: 110px; border: 1px solid #698EEF; padding-left:4px; padding-right:4px; padding-top:1px; padding-bottom:1px; background-color:#009999; color:#FFF; margin-left:5px\" />\n");
      out.write("\n");
      out.write("\t\t\t</form>\n");
      out.write("\n");
      out.write("\n");
      out.write("        </fieldset>\n");
      out.write("        <font size=2 color=red>\n");
      out.write("\n");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${searchAlert}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write('\n');
      out.write('\n');
  session.removeAttribute("searchAlert"); 
      out.write("\n");
      out.write(" </font>\n");
      out.write("\n");
      out.write("</td></tr>\n");
      out.write("\n");
      out.write("<tr><td height=\"5\">\n");
      out.write("&nbsp;&nbsp;\n");
      out.write("</td></tr>\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<tr><td height=\"20\" align=\"center\"  bgcolor=\"#00CC99\" width=\"100%\" class=\"border\">\n");
      out.write("    <font face=\"Times New Roman\" size=\"3\" color=\"red\">\n");
      out.write("         ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${searchemptyAlert}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write('\n');
      out.write('\n');

   session.removeAttribute("searchemptyAlert");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("     </font>\n");
      out.write("\n");
      out.write("  </td></tr>\n");
      out.write("\n");
      out.write("</table>\n");
      out.write("</div></td>\n");
      out.write("\n");
      out.write("\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<div style=\"vertical-align:bottom\">\r\n");
      out.write("\r\n");
      out.write("<tr>\r\n");
      out.write("    <td bgcolor=\"#0b7ca9\" class=\"bot-bg\" colspan=\"2\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n");
      out.write("      <tr>\r\n");
      out.write("        <td class=\"bottom_menu\"><a href=\"index.jsp\">Home</a> | <a href=\"about.jsp\">About SNOS</a> | <a href=\"services.jsp\">Services</a> | <a href=\"user_register.jsp\">Sign up</a> | <a href=\"index.jsp\">Login</a> | <a href=\"contact.jsp\">Contact Us</a></td>\r\n");
      out.write("      </tr>\r\n");
      out.write("      <tr>\r\n");
      out.write("        <td class=\"bottom_addr\">&copy; 2013 Teledominternational Ltd</td>\r\n");
      out.write("      </tr>\r\n");
      out.write("    </table></td>\r\n");
      out.write("  </tr>\r\n");
      out.write("</table>\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
      out.write("</div>");
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

package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class header_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\" />\r\n");
      out.write("<title>:::SNOS:::</title>\r\n");
      out.write("<meta name=\"description\" content=\"Security website\">\r\n");
      out.write("<meta name=\"keywords\" content=\"Security, Monitoring,Alert\">\r\n");
      out.write("<link rel=\"shortcut icon\"  href=\"resources/images/favicon.ico\" type=\"image/x-icon\" />\r\n");
      out.write("<link href=\"resources/css/style.css\" rel=\"stylesheet\" type=\"text/css\">\r\n");
      out.write("<link href=\"resources/css/loginDisplayCSS.css\" rel=\"stylesheet\" type=\"text/css\">\r\n");
      out.write("<link href=\"resources/css/SlideCss.css\" rel=\"stylesheet\" type=\"text/css\">\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\"  src=\"resources/Script/jquery-1.8.2.js\" ></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"resources/Script/jquery-ui-1.9.0.custom.min.js\" ></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"resources/Script/jquery-ui-tabs-rotate.js\" ></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"resources/Script/script.js\" ></script>\r\n");
      out.write("<script type=\"text/javascript\"   src=\"resources/Script/Tele_script.js\" ></script>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("</head>\r\n");
      out.write("\r\n");
      out.write("<body>\r\n");
      out.write("\r\n");
      out.write("<table width=\"80%\" height=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" bgcolor=\"white\">\r\n");
      out.write("  <tr>\r\n");
      out.write("    <td colspan=\"2\">\r\n");
      out.write("\t<table   cellspacing=\"0\" cellpadding=\"0\" class=\"tableborder\">\r\n");
      out.write("      <tr>\r\n");
      out.write("        <td width=\"1\">\r\n");
      out.write("        <div style=\"position:absolute; width:400px; top:15px; background:url('resources/images/cn-bg.gif'); left: 149px;\">\r\n");
      out.write("        <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n");
      out.write("  <tr>\r\n");
      out.write("    <td width=\"1\"><img  src=\"resources/images/logo.jpg\" alt=\"\" width=\"61\" height=\"61\" class=\"logo\"></td>\r\n");
      out.write("    <td class=\"company_name\">SECURITY NETWORK OPERATING SYSTEM</td>\r\n");
      out.write("  </tr>\r\n");
      out.write("</table>\r\n");
      out.write("        </div>\r\n");
      out.write("\t\t<div id=\"Welcome\" > Hi ");
      out.print(session.getAttribute("user")  );
      out.write(" </div>\r\n");
      out.write("\r\n");
      out.write("        <div id=\"slogan\">\r\n");
      out.write("        SNOS<br/>...Securing Your World</div>\r\n");
      out.write("        <img  src=\"resources/images/bansnos.png\" alt=\"\" width=\"1100\" height=\"196\" class=\"tableborder\"></td>\r\n");
      out.write("\r\n");
      out.write("      </tr>\r\n");
      out.write("\r\n");
      out.write("\t  <tr>\r\n");
      out.write("  <td height=\"5\" >\r\n");
      out.write("\r\n");
      out.write("  </td></tr>\r\n");
      out.write("\r\n");
      out.write("  <tr>\r\n");
      out.write("  <td  class=\"Hyperlink\" valign=\"top\" colspan=\"2\"  align=\"center\" height=\"25\">\r\n");
      out.write("\r\n");
      out.write("<table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\r\n");
      out.write("<tr><td  width=\"91\" align=\"center\"  id=\"menuHyperlink\" >\r\n");
      out.write("<a href=\"index.jsp\"><img src=\"resources/images/home.jpg\"  />&nbsp;Home</a>\r\n");
      out.write("</td>\r\n");
      out.write("<td  width=\"158\" align=\"center\"  id=\"menuHyperlink\">\r\n");
      out.write("<a href=\"about.jsp\"><img src=\"resources/images/arrow.jpg\"  />&nbsp;About SNOS</a></td>\r\n");
      out.write("\r\n");
      out.write("<td  width=\"97\" align=\"center\" id=\"menuHyperlink\">\r\n");
      out.write("<a href=\"services.jsp\"><img src=\"resources/images/arrow.jpg\"  />&nbsp;Services</a></td>\r\n");
      out.write("<td  width=\"134\" align=\"center\" id=\"menuHyperlink\">\r\n");
      out.write("<a href=\"contact.jsp\"><img src=\"resources/images/arrow.jpg\" />&nbsp;Contact us</a></td>\r\n");
      out.write("<td  width=\"231\" align=\"center\" id=\"menuHyperlink\">\r\n");
      out.write("<a href=\"support_client.jsp\"><img src=\"resources/images/arrow.jpg\" />&nbsp;Technical Support</a></td>\r\n");
      out.write("<td width=\"387\"   align=\"right\" >\r\n");
      out.write("    <div   class=\"dropdown\">\r\n");
      out.write("\t<a class=\"account\" >\r\n");
      out.write("\t<span>My Account</span>\t</a>\r\n");
      out.write("\t<div class=\"submenu\" style=\"display: none; \">\r\n");
      out.write("\r\n");
      out.write("\t  <ul class=\"root\">\r\n");
      out.write("<li >\r\n");
      out.write("\t      <a href=\"#\" >Profile</a>\r\n");
      out.write("\t    </li>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t    <li >\r\n");
      out.write("\t      <a href=\"#\" >Contacts Infor</a>\r\n");
      out.write("\t    </li>\r\n");
      out.write("\t   <li >\r\n");
      out.write("\r\n");
      out.write("\t      <a href=\"#settings\">Settings</a>\r\n");
      out.write("\t    </li>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t    <li>\r\n");
      out.write("\t      <a href=\"#\">Action Taken</a>\r\n");
      out.write("\t    </li>\r\n");
      out.write("        <li>\r\n");
      out.write("\t      <a href=\"#\">Change Password</a>\r\n");
      out.write("\t    </li>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t    <li>\r\n");
      out.write("\t      <a href=\"#signout\">Sign Out</a>\r\n");
      out.write("\t    </li>\r\n");
      out.write("\t  </ul>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t</div>&nbsp;&nbsp;&nbsp;</td>\r\n");
      out.write("</tr>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("  </table>\r\n");
      out.write("  </td>\r\n");
      out.write("  </tr>\r\n");
      out.write("\r\n");
      out.write("    </table></td>\r\n");
      out.write("  </tr>");
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

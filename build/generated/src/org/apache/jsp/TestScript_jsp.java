package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class TestScript_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\"\n");
      out.write("   \"http://www.w3.org/TR/html4/loose.dtd\">\n");
      out.write("\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>JSP Page</title>\n");
      out.write("       <script  src=\"resources/Scripts/script_1.js\" type=\"text/javascript\"></script>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("       <td valign=\"top\">\n");
      out.write("        <div  >\n");
      out.write("        <table width=\"450\"  cellspacing=\"0\" cellpadding=\"0\"  align=\"center\" class=\"border\">\n");
      out.write("\n");
      out.write("<tr>\n");
      out.write("     <td width=\"400\" height=\"10\" bgcolor=\" \"  align=\"center\" colspan=\"2\"  style=\"padding-top:10px;\">\n");
      out.write("     <b><font face=\"Times New Roman\" size=\"5\" color=\"blue\">Snos Registration</font></b></td>\n");
      out.write("</tr>\n");
      out.write("\n");
      out.write("<form action=\"TestScript.jsp \" method=\"post\" name=\"TestForm\" onsubmit=\"ValidateFormTest();\">\n");
      out.write("\n");
      out.write("<tr>\n");
      out.write("     <td width=\"400\" height=\"30\" bgcolor=\" \"  align=\"center\">\n");
      out.write("     <b>&nbsp;&nbsp;<font face=\"Times New Roman\" size=\"3\" color=\"red\">\n");
      out.write("        \n");
      out.write("\n");
      out.write("     </font></b></td>\n");
      out.write("</tr>\n");
      out.write("<tr>\n");
      out.write("<td style=\" padding-left:10px;\">\n");
      out.write("<font face= \"Times New Roman\" size = \"3\" color=\"blue\">Email:*&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>\n");
      out.write("<input type=\"text\" size = \"30\" name = \"email\" maxlength=\"50\" placeholder=\"Enter Email\"><br><font size=2 color=red>\n");
      out.write("\n");
      out.write(" </font>\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("</td>\n");
      out.write("</tr>\n");
      out.write("\n");
      out.write("<tr>\n");
      out.write("<td height=\"5\">\n");
      out.write("&nbsp;&nbsp;\n");
      out.write("</td>\n");
      out.write("</tr>\n");
      out.write("\n");
      out.write("<tr>\n");
      out.write("<td style=\" padding-left:10px;\">\n");
      out.write("<font face= \"Times New Roman\" size = \"3\" color=\"blue\">Mobile Number:*</font>\n");
      out.write("<input type=\"text\" size = \"30\" name = \"number\" maxlength=\"50\" placeholder=\"Enter Number\"><br><font size=2 color=red>\n");
      out.write("\n");
      out.write(" </font>\n");
      out.write("\n");
      out.write("</td>\n");
      out.write("</tr>\n");
      out.write("\n");
      out.write("<tr>\n");
      out.write("<td height=\"5\">\n");
      out.write("&nbsp;&nbsp;\n");
      out.write("</td>\n");
      out.write("</tr>\n");
      out.write("<tr><td align=\"center\" width = \"100%\"  height= \"20\" colspan=\"2\" >\n");
      out.write("<input type=\"submit\" value=\"Submit\" name=\"RegSnosButton\" style=\"border:1px solid #698EEF; padding-left:4px; padding-right:4px; padding-top:1px; padding-bottom:1px; color:#FFF;background-color:#009999\" >\n");
      out.write(" &nbsp; <input type=\"reset\" value=\"Reset\" name=\"can\" style=\"border: 1px solid #698EEF; padding-left:4px; padding-right:4px; padding-top:1px; padding-bottom:1px; color:#FFF;background-color:#009999\">\n");
      out.write("</td></tr> </form>\n");
      out.write("\n");
      out.write("<tr>\n");
      out.write("      <td td align=\"center\" width = \"100%\"  height= \"20\" colspan=\"2\" >\n");
      out.write("\n");
      out.write("   </td>\n");
      out.write("    </tr>\n");
      out.write("</table>\n");
      out.write("\n");
      out.write("\n");
      out.write("</div></td>\n");
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

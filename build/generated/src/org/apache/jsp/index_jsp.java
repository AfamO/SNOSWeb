package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\"  />\r\n");
      out.write("<title>:::SNOS:::</title>\r\n");
      out.write("<meta name=\"description\" content=\"Security website\">\r\n");
      out.write("<meta name=\"keywords\" content=\"Security, Monitoring,Alert\">\r\n");
      out.write("<link rel=\"shortcut icon\"  href=\"resources/images/favicon.ico\" type=\"image/x-icon\" />\r\n");
      out.write("<link href=\"resources/css/style.css\" rel=\"stylesheet\" type=\"text/css\">\r\n");
      out.write("<link href=\"resources/css/loginDisplayCSS.css\" rel=\"stylesheet\" type=\"text/css\">\r\n");
      out.write("<link href=\"resources/css/SlideCss.css\" rel=\"stylesheet\" type=\"text/css\">\r\n");
      out.write("<link href=\"resources/Style/afamcss.css\" rel=\"stylesheet\" type=\"text/css\">\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\"  src=\"resources/Script/jquery-1.8.2.js\" ></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"resources/Script/jquery-ui-1.9.0.custom.min.js\" ></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"resources/Script/jquery-ui-tabs-rotate.js\" ></script>\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\"   src=\"resources/Script/Tele_script.js\" ></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"resources/Script/script.js\" ></script>\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\t$(document).ready(function(){\r\n");
      out.write("\t\t$(\"#featured\").tabs({fx:{opacity: \"toggle\"}}).tabs(\"rotate\", 6000, true);\r\n");
      out.write("\t});\r\n");
      out.write("</script>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("</head>\r\n");
      out.write("\r\n");
      out.write("<body>\r\n");
      out.write("\r\n");
      out.write("<table width=\"100%\" height=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" bgcolor=\"white\">\r\n");
      out.write("  <tr>\r\n");
      out.write("    <td colspan=\"2\">\r\n");
      out.write("\t<table   cellspacing=\"0\" cellpadding=\"0\" class=\"tableborder\">\r\n");
      out.write("      <tr>\r\n");
      out.write("        <td width=\"1\">\r\n");
      out.write("        <div style=\"position:absolute; width:400px; top:15px; background:url('resources/images/cn-bg.gif'); left: 149px;\">\r\n");
      out.write("        <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n");
      out.write("  <tr>\r\n");
      out.write("    <td width=\"1\"><img  src=\"resources/images/logo.jpg\" alt=\"\" width=\"61\" height=\"61\" class=\"logo\"></td>\r\n");
      out.write("    <td class=\"company_name\">SECURITY NETWORK OPERATION SYSTEM</td>\r\n");
      out.write("  </tr>\r\n");
      out.write("</table>\r\n");
      out.write("        </div>\r\n");
      out.write("        <div id=\"slogan\">\r\n");
      out.write("        SNOS<br/>\r\n");
      out.write("        <img src=\"resources/images/w17.gif\" width=\"17\" height=\"1\">...Securing Your World</div>\r\n");
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
      out.write("<tr><td  width=\"78\" align=\"center\"  id=\"menuHyperlink\" class=\"current_page_item\">\r\n");
      out.write("<a href=\"index.jsp\"><img src=\"resources/images/home.jpg\"  />&nbsp;Home</a>\r\n");
      out.write("</td>\r\n");
      out.write("<td  width=\"158\" align=\"center\"  id=\"menuHyperlink\">\r\n");
      out.write("<a href=\"about.jsp\"><img src=\"resources/images/arrow.jpg\"  />&nbsp;About SNOS</a></td>\r\n");
      out.write("\r\n");
      out.write("<td  width=\"97\" align=\"center\" id=\"menuHyperlink\">\r\n");
      out.write("<a href=\"services.jsp\"><img src=\"resources/images/arrow.jpg\"  />&nbsp;Services</a></td>\r\n");
      out.write("<td  width=\"130\" align=\"center\" id=\"menuHyperlink\">\r\n");
      out.write("<a href=\"contact.jsp \"><img src=\"resources/images/arrow.jpg\" />&nbsp;Contact Us</a></td>\r\n");
      out.write("<td  width=\"219\" align=\"center\" id=\"menuHyperlink\">\r\n");
      out.write("<a href=\"support_client.jsp\"><img src=\"resources/images/arrow.jpg\" />&nbsp;Technical Support</a></td>\r\n");
      out.write("<td width=\"400\" align=\"right\" class=\"\">\r\n");
      out.write("    <span style=\" color:#FFF\">  <script language=\"javascript\">\r\n");
      out.write("        TeleTime();\r\n");
      out.write("</script></span>&nbsp;&nbsp;&nbsp;\r\n");
      out.write("</td>\r\n");
      out.write("</tr>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("  </table>\r\n");
      out.write("  </td>\r\n");
      out.write("  </tr>\r\n");
      out.write("\r\n");
      out.write("    </table></td>\r\n");
      out.write("  </tr>\r\n");
      out.write("\r\n");
      out.write("  \r\n");
      out.write("  <tr>\r\n");
      out.write("  <td height=\"250\" width=\"100%\" align=\"center\" valign=\"top\" colspan=\"2\">\r\n");
      out.write("  <div><table width=\"100%\" height=\"100%\" cellpadding=\"0\" cellspacing=\"0\" >\r\n");
      out.write("  <tr><td>\r\n");
      out.write("  \r\n");
      out.write("  <div  id=\"featured\" >\r\n");
      out.write("\t\t  <ul class=\"ui-tabs-nav\">\r\n");
      out.write("\t       <li class=\"ui-tabs-nav-item\" id=\"nav-fragment-1\"><a href=\"#fragment-1\"></a></li>\r\n");
      out.write("\t      <li class=\"ui-tabs-nav-item\" id=\"nav-fragment-2\"><a href=\"#fragment-2\"></a></li>\r\n");
      out.write("\t      <li class=\"ui-tabs-nav-item\" id=\"nav-fragment-3\"><a href=\"#fragment-3\"></a></li>\r\n");
      out.write("\t       <li class=\"ui-tabs-nav-item\" id=\"nav-fragment-4\"><a href=\"#fragment-4\"></a></li>\r\n");
      out.write("\t      </ul>\r\n");
      out.write("\r\n");
      out.write("\t    <!-- First Content -->\r\n");
      out.write("\t    <div id=\"fragment-1\" class=\"ui-tabs-panel\">\r\n");
      out.write("\t\t\t<img    src=\"resources/slideimage/banner1.png\" width=\"1100\" height=\"250\" />\r\n");
      out.write("\t\t\t <div class=\"info\" >\r\n");
      out.write("\t\t\t\t<h2><a href=\"#\" >&nbsp;&nbsp;Security Network Operating System (SNOS)</a></h2>\r\n");
      out.write("\t\t\t\t<p>Security Network Operating System (SNOS)  is security solution that uses state of the art facility to produce a system that can guaranty effective security and  monitoring of human and property ...<a href=\"#\" >read more</a></p>\r\n");
      out.write("\t\t\t </div>\r\n");
      out.write("\t    </div>\r\n");
      out.write("\r\n");
      out.write("\t    <!-- Second Content -->\r\n");
      out.write("\t    <div id=\"fragment-2\" >\r\n");
      out.write("\t\t\t<img   src=\"resources/slideimage/snoc2.png\"  width=\"1100\" height=\"250\" />\r\n");
      out.write("\t\t\t <div class=\"info\" >\r\n");
      out.write("\t\t\t\t<h2><a href=\"#\" >&nbsp;&nbsp;Security Network Operating System (SNOS)</a></h2>\r\n");
      out.write("\t\t\t\t<p>Security Network Operating System (SNOS)  is security solution that uses state of the art facility to produce a system that can guaranty effective security and  monitoring of human and property ...<a href=\"#\" >read more</a></p>\r\n");
      out.write("\t\t\t </div>\r\n");
      out.write("\t    </div>\r\n");
      out.write("\r\n");
      out.write("\t    <!-- Third Content -->\r\n");
      out.write("\t    <div id=\"fragment-3\" >\r\n");
      out.write("\t\t\t<img  src=\"resources/slideimage/banner3.png\" width=\"1100\" height=\"250\" />\r\n");
      out.write("\t\t\t <div class=\"info\" >\r\n");
      out.write("\t\t\t\t<h2><a href=\"#\" >&nbsp;&nbsp;Security Network Operating System (SNOS)</a></h2>\r\n");
      out.write("\t\t\t\t<p>Security Network Operating System (SNOS)  is security solution that uses state of the art facility to produce a system that can guaranty effective security and  monitoring of human and property.....<a href=\"#\" >read more</a></p>\r\n");
      out.write("\t         </div>\r\n");
      out.write("\t    </div>\r\n");
      out.write("\r\n");
      out.write("\t    <!-- Fourth Content -->\r\n");
      out.write("\t    <div id=\"fragment-4\" >\r\n");
      out.write("\t\t\t<img  src=\"resources/slideimage/banner4.png\" width=\"1100\" height=\"250\" />\r\n");
      out.write("\t\t\t <div class=\"info\" >\r\n");
      out.write("\t\t\t\t<h2><a href=\"#\" >Security Network Operating System (SNOS)</a></h2>\r\n");
      out.write("\t\t\t\t<p>Security Network Operating System (SNOS)  is security solution that uses state of the art facility to produce a system that can guaranty effective security and  monitoring of human and property ...<a href=\"#\" >read more</a></p>\r\n");
      out.write("\t         </div>\r\n");
      out.write("\t    </div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\r\n");
      out.write("\r\n");
      out.write("  \r\n");
      out.write(" </td></tr> </table></div>\r\n");
      out.write("  </td></tr>\r\n");
      out.write("  \r\n");
      out.write("  \r\n");
      out.write("  <!....the center code starts here ....!> \r\n");
      out.write("  \r\n");
      out.write("   \r\n");
      out.write("  \r\n");
      out.write(" \r\n");
      out.write("  <tr>\r\n");
      out.write(" <td width=\"100%\" valign=\"top\" >\r\n");
      out.write(" <table width=\"100%\"  cellspacing=\"0\" cellpadding=\"0\"  >\r\n");
      out.write("\t<tr><td >\r\n");
      out.write("    <div id=\"errmsg1\">This is Error Message.Got it right?</div>\r\n");
      out.write("  <div id=\"errmsg\"></div>\r\n");
      out.write("  <div   ALIGN = \"center\" WIDTH = \"40\" >\r\n");
      out.write("\r\n");
      out.write("<FONT FACE = \"ARIAL\" STYLE=\"\" SIZE = \"2\" COLOR =\"Red\">\r\n");
      out.write("    <strong>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${errmsg}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</strong>\r\n");
      out.write("  \r\n");
      out.write("</FONT></div>\r\n");
      out.write(" </td></tr>\r\n");
      out.write(" <tr><td >\r\n");
      out.write(" <div   ALIGN = \"center\" WIDTH = \"40\" >\r\n");
      out.write("<FONT FACE = \"ARIAL\" STYLE=\"\" SIZE = \"2\" COLOR =\"Blue\">\r\n");
      out.write("\r\n");
      out.write("    <strong>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${logout}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</strong>\r\n");
      out.write("</FONT></div>\r\n");
      out.write("\r\n");
      out.write("  </td></tr>\r\n");
      out.write("  <tr>\r\n");
      out.write("    <td class=\"mbg\" width=\"250\" >\r\n");
      out.write("\t <table width=\"100%\"  cellspacing=\"0\" cellpadding=\"0\"  >\r\n");
      out.write("\t <tr>\r\n");
      out.write("\t <td>\r\n");
      out.write("\t<div style=\" width:250px; vertical-align:top; margin-top:30px;\" id=\"LoginTableContainer\">\r\n");
      out.write("\t\t<form id=\"\" action=\"servlets/RequestProcessing\" method=\"post\" name=\"fo\"  onSubmit=\"return LoginFormValidation();\">\r\n");
      out.write("              <table width=\"200\" height=\"200\"  style=\"margin-left:250px; margin-top:20px; vertical-align:top;\" class=\"tableborder\" >\r\n");
      out.write("        <tr>\r\n");
      out.write("          <td width=\"100%\" height=\"20\"  class=\"login_item\" >LOGIN :&nbsp;&nbsp;<img src=\"resources/images/sign4.gif\" height=\"30\"></td>\r\n");
      out.write("         \r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("          <td height=\"20\" align=\"center\"><input type=\"text\"  name=\"userid\" onchange=\"return LoginFormValidation();\" id=\"loginEmail\" size=\"18\" class=\"logintext\" placeholder=\"Username\"/></td>\r\n");
      out.write("          <td ></td>\r\n");
      out.write("\t\t  <tr><td height=\"20\" align=\"center\">\r\n");
      out.write("          <input type=\"password\" name=\"password\" onchange=\"return LoginFormValidation();\"  id=\"loginPass\" size=\"18\" class=\"logintext\" placeholder=\"Password\"/></td>\r\n");
      out.write("        </tr>\r\n");
      out.write("       \r\n");
      out.write("<tr>\r\n");
      out.write("\r\n");
      out.write("<td id=\"ImgTxt\" height=\"20\" align=\"center\">Please Enter the image below:<input type=\"text\" name=\"code\" size=\"18\" id=\"loginEmail\" class=\"logintext\" placeholder=\"Captcha Image\"></td>\r\n");
      out.write("</tr>\r\n");
      out.write(" <tr>\r\n");
      out.write("\r\n");
      out.write("<td>\r\n");
      out.write("<img src=\"http://localhost:8084/SnoSSoftwareTest3/servlets/CaptchaServlet\">\r\n");
      out.write("\r\n");
      out.write("</td>\r\n");
      out.write("</tr>\r\n");
      out.write("<tr>\r\n");
      out.write("     <input type=\"hidden\" name=\"source\" value=\"desktop\">     <td height=\"20\" valign=\"top\" align=\"center\" ><input type=\"submit\" value=\"Login\" name=\"action\" id=\"LoginformInputSubmit\" />&nbsp;<br><span  id=\"forgetpassword\"><a href=\"recover_password.jsp\" >Forgot Password ?</a></span></td>\r\n");
      out.write("    </tr>\r\n");
      out.write("\r\n");
      out.write("        </table>\r\n");
      out.write("    </form>\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\r\n");
      out.write("        </td></tr>\r\n");
  session.removeAttribute("errmsg"); session.getServletContext().removeAttribute("logout"); 
      out.write("\r\n");
      out.write("\r\n");
      out.write("        <td height=\"5\" >\r\n");
      out.write("  &nbsp;&nbsp;\r\n");
      out.write("  </td></tr>\r\n");
      out.write("  <tr>\r\n");
      out.write("   <td height=\"100\"></td>\r\n");
      out.write("  </tr>\r\n");
      out.write("  \r\n");
      out.write("\t\t\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t <tr>\r\n");
      out.write("\t\t  <td><table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\r\n");
      out.write("\t\t   \r\n");
      out.write("\t\t  <tr>\r\n");
      out.write("\t\t  <td><a href=\"user_register.jsp\"> <img  src=\"resources/images/regsnos.png\"   width=\"200\" class=\"Imagedisplay\"></a></td>\r\n");
      out.write("\t\t  </tr>\r\n");
      out.write("\t\t  <tr>\r\n");
      out.write("\t\t   <td><a href=\"#\"><img   src=\"resources/images/Helpsnos.png\"  width=\"100\" class=\"Imagedisplay\"></a></td>\r\n");
      out.write("\t\t  </tr>\r\n");
      out.write("\t\t  </table>\r\n");
      out.write("\t\t  </td>\r\n");
      out.write("\t\t  </tr>\r\n");
      out.write("\t\t  \r\n");
      out.write("\t\t  \r\n");
      out.write("\t\t</table>\r\n");
      out.write("</td>\r\n");
      out.write("<td  width=\"800\" >\r\n");
      out.write("<table cellpadding=\"0\" cellspacing=\"0\" align=\"center\" width=\"800\" >\r\n");
      out.write("\r\n");
      out.write("<tr><td><h1 class=\"style1\">Welcome to The Home Of First IT Security Company In Nigeria!</h1></td>\r\n");
      out.write("          </tr>\r\n");
      out.write("  \r\n");
      out.write("          <tr>\r\n");
      out.write("            <td class=\"body_txt\"><hr><p>Security Network Operating System (SNOS)  is a security solution that uses state of the art facility to produce a system that can guaranty effective security and  monitoring of humans and properties .\r\n");
      out.write("The Security Network Operating System gives one continues monitoring of one's interest even when you are out of town.\r\n");
      out.write(" </p>\r\n");
      out.write("\t\t\t\r\n");
      out.write("            <p class = \"header\">OUR VISION</p>\r\n");
      out.write("                <p>To Secure every socio-economic process using Hi-tech Information and Communications Technology (ICT) infrastructure and software facilities with appropriate content solutions to boost national economic productivity and prosperity. </p>\r\n");
      out.write("                <p>&nbsp;</p>\r\n");
      out.write("            <p class = \"header\">OUR MISSION</p>\r\n");
      out.write("                <p>To  Detect and Monitor .</p>\r\n");
      out.write("                <p>To Report . </p>\r\n");
      out.write("               <p>To Take Action . </p>\r\n");
      out.write("                <p> </p>\r\n");
      out.write("              </td>\r\n");
      out.write("          </tr>\r\n");
      out.write("\t\t  \r\n");
      out.write("\t\t  <tr>\r\n");
      out.write("\t\t  <td colspan=\"2\"><table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\r\n");
      out.write("\t\t   <tr>\r\n");
      out.write("\t\t   <td><p><img src=\"resources/slideimage/snosimage1.png\"  class=\"tableborder\" id=\"logoMain\"></p><p>Security Network Operating System (SNOS)  is a security solution that uses state of the art facility to produce a system that can guaranty effective security and  monitoring of humans and properties .</p></td>\r\n");
      out.write("\t\t  <td><a href=\"user_register.jsp\"> <img  src=\"resources/images/platformsnos2.gif\" class=\"Imagedisplay\"></a></td>\r\n");
      out.write("\t\t  </tr></table></td>\r\n");
      out.write("\t\t  </tr>\r\n");
      out.write("\t\t  \r\n");
      out.write("</table>\r\n");
      out.write("\r\n");
      out.write("</td>\r\n");
      out.write("</tr>\r\n");
      out.write("</table>\r\n");
      out.write("</td>\r\n");
      out.write("</tr>\r\n");
      out.write("\r\n");
      out.write("<tr>\r\n");
      out.write("    <td bgcolor=\"#0b7ca9\" class=\"bot-bg\" colspan=\"2\"><table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" >\r\n");
      out.write("      <tr>\r\n");
      out.write("       <td class=\"bottom_menu\"><a href=\"index.jsp\">Home</a> | <a href=\"about.jsp\">About SNOS</a> | <a href=\"services.jsp\">Services</a> | <a href=\"user_register.jsp\">Sign up</a> | <a href=\"index.jsp\">Login</a> | <a href=\"contact.jsp\">Contact Us</a> | <a href=\"Admin/index.jsp\">Admin</a></td>\r\n");
      out.write("      </tr>\r\n");
      out.write("      <tr>\r\n");
      out.write("        <td class=\"bottom_addr\">&copy; 2013 Teledominternational Ltd</td>\r\n");
      out.write("      </tr>\r\n");
      out.write("    </table></td>\r\n");
      out.write("  </tr>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
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

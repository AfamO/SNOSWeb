package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class JqueryAjaxTuto_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("           <script type=\"text/javascript\" src=\"resources/Script/jquery-1.9.1.js\" ></script>\n");
      out.write("\n");
      out.write("           <script>\n");
      out.write("\n");
      out.write("               $(function(){\n");
      out.write("                   alert(\"Getting ready to load my file\");\n");
      out.write("                   $(\"#submit\").bind(\"click\", function(event,ui){\n");
      out.write("     alert(\"I have just bound this event to this form\");\n");
      out.write("\n");
      out.write("      getFromServer();\n");
      out.write("  });\n");
      out.write("                   //LoadFile();\n");
      out.write("                   //getFromServer();\n");
      out.write("               });\n");
      out.write("               function LoadFile(){\n");
      out.write("                   $(\"#div1\").load(\"BootStrap.html\",function(responseTxt,statusTxt,xhr){\n");
      out.write("    if(statusTxt==\"success\")\n");
      out.write("      alert(\"External content loaded successfully!\");\n");
      out.write("    if(statusTxt==\"error\")\n");
      out.write("      alert(\"Error: \"+xhr.status+\": \"+xhr.statusText);\n");
      out.write("  });\n");
      out.write("               }\n");
      out.write("               function getFromServer(){\n");
      out.write("                   alert(\"Getting ready to execute this getfromserver function\");\n");
      out.write("                   var formData = $(\"#ChangePassForm\").serialize();\n");
      out.write("                   alert(\"FormData:\"+formData);\n");
      out.write("                   var strUrl = \"http://localhost:8084/SnoSSoftwareTest3/servlets/RequestProcessing?opass=afam&npass=donsimon&\\n\\\n");
      out.write("vnpass=donsimon&id=Change_pass&source=mobile&action=Change My Password\";\n");
      out.write("                    strUrl = \"http://localhost:8084/SnoSSoftwareTest3/servlets/RequestProcessing?\"+formData;\n");
      out.write("                   $.get(strUrl, function(data,status,xhr){\n");
      out.write("                        alert(\"Status:\"+status);\n");
      out.write("                        alert(\"The data:\"+JSON.stringify(data))\n");
      out.write("\n");
      out.write("                        if(status==\"error\")\n");
      out.write("      alert(\"Error: \"+xhr.status+\": \"+xhr.statusText);\n");
      out.write("        \n");
      out.write("                   });\n");
      out.write("               }\n");
      out.write("               \n");
      out.write("           </script>\n");
      out.write("        <title>JSP Page</title>\n");
      out.write("\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <h1>Hello World!</h1>\n");
      out.write("\n");
      out.write("       <h3>Change My Password</h3>\n");
      out.write(" <form id=\"ChangePassForm\"    >\n");
      out.write("<fieldset >\n");
      out.write("<div data-role=\"fieldcontain\">\n");
      out.write("<label for=\"opass\">Old Password:</label>\n");
      out.write("<input type=\"password\" size=\"5\" id=\"opass\" name=\"opass\"  data-inline=\"true\">\n");
      out.write("</div>\n");
      out.write("<div data-role=\"fieldcontain\">\n");
      out.write("<label for=\"npass\">New Password:</label>\n");
      out.write("<input type=\"password\" size=\"15\" id=\"npass\" name=\"npass\"  data-inline=\"true\">\n");
      out.write("</div>\n");
      out.write("<div data-role=\"fieldcontain\">\n");
      out.write("<label for=\"vnpass\">Verify New Password:</label>\n");
      out.write("<input type=\"password\" size=\"15\" id=\"vnpass\" name=\"vnpass\"  data-inline=\"true\">\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("<div data-role=\"fieldcontain\">\n");
      out.write("<input type=\"submit\"  name=\"action\" value=\"Change My Password\" id=\"submit\" >\n");
      out.write("    <input type=\"reset\" data-theme=\"b\" value=\"Clear\" id=\"clear\" data-icon=\"delete\" data-inline=\"true\">\n");
      out.write("        <input type=\"hidden\" name=\"id\" value=\"change_pass\">\n");
      out.write("             <input type=\"hidden\" name=\"source\" value=\"mobile\">\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("</fieldset>\n");
      out.write(" </form>\n");
      out.write("        <div id=\"div1\">\n");
      out.write("\n");
      out.write("\n");
      out.write("        </div>\n");
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

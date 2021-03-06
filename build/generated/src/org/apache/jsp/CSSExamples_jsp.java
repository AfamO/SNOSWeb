package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class CSSExamples_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("        <title>CSS Examples Page</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("       <p style=\"background-image:url(resources/slideimage/snoc2.png);height:20%; width:20%;background-attachment:repeat fixed;-moz-opacity:1;opacity:1;filter:alpha(opacity=100);\">This parapgraph has fixed repeated background image.</p>\n");
      out.write("\n");
      out.write("    <style type=\"text/css\"> a:hover {color: red}\n");
      out.write("</style>\n");
      out.write("<a href=\"#pageLogins\">Bring Mouse Here</a><br>\n");
      out.write("    <style type=\"text/css\">\n");
      out.write("        a:active {color: #FF00CC}\n");
      out.write("</style> <a rel=\"external\" href=\"Html5.html\">Click This Link</a><br>\n");
      out.write("        <style type=\"text/css\">\n");
      out.write("table.one\n");
      out.write("{\n");
      out.write("    border-collapse:collapse;\n");
      out.write("    width:400px;\n");
      out.write("    border-spacing:10px;\n");
      out.write(" }\n");
      out.write("table.two\n");
      out.write("{\n");
      out.write("    border-collapse:separate;\n");
      out.write("    width:400px; \n");
      out.write("    border-spacing:10px 50px;\n");
      out.write("}\n");
      out.write("td.a\n");
      out.write("{ \n");
      out.write("    border-style:dotted;\n");
      out.write("    border-width:3px;\n");
      out.write("    border-color:#000000;\n");
      out.write("    padding: 10px;\n");
      out.write(" }\n");
      out.write("td.b\n");
      out.write("{\n");
      out.write("    border-style:solid;\n");
      out.write("    border-width:3px;\n");
      out.write("    border-color:#333333;\n");
      out.write("    padding:10px;\n");
      out.write("}\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("</style>\n");
      out.write("<table class=\"one\" border=\"1\">\n");
      out.write("<caption>Collapse Border Example with border-spacing</caption>\n");
      out.write("<tr>\n");
      out.write("<td class=\"a\"> Cell A Collapse Example</td>\n");
      out.write("</tr>\n");
      out.write("<tr>\n");
      out.write("<td class=\"b\"> Cell B Collapse Example</td>\n");
      out.write("</tr>\n");
      out.write("</table>\n");
      out.write("<br />\n");
      out.write("<table class=\"two\" border=\"1\">\n");
      out.write("<caption><caption>Separate Border Example with border-spacing</caption></caption>\n");
      out.write("<tr>\n");
      out.write("<td class=\"a\"> Cell A Separate Example</td>\n");
      out.write("</tr>\n");
      out.write("<tr>\n");
      out.write("<td class=\"b\"> Cell B Separate Example</td>\n");
      out.write("</tr>\n");
      out.write("</table>\n");
      out.write("\n");
      out.write("\n");
      out.write("<style type=\"text/css\">\n");
      out.write("caption.top\n");
      out.write("{\n");
      out.write("caption-side:top;\n");
      out.write("} \n");
      out.write("caption.bottom\n");
      out.write("{\n");
      out.write(" caption-side:bottom\n");
      out.write("}\n");
      out.write("caption.lefta\n");
      out.write("{\n");
      out.write("caption-side:left\n");
      out.write("}\n");
      out.write("caption.righta\n");
      out.write("{\n");
      out.write("caption-side:right\n");
      out.write("}\n");
      out.write("</style>\n");
      out.write("<table style=\"width:400px; border:1px solid black;\">\n");
      out.write("<caption class=\"top\"> This caption will appear at the top </caption>\n");
      out.write("<tr>\n");
      out.write("<td > Cell A</td>\n");
      out.write("</tr>\n");
      out.write("<tr>\n");
      out.write(" <td > Cell B</td>\n");
      out.write("</tr> </table> <br />\n");
      out.write("<table style=\"width:400px; border:1px solid black;\">\n");
      out.write("<caption class=\"bottom\"> This caption will appear at the bottom </caption>\n");
      out.write("<tr>\n");
      out.write("    <td > Cell A</td>\n");
      out.write("</tr>\n");
      out.write("<tr><td > Cell B</td></tr>\n");
      out.write("</table> <br />\n");
      out.write("<table style=\"width:400px; border:1px solid black;\">\n");
      out.write("<caption class=\"lefta\"> This caption will appear at the left </caption>\n");
      out.write("<tr>\n");
      out.write("    <td > Cell A</td>\n");
      out.write("    </tr>\n");
      out.write("    <tr><td > Cell B</td></tr>\n");
      out.write("    </table> <br />\n");
      out.write("    <table style=\"width:400px; border:1px solid black;\">\n");
      out.write("    <caption class=\"righta\"> This caption will appear at the right </caption>\n");
      out.write("    <tr><td > Cell A</td></tr>\n");
      out.write("    <tr><td > Cell B</td></tr>\n");
      out.write("    </table>\n");
      out.write("\n");
      out.write("    <style type=\"text/css\">\n");
      out.write("table.empty\n");
      out.write("{\n");
      out.write("    width:350px;\n");
      out.write("    border-collapse:separate;\n");
      out.write("    empty-cells:hide;\n");
      out.write("    }\n");
      out.write("td.empty\n");
      out.write("{\n");
      out.write("    padding:5px;\n");
      out.write("    border-style:solid;\n");
      out.write("    border-width:1px;\n");
      out.write("    border-color:#999999;\n");
      out.write("}\n");
      out.write("</style>\n");
      out.write("    <table class=\"empty\"> \n");
      out.write("    <tr> \n");
      out.write("    <th></th><th>Title one</th> <th>Title two</th>\n");
      out.write("    </tr>\n");
      out.write("    <tr> \n");
      out.write("    <th>Row Title</th> <td class=\"empty\">value</td> <td class=\"empty\">value</td>\n");
      out.write("    </tr>\n");
      out.write("    <tr> <th>Row Title</th> <td class=\"empty\">value</td> <td class=\"empty\"></td>\n");
      out.write("    </tr>\n");
      out.write("    </table>\n");
      out.write("    <style type=\"text/css\">\n");
      out.write("table.auto\n");
      out.write("{\n");
      out.write("    table-layout: auto\n");
      out.write("    }\n");
      out.write("table.fixed\n");
      out.write("{ table-layout: auto\n");
      out.write("}\n");
      out.write("</style>\n");
      out.write("<table class=\"auto\" border=\"1\" width=\"100%\">\n");
      out.write("<tr>\n");
      out.write("<td width=\"20%\">1000000000000000000000000000</td> <td width=\"40%\">10000000</td> <td width=\"40%\">100</td>\n");
      out.write("</tr>\n");
      out.write("</table> <br />\n");
      out.write("<table class=\"fixed\" border=\"1\" width=\"100%\">\n");
      out.write("<tr> <td width=\"20%\">1000000000000000000000000000</td> <td width=\"40%\">10000000</td> <td width=\"40%\">100</td>\n");
      out.write("</tr>\n");
      out.write("</table>\n");
      out.write("\n");
      out.write("<style type=\"text/css\">\n");
      out.write("p.example1\n");
      out.write("{ \n");
      out.write("    border:1px solid;\n");
      out.write("    border-bottom-color:#009900; /* Green */\n");
      out.write("    border-top-color:#FF0000; /* Red */\n");
      out.write("    border-left-color:#330000; /* Black */\n");
      out.write("    border-right-color:#0000CC; /* Blue */\n");
      out.write("}\n");
      out.write("p.example2\n");
      out.write("{ \n");
      out.write("    border:1px solid ;\n");
      out.write("    border-color:#009900; /* Green */\n");
      out.write("}\n");
      out.write("p.shorthandborder\n");
      out.write("{ \n");
      out.write("    border:1px dashed #0000CC;/* shorthand in blue color */\n");
      out.write("    /* Green */\n");
      out.write("}\n");
      out.write("</style>\n");
      out.write("<p class=\"example1\"> This example is showing all borders in different colors. </p>\n");
      out.write("<p class=\"example2\"> This example is showing all borders in green color only. </p>\n");
      out.write("<p class=\"shorthandborder\"> This example is showing shorthandborder in blue color only. </p>\n");
      out.write("\n");
      out.write("<p style=\"border-width:4px; border-style:none;\"> This is a border with none width. </p>\n");
      out.write("<p style=\"border-width:4px; border-style:solid;\"> This is a solid border. </p>\n");
      out.write("<p style=\"border-width:4px; border-style:dashed;\"> This is a dahsed border. </p>\n");
      out.write("\n");
      out.write("<p style=\"border-width:4px; border-style:double;\"> This is a double border. </p>\n");
      out.write("<p style=\"border-width:4px; border-style:groove;\"> This is a groove border. </p>\n");
      out.write("<p style=\"border-width:4px; border-style:ridge\"> This is a ridge border. </p>\n");
      out.write("<p style=\"border-width:4px; border-style:inset;\"> This is a inset border. </p>\n");
      out.write("<p style=\"border-width:4px; border-style:outset;\"> This is a outset border. </p>\n");
      out.write("<p style=\"border-width:4px; border-style:hidden;\"> This is a hidden border. </p>\n");
      out.write("<p style=\"border-width:4px; border-top-style:solid; border-bottom-style:dashed; border-left-style:groove; border-right-style:double;\"> This is a a border with four different styles. </p>\n");
      out.write("\n");
      out.write("<p style=\"border-width:4px; border-style:solid;\"> This is a solid border whose width is 4px. </p>\n");
      out.write("<p style=\"border-width:4pt; border-style:solid;\"> This is a solid border whose width is 4pt. </p>\n");
      out.write("<p style=\"border-width:thin; border-style:solid;\"> This is a solid border whose width is thin. </p>\n");
      out.write("<p style=\"border-width:medium; border-style:solid;\"> This is a solid border whose width is medium; </p>\n");
      out.write("<p style=\"border-width:thick; border-style:solid;\"> This is a solid border whose width is thick. </p>\n");
      out.write("<p style=\"border-bottom-width:4px; border-top-width:10px; border-left-width: 2px; border-right-width:15px; border-style:solid;\"> This is a a border with four different width. </p>\n");
      out.write("\n");
      out.write("\n");
      out.write("<div data-role=\"table\" style=\"background-image:url(resources/slideimage/snoc2.png);width:40%;background-position:100px 200px;\"> <tr><td> This table has background image positioned 100 pixels away from the left and 200 pixels from the top... </td></tr> </div>\n");
      out.write("           <p style=\"font-family:georgia,garamond,serif;\"> This text is rendered in either georgia, garamond, or the default serif font depending on which font you have at your system. </p>\n");
      out.write("           <p style=\"font-style:italic;\"> This text will be rendered in italic style </p>\n");
      out.write("           \n");
      out.write("           <p style=\"font-variant:small-caps;\"> This text will be rendered as small caps </p>\n");
      out.write("           <p style=\"font-weight:bold;\"> This font is bold. </p> <p style=\"font-weight:bolder;\"> This font is bolder. </p> <p style=\"font-weight:900;\"> This font is 900 weight. </p>\n");
      out.write("           <p style=\"font-stretch:ultra-expanded;\"> If this doesn't appear to work, it is likely that your computer doesn't have a condensed or expanded version of the font being used. </p>\n");
      out.write("           <p style=\"font:italic small-caps bold 15px georgia;\"> Applying all the properties on the text at once. </p>\n");
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

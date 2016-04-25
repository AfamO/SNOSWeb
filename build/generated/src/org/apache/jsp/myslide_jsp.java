package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class myslide_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>My slide</title>\n");
      out.write("        <link id='camera-css' href=\"resources/Style/camera.css\"  type='text/css' media='all' >\n");
      out.write("            <style>\n");
      out.write("\t\thtml,body {\n");
      out.write("\t\t\theight: 50%;\n");
      out.write("\t\t\tmargin: 0;\n");
      out.write("\t\t\tpadding: 0;\n");
      out.write("\t\t}\n");
      out.write("\t\ta {\n");
      out.write("\t\t\tcolor: #09f;\n");
      out.write("\t\t}\n");
      out.write("\t\ta:hover {\n");
      out.write("\t\t\ttext-decoration: none;\n");
      out.write("\t\t}\n");
      out.write("\t\t#back_to_camera {\n");
      out.write("\t\t\tbackground: rgba(255,255,255,.9);\n");
      out.write("\t\t\tclear: both;\n");
      out.write("\t\t\tdisplay: block;\n");
      out.write("\t\t\theight: 40px;\n");
      out.write("\t\t\tline-height: 40px;\n");
      out.write("\t\t\tpadding: 20px;\n");
      out.write("\t\t\tposition: relative;\n");
      out.write("\t\t\tz-index: 1;\n");
      out.write("\t\t}\n");
      out.write("\t\t.fluid_container {\n");
      out.write("\t\t\tbottom: 0;\n");
      out.write("\t\t\theight: 50%;\n");
      out.write("\t\t\tleft: 0;\n");
      out.write("\t\t\tposition: fixed;\n");
      out.write("\t\t\tright: 0;\n");
      out.write("\t\t\ttop: 0;\n");
      out.write("\t\t\tz-index: 0;\n");
      out.write("\t\t}\n");
      out.write("\t\t#camera_wrap_4 {\n");
      out.write("\t\t\tbottom: 0;\n");
      out.write("\t\t\theight: 50%;\n");
      out.write("\t\t\tleft: 0;\n");
      out.write("\t\t\tmargin-bottom: 0!important;\n");
      out.write("\t\t\tposition: fixed;\n");
      out.write("\t\t\tright: 0;\n");
      out.write("\t\t\ttop: 0;\n");
      out.write("\t\t}\n");
      out.write("\t\t.camera_bar {\n");
      out.write("\t\t\tz-index: 2;\n");
      out.write("\t\t}\n");
      out.write("\t\t.camera_thumbs {\n");
      out.write("\t\t\tmargin-top: -100px;\n");
      out.write("\t\t\tposition: relative;\n");
      out.write("\t\t\tz-index: 1;\n");
      out.write("\t\t}\n");
      out.write("\t\t.camera_thumbs_cont {\n");
      out.write("\t\t\tborder-radius: 0;\n");
      out.write("\t\t\t-moz-border-radius: 0;\n");
      out.write("\t\t\t-webkit-border-radius: 0;\n");
      out.write("\t\t}\n");
      out.write("\t\t.camera_overlayer {\n");
      out.write("\t\t\topacity: .1;\n");
      out.write("\t\t}\n");
      out.write("\t</style>\n");
      out.write("\n");
      out.write("<script src='resources/Script/jquery.min.js'></script>\n");
      out.write("<script src='resources/Script/jquery.mobile.customized.min.js'></script>\n");
      out.write("<script src=\"resources/Script/jquery.easing.1.3.js\" ></script>\n");
      out.write("<script src=\"resources/Script/camera.min.js\" ></script>\n");
      out.write("<script>\n");
      out.write("    jQuery(function(){\n");
      out.write("\n");
      out.write("\t\t\tjQuery('#camera_wrap_4').camera({\n");
      out.write("\t\t\t\theight: 'auto',\n");
      out.write("\t\t\t\tloader: 'bar',\n");
      out.write("\t\t\t\tpagination: false,\n");
      out.write("\t\t\t\tthumbnails: true,\n");
      out.write("\t\t\t\thover: false,\n");
      out.write("\t\t\t\topacityOnGrid: false,\n");
      out.write("\t\t\t\timagePath: '../images/'\n");
      out.write("\t\t\t});\n");
      out.write("\n");
      out.write("\t\t});\n");
      out.write("\n");
      out.write("</script>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("       \n");
      out.write("\n");
      out.write("        <div class=\"fluid_container\">\n");
      out.write("        <div class=\"camera_wrap camera_emboss pattern_1\" id=\"camera_wrap_4\">\n");
      out.write("            <div data-thumb=\"resources/images/bansnos.png\" data-src=\"resources/images/bansnos.png\">\n");
      out.write("            </div>\n");
      out.write("            <div data-thumb=\"resources/slideimage/banner1.png\" data-src=\"resources/slideimage/banner1.png\">\n");
      out.write("            </div>\n");
      out.write("            <div data-thumb=\"resources/slideimage/snoc2.png\" data-src=\"resources/slideimage/snoc2.png\">\n");
      out.write("            </div>\n");
      out.write("            <div data-thumb=\"resources/slideimage/banner3.png\" data-src=\"resources/slideimage/banner3.png\">\n");
      out.write("            </div>\n");
      out.write("            <div data-thumb=\"resources/slideimage/banner4.png\" data-src=\"resources/slideimage/banner4.png\">\n");
      out.write("            </div>\n");
      out.write("            <div data-thumb=\"resources/slideimage/snosimage1.png\" data-src=\"resources/slideimage/banner4.png\">\n");
      out.write("            </div>\n");
      out.write("        </div><!-- #camera_wrap_3 -->\n");
      out.write("\n");
      out.write("    </div><!-- .fluid_container -->\n");
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

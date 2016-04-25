package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import snossoftwaretest.*;

public final class EmailErrorPage_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(3);
    _jspx_dependants.add("/header2.jsp");
    _jspx_dependants.add("/control_panel.jsp");
    _jspx_dependants.add("/footer2.jsp");
  }

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_forEach_var_items;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_c_forEach_var_items = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_c_forEach_var_items.release();
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    Throwable exception = org.apache.jasper.runtime.JspRuntimeLibrary.getThrowable(request);
    if (exception != null) {
      response.setStatus((Integer)request.getAttribute("javax.servlet.error.status_code"));
    }
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
      out.write(" \r\n");
      out.write("\r\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 5.0 Transitional//EN\"\n");
      out.write("   \"http://www.w3.org/TR/html4/loose.dtd\">\n");
      out.write("\n");
      out.write("\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\" />\n");
      out.write("<title>:::SNOS:::</title>\n");
      out.write("<meta name=\"description\" content=\"Security website\">\n");
      out.write("<meta name=\"keywords\" content=\"Security, Monitoring,Alert\">\n");
      out.write("<link rel=\"shortcut icon\"  href=\"resources/images/favicon.ico\" type=\"image/x-icon\" />\n");
      out.write("<link href=\"resources/css/style.css\" rel=\"stylesheet\" type=\"text/css\">\n");
      out.write("<link href=\"resources/css/loginDisplayCSS.css\" rel=\"stylesheet\" type=\"text/css\">\n");
      out.write("<link href=\"resources/css/SlideCss.css\" rel=\"stylesheet\" type=\"text/css\">\n");
      out.write("<script type=\"text/javascript\" src=\"resources/Script/jquery-1.9.1.js\" ></script>\n");
      out.write("<script type=\"text/javascript\" src=\"resources/Script/script.js\" ></script>\n");
      out.write("<script type=\"text/javascript\"   src=\"resources/Script/Tele_script.js\" ></script>\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("</head>\n");
      out.write("\n");
      out.write("<body>\n");
      out.write("\n");
      out.write("<table width=\"80%\" height=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" bgcolor=\"white\">\n");
      out.write("  <tr>\n");
      out.write("    <td colspan=\"2\">\n");
      out.write("\t<table   cellspacing=\"0\" cellpadding=\"0\" class=\"tableborder\">\n");
      out.write("      <tr>\n");
      out.write("        <td width=\"1\">\n");
      out.write("        <div style=\"position:absolute; width:400px; top:15px; background:url('resources/images/cn-bg.gif'); left: 149px;\">\n");
      out.write("        <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n");
      out.write("  <tr>\n");
      out.write("    <td width=\"1\"><img  src=\"resources/images/logo.jpg\" alt=\"\" width=\"61\" height=\"61\" class=\"logo\"></td>\n");
      out.write("    <td class=\"company_name\">SECURITY NETWORK OPERATING SYSTEM</td>\n");
      out.write("  </tr>\n");
      out.write("</table>\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("        <div id=\"slogan\">\n");
      out.write("        SNOS<br/>...Securing Your World</div>\n");
      out.write("        <img  src=\"resources/images/bansnos.png\" alt=\"\" width=\"1100\" height=\"196\" class=\"tableborder\"></td>\n");
      out.write("\n");
      out.write("      </tr>\n");
      out.write("\n");
      out.write("\t  <tr>\n");
      out.write("  <td height=\"5\" >\n");
      out.write("\n");
      out.write("  </td></tr>\n");
      out.write("\n");
      out.write("  <tr>\n");
      out.write("  <td  class=\"Hyperlink\" valign=\"top\" colspan=\"2\"  align=\"center\" height=\"25\">\n");
      out.write("\n");
      out.write("<table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n");
      out.write("<tr><td  width=\"78\" align=\"center\"  id=\"menuHyperlink\" >\n");
      out.write("<a href=\"index.jsp\"><img src=\"resources/images/home.jpg\"  />&nbsp;Home</a>\n");
      out.write("</td>\n");
      out.write("<td  width=\"158\" align=\"center\"  id=\"menuHyperlink\">\n");
      out.write("<a href=\"about.jsp\"><img src=\"resources/images/arrow.jpg\"  />&nbsp;About snos</a></td>\n");
      out.write("\n");
      out.write("<td  width=\"97\" align=\"center\" id=\"menuHyperlink\">\n");
      out.write("<a href=\"services.jsp\"><img src=\"resources/images/arrow.jpg\"  />&nbsp;Services</a></td>\n");
      out.write("<td  width=\"130\" align=\"center\" id=\"menuHyperlink\">\n");
      out.write("<a href=\"contact.jsp\"><img src=\"resources/images/arrow.jpg\" />&nbsp;Contact us</a></td>\n");
      out.write("<td  width=\"219\" align=\"center\" id=\"menuHyperlink\">\n");
      out.write("<a href=\"support_client.jsp\"><img src=\"resources/images/arrow.jpg\" />&nbsp;Technical Support</a></td>\n");
      out.write("<td width=\"400\"   align=\"right\" >\n");
      out.write("  <span style=\" color:#FFF\">  <script language=\"javascript\">\n");
      out.write("        TeleTime();\n");
      out.write("</script></span>&nbsp;&nbsp;&nbsp;</td>\n");
      out.write("</tr>\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("  </table>\n");
      out.write("  </td>\n");
      out.write("  </tr>\n");
      out.write("\n");
      out.write("    </table></td>\n");
      out.write("  </tr>");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<tr>\r\n");
      out.write(" <td width=\"100%\" valign=\"top\" >\r\n");
      out.write("\r\n");
      out.write(" <table width=\"100%\"  cellspacing=\"0\" cellpadding=\"0\"  >\r\n");
      out.write("\r\n");
      out.write("  <tr>\r\n");
      out.write("\r\n");
      out.write("<td width=\"600\"  valign=\"top\">\r\n");
      out.write("    \r\n");
      out.write("<table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" align=\"center\">\r\n");
      out.write("    ");

    String name="New User";
    String stackTrace="";
    if(session.getAttribute("user")!=null)
        {
            name=session.getAttribute("user").toString();
        }
    
      out.write("\r\n");
      out.write("    ");
      if (_jspx_meth_c_forEach_0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("    <tr><td align=\"center\"><fieldset class=\"regFieldSet\"><legend><font FACE = \"tahoma\" SIZE = \"5\" COLOR =\"green\">");
      out.print(name  );
      out.write("'s Control Panel</font></legend>\r\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("   ");

   String url="";
   String menu="";
   url=request.getRequestURI();
   //out.print("<br> The URI is "+url);
  url=url.replace(request.getContextPath()+"/", "");
  Object nam=session.getAttribute("user");
  String log_out="\"return display('"+nam+"')\"";
 // String log_me_out="\"return display('"+nam+"')\"";
  //out.print("<br>Lastly The URI is "+url);
   //out.print("<br>The Context Path  is "+request.getContextPath());
   //out.print("<br>The New URI  is "+url.replace(request.getContextPath()+"/", " "));
   //public void GetControlPanel(String url)
           //{

       if(url.equals("user_register.jsp"))
            {
                menu="<a href='index.jsp'><font FACE = 'tahoma' SIZE = '4' COLOR ='blue'>Back to Home Page</font></a>&nbsp;&nbsp;" +
                     "<a href='contacts.jsp'><font FACE = 'tahoma' SIZE = '4' COLOR ='blue'>Contacts Us</font></a>&nbsp;&nbsp;" +
                     "<a href='about.jsp'><font FACE = 'tahoma' SIZE = '4' COLOR ='blue'>About Us</font></a>&nbsp;&nbsp;";
                     

            }
       else if(url.equals("device_reg.jsp"))
           {
                menu="<ul class='root'>"+"<li><a href='index.jsp'>Back to Home Page</a></li>" +
                      "<li><a href='user_register.jsp'>Back to Customer Registration Page</a></li>"
                       +"</ul>";
           }
           else if(url.equals("clients_contacts_reg.jsp"))
           {
               menu="<ul class='root'>"+"<li><a href='index.jsp'>Back to Home Page</a></li>" +
                      "<li><a href='user_register.jsp'>Back to Customer Registration Page</a></li>" +
                      "<li><a href='device_reg.jsp'>Back to Device Registration Page</a></li>" +
                     "<li><a href='contacts.jsp'>Contacts Us</a></li>" +
                     "<li><a href='about.jsp'>About Us</a></li>"
                     +"</ul>";
           }
       else if(url.equals("clients_contacts_update.jsp"))
           {
                menu="<ul class='root'>"+"<li><a href='servlets/RequestProcessing?action=sms&source=desktop'>SMS</a></li>" +
                     "<li><a href='servlets/RequestProcessing?action=my profile&source=desktop'>Personal Profile</a></li>" +
                     "<li><a href='servlets/RequestProcessing?action=_&source=desktop'>Actions</a></li>" +
                     "<li><a href='servlets/RequestProcessing?action=device_profile&source=desktop'>Device Profile</a></li>" +
                     "<li><a href='servlets/RequestProcessing?action=sms&source=desktop'>SMS</a></li>" +
                     "<li><a href='change_password.jsp'>Change Password</font></a></li>" +
                     "<li><a href='servlets/RequestProcessing?action=exit&source=desktop' onclick="+log_out+">Logg Off</a></li>"
                      +"</ul>";
           }
       else if(url.equals("user_update.jsp"))
            {
                menu="<ul class='root'>"+"<li><a href='servlets/RequestProcessing?action=_&source=desktop'>Actions</a></li>" +
                     "<li><a href='servlets/RequestProcessing?action=device_profile&source=desktop'>Device Profile</a></li>" +
                     "<li><a href='servlets/RequestProcessing?action=contacts_profile&source=desktop'>Contacts Profile></a></li>" +
                     "<li><a href='servlets/RequestProcessing?action=sms&source=desktop'>SMS</a></li>" +
                     "<li><a href='change_password.jsp'>Change Password</a></li>" +
                     "<li><a href='servlets/RequestProcessing?action=exit&source=desktop' onclick="+log_out+">Logg Off</a></li>"
                       +"</ul>";
           
            }
       else if(url.equals("device_update.jsp"))
           {
                menu="<ul class='root'>"+"<li><a href='servlets/RequestProcessing?action=_&source=desktop'>Actions</a></li>" +
                     "<li><a href='servlets/RequestProcessing?action=my profile&source=desktop'>Personal Profile</a></li>" +
                     "<li><a href='servlets/RequestProcessing?action=contacts_profile&source=desktop'>Contacts Profile</a></li>" +
                     "<li><a href='servlets/RequestProcessing?action=sms&source=desktop'>SMS</a></li>" +
                     "<li><a href='change_password.jsp'>Change Password</a></li>" +
                     "<li><a href='servlets/RequestProcessing?action=exit&source=desktop' onclick="+log_out+">Logg Off</a></li>"
                     +"</ul>";
           }
       else if(url.equals("view_sms.jsp"))
           {
                menu="<ul class='root'>"+"<li><a href='servlets/RequestProcessing?action=sms&source=desktop'>Inbox</a></li><li><a href='servlets/RequestProcessing?action=my profile&source=desktop'>Personal Profile</a></li>" +
                     "<li><a href='servlets/RequestProcessing?action=_&source=desktop'> Actions</a></li>" +
                     "<li><a href='servlets/RequestProcessing?action=device_profile&source=desktop'> Device Profile</a></li>" +
                     "<li><a href='servlets/RequestProcessing?action=contacts_profile&source=desktop'> Contacts Profile</font></a></li>" +
                     "<li><a href='change_password.jsp'> Change Password</a></li>" +
                     "<li><a href='servlets/RequestProcessing?action=exit&source=desktop' onclick="+log_out+" > Logg Off</a></li>"
                     +"</ul>";
           }
           else if(url.equals("action_page.jsp"))
           {
               menu="<ul class='root'>"+"<li><a href='servlets/RequestProcessing?action=my profile&source=desktop'>Personal Profile</a><li>" +
                   "<li><a href='servlets/RequestProcessing?action=sms&source=desktop'>SMS Page</a><li>" +
                   "<li><a href='servlets/RequestProcessing?action=device_profile&source=desktop'>Device Profile</a><li>" +
                   "<li><a href='servlets/RequestProcessing?action=contacts_profile&source=desktop'>Contacts Profile</a><li>" +
                   "<li><a href='change_password.jsp'>Change Password</a></li>" +
                   "<li><a href='servlets/RequestProcessing?action=exit&source=desktop' onclick="+log_out+">Logg Off</a></li>"
                   +"</ul>";
           }
           else if(url.equals("change_password.jsp"))
           {
               menu="<ul class='root'>"+"<li><a href='servlets/RequestProcessing?action=my profile&source=desktop'>Personal Profile</a><li>" +
                   "<li><a href='servlets/RequestProcessing?action=sms&source=desktop'>SMS Page</a><li>" +
                    "<li><a href='servlets/RequestProcessing?action=_&source=desktop'> Actions</a></li>" +
                   "<li><a href='servlets/RequestProcessing?action=device_profile&source=desktop'>Device Profile</a><li>" +
                   "<li><a href='servlets/RequestProcessing?action=contacts_profile&source=desktop'>Contacts Profile</a><li>" +
                   "<li><a href='servlets/RequestProcessing?action=exit&source=desktop' onclick="+log_out+">Logg Off</a></li>"
                   +"</ul>";
           }
       
           else if(url.equals("EmailErrorPage.jsp")||url.equals("FileNotFound.jsp"))
           {
               if(session.getAttribute("user")!=null)
                   {
                        menu="<a href='servlets/RequestProcessing?action=my profile&source=desktop'><font FACE = 'tahoma' SIZE = '4' COLOR ='blue'>Personal Profile</font></a>&nbsp;&nbsp;" +
                        "<a href='servlets/RequestProcessing?action=sms&source=desktop'><font FACE = 'tahoma' SIZE = '4' COLOR ='blue'>SMS Page</font></a>&nbsp;&nbsp;" +
                        "<a href='servlets/RequestProcessing?action=device_profile&source=desktop'><font FACE = 'tahoma' SIZE = '4' COLOR ='blue'>Device Profile</font></a>&nbsp;&nbsp;" +
                        "<a href='servlets/RequestProcessing?action=contacts_profile&source=desktop'><font FACE = 'tahoma' SIZE = '4' COLOR ='blue'>Contacts Profile</font></a>&nbsp;&nbsp;" +
                        "<a href='change_password.jsp'><font FACE = 'tahoma' SIZE = '4' COLOR ='blue'>Change Password</font></a>&nbsp;&nbsp;" +
                        "<a href='servlets/RequestProcessing?action=exit&source=desktop' onclick="+log_out+"><font FACE = 'tahoma' SIZE = '4' COLOR ='blue'>Logg Off</font></a>&nbsp;&nbsp;";
                   }
               else
                   {
                         menu="<a href='index.jsp'><font FACE = 'tahoma' SIZE = '4' COLOR ='blue'>Back to Home Page</font></a>&nbsp;&nbsp;" +
                     "<a href='contacts.jsp'><font FACE = 'tahoma' SIZE = '4' COLOR ='blue'>Contacts Us</font></a>&nbsp;&nbsp;" +
                      "<a href='user_register.jsp'><font FACE = 'tahoma' SIZE = '4' COLOR ='blue'>Not Yet a Member?Register</font></a>&nbsp;&nbsp;" +
                     "<a href='about.jsp'><font FACE = 'tahoma' SIZE = '4' COLOR ='blue'>About Us</font></a>&nbsp;&nbsp;";
                   }
                
           }
       
       else
           {
       }
  if(session.getAttribute("user")==null)
      {
      }
  else
      {
         out.print(menu);
      }
    
   
      out.write('\n');
      out.write('\n');
      out.write("\r\n");
      out.write("</fieldset></td></tr>\r\n");
      out.write("\r\n");
      out.write("<tr><td  width=\"100%\" style=\"padding-left:20px; padding-right:20px; padding-top:20px; padding-bottom:20px\">\r\n");
      out.write("<!--Registration codes  Starts here -->\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<div  id=\"RegistrationPageTable\">\r\n");
      out.write("\r\n");
      out.write("<table width=\"100%\" height=\"\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" class=\"tabborder\" >\r\n");
      out.write("<tr>\r\n");
      out.write("     <td width=\"400\" height=\"30\" bgcolor=\" \"  align=\"center\" colspan=\"2\">\r\n");
      out.write("     <b><font face=\"Times New Roman\" size=\"5\" color=\"blue\">ERROR PAGE</font></b></td>\r\n");
      out.write("</tr>\r\n");
      out.write("<tr><td height=\"5\">\r\n");
      out.write("&nbsp;&nbsp;\r\n");
      out.write("</td></tr>\r\n");
      out.write("\r\n");
      out.write("\r\n");

        String to=application.getInitParameter("admin_email");
         String from=application.getInitParameter("siteadmin_email");
         String adminpass=application.getInitParameter("admin_emailpassword");
        String Subject="[Application Error]";
        String debug="";
        Integer status_code=(Integer)request.getAttribute("javax.servlet.error.status_code");
        if(status_code!=null)
            {
                debug+="Status Code: "+status_code.toString()+"\n";
            }

        Class exception_type=(Class)request.getAttribute("javax.servlet.error.exception_type");
        if(exception_type!=null)
            {
                debug+="Exception_type: "+exception_type.toString()+"\n";
            }
        
         String m=(String)request.getAttribute("javax.servlet.error.message");
        if(m!=null)
            {
                debug+="Message: "+m.toString()+"\n";
            }
         Throwable e=(Throwable)request.getAttribute("javax.servlet.error.exception");
        if(e!=null)
            {
                
                debug+="Exception: "+e.toString()+"\n";
                debug+="Stack Trace:::\n ";
            }
         String request_uri=(String)request.getAttribute("javax.servlet.error.request_uri");
        if( request_uri!=null)
            {
                debug+="Requested_uri: "+request_uri.toString()+"\n";
            }
         String servlet_name=(String)request.getAttribute("javax.servlet.error.servlet_name");
        if(servlet_name!=null)
            {
                debug+="Servlet_name: "+servlet_name.toString()+"\n";
            }
         if(session.getAttribute("admin_error")!=null){
               Subject="[Application Error:Internal Application Error]";
             debug+="Admin Error type:"+session.getAttribute("admin_error").toString();
             }
         else
             {
              Subject="[Application Error:Routine Error]";
             debug+="Error that came from the routinary use of the application";
             }
         //Logger.getLogger(RequestProcessing.class.getName()).log(Level.SEVERE, null, ex);
         new SnosSendEmail().SNOSemailSendToOne(from, adminpass,Subject,debug+"\nThank You\n\n\nFrom Snos Team!",to);

         
        
      out.write("\r\n");
      out.write("        \r\n");
      out.write("\r\n");
      out.write("<tr>\r\n");
      out.write("\t\t<td width=\"500\" valign=\"top\" align=\"center\">\r\n");
      out.write("\r\n");
      out.write("\t\t<fieldset style=\"width:450px; vertical-align:top; color:blue;\">\r\n");
      out.write("\t\t<legend  >My Error Page </legend>\r\n");
      out.write("\t\t <center>\r\n");
      out.write("         <h3>An error has occurred.</h3>\r\n");
      out.write("        <p>Your request was not processed,hence this site<br>\r\n");
      out.write("        is unavailable to process your request.The error info has been sent<br>  to the Administrator at\r\n");
      out.write("        <i>");
      out.print( to );
      out.write("</i> to describe the error and reply immediately.<br>\r\n");
      out.write("          Please do bear with us,we shall surely get back to you immediately<br>\r\n");
      out.write("            as we are only interested in serving you better;that is to the optimum and the<br>\r\n");
      out.write("                best of our abilities.<br><br>\r\n");
      out.write("                h2>\r\n");
      out.write("          <br/>\r\n");
      out.write("    </h2>\r\n");
      out.write(" \r\n");
      out.write("    \r\n");
      out.write("     \r\n");
      out.write("    \r\n");
      out.write("                    ");
      out.write("\r\n");
      out.write("\r\n");
      out.write("            Thank You.\r\n");
      out.write("         </p>\r\n");
      out.write("         </center>\r\n");
      out.write("\t\t</fieldset>\t </td>\r\n");
      out.write("        </tr>\r\n");
      out.write("</table>\r\n");
      out.write("\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("</td>\r\n");
      out.write("\r\n");
      out.write("</tr>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<tr>\r\n");
      out.write("<td>\r\n");
      out.write("<table width=\"100%\" height=\"100%\" align=\"center\">\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("</table>\r\n");
      out.write("</td>\r\n");
      out.write("</tr>\r\n");
      out.write("</table>\r\n");
      out.write("</td>\r\n");
      out.write("\r\n");
      out.write("<td  width=\"250\" valign=\"top\" class=\"mbg\">\r\n");
      out.write("\r\n");
      out.write("<table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"  style=\"margin-top:5px; margin-bottom:5px;\" >\r\n");
      out.write("<tr><td height=\"10\" align=\"center\">\r\n");
      out.write("<div>\r\n");
      out.write("<input type=\"text\" name=\"web\" size=\"40\" placeholder=\"Search From Web\" />\r\n");
      out.write("</div>\r\n");
      out.write("</td></tr>\r\n");
      out.write("</table>\r\n");
      out.write("\r\n");
      out.write("<table cellpadding=\"0\" cellspacing=\"0\" align=\"center\" width=\"100%\">\r\n");
      out.write("\r\n");
      out.write("<tr>\r\n");
      out.write("  <td height=\"20\" bgcolor=\"#00CC99\" align=\"center\" style=\"padding: 1px; border-bottom-style:solid; border-bottom-width:2px; border-right-style:none; color:#F0F; border-right-width:medium\" colspan=\"3\">\r\n");
      out.write("  <font color=\"blue\" size=\"3\" face=\"Georgia, Times New Roman, Times, serif\">* Get Latest SMS To Email \r\n");
      out.write("  <img  src=\"resources/images/alert.gif\"  height=\"20\" align=\"absmiddle\"/></font>\r\n");
      out.write("</td>\r\n");
      out.write("</tr>\r\n");
      out.write("\r\n");
      out.write("<tr><td  width=\"100%\" align=\"center\">\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<div  id=\"subscribeformContainer\">\r\n");
      out.write("\r\n");
      out.write("\t\t\t<form   method=\"post\" action=\"system_sms.php\" name=\"form2\">\r\n");
      out.write("\r\n");
      out.write("  <input type=\"text\" name=\"sea\"  size=\"30\" maxlength=\"50\" id=\"sub\" placeholder=\"Enter E-mail\"  class=\"tabborder\" /><br>\r\n");
      out.write("\r\n");
      out.write("<input type=\"submit\" name=\"subB\" value=\"Subscribe\"  class=\"buttonDisplay\"/>\r\n");
      out.write("\r\n");
      out.write("\t\t\t</form>\r\n");
      out.write("\r\n");
      out.write("\t\t</div>\r\n");
      out.write("</td></tr>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<tr><td height=\"10\" align=\"center\"  bgcolor=\"#00CC99\" width=\"100%\" style=\"padding: 1px; border-bottom-style:solid; border-bottom-width:2px; border-right-style:none; color:#F0F; border-right-width:medium\">\r\n");
      out.write(" \r\n");
      out.write("  </td></tr>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<tr><td  align=\"center\" >\r\n");
      out.write("<a href=\"#\"><img src=\"resources/slideimage/snosimage1.png\"  class=\"tableborder\"  style=\"margin-top:10px;\"></a>\r\n");
      out.write("\r\n");
      out.write("</td></tr>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<tr><td  align=\"center\" >\r\n");
      out.write("<a href=\"#\"><img  src=\"resources/images/platformsnos2.gif\" class=\"Imagedisplay\"  style=\"margin-top:10px;\" ></a>\r\n");
      out.write("\r\n");
      out.write("</td></tr>\r\n");
      out.write("\r\n");
      out.write("<tr><td height=\"5\" >\r\n");
      out.write("  <p>&nbsp;&nbsp;&nbsp;</p>\r\n");
      out.write("  </td></tr>\r\n");
      out.write("</table>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("</td>\r\n");
      out.write("</tr>\r\n");
      out.write("</table>\r\n");
      out.write("</td></tr>\r\n");
      out.write("\r\n");
      out.write("\n");
      out.write("\n");
      out.write(" <tr>\n");
      out.write("    <td bgcolor=\"#0b7ca9\" class=\"bot-bg\" colspan=\"2\"><table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" >\n");
      out.write("      <tr>\n");
      out.write("        <td class=\"bottom_menu\"><a href=\"index.jsp\">Home</a> | <a href=\"about.jsp\">About SNOS</a> | <a href=\"services.jsp\">Services</a> | <a href=\"user_register.jsp\">Sign up</a> | <a href=\"index.jsp\">Login</a> | <a href=\"contact.jsp\">Contact Us</a>  </td>\n");
      out.write("      </tr>\n");
      out.write("      <tr>\n");
      out.write("        <td class=\"bottom_addr\">&copy; 2013 Teledominternational Ltd</td>\n");
      out.write("      </tr>\n");
      out.write("    </table></td>\n");
      out.write("  </tr>\n");
      out.write("</table>\n");
      out.write("\n");
      out.write("</body>\n");
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

  private boolean _jspx_meth_c_forEach_0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:forEach
    org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_forEach_0 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _jspx_tagPool_c_forEach_var_items.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
    _jspx_th_c_forEach_0.setPageContext(_jspx_page_context);
    _jspx_th_c_forEach_0.setParent(null);
    _jspx_th_c_forEach_0.setVar("trace");
    _jspx_th_c_forEach_0.setItems((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${pageContext.exception.stackTrace}", java.lang.Object.class, (PageContext)_jspx_page_context, null));
    int[] _jspx_push_body_count_c_forEach_0 = new int[] { 0 };
    try {
      int _jspx_eval_c_forEach_0 = _jspx_th_c_forEach_0.doStartTag();
      if (_jspx_eval_c_forEach_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\r\n");
          out.write("      stackTrace+=");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${trace}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("<br/>\r\n");
          out.write("    ");
          int evalDoAfterBody = _jspx_th_c_forEach_0.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_forEach_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (Throwable _jspx_exception) {
      while (_jspx_push_body_count_c_forEach_0[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_c_forEach_0.doCatch(_jspx_exception);
    } finally {
      _jspx_th_c_forEach_0.doFinally();
      _jspx_tagPool_c_forEach_var_items.reuse(_jspx_th_c_forEach_0);
    }
    return false;
  }
}

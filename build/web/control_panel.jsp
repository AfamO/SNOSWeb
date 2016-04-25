<%-- 
    Document   : index
    Created on : Apr 15, 2013, 2:30:26 PM
    Author     : Admin
--%>


   <%
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
    
   %>


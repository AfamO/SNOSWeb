<%--
    Document   : device_information
    Created on : Apr 16, 2013, 3:29:24 PM
    Author     : Admin
--%>

<%@page import="java.util.logging.Logger"%>
<%@page import="java.util.logging.Level"%>
<%@page contentType="text/html" pageEncoding="UTF-8" import="snossoftwaretest.*"%>
<%@ page isErrorPage="true" %> 
<%@include  file="header2.jsp" %>


<tr>
 <td width="100%" valign="top" >
 
 <table width="100%"  cellspacing="0" cellpadding="0"  >
	    
  <tr>

<td width="600"  valign="top">
<table cellpadding="0" cellspacing="0" width="100%" align="center">
    <%
    String name="New User";
    if(session.getAttribute("user")!=null)
        {
            name=session.getAttribute("user").toString();
        }
    %>
    <tr><fieldset><legend><font FACE = "tahoma" SIZE = "5" COLOR ="green"><%=name  %>'s Control Panel</font></legend>
<%@include file="control_panel.jsp" %>
</fieldset></tr>

<tr><td  width="100%" style="padding-left:20px; padding-right:20px; padding-top:10px; padding-bottom:20px">
<!--Registration codes  Starts here  request_uri.toString() -->


<div  id="TableActionPageContainer">

<table width="100%" height="" cellpadding="0" cellspacing="0" align="center" id="RegistrationPageTable">
<tr>
     <td width="400" height="30" bgcolor=" "  align="center" colspan="2">
     <b><img src="resources/image/using image/sign.gif" width="70" height="30"  align="absmiddle"/>&nbsp;&nbsp;<font face="Times New Roman" size="5" color="blue">
      RESOURCE NOT FOUND</font></b></td>
</tr>
<tr><td height="5">
&nbsp;&nbsp;
</td></tr>


<tr>
		<td width="357">

		<fieldset>
        <legend class="style93">Error Reporting Page </legend>

<center>
    <%
    String request_uri=(String)request.getAttribute("javax.servlet.error.request_uri");
    String to=application.getInitParameter("admin_email");
         String from=application.getInitParameter("siteadmin_email");
         String adminpass=application.getInitParameter("admin_emailpassword");
        String Subject="[Application Error-FILE NOT FOUND]";
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
         Exception e=(Exception)request.getAttribute("javax.servlet.error.exception");
        if(e!=null)
            {
                
                debug+="Exception: "+e.toString()+"\n";
                debug+="Stack Trace:::\n "+e.getStackTrace()+"\n";
                
            }
         
        if( request_uri!=null)
            {
                debug+="Requested_uri: "+request_uri.toString()+"\n";
            }
         String servlet_name=(String)request.getAttribute("javax.servlet.error.servlet_name");
        if(servlet_name!=null)
            {
                debug+="Servlet_name: "+servlet_name.toString()+"\n";
            }
         
             debug+="THE URI "+request_uri+" was not found on the website.";
             
         //Logger.getLogger(RequestProcessing.class.getName()).log(Level.SEVERE, null, e);
         //send email concerning the file missings.
         //new SnosSendEmail().SNOSemailSendToOne(from, adminpass,Subject,debug+"\nThank You\n\n\nFrom Snos Team!",to);

    %>

<h2 class="style93">Bad URL!</h2>
<strong class='style105'>Sorry, the resource<%//= %> you are trying to reach at <b><%= request.getHeader("host") %><%= request.getContextPath() %>/...</b> does not exist<br>
        in this server.</strong>
        <h3 id="likelyCauses" class="style93">Most likely causes:</h3>
                    <ul>
                        <li id="causeNotConnected" class='style105'>The address does not exist.</li>
                        <li id="causeSiteProblem" class='style105'>The website is encountering problems.</li>
                        <li id="causeErrorInAddress" class='style105'>There might be a typing error in the address.</li>
                    </ul>

 </center>

		</fieldset>	 </td>
        </tr>
</table>

</div>


</td>

</tr>


<tr>
<td>
<table width="100%" height="100%" align="center">


</table>
</td>
</tr>
</table>
</td>


<td  class="mbg" width="250" valign="top">

<table cellpadding="0" cellspacing="0" width="100%"  style="margin-top:5px; margin-bottom:5px;" >
<tr><td height="10" align="center">
<div>
<input type="text" name="web" size="40" placeholder="Search From Web" />
</div>
</td></tr>
</table>

<table cellpadding="0" cellspacing="0" align="center" width="100%">

<tr>
  <td height="20" bgcolor="#00CC99" align="center" style="padding: 1px; border-bottom-style:solid; border-bottom-width:2px; border-right-style:none; color:#F0F; border-right-width:medium" colspan="3">
  <font color="blue" size="3" face="Georgia, Times New Roman, Times, serif">* Get Latest SMS To Email 
  <img  src="resources/images/alert.gif"  height="20" align="absmiddle"/></font>
</td>
</tr>

<tr><td  width="100%" align="center">


<div  id="subscribeformContainer">

			<form   method="post" action="system_sms.php" name="form2">

  <input type="text" name="sea"  size="30" maxlength="50" id="sub" placeholder="Enter E-mail"  class="tabborder" /><br>

<input type="submit" name="subB" value="Subscribe"  class="buttonDisplay"/>

			</form>

		</div>
</td></tr>


<tr><td height="10" align="center"  bgcolor="#00CC99" width="100%" style="padding: 1px; border-bottom-style:solid; border-bottom-width:2px; border-right-style:none; color:#F0F; border-right-width:medium">
 
  </td></tr>


<tr><td  align="center" >
<a href="#"><img src="resources/slideimage/snosimage1.png"  class="tableborder"  style="margin-top:10px;"></a>

</td></tr>


<tr><td  align="center" >
<a href="#"><img  src="resources/images/platformsnos2.gif" class="Imagedisplay"  style="margin-top:10px;" ></a>

</td></tr>

<tr><td height="5" >
  <p>&nbsp;&nbsp;&nbsp;</p>
  </td></tr>
</table>


</td>
</tr>
</table>
</td></tr>

<%@include  file="footer2.jsp" %>
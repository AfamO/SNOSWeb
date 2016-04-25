<%--
    Document   : device_information
    Created on : Apr 16, 2013, 3:29:24 PM
    Author     : Admin
--%>
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
    String stackTrace="";
    if(session.getAttribute("user")!=null)
        {
            name=session.getAttribute("user").toString();
        }
    %>
    <tr><td align="center"><fieldset class="regFieldSet"><legend><font FACE = "tahoma" SIZE = "5" COLOR ="green"><%=name  %>'s Control Panel</font></legend>
<%@include file="control_panel.jsp" %>
</fieldset></td></tr>

<tr><td  width="100%" style="padding-left:20px; padding-right:20px; padding-top:20px; padding-bottom:20px">
<!--Registration codes  Starts here -->


<div  id="RegistrationPageTable">

<table width="100%" height="" cellpadding="0" cellspacing="0" align="center" class="tabborder" >
<tr>
     <td width="400" height="30" bgcolor=" "  align="center" colspan="2">
     <b><font face="Times New Roman" size="5" color="blue">ERROR PAGE</font></b></td>
</tr>
<tr><td height="5">
&nbsp;&nbsp;
</td></tr>


<%
    
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
         if(session.getAttribute("admin_error")!=null)
         {
               Subject="[Application Error:Internal Application Error]";
               debug+="Admin Error type:"+session.getAttribute("admin_error").toString();
         }
         else
             {
              Subject="[Application Error:Routine Error]";
              debug+="Error that came from the routinary use of the application\n";
             }
          Throwable e=(Throwable)request.getAttribute("javax.servlet.error.exception");
        if(e!=null)
            {
                
                
                StackTraceElement []se=e.getStackTrace();
                for(int i=0;i<se.length;i++)
                {
                    stackTrace+=se[i].toString()+"\n";
                }
                debug+="\n\nStack Trace:::\n\n\n"+stackTrace;
            }
         //Logger.getLogger(RequestProcessing.class.getName()).log(Level.SEVERE, null, ex);
         new SnosSendEmail().SNOSemailSendToOne(from, adminpass,Subject,debug+"\nThank You\n\n\nFrom Snos Team!",to);

         
        %>
        

<tr>
		<td width="500" valign="top" align="center">

		<fieldset style="width:450px; vertical-align:top; color:blue;">
		<legend  >My Error Page </legend>
		 <center>
         <h3>An error has occurred.</h3>
        <p>Your request was not processed,hence this site<br>
        is unavailable to process your request.The error info has been sent<br>  to the Administrator at
        <i><%= to %></i> to describe the error and reply immediately.<br>
          Please do bear with us,we shall surely get back to you immediately<br>
            as we are only interested in serving you better;that is to the optimum and the<br>
                best of our abilities.<br><br>
               
          <br/>
    
 
    
     
    
                    <%--
    Document   : device_information
    Created on : Apr 16, 2013, 3:29:24 PM
    Author     : Admin
    the cause is <%= debug %>
    The cause is:<%=m%>
--%>

            Thank You.
         </p>
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

<td  width="250" valign="top" class="mbg">

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
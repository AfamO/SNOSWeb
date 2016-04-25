<%-- 
    Document   : clients_contacts
    Created on : Apr 16, 2013, 2:32:24 PM
    Author     : Admin
--%>
<%@include  file="header2.jsp" %>


<tr>
 <td width="100%" valign="top" >
 
 <table width="100%"  cellspacing="0" cellpadding="0"  >
	    
  <tr>
  
  
  
  
  <td  class="mbg" width="250" valign="top">


<table cellpadding="0" cellspacing="0" align="center" width="100%">

<tr><td align="center"><fieldset class="smsFieldSet"><legend><font FACE = "tahoma" SIZE = "3" COLOR ="green"><%=session.getAttribute("user")  %>'s Control Panel</font></legend>
<%@include file="control_panel.jsp" %>
</fieldset></td></tr>




<tr><td  align="center" >
<a href="#"><img src="resources/slideimage/snosimage1.png"  class="tableborder"  style="margin-top:10px; width:250px;"></a>

</td></tr>


<tr><td  align="center" >
<a href="#"><img  src="resources/images/Helpsnos.png" class="tableborder"  style="margin-top:10px; width:250px" ></a>

</td></tr>

<tr><td height="5" >
  <p>&nbsp;&nbsp;&nbsp;</p>
  </td></tr>
</table>



</td>


<td width="600"  valign="top">


<table cellpadding="0" cellspacing="0" width="100%" align="center">

<tr><td  width="100%" style="padding-left:20px; padding-right:20px; padding-top:10px; padding-bottom:20px">
<!--Registration codes  Starts here -->


<div id="RegistrationPageTable">
<table width="100%" height="" cellpadding="0" cellspacing="0" align="center" >
<tr>
     <td width="400" height="30" bgcolor=" "  align="center" colspan="2">
     <b><img src="resources/images/sign.gif" width="70" height="30"  align="absmiddle"/>&nbsp;&nbsp;<font face="Times New Roman" size="5" color="blue">
     Contacts Registration</font></b></td>
</tr>
<tr>
     <td width="400" height="30" bgcolor=" "  align="center" colspan="2">
     <font face="Times New Roman" size="2" color="red">
     Note that the fields that are marked with * are compulsory</font></td>
</tr>
<tr>
     <td width="400" height="30" bgcolor=" "  align="center" colspan="2">
     <font face="Times New Roman" size="3" color="blue">
     Please do fill in the details of your contacts-those whom you also wish to notify of any security breaches- here.</font></td>
</tr>
     <tr>
     <td width="400" height="30" bgcolor=" "  align="center" colspan="2">
     <font face="Times New Roman" size="3" color="red">
     ${errmsg}${status}</font></td>
</tr>

<%session.removeAttribute("errmsg"); %>
<%session.removeAttribute("status"); %>
<tr>

     <td width="400" height="10"  align="center" colspan="2">
     <div align="center" id="errmsg"> </div></td>
</tr>


<% 
 if(!session.getAttribute("msg").equals(""))
    {
        //display congratulatory message after clicking email link sent to the client for
        // registration comfirmation.
        out.println(session.getAttribute("msg"));
    }
    else
    {
        //simply display the html form-stored in session property 'info'- for 3rd stage of registration
        out.println(session.getAttribute("info"));
    }
 //session.invalidate();
 %>
</table>
</div>

</td>

</tr>

</table>
</td>




</table>
</td>
</tr>


<%@include  file="footer2.jsp" %>
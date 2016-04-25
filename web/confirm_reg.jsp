<%--
    Document   : device_information
    Created on : Apr 16, 2013, 3:29:24 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include  file="header2.jsp" %>


<tr>
 <td width="100%" valign="top" >
 
 <table width="100%"  cellspacing="0" cellpadding="0"  >
<tr>



<td  class="mbg" width="250" valign="top">


<table cellpadding="0" cellspacing="0" align="center" width="100%">

<tr><td align="center"><fieldset class="smsFieldSet"><legend><font FACE = "tahoma" SIZE = "2" COLOR ="green"><%=session.getAttribute("user")  %>'s Control Panel</font></legend>
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


<div  id="RegistrationPageTable">

<table width="100%" height="" cellpadding="0" cellspacing="0" align="center" class="tabborder" style="padding-left:20px; padding-right:20px; padding-top:10px; padding-bottom:20px">
<tr>
     <td width="400" height="30" bgcolor=" "  align="center" colspan="2" valign="top">
     <b><img src="resources/images/sign.gif" width="70" height="30"  align="absmiddle"/>&nbsp;&nbsp;<font face="Times New Roman" size="5" color="blue">
      Registration Confirmation</font></b></td>
</tr>
<tr><td height="5">
&nbsp;&nbsp;
</td></tr>


<tr>
		<td width="500" align="center" valign="top">
		<fieldset style="width:500px; vertical-align:top; color:blue;">
        <legend >Comfimation of Registration </legend>

<center>
<% String msg="";
if(session.getAttribute("err").equals("1"))
               {
           msg=" Your registration has been finalized at this moment.<br>You will have to go back to the home page to re-login so that you can start using our services..<br>";
       }
else
    {
        msg=" We are sorry :But our record shows that your registration has already been confirmed.You do not need to be re-confirmed the second time.<br>";

}
%>
<h2 >CONGRATULATIONS!</h2>
Thank you <strong><%=session.getAttribute("user") %></strong> for registering at this site.<br>
      <%out.println(msg);%>
       Thank You.<br>
           <br><br><br>Wants to go back to the home page?<a href='index.jsp'><font face='Times New Roman' size='5' color='blue'>Click Me</font></a>

 </center>

		</fieldset>		</td></tr>
</table>

</div>


</td>

</tr>

</table>
</td>

</tr>
</table>
</td>
</tr>

<%@include  file="footer2.jsp" %>
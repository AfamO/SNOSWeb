<%-- 
    Document   : changePassword
    Created on : May 13, 2013, 5:08:21 AM
    Author     : Charles
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false"%>

<%@include  file="header.jsp" %>


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
   

<tr><td  width="100%" >
<!--Registration codes  Starts here -->
<div id="RegistrationPageTable">
<table width="100%" height="" cellpadding="0" cellspacing="0" align="center"  class="tabborder">
<!..... code starts here...!>
<tr>
     <td width="400" height="10" bgcolor=" "  align="center" colspan="2">
     <b>&nbsp;&nbsp;<font face="Times New Roman" size="5" color="blue">
    Change My Password</font></b></td>
</tr>
<tr>
     <td width="400" height="10" bgcolor=" "  align="center" colspan="2">
     <font face="Times New Roman" size="2" color="red">
     Note that the fields that are marked with * are compulsory  AND THAT YOU WILL BE RE-DIRECTED TO HOME PAGE TO RE-LOGIIN ONCE THE OPERATION IS SUCCESSFULLY CARRIED OUT.</font></td>
</tr>
<tr>
     <td width="400" height="30" bgcolor=" "  align="center" colspan="2">
     <font face="Times New Roman" size="3" color="red">
     ${errmsg}${status}</font></td>
</tr>

<tr>

     <td width="400" height="10"  align="center" colspan="2">
     <div align="center" id="errmsg"> </div></td>
</tr>

<%  session.removeAttribute("errmsg"); %>

<form onsubmit="return ChangePassWordFormValidation();" method="POST"  action="servlets/RequestProcessing"  name="form5" >
<tr><td align="center" width = "100%"  height= "5" ><font color="blue" size="3" face="Times New Roman">Old Password:</font><span style="color:#FF0000">*</span>
<br /><input type="password" size = "30" name = "opass" maxlength="50"></td>
</tr>
<tr ><td align="center" width = "100%"  height= "5" ><font color="blue" size="3" face="Times New Roman"> New Password:</font><span style="color:#FF0000">*</span>
   <input type="password" size = "30" name = "npass" maxlength="50" style="margin-right:30px;">

</td></tr>
<tr><td align="center" width = "100%"  height= "5" ><font color="blue" size="3" face="Times New Roman"> Verify New Password:</font><span style="color:#FF0000">*</span>
   <input type="password" size = "30" name = "vnpass" maxlength="50" style="margin-right:30px;">

</td></tr>
<tr><td align="center" width = "100%"  height= "20" colspan="2" >



<input type="submit" value="Change My Password" name="action"  class="buttonDisplay" >
 &nbsp; <input type="reset" value="Reset" name="can" class="buttonDisplay" >
<input type="hidden" value="change_pass" name="id" >
    <input type="hidden" value="desktop" name="source" >

</td></tr>
</form>


<tr>
      <td td align="center" width = "100%"  height= "20" colspan="2" >
     <font color='#FF0000'>  </font></td>
    </tr>
 </table>  
</div>


<!..... code ends here...!>
</td>  


<td class="mbg" width="250" valign="top" >
	 <table width="250"  cellspacing="0" cellpadding="0" class="subscribeborder"  align="center">
	 <tr>
<td height="20" bgcolor="#00CC99" align="center" style="padding:1px; border-bottom-style:solid; border-bottom-width:2px; border-right-style:none; color:#F0F; border-right-width:medium" colspan="3">
	 <font color="blue" size="3" face="Georgia, Times New Roman, Times, serif">* Get Latest SMS To Email 
	 <img  src="resources/images/alert.gif" height="20" /></font>
</td></tr>

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
</td>
</tr>


<%@include file="footer.jsp" %>
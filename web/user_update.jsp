<%--
    Document   : process_register
    Created on : Apr 16, 2013, 12:34:28 PM
    Author     : Admin
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
<div  id="RegistrationPageTable">

<table width="100%" height="" cellpadding="0" cellspacing="0" align="center" style="padding-left:20px; padding-right:20px; padding-top:10px; padding-bottom:20px" class="tabborder" >
<tr>
     <td width="400" height="30" bgcolor=" "  align="center" colspan="2">
     <b><img src="resources/images/sign.gif" width="70" height="30"  align="absmiddle"/>&nbsp;&nbsp;<font face="Times New Roman" size="5" color="blue">
    My Personal Profile/Update</font></b></td>
</tr>
<tr>
     <td width="400" height="30" bgcolor=" "  align="center" colspan="2">
     <font face="Times New Roman" size="3" color="red">
     ${errmsg}</font></td>
</tr>
<%  session.removeAttribute("errmsg"); %>
<form onsubmit="return UpdateFormValidation();"   method="POST"  action="servlets/RequestProcessing"  name="form1" >
   <tr><td   align="center" width = "100%"  height= "20">
       <font face= "Times New Roman" size = "3" color="blue">Name:</font><span style="color:#FF0000">*</span><br /><input type="text" size = "30" name = "name" maxlength="50" placeholder="Name" value="${val[0]}"></td><td  align="center" width = "100%"  height= "20" >
<font face= "Times New Roman" size = "3" color="blue">Mobile Number:</font><span style="color:#FF0000">*</span>
<br /><input type="text" disabled="true"  size = "30" value="${val[2]}" name = "gsm" maxlength="50"></td></tr>


<tr><td align="center" width = "100%"  height= "20" ><font face= "Times New Roman" size = "3" color="blue">E-mail:</font><span style="color:#FF0000">*</span>
<input type="text" size = "30" name = "email1" value="${val[3]}" maxlength="50" style="margin-right:30px;" ></td>
<td  align="center" width = "100%"  height= "20" >
<font face= "Times New Roman" size = "3" color="blue">Address:</font><span style="color:#FF0000">*</span>
<br /><textarea cols="20" rows="4" name="address"  >${val[4]} </textarea></td></tr>

<tr><td  align="center" width = "100%"  height= "20" >
<font face= "Times New Roman" size = "3" color="blue">State:</font><span style="color:#FF0000">*</span>
<br /><input type="text" size = "30" value="${val[6]}" name = "state" maxlength="50" > </td> <td align="center" width = "100%"  height= "20" ><font face= "Times New Roman" size = "3" color="blue">LGA:</font><span style="color:#FF0000">*</span>
<input type="text" size = "30" value="${val[5]}" name = "lga" maxlength="50" style="margin-right:30px;">
</td></tr>



<tr><td height="5">
&nbsp;&nbsp;
</td></tr>

<tr><td align="center" width = "100%"  height= "20" colspan="2" >
<input type="submit" value="Update Profile" name="action" id="reg1" class="buttonDisplay">
 &nbsp; <input type="reset" value="Reset" name="can" class="buttonDisplay">
 <input type="hidden" value="reg1" name="id" >
  <input type="hidden" value="desktop" name="source" >
</td></tr> </form>

<tr>
      <td  align="center" width = "100%"  height= "20" colspan="2" >
     <font color='#FF0000'>  </font></td>
    </tr>

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


<%@include  file="footer.jsp" %>
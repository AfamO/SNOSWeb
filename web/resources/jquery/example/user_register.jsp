package resources.jquery.example;

<%--
    Document   : process_register
    Created on : Apr 16, 2013, 12:34:28 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>:::SNOS_PROJECT:::</title>


 <link rel="stylesheet" href="resources/Style/SnosCssDesign.css" />
 <link  rel="stylesheet"  href="resources/Style/Registration_Action_pageCss.css" />

<script  src="resources/Scripts/jquery-1.9.1.js"></script>

<script  src="resources/Scripts/script.js"></script>
<script src="resources/Scripts/Tele_script.js" type="text/javascript"></script>



</head>

<body >
<table cellpadding="0" cellspacing="0" bgcolor="#F4F4F4" height="1000" width="1000" align="center" border="1" bordercolor="#9900CC">
<tr><td height="100" colspan="3"  class="Linkbar">
<table cellpadding="0" cellspacing="0" width="100%"  align="center">
<tr><td><img src="resources/image/using image/log.gif"  width="100%"/></td></tr>



<td height="5" >
  <img src="resources/images/linebar.jpg"  width="100%"/>
  </td></tr>


<tr><td  class="Hyperlink" width="100%" >

<table cellpadding="0" cellspacing="0" width="100%">
<tr><td class="" width="72" align="center">
<a href="home.php"><img src="resources/images/home.jpg"  class="home"/>&nbsp;Home</a>
</td>
<td class="" width="107" align="center">
<a href="about.php"><img src="resources/images/arrow.jpg"  />&nbsp;About us</a>
</td>

<td class="" width="95" align="center">
<a href="services.php"><img src="resources/images/arrow.jpg"  />&nbsp;Services</a>
</td>
<td class="" width="124" align="center">
<a href="contact.php"><img src="resources/images/arrow.jpg" />&nbsp;Contact us</a>
</td>
<td class="" width="180" align="center">
<a href="Tech_support.php"><img src="resources/images/arrow.jpg" />&nbsp;Technical Support</a>
</td>
<td class="" width="309" align="center">

</td>
 <td width="211"  height="20"   >

        <span style="float:right; color:blue;"> <script language="javascript" type="text/javascript">TeleTime();</script>&nbsp;&nbsp;&nbsp;&nbsp;</span>

     </td>
</tr>

</table>

   </td></tr>
</table>
</td>
</tr>


<tr>

<td width="600"  valign="top">
&nbsp;&nbsp;
<table cellpadding="0" cellspacing="0" width="100%" align="center">
    <tr><fieldset><legend><font FACE = "tahoma" SIZE = "5" COLOR ="green">New User's Control Panel</font></legend>
<%@include file="control_panel.jsp" %>
</fieldset></tr>

<tr><td  width="100%" style="padding-left:20px; padding-right:20px; padding-top:10px; padding-bottom:20px">
<!--Registration codes  Starts here -->
<div  id="TableActionPageContainer">

<table width="100%" height="" cellpadding="0" cellspacing="0" align="center" id="RegistrationPageTable">
<tr>
     <td width="400" height="30" bgcolor=" "  align="center" colspan="2">
     <b><img src="resources/image/using image/sign.gif" width="70" height="15"  align="absmiddle"/>&nbsp;&nbsp;<font face="Times New Roman" size="5" color="blue">
     Customer Registration</font></b></td></tr>
     <tr>
     <td width="400" height="30" bgcolor=" "  align="center" colspan="2">
     <font face="Times New Roman" size="2" color="red">
     Note that the fields that are marked with * are compulsory</font></td>
</tr>
     <tr>
     <td width="400" height="30" bgcolor=" "  align="center" colspan="2">
     <font face="Times New Roman" size="3" color="red">
     ${errmsg}</font></td>
</tr>
 <tr>

     <td width="400" height="10"  align="center" colspan="2">
     <div align="center" id="errmsg"> </div></td>
</tr>

<%session.removeAttribute("errmsg"); %>

<form onsubmit="return FormValidation();"  method="POST"  action="servlets/RequestProcessing"  name="form1" >
   <tr><td   align="center" width = "100%"  height= "20">
	<font face= "Times New Roman" size = "3" color="blue">Sur Name:</font><span style="color:#FF0000">*</span><br /><input  type="text" size = "30" name = "Sname" maxlength="50" placeholder="Surname"></td><td align="center" width = "100%"  height= "20" > <font face= "Times New Roman" size = "3" color="blue">Other Name:</font><span style="color:#FF0000">*</span><input type="text" size = "30" name = "Oname" maxlength="50" placeholder="Other Names"  style="margin-right:30px;"> </div></td>
    <div id="onmsg"></div>
    </tr>


<tr><td  align="center" width = "100%"  height= "20" >
<font face= "Times New Roman" size = "3" color="blue">Mobile Number:</font><span style="color:#FF0000">*</span>
<br /><input  type="text" size = "30" name = "gsm" maxlength="50"></td><td align="center" width = "100%"  height= "20" ><font face= "Times New Roman" size = "3" color="blue">E-mail:</font><span style="color:#FF0000">*</span>
<input type="text" size = "30" name = "email1"  maxlength="50" style="margin-right:30px;" >
</td></tr>

<tr><td  align="center" width = "100%"  height= "20" >
<font face= "Times New Roman" size = "3" color="blue">Address:</font><span style="color:#FF0000">*</span>
<br /><textarea id="a1" cols="20" rows="4" name="address"> </textarea></td><td  align="center" width = "100%"  height= "20" ><font face= "Times New Roman" size = "3" color="blue">Area:</font><span style="color:#FF0000"></span>
<textarea cols="20" rows="4" name="Area" style="margin-right:30px;"> </textarea></td>

</tr>

<tr><td  align="center" width = "100%"  height= "20" >
<font face= "Times New Roman" size = "3" color="blue">State:</font><span style="color:#FF0000">*</span>
<br /><input type="text" size = "30" name = "state" maxlength="50" > </td><td align="center" width = "100%"  height= "20" ><font face= "Times New Roman" size = "3" color="blue">Contact Number:</font><span style="color:#FF0000"></span>
<input type="text" size = "30" name = "Contact_num" maxlength="50" style="margin-right:30px;">
</td></tr>

<tr><td  align="center" width = "100%"  height= "20" >
<font face= "Times New Roman" size = "3" color="blue">Login ID:</font><span style="color:#FF0000">*</span>
<br /><select name="snos" size="1">
<option value="SNOS1">SNOS1</option>
<option value="SNOS2">SNOS2</option>
<option value="SNOS3">SNOS3</option>
<option value="SNOS4">SNOS4</option>
<option value="SNOS5">SNOS5</option>
<option value="SNOS6">SNOS6</option>
<option value="SNOS7">SNOS7</option>
<option value="SNOS8">SNOS8</option>
</select>
</td> <td align="center" width = "100%"  height= "20" ><font face= "Times New Roman" size = "3" color="blue">LGA:</font><span style="color:#FF0000">*</span>
<input type="text" size = "30" name = "lga" maxlength="50" style="margin-right:30px;">
</td></tr>


<tr><td align="center" width = "100%"  height= "20" ><font color="blue" size="3" face="Times New Roman">Password:</font><span style="color:#FF0000">*</span>
        <br /><input type="password" size = "30" name = "pass" maxlength="50"></td>
<td align="center" width = "100%"  height= "20" ><font color="blue" size="3" face="Times New Roman"> Verify Password:</font><span style="color:#FF0000">*</span>
   <input type="password" size = "30" name = "vpass" maxlength="50" style="margin-right:30px;">

</td></tr>

<script>
    /*
    $(function(){
      var msg=document.getElementById("namemsg");
// window.alert("Thank you");
 $("input[type='submit']").bind("click",function(){
                var CheckSurname=document.forms["form1"]["Sname"];
                var checkOtherName=document.forms["form1"]["Oname"];
                var checkgsm=document.forms["form1"]["gsm"];
                var checkemail=document.forms["form1"]["email1"];
                var checkaddress=document.forms["form1"]["address"];
                var checkstate=document.forms["form1"]["state"];
                var checklga=document.forms["form1"]["lga"];
                var checkpass=document.forms["form1"]["pass"];
                var checkvpass=document.forms["form1"]["vpass"];



           if(CheckSurname.value=="")
              {

                alert("Surname is Empty");
                //ValidateUserName(form1.Sname.value);
                msg.innerHTML="You must enter a value in the Name Field".bold().fontcolor("red").fontsize("5");
                CheckSurname.focus();
                //form1.Sname.focus();
               return false;
               //exit();

              }
              else if(checkOtherName.value=="")
                      {
                          alert("Othername is Empty");
                          //ValidateUserName(form1.Oname.value);
                           msg.innerHTML="You must enter a value in the OtherName Field".bold().fontcolor("red").fontsize("5");
                          checkOtherName.focus();
                            return false;
                      }

              else if(checkgsm.value=="")
                      {
                          alert("Gsm is Empty");
                          //ValidatePhoneNumber(form1.gsm.value);
                           msg.innerHTML="You must enter a value in the GSM Field".bold().fontcolor("red").fontsize("5");
                           checkgsm.focus();
                            return false;
                      }
                 else if(checkemail.value=="")
                      {
                          alert("Email Address is Empty");
                          //ValidateEmail(form1.email1.value);
                           msg.innerHTML="You must enter a value in the Email Field".bold().fontcolor("red").fontsize("5");
                          checkemail.focus();
                            return false;
                      }
                 else if(checkaddress.value=="")
                      {
                          alert("Address is Empty");
                          //ValidateAddress(form1.address.value);
                           msg.innerHTML="You must enter a value in the <strong>Address Field</strong>".bold().fontcolor("red").fontsize("5");
                           checkaddress.focus();
                            return false;
                      }
                else if(checkstate.value=="")
                      {
                            alert("State is Empty");
                           // ValidateUserName(form1.state.value);
                            msg.innerHTML="You must enter a value in the <strong>State Field</strong>".bold().fontcolor("red").fontsize("5");
                            checkstate.focus();
                            return false;
                      }
                else if(checklga.value=="")
                      {
                          alert("LGA is Empty");
                          //ValidateLga(form1.lga.value);
                           msg.innerHTML="You must enter a value in the <strong>LGA Field</strong>".bold().fontcolor("red").fontsize("5");
                           checklga.focus();
                            return false;
                      }
                       else if(checkpass.value=="")
                      {
                          alert("Passoword is Empty");
                          //ValidatePassword(form1.pass.value);
                           msg.innerHTML="You must enter a value in the <strong>Password Field</strong>".bold().fontcolor("red").fontsize("5");
                           checkpass.focus();
                            return false;
                      }
                  else if(checkvpass.value=="")
                      {
                          alert("Vpass is Empty");
                         // ValidatePassword(form1.vpass.value);
                           msg.innerHTML="You must enter a value in the <strong>Verify Password Field</strong>".bold().fontcolor("red").fontsize("5");
                           checkvpass.focus();
                            return false;
                      }
                      else if(checkpass.value!=checkvpass.value)
                      {
                          alert("The two password field must be the same!");
                           msg.innerHTML="<strong> Password and Verify Password Field must be the same</strong>".bold().fontcolor("red").fontsize("5");
                           checkvpass.focus();
                            return false;
                      }
                    else
                          {
                              return true;
                          }

          //return false;

    });
    }
    */
</script>


<tr><td height="5">
&nbsp;&nbsp;
</td></tr>

<tr><td align="center" width = "100%"  height= "20" colspan="2" >
<input type="submit"   value="Next >>" name="action" id="reg1" style="border: 1px solid #698EEF; padding-left: 4; padding-right: 4; padding-top: 1; padding-bottom: 1; color:#FFF;background-color:#009999">
 &nbsp; <input type="reset" value="Reset" name="can" style="border: 1px solid #698EEF; padding-left: 4; padding-right: 4; padding-top: 1; padding-bottom: 1; color:#FFF;background-color:#009999">
 <input type="hidden" value="reg1" name="id" style="border: 1px solid #698EEF; padding-left: 4; padding-right: 4; padding-top: 1; padding-bottom: 1; color:#FFF;background-color:#009999">
</td></tr>
</form>

<tr>
      <td td align="center" width = "100%"  height= "20" colspan="2" >
     <font color='#FF0000'>  </font></td>
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
<td  width="250" valign="top">

<table cellpadding="0" cellspacing="0" width="100%"  style="margin-top:5px; margin-bottom:5px;" >
<tr><td height="10" align="center">
<div>
<input type="text" name="web" size="40" placeholder="Search From Web" />
</div>
</td></tr>
</table>

<table cellpadding="0" cellspacing="0" align="center" width="100%">

<tr>
  <td height="20" bgcolor="#00CC99" align="center" style="padding: 1; ; border-bottom-style:solid; border-bottom-width:1; border-right-style:none; color:#F0F; border-right-width:medium" colspan="3"><font color="blue" size="3" face="Georgia, Times New Roman, Times, serif">* Get Latest SMS To Email <img src="resources/image/using image/alert.gif"  height="20" align="absmiddle"/></font>
</td></tr>

<tr><td  width="100%" align="center">


<div  id="subscribeformContainer">

			<form  id="subscribeForm" method="post" action="system_sms.php" name="form2">

  <input type="text" name="sea"  size="30" maxlength="50" id="sub" placeholder="Enter E-mail" style="top: 40px;"  />

<input type="submit" name="subB" value="Subscribe" style="top: 110px;" id="subscribeformInputSubmit"/>

			</form>

		</div>
</td></tr>

<tr><td height="5">
&nbsp;&nbsp;
</td></tr>


<tr><td height="5">
&nbsp;&nbsp;
</td></tr>

<tr><td height="10" align="center" class="style36" bgcolor="#00CC99" width="100%">
  <p>
   
  </p>
  </td></tr>



<tr><td height="10"  width="100%">
  <img  src="resources/image/linepic.png"  width="100%"/> <br /><img  src="../image/linepic1.png"   width="100%"/>
  </td></tr>

<tr><td  align="center">
<img src="resources/image/hep.jpg" width="216" height="144" />

</td></tr>
<tr><td height="5" >
  <p>&nbsp;&nbsp;&nbsp;</p>
  </td></tr>
</table>



</td>
</tr>

<%@include  file="footer.jsp" %>
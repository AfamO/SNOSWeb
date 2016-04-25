<%--
    Document   : process_register
    Created on : Apr 16, 2013, 12:34:28 PM
    Author     : Admin
--%>



<%@include  file="header2.jsp" %>


<tr>
 <td width="100%" valign="top" >
 
 <table width="100%"  cellspacing="0" cellpadding="0"  >
	    
  <tr>
   
<td  width="600">
<div id="RegistrationPageTable">
    
<table width="100%" height="" cellpadding="0" cellspacing="0" align="center"  class="tabborder">
<tr>
     <td width="400" height="30" bgcolor=" "  align="center" colspan="2">
     <b><img src="resources/images/sign.gif" width="70" height="15"  align="absmiddle"/>&nbsp;&nbsp;<font face="Times New Roman" size="5" color="blue">
         Client Personal Information-Step:1/3
         </font></b></td></tr>
     <tr>
     <td width="400" height="30" bgcolor=" "  align="center" colspan="2">
     <font face="Times New Roman" size="2" color="red">
     Note that the fields that are marked with * are compulsory</font></td>
</tr>
     <tr>
     <td width="400" height="30" bgcolor=" "  align="center" colspan="2">
     <font face="Times New Roman" size="3" color="red">
     ${errmsg}</font></td>
</tr

<tr>

     <td width="400" height="10"  align="center" colspan="2">
     <div align="center" id="errmsg1"> </div></td>
</tr>
 <tr>

     <td width="400" height="10"  align="center" colspan="2">
     <div align="center" id="errmsg"> </div></td>
</tr>

<%session.removeAttribute("errmsg"); %>

<form onSubmit="return FirstFormValidation();"  method="POST"  action="servlets/RequestProcessing"  name="form1" >
   
   <tr><td   align="center" width = "100%"  height= "20">
	<font face= "Times New Roman" size = "3" color="blue">SurName:</font><span style="color:#FF0000">*</span><br /><input  type="text" size = "30"  name = "Sname" maxlength="50" placeholder="Surname"></td>
	<td align="center" width = "100%"  height= "20" > <font face= "Times New Roman" size = "3" color="blue">Other Name:</font><span style="color:#FF0000">*</span><input type="text" size = "30" name = "Oname" maxlength="50" placeholder="Other Names"  style="margin-right:30px;">  </td>
    <div id="onmsg"></div>
   </tr>
   
   <tr><td  align="center" width = "100%"  height= "20" >
<font face= "Times New Roman" size = "3" color="blue">Mobile Number:</font><span style="color:#FF0000">*</span>
<br /><input  type="text" size = "30" onchange="return checkEmail_GsmBeforeRegistrationAddress(this.value,'gsm');"  name = "gsm" maxlength="50"></td><td align="center" width = "100%"  height= "20" ><font face= "Times New Roman" size = "3" color="blue">E-mail:</font><span style="color:#FF0000">*</span>
<input type="text" size = "30" onchange="return checkEmail_GsmBeforeRegistrationAddress(this.value,'myemail');" name = "email1"  maxlength="50" style="margin-right:30px;" >
</td></tr>

<tr><td  align="center" width = "100%"  height= "20" >
<font face= "Times New Roman" size = "3" color="blue">Address:</font><span style="color:#FF0000">*</span>
<br /><textarea id="a1" cols="20" rows="4" name="address"> </textarea></td><td  align="center" width = "100%"  height= "20" ><font face= "Times New Roman" size = "3" color="blue">Area:</font><span style="color:#FF0000"></span>
<textarea cols="20" rows="4" name="Area" style="margin-right:30px;"> </textarea></td>

</tr>


<tr><td  align="center" width = "100%"  height= "20" >
<font face= "Times New Roman" size = "3" color="blue">State:</font><span style="color:#FF0000">*</span>
<br /><select  data-inline="true" name="state" size="1"  onchange="generateList();" onblur="generateList();">
<option>Select your State </option>
<option value="Abia State">Abia</option>
<option value="Akwa Ibom State">Akwa Ibom</option>
<option value="Anambra State">Anambra</option>
<option value="Adamawa state">Adamawa</option>
<option value="Bauchi state">Bauchi</option>
<option value="Bayelsa state">Bayelsa</option>
<option value="Benue state">Benue</option>
<option value="Borno state">Borno</option>
<option value="Cross River state">Cross River</option>
<option value="Delta State">Delta</option>
<option value="Ebonyi state">Ebonyi</option>
<option value="Edo state">Edo</option>
<option value="Ekiti state">Ekiti</option>
<option value="Enugu State">Enugu</option>
<option value="Gombe state">Gombe</option>
<option value="Imo State">Imo</option>
<option value="Jigawa state">Jigawa</option>
<option value="Kaduna state">Kaduna</option>
<option value="Kano state">Kano</option>
<option value="Katsina state">Katsina</option>
<option value="Kebbi state">Kebbi</option>
<option value="Kogi state">Kogi</option>
<option value="Kwara state">Kwara</option>
<option value="Lagos State">Lagos</option>
<option value="Nasarawa state">Nasarawa</option>
<option value="Niger state">Niger</option>
<option value="Ogun State">Ogun</option>
<option value="Ondo state">Ondo</option>
<option value="Osun state">Osun</option>
<option value="Oyo state">Oyo</option>
<option value="Plateau state">Plateau</option>
<option value="Rivers state">Rivers</option>
<option value="Sokoto state">Sokoto</option>
<option value="Taraba state">Taraba</option>
<option value="Zamfara state">Zamfara</option>
<option value="FCT">FCT</option>



</select> </td> <td align="center" width = "100%"  height= "20" ><font face= "Times New Roman" size = "3" color="blue">LGA:</font><span style="color:#FF0000">*</span>
 <div align="center" id="lgalist">
 Select your State to choose your LGA.
 </div>
    <div align="center" id="lgamsg">

     </div>
     <select  id="mylg" name="lga" onchange="getMyLGValue(this.value);" >
     </select>
     
<input  id="mylgaa" type="text" size = "20" placeholder="Enter you LGA here" name = "lgaa"  maxlength="50" style="margin-right:30px;" ></td>

</td></tr>
<tr>


     <td width="400" height="10"  align="center" colspan="2">
        
     </td>
</tr>






<tr><td align="center" width = "100%"  height= "20" ><font color="blue" size="3" face="Times New Roman">Password:</font><span style="color:#FF0000">*</span>
        <br /><input type="password" size = "30" name = "pass"  maxlength="50"></td>
<td align="center" width = "100%"  height= "20" ><font color="blue" size="3" face="Times New Roman"> Verify Password:</font><span style="color:#FF0000">*</span>
   <input type="password" size = "30"  name = "vpass" maxlength="50" style="margin-right:30px;">

</td></tr>


<tr><td height="5">
&nbsp;&nbsp;
</td></tr>
   
 <tr><td align="center" width = "100%"  height= "20" colspan="2" >
<input type="submit"   value="Next >>" name="action" id="reg0" class="buttonDisplay">
 &nbsp; <input type="reset" value="Reset" name="can" class="buttonDisplay">
     <input type="hidden" value="desktop" name="source" >
 <input type="hidden" value="reg1" name="id" >
</td></tr>
</form>

<tr>
      <td td align="center" width = "100%"  height= "20" colspan="2" >
     <font color='#FF0000'>  </font></td>
    </tr>
 </table>  
</div>



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

			<form   method="post" action="" name="form2">

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


<%@include  file="footer2.jsp" %>
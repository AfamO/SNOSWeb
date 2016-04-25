<%-- 
    Document   : device_information
    Created on : Apr 16, 2013, 3:29:24 PM
    Author     : Admin
--%>



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
<div id="RegistrationPageTable">

<table width="100%" height="" cellpadding="0" cellspacing="0" align="center"  class="tabborder">
<tr>
     <td width="400" height="30" bgcolor=" "  align="center" colspan="2">
     <b><img src="resources/images/sign.gif" width="70" height="15"  align="absmiddle"/>&nbsp;&nbsp;<font face="Times New Roman" size="5" color="blue">
     Object/Personal Belonging Information-Step:2/3</font></b></td>
</tr>
<tr>
     <td width="400" height="30" bgcolor=" "  align="center" colspan="2">
     <font face="Times New Roman" size="2" color="red">
     Note that the fields that are marked with * are compulsory</font></td>
</tr>
<tr>
     <td width="400" height="30" bgcolor=" "  align="center" colspan="2">
     <font face="Times New Roman" size="3" color="blue">
     Please kindly do enter the data of your personal belongings that you want SNOS to secure for you.</font></td>
</tr>
<tr>
     <td width="400" height="30"   align="center" colspan="2">
     <font face="Times New Roman" size="3" color="red">
     ${errmsg}${status}</font></td>
</tr>

<tr>

     <td width="400" height="10"  align="center" colspan="2">
     <div align="center" id="errmsg"> </div></td>
</tr>

<%  session.removeAttribute("errmsg"); %>
<%session.removeAttribute("status"); %>

<form onsubmit="return FormValidation2();" method="POST"  action="servlets/RequestProcessing"  name="form2" >
<tr><td   align="center" width = "100%"  height= "20"><font face= "Times New Roman" size = "3" color="blue">Name:</font><span style="color:#FF0000">*</span><br /><input type="text" size = "30" name = "nam" maxlength="50" placeholder="Devices's Name"></td><td align="center" width = "100%"  height= "20" > <font face= "Times New Roman" size = "3" color="blue">Housing Type:</font><span style="color:#FF0000">*</span><input type="text" size = "30" name = "house_type" maxlength="50" placeholder="Type of Structure housing your equipment"  style="margin-right:30px;"></td></tr>
<tr><td  align="center" width = "100%"  height= "20" >
<font face= "Times New Roman" size = "3" color="blue">Description:</font><span style="color:#FF0000">*</span>
<br /><textarea cols="20"  rows="4" name="description"> </textarea></td><td  align="center" width = "100%"  height= "20" ><font face= "Times New Roman" size = "3" color="blue">Location(in the house):</font><span style="color:#FF0000">*</span>
<textarea cols="20" rows="4" name="Location" style="margin-right:30px;"> </textarea></td>

</tr>
<tr><td  align="center" width = "100%"  height= "20" >
<font face= "Times New Roman" size = "3" color="blue">Address:</font><span style="color:#FF0000">*</span>
<br /><textarea id="ad1" cols="20" rows="4" name="address"> </textarea></td><td  align="center" width = "100%"  height= "20" ><font face= "Times New Roman" size = "3" color="blue">Area:</font><span style="color:#FF0000"></span>
<textarea cols="20" rows="4" name="area" style="margin-right:30px;"> </textarea></td>

</tr>
<tr><td  align="center" width = "100%"  height= "20" >
<font face= "Times New Roman" size = "3" color="blue">State:</font><span style="color:#FF0000">*</span>
<br /><select name="state" size="1" onchange="generateList2();">
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
 <div align="center" id="lgalist"><font face= "Times New Roman" size = "3" color="blue">Thank you</font>

     
     Select your State to choose your LGA.
     </div> <div align="center" id="lgamsg">
      <select  id="mylg" name="lga" onchange="getMyLGValue(this.value);" >

     </select>
     </div>
</td></tr>

    <tr>
     <td width="400" height="10"  align="center" colspan="2">
     <font face="Times New Roman" size="5" color="red">
     Do you wish to Register additional Equipment/Device/Sensor?:<span style="color:#FF0000">*</span></font></td>
</tr>

<tr><td>
    <font face= "Times New Roman" size = "6" color="blue">NO</font><span style="color:#FF0000">*</span>
<input type="radio" cols="20" checked  rows="4" name="extra_sensor" value="no"></td><td  align="center" width = "100%"  height= "20" ><font face= "Times New Roman" size = "6" color="blue">Yes</font><span style="color:#FF0000">*</span>
<input type="radio" cols="20"  rows="4" name="extra_sensor" value="yes"></td>
&nbsp;&nbsp;
</td></tr>
<tr><td height="5">
&nbsp;&nbsp;
</td></tr>

<tr><td align="center" width = "100%"  height= "20" colspan="2" >

       

<input type="submit" value="Next >>" name="action"  class="buttonDisplay" >
 &nbsp; <input type="reset" value="Reset" name="can" class="buttonDisplay" >
<input type="hidden" value="reg2" name="id" >
 <input type="hidden" value="desktop" name="source" >
</td></tr> </form>

<tr>
      <td td align="center" width = "100%"  height= "20" colspan="2" >
     <font color='#FF0000'>  </font></td>
    </tr>

</table>

</div>


</td>

</tr>
</table>


</td>




</tr>
</table></td></tr>

<%@include  file="footer2.jsp" %>
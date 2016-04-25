<%--
    Document   : device_information
    Created on : Apr 16, 2013, 3:29:24 PM
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

<table width="100%" height="" cellpadding="0" cellspacing="0" align="center"  class="tabborder" style="padding-left:20px; padding-right:20px; padding-top:10px; padding-bottom:20px">
<tr>
     <td width="400" height="30" bgcolor=" "  align="center" colspan="2">
     <b><img src="resources/images/sign.gif" width="70" height="30"  align="absmiddle"/>&nbsp;&nbsp;<font face="Times New Roman" size="5" color="blue">
         My Device Profile/Update:<%=(Integer)session.getAttribute("did")+1 %> of ${rs}</font></b></td>
</tr>
<tr>
     <td width="400" height="10"   align="center" colspan="2">
     <font face="Times New Roman" size="3" color="red">
     ${errmsg}</font></td>
</tr>

<%  session.removeAttribute("errmsg"); %>
<tr><td  width = "100%"><font face="Times New Roman" size="2" color="blue">Note:The Result of your Update is seen immediately you click 'Update Device' Button</font></td></tr>
<form onsubmit="return UpdateFormValidation2();"   method="POST"  action="servlets/RequestProcessing"  name="form2" >
<tr><td   align="center" width = "100%"  height= "20"><font face= "Times New Roman" size = "3" color="blue">Name:</font><span style="color:#FF0000">*</span><br /><input type="text" size = "30" value="${val[0]}" name = "nam" maxlength="50" placeholder="Contacts's Name"></td><td align="center" width = "100%"  height= "20" > <font face= "Times New Roman" size = "3" color="blue">Housing Type:</font><span style="color:#FF0000">*</span><input type="text" value="${val[2]}" size = "30" name = "house_type" maxlength="50" placeholder="Type of Structure housing your equipment"  style="margin-right:30px;"></td></tr>
<tr><td  align="center" width = "100%"  height= "20" >
<font face= "Times New Roman" size = "3" color="blue">Description:</font><span style="color:#FF0000">*</span>
<br /><textarea cols="20" rows="4"  name="description">${val[1]} </textarea></td><td  align="center" width = "100%"  height= "20" ><font face= "Times New Roman" size = "3" color="blue">Location(in the house):</font><span style="color:#FF0000">*</span>
<textarea cols="20"  rows="4" name="Location" style="margin-right:30px;">${val[3]} </textarea></td>

</tr>
<tr><td  align="center" width = "100%"  height= "20" >
<font face= "Times New Roman" size = "3" color="blue">Address:</font><span style="color:#FF0000">*</span>
<br /><textarea cols="20" rows="4" name="address" >${val[4]} </textarea></td><td align="center" width = "100%"  height= "20" > <font face= "Times New Roman" size = "3" color="blue"> State:</font><span style="color:#FF0000">*</span>
        <select name="state" size="1" >
<option value='${val[6]}'>${val[6]} </option>
<option value="Enugu">Enugu </option>
<option value="Anambra">Anambra</option>
<option value="Lagos">Lagos</option>
<option value="Imo">Imo</option>
<option value="Ogun">Ogun</option>
</select>
</td>

</tr>
<tr>
    <td   align="center" width = "100%"  height= "20">
	<font face= "Times New Roman" size = "3" color="blue">LGA:</font><span style="color:#FF0000">*</span><br />
    <select name="lga" size="1" >
<option value="${val[5]}"  >${val[5]} </option>
<option value="Awgu">Awgu </option>
<option value="Eti-Osa 2">Eti-Osa 2</option>
<option value="Eti-Osa 3">Eti-Osa 3</option>
<option value="Aniri South">Aniri South</option>
<option value="Ikeja North">Ikeja North</option>
</select>
</td>
    </tr>

<tr><td height="5">
&nbsp;&nbsp;
</td></tr>

<tr><td align="center" width = "100%"  height= "20" colspan="2" >
     <%
        String next="<input type='submit' value='Next >>' name='action' class='buttonDisplay'>";
        String prev="<input type='submit' value='<< Previous' name='action' class='buttonDisplay'>";
         if((Integer)session.getAttribute("did")==0 && (Integer)session.getAttribute("rs")!=1)
        {
            out.println(next);
        }
        
        else if((Integer)session.getAttribute("rs")==(Integer)session.getAttribute("did")+1 && (Integer)session.getAttribute("rs")!=1)
        {
            out.println(prev);
        }
        else if((Integer)session.getAttribute("did")<(Integer)session.getAttribute("rs")&& (Integer)session.getAttribute("rs")!=1)
        {
            out.println(prev+"&nbsp;");out.println(next);
          

        }


        %>
    
<input type="submit" value="Update Device" name="action" class="buttonDisplay">
    

 &nbsp; <input type="reset" value="Reset" name="can" class="buttonDisplay">  <input type="hidden" value="reg22" name="id" >
     <input type="hidden" value="desktop" name="source" >
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

</table>
</td>

</tr>
</table>

<%@include  file="footer.jsp" %>
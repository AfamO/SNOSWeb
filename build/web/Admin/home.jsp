<%-- 
    Document   : home
    Created on : Apr 22, 2013, 8:15:39 AM
    Author     : Charles
--%>



  <%@include  file="header.jsp" %>

  <td valign="top">
        <div  id="LoginTableContainer">
        <table width="450"  cellspacing="0" cellpadding="0"  align="center" class="border">

<form action="../AdminRequestProcessing" method="post" name="adminForm">

<tr>
     <td width="400" height="30" bgcolor=" "  align="center" colspan="2" style="padding-top:10px;">
     <b>&nbsp;&nbsp;<font face="Times New Roman" size="5" color="blue">Administrators Information</font></b></td>
</tr>
<tr>
<td style=" padding-left:10px;">
<font face= "Times New Roman" size = "3" color="blue">Name:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>
<input type="text" size = "30" name = "adminname" maxlength="50" placeholder="Enter Name">
</td>
</tr>

<tr>
<td height="5">
&nbsp;&nbsp;
</td>
</tr>

<tr>
<td style=" padding-left:10px;">
<font face= "Times New Roman" size = "3" color="blue">user id:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>
<input type="text" size = "30" name = "userid" maxlength="50" placeholder="Enter user id">
</td>
</tr>

<tr>
<td height="5">
&nbsp;&nbsp;
</td>
</tr>


<tr>
<td style=" padding-left:10px;">
<font face= "Times New Roman" size = "3" color="blue">Password:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>
<input type="password" size = "30" name = "pass" maxlength="50" placeholder="Password">
</td>
</tr>

<tr>
<td height="5">
&nbsp;&nbsp;
</td>
</tr>

<tr>
<td style=" padding-left:10px;">
<font face= "Times New Roman" size = "3" color="blue"> Verify Password:</font>
<input type="password" size = "30" name = "vpass" maxlength="50" placeholder="Password">
</td>
</tr>

<tr>
<td height="5">
&nbsp;&nbsp;
</td>
</tr>

<tr>
<td style=" padding-left:10px;">
<font face= "Times New Roman" size = "3" color="blue">Status:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>
<select name="status" size="1">
<option>Active</option>
<option>Non-Active</option>
</select>
</td>
</tr>
<tr>
<td height="5">
&nbsp;&nbsp;
</td>
</tr>


<tr><td align="center" width = "100%"  height= "20" colspan="2" >
<input type="submit" value="Submit" name="AdminB" style="border: 1px solid #698EEF; padding-left:4px; padding-right:4px; padding-top:1px; padding-bottom:1px; color:#FFF;background-color:#009999">
 &nbsp; <input type="reset" value="Reset" name="can" style="border: 1px solid #698EEF; padding-left:4px; padding-right:4px; padding-top:1px; padding-bottom:1px; color:#FFF;background-color:#009999">
</td></tr> </form>

<tr>
      <td td align="center" width = "100%"  height= "20" colspan="2" >

     </td>
    </tr>
</table>


</div></td>

<%@include  file="footer.jsp" %>
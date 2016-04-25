<%-- 
    Document   : Snos_registration
    Created on : Apr 18, 2013, 8:13:18 AM
    Author     : Charles
--%>



   <%@include  file="header.jsp" %>
   
<%@ page import="java.io.*; import java.util.*;
    import javax.servlet.http.*;" %>

<td valign="top">
        <div  id="LoginTableContainer">
        <table width="450"  cellspacing="0" cellpadding="0"  align="center" class="border">

<tr>
     <td width="400" height="10" bgcolor=" "  align="center" colspan="2"  style="padding-top:10px;">
     <b><font face="Times New Roman" size="5" color="blue">Snos Registration</font></b></td>
</tr>

<form action="../AdminRequestProcessing" method="post" name="SnosForm" onsubmit=" return ValidateAdminSnosRegistration();">

<tr>
     <td width="400" height="30" bgcolor=" "  align="center">
     <b>&nbsp;&nbsp;<font face="Times New Roman" size="3" color="red">
         ${Issnosexists} ${emptyAlert}

<%  session.removeAttribute("Issnosexists");
   session.removeAttribute("emptyAlert");
%>


     </font></b></td>
</tr>
<tr>
<td style=" padding-left:10px;">
<font face= "Times New Roman" size = "3" color="blue">Snos Type:*&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>
<input type="text" size = "30" name = "snosType" maxlength="50" placeholder="Enter Snos Type"><br><font size=2 color=red>
    
${errorAlert}

<%  session.removeAttribute("errorAlert"); %>
 </font>

                  


</td>
</tr>

<tr>
<td height="5">
&nbsp;&nbsp;
</td>
</tr>

<tr>
<td style=" padding-left:10px;">
<font face= "Times New Roman" size = "3" color="blue">Mobile Number:*</font>
<input type="text" size = "30" name = "number" maxlength="50" placeholder="Enter Number"><br><font size=2 color=red>

${errorAlert2}

<%  session.removeAttribute("errorAlert2"); %>
 </font>

</td>
</tr>

<tr>
<td height="5">
&nbsp;&nbsp;
</td>
</tr>
<tr><td align="center" width = "100%"  height= "20" colspan="2" >
<input type="submit" value="Submit" name="RegSnosButton" style="border:1px solid #698EEF; padding-left:4px; padding-right:4px; padding-top:1px; padding-bottom:1px; color:#FFF;background-color:#009999" >
 &nbsp; <input type="reset" value="Reset" name="can" style="border: 1px solid #698EEF; padding-left:4px; padding-right:4px; padding-top:1px; padding-bottom:1px; color:#FFF;background-color:#009999">
</td></tr> </form>

<tr>
      <td td align="center" width = "100%"  height= "20" colspan="2" >
          
   </td>
    </tr>
</table>


</div></td>

<%@include  file="footer.jsp" %>
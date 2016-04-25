<%-- 
    Document   : AdminReport
    Created on : Apr 23, 2013, 6:06:54 AM
    Author     : Charles
--%>

 <%@include  file="header.jsp" %>

<td valign="top">
        <div  id="SearchContainer">
        <table width="450"  cellspacing="0" cellpadding="0"  align="center" class="border">


<tr>
  <td height="20" bgcolor="#00CC99" align="center" class="border" colspan="3"><font color="blue" size="3" face="Georgia, Times New Roman, Times, serif">SMS Message Report Page</font>
</td></tr>

<tr><td height="5">
&nbsp;&nbsp;
</td></tr>

<tr><td  width="100%" align="center" >

 <fieldset>
 <legend><font color="blue" size="3" face="Georgia, Times New Roman, Times, serif">* Enter SNOS Type to retrive Record</font></legend>

			<form   method="post" action="../AdminRequestProcessing" name="searchForm" onsubmit=" return ValidateAdminReport();">

  <input type="text" name="search"  size="30" maxlength="50"  placeholder="Enter SNOS Type" style="top: 40px;"  />
  

<input type="submit" name="findB" value="Find" style="top: 110px; border: 1px solid #698EEF; padding-left:4px; padding-right:4px; padding-top:1px; padding-bottom:1px; background-color:#009999; color:#FFF; margin-left:5px" />

			</form>

		
        </fieldset>
        <font size=2 color=red>

${searchAlert}

<%  session.removeAttribute("searchAlert"); %>
 </font>

</td></tr>

<tr><td height="5">
&nbsp;&nbsp;
</td></tr>




<tr><td height="20" align="center"  bgcolor="#00CC99" width="100%" class="border">
    <font face="Times New Roman" size="3" color="red">
         ${searchemptyAlert}

<%  
   session.removeAttribute("searchemptyAlert");
%>


     </font>

  </td></tr>

</table>
</div></td>


<%@include  file="footer.jsp" %>
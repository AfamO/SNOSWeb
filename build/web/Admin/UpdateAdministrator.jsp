<%-- 
    Document   : UpdateAdministrator
    Created on : Apr 26, 2013, 6:48:55 AM
    Author     : Charles
--%>

 <%@include  file="header.jsp" %>

<td valign="top">
        <div  id="LoginTableContainer">
<table align="center" cellpadding="0" cellspacing="0">

<form action="../AdminRequestProcessing" method="post" name="adminForm" onsubmit="ValidateAdministrator();">




    <%@ page import="java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;"%>


<%

   Statement statement;

   snossoftwaretest.Connector1 dbconnector = new  snossoftwaretest.Connector1();
   
            dbconnector.databaseConnect();

             String adminpara2=(String)session.getAttribute("adminupdateparaItem");

      try {

          statement = dbconnector.dbconn.createStatement();

          String query = "SELECT * FROM sensor_user WHERE uid = '" + adminpara2 + "'" ;

           ResultSet rs = statement.executeQuery(query);
           // int num=0;

             if(rs.next()){
                 // out.println(rs.getString ("fone"));
                 %>

<tr>
     <td width="400" height="30" bgcolor=" "  align="center" colspan="2" style="padding-top:10px;">
     <b>&nbsp;&nbsp;<font face="Times New Roman" size="5" color="blue">Admin Registration</font></b></td>
</tr>

<tr>
<td height="5"  align="center">
<font size=2 color=red>

${adminemptyAlert}${passCheck}${adminexistsAlert}

<%  session.removeAttribute("adminemptyAlert");
    session.removeAttribute("passCheck");
    session.removeAttribute("adminexistsAlert");%>
 </font>
</td>
</tr>
<tr>
<td style=" padding-left:10px;">
<font face= "Times New Roman" size = "3" color="blue">Name:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>
<input type="text" size = "30" name = "adminname" maxlength="50" value='<%out.println(rs.getString ("user_name")); %>'>
</td>
</tr>

<tr>
<td height="5">
&nbsp;&nbsp;
</td>
</tr>

<tr>
<td style=" padding-left:10px;">
<font face= "Times New Roman" size = "3" color="blue">user id:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>
<input type="text" size = "30" name = "userid" maxlength="50" value='<%out.println(rs.getString ("user_id")); %>'>
</td>
</tr>

<tr>
<td height="5">
&nbsp;&nbsp;
</td>
</tr>


<tr>
<td style=" padding-left:10px;">
<font face= "Times New Roman" size = "3" color="blue">Password:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>
<input type="password" size = "30" name = "pass" maxlength="50" >
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
<input type="password" size = "30" name = "vpass" maxlength="50" >
</td>
</tr>

<tr>
<td height="5">
&nbsp;&nbsp;
</td>
</tr>

<tr>
<td style=" padding-left:10px;">
<font face= "Times New Roman" size = "3" color="blue">Status:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>
<input type="text" size = "30" name = "status" maxlength="50" ><input type="hidden" size = "5" name = "admin_id" value='<%out.println(rs.getString ("uid")); %>'
</td>
</tr>



<%
               }
            dbconnector.dbconn.close();

          }
      catch (SQLException exc) {

                    out.println("<html><body>"+"<h3>No Information Found</h3><br>" +
                     "<a href="+"><b>Home</b></a>&nbsp;&nbsp;&nbsp;&nbsp;");
                   // out.println(adminpara2);

         }



%>


<tr>
<td height="5">
&nbsp;&nbsp;
</td>
</tr>


<tr><td align="center" width = "100%"  height= "20" colspan="2" >
<input type="submit" value="update" name="updateAdminButton" style="border: 1px solid #698EEF; padding-left:4px; padding-right:4px; padding-top:1px; padding-bottom:1px; color:#FFF;background-color:#009999">
</td></tr> </form>

<tr>
      <td td align="center" width = "100%"  height= "20" colspan="2" >

     </td>
    </tr>
</table>


</div></td>

<%@include  file="footer.jsp" %>
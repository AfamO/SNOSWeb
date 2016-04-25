<%-- 
    Document   : Updatesnos
    Created on : Apr 25, 2013, 3:11:15 AM
    Author     : Charles
--%>

<%@page language="java"%>

 <%@include  file="header.jsp" %>

   

<td valign="top">
        <div  id="LoginTableContainer">
            <table align="center" cellpadding="0" cellspacing="0" class="border"S>

<tr>
     <td width="400" height="10" bgcolor=" "  align="center" colspan="2" style="padding-top:10px;">
     <b><font face="Times New Roman" size="5" color="blue">Snos Registration</font></b></td>
</tr>

<form action="../AdminRequestProcessing" method="post" name="SnosForm" onsubmit="ValidateAdminSnosRegistration();">

<tr>
     <td width="400" height="30" bgcolor=" "  align="center">
     <b>&nbsp;&nbsp;<font face="Times New Roman" size="3" color="blue">
         ${Issnosexists} ${emptyAlert}

<%  session.removeAttribute("Issnosexists");
   session.removeAttribute("emptyAlert");
%>

        
     </font></b></td>
</tr>


<%@ page import="java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;"%>


<%

   Statement statement;

   snossoftwaretest.Connector1 dbconnector = new  snossoftwaretest.Connector1();
   snossoftwaretest.UserInforGet record = new snossoftwaretest. UserInforGet();

            dbconnector.databaseConnect();

             String updatepara2=(String)session.getAttribute("UpdateparaItem");

      try {

          statement = dbconnector.dbconn.createStatement();

          String query = "SELECT * FROM snos_table WHERE snos_type = '" + updatepara2 + "'" ;

           ResultSet rs = statement.executeQuery(query);
           // int num=0;

             if(rs.next()){
                 // out.println(rs.getString ("fone"));
                 %>

<tr>
<td>
<font face= "Times New Roman" size = "3" color="blue" style=" padding-left:10px;">Snos Type:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>
<input type="text" size = "30" name = "snosType" maxlength="50" value='<%out.println(rs.getString ("snos_type")); %>' >
</td>
</tr>




<tr>
<td height="5">
&nbsp;&nbsp;
</td>
</tr>

<tr>
<td>
<font face= "Times New Roman" size = "3" color="blue" style=" padding-left:10px;"> Mobile Number:</font>
<input type="text" size = "30" name = "number" maxlength="50" >
</td>
</tr>
<%
                // record.SetSensor(rs.getString ("snos_type"));
                // record.SetFone(rs.getString ("fone"));

               }

   // out.println(updatepara2);

            dbconnector.dbconn.close();

          }
      catch (SQLException exc) {

                   // String link=req.getContextPath()+"/Admin/login.jsp";
                   // String link2=req.getContextPath()+"/Admin/Administrator.jsp";

                    out.println("<html><body>"+"<h3>No Information Found</h3><br>" +
                     "<a href="+"><b>Home</b></a>&nbsp;&nbsp;&nbsp;&nbsp;");
                    out.println(updatepara2);

         }



%>




<tr>
<td height="5">
&nbsp;&nbsp;
</td>
</tr>
<tr><td align="center" width = "100%"  height= "20" colspan="2" >
<input type="submit" value="update" name="updateSnosButton" style="border: 1px solid #698EEF; padding-left:4px; padding-right:4px; padding-top:1px; padding-bottom:1px; color:#FFF;background-color:#009999">
 </td></tr> </form>

<tr>
      <td td align="center" width = "100%"  height= "20" colspan="2" >

   </td>
    </tr>
</table>


</div></td>

<%@include  file="footer.jsp" %>
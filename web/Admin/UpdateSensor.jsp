<%-- 
    Document   : updateSensor
    Created on : Apr 26, 2013, 4:55:47 AM
    Author     : Charles
--%>

<%@page language="java"%>

 <%@include  file="header.jsp" %>



<td valign="top">
        <div  id="LoginTableContainer">

            <table align="center" cellpadding="0" cellspacing="0" class="border">

<tr>
     <td width="400" height="10" bgcolor=" "  align="center" colspan="2">
     <b><font face="Times New Roman" size="5" color="blue">Sensor Registration</font></b></td>
</tr>

<form action="../AdminRequestProcessing" method="post" name="SnosForm" onsubmit="ValidateSensorRegistration();">

<tr>
     <td width="400" height="30" bgcolor=" "  align="center">
     <b>&nbsp;&nbsp;<font face="Times New Roman" size="3" color="blue">

     </font></b>

     <br><font size=2 color=red>

${sensoremptyAlert}

<%  session.removeAttribute("sensoremptyAlert"); %>
 </font>

     </td>
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

             String sensorupdatepara2=(String)session.getAttribute("SensorupdateparaItem");

      try {

          statement = dbconnector.dbconn.createStatement();

          String query = "SELECT * FROM sensor_table WHERE id = '" + sensorupdatepara2 + "'" ;

           ResultSet rs = statement.executeQuery(query);
           // int num=0;

             if(rs.next()){
                 // out.println(rs.getString ("fone"));
                 %>

<tr>
<td style="padding-left:10px;">
<font face= "Times New Roman" size = "3" color="blue">Snos Type:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>
<input type="text" size = "30" name = "snosType" maxlength="50" value='<%out.println(rs.getString ("snos_type")); %>' >
</td>
</tr>




<tr>
<td height="5">
&nbsp;&nbsp;
</td>
</tr>

<tr>
<td style="padding-left:10px;">
<font face= "Times New Roman" size = "3" color="blue">Sensor Type:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>
<input type="text" size = "30" name = "sensor_type" maxlength="50" ><input type="hidden" size = "5" name = "sensor_id" value='<%out.println(rs.getString ("id")); %>' >
</td>
</tr>
<%
               
               }

            dbconnector.dbconn.close();

          }
      catch (SQLException exc) {

                    out.println("<html><body>"+"<h3>No Information Found</h3><br>" +
                     "<a href="+"><b>Home</b></a>&nbsp;&nbsp;&nbsp;&nbsp;");
                   // out.println(sensorupdatepara2);

         }



%>




<tr>
<td height="5">
&nbsp;&nbsp;
</td>
</tr>
<tr><td align="center" width = "100%"  height= "20" colspan="2" >
<input type="submit" value="update" name="updateSensorButton" style="border: 1px solid #698EEF; padding-left:4px; padding-right:4px; padding-top:1px; padding-bottom:1px; color:#FFF;background-color:#009999">
 </td></tr> </form>

<tr>
      <td td align="center" width = "100%"  height= "20" colspan="2" >

   </td>
    </tr>
</table>


</div></td>

<%@include  file="footer.jsp" %>
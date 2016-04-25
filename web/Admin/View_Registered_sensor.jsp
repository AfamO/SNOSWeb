<%-- 
    Document   : view_Registered_sensor
    Created on : Apr 26, 2013, 4:44:14 AM
    Author     : Charles
--%>

<%@include  file="header.jsp" %>

  <td valign="top">
    <!....start code here ....!>
        <div  id="ViewTableContainer">

        <table width="450"  cellspacing="0" cellpadding="0"  align="center" class="border" id="table_view">

     

<%@ page import="java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;"%>

<%

   Statement statement;

   snossoftwaretest.Connector1 dbconnector = new  snossoftwaretest.Connector1();

            dbconnector.databaseConnect();

      try {

          statement = dbconnector.dbconn.createStatement();
            String query = "SELECT * FROM sensor_table" ;
           ResultSet rs = statement.executeQuery(query);
            int num=1;

            out.println("<table cellpadding='0' cellspacing='0' align='center' width='600'>");
             out.println("<tr>");
                  out.println("<th width=30 scope=col>S/N</th>");
                   out.println("<th width=80 scope=col>Snos Type</th>");
                    out.println("<th width=150 scope=col>Sensor</th>");
                     
                      out.println("<th width=80 scope=col></th>");
                      out.println("<th width=80 scope=col></th>");

                  out.println("</tr>");
           
            while (rs.next()) {


                String Snos_Name = rs.getString ("snos_type");
                String sensor = rs.getString ("sensor");
                String sensorid=rs.getString("id");

                    out.println("<tr>");

                  
                    out.println("<td  > "+ num +"</td>");
                    out.println("<td  > "+ Snos_Name +"</td>");
                    out.println("<td  > "+ sensor +"</td>");

out.println("<td> <form method=post action=../AdminRequestProcessing><p><input type=hidden  name='delete_id' value="+sensorid+
        "><input type='submit'"+ "value='Delete'  name='deletesensorB' id='RegButton' /></p></form></td>");


out.println("<td> <form method=post action=../AdminRequestProcessing><p><input type=hidden  name='update_id' value="+sensorid+
        "><input type='submit'"+ "value='update'  name='updatesensorB' id='RegButton' /></p></form></td>");



                    out.println("</tr> ");


                 num++;
               }



            dbconnector.dbconn.close();
            out.println("</table> ");
          }
      catch (SQLException exc) {

                    out.println("<html><body>"+"<h3>No Information Found</h3><br>" +
                     "<a href="+"><b>Home</b></a>&nbsp;&nbsp;&nbsp;&nbsp;");

         }



%>

        </table>
  </div></td>
<%@include  file="footer.jsp" %>
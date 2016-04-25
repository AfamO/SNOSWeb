<%-- 
    Document   : View_Registered_administrator
    Created on : Apr 26, 2013, 6:50:48 AM
    Author     : Charles
--%>

<%@include  file="header.jsp" %>

 <td valign="top">
     <!....start code here ....!>
        <div  id="ViewTableContainer">
        <table width="450"  cellspacing="0" cellpadding="0"  align="center" class="border">

      

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
            String query = "SELECT * FROM sensor_user" ;
           ResultSet rs = statement.executeQuery(query);
            int num=1;

            out.println("<table cellpadding='0' cellspacing='0' align='center' width='600'  >");
            out.println("<tr>");
                  out.println("<th width=30 scope=col>S/N</th>");
                   out.println("<th width=80 scope=col>Name</th>");
                    out.println("<th width=150 scope=col>user Identity code</th>");
                     out.println("<th width=80 scope=col>Password</th>");
                      out.println("<th width=80 scope=col>Status</th>");

                      out.println("<th width=80 scope=col></th>");
                      out.println("<th width=80 scope=col></th>");
                 
                  out.println("</tr>");
                   

            while (rs.next()) {


                String user_Name = rs.getString ("user_name");
                String user = rs.getString ("user_id");
                String password=rs.getString("password");
                String userid =rs.getString("uid");

                String status=rs.getString("status");

                  out.println("<tr>");


                    out.println("<td  > "+ num +"</td>");
                    out.println("<td  > "+ user_Name +"</td>");
                    out.println("<td  > "+ user +"</td>");
                    out.println("<td  > "+ password +"</td>");
                    out.println("<td  > "+ status +"</td>");


out.println("<td> <form method=post action=../AdminRequestProcessing><p><input type=hidden  name='delete_id' value="+userid+
        "><input type='submit'"+ "value='Delete'  name='deleteAdminB' id='RegButton' /></p></form></td>");


out.println("<td> <form method=post action=../AdminRequestProcessing><p><input type=hidden  name='update_id' value="+userid+
        "><input type='submit'"+ "value='update'  name='updateadminB' id='RegButton' /></p></form></td>");



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
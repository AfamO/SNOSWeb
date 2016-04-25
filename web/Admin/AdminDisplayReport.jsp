<%-- 
    Document   : AdminDisplayReport
    Created on : Apr 24, 2013, 6:11:18 AM
    Author     : Charles
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

  <%@include  file="header.jsp" %>

  <td valign="top">
      <div><a href="AdminReport.jsp">Search Again with Snos Type</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="AdminDateSearch.jsp">Search Again with Date</a></div>

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
           String para=(String)session.getAttribute("searchItem");

      try {
           
          statement = dbconnector.dbconn.createStatement();
        // String query = "SELECT * FROM sms_in where snos_type='"+para+"'"+"OR re_time like'%"+para+"%' ORDER BY re_time  ASC LIMIT";
                 //"' OR re_time like'%"+para+"%' ORDER BY re_time  ASC LIMIT ";
         
           String query = "SELECT * FROM sms_in WHERE snos_type = '"+ para +  "'" + " OR re_time like'%" + para +"%' ORDER  by  re_time";
           ResultSet rs = statement.executeQuery(query);
            int num=1;
               if(rs.getRow()==0)
                   {
                   out.println("<table cellpadding='0' cellspacing='0' align='center' width='500'>");
                    out.println("<tr>");
                   out.println("<td > No Information Found</td>");

                  out.println("</tr> ");

                  out.println("</table> ");
                   }else{
            out.println("<table cellpadding='0' cellspacing='0' align='center' width='500'>");
            out.println("<tr>");
                  out.println("<th width=30 scope=col>S/N</th>");
                   out.println("<th width=80 scope=col>Sender</th>");
                    out.println("<th width=150 scope=col>Message Contents</th>");
                     out.println("<th width=80 scope=col>Date</th>");
                      out.println("<th width=80 scope=col>Status</th>");

                     // out.println("<th width=80 scope=col></th>");
                     // out.println("<th width=80 scope=col></th>");

                  out.println("</tr>");


            while (rs.next()) {


                String Sender = rs.getString ("snos_type");
                String Tex = rs.getString ("msg").toUpperCase();
                String date1 = rs.getString ("re_time");
                String status = rs.getString("status");

                // data= new String []{Sender,Tex,date1,status};


                // String link=req.getContextPath()+"/Admin/login.jsp";
                   // String link2=req.getContextPath()+"/Admin/Administrator.jsp";


                    out.println("<tr>");
                    out.println("<td  > "+num +"</td>");
                    out.println("<td  > "+ Sender +"</td>");
                    out.println("<td  > "+ Tex +"</td>");
                    out.println("<td  > "+ date1 +"</td>");
                    out.println("<td  > "+ status +"</td>");

                    out.println("</tr> ");


                 num++;
               }

           dbconnector.dbconn.close();
            out.println("</table> ");
          }
            }
      catch (SQLException exc) {
             out.println(para);
                   // String link=req.getContextPath()+"/Admin/login.jsp";
                   // String link2=req.getContextPath()+"/Admin/Administrator.jsp";

                    out.println("<html><body>"+"<div style='margin-left:250px;'><h3>No Information Found</h3><br>" +
                     "<a href="+"><b>Home</b></a>&nbsp;&nbsp;&nbsp;&nbsp;" +
                     "<a href="+"><b>Search Again</b></a></div></body></html>");

         }


 





/*
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
           Connection con = DriverManager.getConnection("jdbc:odbc:student");
Statement st=con.createStatement();
ResultSet rs=st.executeQuery("select * from data where name like 'a%'");
while(rs.next()){
out.println(rs.getString("name")+"<br>");
}
 
 */
%>
        </table>
  </div></td>
  
<%@include  file="footer.jsp" %>
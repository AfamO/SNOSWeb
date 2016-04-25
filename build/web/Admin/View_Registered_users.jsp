<%-- 
    Document   : View_Registered_users
    Created on : Apr 24, 2013, 9:42:14 AM
    Author     : Charles
--%>

 <%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include  file="viewheader.jsp" %>


   <td valign="top" >
   <!....start code here ....!>
      <div  >

        <table  cellspacing="0" cellpadding="0"  align="center" >

      

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
            String query = "SELECT * FROM snos_client ";
           ResultSet rs = statement.executeQuery(query);
            int num=1;

            out.println("<table cellpadding='0' cellspacing='0' align='center' width='100%' border='1' id='viewUser' >");
              out.println("<tr>");
                  out.println("<th width=30 scope=col>S/N</th>");
                   out.println("<th width=80 scope=col>Name</th>");
                    out.println("<th width=50 scope=col>Mobile Number</th>");
                     out.println("<th width=150 scope=col>Location</th>");
                      out.println("<th width=80 scope=col>Contact Number</th>");
                      out.println("<th width=80 scope=col>Area</th>");
                      out.println("<th width=80 scope=col>Building Type</th>");
                     out.println("<th width=80 scope=col>State</th>");
                      out.println("<th width=80 scope=col>LGA</th>");
                      out.println("<th width=50 scope=col>Snos Type</th>");
                     out.println("<th width=80 scope=col>E-mail</th>");
                      out.println("<th width=80 scope=col>Contact E-mail</th>");
                      out.println("<th width=50 scope=col>Password</th>");


                      out.println("<th width=80 scope=col></th>");
                      out.println("<th width=80 scope=col></th>");

                  out.println("</tr>");
            while (rs.next()) {


                String ClientName = rs.getString ("Client_name");
                String fone = rs.getString ("fone");
                String contact = rs.getString ("contact");
                String loco = rs.getString("loco");
                String area = rs.getString("area");
                String build_type=rs.getString("build_type");
                String state = rs.getString("state");
                String lga=  rs.getString("lga");
                String snos_type=rs.getString("snos_type");
                String Client_email=rs.getString("Client_email");
                String Contact_email=rs.getString("Contact_email");
                String pass =rs.getString("password");

                // data= new String []{Sender,Tex,date1,status};


                // String link=req.getContextPath()+"/Admin/login.jsp";
                   // String link2=req.getContextPath()+"/Admin/Administrator.jsp";


                    out.println("<tr>");
                    out.println("<td  width='10' > "+ num +"</td>");
                    out.println("<td width='80' > "+ ClientName +"</td>");
                    out.println("<td   width='30'> "+ fone +"</td>");
                    out.println("<td  width='100'> "+ loco +"</td>");
                    out.println("<td   width='40'> "+ contact +"</td>");
                    out.println("<td  width='100'> "+ area +"</td>");
                    out.println("<td  width='50'> "+ build_type +"</td>");

                    out.println("<td width='50' > "+ state +"</td>");
                    out.println("<td width='30' > "+ lga +"</td>");
                    out.println("<td width='20' > "+ snos_type +"</td>");
                    out.println("<td width='50' > "+ Client_email +"</td>");
                    out.println("<td  width='50'> "+ Contact_email +"</td>");
                    out.println("<td width='50' > "+ pass +"</td>");


                    out.println("</tr> ");


                 num++;
               }



            dbconnector.dbconn.close();
            out.println("</table> ");
          }
      catch (SQLException exc) {

                   // String link=req.getContextPath()+"/Admin/login.jsp";
                   // String link2=req.getContextPath()+"/Admin/Administrator.jsp";

                    out.println("<html><body>"+"<h3>No Information Found</h3><br>" +
                     "<a href="+"><b>Home</b></a>&nbsp;&nbsp;&nbsp;&nbsp;");

         }



%>

        </table>
  </div></td>
  
<%@include  file="footer.jsp" %>
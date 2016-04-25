<%-- 
    Document   : View_Registered_snos
    Created on : Apr 25, 2013, 2:51:29 AM
    Author     : Charles
--%>


 <%@include  file="header.jsp" %>

  <td valign="top">
     <!....start code here ....!>
        <div  id="ViewTableContainer">
    
        <table width="450"  cellspacing="0" cellpadding="0"  align="center" >

    

<%@ page import="java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;"%>

<%

    Statement statement;

    // session.setAttribute("/Admin/Updatesnos.jsp","update");
     // session.setAttribute("/Admin/deleteSnos.jsp","delete");

   snossoftwaretest.Connector1 dbconnector = new  snossoftwaretest.Connector1();

            dbconnector.databaseConnect();
          
      try {

          statement = dbconnector.dbconn.createStatement();
            String query = "SELECT * FROM snos_table" ;
           ResultSet rs = statement.executeQuery(query);
            int num=1;

            out.println("<table cellpadding='0' cellspacing='0' align='center' width='700'>");
             out.println("<tr>");
                  out.println("<th width=30 scope=col>S/N</th>");
                   out.println("<th width=80 scope=col>Snos Type</th>");
                    out.println("<th width=150 scope=col>Mobile Number</th>");
                    
                      out.println("<th width=80 scope=col></th>");
                      out.println("<th width=80 scope=col></th>");

                  out.println("</tr>");
            // out.println("<tr><td> </td></tr>");
            while (rs.next()) {


                String ClientName = rs.getString ("snos_type");
                String fone = rs.getString ("fone");
                
              

               // String Updatelink= (String)session.getAttribute("update");
                
                // String Deletelink= (String)session.getAttribute("delete");


                   // String link2=req.getContextPath()+"/Admin/Administrator.jsp";


                    out.println("<tr>");
                   
                   //out.println( "<td  ><a href=Updatesnos.jsp"+"><b>update</b></a></td");

                    out.println("<td  > "+ num +"</td>");
                    out.println("<td  > "+ ClientName +"</td>");
                    out.println("<td  > "+ fone +"</td>");

out.println("<td> <form method=post action=../AdminRequestProcessing><p><input type=hidden  name='delete_id' value="+ClientName+
        "><input type='submit'"+ "value='Delete'  name='deletesnosB' id='RegButton' /></p></form></td>");


out.println("<td> <form method=post action=../AdminRequestProcessing><p><input type=hidden  name='update_id' value="+ClientName+
        "><input type='submit'"+ "value='update'  name='updatesnosB' id='RegButton' /></p></form></td>");


                   
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
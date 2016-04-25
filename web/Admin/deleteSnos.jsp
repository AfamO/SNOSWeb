<%-- 
    Document   : deleteSnos
    Created on : Apr 25, 2013, 3:10:53 AM
    Author     : Charles
--%>

 <%@include  file="header.jsp" %>

<td valign="top">
        <div  >
        <table width="450"  cellspacing="0" cellpadding="0"  align="center" class="border">


    <%@ page import="java.sql.*;
    import java.io.*;"%>

    <%

     Statement statement;

    // session.setAttribute("/Admin/Updatesnos.jsp","update");
     // session.setAttribute("/Admin/deleteSnos.jsp","delete");

   snossoftwaretest.Connector1 dbconnector = new  snossoftwaretest.Connector1();

            dbconnector.databaseConnect();
    String deletepara=(String)session.getAttribute("snosdeletePara");

    dbconnector.databaseConnect();


          try {
            statement = dbconnector.dbconn.createStatement();
            String query = "DELETE FROM snos_table WHERE snos_type = '" + deletepara + "'";
            int result = statement.executeUpdate( query );
             statement.close();

             out.println("<h3> Information Deleted Successfuly</h3><br>" +
           "<a href=login.jsp"+"><b>Home</b></a>&nbsp;&nbsp;&nbsp;&nbsp;" +
           "<a href=View_Registered_snos.jsp"+"><b>Click to delete Again</b></a>");


          }catch (SQLException exc) {

            out.println(exc.toString());
        }

    %>
        </table>
</div>
       
   

<%@include  file="footer.jsp" %>
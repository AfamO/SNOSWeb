<%-- 
    Document   : deleteAdministrator
    Created on : Apr 26, 2013, 6:49:57 AM
    Author     : Charles
--%>

<%@include  file="header.jsp" %>

<td valign="top">
        <div id="LoginTableContainer">
        <table width="450"  cellspacing="0" cellpadding="0"  align="center" class="border">


    <%@ page import="java.sql.*;
    import java.io.*;"%>

    <%

    Statement statement;


   snossoftwaretest.Connector1 dbconnector = new  snossoftwaretest.Connector1();

            dbconnector.databaseConnect();
    String deletepara=(String)session.getAttribute("AdmindeletePara");

    dbconnector.databaseConnect();


          try {
            statement = dbconnector.dbconn.createStatement();
            String query = "DELETE FROM sensor_user WHERE uid = '" + deletepara + "'";
            int result = statement.executeUpdate( query );
             statement.close();

             out.println("<h3> Information Deleted Successfuly</h3><br>" +
           "<a href=login.jsp"+"><b>Home</b></a>&nbsp;&nbsp;&nbsp;&nbsp;" +
           "<a href=View_Registered_administrator.jsp"+"><b>Click to delete Again</b></a>");


          }catch (SQLException exc) {

            out.println(exc.toString());
        }

    %>
        </table>
</div>

<%@include  file="footer.jsp" %>

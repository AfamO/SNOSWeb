<%-- 
    Document   : test2
    Created on : May 2, 2013, 2:29:45 AM
    Author     : Charles
--%>

<%@ page import="java.sql.*" %>
<%

//String bis = request.getParameter("status");

String name = request.getParameter("name").toString();
String buffer="<div>";
 Statement statement;

   snossoftwaretest.Connector1 dbconnector = new  snossoftwaretest.Connector1();

            dbconnector.databaseConnect();

      try {

          statement = dbconnector.dbconn.createStatement();
          ResultSet rs=statement.executeQuery("select * from sms_in where snos_type like '"+name+"%'");
while(rs.next())
{
buffer=buffer+rs.getString("name")+"<br>";
}
buffer=buffer+"</div>";
response.getWriter().println(buffer);
}
catch (Exception e) {
System.out.println(e);
}
%>
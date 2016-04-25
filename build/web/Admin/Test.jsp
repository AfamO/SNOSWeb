<%-- 
    Document   : Test
    Created on : May 5, 2013, 10:28:13 AM
    Author     : Charles
--%>

<%@page import="java.util.*,java.sql.*,java.io.*" %>
<%@page import="javax.servlet.*" %>
<%@page import="javax.servlet.http.*" %>

<%! Connection con; %>
<%! Statement s; %>
<%! ResultSet rs; %>
<%! PreparedStatement pst; %>
<%
try{

 Class.forName("com.mysql.jdbc.Driver");
 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/snossmsdb", "root", "");
          
    s=con.createStatement();


 String query = "SELECT * FROM snos_table" ;
           ResultSet rs = s.executeQuery(query);
            int num=0;

            out.println("<table cellpadding='0' cellspacing='0' align='center' width='700'>");
            // out.println("<tr><td> </td></tr>");
            while (rs.next()) {


                String ClientName = rs.getString ("snos_type");
                String fone = rs.getString ("fone");

                    out.println("<tr>");

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
    out.println("</table> ");
}
catch(Exception e)
{ 
    
    e.printStackTrace();

}
%>

<html>
<body>

</body>
</html>

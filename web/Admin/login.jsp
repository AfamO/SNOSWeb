<%-- 
    Document   : login
    Created on : Apr 18, 2013, 8:17:09 AM
    Author     : Charles
--%>


   <%@include  file="AdminLoginheader.jsp" %>

<div>
<table cellpadding="0" cellspacing="0" width="100%" align="center">
 <tr>
 <td>
 <div id="formContainer"> 

			<form id="login" method="post" action="../AdminRequestProcessing" name="loginForm" onsubmit="return ValidateLogin();" >

				<a href="#" id="flipToRecover" class="flipLink">Forgot?</a>

             <input type="text" name="userid" id="loginEmail" placeholder="Username" />

				<input type="password" name="password" id="loginPass" placeholder="Password" />


				<input type="submit" name="submitB" value="Login" />

			</form>


            <%@ page import="java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;"%>


<%

  Statement statement;

   snossoftwaretest.Connector1 dbconnector = new  snossoftwaretest.Connector1();
   snossoftwaretest.UserInforGet record = new snossoftwaretest. UserInforGet();

            dbconnector.databaseConnect();

             String userName=(String)session.getAttribute("LoginName");
             String pass =(String)session.getAttribute("PasswordName");
             
             
             //String buttonVal=

            // if()

      try {

          statement = dbconnector.dbconn.createStatement();

          String query = "SELECT * FROM snos_client WHERE user_id = '" + userName + "'"+"AND password = '" +pass+"'" ;

           ResultSet rs = statement.executeQuery(query);
           // int num=0;

             if(rs.next()){


                 %>


			<form id="recover" method="post" action="Admin_system.php">

				<a href="#" id="flipToLogin" class="flipLink">Forgot?</a>
				<input type="text" name="adminuser" id="loginEmail" placeholder="Username" style="top: 138px;" />
				<input type="password" name="usernamepass" id="recoverEmail" placeholder="Old Password" style="top: 178px;" />
				<input type="password" name="regpassword" id="recoverEmail" placeholder="New Password" />

				<input type="submit" name="subB" value="Submit" />

			</form>

		</div>
        <div id="phpMess"> </div>



        </td></tr>
        <tr><td height="5">
        &nbsp;&nbsp;
        </td></tr>


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




</table>
</div>

<%@include  file="footer.jsp" %>

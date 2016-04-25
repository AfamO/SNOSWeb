
<%@include  file="header.jsp" %>

        <td valign="top">
        <div  id="LoginTableContainer">
        <table width="450"  cellspacing="0" cellpadding="0"  align="center" class="border">

<form action="../AdminRequestProcessing" method="post" name="sensorForm" onsubmit="return ValidateSensorRegistration();">
<tr>
     <td width="400" height="30" bgcolor=" "  align="center" colspan="2" style="padding-top:10px;">
     <b>&nbsp;&nbsp;<font face="Times New Roman" size="5" color="blue">Sensor Registration</font></b>

     <br><font size=2 color=red>

${sensoremptyAlert}

<%  session.removeAttribute("sensoremptyAlert"); %>
 </font></td>
</tr>

<%@ page import="java.sql.*;
import java.io.*;"%>

<%

    Statement statement;

    snossoftwaretest.Reconsoft count2= new snossoftwaretest.Reconsoft();

   snossoftwaretest.Connector1 dbconnector = new  snossoftwaretest.Connector1();

dbconnector.databaseConnect();

       try {
                statement = dbconnector.dbconn.createStatement();

                String query = "SELECT * FROM snos_table " ;
                ResultSet rs = statement.executeQuery(query);
                int num = 0;
                num = count2.CountRegisteredSNOS();
                num = num + 1;
               // String record[] = new String[num];
                //record[0] = "Choose one";
                %>


<tr>
<td style="padding-left:10px;">
<font face= "Times New Roman" size = "3" color="blue">Snos Type:*&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>
<select name="snos_type" size="1">
    <option value="0">Choose-one</option>

   <% for (int i = 1; i < num; i++){
                    rs.next();

            //record[i] = (rs.getString(2));
                    //if (i == num -1){
                       // return record;
                   // }
                    %>

 
<option value="<%out.println(rs.getString(2));%>"><%out.println(rs.getString(2));%></option>

        <%  } %>



</select>

</td>
</tr>

<tr>
<td height="5">
&nbsp;&nbsp;

<%

               // return record;

          }catch (SQLException exc) {

          // JOptionPane.showMessageDialog(null,exc.toString( ));
            //String[] record = {"No Information"};
           // return record;
        }

%>
</td>
</tr>

<tr>
<td style=" padding-left:10px;">
<font face= "Times New Roman" size = "3" color="blue">Sensor Type:*</font>
<input type="text" size = "30" name = "sensor" maxlength="50" placeholder="Enter Sensor Type"><br><font size=2 color=red>

${senserrorAlert}

<%  session.removeAttribute("senserrorAlert"); %>
 </font>
</td>
</tr>

<tr>
<td height="5">
&nbsp;&nbsp;
</td>
</tr>
<tr><td align="center" width = "100%"  height= "20" colspan="2" >
<input type="submit" value="Submit" name="SensorSub" style="border:1px solid #698EEF; padding-left:4px; padding-right:4px; padding-top:1px; padding-bottom:1px; color:#FFF;background-color:#009999">
 &nbsp; <input type="reset" value="Reset" name="can" style="border:1px solid #698EEF; padding-left:4px; padding-right:4px; padding-top:1px; padding-bottom:1px; color:#FFF;background-color:#009999">
</td></tr> </form>

<tr>
      <td td align="center" width = "100%"  height= "20" colspan="2" >
          
     </td>
    </tr>
</table>
</div></td>


<%@include  file="footer.jsp" %>
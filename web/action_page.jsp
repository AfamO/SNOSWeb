<%--
    Document   : device_information
    Created on : Apr 16, 2013, 3:29:24 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.Vector" isELIgnored="false"%>

<%@include  file="header.jsp" %>


<tr>
 <td width="100%" valign="top" >

 <table width="100%"  cellspacing="0" cellpadding="0"  >

  <tr>


<td  class="mbg" width="250" valign="top">


<table cellpadding="0" cellspacing="0" align="center" width="100%">

<tr><td align="center"><fieldset class="smsFieldSet"><legend><font FACE = "tahoma" SIZE = "2" COLOR ="green"><%=session.getAttribute("user")  %>'s Control Panel</font></legend>
<%@include file="control_panel.jsp" %>
</fieldset></td></tr>




<tr><td  align="center" >
<a href="#"><img src="resources/slideimage/snosimage1.png"  class="tableborder"  style="margin-top:10px; width:250px;"></a>

</td></tr>


<tr><td  align="center" >
<a href="#"><img  src="resources/images/Helpsnos.png" class="tableborder"  style="margin-top:10px; width:250px" ></a>

</td></tr>

<tr><td height="5" >
  <p>&nbsp;&nbsp;&nbsp;</p>
  </td></tr>
</table>



</td>





<td width="750"  valign="top">

<table cellpadding="0" cellspacing="0" width="100%" align="center">

<tr><td  width="100%" style="padding-left:20px; padding-right:20px; padding-top:10px; padding-bottom:20px">
<!--Registration codes  Starts here -->


<div  id="ViewsmsTableContainer">


		
       


   

 <%
 //<legend class="style105" id="TableActionPageContainer">ACTION PAGE </legend>
 String obj="";
    Vector row=(Vector)session.getAttribute("row");
    if(row.size()==0){
		  obj= "<table width='100%' height='40' cellpadding='0' cellspacing='0' align='center'><tr>";
          obj+="<td height='35' align='center' class='text'>";
           obj+=" <FONT FACE = 'ARIAL BLACK' SIZE = '2' COLOR ='blue'> We are sorry to inform you that there are <i>No Actions Found!</i> for you.<br>Thank you.</FONT></div></td> </tr></table>";
           //out.println(output);
		   }
    else {
         

                    int count=1;

					 obj+="<table cellpadding='0' cellspacing='0' width= '80%' border=1 align='center' id='ActionPageTable '>";
					 obj+=" <caption>";
					 obj+="	<strong><font face= 'Tahoma' size = '4' color='blue'>SNOS ACTION TABLE:<I>"+row.size()+"</I> ACTIONS HAVE BEEN  TAKEN SO FAR</font></strong>";
					 obj+=" </caption>";

					 obj+=" <tr>";
					obj+="<th width=46 scope=col><font face= 'Times New Roman' size = '3' color='blue'>SN</font></th>";
                    obj+="<th width=46 scope=col><font face= 'Times New Roman' size = '3' color='blue'>SENSOR</font></th>";
                    obj+="<th width=20 scope=col><font face= 'Times New Roman' size = '3' color='blue'>ACTION TYPE</font></th>";
                    obj+="<th width=110 scope=col><font face= 'Times New Roman' size = '3' color='blue'>RECIPIENT NAME</font></th>";
                    obj+="<th width=46 scope=col><font face= 'Times New Roman' size = '3' color='blue'>RELATIONSHIP</font></th>";
                    obj+="<th width=110 scope=col><font face= 'Times New Roman' size = '3' color='blue'>ADDRESS</font></th>";
					obj+="<th width=46 scope=col><font face= 'Times New Roman' size = '3' color='blue'>GSM NO</font></th>";
					//obj+="<th width=50 scope=col>EMAIL</th>";
					obj+="<th width=46 scope=col><font face= 'Times New Roman' size = '3' color='blue'>TEXT SENT</font></th>";
                    obj+="<th width=46 scope=col><font face= 'Times New Roman' size = '3' color='blue'>DATE SENT</font></th>";
					 obj+="</tr>";
                    for(int i=0;i<row.size();i++){
                String []data=(String[])row.get(i);
                if(data[1].equals("e"))
                    {
                        data[1]="EMAIL";
                    }
                    else if(data[1].equals("s"))
                        {
                            data[1]="SMS";
                        }
                if(i==3)
                    {
                    break;
                    }
					obj+="<tr>";
					 obj+="<td>"+count+++"</td>";
					obj+="<td>"+data[0]+"</td>";
					obj+="<td>"+data[1]+"</td>";

					obj+="<td>"+data[2]+"</td>";
                    obj+="<td>"+data[3]+"</td>";
					obj+="<td>"+data[4]+"</td>";

					obj+="<td>"+data[5]+"</td>";
                    //obj+="<td>"+data[6]+"</td>";
					obj+="<td>"+data[7]+"</td>";

					obj+="<td>"+data[8]+"</td>";
				obj+="</tr>";
                }

				obj+="</table>";

}




out.println(obj);
 %>


 
</div>
</td>

</tr>

</table>
</td>

</tr>
</table>
</td>
</tr>

<%@include  file="footer.jsp" %>
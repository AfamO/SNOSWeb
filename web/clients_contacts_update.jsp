<%--
    Document   : clients_contacts
    Created on : Apr 16, 2013, 2:32:24 PM
    Author     : Admin
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false"%>

<%@include  file="header2.jsp" %>


<tr>
 <td width="100%" valign="top" >

 <table width="100%"  cellspacing="0" cellpadding="0"  >

  <tr>





<td  class="mbg" width="250" valign="top">


<table cellpadding="0" cellspacing="0" align="center" width="100%">

<tr><td align="center"><fieldset class="smsFieldSet"><legend><font FACE = "tahoma" SIZE = "3" COLOR ="green"><%=session.getAttribute("user")  %>'s Control Panel</font></legend>
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


<td width="650"  valign="top">
<table width="100%"  cellpadding="0" cellspacing="0" align="center" >

<tr><td  width="100%" valign="top" >
<!--Registration codes  Starts here -->


<div  id="RegistrationPageTable">

<table width="100%"  cellpadding="0" cellspacing="0" align="center"  style="padding-left:20px; padding-right:20px; padding-top:10px; padding-bottom:20px"  valign="top" class="tabborder">
<tr>
     <td width="400" height="30"  align="center" colspan="2" valign="top">
     <b><img src="resources/images/sign.gif" width="70" height="30"  align="absmiddle"/>&nbsp;&nbsp;<font face="Times New Roman" size="5" color="blue">
     Clients Contacts Profile/Update:<%=(Integer)session.getAttribute("did")+1 %> of ${rs} </font></b></td>
</tr>
<tr>
     <td width="400" height="30" bgcolor=" "  align="center" colspan="2">
     <font face="Times New Roman" size="3" color="red">
     ${errmsg}</font></td>
</tr>

<%session.removeAttribute("errmsg"); %>




<tr><td  width = "100%">
    <div style=" color:blue;padding-left:40px;">
    Note:The Result of your Update is seen immediately you click <em>'Update Contact'</em> Button
    </div></td></tr>
<form onsubmit="return UpdateFormValidation3();"  method='POST'  action='servlets/RequestProcessing'  name='form3' >
                   <tr><td align='center' width = '100%'  height= '20' > <font face= 'Times New Roman' size = '3' color='blue'>Name:</font><span style='color:#FF0000'>*</span><br /><input type='text' size = '30' value=' ${val[0]}' name = 'nam' maxlength='50' placeholder='Name'  style='margin-right:30px;'></td>
				   <td align='center' width = '100%'  height= '20' ><font face= 'Times New Roman' size = '3' color='blue'>E-mail:</font><span style='color:#FF0000'>*</span>
                    <input type='text' size = '30' value=' ${val[3]}' name = 'email1' maxlength='50' style='margin-right:30px;' >
                    </td>
					</tr>


                    <tr><td  align='center' width = '100%'  height= '20' >
                   <font face= 'Times New Roman' size = '3' color='blue'>Relationship/Position:</font><span style='color:#FF0000'>*</span>
                    <br /><input type='text' size = '30' value=' ${val[1]}' name = 'relation_pos' maxlength='50'></td><td align='center' width = '100%'  height= '20' ><font face= 'Times New Roman' size = '3' color='blue'>Mobile Number:</font><span style='color:#FF0000'>*</span>
                    <input type='text' size = '30' value=' ${val[4]}' name = 'fone' maxlength='50' style='margin-right:30px;' >
                   </td></tr>

                    <tr><td  align='center' width = '100%'  height= '20' >
                    <font face= 'Times New Roman' size = '3' color='blue'>Address:</font><span style='color:#FF0000'>*</span>
                    <br /><textarea cols='20' rows='4' name='address'> ${val[2]} </textarea></td></tr><tr><td height='5'>&nbsp;&nbsp;</td></tr>
					
					<tr><td align='center' width = '100%'  height= '20' colspan='2' >

                     <%
        String next="<input type='submit' value='Next >>' name='action' class='buttonDisplay'>";
        String prev="<input type='submit' value='<< Previous' name='action'  class='buttonDisplay'>";
		
         if((Integer)session.getAttribute("did")==0 && (Integer)session.getAttribute("rs")!=1)
        {
            out.println(next);
        }

        else if((Integer)session.getAttribute("rs")==(Integer)session.getAttribute("did")+1 && (Integer)session.getAttribute("rs")!=1)
        {
            out.println(prev);
        }
        else if((Integer)session.getAttribute("did")<(Integer)session.getAttribute("rs") && (Integer)session.getAttribute("rs")!=1)
        {
                out.println(prev+"&nbsp;");out.println(next);
        }


        %>

                    <input type='submit' value='Update Contact'  name='action'  class="buttonDisplay">
                    &nbsp; <input type='reset' value='Reset' name='can' class="buttonDisplay">
                    <input type='hidden' value='reg33' name='id' >
                         <input type="hidden" value="desktop" name="source" >
                    </td></tr> 
					</form>
					
					
<tr><td td align='center' width = '100%'  height= '20' colspan='2' >
<font color='#FF0000'>  </font>
</td></tr>
                    
</table>



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
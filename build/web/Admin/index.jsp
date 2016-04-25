<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>:::SNOS PROJECT:::</title>
<meta name="description" content="Security website">
<meta name="keywords" content="Security, Networks,monitoring">

<link rel="shortcut icon"  href="resources/images/favicon.ico" type="image/x-icon" />
<link href="resources/css/style.css" rel="stylesheet" type="text/css">
<link href="resources/css/loginCSS.css" rel="stylesheet" type="text/css">
<style type="text/css">
<!--
.style1 {color: #003366}
-->
</style>

<script src="resources/Script/admin_script.js" type="text/javascript"></script>
<script type="text/javascript">
    function ValidateLoginMessage()
{

var username=document.forms["LoginForm"]["userid"];
var password=document.forms['LoginForm']['password'];
//var usernameFormat3= /^[a-zA-Z]+$/;
//var passwordFormat= /^[0-9a-zA-Z]{5,12}+$/;

/*
if(username== "")
{
	window.alert("User name should not be Empty");
	document.forms['LoginForm']['userid'].focus();
	return false;
}
if( password== "")
{
window.alert("password should not be Empty");
document.forms['LoginForm']['password'].focus();
return false;
}*/
var usernameFormat3= /^[a-zA-Z]{5,15}$/;
var passwordFormat = /^[a-zA-Z]+[0-9]{2,}$/;
if(username.valueOf().value=="")
{
window.alert('Username should not be empty');
document.forms["LoginForm"]["userid"].focus();
return false;
}else if(!username.value.match(usernameFormat3)){
 window.alert('Username must have alphabet characters of 5 to 15 only e.g teledom');
document.forms["LoginForm"]["userid"].focus();
return false;
}else if(password.valueOf().value==""){
window.alert('Password should not be empty');
document.forms['LoginForm']['password'].focus();
return false;
}else if(!password.value.match(passwordFormat)){
 window.alert('Password should be Alphanumeric characters only e.g teledom04');
document.forms['LoginForm']['password'].focus();
return false;
}

return true;
}


function ValidateUserName(checkname)
{
var usernameFormat3= /^[a-zA-Z]+$/;
if(!checkname.valueOf().value=="" && checkname.value.match(usernameFormat3))
{
return true;
}
else
{
window.alert('Username must have alphabet characters only');
document.checkname.focus();
return false;
}
}

function ValidatePassword(checkpassword)
{
var passwordFormat = /^[0-9]+$/;
if(!checkpassword.valueOf().value=="" && checkpassword.value.match(passwordFormat))
{
return true;
}
else
{
window.alert('password must be numeric characters only');
document.checkpassword.focus();
return false;
}
}

</script>
</head>

<body>
<table width="1100" height="100%"  cellspacing="0" cellpadding="0" align="center"  class="border">
  <tr>
    <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="1">
        <div style="position:absolute; width:400px; top:20px; background:url('resources/images/cn-bg.gif');">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="1"><img  src="resources/images/logo.jpg" alt="" width="61" height="61" class="logo"></td>
    <td class="company_name">SECURITY NETWORK OPERATING SYSTEM</td>
  </tr>
</table>
        </div>
        <div  id="slogan">
        SNOS<br/>
        <img src="resources/images/w17.gif" width="17" height="1">...Securing Your World</div>
        <img  src="resources/images/bansnos.png" alt="" width="780" height="196"></td>
        <td class="hbg">&nbsp;</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td height="100%" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="188" valign="top">
        <div style="position:relative; top:0px; width:188px;">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td class="mbg"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td><p><img src="images/spacer.gif" alt="" width="1" height="14"></p>
          <p>&nbsp;</p></td>
      </tr>
      <tr>
        <td class="menu"><a href="index.jsp">Home</a></td>
      </tr>
      <tr>
        <td height="1"><img src="resources/images/m-sep0.gif" alt="sep0" width="188" height="1"></td>
      </tr>
      <tr>
        <td class="menu"><a href="administrator.jsp">Add Admin</a></td>
      </tr>
      <tr>
        <td height="1"><img src="resources/images/m-sep.gif" alt="sep4" width="188" height="1"></td>
      </tr>
      <tr>
        <td class="menu"><a href="Sensor_registration.jsp">Sensors</a></td>
      </tr>
      <tr>
        <td height="1"><img src="resources/images/m-sep.gif" alt="sep2" width="188" height="1"></td>
      </tr>
      <tr>
        <td class="menu"><a href="Snos_registration.jsp">SNOS</a></td>
      </tr>
      <tr>
        <td height="1"><img src="images/m-sep.gif" alt="sep3" width="188" height="1"></td>
      </tr>
      
      <tr>
        <td class="menu"><a href="View_Registered_administrator.jsp">View Admin</a></td>
      </tr>
      <tr>
        <td height="1"><img src="resources/images/m-sep.gif" alt="sep" width="188" height="1"></td>
      </tr>
      <tr>
        <td class="menu"><a href="View_Registered_users.jsp">View users</a></td>
      </tr>

      <tr>
        <td height="1"><img src="resources/images/m-sep.gif" alt="sep" width="188" height="1"></td>
      </tr>
      <tr>
        <td class="menu"><a href="View_Registered_sensor.jsp">View Sensors</a></td>
      </tr>

      <tr>
        <td height="1"><img src="resources/images/m-sep.gif" alt="sep" width="188" height="1"></td>
      </tr>
      <tr>
        <td class="menu"><a href="View_Registered_snos.jsp">View SNOS</a></td>
      </tr>
     <tr>
        <td height="1"><img src="resources/images/m-sep.gif" alt="sep" width="188" height="1"></td>
      </tr>
      <tr>
        <td class="menu"><a href="AdminDisplayReport.jsp">Generate Report</a></td>
      </tr>

      <tr>
        <td><p><img src="resources/images/spacer.gif" alt="" width="1" height="12"></p>
          <p>&nbsp;</p>
          <p align="center">Lets help<img src="resources/images/hep.jpg" alt="help me" width="100" height="60"></p></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td align="right" bgcolor="#FFFFFF"><img src="resources/images/m-bottom.gif" alt="" width="156" height="110"></td>
  </tr>
</table>

        </div>
        </td>
        <td valign="top">
        <div  id="LoginTableContainer">
        <table width="450"  cellspacing="0" cellpadding="0"  align="center" class="border">
        
         <! ....Code starts here....!>
          <tr><td align="center" class="style1">
          Administrators Login Area
          <div>Manage and Maintain security status Activities
          </div>
          </td>
          </tr>
          <tr>
<td height="5"  align="center">
<font size=2 color=red>

${emptyLoginAlert} ${LoginerrorAlert}

<%  session.removeAttribute("emptyLoginAlert");
    session.removeAttribute("LoginerrorAlert");%>
 </font>
</td>
</tr>
          <form  method="post" action="../AdminRequestProcessing" name="LoginForm" onsubmit=" return ValidateLoginMessage();">

              <tr><td align="center" height="10"> 
             <input type="text" name="userid"  id="loginEmail" placeholder="Username"  class="border"/>
             
             </td></tr>
                  <tr><td align="center" height="10">
				<input type="password" name="password"  id="loginPass" placeholder="Password" class="border" />
                  </td></tr>
                   <tr><td>
				<input type="submit" name="submit_login" value="Login" id="LoginformInputSubmit" />
               </td></tr>
			</form>
        </table>
        </div>
        </td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td bgcolor="#0b7ca9" class="bot-bg"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td class="bottom_menu"><a href="index.jsp">Home</a> | <a href="about.jsp">About Us</a> | <a href="services.jsp">Services</a> | <a href="signup.jsp">Sign up</a> | <a href="index.jsp">Login</a> | <a href="contact.jsp">Contact Us</a></td>
      </tr>
      <tr>
        <td class="bottom_addr">&copy; 2013 Teledominternational Ltd</td>
      </tr>
    </table></td>
  </tr>
</table>

</body>
</html>
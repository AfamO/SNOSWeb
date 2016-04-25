<%-- 
    Document   : header
    Created on : Apr 16, 2013, 12:34:28 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>:::SNOS:::</title>
<meta name="description" content="Security website">
<meta name="keywords" content="Security, Monitoring,Alert">
<link rel="shortcut icon"  href="resources/images/favicon.ico" type="image/x-icon" />
<link href="resources/css/style.css" rel="stylesheet" type="text/css">
<link href="resources/css/loginDisplayCSS.css" rel="stylesheet" type="text/css">
<link href="resources/css/SlideCss.css" rel="stylesheet" type="text/css">


<script type="text/javascript"  src="resources/Script/jquery-1.8.2.js" ></script>
<script type="text/javascript" src="resources/Script/jquery-ui-1.9.0.custom.min.js" ></script>
<script type="text/javascript" src="resources/Script/jquery-ui-tabs-rotate.js" ></script>
<script type="text/javascript" src="resources/Script/script.js" ></script>
<script type="text/javascript"   src="resources/Script/Tele_script.js" ></script>


</head>

<body>

<table width="80%" height="100%" border="0" cellspacing="0" cellpadding="0" align="center" bgcolor="white">
  <tr>
    <td colspan="2">
	<table   cellspacing="0" cellpadding="0" class="tableborder">
      <tr>
        <td width="1">
        <div style="position:absolute; width:400px; top:15px; background:url('resources/images/cn-bg.gif'); left: 149px;">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="1"><img  src="resources/images/logo.jpg" alt="" width="61" height="61" class="logo"></td>
    <td class="company_name">SECURITY NETWORK OPERATING SYSTEM</td>
  </tr>
</table>
        </div>
		<div id="Welcome" > Hi <%=session.getAttribute("user")  %> </div>

        <div id="slogan">
        SNOS<br/>...Securing Your World</div>
        <img  src="resources/images/bansnos.png" alt="" width="1100" height="196" class="tableborder"></td>

      </tr>

	  <tr>
  <td height="5" >

  </td></tr>

  <tr>
  <td  class="Hyperlink" valign="top" colspan="2"  align="center" height="25">

<table cellpadding="0" cellspacing="0" width="100%">
<tr><td  width="91" align="center"  id="menuHyperlink" >
<a href="index.jsp"><img src="resources/images/home.jpg"  />&nbsp;Home</a>
</td>
<td  width="158" align="center"  id="menuHyperlink">
<a href="about.jsp"><img src="resources/images/arrow.jpg"  />&nbsp;About SNOS</a></td>

<td  width="97" align="center" id="menuHyperlink">
<a href="services.jsp"><img src="resources/images/arrow.jpg"  />&nbsp;Services</a></td>
<td  width="134" align="center" id="menuHyperlink">
<a href="contact.jsp"><img src="resources/images/arrow.jpg" />&nbsp;Contact us</a></td>
<td  width="231" align="center" id="menuHyperlink">
<a href="support_client.jsp"><img src="resources/images/arrow.jpg" />&nbsp;Technical Support</a></td>
<td width="387"   align="right" >
    <div   class="dropdown">
	<a class="account" >
	<span>My Account</span>	</a>
	<div class="submenu" style="display:none; ">

	  <ul class="root">
            <li >
	      <a href="#" >Profile</a>
	    </li>


	    <li >
	      <a href="#" >Contacts Info</a>
	    </li>
	   <li >

	      <a href="#settings">Settings</a>
	    </li>


	    <li>
	      <a href="#">Action Taken</a>
	    </li>
        <li>
	      <a href="#">Change Password</a>
	    </li>


	    <li>
	      <a href="#signout">Sign Out</a>
	    </li>
	  </ul>
	</div>
	</div>&nbsp;&nbsp;&nbsp;</td>
</tr>



  </table>
  </td>
  </tr>

    </table></td>
  </tr>
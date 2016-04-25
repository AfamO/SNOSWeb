<%-- 
    Document   : test1
    Created on : May 2, 2013, 2:28:36 AM
    Author     : Charles
--%>

<%@page import="java.sql.*"%>
<html>
<head>
<script type="text/javascript">
function showData(value){

xmlHttp=GetXmlHttpObject()
var url="test2.jsp";
url=url+"?name="+value;
xmlHttp.onreadystatechange=stateChanged
xmlHttp.open("GET",url,true)
xmlHttp.send(null)
}
function stateChanged() {
if(xmlHttp.readyState==4 || xmlHttp.readyState=="complete"){
    var showdata = xmlHttp.responseText;
    document.getElementById("mydiv").innerHTML= showdata;
    }
}
function GetXmlHttpObject(){
var xmlHttp=null;
try {
  xmlHttp=new XMLHttpRequest();
 }
catch (e) {
 try  {
  xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");
  }
 catch (e)  {
  xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
  }
 }
return xmlHttp;
}
</script>
</head>
<body>
<form name="employee">
<input  type="text" name="name" id="name" onkeyup="showData(this.value);"><br>
<div id="mydiv"></div>

</table>
</body>
</html>
<%-- 
    Document   : myprogress
    Created on : Dec 13, 2013, 2:54:01 PM
    Author     : fupre1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
<head>
  <meta charset="utf-8">
  <title>progressbar demo</title>
  <link rel="stylesheet" href="//code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css">
  <script src="//code.jquery.com/jquery-1.9.1.js"></script>
  <script src="//code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
</head>
<body>

<div id="progressbar"></div>

<script>
$( "#progressbar" ).progressbar({
  value: false
});
</script>

</body>
</html>

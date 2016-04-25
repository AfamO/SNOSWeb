<%-- 
    Document   : process
    Created on : May 20, 2013, 2:59:52 AM
    Author     : Charles
--%>

<%@ page language="java" %>
<%@ page import="java.util.*" %>
<%!

%>
<jsp:useBean id="proHandler" class="snossoftwaretest.InformationProcess" scope="request">
  <jsp:setProperty name="proHandler" property="*"/>
</jsp:useBean>

<jsp:useBean id="checkHandler" class="snossoftwaretest.PatternCheck" scope="request">
<jsp:setProperty name="checkHandler" property="*"/>
</jsp:useBean>

<%
if (proHandler.validate()) {

%>
    <jsp:forward page="success.jsp"/>
<%
   }  else {
%>
    <jsp:forward page="retry.jsp"/>
<%
   }
%>

<%-- 
    Document   : ErrorPage
    Created on : Oct 10, 2011, 9:16:23 AM
    Author     : Afam
--%>


<html>
      <%@include  file="header2.jsp" %>
        <tr>
 <td width="100%" valign="top" >

 <table width="100%"  cellspacing="0" cellpadding="0"  >

  <tr>

<td width="600"  valign="top">
    
<table cellpadding="0" cellspacing="0" width="100%" align="center">

          

		<td width="200"  align="left">

		<table border="1" cellspacing="0" cellpadding="0" width="190" id="leftcol">
	   <tr>
		<td width="182" class="style115" align="center"><span class="style93" >LICENCE:</span><br />
		    <p><span class="style115">This site has been developed for facilitating buying and selling activities Online, Any malicious use of this should not be used for any advertisement or product promotion. By visiting this website your are accepting all legal terms that allows for English Online Shopping.</span></p>
			 <p><span class="style93">We Need Your Help </span><br />
			 Help us advertising - any link ranks us higher and draws more users to our site.
       Help us getting boards - we can put bidding contest and bidding panels in BQ database instantly.</p>

			<p><span class="style93">HIGHLIGHT:</span><br />
			<span class="style115">A - Mac laptops<br />
                        B - Vano Loptops<br />
                        C - Shoes<br />
                        D - Underwear<br />
                        E - Motorla Phones<br />
                        F - Dell Loptops<br />
                        G - Nokia Mobile </span></p> <br />
		  &nbsp;<br />          </td>
         <td width="8">&nbsp;</td>
        </tr>
		
		
      </table>
	
	</td>
	
	<td width="660" height="400" valign="top"  bgcolor="#000000">
<table border="0" cellpadding="0" cellspacing="0"  align="center">
            <tr>
			<td>
                <p align="center"><span class="style87">Truth about bidding </span> </p>
            <p align="center"><span class="style115"> You are declarer about 25% of time.
              You are defender about 50% of time.
              But you bid on every board. With accurate bidding you don't even need to play that well although for best results you should know more than the basics Same in defence. Bid well and force opponents into a bad contract. Bridge IS bidding, a kind of language between 2 people. Practice your bidding language skills here at Bidding Quest. It is the path to becoming a better bridge player! </span></p>
            <br />         
			 </td>
          </tr>
        </table>
    
    <td width="660" >
	<table border="0" cellspacing="0" cellpadding="0" >
		<tr>
		<td >
		<fieldset>
		<legend class="style93">My Error Page </legend>
		<p><span class="style105">
          <%
        String m=(String)request.getAttribute("javax.servlet.error.message");
        %>
        <h3 class="style93">An error has occured.</h3>
        <p class="style105">Your request was not processed,hence this site<br>
        is unavailable to process your requset.Please<br>
        send error info to the Administrator at<br>
        <i><%= application.getInitParameter("admin_email") %></i> to describe<br>
            the problem(s).With <b><%=m%></b> listed as the cause
            of the error.
         </p>
            </span></p>
		
		</fieldset>		</td>
		</tr>
		</table>
		</td>

	</table>
	</td></tr>
	
         <%@include  file="footer2.jsp" %>
  
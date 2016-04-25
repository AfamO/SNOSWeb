
<%--
    Document   : device_information
    Created on : Apr 16, 2013, 3:29:24 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.Vector" isELIgnored="false" %>

<%@include  file="header2.jsp" %>

<%
//these variables hold what will be printed out
String output="";String output1="";
 
String navig=""; // holds the navigation link
//Is sms Vector row not found in the session. I mean is sms not stored in the user session
if(session.getAttribute("row")==null && session.getAttribute("min")==null)
{
    //then it must be that the user has no sms available fot him/her
    output= "<table width='100%' height='40' cellpadding='0' cellspacing='0' align='center'><tr>";
    output+="<td height='95' align='center' class='text'>";
    output+=" <FONT FACE = 'ARIAL BLACK' SIZE = '2' COLOR ='blue'> We are sorry to inform you that you are not permitted to view our SMS page<br>Thank you.</FONT></div></td> </tr></table>";
}
else
{
    //then get the sms row object-since the user has sms alerts
    Vector row=(Vector)session.getAttribute("row");
    //get the starting minimum of his/her alerts in the form of 1-10 of 30. where 1=starting minimum
    int min=Integer.parseInt(session.getAttribute("min").toString());
    //get the currently retrieved sms count of his/her alerts in the form of 1-10 of 30. where 10=currently retrieved sms count
    int currcount=Integer.parseInt(session.getAttribute("smscount").toString());
    //get the total sms count/size of his/her alerts in the form of 1-10 of 30. where 30=total stored sms count.
    int totalcount=Integer.parseInt(session.getAttribute("smstotalcount").toString());
    //if the current retrieved sms count is greater than total sms size
    if(currcount>totalcount)
    {
        //then make the current one to be total so that you will not display something like 1-10 of 5
        //which is wrong since current count should never be greater or higher than total one.
        currcount=totalcount;
    }
    //get the links for both 'next' and 'previous' sms navigation links
    String next="<id='MessageTable' ><a href='servlets/RequestProcessing?currcount="+currcount+"&action=nextsms&source=desktop'>&nbsp;Next>></a></font>";
    String prev="<id='menuHyperlink' class='current_page_item'><a href='servlets/RequestProcessing?currcount="+currcount+"&action=prevsms&source=desktop'><<&nbsp;Previous &nbsp;</a></font>";
    //store these to be used later in javascript
    session.setAttribute("next", next);
    session.setAttribute("prev", prev);
    //Did the user attempt to search something and thus made the servlet to store a search property/attribute called 'serr'
    if(session.getAttribute("serr")!=null && !session.getAttribute("serr").equals(""))
    {
        //get the attribute to display it for the user to see
        output=(String)session.getAttribute("serr");
        //remove it from the session to avoid showing it continously as long as the session lasts.
        session.removeAttribute("serr");
    }
    else
    {
        output+="<br><br><center>";
	output+="<table width=100% id='MessageTable' >";
        //Did the servlet successfully retrieved sms inbox alert or searched alerts for the user?
        if(session.getAttribute("inbox_type")!=null && !session.getAttribute("inbox_type").equals("")|| session.getAttribute("searchtf")!=null && !session.getAttribute("searchtf").equals(""))
        {
            //then display accordingly
            output1+="<td height='10' class='text'>";
            output1+=" <FONT FACE = 'ARIAL BLACK' SIZE = '2' COLOR ='blue'>Done searching for '<i>"+session.getAttribute("searchtf")+"</i>' ?<br><a href='servlets/RequestProcessing?action=sms'>Back To SMS Inbox</a></FONT></td>";
        }
        //cast the stored session attribute-row1-holding the retrieved alerts to Vecotor object to be accessed accordingly
        Vector row1=(Vector)session.getAttribute("row1");
        if(session.getAttribute("row1")!=null)
        {
            //loop through the row object
            for(int i=0;i<row1.size();i++)
            {
                String []data=(String[])row1.get(i);
		//get a new html table row
		output+="<tr>";
                //construct a checkbox
		output+="<td >"+"<input type='checkbox' name='read[$cnt]' style='color: #008000; border: 1px solid #008000;'>"+"</td>";
		//output+="<td><font face='Tahoma' style='bold' size='3' color='blue'>"+data[0]+"</font></td>";
                //get the sms alert text
                output+="<td>"+data[1]+"</td>";
                //get the sms alert date/time
                output+="<td>"+data[2]+"</td>";
                //get the 'action taken' form button to be used
                output+= "<td><form method=post action='servlets/RequestProcessing'><p><input type=hidden  name='sid' value='"+session.getAttribute("sid")+"'>" +
                "<input type='submit' class='Action_tab'  name='action' alt='submit' value='_' /></p></form></td>";
                //get the new row object
                output+="</tr>";
            }
        }
        for(int i=0;i<row.size();i++)
        {
            String []data=(String[])row.get(i);
					//$snos_type=$row1['snos_type'];
					 output+="<tr>";
					 output+="<td >"+"<input type='checkbox' name='read[$cnt]' style='color: #008000; border: 1px solid #008000;'>"+"</td>";
					 //output+="<td><font face='Tahoma' style='bold' size='3' color='blue'>"+data[0]+"</font></td>";
					//output+="<td>"+ou+"</td>";
                      output+="<td>"+data[1]+"</td>";
					 output+="<td>"+data[2]+"</td>";
                                         

        output+= "<td><form method=post action='servlets/RequestProcessing'><p><input type=hidden  name='sid' value='"+session.getAttribute("sid")+"'>" +
                "<input type='submit' class='Action_tab'  name='action' alt='submit' value='_' /></p></form></td>";
				output+="</tr>";

				}



				 output+="</table></center>";
                 }
// if current sms count is zero, then tell the client that there is no sms alert for him/her
// current sms count should be greater than 0 if there were sms alert.
if(currcount==0)
{
    output= "<table width='100%' height='40' cellpadding='0' cellspacing='0' align='center'><tr>";
    output+="<td height='95' align='center' class='text'>";
    output+=" <FONT FACE = 'ARIAL BLACK' SIZE = '2' COLOR ='blue'> We are sorry to inform you that there are <i>No Messages Found!</i> for you.<br>Thank you.</FONT></div></td> </tr></table>";
}
//if the minimum starting point is 1 and current sms count is less or equal to 10
else if(min==1  && currcount<=10)
{
    //Is the total sms size less or equal to 10
    if(totalcount<=10)
    {
        //then make the current one to be total so that you will not display something like 1-10 of 5
        //which is wrong since current count should never be greater or higher than total one.
        currcount=totalcount;
        //then construct the appropriate navigation link for this case.
        navig += " "+min+"- "+currcount+" of "+totalcount;
    }
    //what if it is greater or equal to 10
    else if(totalcount>10)
    {
        //still do as above but add the 'next' button so that the client can see the next batch of his/her sms alerts
        navig += " "+min+"- "+currcount+" of "+totalcount+next;
    }
    else{
        //then don't add any link since both must be equal in this case.
        navig += " "+min+"- "+currcount+" of "+totalcount;
    }
}
//Is the current count greater than 10?
else if(currcount>10)
{
    //Is it less than totalcount
    if(currcount<totalcount)
    {
        //then add both 'precious' and 'next' links so that the client can navigate either way.
        navig += prev+" "+min+"- "+currcount+" of "+totalcount+next;
    }
    //what is both are equal?
    else if(currcount==totalcount)
    {
        //then add ONLY 'previous ' link
        navig +=prev+" "+min+"- "+ currcount + " of " + totalcount;
    }
}
//store these attributes to be later used in Javascript event source-for asynchronous sms alert retrieval
session.setAttribute("min", min);
//session.setAttribute("currcount", currcount);
session.setAttribute("totalcount", totalcount);

 }
//store this attribute to be later used in Javascript event source-for asynchronous sms alert retrieval
session.setAttribute("moutput", output);

 %>


<script type="text/javascript">
    /*
    * This just resets whatever element is in the div object
    */
    function reset(elObject)
    {
        if(elObject !== null && elObject.hasChildNodes())
        {
            for(var i = 0; i < elObject.childNodes.length; i++)
            {
                elObject.removeChild(elObject.firstChild);
            }
        }
    }
    /*
    * This uses-HTML5/Javascript Event Source- method constantly checks the server and retrieves latest sms alerts
    * and displays it to the client,it also updates the navigation link. 
    */
    function setupEventSource()
    {
        //get the client snos number stored in the session.
        var sid="${sid}";//the snos id or snos number
        //get the constructed alert message output stord in the session at the jsp code session.
        var mout="${moutput}";    
        //alert("sid="+sid);
        var output1 = document.getElementById("sms_alert_inbox_id");//get the div to display sms on
        var leftNav = document.getElementById("leftNavig");//get the div for left navigation
        var rightNav = document.getElementById("rightNavig"); //get the div for right navigation
        reset(output1);
        //display the jsp session generated/stored alerts
        output1.innerHTML=mout;
        //Is the event source object recognized?             
        if(typeof(EventSource) !== "undefined")
        {
            //get a new instance of event source passing the event source servlet name
            //called 'mysse' along with the snos number/id
            var source = new EventSource("mysse?sid=" +sid);
            //did a new message or alert arrived?
            source.onmessage = function(event) {
            var navig="";// the navigator link 
            //clears all the data/elements in previous obtained div elements
            reset(output1);
            reset(leftNav);
            reset(rightNav);
            var alertOutput="";
            //get the data and pass it as JSON object
            var data=JSON.parse(event.data);
            //var medata=JSON.stringify(data);
            //construct the html output table for display 
             alertOutput+="<br><center>";
	     alertOutput+="<table width=100% id='MessageTable' >";
             //get the size of the data returned
             var size=data.size;
             //get the total size of all the alerts for the current user.
             var total_size=data.total_size;
             var alertsArray=data.alert.split("brk");//split the alerts into array using 'brk' as a separator
             var dateArray=data.date.split("brk");//split the dates into array using 'brk' as a separator
             //loop through the resultant alerts and dates array 
             for(var i=0; i<size;i++)
             {
                  //get the alert and dates for this postion i in the loop.
                 $alert= alertsArray[i];
                 $date= dateArray[i];
                 //construct an html table row 
                 alertOutput+="<tr>";
                 //construct the checkbox as did in jsp code session
                 alertOutput+="<td >"+"<input type='checkbox' name='read[$cnt]' style='color: #008000; border: 1px solid #008000;'>"+"</td>";
                 //include the alert and date here.
                 alertOutput+="<td>"+$alert+"</td>";
                 alertOutput+="<td>"+$date+"</td>";
                 //get the form object for 'action taken' as likewise defined in jsp code session above.
                 alertOutput+= "<td><form method=post action='servlets/RequestProcessing'><p><input type=hidden  name='sid' value='"+sid+"'>" +
                 "<input type='submit' class='Action_tab'  name='action' alt='submit' value='_' /></p></form></td>";
                 alertOutput+="</tr>";
                 //log them in the console for debugging purposes
                 console.log($alert);
                 console.log($date);
                 //This shows ONLY the first 10 latest alerts at each point in time.
                 if(i===9)
                 {
                     //then terminate the loop since the loop has reached the 10th position.
                     break;
                 }
             }
             alertOutput+="</table></center>";
             //then update the alert sms div to display the new alerts for the user
             output1.innerHTML=alertOutput;
             var min=data.min;// the current alert minimum range
             var current_count=data.sms_current_count; //the retrived alert current sms count
             //Is total sms size greater than 10
             if(total_size>10)
             {
                 //then include 'Next' ONLY link in the navigation
                 navig += min+"- "+current_count+" of "+total_size+"${next}";
             }
             else if(total_size===10)
             {
                 //don't include any of the links
                 navig += min+"- "+current_count+" of "+total_size;
             }
             else
             {
                 //then don't include any too, since total sms size is less than 10 in this case
                 //and the latest sms are already displayed, so there is neither NEXT or PREVIOUS alerts to see
             }
             leftNav.innerHTML=navig; //update the left navigation link
             rightNav.innerHTML=navig;//update the right navigation link
             console.log(event.data);//log the data in the console for debugging purposes
             };
             //then close the EventSource object
             source.addEventListener('close', function(event) {
            source.close();
            }, false);
            source.onerror=function(error)
            {
                alert("We are sorry to inform you that new alerts could not be retrieved for you.\nPlease do contact the web administrator\nThank you");
                console.log('Failed to Start EventSource: ', error);
                output1.innerHTML=mout;
                //output1.innerHTML="We are sorry to inform you that new alerts could not be retrieved for you.\nPlease do contact the web administrator\nThank you";
                
            };
        } 
        else
        {
            output1.innerHTML = "Sorry, Server-Sent Events are not supported in your browser";
        }
        return false;
    }
        //here call the above javascript event source method to be activated immediately the page loads
	$(document).ready(function(){
        setupEventSource();
        //
	});
</script>
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
<a href="#"><img src="resources/slideimage/snosimage1.png"  class="tableborder"  style="margin-top:10px; width:100%;"></a>

</td></tr>


<tr><td  align="center" >
<a href="#"><img  src="resources/images/Helpsnos.png" class="tableborder"  style="margin-top:10px; width:100%" ></a>

</td></tr>

<tr><td height="5" >
  <p>&nbsp;&nbsp;&nbsp;</p>
  </td></tr>
</table>
</td>

<td width="750"  valign="top">

<table cellpadding="0" cellspacing="0" width="100%" style="margin-top:5px; margin-left:25px;"  >


<tr>

  <td height="10" align="left">
<div>
<input type="text" name="web" size="40" placeholder="Search From Web" style="color: #008000; border: 1px solid #008000;height:20px;padding-left:10px;" class="tabborder" />
<input type="submit" name="action" value="Search"  class="buttonDisplay">
</div>
</td>

<td height="10" align="right">
   
<form onsubmit="return SearchFormValidation();" action="servlets/RequestProcessing" method="post" id="f3">
<input type="text" onkeyup="reSetSMSDiv()" name="searchtf" value="${searchtf}" size="40" placeholder="Search-By Date or Message-From Inbox"  style="color: #008000; border: 1px solid #008000;height:20px;padding-left:10px;" class="tabborder" />
<input type="submit" name="action" value="Search"  class="buttonDisplay"><input type="hidden" name="source" value="desktop"><input type="hidden" name="id" value="s1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
</form></td>
<td><%//out.println(output1);%></td>
</tr>

<tr>

     <td width="400" height="10"  align="center" colspan="2">
     <div align="center" id="errmsg"> </div></td>
</tr>

</table>
<div id="aId"></div>
<div id="ViewsmsTableContainer" onmouseover="">
<table cellpadding="0" cellspacing="0" width="100%" align="center"  style="padding-left:20px;color:blue;" >
<tr>
    <td><b id="leftNavig"><%out.println(navig);%></b>&nbsp;<font face="Times New Roman" size="2" color="blue">THE SIZE OF RETURNED INBOX IS </font>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <b id="rightNavig"><%out.println(navig);%></b><td align="right"><%out.println(output1);%></td></tr>
<tr>
    <td>
        </td><td></td><td></td><td></td><td></td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <td><div id="sms_alert_inbox_id" class="float:left; "></div>
        
     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 </td>
</tr>
</table>
<!--
<%//out.println(output);%>
!-->
</div>
</td>
</tr>
</table>
</td></tr>

<%@include  file="footer.jsp" %>
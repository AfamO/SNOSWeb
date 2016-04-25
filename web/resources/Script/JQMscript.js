/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * This script is the engine of SNOS Mobile web app. 
 * It contains most of the logic needed to register clients for the first time,
 * views their sms alerts and modify their registered data as well as change 
 * their passwords and clients login and sign outs
 */


var $user="";//This tracks  the client email address when logged in
var $pass=""; // This tracks the client password when logged in
var $RetrievedValueCounter=0; //This is a counter and tracker for the row number of contacts and device/objects data as STORED in Java Vector row object in the servlet. I mean it is the key/index of each row item.
var $RetrievedRowSize=0; // This holds the row size of the all the items retrieved at a point in time
var $IssmsRefreshed=0;   //This tracks whether sms inbox link has been refreshed or not
var $IsUserComfirmed="0"; //This is used to track wether a client who has registered has been confirmed or not.
var $SMSCurrentCount=0;  // This holds the current displayed number of sms alerts.
var $IsUserSignedOut=0;  // This tracks wether a user has signed out or not
var $next,$prev;         // This tracks the previous and next links respectively.
var $log_out="";         // This holds logout message ans string
var resp;                // This is used to get the server response-that is in JSON format
var $SID="";             //This tracks the client's SNOS number.
var $NAME="";            //This tracks the client user NAME
var $UID;                //This tracks the current row number being accessed either in a table row of contacts data or device/objects data AS STORED IN DATABASE TABLE for data UPDATE. It is the row id in the database table used to update the row.
var $currentSMSTotalCount=0;  //This holds the current sms count. I mean in a "1-10 of 20" format. 10= sms current count
var $SMSTotalCount=0;       //This holds the sms total count as retrieved from the server. I mean in a "1-10 of 20" format. 20= sms total count
var $IsInboxCllicked=0;
var $StoredSMSTotalCount=0;


/*
 * 
 * @param {type} data: This represent the JSON server retrieved sms alert data to display in a div element
 * @param {type} $smsDiv: This represent the div in question, where the data is to be displayed.
 * @returns {undefined}
 */
function DisplaySMS(data,$smsDiv){
    var navig,output,next,prev,currcount,min,totalcount;
    var Urlorigin=window.location.origin+"/SnoSSoftwareTest3/";

    var countSMS=0;
    navig="";
    //min-This is minimum. I mean in a "1-10 of 20" format. 1= minimum
    min=data.min;
  
    prev=5;
    //is the minimum value null, this can tell that there is no available sms alert.
    if(min==null){
        //display the appropriate messages
           strHtml= "<center><table data-role='table' data-theme='a' class='ui-responsive'  width=100% id='MessageTable' ><tr>";
          strHtml+="<td height='95' align='center' class='text'>";
           strHtml+=" <FONT FACE = 'ARIAL BLACK' SIZE = '2' COLOR ='blue'> We are sorry to inform you that you are not permitted to view our SMS page<br>Thank you.</FONT></div></td> </tr></table></center>";
    }
    else
        {
            //then get the sms current count. I mean in a "1-10 of 20" format. 10= sms current count
            currcount=data.smscount;
            //I mean in a "1-10 of 20" format. 20= sms total count
            totalcount=data.smstotalcount;
            //Is current count greater than total one?-This can happene when a user is pressing "next" button constantly.
            if(currcount>totalcount)
            {
                //then make the total to be the current
                   currcount=totalcount;
            }
             //then construct the 'next'sms button  adding a 'NextorPreviousSMS' sms method. 
             next="<button type='submitt' data-theme='b' class='buttonDisplay' onclick=' NextorPreviousSMS(true)' data-icon='next' data-inline='true'>Next</button>";
             //then construct the 'previous' sms button adding a 'NextorPreviousSMS' sms method. 
            prev="<button type='submitt' data-theme='b' class='buttonDisplay' onclick=' NextorPreviousSMS(false)' data-icon='prev' data-inline='true'>Previous</button>";
            
            // assign this to the global variable
            $SMSCurrentCount=currcount;
            
    }
     //build the jqwery mobile table by adding a div element with a data role of table.
    var strHtml="<center><div data-role='table' data-theme='b' class='ui-responsive'  width=100% id='MessageTable' >";
    //Then outer loop the server retrieved json data passed as parameter
    $.each(data, function(i,item){
     
     $.each(this, function(key, value) {
       //do an inner loop
       //This logic is used to ensure that 'undefined' is not printed out when the data retrieved is less than 10. Since the alerts come in 10 batches.
        if(value.sms!=undefined)
            {
                //then print the sms alert and corresponding time in html td element
                strHtml+="<tr>";
                 $SID=value.id;
        // strHtml+="<td >"+value.id+"</td>";
          strHtml+="<td>"+value.sms+"&nbsp;&nbsp;</td>";
           strHtml+="<td>"+value.date+"&nbsp;&nbsp;</td>";
           //+Urlorigin+"servlets/RequestProcessing
             strHtml+= "<td><form method=post action='void(0)'><p><input type=hidden  name='sid' value='"+value.id+"'>" +
                "<input type='submit' class='Action_tab'  name='action'  value='_' /></p></form></td>&nbsp;&nbsp;";
           strHtml+="</tr>";
           
            }
            else
                {


                }
                // assign the client name to the global $NAME variable to be used through out the page.
               $NAME=data.name;
             
            //console.log(value.id);
        });
        //close the appropriate html tags
         strHtml+="</div></center>";
         });


         if(currcount==0){
             //then display that no alerts where found.
                 strHtml= "<table width='100%' height='40' cellpadding='0' cellspacing='0' align='center'><tr>";
                strHtml+="<td height='95' align='center' class='text'>";
                strHtml+=" <FONT FACE = 'ARIAL BLACK' SIZE = '2' COLOR ='blue'> We are sorry to inform you that there are <i>No Messages Found!</i> for you.<br>Thank you.</FONT></div></td> </tr></table>";
           //out.println(output);
       }
       //if min value =1 and current count is less than or equal to 10, it means that alerts were found.
       else if(min==1  && currcount<=10)
 {
     //Are there still more alerts apart from the first 10 batch.
    if(totalcount>10){
        //then include 'next' button in the navigation link so that the user can view further alerts.
        navig += " "+min+"- "+currcount+" of "+totalcount+" "+next;
        
    }
    else{
        //then don't include 'next' button.
        navig += " "+min+"- "+currcount+" of "+totalcount;
        }
 }
//has the current count exceeded 10
else if(currcount>10)
    {
        //Is it still less than totalcount
        if(currcount<totalcount)
            {
                    //keep displaying 'next' sms button
                    navig += prev+" "+min+"- "+currcount+" of "+totalcount+" "+next;
                   
            }
 //when it now equals to total count and totalcount is greater than 10
 else if(currcount==totalcount && totalcount>10 )
 {
     // then add 'previous' link instead of next so that the user can see previous alerts.
        navig +=prev+" "+min+"- "+ currcount + " of " + totalcount;
       
 }
 }
 //
//Now print all the constructed outputs including the navigation link as well as the actual alerts and their dates.
var Print_AllstrHtml="<center><div data-role='table' data-theme='a' class='ui-responsive'  width=100% id='MessageTable' >";
Print_AllstrHtml+="<tr><td align='center'>"+navig+"</td></tr><tr>";
Print_AllstrHtml+="<tr><td>"+strHtml+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr></div>";
     
        //change the the color theme of the home page
        $("#pagehome").attr("data-theme","b");
        
                //display the final output in the div element passed to this method so that user can now see it.
                 $smsDiv.html(Print_AllstrHtml);
             //navigate to the pageviewsms page so that all these alerts can be seen.
            $.mobile.changePage("#pageviewsms", "slide", false);
}
/*
* This method enables a client to change his/her password.

 * @returns {undefined} */
function ChangePass(){
var $page = $("#pagehome");

//Are the password form fields correctly validated? 
if(ChangePassWordFormValidation())
    {
//then make ajax asynchromous request to the server using  hhtp 'post' method,
//with the belwo url and getting all the fields and values of 'ChangePassForm' form page 
 $.ajax({
type: "POST",
url: window.location.origin+"/SnoSSoftwareTest3/servlets/RequestProcessing?action=Change My Password&snos_type="+$SID,
data: $("#ChangePassForm").serialize(),
cache: false,
async:true,
beforeSend: function() {
 // This callback function will trigger before data is sent
                       
   $.mobile.loading('show'); // This will show ajax spinner
    },
complete: function(data) {

        
},
success: function(data) {
 // hide the spinner since the request has completed successfully.
$.mobile.loading('hide');
//get the status code from the server.
var status=data.status;
//get the corresponding status message.
var msg=data.msg;
// Is it successfull 1: means success, 0: means failure, 3: means processing was halted in the process, perhaps by invalid result at server-side validation.
if(status==1)
    {
    alert(msg+"\n Redirecting you to Home page to go and Relogin");

        //redirects to home page by sliding
       $.mobile.changePage("#pagehome", "slide", false);
      //$page.find(".content").empty();
    }
    else if(status==0)
        {
            //display the error message and direct to thesame page
            alert("Sorry We don't know you,Are you sure you have registered for this service?");
             $("#changePassmsg").html(msg).css("size", "3").css("color", "red");
        }
        else if(status==3)
            {
                //display the error message and still direct to the same page
                alert(msg);
                $("#changePassmsg").html(msg).css("size", "3").css("color", "red");
            }

//var dataArray=jQuery.parseJSON(alertinfo);


},
error: function (xhr, ajaxOptions, thrownError) {
    //error occured
   $.mobile.loading('hide');
   /*
    * note some of these codes are intentionally left for debugging purposes
    alert("The Error breakdown is:\n"+JSON.stringify(xhr));
                        alert("Error Status: " + xhr.status + "\n" +
                               "Message: " + xhr.statusText + "\n" +
                               "Response: " + xhr.responseText + "\n" +
                               "ThrownError: " +  thrownError);
                           //console.log("error" + xhr.status + xhr.statusText);
       */

//go to error page
var $page = $("#pageError .content");
// Build an error message
var strHtml = "<h3>Data Retrieval Failed</h3>";
strHtml += "<p>We were unable to get your info. Please check your internet<br> connection or/and try again later.<br>Thank you</p>"
// Place the message in the error dialog
$page.html(strHtml);
// Show the dialog
//$("#show-error-page").click();
}
});

 }
 else
 {

 }


 }
/*
* This method occassionally refreshes the some pages including alerts page to retrieve the latest alerts

 * @param {type} timeoutPeriod: This is the time interval to do the refreshing.
 * @returns {undefined} */
function timedRefresh(timeoutPeriod) {
	
//set the time interval.
    setInterval(function(){
        //get the current browser url.
        var link1=document.URL;
         //link1="http://localhost:8084/SnoSSoftwareTest3/SnosApp.jsp&action=confirm&sessid=932A03B7B6376D8A9ED480207AE1D0DB&sid=SNOS7&source=mobile";
       // alert("The document.URL="+document.URL);
        var linkArray=link1.split("&", 120);
        //find out if there is 'comfirm' and 'sessid' keywords which suggest that the user want to comfirm his/her registrations.
        if(link1.indexOf("confirm", 40)!=-1 && link1.indexOf("sessid", 40)!=-1 && $IsUserComfirmed!=1)
            {
                //then call the method that will process the confirmation process.
                ConfirmClientRegistration(link1);
                $IsUserComfirmed=1;
            }
            else
                {
                   
                    
                }
                //if the user's name is not set globally, meaning that the client is not logged in
                if($NAME==""){
                    //do nothing
                }
                else
                    {
                        //then automatically refresh the client's sms alerts by re-connecting to the server to retrieve new ones.
                        $IssmsRefreshed=1;
                        ClientLogin(true);
                    }
                
    },timeoutPeriod);
   // reSetSMSDiv();
    //alert("Who are you?");
}

/*
 * This method performs client registration, collects clients data and submitt them to the server
 * @param {type} obj: This means the stage and type of form to register:: form1= first stage, form2=2nd stage and form3=3rd stage of registration
 * @returns {undefined}
 */
function ClientRegistration(obj) {
// get all the form fields with their corrsponding values.
var formData = $("#"+obj).serialize();
//get the url to use
var strUrl = window.location.origin+"/SnoSSoftwareTest3/servlets/RequestProcessing?source=mobile";
var Isvalid=false;
//Is this the 3rd stage
if(obj=="form3")
                {
                    //then let the url include 'Submitt' action parameter value instead of normal 'Next >>' value
                    strUrl += "&action=Submitt";
                }
                else
                    {
                        //this means that this is not the 3rd stage and further step is to be taken.
                        strUrl += "&action=Next >>";
                        
                    }
                    //Is valid is used to authenticate the form values of respective stage of the registration
                    if(obj=="form1"){
                        Isvalid=FirstFormValidation();
                    }
                    else if(obj=="form2")
                        {
                            Isvalid=FormValidation2();
                            strUrl+="&snos_type="+$SID;//attach the snos type or SNOS number
                        }
                        else
                            {
                                  Isvalid=FormValidation3();
                                  strUrl+="&snos_type="+$SID;
 
}
//Is the correct stage properly validated?
if(Isvalid)
{
    //then make ajax asynchromous request to the server using  hhtp 'post' method,
//with the appropriate url and getting all the fields and values of appropriate  form page
    $.ajax({
        url: strUrl,
        data: formData,
        type: 'POST',
        cache: false,
        async: 'true',
       beforeSend: function() {
           //once again show the ajax spinner before sending the request
               $.mobile.loading('show');
                       
    },
        complete: function(data) {
                    
                },
        success: function (response) {
            //$.mobile.pageLoading(true);
            //hide the spinner after wards
           $.mobile.loading('hide');
           //get the response status and the corresponding msg.
        var status=response.status;
        var msg=response.msg;
        
        //Is the status 1: success, 0: failure, 3: failure in the process
        if(status==1)
        {
           alert(msg);
           //Was the last request made from the 1st stage page form?
            if(obj=="form1")
                {
                    //then logically move to the 2nd stage
                    $.mobile.changePage("#pageregisterdevice");
                }
                //Was the last request made from the 2nd stage page form?
                else if(obj=="form2")
                    {
                        //then logically move to the last stage of registration
                         $.mobile.changePage("#pageregistercontacts");
                    }
                    else
                        {
                            //display the appropriate message with red color
                            $("#errmsgss3").html(msg).css("size", "3").css("color", "red");
                            
                        }
                    }
    else if(status==0)//0:failure. Display failure error message
        {
            alert("Sorry We don't know you,Are you sure you have registered for this service?");
        }
        else if(status==3) //3: failure in the process of execution
        //Display the appropriate error message depending on the registration stage.
        {
            if(obj=="form1"){
                $("#errmsgss1").html(msg).css("size", "3").css("color", "red");
            }
            else if(obj=="form2"){
                $("#errmsgss2").html(msg).css("size", "3").css("color", "red");
            }
            else
            {
                $("#errmsgss2").html(msg).css("size", "3").css("color", "red");
            }

           
           
            
        }
        else
            {
                alert("OOOps! something went wrong. Try again later.<br>Thank you");
            }


        },
        error:function(response,status,er) {
            $.mobile.pageLoading(true);
            //display error message with contacting the server
           // alert("error: "+response+" status: "+status+" er:"+er+"\nDetails:\n"+JSON.stringify(response));
            alert("Error occured communication with the server");
           

        }

    });
}
else{ }
     
}
/*
* This just get the value of object passed to it.

 * @param {type} obj
 * @returns {unresolved} */
function getInputvalue(obj){
    var value=obj.value;
    return value;
}
/*
 * This just displays the comfirm message-with his user name- before the user signs out.
 * @param {type} name: The user name in question
 * @returns {Boolean}
 */
function display(name)
{
var ans1=confirm("Dear "+name+",\nAre you sure that you want to logout?");
// return the apprpriate boolean variable depending on whether the user comfimred that
// he is signing out or not.
if(ans1==false)
    {
        return false;
    }
    else
        {
            return true;
        }

}
/*
 * This resets and clears off a div object passed to it
 * to clear off already containing values.
 * @param {type} elObject
 * @returns {undefined}
 */
function reset(elObject){
    if(elObject != null && elObject.hasChildNodes(  )){
        for(var i = 0; i < elObject.childNodes.length; i++){
            elObject.removeChild(elObject.firstChild);
        }
    }
}
/*
 * This allows the client to Login either to view his sms alerts 
 * or to continue from where he stopped at the registration message-that is 
 * if he has not completed the process.
 * @param {type} bool
 * @returns {undefined}
 */
function ClientLogin(bool){
// get the servlet url.
var strUrl = window.location.origin+"/SnoSSoftwareTest3/servlets/RequestProcessing?";
var $page = $("#pageviewsms");
//get the div element to use to display the retrieved sms alerts.
var $smsDiv = $("#sms");
//tell the servlet that you want to perform Login and that you are coming from a mobile app
strUrl += "action=Login&source=mobile";
// Assign the user and passowrd fields to the respective global variables for
//appropriate tracking later on.
 $user=$("#user").val();
 $pass=$("#pass").val();
//Does the user want to reload the sms page
if(bool==true)
    {
        //then re-assign these user parameters so that then can be re-picked up from the form fields 
        ////when sending the requests back to server again
        $("#user").val($user);
        $("#pass").val($pass);
       
    }
    else
        {

        }
        //get the Login Form fields and their corresponding values to be sent to servlet in the server.
        var formData = $("#LoginForm").serialize();

//Is the login validation process successfull?
if(LoginFormValidation())
    {
     // Then make the appropriate ajax GET request using the above url
 $.ajax({
url: strUrl,
type: "GET",
data: formData,
cache: false,
beforeSend: function() {
    //show this only if the user is coming for the first time. I mean his name
    //is not yet in the global variable.
       if($NAME==""){
            $.mobile.loading('show'); // This will show ajax spinner
       }
       else
           {
                
           }
      
    },
complete: function(data) {

 

},
success: function(data) {

// Then get the status once again
var status=data.status;
//get the corresponding message
var msg=data.msg;
//if the user name has not been stored globally
 if($NAME=="")
        {
            //them make this to be total sms count
             $SMSTotalCount=data.smstotalcount;
        }
        else
            {
                //them make this to be the total sms count
                $currentSMSTotalCount=data.smstotalcount;
            }
//This tells the client the number of 'new sms alerts' that he/she has
if($IssmsRefreshed===1 && $currentSMSTotalCount>$SMSTotalCount){
 //get the number of new alerts received and display it
var numberofNewSMS=$currentSMSTotalCount-$SMSTotalCount;
 $("#searcherr").html("You have "+numberofNewSMS+" NEW ALERT(S)").css("size", "3").css("color", "blue");
       }
       else
           {
               //hide the spinner
                $.mobile.loading('hide');
           }
var strHtml="<center><table data-role='table' data-theme='a' class='ui-responsive' data-mode='columntoggle' width=100% id='MessageTable' >";
//Did the client succed in displaying the alerts
if(status==1)
    {
        //then show the correct control panels
        $("#myCpanel").show(); $("#myCpanel1").show(); $("#myCpanel2").show(); $("#myCpanel3").show(); $("#myCpanel4").show();
              $("#myCpanel5").show();
     if($currentSMSTotalCount>$SMSTotalCount)
            {
                 $currentSMSTotalCount=0;
                 //then appropriately formats and display the alerts
                 DisplaySMS(data,$smsDiv);
                
            }
            //If the user is just loggin in the first time. I mean sms inbox 'refresh' has not been done
             if($NAME==""){
               //then appropriately formats and display the alerts
                DisplaySMS(data,$smsDiv);
            }
            else{
                
            }
            
    //$log_out=display($user);
    }
    else if(status==0)
        {
            alert("Sorry We don't know you,Are you sure you have registered for this service?");
        }
        else if(status==2)
            {
                 alert("We regret to inform you that there are no messages found for you.");
            }
        else if(status==3)
            {
                 alert(msg);
                 $("#Logingmsg").html(msg).css("size", "3").css("color", "blue");
            }
            else if(status=="3a")// Stopped at the 1st stage of registration
            {
                //then continue from the second stage of registration
                //get the snos type.
                $SID=data.snos_type;
                 $("#errmsgss2").html(msg).css("size", "3").css("color", "blue");
                  $.mobile.changePage("#pageregisterdevice");
            }
            else if(status=="3b")// Stopped at the 2nd stage of registration
            {
                //then continue from the third stage of registration
                //get the snos type.
                 $SID=data.snos_type;
                 $("#errmsgss3").html(msg).css("size", "3").css("color", "blue");
                  $.mobile.changePage("#pageregistercontacts");
            }
            else if(status=="3c") // Stopped at the 2nd stage of registration
            {
                //then continue by sending email and re-displaying this 3rd page with appropriate message 
                //that a comfirmation email has been sent
                //get the snos type.
                     $SID=data.snos_type;
                  $("#errmsgss3").html(msg).css("size", "3").css("color", "blue");
                  $.mobile.changePage("#pageregistercontacts");
            }
},
error: function (xhr, ajaxOptions, thrownError) {
     $.mobile.loading('hide');

}
});
 }
 else
 {

 }


 }

/*
* This performs sms alert search.

 * @returns {undefined} */
function SearchSMSInbox(){
//get the general servlet url
var strUrl = window.location.origin+"/SnoSSoftwareTest3/servlets/RequestProcessing?";
var $page = $("#pageviewsms");
//get the sms div that will display the search result
var $smsDiv = $("#sms");
//tell the servlet that you want to perform a search action and that you are coming from mobile background
// and that SNOS number type is as attached
strUrl += "action=Search&source=mobile&inbox_type=search&id=s1&snos_type="+$SID;

//get the appropriate search field name and the corresponding search item
 var formData = $("#SearchForm").serialize();

//Is the search form validation performed accurately?
if(SearchFormValidation())
    {
        //then make the appropriate ajax request.
 $.ajax({
url: strUrl,
type: "GET",
data: formData,
cache: false,
beforeSend: function() {

      $.mobile.loading('show'); // This will show ajax spinner
    },
complete: function(data) {

},
success: function(data) {
    //Then hide the spinner once again.
  $.mobile.loading('hide');
 // get the status and appropriate msg
var status=data.status;

var msg=data.msg;
var strHtml="<center><table data-role='table' data-theme='a' class='ui-responsive' data-mode='columntoggle' width=100% id='MessageTable' >";
//<thead><tr><th>CustomerID</th><th>SMS</th><th>ALERTDATE</th><th>ACTION TAKEN</th></tr></thead>
if(status==1)
    {
    // display the formatted sms output
    DisplaySMS(data,$smsDiv);
   $("#searcherr").html("These are the items found for your search:").css("size", "2").css("color", "blue");
    }
    else if(status==0)
        {
            alert("Sorry We don't know you,Are you sure you have registered for this service?");
        }
        else if(status==2)
            {
                 alert("We regret to inform you that there are no messages found for you.");
            }
        else if(status==3)
            {
                 alert(msg);
                 $("#Logingmsg").html(msg).css("size", "3").css("color", "blue");
            }
            




},
error: function (xhr, ajaxOptions, thrownError) {
     $.mobile.loading('hide');
}
});
 }
 else
 {

 }


 }

/*
* Performs the thorough validation of search form field.

 * @returns {Boolean} */
function SearchFormValidation(){
 //window.alert("Thank you");
 var errmsg=document.getElementById("searcherr");
 var NameFormat= /^[a-z A-Z0-9=,.]{2,35}$/;
 var searchtf=document.forms["SearchForm"]["searchtf"].value;
 reset(errmsg);
  if(searchtf.trim(searchtf)==""){
    //alert("Search Field is Empty");
    errmsg.innerHTML="Please enter an item to search for in the Search Field.".bold().fontsize("4").fontcolor("red");
    //DisplayError("Please enter an item to search for in the Search Field.","INVALID INPUT");
    document.forms["SearchForm"]["searchtf"].focus();
        return false;
}

else if(!searchtf.trim(searchtf).match(NameFormat))
        {
            //alert('Please enter a valid search item like door open,URGENT,URGENT ASSISTANCE,etc');
             //DisplayError("Please enter a valid search item like door open,URGENT,URGENT ASSISTANCE,etc.","INVALID INPUT");
            errmsg.innerHTML="Please enter a valid search item like door open,URGENT,URGENT ASSISTANCE,etc".bold().fontsize("4").fontcolor("red");
            document.forms["SearchForm"]["searchtf"].focus();
            return false;
        }
    
            else
                {
                   
                   
                    return true;
                }
        }

/*
 * This methods customizes and display error message in a formatted way 
 * @param {type} msg -error messag in question
 * @param {type} title- the title of the error message
 * @returns {undefined}
 */
function DisplayError(msg,title){
var $page = $("#pageError .content");
// Build an error message
var strHtml = "<h3 align='center'>"+title+"</h3>";
strHtml += "<p align='center'>"+msg+"<br>Thank you.</p>"
// Place the message in the error dialog
$page.html(strHtml);
// Show the dialog
$("#show-error-page").click();
}

function NextorPreviousSMS(bool){
    //get servlet url
var strUrl = window.location.origin+"/SnoSSoftwareTest3/";
var $page = $("#pageviewsms");
//get the famous sms div
var $smsDiv = $("#sms");
//Did the user click on the 'Next' sms link?
if(bool==true)
    {
        //construct the appropriate servlet link
        strUrl+="servlets/RequestProcessing?id=gNext&currcount="+$SMSCurrentCount+"&action=nextsms";

    }
    else
        {
            //do similiarly as above
                 strUrl+="servlets/RequestProcessing?id=gPrev&currcount="+$SMSCurrentCount+"&action=prevsms";
        }
        //tell the servlet that your current sms count  and that you are coming from mobile background
      // and that SNOS number type is as attached
        strUrl += "&source=mobile&smscount="+$SMSCurrentCount;
        strUrl+="&snos_type="+$SID;
      
      //then make tha appropriate ajax GET request
 $.ajax({
url: strUrl,
type: "GET",

cache: false,
beforeSend: function() {
                        // This callback function will trigger before data is sent
                        //alert("Pageloading sPinner will start nOw");
                       $.mobile.loading('show'); // This will show ajax spinner
    },
complete: function(data) {



},
success: function(data) {


//alert("Getting Ready to Update SNOS-Success");


$.mobile.loading('hide');

var status=data.status;

var msg=data.msg;

if(status==1)
    {
// on success status from server
//then display the result in a formatted way
    DisplaySMS(data,$smsDiv);

    }
    else if(status==0)
        {
            alert("Sorry We don't know you,Are you sure you have registered for this service?");
        }
        else if(status==2)
            {
                 alert("We regret to inform you that there are no messages found for you.");
            }
        else if(status==3)
            {
                 alert(msg);
                 //$("#Logingmsg").html(msg).css("size", "3").css("color", "blue");
            }
           




},
error: function (xhr, ajaxOptions, thrownError) {
    $.mobile.loading('hide');
     /*
    alert("The Error breakdown is:\n"+JSON.stringify(xhr));
                        alert("Error Status: " + xhr.status + "\n" +
                               "Message: " + xhr.statusText + "\n" +
                               "Response: " + xhr.responseText + "\n" +
                               "ThrownError: " +  thrownError);
                           //console.log("error" + xhr.status + xhr.statusText);
    */

//alert("Getting Ready to Update SNOS-Failed, because of \n\
//"+xhr.statusText+ "\nThe url="+strUrl);

//alert("An error occurred. Please try again. ");
// Get the page
var $page = $("#pageError .content");
// Build an error message
var strHtml = "<h3>Data Retrieval Failed</h3>";
strHtml += "<p>We were unable to get your information.The server may be down.<br> Also, please check your internet connection or/and try again later.<br>Thank you.</p>"
// Place the message in the error dialog
$page.html(strHtml);
// Show the dialog
$("#show-error-page").click();
}
});
 


 }

/*
* This performs client registration comfirmation

 * @param {type} Url
 * @returns {undefined} */
function ConfirmClientRegistration(Url){
//var Url=document.URL;
      //split url to get things like parameters and keywards like action= 'comfirm', sessid='122$%%12233', snostype and source='mobile'
       var linkArray=Url.split("&", 120);
 // get the servlet      
var strUrl = window.location.origin+"/SnoSSoftwareTest3/servlets/RequestProcessing?";
var $Mypage=("#pagePersonalProfile");
var formData=null;
    //attach the result of the above split to the url.
    strUrl += "&"+linkArray[1];
    strUrl += "&"+linkArray[2];
    strUrl += "&"+linkArray[3];
    strUrl += "&"+linkArray[4];

//then make the appropriate ajax request
 $.ajax({
url: strUrl,
type: "GET",
//data: formData,
cache: false,
beforeSend: function() {
                        // This callback function will trigger before data is sent
                        //alert("Pageloading sPinner will start nOw");
                       $.mobile.pageLoading(); // This will show ajax spinner
    },
complete: function(data) {


},
success: function(data) {
  $.mobile.loading('hide');

//alert(JSON.stringify(data));
 //get the status and the corresponding messages.
var status=data.status;
var msg=data.msg;

if(status==1)
    {
      //display the result of the comfirmation and display it by navigating to the comfirm page.
     $("#errmsg4ab").html(msg).css("size", "3").css("color", "blue");
     $.mobile.changePage("#pageConfirmReg");
    }
    else if(status==0)
        {
            alert("Sorry We don't know you,Are you sure you have registered for this service?");
        }
        else
            {
                

                alert(" We are sorry to inform you that there are <i>No Messages Found!</i> for you.<br>Thank you");


            }


},
error: function (xhr, ajaxOptions, thrownError) {
     $.mobile.loading('hide');
    
     $("#errmsg11").html("OOps! something went wrong<br>Your update was not successfully carried out.").css("size", "3").css("color", "blue");
 
    // display the error
//alert("An error occurred. Please try again. ");
// Get the page
var $page = $("#pageError .content");
// Build an error message
var strHtml = "<h3>Data Retrieval Failed</h3>";
strHtml += "<p>We were unable to get your information.The server may be down.<br> Also, please check your internet connection or/and try again later.<br>Thank you.</p>"
// Place the message in the error dialog
$page.html(strHtml);
// Show the dialog
$("#show-error-page").click();
}
});
 //}
 //else
 //{

 //}


 }

/*
* This method allows the client to view his/her clients personal profile

 * @param {type} bool
 * @returns {undefined} */

function ClientProfileView(bool){

var strUrl = window.location.origin+"/SnoSSoftwareTest3/servlets/RequestProcessing?";
var formData=null;
var Isvalid=false;

if(bool===true){
    //tells the servlet that you just want to carry out a profile view action 
    //and that you are coming from the mobile app.
    strUrl += "action=my profile&source=mobile";
    Isvalid=true;
}
else
    {
        // tells the web service servlet that you wan to update profile
        strUrl += "action=Update Profile&source=mobile";
        //get the data fields from form11 and the appropriate values.
         formData = $("#form11").serialize();
         Isvalid=true;
             //FirstFormUpdateValidation();
 }
strUrl+="&snos_type="+$SID;
//is the update form validation successfull?
if(Isvalid)
   {
       //make the needed ajax GET request
 $.ajax({
url: strUrl,
type: "GET",
data: formData,
cache: false,
beforeSend: function() {
                       
$.mobile.loading('show'); // This will show ajax spinner
    },
complete: function(data) {

 
},
success: function(data) {
// Delete the existing list, if any

//alert(JSON.stringify(data));
 $.mobile.loading('hide');

//get the status and the corresponding message.
var status=data.status;
var msg=data.msg;
//Is the server request successfull?
if(status===1)
{
    //Did the user click to update his data?
    if(bool===false){
     //then display the appropriate  success message
     $("#errmsg11").html("Your update was successfully carried out.").css("size", "3").css("color", "blue");
 }
 else
     {
         //else clear the message in this div id.
         $("#errmsg11").html("");
     }
 //then in any case, display the retrieved data on the form fields
$("#fullname").val(data.client_name);
$("#email1").val(data.client_email);
$("#gsm").val(data.fone);
$("#add").val(data.contact);
$("#mystate").val(data.state);
$("#lg").val(data.lga);
    }
    else if(status==0)
        {
            alert("Sorry We don't know you,Are you sure you have registered for this service?");
        }
        else
            {
                if(bool==false){
     $("#errmsg11").html("OOps something went wrong!!!<br>Your update was not successfully carried out.").css("size", "3").css("color", "blue");
 }
 else
     {
         $("#errmsg11").html("");
     }

               $("#errmsg11").html(msg).css("size", "3").css("color", "blue");


            }


},
error: function (xhr, ajaxOptions, thrownError) {
     $.mobile.loading('hide');
     if(bool==false){
     $("#errmsg11").html("OOps!!! something went wrong<br>Your update was not successfully carried out.").css("size", "3").css("color", "blue");
 }
 else
     {
         $("#errmsg11").html("");
     }
   

var $page = $("#pageError .content");
// Build an error message
var strHtml = "<h3>Data Retrieval Failed</h3>";
strHtml += "<p>We were unable to get your information.The server may be down.<br> Also, please check your internet connection or/and try again later.<br>Thank you.</p>"
// Place the message in the error dialog
$page.html(strHtml);
// Show the dialog
$("#show-error-page").click();
}
});
 }
 else
 {

 }


 }

/*
*This method can either view or update the clients device/object data
 * @param {type} bool: This booleans checks wether this function is called to just view the data or update the data.
 * @returns {undefined} */
function DeviceProfileView(bool){
//get the servlet url
var strUrl = window.location.origin+"/SnoSSoftwareTest3/servlets/RequestProcessing?";
var $Mypage=("#pageDeviceProfile");
var formData=null;
var Isvalid=false;
//Do you want to  view the object profile data?
if(bool===true){
    //tell the servlet that you want to just view the device/object data.
    strUrl += "action=device_profile&source=mobile";
    Isvalid=true;
     
}
//Did the user click 'Next' button to view other  next object/device data
else if(bool==="Next"){
    //then tell the servlet accordingly, passing the particular row 'did' to retrieve
    strUrl += "action=Next >>&id=reg22&source=mobile&did="+$RetrievedValueCounter;
   
    Isvalid=true;


}
//Did the user click 'Previous' button to view previous other object/device data
else if(bool=="Prev"){
    //then tell the servlet accordingly, passing the particular row 'did' to retrieve
    strUrl += "action=<< Previous&id=reg22&source=mobile&did="+$RetrievedValueCounter;
     Isvalid=true;
}
//else the client must want to update his/her device/object data.
else
    {
        //then tell the servlet accordingly, passing the particular row 'did' and 
        //database 'uid' which identifies the row to update in the database table.
         strUrl += "action=Update Device&source=mobile&uid="+$UID+"&did="+$RetrievedValueCounter;
         Isvalid=UpdateFormValidation2();
         formData = $("#form22").serialize();
         //set these fields to be empty
         $("#dname").val("");
         $("#dhouse_type").val("");
         $("#desc").val("");
         $("#localObj").val("");
         $("#addre").val("");
         $("#mystate2").val("");
         $("#mylg2").val("");
     }
    strUrl+="&snos_type="+$SID; // attach the snos type.
//Is the form validation successfull? 
if(Isvalid) 
    {
 $.ajax({
url: strUrl,
type: "GET",
data: formData,
cache: false,
beforeSend: function() {
                        // This callback function will trigger before data is sent
                        //alert("Pageloading sPinner will start nOw");
                       $.mobile.loading('show'); // This will show ajax spinner
    },
complete: function(data) {


},
success: function(data) {
// Delete the existing list, if any
//alert("Getting Ready to Update SNOS-Success");
$.mobile.loading('hide');
//Did the client clicked 'Next' or 'Previous' button to view respectively next and previous data?
if(bool=="Next" || bool=="Prev"){
    
     //then get the row number in the servlet Vector object row.
     $RetrievedValueCounter=data.did;
     //increment it by 1-for display- since the vector row normally starts from zero(0)
     var counter=$RetrievedValueCounter+1;
     //get the row size
     $RetrievedRowSize=data.rs;
     //then display the info using html element that resides in this current page whose id is DeviceRowNumber
     $("#DeviceRowNumber").html("Row:"+counter+" of "+$RetrievedRowSize).css("size", "3").css("color", "blue");
     //if the vector row id is zero and row size is not 1
        if($RetrievedValueCounter===0&&$RetrievedRowSize!==1){
         
         //hides the previous button since there is nothing to view previously.
         $("#actionPrevUpdate").hide();
         //but show the next button
           $("#actionNextUpdate").show();
    }
      // if the row size is NOW equal to the current row number(did) and rowsize is not 1
    else if($RetrievedRowSize==$RetrievedValueCounter+1&&$RetrievedRowSize!=1){
        //then logically show previous button but hide next one
         $("#actionPrevUpdate").show();
         $("#actionNextUpdate").hide();
    }
    //Is the row size NOW less than the current row number(did) and rowsize is not 1
     else if($RetrievedValueCounter<$RetrievedRowSize&&$RetrievedRowSize!=1){
         //then show both buttons
         $("#actionPrevUpdate").show();
         $("#actionNextUpdate").show();
    }
   
 }
 else
     {
        //get the database row id tracker-for updating this particular row in the database
        $UID=data.uid;
        //get the Vector row id tracker-for accessing this particular row in the java Vecotor Object.
        $RetrievedValueCounter=data.did;
     }

//get the status and the corresponding message status
var status=data.status;
var msg=data.msg;
//Is the retrieval successfull?
if(status===1)
{
    //Does the client want to carry out update-at this point in time?
    if(bool===false)
    {
        $("#errmsg22").html("Your update was successfully carried out.").css("size", "3").css("color", "blue");
    }
    else
    {
         $("#errmsg22").html("");
    }
    //In any case display the retrieved data in the form fields
    $("#dname").val(data.name);
    $("#dhouse_type").val(data.build_type);
    $("#desc").val(data.descrpt);
    $("#localObj").val(data.loc);

    $("#addre").val(data.contact);
    $("#mystate2").val(data.state);
    $("#mylg2").val(data.lga);
}
//Is there a failure in retrieving process?
else if(status===0)
{
    alert("Sorry We don't know you,Are you sure you have registered for this service?");
}
else
{
    if(bool===false)
    {
        $("#errmsg22").html(msg).css("size", "3").css("color", "blue");
    }
                else
                {
                    $("#errmsg22").html("");
                }
                alert(" We are sorry to inform you that there are no device information found for you.<br>Thank you.");
            }
},
error: function (xhr, ajaxOptions, thrownError) {
    $.mobile.loading('hide');
   
//display the appropriate error message

var $page = $("#pageError .content");
// Build an error message
var strHtml = "<h3>Data Retrieval Failed</h3>";
strHtml += "<p>We were unable to get your information.The server may be down.<br> Also, please check your internet connection or/and try again later.<br>Thank you.</p>"
// Place the message in the error dialog
$page.html(strHtml);
// Show the dialog
$("#show-error-page").click();
}
});
 }//end of  validation if statemtent.
 else
 {

 }


 }

/*
* 
 This method can either view or update the clients contacts data
 * @param {type} bool: This booleans checks wether this function is called to just view the data or update the data
 * @returns {undefined} */
function ContactsProfileView(bool){
//get the servlet-webservice url
var strUrl = window.location.origin+"/SnoSSoftwareTest3/servlets/RequestProcessing?";
var $Mypage=("#pageContactsProfile");
var Isvalid=false;
var formData=null;
//Do you want to  view the object profile data?
if(bool===true){
    //tell the servlet that you want to just view the contacts data
    strUrl += "action=contacts_profile&source=mobile";
    Isvalid=true;
}
else if(bool==="Next"){
    //tell the servlet that you want to just view the next 10 batch of contacts data
    strUrl += "action=Next >>&id=reg33&source=mobile&did="+$RetrievedValueCounter;

   Isvalid=true;


}
else if(bool==="Prev"){
    //tell the servlet that you want to just view the previous 10 batch of contacts data
    strUrl += "action=<< Previous&id=reg33&source=mobile&did="+$RetrievedValueCounter;
    Isvalid=true;
}
else
{
    //tell the servlet that you want to just UPDATE the contacts data using a database table row id(uid)
    //and Java Vector row  id(did)-which tracks the key/index of in the row.
    strUrl += "action=Update Contact&source=mobile&uid="+$UID+"&did="+$RetrievedValueCounter;
    formData = $("#form33").serialize();
    Isvalid=UpdateFormValidation3();
    $("#nam").val("");
    $("#relation_pos").val("");
    $("#gsm1").val("");
    $("#add1").val("");
    $("#email11").val("");

    }
strUrl+="&snos_type="+$SID;


if(Isvalid)
    {
 $.ajax({
url: strUrl,
type: "GET",
data: formData,
cache: false,
beforeSend: function() {
                        // This callback function will trigger before data is sent
                        //alert("Pageloading sPinner will start nOw");
                      $.mobile.loading('show'); // This will show ajax spinner
    },
complete: function(data) {


},
success: function(data) {
// Delete the existing list, if any
//alert("Getting Ready to Update SNOS-Success");
 $.mobile.loading('hide');
//Did the client clicked 'Next' or 'Previous' button to view respectively next and previous data?
if(bool=="Next" || bool=="Prev"){
    //then get the row number in the servlet Vector object row.
     $RetrievedValueCounter=data.did;
     //increment it by 1-for display- since the vector row normally starts from zero(0)
     var counter=$RetrievedValueCounter+1;
     //get the row size
     $RetrievedRowSize=data.rs;
     //then display the info using html element that resides in this current page whose id is ContactRowNumber
     $("#ContactRowNumber").html("Row:"+counter+" of "+$RetrievedRowSize).css("size", "3").css("color", "blue");
     //if the vector row id is zero and row size is not 1
     if($RetrievedValueCounter===0&&$RetrievedRowSize!==1){
         //hides the previous button since there is nothing to view previously.
         $("#actionPrevUpdate2nd").hide();
         //but show the next button
         $("#actionNextUpdate2nd").show();
    }
    // if the row size is NOW equal to the current row number(did) and rowsize is not 1
    else if($RetrievedRowSize==$RetrievedValueCounter+1&&$RetrievedRowSize!=1){
        //then logically show previous button but hide next one.
         $("#actionPrevUpdate2nd").show();
         $("#actionNextUpdate2nd").hide();
    }
    //Is the row size NOW less than the current row number(did) and rowsize is not 1
    else if($RetrievedValueCounter<$RetrievedRowSize&&$RetrievedRowSize!=1){
    //then show both buttons
    $("#actionPrevUpdate2nd").show();
    $("#actionNextUpdate2nd").show();
}

 }
 else
     {
         //get the database row tracker and vector row tracker
        $UID=data.id;
        $RetrievedValueCounter=data.did;
     }
//get also the status and the corresponding message
var status=data.status;
var msg=data.msg;
//Was server request successfull?
if(status===1)
{
    //Does the user want to update his/her contacts profile?
    if(bool===false)
    {
        $("#errmsg33").html("Your update was successfully carried out.").css("size", "3").css("color", "blue");
    }
   else
    {
        $("#errmsg33").html("");
    }
    $("#nam").val(data.name);
    $("#relation_pos").val(data.relat_pos);
    $("#gsm1").val(data.fone);
    $("#add1").val(data.contact);
    $("#email11").val(data.client_email);
}
//Did the request fail?
else if(status===0)
{
    alert("Sorry We don't know you,Are you sure you have registered for this service?");
}
else
{
    if(bool===false)
    {
        $("#errmsg33").html(msg).css("size", "3").css("color", "blue");
    }
    else
    {
        $("#errmsg33").html("");
    }
    $("#errmsg33").html(msg).css("size", "3").css("color", "red");
}

},
error: function (xhr, ajaxOptions, thrownError) {
    $.mobile.loading('hide');
    
var $page = $("#pageError .content");
// Build an error message
var strHtml = "<h3>Data Retrieval Failed</h3>";
strHtml += "<p>We were unable to get your information.The server may be down.<br> Also, please check your internet connection or/and try again later.<br>Thank you.</p>"
// Place the message in the error dialog
$page.html(strHtml);
// Show the dialog
$("#show-error-page").click();
}
});
 }
 else
 {

 }
 
 }
 /*
 * This does the work of signing out somebody who has just registered.
  */
 function LogOff(){
// call a function that will a javascript comfirm dialog box with the client's NAME 
$log_out=  display($NAME);
//get the needed servlet url
var strUrl = window.location.origin+"/SnoSSoftwareTest3/servlets/RequestProcessing?";
var $Mypage=("#pageContactsProfile");
//tell the servlet that you just want to sign out from the web-service and that you are coming from mobile browser
strUrl += "action=exit&source=mobile";
// get a handler for client personal profile page to be used to access the form fields in the page.
var $pppage = $("#pagePersonalProfile");
// get a handler for client device/object profile page to be used to access the form fields in the page
var $dppage = $("#pageDeviceProfile");
// get a handler for client contacts profile page to be used to access the form fields in the page
var $cppage = $("#pageContactsProfile");
//Did the person comfirm that he/she really wants to sign out?
//then make an ajax GET request
if($log_out)
  {
 $.ajax({
url: strUrl,
type: "GET",
//data: formData,
cache: false,
beforeSend: function() {
                        // This callback function will trigger before data is sent
                        //alert("Pageloading sPinner will start nOw");
                      $.mobile.loading('show'); // This will show ajax spinner
    },
complete: function(data) {


},
success: function(data) {
// Delete the existing list, if any
//alert("Getting Ready to Update SNOS-Success");
 $.mobile.loading('hide');
//get the status report
var status=data.status;
//Is it positive-successfull?
if(status==1)
    {
    //then clears out the previously stored global varibales that tracks the user when he Logged In. 
$IsUserSignedOut=1;
$user="";
$pass="";
$NAME="";
var smsDiv = document.getElementById("sms");
var searcherr = document.getElementById("searcherr");
//Also resets the Div element that displays the sms and search info
reset(smsDiv);
reset(searcherr);
//resets other search id elements
$("#searcherr").val("");$("#searchsms").val("");
$("#searcherr").end();
//clears and hides the control panels for all the pages that can be accessed when a client has logged in.
Init();
//Use each of the above handler to clear the also the data in all the form fileds in different form pages used application-wide
$pppage.find("#fullname").val("");$pppage.find("#gsm").val("");$pppage.find("#email1").val("");
$pppage.find("#add").val("");$pppage.find("#mystate").val("");$pppage.find("#mylg").val("");

$cppage.find("#nam").val("");$cppage.find("#relation_pos").val("");$cppage.find("#gsm1").val("");
$cppage.find("#add1").val("");$cppage.find("#email11").val("");

$dppage.find("#dhouse_type").val("");$dppage.find("#dname").val("");$dppage.find("#desc").val("");$dppage.find("#localObj").val("");
$dppage.find("#addre").val("");$dppage.find("#mystate2").val("");$dppage.find("#mylg2").val("");
// display the success message and redirects the client to home pages
alert(data.msg+"\n Thus you will now be redirected to home page.");
$.mobile.changePage("#pagehome", "slide", false);
 //$.mobile.changePage("#pagehome");
  }
  // It failed
  else if(status===0)
        {
            alert("Sorry We don't know you,Are you sure you have registered for this service?");
        }
        else
            {
                alert(" We are sorry to inform you that there are <i>No Messages Found!</i> for you.<br>Thank you");
            }
},
error: function (xhr, ajaxOptions, thrownError) {
    $.mobile.loading('hide');
    
//then display the appropriate error message
var $page = $("#pageError .content");
// Build an error message
var strHtml = "<h3>Data Retrieval Failed</h3>";
strHtml += "<p>We were unable to get your information.The server may be down.<br> Also, please check your internet connection or/and try again later.<br>Thank you.</p>"
// Place the message in the error dialog
$page.html(strHtml);
// Show the dialog
$("#show-error-page").click();
}
});
 }
 else
 {

 }


 }
// These display the elements needed to view and animate control
//panel when the client has logged in and wants to view his
//her control panel at the right hand side of the sms page.
 $(document).ready(function()
{
$(".account").click(function()
{
var X=$(this).attr('id');

if(X==1)
{
$(".submenu").hide();
$(this).attr('id', '0');
}
else
{

$(".submenu").show();
$(this).attr('id', '1');
}

});

//Mouseup textarea false
$(".submenu").mouseup(function()
{
return false
});
$(".account").mouseup(function()
{
return false
});


//Textarea without editing.
$(document).mouseup(function()
{
$(".submenu").hide();
$(".account").attr('id', '');
});

});
/*
* These hide all the control panels div/elements created
* especially at the start up of the web application
*/
function Init(){
//hides local government divs/elements located at first and 2nd registraton form pages
$("#lgDiv").hide();
$("#lgDiv2").hide();
$("#mylg").hide();
//hides all the other control panels for each page(that's accessed when logged in)
$("#myCpanel").hide(); $("#myCpanel1").hide(); $("#myCpanel2").hide(); $("#myCpanel3").hide(); $("#myCpanel4").hide();
$("#myCpanel5").hide();
    
 
}
/*
 * Perform Login form validation
 */
function LoginFormValidation(){
 var Isvalid=false;
 var errmsg=document.getElementById("Logingmsg");
 var NameFormat= /^[a-z A-Z,.]{2,35}$/;
 var lga= /^[a-z A-Z0-9,-_.]{2,35}$/;
 var AddressFormat= /^[a-z A-Z0-9,;:-_.]{5,160}$/;
 var email_regex=/^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
 //var email_regex=regex=/@(\\w{2,}\\.(\\w{2,}\\.)?[a-zA-Z]{2,3})$/;
 var mail1=document.forms["LoginForm"]["userid"].value;
 var pass=document.forms["LoginForm"]["password"].value;

  if(mail1.trim(mail1)==""){
    window.alert("Email address is Empty");
    errmsg.innerHTML="Please enter a value in the Email address.".bold().fontsize("4").fontcolor("red");
    document.forms["LoginForm"]["userid"].focus();
        Isvalid= false;
}
else if(!mail1.trim(mail1).match(email_regex))
        {
            alert('Please enter a valid Email address like ####@#####.com');
            errmsg.innerHTML="Please enter a valid Email address like ####@#####.com".bold().fontsize("4").fontcolor("red");
            document.forms["LoginForm"]["userid"].focus();
           // checkAddress(mail1.trim(mail1));
           Isvalid= false;

        }
        /*
        else if(checkAddress(mail1.trim(mail1))==false){

        return false;

}
*/

else  if(pass.trim(pass)==""){
   alert("Password is Empty");
    errmsg.innerHTML="Please enter a value in the Password Field.".bold().fontsize("4").fontcolor("red");
    document.forms["LoginForm"]["password"].focus();
   Isvalid= false;

    }
     else  if(!pass.trim(pass).match(lga)){
   alert("Make sure there are no numbers or special\n characters(eg:1,;@,&,'',$) in the Password field.");
   errmsg.innerHTML="Make sure there are no numbers or special\n characters(eg:1,;@,&,'',$) in the Password field.".bold().fontsize("4").fontcolor("red");
   document.forms["LoginForm"]["password"].focus();
   Isvalid= false;
}


else
    {
        Isvalid= true;
    }
return Isvalid;
}
/*
 * Performs the validation of 1st stage registration form
 */
function FirstFormValidation(){
  var Isvalid=false;
 var errmsg=document.getElementById("errmsgss1");
 var gsm_lent=document.forms["form1"]["gsm"].value.toString().length;
 var gsm_regex=/^[2-4]{3}?[7-8]{1}[0-9]{9}/;
 var gsm_regex1=/^[08064500095]{11}/;
 var NameFormat= /^[a-z A-Z,.]{2,35}$/;
 var lga= /^[a-z A-Z0-9,-_.]{2,35}$/;
 var AddressFormat= /^[a-z A-Z0-9,;:-_.]{5,160}$/;
 var email_regex=/^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
 var sname=document.forms["form1"]["Sname"].value;
 var oname=document.forms["form1"]["Oname"].value;
 var gsm=document.forms["form1"]["gsm"].value;
 var mail1=document.forms["form1"]["email1"].value;
 var add=document.forms["form1"]["address"].value;
 var state=document.forms["form1"]["state"].value;
 //var lg=document.forms["form1"]["lga"].value;
 var pass=document.forms["form1"]["pass"].value;
 var vpass=document.forms["form1"]["vpass"].value;
  var lg=document.forms["form1"]["lga"].value;
 var lgaa=document.forms["form1"]["lgaa"].value;
 reset(errmsg);
 if(lg=="")
     {
         lgaa=document.forms["form1"]["lgaa"].value;
     }

//window.alert("My LG is:"+lg);
    if(sname==""){
        window.alert("Surname is Empty");
        errmsg.innerHTML="Please enter a value in the SurName Field.".bold().fontsize("3").fontcolor("red");
         DisplayError("Please enter a value in the SurName Field.","Invalid Input");
        document.forms["form1"]["Sname"].focus();
         Isvalid= false;
    }
    else if(!sname.trim(sname).match(NameFormat))
        {
            alert('SurName must have characters only and must be between 2 to 35');
             errmsg.innerHTML="SurName must have characters ONLY and must be between 2 to 35.".bold().fontsize("3").fontcolor("red");
             DisplayError('SurName must have characters only and must be between 2 to 35',"Invalid Input");
             document.forms["form1"]["Sname"].focus();
             Isvalid= false;
        }
   else if(oname==""){
       alert("Othername is Empty");
        errmsg.innerHTML="Please enter a value in the OtherName Field.".bold().fontsize("3").fontcolor("red");
         DisplayError('Please enter a value in the OtherName Field.',"Invalid Input");
        document.forms["form1"]["Oname"].focus();
        Isvalid= false;

    }
    else if(!oname.trim(oname).match(NameFormat))
        {
            alert('OtherName must have characters only and must be between 2 to 35');
            errmsg.innerHTML="OtherName must have characters ONLY and must be between 2 to 35.".bold().fontsize("3").fontcolor("red");
             DisplayError("OtherName must have characters ONLY and must be between 2 to 35.","Invalid Input");
            document.forms["form1"]["Oname"].focus();
              Isvalid= false;
        }
        else if(gsm=="")
        {
            alert('Mobile Number Field is Empty!');
            errmsg.innerHTML="Please enter a value in the Mobile Number Field.".bold().fontsize("3").fontcolor("red");
            DisplayError("Please enter a value in the Mobile Number Field.","Invalid Input");
            document.forms["form1"]["gsm"].focus();
             Isvalid= false;
        }
   else if(!gsm.trim(gsm).match(gsm_regex) ||gsm_lent!=13){
       alert("Mobile Number must be in this format \n234##########.\nCheck also that the length of the numbers is 13. ");
       DisplayError("Mobile Number must be in this format \n234##########.\nCheck also that the length of the numbers is 13.","Invalid Input");
       errmsg.innerHTML="Mobile Number must be in this format \n234##########.\nCheck also that the length of the numbers is 13.".bold().fontsize("3").fontcolor("red");
        document.forms["form1"]["gsm"].focus();
         Isvalid= false;
        //||(!document.forms["form1"]["gsm"].value.match(gsm_regex1))||document.forms["form1"]["gsm"].value.toString().length!=11
}


 else if(mail1==""){
    alert("Email address is Empty");
    errmsg.innerHTML="Please enter a value in the Email address Field.".bold().fontsize("3").fontcolor("red");
        document.forms["form1"]["email1"].focus();
         Isvalid= false;
}
else if(!mail1.trim(mail1).match(email_regex))
        {
            alert('Please enter a valid Email address like ####@#####.com');
            errmsg.innerHTML="Please enter a valid Email address like ####@#####.com".bold().fontsize("3").fontcolor("red");
            document.forms["form1"]["email1"].focus();
              Isvalid= false;
        }
 else if(add.trim(add)=="")
        {
            alert('Address Field is Empty!');
            errmsg.innerHTML="Please enter a value in the  Address Field.".bold().fontsize("3").fontcolor("red");
            document.forms["form1"]["address"].focus();
             Isvalid= false;
        }

        else if(!add.trim(add).match(AddressFormat))
        {
            alert("Make sure there are no numbers or special \ncharacters(eg:1,&,#,'',$) in the Address field.");
            errmsg.innerHTML="Make sure there are no numbers or special \ncharacters(eg:1,&,#,'',$) in the Address field.".bold().fontsize("3").fontcolor("red");
            document.forms["form1"]["address"].focus();
              Isvalid= false;
        }
else  if(state.trim(state)=="Select your State"){
   alert("State Not Selected");
   errmsg.innerHTML="State Must be Selected.".bold().fontsize("3").fontcolor("red");
   document.forms["form1"]["state"].focus();
     Isvalid= false;
}
else  if(!state.trim(state).match(NameFormat)){
   alert("Make sure there are no numbers or special \ncharacters(eg:1,'',$) in the state field.");
   errmsg.innerHTML="Make sure there are no numbers or special \ncharacters(eg:1,'',$) in the state field.".bold().fontsize("3").fontcolor("red");
   document.forms["form1"]["state"].focus();
    Isvalid= false;
}

   else if(lg.trim(lg)=="" && lgaa.trim(lgaa)==""){
  alert("LGA Must be entered");
   

     //lgfocus=document.forms["form1"]["lgaa"];
     errmsg.innerHTML="LGA Must be entered.".bold().fontsize("3").fontcolor("red");
     //errmsg.innerHTML="LGA  is null.".bold().fontsize("4").fontcolor("red");
     document.forms["form1"]["lgaa"].focus();
       Isvalid= false;
}

else if(lg.trim(lg)!="" && lg.trim(lg)=="Choose one"){
    alert("LGA Not Selected");
  
        errmsg.innerHTML="LGA Must be Selected.".bold().fontsize("3").fontcolor("red");
        document.forms["form1"]["lga"].focus();
         Isvalid= false;

}

else   if(pass.trim(pass)==""){
    alert("Password is Empty");
    errmsg.innerHTML="Please enter a value in the Password field.".bold().fontsize("3").fontcolor("red");
    document.forms["form1"]["pass"].focus();
       Isvalid= false;

    }
     else  if(!pass.trim(pass).match(lga)){
   alert("Make sure there are no numbers or special\n characters(eg:1,;@,&,'',$) in the Password field.");
   errmsg.innerHTML="Make sure there are no numbers or special\n characters(eg:1,;@,&,'',$) in the Password field.".bold().fontsize("3").fontcolor("red");
   document.forms["form1"]["pass"].focus();
    Isvalid= false;
}
else  if(vpass.trim(vpass)==""){

    alert("Verify Password is Empty");
    errmsg.innerHTML="Please enter a value in the Verify Password field.".bold().fontsize("3").fontcolor("red");
    document.forms["form1"]["vpass"].focus();
       Isvalid= false;
}
 else  if(!vpass.trim(vpass).match(lga)){
   alert("Make sure there are no numbers or special\n characters(eg:1,;@,&,'',$) in the Verify Password field.");
   errmsg.innerHTML="Make sure there are no numbers or special\n characters(eg:1,;@,&,'',$) in the Verify Password field.".bold().fontsize("3").fontcolor("red");
   document.forms["form1"]["vpass"].focus();
    Isvalid= false;
}
else if(pass.trim(pass)!=vpass.trim(vpass))
        {
         alert("Password and Verify Password Field must be the same");
          errmsg.innerHTML="<strong> Password and Verify Password Field must be the same</strong>".bold().fontcolor("red").fontsize("3");
          document.forms["form1"]["pass"].focus();
           Isvalid= false;
        }
else{
      Isvalid= true;
    

}
return Isvalid;
}
/*
 * Performs the validation of 2nd stage registration form
 */
function FormValidation2(){
 var gsm_regex=/^[2-4]{3}?[7-8]{1}[0-9]{9}/;
 var gsm_regex1=/^[08064500095]{11}/;
 var NameFormat= /^[a-z A-Z,.]{2,35}$/;
 var AddressFormat= /^[a-z A-Z0-9,;:-_.]{5,160}$/;
 var lga= /^[a-z A-Z0-9,-_.]{2,35}$/;
 var email_regex=/^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
 var errmsg=document.getElementById("errmsgss2");
 var name=document.forms["form2"]["nam"].value;
 var house_type=document.forms["form2"]["house_type"].value;
 var desc=document.forms["form2"]["description"].value;
 var loc=document.forms["form2"]["Location"].value;
 var add=document.forms["form2"]["address"].value;
 var state=document.forms["form2"]["state"].value;
 var lg=document.forms["form2"]["lga"].value;
 var lgaa=document.forms["form2"]["lgaa"].value;
 if(name.trim(name)==""){
       window.alert("Thank you1");
        alert("Device Name is Empty");
        errmsg.innerHTML="Please enter a value in the Name field.".bold().fontsize("3").fontcolor("red");
        document.forms["form2"]["nam"].focus();
       Isvalid= false;
    }
    else if(!name.trim(name).match(NameFormat))
        {
            alert('Name must have characters only and must be between 2 to 35');
             errmsg.innerHTML="Name must have characters only and must be between 2 to 35.".bold().fontsize("3").fontcolor("red");
            document.forms["form2"]["nam"].focus();
            Isvalid= false;
        }
   else if(house_type.trim(house_type)==""){
       alert("Housing Type is Empty");
       errmsg.innerHTML="Please enter a value in the Housing Type field.".bold().fontsize("3").fontcolor("red");
        document.forms["form2"]["house_type"].focus();
       Isvalid= false;

    }
    else if(!house_type.trim(house_type).match(NameFormat))
        {
            alert('Housing Type must have characters only and must be between 3 to 35');
            errmsg.innerHTML="Housing Type must have characters only and must be between 3 to 35.".bold().fontsize("3").fontcolor("red");
            document.forms["form2"]["house_type"].focus();
           Isvalid= false;
        }
        else if(desc.trim(desc)=="")
        {
            alert('Description Field is Empty!');
            errmsg.innerHTML="Description Field is Empty.".bold().fontsize("3").fontcolor("red");
            document.forms["form2"]["description"].focus();
           Isvalid= false;
        }

        else if(!desc.trim(desc).match(lga))
        {
            alert("Make sure there are no numbers or special \ncharacters(eg:1,'',$) in the Description field.");
            errmsg.innerHTML="Make sure there are no numbers or special \ncharacters(eg:1,'',$) in the Description field.".bold().fontsize("3").fontcolor("red");
            document.forms["form2"]["description"].focus();
          Isvalid= false;
        }
        else if(loc.trim(loc)=="")
        {
            alert('Location Field is Empty!');
            errmsg.innerHTML="Location Field is Empty.".bold().fontsize("3").fontcolor("red");
            document.forms["form2"]["Location"].focus();
           Isvalid= false;
        }
        else if(!loc.trim(loc).match(AddressFormat))
        {
            alert("Make sure there are no numbers or special \ncharacters(eg:1,'',$) in the Location field.");
            errmsg.innerHTML="Make sure there are no numbers or special \ncharacters(eg:1,'',$) in the Location field.".bold().fontsize("3").fontcolor("red");
            document.forms["form2"]["Location"].focus();
            Isvalid= false;
        }

        else if(add.trim(add)=="")
        {
            alert('Address Field is Empty!');
            errmsg.innerHTML="Address Field is Empty.".bold().fontsize("3").fontcolor("red");
            document.forms["form2"]["address"].focus();
           Isvalid= false;
        }

        else if(!add.trim(add).match(AddressFormat))
        {
            alert("Make sure there are no numbers or special \ncharacters(eg:1,&,#,'',$) in the Address field.");
            errmsg.innerHTML="Make sure there are no numbers or special \ncharacters(eg:1,'',$) in the Address field.".bold().fontsize("3").fontcolor("red");
            document.forms["form2"]["address"].focus();
            Isvalid= false;
        }


else  if(!state.trim(state).indexOf("Select",0)){
   alert("State Not Selected");
   errmsg.innerHTML="State Not Selected.".bold().fontsize("3").fontcolor("red");
   document.forms["form2"]["state"].focus();
  Isvalid= false;
}
else  if(!state.trim(state).match(AddressFormat)){
   alert("Make sure there are no numbers or special \ncharacters(eg:1,'',$) in the State field.");
   errmsg.innerHTML="Make sure there are no numbers or special \ncharacters(eg:1,'',$) in the State field.".bold().fontsize("3").fontcolor("red");
   document.forms["form2"]["state"].focus();
   Isvalid= false;
}

  else if(lg.trim(lg)=="" && lgaa.trim(lgaa)==""){
  alert("LGA Must be entered");


     //lgfocus=document.forms["form1"]["lgaa"];
     errmsg.innerHTML="LGA Must be entered.".bold().fontsize("3").fontcolor("red");
     //errmsg.innerHTML="LGA  is null.".bold().fontsize("4").fontcolor("red");
     document.forms["form2"]["lgaa"].focus();
       Isvalid= false;

       if(!lg.trim(lg).match(lga)){
   alert("Make sure there are no numbers or special\n characters(eg:1,;@,&,'',$) in the LGA field.");
   errmsg.innerHTML="Make sure there are no numbers or special\n characters(eg:1,;@,&,'',$) in the LGA field.".bold().fontsize("3").fontcolor("red");
   document.forms["form2"]["lgaa"].focus();
   Isvalid= false;
}
}

else if(lg.trim(lg)!="" && lg.trim(lg)=="Choose one"){
    alert("LGA Not Selected");

        errmsg.innerHTML="LGA Must be Selected.".bold().fontsize("3").fontcolor("red");
        document.forms["form2"]["lga"].focus();
         Isvalid= false;

}
     


else{
   Isvalid= true;

}
return Isvalid;
}
/*
 * Performs the validation of 3rd stage registration form
 */
function FormValidation3(){
 //window.alert("Thank you");
var gsm_lent=document.forms["form3"]["fone"].value.toString().length;
 var gsm_regex=/^[2-4]{3}?[7-8]{1}[0-9]{9}/;
 var gsm_regex1=/^[08064500095]{11}/;
 var NameFormat= /^[a-z A-Z,.]{2,45}$/;
 var AddressFormat= /^[a-z A-Z0-9,;:-_.]{5,160}$/;
 var lga= /^[a-z A-Z0-9,-_.]{2,35}$/;
 var email_regex=/^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
 var errmsg=document.getElementById("errmsgss3");
 var name=document.forms["form3"]["nam"].value;
 var relation_pos=document.forms["form3"]["relation_pos"].value;
 var fone=document.forms["form3"]["fone"].value;
 var mail1=document.forms["form3"]["email1"].value;
 var add=document.forms["form3"]["address"].value;

    if(name.trim(name)==""){
         //window.alert("Thank you1");
        alert("Name is Empty");
        errmsg.innerHTML="Name is Empty.".bold().fontsize("3").fontcolor("red");
        document.forms["form3"]["nam"].focus();
        Isvalid= false;
    }
    else if(!name.trim(name).match(NameFormat))
        {
            alert('Name must have characters only and must be between 2 to 45');
            errmsg.innerHTML="Name must have characters only and must be between 2 to 45.".bold().fontsize("3").fontcolor("red");
            document.forms["form3"]["nam"].focus();
             Isvalid= false;
        }
   else if(relation_pos.trim(relation_pos)==""){
       alert("Relationship/Position is Empty");
       errmsg.innerHTML="Relationship/Position is Empty.".bold().fontsize("3").fontcolor("red");
        document.forms["form3"]["relation_pos"].focus();
        Isvalid= false;

    }
    else if(!relation_pos.trim(relation_pos).match(NameFormat))
        {
            alert('Relationship/Position  must have characters only and must be between 2 to 20');
            errmsg.innerHTML="Relationship/Position must have characters only and must be between 2 to 20.".bold().fontsize("3").fontcolor("red");
            document.forms["form3"]["relation_pos"].focus();
            Isvalid= false;
        }
        else if(fone.trim(fone)=="")
        {
            alert('Mobile Number Field is Empty!');
            errmsg.innerHTML="Mobile Number Field is Empty.".bold().fontsize("3").fontcolor("red");
            document.forms["form3"]["fone"].focus();
            Isvalid= false;
        }
   else if(!fone.trim(fone).match(gsm_regex) ||gsm_lent!=13){
       alert("Mobile Number must be in this format \n234##########.\nCheck also that the correct length of the numbers is 13. ");
        errmsg.innerHTML="Mobile Number must be in this format \n234##########.\nCheck also that the correct length of the numbers is 13 .".bold().fontsize("3").fontcolor("red");
        document.forms["form3"]["fone"].focus();
        Isvalid= false;
        
}
 else if(add.trim(add)=="")
        {
            alert('Address Field is Empty!');
            errmsg.innerHTML="Address  Field is Empty.".bold().fontsize("3").fontcolor("red");
            document.forms["form3"]["address"].focus();
             Isvalid= false;
        }

        else if(!add.trim(add).match(AddressFormat))
        {
            alert("Make sure there are no numbers or special \ncharacters(eg:1,'',$) in the Address field.");
            errmsg.innerHTML="Make sure there are no numbers or special \ncharacters(eg:1,'',$) in the Address field.".bold().fontsize("3").fontcolor("red");
            document.forms["form3"]["address"].focus();
            Isvalid= false;
        }


 else if(mail1.trim(mail1)==""){
        alert("Email address is Empty");
        errmsg.innerHTML="Email address  Field is Empty.".bold().fontsize("3").fontcolor("red");
        document.forms["form3"]["email1"].focus();
         Isvalid= false;
}

else if(!mail1.trim(mail1).match(email_regex))
        {
            alert('Please enter a valid Email address like ####@#####.com');
            errmsg.innerHTML="Please enter a valid Email address like ####@#####.com".bold().fontsize("3").fontcolor("red");
            document.forms["form3"]["email1"].focus();
             Isvalid= false;
        }



else{
   Isvalid= true;

}
return Isvalid;

}
/*
 * Performs the validation of Password reset form
 */
function ChangePassWordFormValidation(){
 //window.alert("Thank you");
 var errmsg=document.getElementById("changePassmsg");
 var lga= /^[a-z A-Z0-9,-_.]{4,35}$/;
var Isvalid=false;
 var opass=document.forms["ChangePassForm"]["opass"].value;
 var npass=document.forms["ChangePassForm"]["npass"].value;
 var vnpass=document.forms["ChangePassForm"]["vnpass"].value;

   if(opass.trim(opass)==""){
    alert("Old Password is Empty");
    errmsg.innerHTML="Please enter a value in the Old Password field.".bold().fontsize("3").fontcolor("red");
    document.forms["ChangePassForm"]["opass"].focus();
      Isvalid= false;

    }
     else  if(!opass.trim(opass).match(lga)){
   alert("Make sure there are no numbers or special\n characters(eg:1,;@,&,'',$) in the Old Password field and that the number of characters is atleast 4.");
   errmsg.innerHTML="Make sure there are no numbers or special\n characters(eg:1,;@,&,'',$) in the Old Password field and that the number of characters is atleast 4.".bold().fontsize("3").fontcolor("red");
   document.forms["ChangePassForm"]["opass"].focus();
     Isvalid= false;
}
else  if(npass.trim(npass)==""){

    alert("New Password is Empty");
    errmsg.innerHTML="Please enter a value in the New Password field.".bold().fontsize("3").fontcolor("red");
    document.forms["ChangePassForm"]["npass"].focus();
      Isvalid= false;
}
 else  if(!npass.trim(npass).match(lga)){
   alert("Make sure there are no numbers or special\n characters(eg:1,;@,&,'',$) in the New Password field.");
   errmsg.innerHTML="Make sure there are no numbers or special\n characters(eg:1,;@,&,'',$) in the New Password field.".bold().fontsize("3").fontcolor("red");
   document.forms["ChangePassForm"]["npass"].focus();
     Isvalid= false;
}
else  if(vnpass.trim(vnpass)==""){

    alert("Verify New Password is Empty");
    errmsg.innerHTML="Please enter a value in the 'Verify New Password' field.".bold().fontsize("3").fontcolor("red");
    document.forms["ChangePassForm"]["vnpass"].focus();
       Isvalid= false;
}
 else  if(!vnpass.trim(vnpass).match(lga)){
   alert("Make sure there are no numbers or special\n characters(eg:1,;@,&,'',$) in the 'Verify New Password'.");
   errmsg.innerHTML="Make sure there are no numbers or special\n characters(eg:1,;@,&,'',$) in the 'Verify New Password'.".bold().fontsize("3").fontcolor("red");
   document.forms["ChangePassForm"]["vnpass"].focus();
     Isvalid= false;
}
else if(npass.trim(npass)!=vnpass.trim(vnpass))
        {
         alert("New  Password and Verify New Password Field must be the same");
          errmsg.innerHTML="<strong> 'New  Password' and 'Verify New Password' Field must be the same</strong>".bold().fontcolor("red").fontsize("3");
          document.forms["ChangePassForm"]["vnpass"].focus();
            Isvalid= false;
        }

else{
   Isvalid= true;

}
return Isvalid;

}

/*
 * Performs the validation of 1st stage registration data update form
 */
function FirstFormUpdateValidation(){
var Isvalid=false;

 var NameFormat= /^[a-z A-Z,.]{2,35}$/;
 var lga= /^[a-z A-Z0-9,-_.]{2,35}$/;
 var AddressFormat= /^[a-z A-Z0-9,;:-_.]{5,160}$/;
 var email_regex=/^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
 var name=document.forms["form11"]["name"].value;
 var oname=document.forms["form11"]["Oname"].value;
 var gsm=document.forms["form11"]["gsm"].value;
 var mail1=document.forms["form11"]["email1"].value;
 var add=document.forms["form11"]["address"].value;
 var state=document.forms["form11"]["state"].value;
 var lg=document.forms["form11"]["lga"].value;
 var pass=document.forms["form11"]["pass"].value;
 var vpass=document.forms["form11"]["vpass"].value;
    if(document.forms["form11"]["name"].value==""){
        window.alert("Name is Empty");
        document.forms["form11"]["name"].focus();
        Isvalid=false;
    }
    else if(!document.forms["form11"]["name"].value.match(NameFormat))
        {
            alert('Name must have characters only and must be between 3 to 35');
            document.forms["form11"]["name"].focus();
            Isvalid=false;
        }


 else if(document.forms["form11"]["email1"].value==""){
    window.alert("Email address is Empty");
        document.forms["form11"]["email1"].focus();
       Isvalid=false;
}
else if(!document.forms["form11"]["email1"].value.match(email_regex))
        {
            alert('Pls enter a valid Email address like ####@#####.com');
            document.forms["form11"]["email1"].focus();
            Isvalid=false;
        }
 else if(document.forms["form11"]["address"].value.trim(document.forms["form11"]["address"].value)=="")
        {
            alert('Address Field is Empty!');
            document.forms["form11"]["address"].focus();
            Isvalid=false;
        }

        else if(!document.forms["form11"]["address"].value.match(AddressFormat))
        {
            window.alert("Make sure there are no numbers or special \ncharacters(eg:1,&,#,'',$) in the Address field.");
            document.forms["form11"]["address"].focus();
            Isvalid=false;
        }
else  if(document.forms["form11"]["state"].value=="" ||!document.forms["form11"]["state"].value.indexOf("Select",0) ){
   window.alert("State Not Selected");
   document.forms["form11"]["state"].focus();
   Isvalid=false;
}
else  if(!document.forms["form11"]["state"].value.match(NameFormat)){
   window.alert("Make sure there are no numbers or special \ncharacters(eg:1,'',$) in the state field.");
   document.forms["form11"]["state"].focus();
   Isvalid=false;
}

 else if(document.forms["form11"]["lga"].value==""){
    window.alert("LGA is Empty");
  document.forms["form11"]["lga"].focus();
   Isvalid=false;
    }
    else  if(!document.forms["form11"]["lga"].value.match(lga)){
   window.alert("Make sure there are no numbers or special\n characters(eg:1,;@,&,'',$) in the LGA field.");
   document.forms["form11"]["lga"].focus();
  Isvalid=false;
}


else{
   Isvalid= true;

}
return Isvalid;


}

/*
 * Performs the validation of 2nd stage registration data update form
 */
function UpdateFormValidation2(){
 var NameFormat= /^[a-z A-Z,.]{2,35}$/;
 var AddressFormat= /^[a-z A-Z0-9,;:-_.]{5,160}$/;
 var lga= /^[a-z A-Z0-9,-_.]{2,35}$/;
var Isvalid=false;
    if(document.forms["form22"]["nam"].value==""){
        // window.alert("Thank you1");
        window.alert("Name is Empty");
        document.forms["form22"]["nam"].focus();
         Isvalid=false;
    }
    else if(!document.forms["form22"]["nam"].value.match(NameFormat))
        {
            alert('Name must have characters only and must be between 3 to 35');
            document.forms["form22"]["nam"].focus();
             Isvalid=false;
        }
   else if(document.forms["form22"]["house_type"].value==""){
       window.alert("Housing Type is Empty");
        document.forms["form22"]["house_type"].focus();
        Isvalid=false;

    }
    else if(!document.forms["form22"]["house_type"].value.match(NameFormat))
        {
            alert('Housing Type must have characters only and must be between 3 to 35');
            document.forms["form22"]["house_type"].focus();
            Isvalid=false;
        }
        else if(document.forms["form22"]["description"].value.trim(document.forms["form22"]["description"].value)=="")
        {
            alert('Description Field is Empty!');
            document.forms["form22"]["description"].focus();
             Isvalid=false;
        }
        else if(!document.forms["form22"]["description"].value.match(AddressFormat))
        {
            window.alert("Make sure there are no numbers or special \ncharacters(eg:1,'',$) in the Description field.");
            document.forms["form22"]["description"].focus();
             Isvalid=false;
        }
        else if(document.forms["form22"]["Location"].value.trim(document.forms["form22"]["Location"].value)=="")
        {
            alert('Location Field is Empty!');
            document.forms["form22"]["Location"].focus();
             Isvalid=false;
        }
        else if(!document.forms["form22"]["Location"].value.match(AddressFormat))
        {
            window.alert("Make sure there are no numbers or special \ncharacters(eg:1,'',$) in the Location field.");
            document.forms["form22"]["Location"].focus();
             Isvalid=false;
        }
        else if(document.forms["form22"]["address"].value.trim(document.forms["form22"]["address"].value)=="")
        {
            alert('Address Field is Empty!');
            document.forms["form22"]["address"].focus();
            Isvalid=false;
        }

        else if(!document.forms["form22"]["address"].value.match(AddressFormat))
        {
            window.alert("Make sure there are no numbers or special \ncharacters(eg:1,&,#,'',$) in the Address field.");
            document.forms["form22"]["address"].focus();
            Isvalid=false;
        }

else  if(document.forms["form22"]["state"].value==""){
   window.alert("State Not Typed!");
   document.forms["form22"]["state"].focus();
   Isvalid=false;
}
else  if(!document.forms["form22"]["state"].value.match(AddressFormat)){
   window.alert("Make sure there are no numbers or special \ncharacters(eg:1,'',$) in the State field.");
   document.forms["form22"]["state"].focus();
    Isvalid=false;
}

 else if(document.forms["form22"]["lga"].value==""){
    window.alert("LGA Not Typed!");
  document.forms["form22"]["lga"].focus();
    Isvalid=false;
    }
    else  if(!document.forms["form22"]["lga"].value.match(lga)){
   window.alert("Make sure there are no numbers or special\n characters(eg:1,;@,&,'',$) in the LGA field.");
   document.forms["form22"]["lga"].focus();
     Isvalid=false;
}

else{
       Isvalid=true;
  }
 return Isvalid;

}
/*
 * Performs the validation of 3rd stage registration data update form
 */
function UpdateFormValidation3(){
var gsm_lent=document.forms["form33"]["fone"].value.toString().length;
 var gsm_regex=/^ [2-4]{3}?[7-8]{1}[0-9]{9}/;
 var Isvalid=false;
 var NameFormat= /^[a-z A-Z,.]{2,35}$/;
 var AddressFormat= /^[a-z A-Z0-9,;:-_.]{5,160}$/;
 var lga= /^[a-z A-Z0-9,-_.]{2,35}$/;
 var email_regex=/^([\w-\. ]+@([\w-]+\.)+[\w-]{2,4})?$/;
 //var mail1=trim()
 var fone=document.forms["form33"]["fone"].value.replace("/^\s+|\s+$/g",'');
    if(document.forms["form33"]["nam"].value==""){
         //window.alert("Thank you1");
        window.alert("Name is Empty");
        document.forms["form33"]["nam"].focus();
        Isvalid= false;
    }
    else if(!document.forms["form33"]["nam"].value.match(NameFormat))
        {
            alert('Name must have characters only and must be between 3 to 35');
            document.forms["form33"]["nam"].focus();
             Isvalid= false;
        }
   else if(document.forms["form33"]["relation_pos"].value==""){
       window.alert("Relationship/Position is Empty");
        document.forms["form33"]["relation_pos"].focus();
         Isvalid= false;

    }
    else if(!document.forms["form33"]["relation_pos"].value.match(NameFormat))
        {
            alert('Relationship/Position  must have characters only and must be between 3 to 35');
            document.forms["form33"]["relation_pos"].focus();
            Isvalid= false;
        }
        
 else if(document.forms["form33"]["address"].value.trim(document.forms["form33"]["address"].value)=="")
        {
            alert('Address Field is Empty!');
            document.forms["form33"]["address"].focus();
             Isvalid= false;
        }

        else if(!document.forms["form33"]["address"].value.match(AddressFormat))
        {
            window.alert("Make sure there are no numbers or special \ncharacters(eg:1,'',$) in the Address field.");
            document.forms["form33"]["address"].focus();
             Isvalid= false;
        }
else{
   Isvalid= true;

}
return Isvalid;

}
/*
 * This method automatically retrieves List of local governments based on a state
 * to be displayed to the html select element
 */
function getLGALists(val,divObj){
var strUrl = window.location.origin+"/SnoSSoftwareTest3/servlets/RequestProcessing?";
//tells the client that you want to perform lists of local governments retrieval
strUrl += "source=mobile&action=ajax_list&state="+val;

 $.ajax({
url: strUrl,
type: "GET",
//data: formData,
cache: false,
beforeSend: function() {
                        // This callback function will trigger before data is sent
                        //alert("Pageloading sPinner will start nOw");
$.mobile.loading('show'); // This will show ajax spinner
    },
complete: function(data) {


},
success: function(data) {

//alert("Getting Ready to Update SNOS-Success");
 $.mobile.loading('hide');

//alert("The data is:\n"+JSON.stringify(data));

var status=data.status;

if(status==1)
    {
var lga=data.lga;

 lga = eval(lga);
 //format the outputs in  select option format (eg. <select option='Isolo lg'>Isolo</select>)
createOptions(divObj,lga);
//display this either in 1st or 2nd stage of registration local government form element
if(divObj=="mylgaaa")
    {
         $("#lgDiv2").show();//show the select list-first choice
          $("#mylgaaa").hide(); //hide the alternate local government text field-optional incase server could not return the local government lists
    }
    else
        {
              $("#lgDiv").show();//show the select list
              $("#mylgaa").hide();//hide the alternate local government text field-optional incase server could not return the local government lists
        }
    }
    //failed to pull the local government list from the server.
    else if(status==0)
    {
        alert("Sorry We don't know you,Are you sure you have registered for this service?");
        $("#mylgaa").show(); //show the text field option since the select option failed. Then type in the local government manually
        $("#lgDiv").hide();  //hide the select option since server failed to return the list
    }
        else
            {
                alert("Ooops a problem occured please try again later.<br>Thank you");
                 $("#mylgaa").show();
                 $("#lgDiv").hide();
            }
},
error: function (xhr, ajaxOptions, thrownError) {
     $.mobile.loading('hide');
     $("#mylgaa").show();
    
// Get the error page 
var $page = $("#pageError .content");
// Build an error message
var strHtml = "<h3>Data Retrieval Failed</h3>";
strHtml += "<p>We were unable to get your information.The server may be down.<br> Also, please check your internet connection or/and try again later.<br>Thank you.</p>"
// Place the message in the error dialog
$page.html(strHtml);
// Show the dialog
//$("#show-error-page").click();
}
});
 

 }
/*
* Check the client email before registration by sending
* a servlet GET request to the server.
 */
function checkEmailBeforeRegistrationAddress(val){
//get the error message to be used to display any error
var $smsDiv = document.getElementById("errmsgss1");
//resets it incase it contained previous error messages
reset($smsDiv);
//tells the servlet that you just want to check wether this particular email address exists
//at a table called clients_temp_tab, where 'user' contains the client email in question
var strUrl = window.location.origin+"/SnoSSoftwareTest3/servlets/RequestProcessing?";
strUrl += "source=mobile&action=ajax&table=clients_temp_tab&user="+val;

 $.ajax({
url: strUrl,
type: "GET",
//data: formData,
cache: false,
beforeSend: function() {
                        // This callback function will trigger before data is sent
                        //alert("Pageloading sPinner will start nOw");
                      $.mobile.loading('show'); // This will show ajax spinner
    },
complete: function(data) {


},
success: function(data) {

//alert("Getting Ready to Update SNOS-Success");
 $.mobile.loading('hide');

//alert("The data is:\n"+JSON.stringify(data));

var status=data.status;

if(status==1)
    {
var name=data.name;

if(name==1){
  // Tells the client that this particular email address already exists in the server
    $("#errmsgss1").html("OOps!!! something went wrong<br>This email address:"+val+" has been registered,Enter another one.<br>Thank you.").css("size", "3").css("color", "red");
    $("#email0").val(" ");
}
else
    {
        //Welcome him/her since the email address is new to the server database.
            $("#errmsgss1").html("Welcome Dearest New Client.").css("size", "3").css("color", "blue");
    }

    }
    else if(status==0)
        {
            alert("Sorry We don't know you,Are you sure you have registered for this service?");
        }
        else
            {
                alert(" We are sorry to inform you that there are <i>No Messages Found!</i> for you.<br>Thank you");
            }

},
error: function (xhr, ajaxOptions, thrownError) {
     $.mobile.loading('hide');
   

// Get the customized error page
var $page = $("#pageError .content");
// Build an error message
var strHtml = "<h3>Data Retrieval Failed</h3>";
strHtml += "<p>We were unable to get your information.The server may be down.<br> Also, please check your internet connection or/and try again later.<br>Thank you.</p>"
// Place the message in the error dialog
$page.html(strHtml);
// Show the dialog
//$("#show-error-page").click();
}
});
 
}

/*
* This simply formats the retrived Lists of local government 
* in an html select option format.
 */
function createOptions(id,_options) {
    if(_options == null || _options.length==0) { return;}
    var opt = null;
    var msg="";
    for(var i = 0; i < _options.length; i++) {
       msg+="<option value='"+_options[i]+"'>"+_options[i]+"</option>";
}
   $("#"+id).html(msg);

}
var lgvalue;
/*
 * Simply authenticates and returns a particular local government value
 */
function getMyLGValue(obj){
   lgvalue=obj.value;
    if(obj=="" ||obj.trim(obj)=="Choose one"){
   alert("LGA Not Selected");
   errmsg.innerHTML="LGA Must be Selected.".bold().fontsize("4").fontcolor("red");
  // document.forms["form1"]["state"].focus();
   return false;
}
    var errmsg = document.getElementById("lgamsg");
        if (obj.value=="Choose one"){
    alert("LGA must be selected selected");
    errmsg.innerHTML="LGA must be selected.".bold().fontsize("4").fontcolor("red");

   return false;
    }
    return lgvalue;

}


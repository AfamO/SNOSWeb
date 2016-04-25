/*
 * This starts, introduces and creates jquery function and constructor
 * @returns {undefined}
 */
$(function(){
   // timedRefresh(3000)
   // myContentHolder.setAttribute("enabled", "true");
   //$("#mylg").attr("disabled", "true");
    /*

*/

$("#mylgaa").hide();
$("#mylg").hide();


//$.browser.device = (/android|webos|iphone|ipad|ipod|blackberry|iemobile|opera mini/i.test(navigator.userAgent.toLowerCase()));
//alert("browser type is:"+$.browser.device);
/*
alert("My hostname is:"+window.location.hostname );
alert("My host is:"+window.location.host );
alert("My Protocol is:"+window.location.protocol );
alert("My host Port is:"+window.location.port );
alert("My Origin is:"+window.location.origin);
   */
});
	// Checking for CSS 3D transformation support
	$.support.css3d = supportsCSS3D();

	var formContainer = $('#formContainer');

	// Listening for clicks on the ribbon links
	$('.flipLink').click(function(e){

		// Flipping the forms
		formContainer.toggleClass('flipped');

		// If there is no CSS3 3D support, simply
		// hide the login form (exposing the recover one)
		if(!$.support.css3d){
			$('#login').toggle();
		}
		e.preventDefault();
	});


	function supportsCSS3D() {
		var props = [
			'perspectiveProperty', 'WebkitPerspective', 'MozPerspective'
		], testDom = document.createElement('a');

		for(var i=0; i<props.length; i++){
			if(props[i] in testDom.style){
				return true;
			}
		}

		return false;
	}
  
 /*
  * This is mearn't to refresh the sms alerts in box periodically to poll latest alerts-
  * though a better way is currently being worked at.
  * @param {type} timeoutPeriod
  * @returns {undefined}
  */
    function Refresh(timeoutPeriod) {
	//setTimeout("location.reload(true);",timeoutPeriod);

    setInterval(function(){
        //alert("Hello!");
    }, timeoutPeriod);
   // reSetSMSDiv();
    //alert("Who are you?");
}

/*
 * This takes care of asynchronous server side event(SSE) to return latest alerts
 * after the user has logged in.
 * @returns {Boolean}
 */
function setupEventSource() {
        var LastTime="${errmsg}";
        var sid="${sid}";
        console.log("sid="+sid+" last_retrieved_time="+LastTime);
        alert("sid="+sid+" last_retrieved_time="+LastTime);
        var output = document.getElementById("output");
        if (typeof(EventSource) !== "undefined") {
          var msg = document.getElementById("textID").value;
          var source = new EventSource("mysse?msg=" + msg);
          
          source.onmessage = function(event) {
             
            output.innerHTML += event.data + "<br>";
          };
          source.addEventListener('close', function(event) {
            console.log(event.data);
            $alert= event.data + "<hr/>";
            source.close();
            }, false);
            source.onerror=function(error)
            {
                //alert("The error message="+JSON.stringify(xhr));
                console.log('Failed to Start EventSource: ', error);
                
            };
        } else {
          output.innerHTML = "Sorry, Server-Sent Events are not supported in your browser";
        }
        return false;
      }


/*
 * This dispalys a user's name-along with comfirm dialog box- whenever he/she wants to sign out
 * @param {type} name
 * @returns {Boolean}
 */
function display(name)
{
var ans1=confirm("Dear "+name+",\nAre you sure that you want to logout?");
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
 * This takes care of asynchronous server side event(SSE) to return latest alerts
 * after the user has logged in.
 * @returns {Boolean}
 */

/*
 * perform the normal trim function.
 * @returns {Boolean}
 */
function trimMe(str)
{
    var ans;
    ans=str.replace("/^\s+|\s+$/g",'');
    return ans

}var lgvalue;
/*
 * Populate the local government select field-for 1st registration form- from database using ajax.
 * @returns {Boolean}
 */
function generateList(){
    var val=document.forms["form1"]["state"].value;
    if (val == null ) { return; }
   
        url = "http://localhost:8084/SnoSSoftwareTest3/servlets/RequestProcessing?action=ajax_list&source=desktop&state="+val;
        httpRequest_list("GET",url,true,handleResponse1);
    
}
/*
 * Populate the local government select field-for 2nd registration form- from database using ajax.
 * @returns {Boolean}
 */
function generateList2(){
    var val=document.forms["form2"]["state"].value;
    if (val == null ) { return; }
   
        url = "http://localhost:8084/SnoSSoftwareTest3/servlets/RequestProcessing?action=ajax_list&state="+val;
        httpRequest_list("GET",url,true,handleResponse1);

}
/*
 * Validate the local government field
 * @returns {Boolean}
 */
function getMyLGValue(obj){
   lgvalue=obj.value;
  // alert("my value is:"+obj);
    if(obj==="" ||obj.trim(obj)==="Choose one"){
   alert("LGA Not Selected");
   errmsg.innerHTML="LGA Must be Selected.".bold().fontsize("4").fontcolor("red");
  // document.forms["form1"]["state"].focus();
   return false;
}
    var errmsg = document.getElementById("lgamsg");
        if (obj.value==="Choose one"){
    alert("LGA must be selected selected");
    errmsg.innerHTML="LGA must be selected.".bold().fontsize("4").fontcolor("red");
 
   return false;
    }
    return lgvalue;

}
/*
 * Validate the local government field
 * @returns {Boolean}
 */
function CheckMyLGValue(){
  
   var errmsg = document.getElementById("lgamsg");
    if(lgavalue===null || lgavalue==="" || lgavalue.trim(lgavalue)==="Choose one"){
   alert("LGA Not Selected");
   errmsg.innerHTML="LGA Must be Selected.".bold().fontsize("4").fontcolor("red");
  // document.forms["form1"]["state"].focus();
   return false;
}
    
       
    return lgvalue;

}


function progress(percent, $element) {
	var progressBarWidth = percent * $element.width() / 100;
	$element.find('div').animate({ width: progressBarWidth }, 500).html(percent + "%&nbsp;");
}
/*
 * This is ajax handle response for generlateList and generateList2 event methods.
 * @returns {undefined}
 */
function handleResponse1(){
    var usedTag,xmlReturnVal;
   var newsel = document.getElementById("errmsg1");
   var lgamsg = document.getElementById("lgalist");
                     reset(newsel);
                     reset(lgamsg);
    try{
        if(request.readyState == 4){
           // alert("My First response is:");
            if(request.status == 200){
               // if(formObjTyp.length > 0 && formObjTyp == "input") {
                    //if (resp != null){
                        //return value is an array
                        xmlReturnVal=request.responseXML;
                        usedTag=xmlReturnVal.getElementsByTagName("response")[0];
           //alert("How do you do?the server response is "+usedTag);
                        resp=usedTag.childNodes[0].data;
                         //var newsel = document.getElementById("lgalist");
                        if(resp=="error" || resp==null)
                            {
                                stylizeDiv("We are sorry,The Server is not available to retrieve the LGs of your state.\nPlease do try again later.\nThank you", newsel);
                            }
                            else
                                {
                        //alert("My response is:"+resp);
                        var objt = eval(resp);
                        //create a new select element
                        var sel = document.createElement("select");
                        sel.setAttribute("name","lga");
                         sel.setAttribute("size","1");
                         sel.setAttribute("Onchange", "getMyLGValue(this);");
                         //sel.setAttribute("Onblur", "getMyLGValue(this);");
                         //alert("value:"+sel);
                        //give the select element some options based
                        //on a list of countries from the server
                        createOptions(sel,objt);
                        //the div element within which the select appears
                        $("#mylg").show();
                        $("#mylgaa").hide();
                        reset(newsel);
                       // newsel.appendChild(sel);
                        }
                    //}
               // }
            } else {
                //request.status is 503 if the application isn't available;
                //500 if the application has a bug
                alert("A problem occurred with communicating between "+
                      "the XMLHttpRequest object and the server program.\\nError: "+err.message);
            }
        }//end outer if
    } catch (err) {
        alert("It does not appear that the server is available"+
              " for this application. Please"+
              " try again very soon. \\nError: "+err.message);
          
          stylizeDiv("We are sorry,Server is not available to retrieve the LGs of your state.\nPlease do Kindly type it in the alternate form provided to you.\nThank you", newsel);
          var opt
           //opt=document.createElement("input");
          // opt.setAttribute("name","lga");
           //opt.setAttribute("Placeholder","Pleas type your LGA here");
           //opt.setAttribute("size","30");
           //lgamsg.appendChild(opt);
         //  document.write("<input type='text' size = '20' placeholder='Enter you LGA here'   name = 'lga'  maxlength='50' style='margin-right:30px;' >");
         $("#mylg").hide();
         $("#mylgaa").show();
         document.forms["form1"]["lgaa"].focus();



    }
}
/*
 * Validates form for search field
 * @returns {Boolean}
 */

function SearchFormValidation(){
 //window.alert("Thank you");
 var errmsg=document.getElementById("errmsg");
 var NameFormat= /^[a-z A-Z0-9=,.]{2,35}$/;
 var searchtf=document.forms["f3"]["searchtf"].value;

  if(searchtf.trim(searchtf)==""){
    alert("Search Field is Empty");
    errmsg.innerHTML="Please enter an item to search for in the Search Field.".bold().fontsize("4").fontcolor("red");
    document.forms["f3"]["searchtf"].focus();
        return false;
}
else if(!searchtf.trim(searchtf).match(NameFormat))
        {
            alert('Please enter a valid search item like door,open,URGENT,URGENT ASSISTANCE,etc');
            errmsg.innerHTML="Please enter a valid search item like door,open,URGENT,URGENT ASSISTANCE,etc".bold().fontsize("4").fontcolor("red");
            document.forms["f3"]["searchtf"].focus();
            return false;
        }
            else
                {
                    return true;
                }
        }
        /*
         * These variables are declared to be global variables used 
         * in other methods within this javascript file
         * resp: for ajax response object
         * check: a normal boolean that checks if an action has happened.
         * value: a  normal value holding a particular value assigned to it.
         * @type Boolean|Boolean|Boolean|Boolean|Boolean|Boolean|Boolean
         */

var check=false;var resp;var value;
/*
 * This was initially written to aynchronously validate a user email address
 * immediately the user enters it to the text field to login.
 * @param {type} val
 * @returns {Boolean}
 */
    function checkAddress(val){

    var url;
    
        url = "http://localhost:8084/SnoSSoftwareTest3/servlets/RequestProcessing?action=ajax&source=desktop&user="+val;
        //url="<%'http://'"+"+request.getHeader('host')"+"+request.getContextPath()"+"'/servlets/RequestProcessing?action=ajax&user='"+val+" %>";
        //url="<%request.getHeader('host')%>";

     // alert("the URL IS"+host);
       //var  url="http://localhost:8084/BiddingApp/EmailChecker?email="+encodeURIComponent(val);
        httpRequest("GET",url,true,handleResponse);
        if(check===false)
            {
                return false;
            }
            else
                {
                    return true;
                }
   // }
}
/*
 * 
 * @param {type} val
 * @param {type} type
 * @returns {Boolean}
 * Validate the gsm and email address with ajax-asyuchronously.
 */
function checkEmail_GsmBeforeRegistrationAddress(val,type){

    var url;
    var gsm_lent=document.forms["form1"]["gsm"].value.toString().length;
    var gsm_regex=/^[0]{1}?[7-9]{1}[0-9]{9}/;
    var email_regex=/^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
    var  btn= document.getElementById("reg0");
     //alert("Are you sure your E-mail is? "+val);
    var ajax_load = "<img src='load.gif' title='loading... pls wait!' width='30px' height='20px' />";
 value=val;
 var requestType=""; //Is it email or gsm
 if(type==="myemail")
     {
         requestType="user"; 
         check=true;//for email
         if(val==="")
         {
             alert("Email address is Empty");
             errmsg.innerHTML="Please enter a value in the Email address Field.".bold().fontsize("4").fontcolor("red");
             document.forms["form1"]["email1"].focus();
             return false;
         }
         else if(!val.trim(val).match(email_regex))
         {
             alert('Please enter a valid Email address like ####@#####.com');
             errmsg.innerHTML="Please enter a valid Email address like ####@#####.com".bold().fontsize("4").fontcolor("red");
             document.forms["form1"]["email1"].focus();
             return false;
         }
     }
     else
      {
            requestType="gsm";
            check=false; //for gsm
            if(val==="")
        {
            alert('Mobile Number Field is Empty!');
            errmsg.innerHTML="Please enter a value in the Mobile Number Field.".bold().fontsize("4").fontcolor("red");
            document.forms["form1"]["gsm"].focus();
            return false;
        }
        else if(!val.trim(val).match(gsm_regex) ||gsm_lent!=11){
       alert("Mobile Number must be in this format \n080########.\nCheck also that the length of the numbers is 11. ");
       errmsg.innerHTML="Mobile Number must be in this format \n080########.\nCheck also that the length of the numbers is 11.".bold().fontsize("4").fontcolor("red");
       document.forms["form1"]["gsm"].focus();
       return false;
      }
      //append Nigeria code since that is how it is stored in the database and used by SNOC
      val=val.substring(1,11);
      val="234"+val;
  }
        url = "http://localhost:8084/SnoSSoftwareTest3/servlets/RequestProcessing?action=ajax&table=clients_temp_tab&source=desktop&"+requestType+"="+val;
        //url="<%'http://'"+"+request.getHeader('host')"+"+request.getContextPath()"+"'/servlets/RequestProcessing?action=ajax&user='"+val+" %>";
        //url="<%request.getHeader('host')%>";
        httpRequest("GET",url,true,handleResponse);
        if(check==false)
            {
                
                return false;
            }
            else
                {
                     
                    return true;
                }
  
}
/*
 * 
 * @param {type} val
 * @param {type} type
 * @returns {Boolean}
 * Do the above similiarly for form3 -3rd stage of registration
 */
function checkEmail_GsmBeforeRegistrationAddress_Form3(val,type){

    var url;
    var gsm_lent=document.forms["form3"]["fone"].value.toString().length;
    var gsm_regex=/^[0]{1}?[7-9]{1}[0-9]{9}/;
    var email_regex=/^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
    var  btn= document.getElementById("reg0");
     //alert("Are you sure your E-mail is? "+val);
    var ajax_load = "<img src='load.gif' title='loading... pls wait!' width='30px' height='20px' />";
    value=val;
    var requestType=""; //Is it email or gsm
    if(type==="myemail")
     {
         requestType="user"; 
         check=true;//for email
         if(val==="")
         {
             alert("Email address is Empty");
             errmsg.innerHTML="Please enter a value in the Email address Field.".bold().fontsize("4").fontcolor("red");
             document.forms["form3"]["email1"].focus();
             return false;
         }
         else if(!val.trim(val).match(email_regex))
         {
             alert('Please enter a valid Email address like ####@#####.com');
             errmsg.innerHTML="Please enter a valid Email address like ####@#####.com".bold().fontsize("4").fontcolor("red");
             document.forms["form3"]["email1"].focus();
             return false;
         }
     }
     else
      {
            requestType="gsm";
            check=false; //for gsm
            if(val==="")
        {
            alert('Mobile Number Field is Empty!');
            errmsg.innerHTML="Please enter a value in the Mobile Number Field.".bold().fontsize("4").fontcolor("red");
            document.forms["form3"]["fone"].focus();
            return false;
        }
        else if(!val.trim(val).match(gsm_regex) ||gsm_lent!=11){
       alert("Mobile Number must be in this format \n080########.\nCheck also that the length of the numbers is 11. ");
       errmsg.innerHTML="Mobile Number must be in this format \n080########.\nCheck also that the length of the numbers is 11.".bold().fontsize("4").fontcolor("red");
       document.forms["form3"]["fone"].focus();
       return false;
      }
      //append Nigeria code since that is how it is stored in the database and used by SNOC
      val=val.substring(1,11);
      val="234"+val;
  }
        url = "http://localhost:8084/SnoSSoftwareTest3/servlets/RequestProcessing?action=ajax&table=clients_temp_tab&source=desktop&"+requestType+"="+val;
        //url="<%'http://'"+"+request.getHeader('host')"+"+request.getContextPath()"+"'/servlets/RequestProcessing?action=ajax&user='"+val+" %>";
        //url="<%request.getHeader('host')%>";

   
        httpRequest("GET",url,true,handleResponse);
        if(check==false)
            {
                
                return false;
            }
            else
                {
                     
                    return true;
                }
  
}
/*
 * Event handler for XMLHttpRequest for the immediates two above functions
 * @returns {unresolved}
 */
function handleResponse(){
    var NameFormat= /^[a-z A-Z,.]{2,35}$/;
    var usedTag, answer,xmlReturnVal;
    var div1 = document.getElementById("errmsg1");
    var  errmsg= document.getElementById("errmsg");
     var  btn= document.getElementById("reg0");
    reset(errmsg);
    try{
    if(request.readyState === 4){
        if(request.status === 200){
            //implement Document object in DOM
            xmlReturnVal = request.responseXML;
            //$("#errmsg1").load("http://localhost:8084/SnoSSoftwareTest/view_sms.jsp",function(responseText,statusText){
            //alert("Response ISSS:\n" + responseText+" and statusText is "+statusText);
       // });


           usedTag=xmlReturnVal.getElementsByTagName("response")[0];
          // alert("How do you do?the server response is "+usedTag);
           resp=usedTag.childNodes[0].data;
            //alert("How do you do?the server response is "+resp);

            if(resp==1)
                {
                    //stylizeDiv("Hi "+resp+" we know you and you are welcomed<br>", div1);
                    if(check==true)
                        {
                            errmsg.innerHTML="Hi, you have already registerd in this site with this email address ".bold().fontsize("4").fontcolor("red")+value.bold().fontsize("4").fontcolor("red")+".\nYou are not permitted to register again.\nThank you ".bold().fontsize("4").fontcolor("red");
                            document.forms["form1"]["email1"].focus();
                        }
                        else
                            {
                                errmsg.innerHTML="Hi, you have already registerd in this site with this gsm number ".bold().fontsize("4").fontcolor("red")+value.bold().fontsize("4").fontcolor("red")+".\nYou are not permitted to register again.\nThank you ".bold().fontsize("4").fontcolor("red");
                                document.forms["form1"]["gsm"].focus();
                            }
                                        //btn.setAttribute("disabled", "true");
                      
                     
                    // $("#reg1").attr("disabled","true");
                      //reset(errmsg)
                    
                }
                else if(resp==0)
                 {
                     //stylizeDiv("Hi Stranger we don't know you.", div1);
                     check=false;
                     if(value!="undefined" && btn==null)
                         {
                              eMsg("Hi Stranger we don't know you","red");

                         }
                         else
                             {
                                 eMsg("Hi new client, you are welcomed","blue");
                             }

                }
                 
                else if(resp!=0 && resp!=1)
                    {
                         check=true;
                    eMsg("Hi "+resp+" you are welcomed.","blue");

                    }
                return;
        }
        else {
            alert("A problem occurred with communicating between the "+
                  "XMLHttpRequest object and the Server program..\\nError: "+err.message);
            stylizeDiv("Could not access the Server.Make sure your browser is up to date", div1);
        }
    }//end outer if
    }
    catch (err)   {
        alert("It does not appear that the server is "+
              "available for this application. Please"+
              " try again very soon. ");
           stylizeDiv("Server is not available to process your request", div1);
           

    }
}
/*
 * This creates and formats-to select list- local government options lists retrieved from the database.
 * @param {type} sel
 * @param {type} _options
 * @returns {unresolved}
 */

function createOptions(sel,_options) {
    //_options is an array of strings that represent the values of
    //a select list, as in each option of the list.
    //sel is the select object
    if(_options == null || _options.length==0) { return;}
    var opt = null;
    var msg="";
    for(var i = 0; i < _options.length; i++) {
        //opt = document.createElement("option");
        //opt.appendChild(document.createTextNode(_options[i]));
      
        //sel.appendChild(opt);
   

msg+="<option value='"+_options[i]+"'>"+_options[i]+"</option>";

      
    }
    var myContentHolder = document.getElementById("mylg");
   
$("#mylg").html(msg);

}



function reSetSMSDiv(){


//$("#TableSMSContainer").html(msg);
// $("#TableSMSContainer").load("http://localhost:8084/SnoSSoftwareTest/content.html #divContent",function(responseText,statusText){
      //      alert("Response ISSS:\n" + responseText+" and statusText is "+statusText);
     //   });
     /*
        var txt = "afamsimon@gmail.com";
    alert("Are you sure your E-mail is? "+txt);
    var ajax_load = "<img src='load.gif' title='loading... pls wait!' />";

//  load() functions
    var loadUrl = "http://localhost:8084/SnoSSoftwareTest/servlets/RequestProcessing";

        $("#TableSMSContainer").html(ajax_load)
            load(loadUrl, {"action":"ajax","user":txt}, function(responseText,statusText,xhr){
            if(statusText=="success")

                    alert("Successfully Loaded Content");
                alert("The response Text is:\n "+responseText);

             if(statusText=="error")

                    alert("An  "+xhr.status+" error occured with message: "+xhr.statusText);
                //if(statusText == "error")
                       // alert("An error occurred: " + xhr.status + " - " + xhr.statusText);

        });
    */
   var txt = document.forms["f3"]["searchtf"].value;
   var url=" http://localhost:8084/SnoSSoftwareTest3/servlets/RequestProcessing";
   //alert("Are you sure your E-mail is? "+txt);
     //$(sender).val("Loading - please wait...");

   // $("#imgProgress").show();

            $.get(url,{action:"ajax1",searchtf: txt},function(data,Textstatus){
                 //$(sender).val("Perform Calculation");

               // $("#imgProgress").hide();
               //alert("Response  for Ajax1 is:");
              // resp=data.getElementsByTagName("response")[0];
          // resp=resp.childNodes[0].data;
                //alert("Retrieved info is:"+resp);
                $("#ViewsmsTableContainer").load("http://localhost:8084/SnoSSoftwareTest3/view_sms.jsp #ViewsmsTableContainer",function(responseText,statusText){
         // alert("Response ISSS:\n" + responseText+" and statusText is "+statusText);
      });
        }).error(function()
        {
                //$("#imgProgress").hide();
               // $(sender).val("Try again");
                alert("An error occurred")
               // alert("The request obj is:"+req)

        });

}
function getNewSMSDiv(userid,pass){

//$("#TableSMSContainer").html(msg);
// $("#TableSMSContainer").load("http://localhost:8084/SnoSSoftwareTest/content.html #divContent",function(responseText,statusText){
      //      alert("Response ISSS:\n" + responseText+" and statusText is "+statusText);
     //   });
     /*
        var txt = "afamsimon@gmail.com";
    alert("Are you sure your E-mail is? "+txt);
    var ajax_load = "<img src='load.gif' title='loading... pls wait!' />";

//  load() functions
    var loadUrl = "http://localhost:8084/SnoSSoftwareTest/servlets/RequestProcessing";

        $("#TableSMSContainer").html(ajax_load)
            load(loadUrl, {"action":"ajax","user":txt}, function(responseText,statusText,xhr){
            if(statusText=="success")

                    alert("Successfully Loaded Content");
                alert("The response Text is:\n "+responseText);

             if(statusText=="error")

                    alert("An  "+xhr.status+" error occured with message: "+xhr.statusText);
                //if(statusText == "error")
                       // alert("An error occurred: " + xhr.status + " - " + xhr.statusText);

        });
    */
   var url="http://localhost:8084/SnoSSoftwareTest/servlets3/RequestProcessing";
   //alert("Are you sure your E-mail is? "+txt);
     //$(sender).val("Loading - please wait...");

   // $("#imgProgress").show();

            $.get(url,{action:"ajax2",userid: userid,password:pass},function(data,Textstatus){
                 //$(sender).val("Perform Calculation");

               // $("#imgProgress").hide();
               //alert("Response  for Ajax1 is:");
              // resp=data.getElementsByTagName("response")[0];
          // resp=resp.childNodes[0].data;
                //alert("Retrieved info is:"+resp);
                $("#TableSMSContainer").load("http://localhost:8084/SnoSSoftwareTest3/view_sms.jsp #TableSMSContainer",function(responseText,statusText){
         // alert("Response ISSS:\n" + responseText+" and statusText is "+statusText);
      });
        }).error(function()
        {
                //$("#imgProgress").hide();
               // $(sender).val("Try again");
                alert("An error occurred")
               // alert("The request obj is:"+req)

        });

}
/*
 * This opens and initiates the ajax http request
 * @param {type} reqType
 * @param {type} url
 * @param {type} bool
 * @returns {undefined}
 */
function initReq(reqType,url,bool){
    try{
        /* Specify the function that will handle the HTTP response */
        request.onreadystatechange=handleResponse;
        request.open(reqType,url,bool);
        request.send(null);
    } catch (errv) {
        alert(
                "The application cannot contact the server at the moment. "+
                "Please try again in a few seconds." );
    }
}
/*
 * This does similiar function as above
 * @param {type} reqType
 * @param {type} url
 * @param {type} bool
 * @returns {undefined}
 */
function initReq_list(reqType,url,bool){
    try{
        /* Specify the function that will handle the HTTP response */
        request.onreadystatechange=handleResponse1;
        request.open(reqType,url,bool);
        request.send(null);
    } catch (errv) {
        alert(
                "The application cannot contact the server at the moment. "+
                "Please try again in a few seconds." );
    }
}
/* Wrapper function for constructing a request object.
 Parameters:
  reqType: The HTTP request type, such as GET or POST.
  url: The URL of the server program.
  asynch: Whether to send the request asynchronously or not. */
function httpRequest(reqType,url,asynch){
    //Mozilla-based browsers
    if(window.XMLHttpRequest){
        request = new XMLHttpRequest();
    } else if (window.ActiveXObject){
        request=new ActiveXObject("Msxml2.XMLHTTP");
        if (! request){
            request=new ActiveXObject("Microsoft.XMLHTTP");
        }
    }
    //the request could still be null if neither ActiveXObject
    //initialization succeeded
    if(request){
        initReq(reqType,url,asynch);
    } else {
        alert("Your browser does not permit the use of all "+
              "of this application's features!");
    }
}
function httpRequest_list(reqType,url,asynch){
    //Mozilla-based browsers
    if(window.XMLHttpRequest){
        request = new XMLHttpRequest();
    } else if (window.ActiveXObject){
        request=new ActiveXObject("Msxml2.XMLHTTP");
        if (! request){
            request=new ActiveXObject("Microsoft.XMLHTTP");
        }
    }
    //the request could still be null if neither ActiveXObject
    //initialization succeeded
    if(request){
        initReq_list(reqType,url,asynch);
    } else {
        alert("Your browser does not permit the use of all "+
              "of this application's features!");
    }
}
/*
 * This simply displays messages to div object
 * @param {type} msg
 * @param {type} sColor
 * @returns {undefined}
 */
function eMsg(msg,sColor){
    var div = document.getElementById("errmsg1");
    div.style.color=sColor;
    div.style.fontSize="0.9em";
    //remove old messages
    if(div.hasChildNodes(  )){
        div.removeChild(div.firstChild);
    }
    div.appendChild(document.createTextNode(msg));

}
/*
 * This styles divs
 * @param {type} bdyTxt
 * @param {type} div
 * @returns {undefined}
 */
function stylizeDiv(bdyTxt,div){
   reset(div);
    div.innerHTML=" ";
    div.style.fontSize="1.2em";
    div.style.backgroundColor="yellow";
   // div.appendChild(document.createTextNode(bdyTxt));
    div.innerHTML=bdyTxt;

}
/*
 * This clears and resets div object
 * @param {type} elObject-the div object to reset
 * @returns {undefined}
 */
function reset(elObject){
    if(elObject != null && elObject.hasChildNodes(  )){
        for(var i = 0; i < elObject.childNodes.length; i++){
            elObject.removeChild(elObject.firstChild);
        }
    }
}

/* This validates the user login form at the home page
 * 
 * @returns {Boolean}
 */
function LoginFormValidation(){
 //window.alert("Thank you");
 var errmsg=document.getElementById("errmsg");
 var NameFormat= /^[a-z A-Z,.]{2,35}$/;
 var lga= /^[a-z A-Z0-9,-_.]{2,35}$/;
 var AddressFormat= /^[a-z A-Z0-9,;:-_.]{5,160}$/;
 var email_regex=/^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
 //var email_regex=regex=/@(\\w{2,}\\.(\\w{2,}\\.)?[a-zA-Z]{2,3})$/;
 var mail1=document.forms["fo"]["userid"].value;
 var pass=document.forms["fo"]["password"].value;

  if(mail1.trim(mail1)==""){
    //window.alert("Email address is Empty");
    errmsg.innerHTML="Please enter a value in the Email address.".bold().fontsize("4").fontcolor("red");
    document.forms["fo"]["userid"].focus();
        return false;
}
else if(!mail1.trim(mail1).match(email_regex))
        {
           // alert('Please enter a valid Email address like ####@#####.com');
            errmsg.innerHTML="Please enter a valid Email address like ####@#####.com".bold().fontsize("4").fontcolor("red");
            document.forms["fo"]["userid"].focus();
           // checkAddress(mail1.trim(mail1));
            return false;

        }
        /*
        else if(checkAddress(mail1.trim(mail1))==false){

        return false;

}
*/

else  if(pass.trim(pass)==""){
   // window.alert("Password is Empty");
    errmsg.innerHTML="Please enter a value in the Password Field.".bold().fontsize("4").fontcolor("red");
    document.forms["fo"]["password"].focus();
     return false;

    }
     else  if(!pass.trim(pass).match(lga)){
   //window.alert("Make sure there are no numbers or special\n characters(eg:1,;@,&,'',$) in the Password field.");
   errmsg.innerHTML="Make sure there are no numbers or special\n characters(eg:1,;@,&,'',$) in the Password field.".bold().fontsize("4").fontcolor("red");
   document.forms["fo"]["password"].focus();
   return false;
}


else
    {
        return true;
    }

}
/*
 * This validates the first form-in the 1st step of client registration
 * @returns {Boolean}
 */
function FirstFormValidation(){
 //window.alert("Thank you");
 var errmsg=document.getElementById("errmsg");
 var gsm_lent=document.forms["form1"]["gsm"].value.toString().length;
 //var gsm_regex=/^[2-4]{3}?[7-8]{1}[0-9]{9}/;
 var gsm_regex=/^[0]{1}?[7-9]{1}[0-9]{9}/;
 var NameFormat= /^[a-z A-Z,.]{2,35}$/;
 var lga= /^[a-z A-Z0-9,-_.]{2,35}$/;
 var AddressFormat= /^[a-z A-Z0-9,;:-_.]{5,160}$/;
 var email_regex=/^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
 var sname=document.forms["form1"]["Sname"].value;
 var oname=document.forms["form1"]["Oname"].value;
 var gsm=document.forms["form1"]["gsm"].value;
 var mail1=document.forms["form1"]["email1"].value;
 var add=document.forms["form1"]["address"].value;
 var lg=document.forms["form1"]["lga"].value;
 var state=document.forms["form1"]["state"].value;
 //var lg=document.forms["form1"]["lga"].value;
 var pass=document.forms["form1"]["pass"].value;
 var vpass=document.forms["form1"]["vpass"].value;
 var lgaa=document.forms["form1"]["lgaa"].value;
 reset(errmsg);
 if(lg=="")
     {
         lgaa=document.forms["form1"]["lgaa"].value;
     }
    if(sname==""){
        window.alert("Surname is Empty");
        errmsg.innerHTML="Please enter a value in the SurName Field.".bold().fontsize("4").fontcolor("red");
        document.forms["form1"]["Sname"].focus();
        return false;
    }
    else if(!sname.trim(sname).match(NameFormat))
        {
            alert('SurName must have characters only and must be between 2 to 35');
             errmsg.innerHTML="SurName must have characters ONLY and must be between 2 to 35.".bold().fontsize("4").fontcolor("red");
            document.forms["form1"]["Sname"].focus();
            return false;
        }
   else if(oname==""){
       alert("Othername is Empty");
        errmsg.innerHTML="Please enter a value in the OtherName Field.".bold().fontsize("4").fontcolor("red");
        document.forms["form1"]["Oname"].focus();
        return false;

    }
    else if(!oname.trim(oname).match(NameFormat))
        {
            alert('OtherName must have characters only and must be between 2 to 35');
            errmsg.innerHTML="OtherName must have characters ONLY and must be between 2 to 35.".bold().fontsize("4").fontcolor("red");
            document.forms["form1"]["Oname"].focus();
            return false;
        }
        else if(gsm=="")
        {
            alert('Mobile Number Field is Empty!');
            errmsg.innerHTML="Please enter a value in the Mobile Number Field.".bold().fontsize("4").fontcolor("red");
            document.forms["form1"]["gsm"].focus();
            return false;
        }
   else if(!gsm.trim(gsm).match(gsm_regex) ||gsm_lent!=11){
       alert("Mobile Number must be in this format \n080########.\nCheck also that the length of the numbers is 13. ");
       errmsg.innerHTML="Mobile Number must be in this format \n080########.\nCheck also that the length of the numbers is 13.".bold().fontsize("4").fontcolor("red");
        document.forms["form1"]["gsm"].focus();
        return false;
        //||(!document.forms["form1"]["gsm"].value.match(gsm_regex1))||document.forms["form1"]["gsm"].value.toString().length!=11
}
else if(mail1==""){
    alert("Email address is Empty");
    errmsg.innerHTML="Please enter a value in the Email address Field.".bold().fontsize("4").fontcolor("red");
        document.forms["form1"]["email1"].focus();
        return false;
}
else if(!mail1.trim(mail1).match(email_regex))
        {
            alert('Please enter a valid Email address like ####@#####.com');
            errmsg.innerHTML="Please enter a valid Email address like ####@#####.com".bold().fontsize("4").fontcolor("red");
            document.forms["form1"]["email1"].focus();
            return false;
        }
 else if(add.trim(add)=="")
        {
            alert('Address Field is Empty!');
            errmsg.innerHTML="Please enter a value in the  Address Field.".bold().fontsize("4").fontcolor("red");
            document.forms["form1"]["address"].focus();
            return false;
        }

        else if(!add.trim(add).match(AddressFormat))
        {
            alert("Make sure there are no numbers or special \ncharacters(eg:1,&,#,'',$) in the Address field.");
            errmsg.innerHTML="Make sure there are no numbers or special \ncharacters(eg:1,&,#,'',$) in the Address field.".bold().fontsize("4").fontcolor("red");
            document.forms["form1"]["address"].focus();
            return false;
        }
else  if(state.trim(state)=="Select your State"){
   alert("State Not Selected");
   errmsg.innerHTML="State Must be Selected.".bold().fontsize("4").fontcolor("red");
   document.forms["form1"]["state"].focus();
   return false;
}
else  if(!state.trim(state).match(NameFormat)){
   alert("Make sure there are no numbers or special \ncharacters(eg:1,'',$) in the state field.");
   errmsg.innerHTML="Make sure there are no numbers or special \ncharacters(eg:1,'',$) in the state field.".bold().fontsize("4").fontcolor("red");
   document.forms["form1"]["state"].focus();
   return false;
}

   else if(lg.trim(lg)=="" && lgaa.trim(lgaa)==""){
  window.alert("LGA Must be entered");
   //errmsg.innerHTML="LGA Must be Selected.".bold().fontsize("4").fontcolor("red");
   // errmsg.innerHTML="LGA  Selected is:".bold().fontsize("4").fontcolor("red")+lg;
  // document.forms["form1"]["state"].focus();
  //if(lgaa=="" && lg.trim(lg)==""){
     // alert("LGa is null");
     
     //lgfocus=document.forms["form1"]["lgaa"];
     errmsg.innerHTML="LGA Must be entered.".bold().fontsize("4").fontcolor("red");
     //errmsg.innerHTML="LGA  is null.".bold().fontsize("4").fontcolor("red");
     document.forms["form1"]["lgaa"].focus();
      return false;
 
}

else if(lg.trim(lg)!="" && lg.trim(lg)=="Choose one"){
alert("LGA Not Selected");
   //errmsg.innerHTML="LGA Must be Selected.".bold().fontsize("4").fontcolor("red");
   // errmsg.innerHTML="LGA  Selected is:".bold().fontsize("4").fontcolor("red")+lg;
  // document.forms["form1"]["state"].focus();
  
  
         //errmsg.innerHTML="LGAA is null.".bold().fontsize("4").fontcolor("red");
        //  lgfocus=document.forms["form1"]["lgaa"];
        errmsg.innerHTML="LGA Must be Selected.".bold().fontsize("4").fontcolor("red");
        document.forms["form1"]["lga"].focus();
         return false;
     }

else   if(pass.trim(pass)==""){
    alert("Password is Empty");
    errmsg.innerHTML="Please enter a value in the Password field.".bold().fontsize("4").fontcolor("red");
    document.forms["form1"]["pass"].focus();
     return false;

    }
     else  if(!pass.trim(pass).match(lga)){
   alert("Make sure there are no numbers or special\n characters(eg:1,;@,&,'',$) in the Password field.");
   errmsg.innerHTML="Make sure there are no numbers or special\n characters(eg:1,;@,&,'',$) in the Password field.".bold().fontsize("4").fontcolor("red");
   document.forms["form1"]["pass"].focus();
   return false;
}
else  if(vpass.trim(vpass)==""){

    alert("Verify Password is Empty");
    errmsg.innerHTML="Please enter a value in the Verify Password field.".bold().fontsize("4").fontcolor("red");
    document.forms["form1"]["vpass"].focus();
     return false;
}
 else  if(!vpass.trim(vpass).match(lga)){
   alert("Make sure there are no numbers or special\n characters(eg:1,;@,&,'',$) in the Verify Password field.");
   errmsg.innerHTML="Make sure there are no numbers or special\n characters(eg:1,;@,&,'',$) in the Verify Password field.".bold().fontsize("4").fontcolor("red");
   document.forms["form1"]["vpass"].focus();
   return false;
}
else if(pass.trim(pass)!=vpass.trim(vpass))
        {
         alert("Password and Verify Password Field must be the same");
          errmsg.innerHTML="<strong> Password and Verify Password Field must be the same</strong>".bold().fontcolor("red").fontsize("4");
          document.forms["form1"]["pass"].focus();
          return false;
        }
else{
    //append Nigeria code  to gsm since that is how it is stored in the database and used by SNOC
      gsm=gsm.substring(1,11);
      gsm="234"+gsm;
    return true;

}

}
/*
 * This validates the first update form-in the client profile page
 * @returns {Boolean}
 */
function UpdateFormValidation(){

 //var gsm_lent=document.forms["form1"]["gsm"].value.toString().length;
 var gsm_regex=/^[2-4]{3}?[7-8]{1}[0-9]{9}/;
 var gsm_regex1=/^[08064500095]{11}/;
 var NameFormat= /^[a-z A-Z,.]{2,35}$/;
 var lga= /^[a-z A-Z0-9,-_.]{2,35}$/;
 var AddressFormat= /^[a-z A-Z0-9,;:-_.]{5,160}$/;
 var email_regex=/^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
  var name=document.forms["form1"]["name"].value;
 var oname=document.forms["form1"]["Oname"].value;
 var gsm=document.forms["form1"]["gsm"].value;
 var mail1=document.forms["form1"]["email1"].value;
 var add=document.forms["form1"]["address"].value;
 var state=document.forms["form1"]["state"].value;
 var lg=document.forms["form1"]["lga"].value;
 var pass=document.forms["form1"]["pass"].value;
 var vpass=document.forms["form1"]["vpass"].value;
    if(document.forms["form1"]["name"].value==""){
        window.alert("Name is Empty");
        document.forms["form1"]["name"].focus();
        return false;
    }
    else if(!document.forms["form1"]["name"].value.match(NameFormat))
        {
            alert('Name must have characters only and must be between 3 to 35');
            document.forms["form1"]["name"].focus();
            return false;
        }


 else if(document.forms["form1"]["email1"].value==""){
    window.alert("Email address is Empty");
        document.forms["form1"]["email1"].focus();
        return false;
}
else if(!document.forms["form1"]["email1"].value.match(email_regex))
        {
            alert('Pls enter a valid Email address like ####@#####.com');
            document.forms["form1"]["email1"].focus();
            return false;
        }
 else if(document.forms["form1"]["address"].value.trim(document.forms["form1"]["address"].value)=="")
        {
            alert('Address Field is Empty!');
            document.forms["form1"]["address"].focus();
            return false;
        }

        else if(!document.forms["form1"]["address"].value.match(AddressFormat))
        {
            window.alert("Make sure there are no numbers or special \ncharacters(eg:1,&,#,'',$) in the Address field.");
            document.forms["form1"]["address"].focus();
            return false;
        }
else  if(document.forms["form1"]["state"].value=="" ||!document.forms["form1"]["state"].value.indexOf("Select",0) ){
   window.alert("State Not Selected");
   document.forms["form1"]["state"].focus();
   return false;
}
else  if(!document.forms["form1"]["state"].value.match(NameFormat)){
   window.alert("Make sure there are no numbers or special \ncharacters(eg:1,'',$) in the state field.");
   document.forms["form1"]["state"].focus();
   return false;
}

 else if(document.forms["form1"]["lga"].value==""){
    window.alert("LGA is Empty");
  document.forms["form1"]["lga"].focus();
   return false;
    }
    else  if(!document.forms["form1"]["lga"].value.match(lga)){
   window.alert("Make sure there are no numbers or special\n characters(eg:1,;@,&,'',$) in the LGA field.");
   document.forms["form1"]["lga"].focus();
   return false;
}


else{
    window.alert("Congratulations!!!\nThank you.");
    return true;

}

}
/*
 * This validates the second form-in the 2nd step of client registration
 * @returns {Boolean}
 */
function FormValidation2(){
 var gsm_regex=/^[2-4]{3}?[7-8]{1}[0-9]{9}/;
 var gsm_regex1=/^[08064500095]{11}/;
 var NameFormat= /^[a-z A-Z,.]{2,35}$/;
 var AddressFormat= /^[a-z A-Z0-9,;:-_.]{5,160}$/;
 var lga= /^[a-z A-Z0-9,-_.]{2,35}$/;
 var email_regex=/^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
 var errmsg=document.getElementById("errmsg");
 var name=document.forms["form2"]["nam"].value;
 var house_type=document.forms["form2"]["house_type"].value;
 var desc=document.forms["form2"]["description"].value;
 var loc=document.forms["form2"]["Location"].value;
 var add=document.forms["form2"]["address"].value;
 var state=document.forms["form2"]["state"].value;
 var lg=document.forms["form2"]["lga"].value;
 var extra_sensor=document.forms["form2"]["extra_sensor"].value;
 var con;

    if(name.trim(name)==""){
        // window.alert("Thank you1");
        alert("Name is Empty");
        errmsg.innerHTML="Please enter a value in the Name field.".bold().fontsize("4").fontcolor("red");
        document.forms["form2"]["nam"].focus();
        return false;
    }
    else if(!name.trim(name).match(NameFormat))
        {
            alert('Name must have characters only and must be between 2 to 35');
             errmsg.innerHTML="Name must have characters only and must be between 2 to 35.".bold().fontsize("4").fontcolor("red");
            document.forms["form2"]["nam"].focus();
            return false;
        }
   else if(house_type.trim(house_type)==""){
       alert("Housing Type is Empty");
       errmsg.innerHTML="Please enter a value in the Housing Type field.".bold().fontsize("4").fontcolor("red");
        document.forms["form2"]["house_type"].focus();
        return false;

    }
    else if(!house_type.trim(house_type).match(NameFormat))
        {
            alert('Housing Type must have characters only and must be between 3 to 35');
            errmsg.innerHTML="Housing Type must have characters only and must be between 3 to 35.".bold().fontsize("4").fontcolor("red");
            document.forms["form2"]["house_type"].focus();
            return false;
        }
        else if(desc.trim(desc)=="")
        {
            alert('Description Field is Empty!');
            errmsg.innerHTML="Description Field is Empty.".bold().fontsize("4").fontcolor("red");
            document.forms["form2"]["description"].focus();
            return false;
        }

        else if(!desc.trim(desc).match(lga))
        {
            alert("Make sure there are no numbers or special \ncharacters(eg:1,'',$) in the Description field.");
            errmsg.innerHTML="Make sure there are no numbers or special \ncharacters(eg:1,'',$) in the Description field.".bold().fontsize("4").fontcolor("red");
            document.forms["form2"]["description"].focus();
            return false;
        }
        else if(loc.trim(loc)=="")
        {
            alert('Location Field is Empty!');
            errmsg.innerHTML="Location Field is Empty.".bold().fontsize("4").fontcolor("red");
            document.forms["form2"]["Location"].focus();
            return false;
        }
        else if(!loc.trim(loc).match(AddressFormat))
        {
            alert("Make sure there are no numbers or special \ncharacters(eg:1,'',$) in the Location field.");
            errmsg.innerHTML="Make sure there are no numbers or special \ncharacters(eg:1,'',$) in the Location field.".bold().fontsize("4").fontcolor("red");
            document.forms["form2"]["Location"].focus();
            return false;
        }

        else if(add.trim(add)=="")
        {
            alert('Address Field is Empty!');
            errmsg.innerHTML="Address Field is Empty.".bold().fontsize("4").fontcolor("red");
            document.forms["form2"]["address"].focus();
            return false;
        }

        else if(!add.trim(add).match(AddressFormat))
        {
            alert("Make sure there are no numbers or special \ncharacters(eg:1,&,#,'',$) in the Address field.");
            errmsg.innerHTML="Make sure there are no numbers or special \ncharacters(eg:1,'',$) in the Address field.".bold().fontsize("4").fontcolor("red");
            document.forms["form2"]["address"].focus();
            return false;
        }


else  if(!state.trim(state).indexOf("Select",0)){
   alert("State Not Selected");
   errmsg.innerHTML="State Not Selected.".bold().fontsize("4").fontcolor("red");
   document.forms["form2"]["state"].focus();
   return false;
}
else  if(!state.trim(state).match(AddressFormat)){
   alert("Make sure there are no numbers or special \ncharacters(eg:1,'',$) in the State field.");
   errmsg.innerHTML="Make sure there are no numbers or special \ncharacters(eg:1,'',$) in the State field.".bold().fontsize("4").fontcolor("red");
   document.forms["form2"]["state"].focus();
   return false;
}

 else if(lg.trim(lg)=="Choose one"){
    alert("LGA Not Selected!");
    errmsg.innerHTML="LGA Not Selected.".bold().fontsize("4").fontcolor("red");
  document.forms["form2"]["lga"].focus();
   return false;
    }
    else  if(!lg.trim(lg).match(lga)){
   alert("Make sure there are no numbers or special\n characters(eg:1,;@,&,'',$) in the LGA field.");
   errmsg.innerHTML="Make sure there are no numbers or special\n characters(eg:1,;@,&,'',$) in the LGA field.".bold().fontsize("4").fontcolor("red");
   document.forms["form2"]["lga"].focus();
   return false;
}
/*
 else  if(extra_sensor.trim(extra_sensor)=="no"){
con =confirm("Are you sure that you don't want to register extra device/belonging or sensor?")

   if(con==false)
    {
        
         alert("Check then the 'Yes' radio button.");
   errmsg.innerHTML="Check then the 'Yes' radio button.".bold().fontsize("4").fontcolor("red");
        document.forms["form2"]["extra_sensor"].focus();
        return false;
    }
    else
        {
            return true;
        }

   
  // return false;
}
else  if(extra_sensor.trim(extra_sensor)=="yes"){
   con=confirm("Are you sure that you want to register extra device/belonging or sensor?")

   if(con==false)
    {
         return true;

    }
    else
        {
             
         alert("Check then the 'No' radio button.");
   errmsg.innerHTML="Check then the 'No' radio button.".bold().fontsize("4").fontcolor("red");
        document.forms["form2"]["extra_sensor"].focus();
        return false;
        }


  // return false;
}
*/



else{
    return true;

}

}
/*
 * This validates the update of the client object(device) data form. I mean it vlaidates the data entered at 
 * the above immediate form when changes are made and want to be updated.
 * @returns {Boolean}
 */
function UpdateFormValidation2(){

 var gsm_regex=/^[2-4]{3}?[7-8]{1}[0-9]{9}/;
 var gsm_regex1=/^[08064500095]{11}/;
 var NameFormat= /^[a-z A-Z,.]{2,35}$/;
 var AddressFormat= /^[a-z A-Z0-9,;:-_.]{5,160}$/;
 var lga= /^[a-z A-Z0-9,-_.]{2,35}$/;
 var email_regex=/^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
    if(document.forms["form2"]["nam"].value==""){
        // window.alert("Thank you1");
        window.alert("Name is Empty");
        document.forms["form2"]["nam"].focus();
        return false;
    }
    else if(!document.forms["form2"]["nam"].value.match(NameFormat))
        {
            alert('Name must have characters only and must be between 3 to 35');
            document.forms["form2"]["nam"].focus();
            return false;
        }
   else if(document.forms["form2"]["house_type"].value==""){
       window.alert("Housing Type is Empty");
        document.forms["form2"]["house_type"].focus();
        return false;

    }
    else if(!document.forms["form2"]["house_type"].value.match(NameFormat))
        {
            alert('Housing Type must have characters only and must be between 3 to 35');
            document.forms["form2"]["house_type"].focus();
            return false;
        }
        else if(document.forms["form2"]["description"].value.trim(document.forms["form2"]["description"].value)=="")
        {
            alert('Description Field is Empty!');
            document.forms["form2"]["description"].focus();
            return false;
        }
        else if(!document.forms["form2"]["description"].value.match(AddressFormat))
        {
            window.alert("Make sure there are no numbers or special \ncharacters(eg:1,'',$) in the Description field.");
            document.forms["form2"]["description"].focus();
            return false;
        }
        else if(document.forms["form2"]["Location"].value.trim(document.forms["form2"]["Location"].value)=="")
        {
            alert('Location Field is Empty!');
            document.forms["form2"]["Location"].focus();
            return false;
        }
        else if(!document.forms["form2"]["Location"].value.match(AddressFormat))
        {
            window.alert("Make sure there are no numbers or special \ncharacters(eg:1,'',$) in the Location field.");
            document.forms["form2"]["Location"].focus();
            return false;
        }
        else if(document.forms["form2"]["address"].value.trim(document.forms["form2"]["address"].value)=="")
        {
            alert('Address Field is Empty!');
            document.forms["form2"]["address"].focus();
            return false;
        }

        else if(!document.forms["form2"]["address"].value.match(AddressFormat))
        {
            window.alert("Make sure there are no numbers or special \ncharacters(eg:1,&,#,'',$) in the Address field.");
            document.forms["form2"]["address"].focus();
            return false;
        }

else  if(!document.forms["form2"]["state"].value.indexOf("Select",0)){
   window.alert("State Not Selected");
   document.forms["form2"]["state"].focus();
   return false;
}
else  if(!document.forms["form2"]["state"].value.match(AddressFormat)){
   window.alert("Make sure there are no numbers or special \ncharacters(eg:1,'',$) in the State field.");
   document.forms["form2"]["state"].focus();
   return false;
}

 else if(!document.forms["form2"]["lga"].value.indexOf("Select",0)){
    window.alert("LGA Not Selected!");
  document.forms["form2"]["lga"].focus();
   return false;
    }
    else  if(!document.forms["form2"]["lga"].value.match(lga)){
   window.alert("Make sure there are no numbers or special\n characters(eg:1,;@,&,'',$) in the LGA field.");
   document.forms["form2"]["lga"].focus();
   return false;
}


else{
     alert("Congratulations!!!\nThank you.");
    return true;

}

}
/*
 * This validates the third form-in the 3rd step of client registration
 * @returns {Boolean}
 */
function FormValidation3(){
var gsm_lent=document.forms["form3"]["fone"].value.toString().length;
 var gsm_regex=/^[2-4]{3}?[7-8]{1}[0-9]{9}/;
 var gsm_regex1=/^[08064500095]{11}/;
 var NameFormat= /^[a-z A-Z,.]{2,45}$/;
 var AddressFormat= /^[a-z A-Z0-9,;:-_.]{5,160}$/;
 var lga= /^[a-z A-Z0-9,-_.]{2,35}$/;
 var email_regex=/^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
 var errmsg=document.getElementById("errmsg");
 var name=document.forms["form3"]["nam"].value;
 var relation_pos=document.forms["form3"]["relation_pos"].value;
 var fone=document.forms["form3"]["fone"].value;
 var mail1=document.forms["form3"]["email1"].value;
 var add=document.forms["form3"]["address"].value;

    if(name.trim(name)==""){
         //window.alert("Thank you1");
        alert("Name is Empty");
        errmsg.innerHTML="Name is Empty.".bold().fontsize("4").fontcolor("red");
        document.forms["form3"]["nam"].focus();
        return false;
    }
    else if(!name.trim(name).match(NameFormat))
        {
            alert('Name must have characters only and must be between 2 to 45');
            errmsg.innerHTML="Name must have characters only and must be between 2 to 45.".bold().fontsize("4").fontcolor("red");
            document.forms["form3"]["nam"].focus();
            return false;
        }
   else if(relation_pos.trim(relation_pos)==""){
       alert("Relationship/Position is Empty");
       errmsg.innerHTML="Relationship/Position is Empty.".bold().fontsize("4").fontcolor("red");
        document.forms["form3"]["relation_pos"].focus();
        return false;

    }
    else if(!relation_pos.trim(relation_pos).match(NameFormat))
        {
            alert('Relationship/Position  must have characters only and must be between 2 to 20');
            errmsg.innerHTML="Relationship/Position must have characters only and must be between 2 to 20.".bold().fontsize("4").fontcolor("red");
            document.forms["form3"]["relation_pos"].focus();
            return false;
        }
        else if(fone.trim(fone)=="")
        {
            alert('Mobile Number Field is Empty!');
            errmsg.innerHTML="Mobile Number Field is Empty.".bold().fontsize("4").fontcolor("red");
            document.forms["form3"]["fone"].focus();
            return false;
        }
   else if(!fone.trim(fone).match(gsm_regex) ||gsm_lent!=13){
       alert("Mobile Number must be in this format \n234##########.\nCheck also that the correct length of the numbers is 13. ");
        errmsg.innerHTML="Mobile Number must be in this format \n234##########.\nCheck also that the correct length of the numbers is 13 .".bold().fontsize("4").fontcolor("red");
        document.forms["form3"]["fone"].focus();
        return false;
        //||(!document.forms["form1"]["gsm"].value.match(gsm_regex1))||document.forms["form1"]["gsm"].value.toString().length!=11
}
 else if(add.trim(add)=="")
        {
            alert('Address Field is Empty!');
            errmsg.innerHTML="Address  Field is Empty.".bold().fontsize("4").fontcolor("red");
            document.forms["form3"]["address"].focus();
            return false;
        }

        else if(!add.trim(add).match(AddressFormat))
        {
            alert("Make sure there are no numbers or special \ncharacters(eg:1,'',$) in the Address field.");
            errmsg.innerHTML="Make sure there are no numbers or special \ncharacters(eg:1,'',$) in the Address field.".bold().fontsize("4").fontcolor("red");
            document.forms["form3"]["address"].focus();
            return false;
        }


 else if(mail1.trim(mail1)==""){
        alert("Email address is Empty");
        errmsg.innerHTML="Email address  Field is Empty.".bold().fontsize("4").fontcolor("red");
        document.forms["form3"]["email1"].focus();
        return false;
}

else if(!mail1.trim(mail1).match(email_regex))
        {
            alert('Please enter a valid Email address like ####@#####.com');
            errmsg.innerHTML="Please enter a valid Email address like ####@#####.com".bold().fontsize("4").fontcolor("red");
            document.forms["form3"]["email1"].focus();
            return false;
        }



else{
    return true;

}

}

/*
 * This validates the update form of the contacts profile-containg 
 * the data entered at the above immediate form
 * 
 */

function UpdateFormValidation3(){
var gsm_lent=document.forms["form3"]["fone"].value.toString().length;
 var gsm_regex=/^ [2-4]{3}?[7-8]{1}[0-9]{9}/;
 var gsm_regex1=/^[08064500095]{11}/;
 var NameFormat= /^[a-z A-Z,.]{2,35}$/;
 var AddressFormat= /^[a-z A-Z0-9,;:-_.]{5,160}$/;
 var lga= /^[a-z A-Z0-9,-_.]{2,35}$/;
 var email_regex=/^([\w-\. ]+@([\w-]+\.)+[\w-]{2,4})?$/;
 //var mail1=trim()
 var fone=document.forms["form3"]["fone"].value.replace("/^\s+|\s+$/g",'');
    if(document.forms["form3"]["nam"].value==""){
         //window.alert("Thank you1");
        window.alert("Name is Empty");
        document.forms["form3"]["nam"].focus();
        return false;
    }
    else if(!document.forms["form3"]["nam"].value.match(NameFormat))
        {
            alert('Name must have characters only and must be between 3 to 35');
            document.forms["form3"]["nam"].focus();
            return false;
        }
   else if(document.forms["form3"]["relation_pos"].value==""){
       window.alert("Relationship/Position is Empty");
        document.forms["form3"]["relation_pos"].focus();
        return false;

    }
    else if(!document.forms["form3"]["relation_pos"].value.match(NameFormat))
        {
            alert('Relationship/Position  must have characters only and must be between 3 to 35');
            document.forms["form3"]["relation_pos"].focus();
            return false;
        }
        else if(document.forms["form3"]["fone"].value=="")
        {
            alert('Mobile Number Field is Empty!');
            document.forms["form3"]["fone"].focus();
            return false;
        }
   else if(!fone.match(gsm_regex)){
       window.alert("Mobile Number must be in this format \n234##########.\nCheck also the correct length of the numbers. ");
        document.forms["form3"]["fone"].focus();
        return false;
        //||(!document.forms["form1"]["gsm"].value.match(gsm_regex1))||document.forms["form1"]["gsm"].value.toString().length!=11
}
 else if(document.forms["form3"]["address"].value.trim(document.forms["form3"]["address"].value)=="")
        {
            alert('Address Field is Empty!');
            document.forms["form3"]["address"].focus();
            return false;
        }

        else if(!document.forms["form3"]["address"].value.match(AddressFormat))
        {
            window.alert("Make sure there are no numbers or special \ncharacters(eg:1,'',$) in the Address field.");
            document.forms["form3"]["address"].focus();
            return false;
        }


 else if(document.forms["form3"]["email1"].value==""){
    window.alert("Email address is Empty");
        document.forms["form3"]["email1"].focus();
        return false;
}

else if(!trim(document.forms["form3"]["email1"]).value.match(email_regex))
        {
            alert('Pls enter a valid Email address like ####@#####.com');
            document.forms["form3"]["email1"].focus();
            return false;
        }



else{
    return true;

}

}
/*
 * This method validates the ChangePassWord form.
 * 
 */
function ChangePassWordFormValidation(){
 var errmsg=document.getElementById("errmsg");
 var lga= /^[a-z A-Z0-9,-_.]{4,35}$/;

 var opass=document.forms["form5"]["opass"].value;
 var npass=document.forms["form5"]["npass"].value;
 var vnpass=document.forms["form5"]["vnpass"].value;
  
   if(opass.trim(opass)==""){
    alert("Old Password is Empty");
    errmsg.innerHTML="Please enter a value in the Old Password field.".bold().fontsize("4").fontcolor("red");
    document.forms["form5"]["opass"].focus();
     return false;

    }
     else  if(!opass.trim(opass).match(lga)){
   alert("Make sure there are no numbers or special\n characters(eg:1,;@,&,'',$) in the Old Password field and that the number of characters is atleast 4.");
   errmsg.innerHTML="Make sure there are no numbers or special\n characters(eg:1,;@,&,'',$) in the Old Password field and that the number of characters is atleast 4.".bold().fontsize("4").fontcolor("red");
   document.forms["form5"]["opass"].focus();
   return false;
}
else  if(npass.trim(npass)==""){

    alert("New Password is Empty");
    errmsg.innerHTML="Please enter a value in the New Password field.".bold().fontsize("4").fontcolor("red");
    document.forms["form5"]["npass"].focus();
     return false;
}
 else  if(!npass.trim(npass).match(lga)){
   alert("Make sure there are no numbers or special\n characters(eg:1,;@,&,'',$) in the New Password field.");
   errmsg.innerHTML="Make sure there are no numbers or special\n characters(eg:1,;@,&,'',$) in the New Password field.".bold().fontsize("4").fontcolor("red");
   document.forms["form5"]["npass"].focus();
   return false;
}
else  if(vnpass.trim(vnpass)==""){

    alert("Verify New Password is Empty");
    errmsg.innerHTML="Please enter a value in the 'Verify New Password' field.".bold().fontsize("4").fontcolor("red");
    document.forms["form5"]["vnpass"].focus();
     return false;
}
 else  if(!vnpass.trim(vnpass).match(lga)){
   alert("Make sure there are no numbers or special\n characters(eg:1,;@,&,'',$) in the 'Verify New Password'.");
   errmsg.innerHTML="Make sure there are no numbers or special\n characters(eg:1,;@,&,'',$) in the 'Verify New Password'.".bold().fontsize("4").fontcolor("red");
   document.forms["form5"]["vnpass"].focus();
   return false;
}
else if(npass.trim(npass)!=vnpass.trim(vnpass))
        {
         alert("New  Password and Verify New Password Field must be the same");
          errmsg.innerHTML="<strong> 'New  Password' and 'Verify New Password' Field must be the same</strong>".bold().fontcolor("red").fontsize("4");
          document.forms["form5"]["vnpass"].focus();
          return false;
        }

else{
    return true;

}

}
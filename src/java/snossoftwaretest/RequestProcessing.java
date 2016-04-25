/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * This class receives request from the web, mobile web and mobile native versions 
 * of SNOS software version; it then processes the request via http response, 
 * json web services and json repectively.
 * Developer: Afam Okonkwo
 * @author Afam Okonkwo;
 * @param class
 * @see SmsMain.java,Reconsoft.java,JsonProperties.java,SnosSendEmail.java,UserInforGet
 * @version 1.0
 * 
 */

package snossoftwaretest;

import java.io.IOException;
import java.util.logging.Level;
import org.apache.log4j.Logger;
import javax.servlet.*;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.Vector;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import com.google.gson.*;
import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Message;
import javax.swing.JOptionPane;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

/**
 * This class receives request from the web, mobile web and mobile native versions 
 * of SNOS software version; it then processes the request via http response, 
 * json web services and json respectively.
 *  @author Afam Okonkwo;
 * @param row Vector object called row which stores sms alerts retrieved from database.
 * @param uid :String object that tracks the id of each of user's device row or contact data row as located in the databse table.
 * This is for update purposes.
 * @param ACCOUNT_SID String object for TWILO(twilio.com) cloud sms authentication.
 * @param AUTH_TOKEN String object for TWILO(twilio.com) cloud sms authentication.
 * @see SmsMain.java,Reconsoft.java,JsonProperties.java,SnosSendEmail.java,UserInforGet
 * @version 1.0
 * 
 */
public class RequestProcessing extends HttpServlet 
{
    private Vector row;
    private String table_id="";
    public static final String ACCOUNT_SID = System.getProperty("ACCOUNT_SID");
    public static final String AUTH_TOKEN = System.getProperty("AUTH_TOKEN");
    /** 
     * This method Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @param par String- this represent the value of each form's action(example 'Submitt','Next', 'Previous',etc) 
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException 
    {
        try
        {
            // gets the out -for outputting message- object from reponse getwriter method
            PrintWriter out = response.getWriter();
            //Initializes the row Vector object.
            row=new Vector();
            //gets the http session object to be used through out this class.
            HttpSession session=request.getSession();
            //gets the servlet context object.
            ServletContext ct=session.getServletContext();
            try 
            {
                //Get the Database class object
                Reconsoft  rs=new Reconsoft();
                //logger.info( "The User just logged in");
                //Gets a database connector object.
                Connector c=new Connector();
                //Initalizes the PatternCheck Class
                PatternCheck pc=new PatternCheck();
                //Connects to the database.
                c.databaseConnect();
                 
                //Intialize the session object property to store error messages.
                session.setAttribute("errmsg","");
                //Gets the json object 
                JSONObject obj = new JSONObject();
                //Check to find out if database connection was successfull.
                if(Connector.getIsConnected())
                {
                    out.println("Connected");
                    //DB connection was okay.
                    System.out.println("DB CONNECTION SUCCESSFUL:THUS CAN CONNECT TO DB");
                }
                else
                {
                    out.write("Not Connected");
                    //DB connection was not established.And then send an email message to the administrator
                    //after storing the message to session and then redirects the client to error page, explaining that
                    //something went wrong.
                    session.setAttribute("admin_error", "This application can't connect to  database, check db parameters in web.xml");
                    response.sendRedirect(request.getContextPath()+"/EmailErrorPage.jsp");

                 }
                //Defines and declares an object for class UserInforGet-that sets and gets most of all the users personal properties
                //or variables.
                 UserInforGet uifg;
                 //search the http request sent for a parameter called action and gets it value.
                 String par=request.getParameter("action");
                 //search the http request sent for a parameter called source-to know 
                 //whether the request is coming from destop browser or mobile phone and gets it value.
                 String source=request.getParameter("source");
                 //Check if the par-that is action value is not equal to null nor has empty string.
                 if(par!=null &&!par.equals(""))
                 {
                     // If the par- or action value- sent is "Next" and has this other parameters-that is if is the first Client 
                     //or first stage of Registration.
                    if(par.equalsIgnoreCase("Next >>") && request.getParameter("id").equalsIgnoreCase("reg1"))
                    {
                        //declare a string and get the surname and othernames of the client and concatenate the two names
                        String msg;
                        String name=request.getParameter("Sname").trim()+"  "+request.getParameter("Oname").trim();
                        //Gets the session id-which is unique- of the moment to help you distinguish between each client later
                        String sessid=request.getSession().getId();
                        //Initialize the snos variable and the int type
                        String snos="";
                        int sn=0;
                        //gets the last client identity number- SNOS number-from the database
                        sn  =rs.SNOSCounter();
                      //Checks to ensure that it is not zero or empty, and displays error page if it is
                        if(sn==0){
                         session.setAttribute("admin_error", "The GENERATED SNOS Number cannot be zero");
                         response.sendRedirect(request.getContextPath()+"/EmailErrorPage.jsp");
                        
                    }
                    //it is neither zero nor empty.
                    else
                    {
                        //increments the counter and then conactenates the number to form the right client id format e.g SNOS1 or SNOS2 
                        sn++;
                        snos="SNOS"+Integer.toString(sn);
                        //check the database to ensure that the this particular id does not exists already; 
                        //and alerts the admin and displays error page if it does
                        if(rs.CheckIfItemExist("snos_table","snos_type", snos) && rs.CheckIfItemExist("snos_table","status", "active") )
                        {
                            session.setAttribute("admin_error", "This SNOS Number already exists.The generated SNOS number  '"+snos+"' for this new  user with this email address "+request.getParameter("email1").trim()+" And with this Name: "+request.getParameter("Sname").trim()+" "+request.getParameter("Oname").trim()+"\n And the Client that has this SNOS Number '"+snos+"' is still an active Client ");
                            response.sendRedirect(request.getContextPath()+"/EmailErrorPage.jsp");
                            // Logger.getLogger(RequestProcessing.class.getName()).log(Level.SEVERE, null, ex);
                        }
                     }
                     String link1="";
                     //Is this request coming from a mobile web browser or mobile native version of SNOS app
                     if(request.getParameter("source").equals("mobile"))
                     {
                         //get the mobile_host url from System property method as initialized by the AppListener class
                         String mobile_host = System.getProperty("mobile_host");
                         //construct a url that will be sent to the user's email box for registration confirmation.
                         link1=  "http://"+mobile_host+request.getContextPath()+"/index.html?&action=confirm&sessid="+sessid+"&sid="+snos+"&source=mobile";
                       
                     }
                     //then this request must be coming from destop webrowser.
                     else
                     {
                         //construct a url that will be sent to the user's email box for registration confirmation.
                         link1=  "http://"+request.getHeader("host")+request.getContextPath()+"/servlets/RequestProcessing?action=confirm&source=desktop&sessid="+sessid+"&sid="+snos;
                     }
                     //store the user SNOS number/ the constructed link, his/her firstname and email address in a session for later references
                     session.setAttribute("snos_type", snos);
                     request.getSession().setAttribute("link1", link1);
                     request.getSession().setAttribute("fname", request.getParameter("Sname").trim());
                     request.getSession().setAttribute("email1", request.getParameter("email1").trim());
                     String lga="";
                     //Does the request contain lga or lgaa parameter from html form text value in LGA field
                     //lga is for select field while lgaa is for just text field-this is done because of possible ajax url connection
                     //,not being available to populate select or List field with lists of Retrieved local governments.
                     if(request.getParameter("lga")!=null)
                     {
                         lga=request.getParameter("lga").trim();
                     }
                     else if(request.getParameter("lgaa")!=null)
                     {
                         lga=request.getParameter("lgaa").trim();
                     }
                     // Validated and check the inputed fields for special characters or empty fields or even wrong formats
                     if(pc.checkUserName(request.getParameter("Sname").trim(), "Client Name", "Okonkwo or Ugwu")
                     && pc.checkUserName(request.getParameter("Oname").trim(), "Other Name", "Surname FirstName LastName") && pc.checkGsm(request.getParameter("gsm").trim(), "Snos Gsm Field", "234xxxxxxxxxx or 080xxxxxxxx") && pc.checkEmail(request.getParameter("email1").trim(),"Client's Email","newuser@snosfotress.com")
                     && pc.checkLocation(request.getParameter("address").trim(), "Client's Location","5 Sule Abuka Crescent,Opebi,Ikeja") && pc.checkUserName(request.getParameter("state").trim(),"State Field" , "Enugu or Anambra or Lagos") && !(lga.contains("Choose one") ||lga.equals(""))
                     && pc.checkArbitraryField(request.getParameter("pass").trim(),"Password Field" , "Bungallow or Duplex or One-Bedroom Flat"))
                     {
                         // Ensure that both the two passwords(both the password and Verify or Confirm password) fields march  
                         if(!request.getParameter("pass").trim().equals(request.getParameter("vpass").trim()))
                         {
                             // Is this request coming from a mobile web browser or mobile native version of SNOS app 
                             if(request.getParameter("source").equals("mobile"))
                             {
                                 //json status 3 means error message
                                 obj.put("status", "3");
                                 //write that the error message
                                 obj.put("msg", "The two password fields must be the same.<br>Thank you");
                                 //Sends the JSON message to either the mobile browser or mobile native app.
                                 sendJSON(response, obj.toJSONString());
                             }
                             // the request comes from the desktop browser.
                             else
                             {
                                 //write that the error message and store it in session property called errmsg
                                 session.setAttribute("errmsg","The two password fields must be the same.<br>Thank you");
                                 //redirect to the same page to display the error message.
                                 response.sendRedirect(request.getContextPath()+"/user_register.jsp");
                             }
                      
                         }
                         // Has this email address being registered already?
                         if(rs.CheckIfItemExist("clients_temp_tab","email1", request.getParameter("email1").trim()))
                         {
                             //since the email already exists, find if the request is coming from mobile device
                             if(request.getParameter("source").equals("mobile"))
                             {
                                 //json status 3 means error message
                                 obj.put("status", "3");
                                 //write that the error message
                                 obj.put("msg", "You have already registered in this service with this email \n"+request.getParameter("email1")+"<br>Please enter a new email address<br>Thank you");
                                 //Sends the JSON message to either the mobile browser or mobile native app.
                                 sendJSON(response, obj.toJSONString());
                             }
                             else
                             {
                                 //write that the error message and store it in session property called errmsg
                                 session.setAttribute("errmsg","You have already registered in this site with this email "+request.getParameter("email1")+".<br>Thank you");
                                 //redirect to the same page to display the error message.
                                 response.sendRedirect(request.getContextPath()+"/user_register.jsp");
                             }
                       
                         }
                         // The email address has not being registered 
                         else
                         {
                             //obtain an object of the get and setter(UserInforGet)class . Note "1c" means that the first stage oc client
                             //registration has been completed.
                             uifg=new   UserInforGet(snos,request.getParameter("gsm").trim(),name,request.getParameter("address").trim(),lga,request.getParameter("state").trim(),request.getParameter("email1").trim(),request.getParameter("pass").trim(),new java.util.Date().toLocaleString(),sessid,link1,"1c");
                             //insert the form information to the database temporal table called "clients_temp_tab".
                             rs.AddUserInfo(uifg, "clients_temp_tab");
                             //Add the new generated SNOS number to database to keep track of it.
                             rs.AddSnos(uifg, "active");
                             //get these parameters from System property method as initialized by the AppListener class-with 
                             //web.xml context init parameters
                             String sender = System.getProperty("siteadmin_email");
                             String admin_emailpassword = System.getProperty("admin_emailpassword");
                             String admin_email = System.getProperty("adminemail");
                             String firstadmin_gsm = System.getProperty("firstadmin_gsm");
                             String secondadmin_gsm = System.getProperty("secondadmin_gsm");
                             String messageToSend="A CLIENT IS CURRENTLY REGISTERING FOR SNOS\n\n";
                             //construct the sms message to be sent to the admin alerting him or her that somebody is registering
                             messageToSend+=  "E-mail: "+request.getParameter("email1").trim()+"\n";
                             messageToSend+=  "Name: "+name+"\n";
                             messageToSend+=  "SNOSID: "+snos+"\n";
                             messageToSend+=  "GSM Number: "+request.getParameter("gsm").trim()+"\n\n\n";
                             messageToSend+=  "snosfortress.com";
                             //Validate these the above web.xml parameters.
                             if(sender==null||admin_emailpassword==null ||admin_email==null)
                             {
                                 //find if the request is coming from mobile device
                                 if(request.getParameter("source").equals("mobile"))
                                 {
                                     //then compose and send json error message what happened.
                                     msg="We are sorry: An internal server error occured.The administrator has been notified<br>Thank you";
                                     obj.put("status", "3");
                                     obj.put("msg", msg);
                                     sendJSON(response, obj.toJSONString());
                                  }
                                  // it must have come from the desktop browser.
                                  else
                                  {
                                     //write that the error message and store it in session property called admin_error
                                     session.setAttribute("admin_error", "The SNOC administrators email parameters have been tampered with<br>Thus this application cannot send email.");
                                     //redirect  to EmailErrorPage.jsp to display a friendlier error message to the user.
                                     response.sendRedirect(request.getContextPath()+"/EmailErrorPage.jsp");
                                  }
                                  
                                  
                               }
                               // the web.xml parameters are neither null nor empty.
                               else
                               {
                                   // Send the sms message and check if it sent successfully by returning true.
                                   if(SendSMS(messageToSend,firstadmin_gsm,session,response, request) || SendSMS(messageToSend,secondadmin_gsm,session,response, request) )
                                   {
                                       // sms sent successfully
                                       System.out.println("SMS sent to notify one of the Admins");
                                       if(request.getParameter("source").equals("mobile"))
                                       {
                                           //json status 1 means success or okay and all went well. 
                                           obj.put("status", "1");
                                           //compose and send the mobile device this message.
                                           obj.put("msg", "First Stage of your Registration was successfull");
                                           sendJSON(response, obj.toJSONString());
                                       }
                                       else
                                       {
                                           //it was successful or okay and all went well.Redirect to start the second stage of registration 
                                           response.sendRedirect(request.getContextPath()+"/device_reg.jsp");
                                       }
                                   }
                                   else
                                   {
                                       //something went wrong and the sms was not sent.
                                       response.sendError(500);
                                       System.out.println("SMS  not sent to notify first Admin-Error");
                                       if(request.getParameter("source").equals("mobile"))
                                       {
                                           msg="We are sorry: An internal server error occured.The administrator has been notified<br>Thank you";
                                           obj.put("status", "3");
                                           obj.put("msg", msg);
                                           sendJSON(response, obj.toJSONString());
                                       }
                                       else
                                       {
                                           session.setAttribute("admin_error", "The SNOC administrators GSM numbers have been tampered with. Thus this application cannot send SMS.");
                                           response.sendRedirect(request.getContextPath()+"/EmailErrorPage.jsp");
                                       }
                                   }
                               }
                            }
                     }
                        //validation of the form fields with pattern checkers were not successfull.
                        else
                        {
                            if(request.getParameter("source").equals("mobile"))
                            {
                                //compose and send  error message to the mobile device
                                obj.put("status", "3");
                                obj.put("msg", "One of your fields has invalid inputs like numbers,or has too long letters.Also verify that you didn't enter special characters(eg:!,','',$,%) or any other wrong input(s) and Make sure  that you entered the correct gsm and email formats.<br>Thank you");
                                sendJSON(response, obj.toJSONString());
                            }
                            else
                            {
                                //compose and send  error message to the desktop browser.
                                session.setAttribute("errmsg","One of your fields has invalid inputs like numbers,or has too long letters.Also verify that you didn't enter special characters(eg:!,','',$,%) or any other wrong input(s) and Make sure  that you entered the correct gsm and email formats.<br>Thank you");
                                response.sendRedirect(request.getContextPath()+"/user_register.jsp");
                            }
                        }
                    //Does the par variable or form button action value ="Next>>" and id=reg2-which means second form and second stage of (device )registration
                    }
                    else if(par.equalsIgnoreCase("Next >>") && request.getParameter("id").equalsIgnoreCase("reg2"))
                    {
                        //get all the values from the device registration form-located at the(device_reg.jsp) jsp page.
                        String UserName=request.getParameter("nam").trim();
                        String desc=request.getParameter("description").trim();
                        String house_type=request.getParameter("house_type").trim();
                        String loc=request.getParameter("Location").trim();
                        String add=request.getParameter("address").trim();
                        String state=request.getParameter("state").trim();
                        String extra_sensor=request.getParameter("extra_sensor").trim();
                        String lga="";
                        //Does the request contain lga or lgaa parameter from html form text value in LGA field
                        //NB:lga is for select field while lgaa is for just ordinary text field-this is done because of possible ajax url connection
                        //,not being available to populate select or List field with lists of Retrieved local governments.
                        if(request.getParameter("lga")!=null)
                        {
                            lga=request.getParameter("lga").trim();
                        }
                        else if(request.getParameter("lgaa")!=null)
                        {
                             lga=request.getParameter("lgaa").trim();
                        }
                        // Validate and check the inputed fields for special characters or empty fields or even wrong formats
                        if(pc.checkUserName(UserName, "Device Field", "Mercedez Benz")
                        && pc.checkLocation(desc, "Description Field", "Cool car") && pc.checkUserName(house_type, "Housing Type Field", "Bungallow or Duplex") && pc.checkLocation(loc,"Device Location","Inside Bedroom or Back of the House")
                        && pc.checkLocation(add, "Device's Address","5 Sule Abuka Crescent,Opebi,Ikeja") && pc.checkUserName(state,"State Field" , "Enugu or Anambra or Lagos") && !(lga.contains("Choose one") ||lga.equals("")))
                        {
                            String snos= "";
                            //get the SNOS number from the mobile if the request is coming from mobile
                            if(request.getParameter("source").equals("mobile"))
                            {
                                snos=request.getParameter("snos_type");
                            }
                            else
                            {
                                //otherwise get it from stored session of the browser's desktop.
                                snos=session.getAttribute("snos_type").toString();
                            }
                            //get the obkect of the get and set class UserInforGet
                            uifg=new   UserInforGet(snos,UserName,desc,house_type,loc,add,lga,state);
                            // Insert the device or object information to a temporal table called client_device_temp_tab
                            //and update the the client registration status to show that he/she has completed the second stage of registration.
                            rs.AddDeviceInfo(uifg, "client_device_temp_tab");
                            //Update the client's current registration stage
                            //NB: 1c= stage 1, 2c=stage 2, 3c=stage 3(last stage)
                            rs.UpdateUserStatus(snos, "2c");
                            // Does the person wants to register for additional equipment/object?
                            if(extra_sensor.equals("yes"))
                            {
                                if(request.getParameter("source").equals("mobile"))
                                {
                                    //send a message to the mobile device telling it to register for additional device/object
                                    obj.put("status", "3");
                                    obj.put("msg", "Submission was Successfull.<br>Please Kindly do register for the extra device/equipment/sensor that you want to secure<br> as you previously checked in the radio button before the last submission.\nThank you.");
                                    sendJSON(response, obj.toJSONString());
                                }
                                else
                                {
                                    //send a message to the desktop browser telling it to register for additional device/object
                                    session.setAttribute("status","Submission was Successfull.<br>Please Kindly do register for the extra device/equipment/sensor that you want to secure<br> as you previously checked in the radio button before the last submission.\nThank you.");
                                    response.sendRedirect(request.getContextPath()+"/device_reg.jsp");
                                }
                            }
                            // Then he/she does not want to register additional object/equipment.
                            else
                            {
                                if(request.getParameter("source").equals("mobile"))
                                {
                                    //out put the below message
                                    obj.put("status", "1");
                                    obj.put("msg", "The Device Registration was successfull");
                                    sendJSON(response, obj.toJSONString());
                                }
                                else
                                {
                                    
                                    //construct the form for the 3rd stage of registration-dynamically-and then store it in the session to print it in the jsp file of the 3rd stage.
                                    String form="<form onsubmit='return FormValidation3();'   method='POST'  action='servlets/RequestProcessing'  name='form3' >";
                                    form+=" <tr><td align='center' width = '100%'  height= '20' > <font face= 'Times New Roman' size = '3' color='blue'>Name:</font><span style='color:#FF0000'>*</span><input type='text' size = '30' name = 'nam' maxlength='50' placeholder='Name'  style='margin-right:30px;'></td></tr>";
                                    form+="<tr><td  align='center' width = '100%'  height= '20' >";
                                    form+="<font face= 'Times New Roman' size = '3' color='blue'>Relationship/Position:</font><span style='color:#FF0000'>*</span>";
                                    form+="<br /><input type='text' size = '30' name = 'relation_pos' maxlength='50'></td><td align='center' width = '100%'  height= '20' ><font face= 'Times New Roman' size = '3' color='blue'>Mobile Number:</font><span style='color:#FF0000'>*</span>";
                                    form+="<input type='text' size = '30' onchange=\"return checkEmail_GsmBeforeRegistrationAddress(this.value,'gsm');\" name = 'fone' maxlength='50' style='margin-right:30px;' >";
                                    form+="</td></tr>";
                                    form+="<tr><td  align='center' width = '100%'  height= '20' >";
                                    form+="<font face= 'Times New Roman' size = '3' color='blue'>Address:</font><span style='color:#FF0000'>*</span>";
                                    form+="<br /><textarea cols='20' rows='4' name='address'> </textarea></td><td align='center' width = '100%'  height= '20' ><font face= 'Times New Roman' size = '3' color='blue'>E-mail:</font><span style='color:#FF0000'>*</span>";
                                    form+="<input type='text' size = '30' onchange=\"return checkEmail_GsmBeforeRegistrationAddress(this.value,'myemail');\" name = 'email1' maxlength='50' style='margin-right:30px;' >";
                                    form+="</td></tr><tr><td width='400' height='10'  align='center' colspan='2'> <font face='Times New Roman' size='5' color='red'>Do you wish to Register additional contacts-It is highly recommended that you do?:" +"<span style='color:#FF0000'>*</span></font></td></tr>"+

                                        "<tr><td><font face= 'Times New Roman' size = '6' color='blue'>NO</font><input type='radio' checked   name='extra_contact' value='no'></td><td  align='center' width = '100%'  height= '20' >" +
                                        "<font face= 'Times New Roman' size = '6' color='blue'>Yes</font><input type='radio'  name='extra_contact' value='yes'></td>" +
                                            "</td></tr><tr><td height='5'>&nbsp;&nbsp;</td></tr><tr><td align='center' width = '100%'  height= '20' colspan='2' >";
                                    form+="<input type='submit' value='Submitt'  name='action' style='border: 1px solid #698EEF; padding-left: 4; padding-right: 4; padding-top: 1; padding-bottom: 1; color:#FFF;background-color:#009999'>";
                                    form+="&nbsp; <input type='reset' value='Reset' name='can' style='border: 1px solid #698EEF; padding-left: 4; padding-right: 4; padding-top: 1; padding-bottom: 1; color:#FFF;background-color:#009999'>";
                                    form+="<input type='hidden' value='reg3' name='id' style='border: 1px solid #698EEF; padding-left: 4; padding-right: 4; padding-top: 1; padding-bottom: 1; color:#FFF;background-color:#009999'>";
                                    form+="<input type='hidden' value='desktop' name='source' >";
                                    form+="</td></tr> </form><tr><td align='center' width = '100%'  height= '20' colspan='2' ><font color='#FF0000'>  </font></td></tr></form>";
                                    request.getSession().setAttribute("info", form);
                                    request.getSession().setAttribute("msg", "");
                                    response.sendRedirect(request.getContextPath()+"/clients_contacts_reg.jsp");
                                }
                            }
                   
                        }
                        //then the object/device registration's validation was not successfull.
                        else
                        {
                            if(request.getParameter("source").equals("mobile"))
                            {
                                //compose and send the necessary error message
                                obj.put("status", "3");
                                obj.put("msg", "One of your fields has invalid inputs like numbers,or has too long letters.Also verify that you didn't enter special characters(eg:!,','',$,%) or any other wrong input(s) and Make sure  that you entered the correct gsm and email formats.<br>Thank you");
                                sendJSON(response, obj.toJSONString());
                            }
                            else
                            {
                                //compose and send the necessary message error
                                session.setAttribute("errmsg","One of your fields has invalid inputs like numbers,or has too long letters.Also verify that you didn't enter special characters(eg:!,','',$,%) or any other wrong input(s) and Make sure  that you entered the correct gsm and email formats.<br>Thank you");
                                response.sendRedirect(request.getContextPath()+"/device_reg.jsp");
                            }
                        }
                        
                    }
                    //Does the par variable or form button action value ="Submitt" and id=reg3-which means 3rd form and 3rd stage of (contacts)registration
                    else if(par.equalsIgnoreCase("Submitt") && request.getParameter("id").equalsIgnoreCase("reg3"))
                    {
                        //get the name of the contact person
                        String UserName=request.getParameter("nam").trim();
                        String snos="";
                        //retrieve the SNOS number from either mobile device's request or browser's session
                        if(request.getParameter("source").equals("mobile"))
                        {
                            snos=request.getParameter("snos_type");
                        }
                        else
                        {
                            snos=   session.getAttribute("snos_type").toString();
                        }
                        //retrieve all other form parameters sent along with the request from clients_contacts_reg.jsp
                        String fone=request.getParameter("fone").trim();
                        String relation_pos=request.getParameter("relation_pos").trim();
                        String add=request.getParameter("address").trim();
                        String mail1=request.getParameter("email1").trim();
                        String extra_contact=request.getParameter("extra_contact").trim();
                        //once again validate for special or empty characters or wrong formats using pattern checker class:PatternChecker.java
                        if(pc.checkUserName(UserName, "Name Field", "Ugwu Mike John")
                        && pc.checkLocation(relation_pos, "Realations's Position Field", "DPO,Younger Brother") && pc.checkGsm(fone, "Gsm", "2348xxxxxxxxx or 08xxxxxxxxx")
                        && pc.checkLocation(add, "Contacts's Address","5 Sule Abuka Crescent,Opebi,Ikeja") && pc.checkEmail(mail1,"Client's Email","newuser@snosfotress.com"))
                        {
                            // Insert the contact's data to a temporal table and update the client's registration status to 3c meaning that the 3rd and final stage of
                            //registration has just been completed.
                            uifg=new UserInforGet(snos,UserName,relation_pos,add,mail1,fone);
                            rs.AddClient_Contacts(uifg, "clients_contacts_temp_tab");
                            rs.UpdateUserStatus(snos, "3c");
                            //once agaiin, does this user wish to register additional contacts-which is highly recommended since the current required number is 8.
                            if(extra_contact.equals("yes"))
                            {
                                if(request.getParameter("source").equals("mobile"))
                                {
                                    //out put the required message.
                                    obj.put("status", "3");
                                    obj.put("msg", "Submission was Successfull.<br>Please Kindly do add the additional contacts that you want to register<br> as you previously checked in the radio button before the last submission.\nThank you.");
                                    sendJSON(response, obj.toJSONString());
                                }
                                else
                                {
                                    String form="<form onsubmit='return FormValidation3();'   method='POST'  action='servlets/RequestProcessing'  name='form3' >";
                                    form+=" <tr><td align='center' width = '100%'  height= '20' > <font face= 'Times New Roman' size = '3' color='blue'>Name:</font><span style='color:#FF0000'>*</span><input type='text' size = '30' name = 'nam' maxlength='50' placeholder='Name'  style='margin-right:30px;'></td></tr>";
                                    form+="<tr><td  align='center' width = '100%'  height= '20' >";
                                    form+="<font face= 'Times New Roman' size = '3' color='blue'>Relationship/Position:</font><span style='color:#FF0000'>*</span>";
                                    form+="<br /><input type='text' size = '30' name = 'relation_pos' maxlength='50'></td><td align='center' width = '100%'  height= '20' ><font face= 'Times New Roman' size = '3' color='blue'>Mobile Number:</font><span style='color:#FF0000'>*</span>";
                                    form+="<input type='text' size = '30' name = 'fone' maxlength='50' style='margin-right:30px;' >";
                                    form+="</td></tr>";
                                    form+="<tr><td  align='center' width = '100%'  height= '20' >";
                                    form+="<font face= 'Times New Roman' size = '3' color='blue'>Address:</font><span style='color:#FF0000'>*</span>";
                                    form+="<br /><textarea cols='20' rows='4' name='address'> </textarea></td><td align='center' width = '100%'  height= '20' ><font face= 'Times New Roman' size = '3' color='blue'>E-mail:</font><span style='color:#FF0000'>*</span>";
                                    form+="<input type='text' size = '30' name = 'email1' maxlength='50' style='margin-right:30px;' >";
                                    form+="</td></tr><tr><td width='400' height='10'  align='center' colspan='2'> <font face='Times New Roman' size='5' color='red'>Do you wish to Register additional contacts-It is highly recommended that you do?:" +
                                    "<span style='color:#FF0000'>*</span></font></td></tr><tr><td><font face= 'Times New Roman' size = '6' color='blue'>NO</font><input type='radio' checked   name='extra_contact' value='no'></td><td  align='center' width = '100%'  height= '20' >" +
                                    "<font face= 'Times New Roman' size = '6' color='blue'>Yes</font><input type='radio'  name='extra_contact' value='yes'></td>" +
                                    "</td></tr><tr><td height='5'>&nbsp;&nbsp;</td></tr><tr><td align='center' width = '100%'  height= '20' colspan='2' >";
                                    form+="<input type='submit' value='Submitt'  name='action' style='border: 1px solid #698EEF; padding-left: 4; padding-right: 4; padding-top: 1; padding-bottom: 1; color:#FFF;background-color:#009999'>";
                                    form+="&nbsp; <input type='reset' value='Reset' name='can' style='border: 1px solid #698EEF; padding-left: 4; padding-right: 4; padding-top: 1; padding-bottom: 1; color:#FFF;background-color:#009999'>";
                                    form+="<input type='hidden' value='desktop' name='source' ><input type='hidden' value='reg3' name='id' style='border: 1px solid #698EEF; padding-left: 4; padding-right: 4; padding-top: 1; padding-bottom: 1; color:#FFF;background-color:#009999'>";
                                    form+="</td></tr> </form><tr><td td align='center' width = '100%'  height= '20' colspan='2' ><font color='#FF0000'>  </font></td></tr></form>";
                                    request.getSession().setAttribute("info", form);
                                    request.getSession().setAttribute("msg", "");
                                    session.setAttribute("status","Submission was Successfull.<br>Please Kindly do register for the extra contacts <br> as you previously checked in the radio button before the last submission.\nThank you.");
                                    response.sendRedirect(request.getContextPath()+"/clients_contacts_reg.jsp");
                                }
                            }
                            //I don't want to register for additional contacts.
                            else
                            {
                                //get the persons info(firstname, email address and the comfirmation link) from temporal table
                                 String msg="";
                                 String host="";
                                 String fname=rs.RetrieveItem("clients_temp_tab", "snos_type", snos, "name");
                                 String mylink=rs.RetrieveItem("clients_temp_tab", "snos_type", snos, "link1");
                                 String myemail=rs.RetrieveItem("clients_temp_tab", "snos_type", snos, "email1");
                                 if(request.getParameter("source").equals("mobile"))
                                 {
                                     //get the mobile host from the web.xml as initialized by the AppListener class
                                     host=System.getProperty("mobile_host");
                                 }
                                 else
                                 {
                                     //else get the destop browser website host
                                     host=request.getHeader("host")+request.getContextPath();
                                 }
                                 //use this to compose email message that will be sent to the client for SNOS REGISTRATION CONFIRMATION.
                                 String emeailMsg="";
                                 emeailMsg="Hi "+fname+
                                 ", thank you for registering at \n"+"http://"+host+"\n" +
                                 "Please click on the link below to confirm your registration and thus complete it\n"+
                                 "Click on:\n "+mylink+
                                 "  "+"\nThank You\n\n\n...From Snos Team!";
                                 //get the administrators email address and email password
                                 //to use his account to send the email from his Email Service Provider
                                 String sender = System.getProperty("siteadmin_email");
                                 String admin_emailpassword = System.getProperty("admin_emailpassword");
                                 if(sender==null||admin_emailpassword==null)
                                 {
                                     // then these values are null
                                     if(request.getParameter("source").equals("mobile"))
                                     {
                                         //out put the required message.
                                         obj.put("status", "3");
                                         obj.put("msg", "Can't send email.Server Internal Error occured please contact the administrator.\nThank you.");
                                         sendJSON(response, obj.toJSONString());
                                     }
                                     else
                                     {
                                          session.setAttribute("errmsg","Can't send email.Server Internal Error occured please contact the administrator.<br>Thank you.");
                                          session.setAttribute("admin_error","Can't send email.The email sending parameter has been tempered with.<br>Thank you.");
                                          response.sendRedirect(request.getContextPath()+"/clients_contacts_reg.jsp");
                                     }
                                 }
                                 //then the email parameters are in tact.Now go ahead and send the email.
                                 else
                                 {
                                     if(new SnosSendEmail().SNOSemailSendToOne(sender, admin_emailpassword,"SNOS REGISTRATION CONFIRMATION",emeailMsg ,myemail))
                                    {
                                        //the email was sent successfully.
                                        if(request.getParameter("source").equals("mobile"))
                                        {
                                            //compose appropriate message
                                            msg="<tr><td><font face='Times New Roman' size='3' color='blue'><CENTER>Congratulations!!!</CENTER> <br>Your Last Stage of registration is successfull,An email has been sent to your inbox.Please click the link sent to your inbox to <strong>confirm</strong> and complete your registration.\n\nThank you. <br><br><br>Wants to go back to home page?<a href='#pagehome'><font face='Times New Roman' size='5' color='blue'>Click Me</a></font></td></tr>";
                                        }
                                        //Is this request coming PRIMARILY from mobile native and not mobile browser
                                        else if(request.getParameter("mnative")!=null)
                                        {
                                            //compose apprpriate message for the mobile native
                                            msg="Congratulations!!!\nYour Last Stage of registration is successfull,An email has been sent to your inbox.Please click the link sent to your inbox to confirm and complete your registration.\n\nThank you. ";
                                        }
                                        else
                                        {
                                            //compose appropriate message for standalone/destop browser.
                                            msg="<tr><td><font face='Times New Roman' size='5' color='blue'><CENTER>Congratulations!!!</CENTER> <br>Your Last Stage of registration is successfull,An email has been sent to your inbox.Please click the link sent to your inbox to <strong>confirm</strong> and complete your registration.\n\nThank you. <br><br><br>Wants to go back to home page?<a href='index.jsp'><font face='Times New Roman' size='5' color='blue'>Click Me</a></font></td></tr>";
                                        }

                                      }
                                      //then the email was not sent
                                      else
                                      {
                                          if(request.getParameter("mnative")!=null)
                                          {
                                              //compose the appropriate error message for mobile native version only
                                              msg="Error Sending E-Mail!!!\nOops something has gone pearshaped!Your Last Stage of registration was not successfull,An e-mail could not be sent to your inbox.Please go back to the home page and re-login to resend the e-mail  and complete your registration.\n\nThank you. ";
                                           }
                                           else
                                           {
                                               //compose the appropriate error message for mobile web version and destop browser only
                                               msg="<tr><td><font face='Times New Roman' size='5' color='blue'><CENTER>Error Sending E-Mail!!!</CENTER> <br>Oops something has gone pearshaped!Your Last Stage of registration was not successfull,An e-mail could not be sent to your inbox.Please go back to the home page and re-login to resend the e-mail  and complete your registration.\n\nThank you. <br><br><br>Wants to go back to home page?<a href='index.jsp'><font face='Times New Roman' size='5' color='blue'>Click Me</a></font></td></tr>";
                                           }

                                      }

                                 }
                                 //the send the appropriate email sent-result message depending on the version of the SNOS app.
                                 if(request.getParameter("source").equals("mobile"))
                                 {
                                      obj.put("status", "1");
                                      obj.put("msg", msg);
                                      sendJSON(response, obj.toJSONString());
                       
                                 }
                                 else
                                 {
                                     //remove the unneeded session property now.
                                     request.getSession().removeAttribute("info");
                                     //store the email sent-result message in a new session property called msg
                                     //to be outputed at jsp file of the contacs registration page.
                                     request.getSession().setAttribute("msg", msg);
                                     response.sendRedirect(request.getContextPath()+"/clients_contacts_reg.jsp");
                                 }

                            }
                        }
                        // validation-was not successfull- for special or empty characters or wrong formats using pattern checker class:PatternChecker.java
                        else
                        {
                            if(request.getParameter("source").equals("mobile"))
                            {
                                obj.put("status", "3");
                                obj.put("msg", "One of your fields has invalid inputs like numbers,or has too long letters.Also verify that you didn't enter special characters(eg:!,','',$,%) or any other wrong input(s) and Make sure  that you entered the correct gsm and email formats.<br>Thank you");
                                sendJSON(response, obj.toJSONString());
                            }
                            else
                            {
                                session.setAttribute("errmsg","One of your fields has invalid inputs like numbers,or has too long letters.Also verify that you didn't enter special characters(eg:!,','',$,%) or any other wrong input(s) and Make sure  that you entered the correct gsm and email formats.<br>Thank you");
                                response.sendRedirect(request.getContextPath()+"/clients_contacts_reg.jsp");
                            }

                        }
           }
           //Does the form action  request parameter contain 'confirm' . I mean has the user 
           //visited his email address and clicked on the link sent to his email
           else if(par.equals("confirm") && !request.getParameter("sid").equals("") && !request.getParameter("sessid").equals(""))
           {
               //use his SNOS number request parameter called 'sid' to query database for his personal infor:email,fone and name
               String msg="";
               String sid=request.getParameter("sid").trim();
               String name=  rs.RetrieveItem("clients_temp_tab", "snos_type", sid, "name");
               String email=  rs.RetrieveItem("clients_temp_tab", "snos_type", sid, "email1");
               String fone=  rs.RetrieveItem("clients_temp_tab", "snos_type", sid, "fone");
               String sender = System.getProperty("siteadmin_email");
               String admin_email = System.getProperty("adminemail");
               String admin_emailpassword = System.getProperty("admin_emailpassword");
               String firstadmin_gsm = System.getProperty("firstadmin_gsm");
               String secondadmin_gsm = System.getProperty("secondadmin_gsm");
               if(sender==null||admin_emailpassword==null ||admin_email==null)
               {
                   if(request.getParameter("source").equals("mobile"))
                   {
                       obj.put("status", "3");
                       obj.put("msg", "Can't comfirm your registration. Contact Administrator");
                       sendJSON(response, obj.toJSONString());
                    }
                    else
                    {
                        session.setAttribute("user", name);
                        session.setAttribute("errmsg", "Can't comfirm your registration. Contact Administrator");
                        session.setAttribute("admin_error", "The email parameters have been tampered with at web.xml");
                        response.sendRedirect(request.getContextPath()+"/EmailErrorPage.jsp");
                    }
                                    
               }
               //find out if this client has already been comfirmed by checking the 
               //permanent client registration table-instead of the temporal one-his/her SNOS number.
               boolean IsSnosClientConfirmed=rs.CheckIfItemExist("clients_tab","snos_type", sid);
               //if both his SNOS number exists and his email address exist in the permanent table
               if(IsSnosClientConfirmed && !email.equals(""))
               {
                   //then he/she has been confirmed, compose the needed message
                   msg="We are sorry :But our record shows that your registration has already been confirmed.You do not need to be re-confirmed.<br>Thank you.";
                   if(request.getParameter("source").equals("mobile"))
                   {
                       obj.put("status", "3");
                       obj.put("msg", msg);
                       sendJSON(response, obj.toJSONString());
                   }
                   else
                   {
                        session.setAttribute("user", name);
                        session.setAttribute("err", "0");
                        response.sendRedirect(request.getContextPath()+"/confirm_reg.jsp");
                   }
               }
               //then he/she has not been confirmed, start the process of confirmation.
               else
               {
                   //get the client's session id sent to him/her-through email- after the 3rd stage of registration
                   //and then use it to retrieve all his client info as stored in the temporal table.
                   uifg=rs.getClientDetails(sid,request.getParameter("sessid").trim(), "clients_temp_tab");
                   UserInforGet uifg1;
                   //Assign the get and set class object to another class instance
                   uifg1=uifg;
                   //get another object of get and set class, indicating the client registration status-in permanent table-to be in 'ACTIVATED' state.
                   uifg=new   UserInforGet(sid,uifg.getFone(), uifg.getClient(),uifg.getContact(),uifg.getLga(),uifg.getState(),uifg.getClientEmail(),uifg.getPass(),new java.util.Date().toLocaleString(),"","","ACTIVATED");
                   //Insert the client's data and confirmation info to the permanent table.
                   rs.AddConfirmUserInfo(uifg, "clients_tab");
                   //get ALL the client's contact's info from the contact's temporal table
                   row=rs.getClientContactsDetails(request.getParameter("sid"), "clients_contacts_temp_tab");
                   for(int i=0;i<row.size();i++)
                   {
                       String []data=(String[])row.get(i);
                       //construct a new object of get and set for the contact's info
                       uifg=new   UserInforGet(sid,data[0],data[1],data[2],data[3],data[4]);
                       //insert the info to the contact's permanent table
                       rs.AddClient_Contacts(uifg, "clients_contacts_tab");
                   }
                   //get ALL the client's object/device's info from the device's temporal table
                   row=rs.getClientDeviceDetails(sid, "client_device_temp_tab");
                   for(int i=0;i<row.size();i++)
                   {
                       String []data=(String[])row.get(i);
                       //construct a new object of get and set for the object/device's info
                       uifg=new   UserInforGet(sid,data[0],data[1],data[2],data[3],data[4],data[5],data[6]);
                       //insert the info to the object/device's permanent table.
                       rs.AddDeviceInfo(uifg, "clients_device_tab");
                   }
                   //compose the comfirmation success message
                   msg="Your registration has been finalized at this moment.<br>You will have to go back to the home page to login so that you can start using our services..<br>Thank you";
                   //compose the confirm success message to be sent to the Administrator via sms
                   String messageToSend="A CLIENT REGISTERATION FOR SNOS HAS BEEN CONFIRMED AND THUS COMPLETED \n\n";
                   messageToSend+=  "E-mail: "+email+"\n";
                   messageToSend+=  "Name: "+name+"\n";
                   messageToSend+=  "SNOSID: "+sid+"\n";
                   messageToSend+=  "GSM Number: "+fone+"\n\n\n";
                   messageToSend+=  "snosfortress.com";
                   if(sender==null||admin_emailpassword==null ||admin_email==null)
                   {
                       //send the appropriate error message
                       if(request.getParameter("source").equals("mobile"))
                       {
                           msg="Can't send Email: An internal server error occured.The administrator has been notified<br>Thank you";
                           obj.put("status", "3");
                           obj.put("msg", msg);
                           sendJSON(response, obj.toJSONString());
                       }
                       else
                       {
                           session.setAttribute("errmsg", "\"Can't send Email: An internal server error occured.The administrator has been notified<br>Thank you");
                           session.setAttribute("admin_error", "The SNOC administrators email parameters have been tampered with<br>Thus this application cannot send email.");
                           response.sendRedirect(request.getContextPath()+"/EmailErrorPage.jsp");

                       }


                   }
                   //the email parameters are in tact
                   else
                   {
                       //try to send SMS to the admini notifying about the success of the confirmation for offline client registration
                       //at the SNOC
                       if(SendSMS(messageToSend,firstadmin_gsm,session,response, request) || SendSMS(messageToSend,secondadmin_gsm,session,response, request) )
                       {
                           System.out.println("SMS sent to notify one of the Admins");
                           if(request.getParameter("source").equals("mobile"))
                           {
                               //send the appropriate message to the mobile device.
                               msg="Congratulations! <i>"+name+"</i><br><br><br>Your registration has been finalized at this moment.<br>You will have to go back to the home page to re-login so that you can start using our services..<br>Thank you";
                               obj.put("status", "1");
                               obj.put("msg", msg);
                               sendJSON(response, obj.toJSONString());
                           }
                           else
                           {
                               //send the appropriate message to the standalone browser.
                               session.setAttribute("user", name);//Store the user's name in the session to be displayed at the control panel
                               session.setAttribute("err", "1");
                               response.sendRedirect(request.getContextPath()+"/confirm_reg.jsp");
                           }
                       }
                       //the SMS sending was not successfull.
                   }
              }//close the brace of the 'else' part of finding if the client has been confirmed.
           }
           //Was the form action Button containing 'Update Profile' clicked? . I mean does the user 
           //wants to update his/her registered personal information.
           else if(par.equals("Update Profile"))
           {
               String snos="";
               //get the SNOS number from either the mobile device source or desktop source 
               if(request.getParameter("source").equals("mobile"))
               {
                   snos=request.getParameter("snos_type");
               }
               else
               {
                   snos=   session.getAttribute("snos_type").toString();
               }
               //get all the required information to update from the form.
               //we decided not to allow client to update gsm number because it is tied to his/her SNOS number
               //and thus can only be updated with administrator's notice and consent-most likely at the SNOC.
               String msg="Oops something went wrong";
               String UserName=request.getParameter("name").trim();
               String email1=request.getParameter("email1").trim();
               String add=request.getParameter("address").trim();
               String state=request.getParameter("state").trim();
               //Then once again: Do validate and filter the form info using Pattern check class.
               if(pc.checkUserName(UserName, "Client Name", "Okonkwo or Ugwu") && pc.checkEmail(email1,"Client's Email","newuser@snosfotress.com")
               && pc.checkLocation(add, "Client's Location","5 Sule Abuka Crescent,Opebi,Ikeja") && pc.checkUserName(state,"State Field" , "Enugu or Anambra or Lagos") && !(request.getParameter("lga").trim().contains("select") ||request.getParameter("lga").trim().contains("choose") ||request.getParameter("lga").trim().contains("you")))
               {
                   //get the object of get and set class
                   UserInforGet ui=new   UserInforGet("","",UserName,add,request.getParameter("lga").trim(),state,email1,"","","","","");
                   //Update the info using the client's SNOS number.
                   rs.UpdateClientDetails(snos, ui);
                   //Retrieve the client's personal info immediatley to show him/her-and dispaly them- that
                   //the update has taken effect.
                   uifg=rs.getClientDetails1(snos, "clients_tab");
                   String[] value=new String[]{uifg.getClient(),snos,uifg.getFone(),uifg.getClientEmail(),uifg.getContact(),uifg.getLga(),uifg.getState()};
                   if(!request.getParameter("source").equals("mobile"))
                   {
                       //redirect and display the update result to the destop browser immediately.
                       session.setAttribute("val", value);
                       response.sendRedirect(request.getContextPath()+"/user_update.jsp");
                   }
                   else
                   {
                       //display the update result to the mobile browser or native app immediately.
                       String status="1";
                       //get an object of JSON get and set class called:JsonProperties.java
                       JsonProperties  jsp=new JsonProperties(snos,uifg.getClient(),uifg.getFone(),uifg.getClientEmail(),uifg.getContact(),uifg.getLga(),uifg.getState(),status);
                       //get a GSON object to convert the json property class to json string
                       //fit for sending.
                       Gson gson = new Gson();
                       gson=new GsonBuilder().create();
                       String  json_prop_detail= gson.toJson(jsp);
                       //sent the converted json string
                       System.out.println("The Json Output:"+json_prop_detail);
                       sendJSON(response, json_prop_detail);
                     }
               }
               //the pattern checker validation failed then.
               else
               {
                   //compose and send the appropriate message then.
                   if(request.getParameter("source").equals("mobile"))
                   {
                       msg="One of your fields has invalid inputs like numbers,or has too long/short letters<br>Thank you";
                       obj.put("status", "3");
                       obj.put("msg", msg);
                       sendJSON(response, obj.toJSONString());
                   }
                   else
                   {
                       session.setAttribute("errmsg","One of your fields has invalid inputs like numbers,or has too long/short letters.Also verify that you didn't enter special characters(eg:!,','',$,%) or any other wrong input(s) and Make sure  that you entered the correct gsm and email formats.<br>Thank you");
                       response.sendRedirect(request.getContextPath()+"/user_update.jsp");
                   }
               }
           }
           //Does the form action control panel link containing 'my profile' has been clicked . I mean does the user 
           //wants to view his/her registered personal information.
           else if(par.equals("my profile"))
           {
               String snos="";
               //get the SNOS number from either the mobile device source or desktop source
               if(request.getParameter("source").equals("mobile"))
               {
                   snos=request.getParameter("snos_type");
               }
               else
               {
                   snos=   session.getAttribute("snos_type").toString();
               }
               //retrieve all the client's personal information from the permanent database table called:clients_tab
               uifg=rs.getClientDetails1(snos, "clients_tab");
               String[] value=new String[]{uifg.getClient(),snos,uifg.getFone(),uifg.getClientEmail(),uifg.getContact(),uifg.getLga(),uifg.getState()};
               String status="1";
               if(!request.getParameter("source").equals("mobile"))
               {
                   //redirect and display the retrieved info to the destop browser immediately.
                   session.setAttribute("val", value);
                   response.sendRedirect(request.getContextPath()+"/user_update.jsp");
               }
               else
               {
                   //display the update result to the mobile browser or native app immediately.
                   //get an object of JSON get and set class called:JsonProperties.java
                   JsonProperties  jsp=new JsonProperties(snos,uifg.getClient(),uifg.getFone(),uifg.getClientEmail(),uifg.getContact(),uifg.getLga(),uifg.getState(),status);
                   Gson gson = new Gson();
                   gson=new GsonBuilder().create();
                   String  json_prop_detail= gson.toJson(jsp);
                   System.out.println("The Json Output:"+json_prop_detail);
                   sendJSON(response, json_prop_detail);
               }
                   
           }
           //Was the form action Button containing 'Update Device' clicked? . I mean does the user 
           //wants to update his/her registered Device/Object's information.
           else if(par.equals("Update Device"))
           {
               String snos="";
               // variable 'counter_id' is a counter-uses by this servlet-as an index for the array of devices retrieved from DB
               //I mean to access each device info in the String array-value-, you must use did to do so. And later increments or decrments depending
               //on weather you are accessing next and previous device info respectively in the array.
               int counter_id=0;
               // variable 'table_id' contains the particular device id in its database table row .
               if(request.getParameter("source").equals("mobile"))
               {
                   snos=request.getParameter("snos_type");
                   table_id= request.getParameter("uid");
                   counter_id= Integer.parseInt(request.getParameter("did"));
               }
               else
               {
                   snos=   session.getAttribute("snos_type").toString();
                   table_id= (String)session.getAttribute("uid");
                   counter_id= (Integer)session.getAttribute("did");
               }
               //get all the device info needed for the update
               String msg="Oops something went wrong";
               row=new Vector();
               String UserName=request.getParameter("nam").trim();
               String desc=request.getParameter("description").trim();
               String house_type=request.getParameter("house_type").trim();
               String loc=request.getParameter("Location").trim();
               String add=request.getParameter("address").trim();
               String state=request.getParameter("state").trim();
               String lga=request.getParameter("lga").trim();
               //Do the user validation of form fields using pattern checker class.
               if(pc.checkUserName(UserName, "Device Field", "Mercedez Benz")
               && pc.checkLocation(desc, "Description Field", "Cool car") && pc.checkUserName(house_type, "Housing Type Field", "Bungallow or Duplex") && pc.checkLocation(loc,"Device Location","Inside Bedroom or Back of the House")
               && pc.checkLocation(add, "Device's Address","5 Sule Abuka Crescent,Opebi,Ikeja") && pc.checkUserName(state,"State Field" , "Enugu or Anambra or Lagos") && !(lga.contains("select") ||lga.contains("choose") ||lga.contains("you")))
               {
                   //get the object of the class.
                   uifg=new   UserInforGet("",UserName,desc,house_type,loc,add,lga,state);
                   //carry out the needed update  using the primary key -uid- of the permanent device table
                   //and the SNOS number.
                   rs.UpdateDeviceDetails(snos, uifg, table_id);
                   //Retrieve the client's Device info immediatley to show him/her-and dispaly them- that
                   //the update has taken effect. And then assign all to a Vector row.
                    row=rs.getClientDeviceDetails(snos, "clients_device_tab");
                    //get  a particular row -and return it as an array of value-passing the counter 'did' as an index to the row. 
                    String value[]=(String[])row.get(counter_id);
                    if(!request.getParameter("source").equals("mobile"))
                    {
                        //if the request id from the the desktop browser, store the counter/index as a session property called 'did'
                        //and store the array of value as 'val' to be access at the device_update.jsp 
                        session.setAttribute("did", counter_id);
                        session.setAttribute("val", value);
                        response.sendRedirect(request.getContextPath()+"/device_update.jsp");
                    }
                    else
                    {
                        //store the 'counter_id' and 'value' as  json property class to be sent to mobile device
                        //get an object of JSON get and set class called:JsonProperties.java
                        JsonProperties  jsp=new JsonProperties(value,counter_id,table_id,row.size());
                        Gson gson = new Gson();
                        //get gson object and use it to create a json string to be sent.
                        gson=new GsonBuilder().create();
                        String  json_prop_detail= gson.toJson(jsp);
                        System.out.println("The Json Output:"+json_prop_detail);
                        sendJSON(response, json_prop_detail);
                    }

               }
               //the validation with pattern check failed
               else
               {
                   //send the appropriate error message
                   if(request.getParameter("source").equals("mobile"))
                   {
                       msg="One of your fields has invalid inputs like numbers,or has too long/short letters<br>Thank you";
                       obj.put("status", "3");
                       obj.put("msg", msg);
                       sendJSON(response, obj.toJSONString());
                   }
                   else
                   {
                       session.setAttribute("errmsg","One of your fields has invalid inputs like numbers,or has too long letters.Also verify that you didn't enter special characters(eg:!,','',$,%) or any other wrong input(s) and Make sure  that you entered the correct gsm and email formats.<br>Thank you");
                       response.sendRedirect(request.getContextPath()+"/device_update.jsp");
                   }
               }
           }
           //Was the form action Link/Button containing 'device_profile' or 'Next >>' or '<< Previous' clicked? . I mean does the user 
           //wants to view his/her registered Device/Object's information.Or view the next or previous device info?
           else if(par.equals("device_profile") || (par.equalsIgnoreCase("Next >>") && request.getParameter("id").equalsIgnoreCase("reg22")) ||(par.equalsIgnoreCase("<< Previous") && request.getParameter("id").equalsIgnoreCase("reg22")))
           {
               // variable 'counter_id' is a counter-uses by this servlet-as an index for the array of devices retrieved from DB
               //I mean to access each device info in the String array-value-, you must use did to do so. And later increments or decrments depending
               //on weather you are accessing next and previous device info respectively in the array.
               table_id="";
               // variable 'table_id' contains the particular device id  in its database table row .
               int counter_id=0;
               String snos="";
               //get the snos number from either the mobile device request or desktop browser session
               if(request.getParameter("source").equals("mobile"))
               {
                   snos=request.getParameter("snos_type");
               }
               else
               {
                   snos=   session.getAttribute("snos_type").toString();
               }
               //instantiate the row
               row=new Vector();
               //retrieve all the registered device/object data
               row=rs.getClientDeviceDetails(snos, "clients_device_tab");
               //store the row in a session property called 'rs'.
               session.setAttribute("rs", row.size());
               //if the action parameter is 'device_profile'.I mean if the user clicked to view his device profile
               if(par.equalsIgnoreCase("device_profile"))
               {
                   //initialize the counter 'counter_id' to be zero.
                   counter_id=0;
               }
               //Did the client click "<<Previous" Button . I mean does he/she wants to see his/her previous device? 
               else if((par.equalsIgnoreCase("<< Previous") && request.getParameter("id").equalsIgnoreCase("reg22")))
               {
                   if(!request.getParameter("source").equals("mobile"))
                   {
                       //get the row counter or and index from the desktop browser session
                       counter_id= (Integer)session.getAttribute("did");
                   }
                   else
                   {
                       //get the row counter or and index from the mobile browser or mobile native app.
                       counter_id=Integer.parseInt(request.getParameter("did"));
                   }
                   //since the client wants to view previous device data, you must decrement the row counter/index
                   counter_id--;
               }
               //Did the click "Next >>" Button . I mean did he/she want to see his/her previous device data? 
               else if((par.equalsIgnoreCase("Next >>") && request.getParameter("id").equalsIgnoreCase("reg22")))
               {
                   if(!request.getParameter("source").equals("mobile"))
                   {
                       //get the row counter or and index from the desktop browser session
                       counter_id= (Integer)session.getAttribute("did");
                   }
                   else
                   {
                       counter_id=Integer.parseInt(request.getParameter("did"));
                   }
                   //since the client wants to view next device data, you must increment the row counter/index
                   counter_id++;
               }
               //then retrieve a particular array of device data from the Vector row using the counter/index 'did' to access it.
               String value[]=(String[])row.get(counter_id);
               //get the row primary key id so that you can use it to update device data table in the database.
               table_id=value[7];
               if(!request.getParameter("source").equals("mobile"))
               {
                   //store the array value in a session property called 'val' and store other information too like did and uid
                   session.setAttribute("did", counter_id);
                   session.setAttribute("uid", table_id);
                   session.setAttribute("val", value);
                   response.sendRedirect(request.getContextPath()+"/device_update.jsp");

               }
               else
               {
                   JsonProperties  jsp;
                   //get an object of json property class to download just one device/object profile
                   jsp=new JsonProperties(value,counter_id,table_id,row.size());
                   // get an object of Gson  classs
                   Gson gson = new Gson();
                   // use the Gson object to create a builder
                   gson=new GsonBuilder().create();
                   //use the builder to get a json string to be sent to a mobile device
                   String  json_prop_detail= gson.toJson(jsp);
                   System.out.println("The Json Output for DEVICE OBJECT IS:"+json_prop_detail);
                   sendJSON(response, json_prop_detail);
               }

           }
           //Did the person click an "Update Contact" button to Update his Contacts
           else if(par.equals("Update Contact"))
           {
               String msg="Oops something went wrong";
               // variable 'did' is a counter-uses by this servlet-as an index for the array of devices retrieved from DB
               //I mean to access each device info in the String array-value-, you must use did to do so. And later increments or decrments depending
               //on weather you are accessing next and previous device info respectively in the array.
               int did=0;
               // variable 'uid' contains the particular device id in the in its database table row .
               String snos="";
               if(request.getParameter("source").equals("mobile"))
               {
                   //get the row counter/index from the mobile browser or mobile native app.
                   snos=request.getParameter("snos_type");
                   table_id= request.getParameter("uid");
                   //convert it to an integer.
                   did= Integer.parseInt(request.getParameter("did"));
               }
               else
               {
                   //get the row counter or and index from the desktop browser session
                   snos=   session.getAttribute("snos_type").toString();
                   table_id= (String)session.getAttribute("uid");
                   did= (Integer)session.getAttribute("did");
               }
               row=new Vector();
               //get all the contacts data to update from the contact form
               String UserName=request.getParameter("nam").trim();
               String fone=request.getParameter("fone").trim();
               String relation_pos=request.getParameter("relation_pos").trim();
               String add=request.getParameter("address").trim();
               String mail1=request.getParameter("email1").trim();
               //carryout the usual validation with the Pattern Check class.
               if( pc.checkUserName(request.getParameter("nam").trim(), "Name Field", "Ugwu Mike John")
               && pc.checkLocation(request.getParameter("relation_pos"), "Relations's Position Field", "DPO,Younger Brother") && pc.checkGsm(request.getParameter("fone").trim(), "Gsm", "2348xxxxxxxxx or 08xxxxxxxxx")
               && pc.checkLocation(request.getParameter("address"), "Contacts's Address","5 Sule Abuka Crescent,Opebi,Ikeja") && pc.checkEmail(request.getParameter("email1").trim(),"Client's Email","newuser@snosfotress.com"))
               {
                   uifg=new   UserInforGet("",UserName,relation_pos,add,mail1,fone);
                   //update the contact's permanent table with the data after getting the get and set class object:uig
                   rs.UpdateClientsContactsDetails(snos, uifg, table_id);
                   //retrieve the updated info immediately to show that the update has taken effect.
                   row=rs.getClientContactsDetails(snos, "clients_contacts_tab");
                   String value[]=(String[])row.get(did);
                   table_id=value[5];
                   if(!request.getParameter("source").equals("mobile"))
                   {
                       //store the array value in a session property called 'val' and store other information too like did and uid
                       session.setAttribute("did", did);
                       session.setAttribute("uid", table_id);
                       session.setAttribute("val", value);
                       //navigate to contact's jsp page and display the result of the update 
                       response.sendRedirect(request.getContextPath()+"/clients_contacts_update.jsp");

                   }
                   else
                   {
                       // prepare to send json to mobile device or mobile browser as has been done before.
                       JsonProperties  jsp=new JsonProperties(value[0],value[1],value[2],value[3],value[4],value[5],did,row.size());
                       Gson gson = new Gson();
                       gson=new GsonBuilder().create();
                       //get the json string to be sent to the mobile device.
                       String  json_prop_detail= gson.toJson(jsp);
                       System.out.println("The Json Output:"+json_prop_detail);
                       sendJSON(response, json_prop_detail);
                   }
               }
               //then the validation with pattern check class failed
               else
               {
                   //compare and send the appropriate messages then.
                   if(request.getParameter("source").equals("mobile"))
                   {
                       msg="One of your fields has invalid inputs like numbers,or has too long/short letters<br>Thank you";
                       obj.put("status", "3");
                       obj.put("msg", msg);
                       sendJSON(response, obj.toJSONString());
                   }
                   else
                   {
                       session.setAttribute("errmsg","One of your fields has invalid inputs like numbers,or has too long letters.Also verify that you didn't enter special characters(eg:!,','',$,%) or any other wrong input(s) and Make sure  that you entered the correct gsm and email formats.<br>Thank you");
                       response.sendRedirect(request.getContextPath()+"/clients_contacts_update.jsp");
                   }
               }
               
           }
           else if(par.equals("contacts_profile") || (par.equalsIgnoreCase("Next >>") && request.getParameter("id").equalsIgnoreCase("reg33")) ||(par.equalsIgnoreCase("<< Previous") && request.getParameter("id").equalsIgnoreCase("reg33")))
           {
               // variable 'did' is a counter-uses by this servlet-as an index for the array of devices retrieved from DB
               //I mean to access each device info in the String array-value-, you must use did to do so. And later increments or decrments depending
               //on weather you are accessing next and previous device info respectively in the array.
               // variable 'uid' contains the particular device id in the in its database table row .
               int did=0;
               table_id="";
               row=new Vector();
               String snos="";
               //get the snos number from either the mobile device request or desktop browser session
               if(request.getParameter("source").equals("mobile"))
               {
                   snos=request.getParameter("snos_type");
               }
               else
               {
                   snos=   session.getAttribute("snos_type").toString();
               }
               //retrieve all the registered clients' contacts data from database.
               row=rs.getClientContactsDetails(snos, "clients_contacts_tab");
               //if the action parameter is 'contacts_profile'.I mean did the user click to view his contacts profile
               if(par.equalsIgnoreCase("contacts_profile"))
               {
                   //initialize the counter 'did' to be zero.
                   did=0;
               }
               //Did the client click "<<Previous" Button . I mean does he/she wants to see his/her previous contacts? 
               else if((par.equalsIgnoreCase("<< Previous") && request.getParameter("id").equalsIgnoreCase("reg33")))
               {
                   if(!request.getParameter("source").equals("mobile"))
                   {
                       //get the row counter or and index from the desktop browser session
                       did= (Integer)session.getAttribute("did");
                   }
                   else
                   {
                       //get the row counter or and index from the mobile browser or mobile native app.
                       did=Integer.parseInt(request.getParameter("did"));
                   }
                   //since the client wants to view previous contacts data, you must decrement the row counter/index
                   did--;
               }
               //Did the client click "Next >>" Button . I mean did he/she want to see his/her previous contacts data? 
               else if((par.equalsIgnoreCase("Next >>") && request.getParameter("id").equalsIgnoreCase("reg33")))
               {
                   if(!request.getParameter("source").equals("mobile"))
                   {
                       //get the row counter or and index from the desktop browser session
                       did= (Integer)session.getAttribute("did");
                   }
                   else
                   {
                       //get the row counter or and index from the mobile device
                       did=Integer.parseInt(request.getParameter("did"));
                   }
                   //since the client wants to view next device data, you must increment the row counter/index
                   did++;
               }
               //then retrieve a particular array of contact data from the Vector row using the counter/index 'did' to access it
               String value[]=(String[])row.get(did);
               //get the row primary key id so that you can use it to update device data table in the database.
               table_id=value[5];
               if(!request.getParameter("source").equals("mobile"))
               {
                   //store the array value in a session property called 'val', the row size as 'rs' and store other information too like did and uid.
                   session.setAttribute("rs", row.size());
                   session.setAttribute("did", did);
                   session.setAttribute("uid", table_id);
                   session.setAttribute("val", value);
                   //navigate to contact's jsp page and display the result of the update 
                   response.sendRedirect(request.getContextPath()+"/clients_contacts_update.jsp");
               }
               else
               {
                   // get an object of json property classs
                   JsonProperties  jsp=new JsonProperties(value[0],value[1],value[2],value[3],value[4],value[5],did,row.size());
                    // get an object of Gson  classs
                   Gson gson = new Gson();
                   // use the Gson object to create a builder
                   gson=new GsonBuilder().create();
                   //use the builder to get a json string to be sent to a mobile device
                   String  json_prop_detail= gson.toJson(jsp);
                   System.out.println("The Json Output:"+json_prop_detail);
                   sendJSON(response, json_prop_detail);
               }
           }
          //Did the client click the "Login" button at home page(index.jsp). I mean does he/she want to Login to view alerts?
          else if(par.equals("Login"))
          {
              //get the username and password as entered by the user
              String mail1=request.getParameter("userid").trim();
              String pass=request.getParameter("password").trim();
              //String captcha = (String)session.getAttribute("captcha");
              // String code = (String)request.getParameter("code").trim();
              //vallidate both the user's name and password with the Pattern Check class.
              if(pc.checkEmail(mail1,"Client's Email","newuser@snosfotress.com")&& pc.checkArbitraryField(pass,"PassWorld Field","af1aba"))
              {
                  //get the the client's SNOS nunber
                  String snos=  rs.RetrieveItem("clients_temp_tab", "email1", mail1, "snos_type");
                  //store it in a session as property "snos_type"
                  session.setAttribute("snos_type", snos);
                  snos=  session.getAttribute("snos_type").toString();
                  // retrieve the status of the user's registration from the temporal registration table using his SNOS number to search.
                  String status=rs.RetrieveItem("clients_temp_tab","snos_type",snos,"status");
                  // retrieve the status of the user's registration from the permamnet registration table to know whether his registration has been
                  //"ACTIVATED" or "UNACTIVTED"
                  String finalstatus=rs.RetrieveItem("clients_tab","snos_type",snos,"status");
                  if(finalstatus.equals(""))
                  {
                      //then the user's data cannot be found at all at the permanent table
                      //and thus he either has not started registering at all or has but has not completed it.In any case he is in
                      //an "UNACTIVATED" state untill he registers and is comfirmed before he goes into "ACTIVATED" state in the permanent table.
                      finalstatus="UNACTIVATED";
                  }
                  else
                  {
                      //then atleast he has started registering.
                  
                  }
                  //if he/she has started registering but his registration has not been  confirmed
                  if(rs.CheckClientsLoginParameters(mail1, pass,"clients_temp_tab") && !finalstatus.equalsIgnoreCase("ACTIVATED"))
                  {
                      //get these his information from temporal table and store them in a session.
                      String fnam=rs.RetrieveItem("clients_temp_tab", "email1", mail1, "name");
                      String link1=rs.RetrieveItem("clients_temp_tab", "email1", mail1, "link1");
                      request.getSession().setAttribute("link1", link1);
                      request.getSession().setAttribute("email1", mail1);
                      request.getSession().setAttribute("fname", fnam);
                      //Has he/she done the first stage of registration?
                      if(status.equalsIgnoreCase("1c"))
                      {
                          //then display message-to either mobile device or destop browser- telling him/her to continue from the 2nd stage.
                          if(request.getParameter("source").equals("mobile"))
                          {
                              //status 3a is telling the mobile device to display the 2nd stage form page 
                              obj.put("status", "3a");
                              obj.put("snos_type",  snos);
                              obj.put("msg", "Please Kindly do  Complete Your Registration.You stopped at the first stage, you will now continue from Device Registration<br>Thank you.");
                              sendJSON(response, obj.toJSONString());
                          }
                          else
                          {
                              session.setAttribute("status","Please Kindly do  Complete Your Registration.You stopped at the first stage, you will now continue from Device Registration.<br>Thank you.");
                              //take him/her to the 2nd stage form page.
                              response.sendRedirect(request.getContextPath()+"/device_reg.jsp");
                          }
                      }
                      //Has he/she done the 2nd stage of registration?
                      else if(status.equalsIgnoreCase("2c"))
                      {
                          //then display message-to either mobile device or destop browser- telling him/her to continue from the 3rd stage.
                          if(request.getParameter("source").equals("mobile"))
                          {
                              //status 3b is telling the mobile device to display the 3rd stage form page 
                              obj.put("status", "3b");
                              obj.put("snos_type",  snos);
                              obj.put("msg", "Please Kindly do  Complete Your Registration.You stopped at the second stage, you will now continue from Client's Contacts Registration.<br>Thank you.");
                              sendJSON(response, obj.toJSONString());
                          }
                          else
                          {
                              //build dynamically the 3rd stage form and store it in the browser session attribute called 'info'
                              //to display when the user is taken to 3rd stage page.
                              session.setAttribute("status","Please Kindly do  Complete Your Registration.You stopped at the second stage, you will now continue from Client's Contacts Registration.<br>Thank you.");
                              //construct the form for the 3rd stage of registration-dynamically-and then store it in the session to print it in the jsp file of the 3rd stage.
                              String form="<form onsubmit='return FormValidation3();'   method='POST'  action='servlets/RequestProcessing'  name='form3' >";
                              form+=" <tr><td align='center' width = '100%'  height= '20' > <font face= 'Times New Roman' size = '3' color='blue'>Name:</font><span style='color:#FF0000'>*</span><input type='text' size = '30' name = 'nam' maxlength='50' placeholder='Name'  style='margin-right:30px;'></td></tr>";
                              form+="<tr><td  align='center' width = '100%'  height= '20' >";
                              form+="<font face= 'Times New Roman' size = '3' color='blue'>Relationship/Position:</font><span style='color:#FF0000'>*</span>";
                              form+="<br /><input type='text' size = '30' name = 'relation_pos' maxlength='50'></td><td align='center' width = '100%'  height= '20' ><font face= 'Times New Roman' size = '3' color='blue'>Mobile Number:</font><span style='color:#FF0000'>*</span>";
                              form+="<input type='text' size = '30' onchange='return checkEmail_GsmBeforeRegistrationAddress_Form3(this.value,'gsm');' name = 'fone' maxlength='50' style='margin-right:30px;' >";
                              form+="</td></tr>";
                              form+="<tr><td  align='center' width = '100%'  height= '20' >";
                              form+="<font face= 'Times New Roman' size = '3' color='blue'>Address:</font><span style='color:#FF0000'>*</span>";
                              form+="<br /><textarea cols='20' rows='4' name='address'> </textarea></td><td align='center' width = '100%'  height= '20' ><font face= 'Times New Roman' size = '3' color='blue'>E-mail:</font><span style='color:#FF0000'>*</span>";
                              form+="<input type='text' size = '30' onchange='return checkEmail_GsmBeforeRegistrationAddress_Form3(this.value,'myemail');' name = 'email1' maxlength='50' style='margin-right:30px;' >";
                              form+="</td></tr><tr><td width='400' height='10'  align='center' colspan='2'> <font face='Times New Roman' size='5' color='red'>Do you wish to Register additional contacts-It is highly recommended that you do?:" +"<span style='color:#FF0000'>*</span></font></td></tr>"+

                              "<tr><td><font face= 'Times New Roman' size = '6' color='blue'>NO</font><input type='radio' checked   name='extra_contact' value='no'></td><td  align='center' width = '100%'  height= '20' >" +
                              "<font face= 'Times New Roman' size = '6' color='blue'>Yes</font><input type='radio'  name='extra_contact' value='yes'></td>" +
                              "</td></tr><tr><td height='5'>&nbsp;&nbsp;</td></tr><tr><td align='center' width = '100%'  height= '20' colspan='2' >";
                              form+="<input type='submit' value='Submitt'  name='action' style='border: 1px solid #698EEF; padding-left: 4; padding-right: 4; padding-top: 1; padding-bottom: 1; color:#FFF;background-color:#009999'>";
                              form+="&nbsp; <input type='reset' value='Reset' name='can' style='border: 1px solid #698EEF; padding-left: 4; padding-right: 4; padding-top: 1; padding-bottom: 1; color:#FFF;background-color:#009999'>";
                              form+="<input type='hidden' value='reg3' name='id' style='border: 1px solid #698EEF; padding-left: 4; padding-right: 4; padding-top: 1; padding-bottom: 1; color:#FFF;background-color:#009999'>";
                              form+="<input type='hidden' value='desktop' name='source' >";
                              form+="</td></tr> </form><tr><td align='center' width = '100%'  height= '20' colspan='2' ><font color='#FF0000'>  </font></td></tr></form>";
                              request.getSession().setAttribute("info", form);
                              request.getSession().setAttribute("msg", "");
                              //take him/her to the 3rd stage form page.
                              response.sendRedirect(request.getContextPath()+"/clients_contacts_reg.jsp");
                          }
                      }
                      //Has he/she done the 3rd stage of registration?
                      else if(status.equalsIgnoreCase("3c"))
                      {
                          //then get the needed personal data to send email again-since he has not comfirmed the initial email sent to 
                          //him or the email was not sent successfully.
                          String msg="";
                          String host="";
                          String fname=(String)session.getAttribute("fname");
                          String mylink=(String)session.getAttribute("link1");
                          if(request.getParameter("source").equals("mobile"))
                          {
                              //get the mobile host from web.xml as initialized by AppListener class at the start of this application context.
                              host=System.getProperty("mobile_host");
                          }
                          else
                          {
                              //else get the host dynamically.
                              host=request.getHeader("host")+request.getContextPath();
                          }
                          //then complete the email message to be sent to the client.
                          String emeailMsg="Hi " +fname+
                          ", thank you for registering at \n"+"http://"+host+"\n" +
                          "Please click on the link below to COMFIRM YOUR IDENTITY and thus complete your registration.\n"+
                          "Click on:\n "+mylink+
                          "  "+"\n\nPLEASE IF YOU DID NOT REGISTER FOR SNOS SERVICES,DO NOT BOTHER TO CLICK THE ABOVE LINK,\n SINCE SOMEBODY MIGHT HAVE BEEN PLAYING WITH YOUR EMAIL ADDRESS \n JUST SIMPLY IGNORE THE MESSAGE.\n\nThank You.\n\n\nSNOS Team.\n www.snosfortress.com";
                          String sender = System.getProperty("siteadmin_email");
                          String admin_emailpassword = System.getProperty("admin_emailpassword");
                          //have these parameters been tampered with
                          if(sender.equals("")||admin_emailpassword.equals(""))
                          {
                              //esnd the appropriate error message since they are empty
                              if(request.getParameter("source").equals("mobile"))
                              {
                                  obj.put("status", "3");
                                  obj.put("snos_type",  snos);
                                  obj.put("msg", "Can't send email:Internal server error.<br>Please contact the administrator");
                                  sendJSON(response, obj.toJSONString());
                              }
                              else
                              {
                                  //send error message to the administrator and display friendly error page to the client.
                                  session.setAttribute("admin_error","Email  Parameters have been Tampered with");
                                  response.sendRedirect(request.getContextPath()+"/EmailErrorPage.jsp");
                              }
                          }
                          //the parameters are intact, so attempt to send the email message.
                         else
                         {
                             if(new SnosSendEmail().SNOSemailSendToOne(sender, admin_emailpassword,"SNOS REGISTRATION CONFIRMATION",emeailMsg ,session.getAttribute("email1").toString()))
                             {
                                 //compose a success html message to display in the 3rd stage page
                                 msg="<tr><td><font face='Times New Roman' size='5' color='blue'><CENTER>Congratulations!!!</CENTER> <br>Your Last Stage of registration is successfull,An email has been sent to your inbox.Please click the link sent to your inbox to <strong>confirm</strong> and complete your registration.\n\nThank you. <br><br><br>Wants to go back to home page?<a href='index.jsp'><font face='Times New Roman' size='5' color='blue'>Click Me</a></font></td></tr>";
                             }
                             //compose the appropriate error message then
                             else
                             {
                                 //Is this request coming from Mobile Native/Installed App?
                                 if(request.getParameter("mnative")!=null)
                                 {
                                     //compose the appropriate error message then 
                                     msg="Error Sending E-Mail!!!\nOops something has gone pearshaped!Your Last Stage of registration was not successfull,An e-mail could not be sent to your inbox.Please go back to the home page and re-login to resend the e-mail  and complete your registration.\n\nThank you. ";
                                 }
                                 else
                                 {
                                      //compose the appropriate error message  for either the mobile web or standalone PC browser
                                     msg="<tr><td><font face='Times New Roman' size='5' color='blue'><CENTER>Error Sending E-Mail!!!</CENTER> <br>Oops something has gone pearshaped!Your Last Stage of registration was not successfull,An e-mail could not be sent to your inbox.Please go back to the home page and re-login to resend the e-mail  and complete your registration.\n\nThank you. <br><br><br>Wants to go back to home page?<a href='index.jsp'><font face='Times New Roman' size='5' color='blue'>Click Me</a></font></td></tr>";
                                 }
                             }
                         }
                         //prepare to send the composed message to the apprpriate destination 
                         if(request.getParameter("source").equals("mobile"))
                         {
                             //status 3c is telling the mobile device to display this message in the 3rd stage form page
                             obj.put("status", "3c");
                             obj.put("snos_type",  snos);
                             obj.put("msg", msg);
                             sendJSON(response, obj.toJSONString());
                         }
                         else
                         {
                             session.setAttribute("msg", msg);
                             session.setAttribute("status","Please Kindly do  Complete Your Registration.You stopped at the last stage, you will now finalize by checking and clicking the link forwarded to your email.\nThank you.");
                             request.getSession().removeAttribute("info");
                             response.sendRedirect(request.getContextPath()+"/clients_contacts_reg.jsp");
                         }
                      }
                       
                      //then do nothin since we have exhausted all the stages of registration
                      //It is most likely that this part/block may never execute 
                      //unless the app is hacked.
                      else
                      {
                          
                      }
                          
                 }
                 //Then check if the client has completed his/her registeration and is CONFIRMED,in this case we allow him/her to login
                 //and view his/her alerts(if there are any) and his/her other personal info.
                 else if((rs.CheckClientsLoginParameters(mail1, pass,"clients_tab"))&& (status.equalsIgnoreCase("3c")&& finalstatus.equalsIgnoreCase("ACTIVATED")))
                 {
                     
                     //retrieve from database and store also his SNOS number in a session to be refernced later.
                     String sid =rs.RetrieveItem("clients_tab", "email1", mail1, "snos_type");
                     //Is this request coming from mobile device?
                    if(request.getParameter("source").equals("mobile") && request.getParameter("mnative")!=null)
                    {
                        //check if the client's snos number is already configured for a particular device
                      if(rs.CheckIfItemExist("phone_info", "snos_type", sid))
                            {

                                //this applies only to NAtive Mobile App installed in a mobile device
                                obj.put("status", "3f");
                                obj.put("msg", "The Client device is already configured with this SNOS Number-Client Id");
                                sendJSON(response, obj.toJSONString());
                            }
                    }
                     
                     //get an instance of a Vector object called row.
                     row=new Vector();
                     //retrieve the client's name from the permanent table
                     String fname=rs.RetrieveItem("clients_tab", "email1", mail1, "name");
                     //store it in a session to be referenced later.
                     request.getSession().setAttribute("user",fname );
                     //store his email address too in a session to be refernced later.
                     request.getSession().setAttribute("e-mail", mail1);
                     //store his password too in a session to be refernced later.
                     request.getSession().setAttribute("pass",pass);
                     //stores his client id or snos number in the session-to be referenced later too.
                     request.getSession().setAttribute("sid",sid );
                     //then retrieve all his sms alerts and their dates-if there are any
                     row=rs.querysmsList(sid, 0, 10);
                     if(row.isEmpty())
                     {
                         //Is this request coming from mobile device?
                         if(request.getParameter("source").equals("mobile"))
                         {
                             //This has been treated downward where Is 'row==0' is tasted before sending to mobile devices
                         }
                         else
                         {
                             //set and store some of the needed parameters in the session object
                             //save it in a session
                             //get the latest retrieved id
                             session.setAttribute("last_Retrieved_Id",0);//make it zeor since there is nothing currently in the database table
                             //store it in a session.
                             request.getSession().setAttribute("row", row);
                             //intialize the starting minimum of his alerts e.g 1-10 of 30. where 1=starting minimum
                             request.getSession().setAttribute("min", 1);
                             //store the current smscount of his alerts in a session too. e.g 1-10 of 30 where 10=current sms count
                             request.getSession().setAttribute("smscount", Reconsoft.getSmsCurrentCount());
                             //store the total size of his alerts in a session too.e.g 1-10 of 30 where 30=total size of sms
                             session.setAttribute("smstotalcount", Reconsoft.getSmsTotalCount());
                             response.sendRedirect(request.getContextPath()+"/view_sms.jsp");
                             
                         }
                         
                     }
                     //Are there alerts in the database for this user?
                     else
                     {
                         //then alerts are found for this user
                         //save these initial data/parameters in a session
                         //get the latest retrieved id
                         session.setAttribute("last_Retrieved_Id",rs.getSmsLast_Id(snos));
                         //store it in a session.
                         request.getSession().setAttribute("row", row);
                         //intialize the starting minimum of his alerts e.g 1-10 of 30. where 1=starting minimum
                         request.getSession().setAttribute("min", 1);
                         int sms_total_count=rs.getSmsTotalCount(snos);
                         if(sms_total_count<=10)
                         {
                             //make the current sms count to be equal to total one
                             //store the current smscount of his alerts in a session too. e.g 1-10 of 30 where 10=current sms count
                             request.getSession().setAttribute("smscount", sms_total_count);
                         }
                         else
                         {
                             //then get the default sms current count
                             request.getSession().setAttribute("smscount",Reconsoft.getSmsCurrentCount() );
                         }
                         //store the total size of his alerts in a session too.e.g 1-10 of 30 where 30=total size of sms
                         session.setAttribute("smstotalcount", Reconsoft.getSmsTotalCount());
                     }
                     
                     //Is this request coming from mobile device?
                     if(request.getParameter("source").equals("mobile"))
                     {
                         //intialize variables that will be used to build and send json message.
                         String json_alert_detail="";
                         JsonProperties jsp=null;
                         //intialize date and alerts String arrays that will be used to build and send json message.
                         String date[]=new String[row.size()];
                         String alert[]=new String[row.size()];
                         Gson gson = new Gson();
                         gson=new GsonBuilder().create();
                          //json status '2' means empty rows. I mean that no arlerts were found.
                         status="";
                         jsp=new JsonProperties(sid,alert,date,status,0,0,0,fname);
                         json_alert_detail= gson.toJson(jsp);
                        
                            //Does he want to download the app for the first time?
                         if(request.getParameter("download_all_client_personal_device_contacts_data")!=null)
                         {
                             //Then download all the sms alerts and all the profiles: personal, device/object and contacts data.
                             //to save them in the client's phone.
                            uifg=rs.getClientDetails1(snos, "clients_tab");
                            String[] biodata=new String[]{uifg.getClient(),uifg.getFone(),uifg.getClientEmail(),uifg.getContact(),uifg.getLga(),uifg.getState()};
                            Vector deviceRow=new Vector();
                            //retrieve all the registered device/object data
                            deviceRow=rs.getClientDeviceDetails(snos, "clients_device_tab"); 
                            Vector contactRow=new Vector();
                            //retrieve all the registered clients' contacts data from database.
                            contactRow=rs.getClientContactsDetails(snos, "clients_contacts_tab");
                            row=rs.getAllsmsList(snos);
                            alert=new String[row.size()];
                            date=new String[row.size()];
                            //loop through the alerts and assign the dates and alerts appropriately
                            for(int i=0;i<row.size();i++)
                            {
                                String []data=(String[])row.get(i);
                                alert[i]=data[1];
                                date[i]=data[2];
                            }
                            //Is the mobile device id for push messages sent?
                            if(request.getParameter("device_id")!=null)
                            {
                                String device_id=request.getParameter("device_id");
                                rs.AddPushDeviceId(snos, device_id);
                            }

                             //jsp=new JsonProperties(sid,alert,date,status,1,Reconsoft.getSmsCurrentCount(),Reconsoft.getSmsTotalCount(),fname)
                             jsp= new JsonProperties(sid,alert,date,1,Reconsoft.getSmsCurrentCount(),Reconsoft.getSmsTotalCount(),fname,biodata,deviceRow,contactRow,deviceRow.size(),contactRow.size());
                         }
                         else
                         {
                             //then sms alerts are found.
                             status="1";
                             alert=new String[row.size()];
                             date=new String[row.size()];
                             //loop through the alerts and assign the dates and alerts appropriately
                             for(int i=0;i<row.size();i++)
                             {
                                 String []data=(String[])row.get(i);
                                 alert[i]=data[1];
                                 date[i]=data[2];
                             }
                        if(row.size()==0)
                         {
                             status="2";
                         }
                         else
                         {
                             status="1";
                         }
                         //get an object the json property class
                         jsp=new JsonProperties(sid,alert,date,status,1,Reconsoft.getSmsCurrentCount(),Reconsoft.getSmsTotalCount(),fname);
                         }
                         
                         json_alert_detail= gson.toJson(jsp);
                         System.out.print("The gson alert detail obj:"+json_alert_detail);
                         sendJSON(response,json_alert_detail);
                     }
                     //then dispaly the the alerts to destop browser view_sms.jsp page
                     //by accessing the row stored in a session-as property 'row'- and other stored properties
                    else
                     {
                         response.sendRedirect(request.getContextPath()+"/view_sms.jsp");
                     }
                        
                 }
                 //then since we can't find this client info either in temporal or permanet client registration table
                 //it must then be that he is unknown. I mean he has not registered with this app at all since we can't locate
                 //his login detail anywhere in our database.
                 else
                 {
                     //is this request coming primarily from native mobile app installed on a mobile device.
                     if(request.getParameter("source").equals("mobile") && request.getParameter("mnative")!=null)
                     {
                         //compose and send the appropriate error message
                         obj.put("status", "3");
                         obj.put("msg", "The Client is NOT Registered");
                         sendJSON(response, obj.toJSONString());
                     }
                     //is this request coming primarily from native mobile web browser on a mobile device.
                     else if (request.getParameter("source").equals("mobile") && request.getParameter("mnative")==null)
                     {
                          
                          //compose and send the appropriate error message
                          obj.put("status", "3");
                          obj.put("msg", "Uknown Username or/and Password");
                          sendJSON(response, obj.toJSONString());
                     }
                     //Then it must be coming from PC desktop web browser
                     else
                     {
                         session.setAttribute("errmsg","Uknown Username or/and Password");
                         response.sendRedirect(request.getContextPath()+"/index.jsp");
                     }
                 }
              }
              //then the login parameters's sever side validation failed
              else
              {
                  //compose and send the appropriate error messages
                  if(request.getParameter("source").equals("mobile"))
                  {
                      obj.put("status", "3");
                      obj.put("msg", "Invalid input, please also check for special characters((eg:!,','',$,%))");
                      sendJSON(response, obj.toJSONString());
                  }
                  else
                  {
                      session.setAttribute("errmsg","One of your fields has invalid inputs like numbers,or has too long letters.Also verify that you didn't enter special characters(eg:!,','',$,%) or any other wrong input(s) and Make sure  that you entered the correct email formats.<br>Thank you");
                      response.sendRedirect(request.getContextPath()+"/index.jsp");
                  }
              }
          }
          //Does the user want to view or refresh SMS Inbox or even SEARCH his SMS Inbox when already logged in.
          else if(par.equals("sms"))
          {
              row=new Vector();
              if(session.getAttribute("inbox_type")!=null && !session.getAttribute("inbox_type").equals("")|| session.getAttribute("searchtf")!=null && !session.getAttribute("searchtf").equals(""))
              {
                  //remove these session attributes
                  session.removeAttribute("searchtf");
                  session.removeAttribute("inbox_type");
              }
              //retrieve to alerts from the user's account in the db.
              row=rs.querysmsList(session.getAttribute("sid").toString(),0, 10);
              //store these info(the alers, currentsms count and total size as well as starting minimun) in session properties
              session.setAttribute("row", row);
              session.setAttribute("smscount", Reconsoft.getSmsCurrentCount());
              session.setAttribute("smstotalcount", Reconsoft.getSmsTotalCount());
              session.setAttribute("min", 1);
              //go to sms jsp page and display them.
              response.sendRedirect(request.getContextPath()+"/view_sms.jsp");
          }
          //Does the user want to view next 10 alerts from his/her SMS Inbox when already logged in.
          else if(par.equals("nextsms"))
          {
              String snos="";
              row=new Vector();
              int currcount=0;
              //get or reference the client snos number
              if(request.getParameter("source").equals("mobile"))
              {
                  snos=request.getParameter("snos_type");
              }
              else
              {
                  snos=session.getAttribute("snos_type").toString();
              }
              //get his/her current sms count
              if(request.getParameter("source").equals("mobile"))
              {
                  currcount=Integer.parseInt(request.getParameter("smscount"));
              }
              else
              {
                  currcount =Integer.parseInt(session.getAttribute("smscount").toString());
              }
              //Did the client click "next" sms from search result
              if(session.getAttribute("inbox_type")!=null && session.getAttribute("inbox_type").equals("search"))
              {
                  //then search the database for the term stored in session property 'searchtf'
                  //and returns next 10 sms alerts.
                  //starting from his current sms count called 'currcount'
                  row=rs.searchsmsList(session.getAttribute("sid").toString(),session.getAttribute("searchtf").toString(), currcount, 10,true);
              }
              else
              {
                  //then return only the next 10 sms alerts starting from his current sms count called 'currcount'
                  row=rs.querysmsList(snos,currcount, 10);
              }
              if(request.getParameter("source").equals("mobile"))
              {
                  //prepare to build and send it as json to mobile device.
                  String sid=snos;
                  String status="";
                  String fname=rs.RetrieveItem("clients_tab", "snos_type", sid, "name");
                  String json_alert_detail="";
                  JsonProperties jsp=null;
                  String date[]=new String[row.size()];
                  String alert[]=new String[row.size()];
                  Gson gson = new Gson();
                  gson=new GsonBuilder().create();
                  jsp=new JsonProperties(sid,alert,date,status,0,0,0,fname);
                  json_alert_detail= gson.toJson(jsp);
                  //json status '2' means empty rows. I mean that no arlerts were found.
                  status="2";
                  if(row.size()==0)
                  {
                      status="2";
                  }
                  //then sms alerts are found.
                  else
                  {
                      
                      status="1";
                      alert=new String[row.size()];
                      date=new String[row.size()];
                      for(int i=0;i<row.size();i++)
                      {
                          String []data=(String[])row.get(i);
                          alert[i]=data[1];
                          date[i]=data[2];
                      }
                  }
                  //increment the current count to 10-in order to display the update since you have retrieved additional 10 alerts- and the minimum should now be currentcount plus 1
                  //pass the needed info since the difference between minimum and current sms count-at any point in time- should be 9.
                  //to json property constructor to get a new object
                  jsp=new JsonProperties(sid,alert,date,status,currcount+1,currcount+10,Reconsoft.getSmsTotalCount(),fname);
                  json_alert_detail= gson.toJson(jsp);
                  System.out.print("The gson alert detail obj:"+json_alert_detail);
                  sendJSON(response,json_alert_detail);
              }
              else
              {
                  //then prepare and display everything in desktop browser sms page.
                  session.setAttribute("row", row);
                  session.setAttribute("smscount", currcount+10);
                  session.setAttribute("smstotalcount", Reconsoft.getSmsTotalCount());
                  session.setAttribute("min", currcount+1);
                  response.sendRedirect(request.getContextPath()+"/view_sms.jsp");
              }
          }
          //Does the user want to view previous 10 alerts from his/her SMS Inbox when already logged in.
          else if(par.equals("prevsms"))
          {
              String snos="";
              row=new Vector();
              int currcount=0;
              //get or reference the client snos number
              if(request.getParameter("source").equals("mobile"))
              {
                  snos=request.getParameter("snos_type");
              }
              else
              {
                  snos=session.getAttribute("snos_type").toString();
              }
              //get his/her current sms count
             if(request.getParameter("source").equals("mobile"))
             {
                 currcount=Integer.parseInt(request.getParameter("smscount"));
             }
             else
             {
                 currcount =Integer.parseInt(session.getAttribute("smscount").toString());
             }
             //Did the client click "next" sms from search result
             if(session.getAttribute("inbox_type")!=null && session.getAttribute("inbox_type").equals("search"))
             {
                 //then search the database for the term stored in session property 'searchtf'
                 //and returns previous 10 sms alerts.
                 //starting from his current sms count called 'currcount'
                 row=rs.searchsmsList(session.getAttribute("sid").toString(),session.getAttribute("searchtf").toString(), currcount-20, 10,true);
             }
             else
             {
                  //then return only the previous 10 sms alerts starting from his current sms count called 'currcount'
                  row=rs.querysmsList(snos,currcount-20, 10);
             }
             if(request.getParameter("source").equals("mobile"))
             {
                 //prepare to build and send it as json to mobile device.
                 String sid=snos;
                 String fname=rs.RetrieveItem("clients_tab", "snos_type", sid, "name");
                 String status="";
                 String json_alert_detail="";
                 JsonProperties jsp=null;
                 String date[]=new String[row.size()];
                 String alert[]=new String[row.size()];
                 Gson gson = new Gson();
                 gson=new GsonBuilder().create();
                 jsp=new JsonProperties(sid,alert,date,status,0,0,0,fname);
                 json_alert_detail= gson.toJson(jsp);
                 //json status '2' means empty rows. I mean that no arlerts were found.
                 status="2";
                 if(row.size()==0)
                 {
                     status="2";
                 }
                 //then sms alerts are found.
                 else
                 {
                     //prepare to build and send it as json string
                     status="1";
                     alert=new String[row.size()];
                     date=new String[row.size()];
                     for(int i=0;i<row.size();i++)
                     {
                         String []data=(String[])row.get(i);
                         alert[i]=data[1];
                         date[i]=data[2];
                        
                    }
                 }
                 //decrement the current count to 10-in order to display the update since you have retrieved additional 10 alerts- and the minimum should now be currentcount minus 19
                 //Then  pass to the constructor the needed info since the difference between minimum and current sms count-at any point in time- should be 9.
                 //to json property constructor to get a new object
                 jsp=new JsonProperties(sid,alert,date,status,currcount-19,currcount-10,Reconsoft.getSmsTotalCount(),fname);
                 json_alert_detail= gson.toJson(jsp);
                 System.out.print("The gson alert detail obj:"+json_alert_detail);
                 sendJSON(response,json_alert_detail);
             }
             else
             {
                 //then prepare and display everything in desktop browser sms page.
                 session.setAttribute("row", row);
                 session.setAttribute("smscount", currcount-10);
                 session.setAttribute("smstotalcount", Reconsoft.getSmsTotalCount());
                 session.setAttribute("min", currcount-19);
                 response.sendRedirect(request.getContextPath()+"/view_sms.jsp");
             }
          }
          //This allows a new client of the installed native mobile app to download all his alerts-and update them- and store them in his mobile phone
          //after a particular period of time
          else if(par.equals("Download Alerts") && request.getParameter("id").equalsIgnoreCase("alert_download"))
          {
              String snos="";
              row=new Vector();
              //get the client SNOS number
              snos=request.getParameter("snos_type");
              //Has he downloaded all his alerts before but now wants to update his locally stored alerts
              if(request.getParameter("alertupdate")!=null)
              {
                  //get the last time update was carried
                  String time= request.getParameter("lastUpdate_Time");
                  //search the database and retrieve any available updates since the last 'time'
                  row=rs.getAllsmsListAfterAParticularPeriod(snos, time);
              }
              else
              {
                  //then he has not downladed any alerts to his phone-this is his first time.
                  row=rs.getAllsmsList(snos);
              }
              //prepare and sent it as json string
              String sid=snos;
              //get the name of the client
              String fname=rs.RetrieveItem("clients_tab", "snos_type", sid, "name");
              String status="";
              String json_alert_detail="";
              JsonProperties jsp=null;
              String date[]=new String[row.size()];
              String alert[]=new String[row.size()];
              Gson gson = new Gson();
              gson=new GsonBuilder().create();
              jsp=new JsonProperties(sid,alert,date,status,0,0,0,fname);
              json_alert_detail= gson.toJson(jsp);
              status="2";
              //did it return empty row
              if(row.size()==0)
              {
                  status="2";
              }
              else
              {
                  status="1";
                  alert=new String[row.size()];
                  date=new String[row.size()];
                  for(int i=0;i<row.size();i++)
                  {
                      String []data=(String[])row.get(i);
                      alert[i]=data[1];
                      date[i]=data[2];
                  }
              }
              jsp=new JsonProperties(sid,alert,date,status,0,0,Reconsoft.getSmsTotalCount(),fname);
              json_alert_detail= gson.toJson(jsp);
              System.out.print("The gson alert detail obj:"+json_alert_detail);
              sendJSON(response,json_alert_detail);

          }
          //Did the web app make an asynchronous ajax request-During 1st stage of registration- via javascript enabled browser
          //to determine whether somebody has registered with a particular email address or not?
          else if(par.equals("ajax"))
          {
              String name1="";
              //Did the request come with the client's temporal table name called:clients_temp_tab
              if(request.getParameter("table")!=null && request.getParameter("table").equals("clients_temp_tab"))
              {
                  //this holds either the phone or the user email to search for
                  String email_or_phone=""; 
                  //the field in the database table to search for
                  String field="";
                  if(request.getParameter("user")==null && request.getParameter("device_id")==null)
                  {
                      email_or_phone=request.getParameter("gsm").trim();
                      field="fone";
                      //search-from the db- for the person's name using the email address called 'user' passed as a request parameter
                      name1 =rs.RetrieveItem("clients_temp_tab", field, email_or_phone, "name");
                  }
                   if(request.getParameter("gsm")==null && request.getParameter("device_id")==null)
                  {
                      email_or_phone=request.getParameter("user").trim();
                      field="email1";
                      //search-from the db- for the person's name using the email address called 'user' passed as a request parameter
                      name1 =rs.RetrieveItem("clients_temp_tab", field, email_or_phone, "name");
                  }
                  if(request.getParameter("device_id")!=null)
                  {
                      String device_id=request.getParameter("device_id").trim();
                      //search-from the db- for the person's mobile phone device id using the email address called 'user' passed as a request parameter
                      name1 =rs.RetrieveItem("phone_info", "device_id", device_id, "device_id");
                  }
                  //did you succed in finding the name or not
                  if(name1==null || name1.equals(""))
                  {
                      //this means failure in finding the person's email address
                      name1="0";
                  }
                  else
                  {
                      //this means success in finding the person's email address
                      name1="1";
                  }
              }
              else
              {
                  //Then search for the person's name using the email address called 'user' in the permanent table name called:clients_tab
                  name1 =rs.RetrieveItem("clients_tab", "email1", request.getParameter("user").trim(), "name");
              }
              //prepare to send the result either as json or xml depending on the source of the request
              if(request.getParameter("source").equals("mobile"))
              {
                  obj.put("status", "1");
                  obj.put("name", name1);
                  sendJSON(response,obj.toJSONString());
              }
              else
              {
                  sendXML(response,name1);
              }
          }
          //Did the web app make an asynchronous ajax request via-During 1st and 2nd stage of registration- javascript enabled browser
          //to retrieve all local government lists belonging to a particular state selected
          else if(par.equals("ajax_list"))
          {
              String name1="";
              String lga[] = null;
              //get the state in question
              String state=request.getParameter("state").trim();
              if(state==null || state.equals(""))
              {
                  //This tells the javascript that a problem occured in the process of registration
                  name1="error";
              }
              else
              {
                  //retrieve all the lga belonging to this state.
                  lga=rs.queryListofLga(state);
                  if(lga.length<2)
                  {
                      //This tells the javascript that a problem occured in the process of registration
                      //and that no local governement was found
                      name1="error";
                  }
                  else
                  {
                      //since everything went well, build and convert the lga into a json String
                      name1=this.buildJsonLga(lga);
                  }
                  
              }
              //then prepare to send the lga json string appropriately
              if(request.getParameter("source").equals("mobile"))
              {
                  Gson gson = new Gson();
                  gson=new GsonBuilder().create();
                  JsonProperties jsp=new JsonProperties(name1);
                  String  json_alert_detail= gson.toJson(jsp);
                  System.out.print("The gson alert detail obj:"+json_alert_detail);
                  //Is the request coming from native installed mobile app
                  if(request.getParameter("mnative")==null)
                  {
                       //send to native installed mobile app as json to be parsed
                       //and display in a form list
                      sendJSON(response,json_alert_detail);
                  }
                  else
                  {
                      jsp= new JsonProperties(lga);
                      json_alert_detail= gson.toJson(jsp);
                      //send to mobile web browser as json to be parsed
                      //and displayed in a form list
                      sendJSON(response,json_alert_detail);
                  }
            }
            else
            {
                //send to PC standalone browser as an XML for ajax/javascript to parse
                //and display in a form list
                sendXML(response,name1);
            }
          }
          //Does the user wants to view Actions Taken for his security alerts
          else if(par.equals("_"))
          {
              row=new Vector();
              //query the db for all the actions taken
              String sid=session.getAttribute("sid").toString();
              row=rs.queryActionsTaken(sid);
              if(row.size()==0)
              {
                  //send the appropriate message
                  session.setAttribute("admin_error","The action taken info for "+sid+" is empty.Can't find any in the db");
                  response.sendRedirect(request.getContextPath()+"/Email_ErrorPage.jsp");
              }
              else
              {
                  //otherwise store it in session and do send it.
                 session.setAttribute("row", row);
                 response.sendRedirect(request.getContextPath()+"/action_page.jsp");
                  
              }
          }
          //Does the client want to search for his/her alerts OR did the client make an ajax-based search request asynchronously while-on a search text field- typing for an instance?
          else if((par.equalsIgnoreCase("Search") && request.getParameter("id").equalsIgnoreCase("s1"))||(par.equals("ajax1") && request.getParameter("searchtf")!=null) )
          {
              //get the term to search for
              String searchtf=request.getParameter("searchtf");
              String snos="";
              //get the client's snos number
              row=new Vector();
              if(request.getParameter("source").equals("mobile"))
              {
                  snos=request.getParameter("snos_type");
              }
              else
              {
                  snos=session.getAttribute("snos_type").toString();
              }
              //search the database to retrieve the first 10 alerts marching-starting from 0-marching the search term. 
              row=rs.searchsmsList(snos,searchtf, 0, 10,true);
              //Did you find anything while searching?
              if(!row.isEmpty() )
              {
                  //Then prepare to send to mobile device thru json web service
                  if(request.getParameter("source").equals("mobile"))
                    {
                        String status="";
                        String fname=rs.RetrieveItem("clients_tab", "snos_type", snos, "name");
                        String json_alert_detail="";
                        JsonProperties jsp=null;
                        String date[]=new String[row.size()];
                        String alert[]=new String[row.size()];
                        Gson gson = new Gson();
                        gson=new GsonBuilder().create();
                        status="1";
                        alert=new String[row.size()];
                        date=new String[row.size()];
                        //loop through the search alerts and date and pass them to json property constructor
                        for(int i=0;i<row.size();i++)
                        {
                            String []data=(String[])row.get(i);
                            alert[i]=data[1];
                            date[i]=data[2];
                        }
                        jsp=new JsonProperties(snos,alert,date,status,1,Reconsoft.getSmsCurrentCount(),Reconsoft.getSmsTotalCount(),fname);
                        json_alert_detail= gson.toJson(jsp);
                        System.out.print("The gson alert detail obj:"+json_alert_detail);
                        sendJSON(response,json_alert_detail);
                    }
                    else
                    {
                        //Then prepare to display-to PC browser-the  alert jsp page with the seaech result .
                        session.setAttribute("row", row);
                        session.setAttribute("inbox_type", "search");
                        session.setAttribute("searchtf", searchtf);
                        session.setAttribute("min", 1);
                        session.setAttribute("smscount", Reconsoft.getSmsCurrentCount());
                        session.setAttribute("smstotalcount", Reconsoft.getSmsTotalCount());
                        session.setAttribute("serr","");
                        //javax.swing.JOptionPane.showMessageDialog(null, "Ajax info has been found in the search11.Thank you ");
                        if(request.getParameter("ajax1")==null)
                        {
                            response.sendRedirect(request.getContextPath()+"/view_sms.jsp");
                        }
                        else
                        {

                        }
                    }
              }
              //then the row must be empty. I mean no items found marching the search term.
              else
              {
                  //Then prepare to send to mobile device the error message via json
                  if(request.getParameter("source").equals("mobile"))
                  {
                      String status="2";
                      String fname=rs.RetrieveItem("clients_tab", "snos_type", snos, "name");
                      String json_alert_detail="";
                      JsonProperties jsp=null;
                      String date[]=new String[row.size()];
                      String alert[]=new String[row.size()];
                      Gson gson = new Gson();
                      gson=new GsonBuilder().create();
                      jsp=new JsonProperties(snos,alert,date,status,0,0,0,fname);
                      json_alert_detail= gson.toJson(jsp);
                      json_alert_detail= gson.toJson(jsp);
                      System.out.print("The gson alert detail obj:"+json_alert_detail);
                      sendJSON(response,json_alert_detail);
                  }
                  else
                  {
                      //Then prepare to display-to PC browser-the alert jsp page with the seaech "on item found" result
                      String output="";
                      output= "<table width='100%' height='40' cellpadding='0' cellspacing='0' align='center'><tr>";
                      output+="<td height='95' align='center' class='text'>";
                      output+=" <FONT FACE = 'ARIAL BLACK' SIZE = '2' COLOR ='blue'> We are sorry to inform you that no text matches<br> your search item and thus we can't find  <i>"+searchtf+"</i>  that you are looking for.<br>Thank you.\n<a href='servlets/RequestProcessing?action=sms'>Back to Inbox </a></FONT></div></td> </tr></table>";
                      session.setAttribute("serr",output );
                      if(request.getParameter("ajax1")==null)
                      {
                          response.sendRedirect(request.getContextPath()+"/view_sms.jsp");
                      }
                      else
                      {

                      }
                  } 
              }
          }
          //Does the user wants to change his/her password?
          else if(par.equals("Change My Password") && request.getParameter("id").equalsIgnoreCase("change_pass"))
          {
              //get the the user's old password from the form
              String opass=request.getParameter("opass").trim();
              //get the the user's new password from the form
              String npass=request.getParameter("npass").trim();
              //get the the user's email address from the session as stored the user logged in
              String mail1=(String)session.getAttribute("e-mail");
              //get the the user's comfirm or verify new password field text from the form
              String vnpass=request.getParameter("vnpass").trim();
              //Do the normal server side validation of these parameters
              if(pc.checkArbitraryField(request.getParameter("opass").trim(),"Password Field" , "Bungallow or Duplex or One-Bedroom Flat") && pc.checkArbitraryField(request.getParameter("npass").trim(),"Password Field" , "Bungallow or Duplex or One-Bedroom Flat") &&pc.checkArbitraryField(request.getParameter("vnpass").trim(),"Password Field" , "Bungallow or Duplex or One-Bedroom Flat"))
              {
                  //Is this user really registered-with this app- or did he hack into somebody's account
                  if((rs.CheckClientsLoginParameters(mail1, opass,"clients_tab")))
                  {
                      //Are the 'new password' and 'comfirm/verify new password' the same? 
                      if(npass.equalsIgnoreCase(vnpass))
                      {
                          //get the client's SNOS number from the corresponding source of request
                          String sid= "";
                          if(request.getParameter("source").equals("mobile"))
                          {
                              sid=request.getParameter("snos_type");
                          }
                          else
                          {
                              sid=   session.getAttribute("snos_type").toString();
                          }
                          //get the client (primary key)id that will be needed for the password update or overwrite.
                          String clientId=rs.RetrieveItem("clients_tab", "snos_type", sid, "id");
                          //carryout the update using the client's snos number and id.
                          int res=rs.UpdateUserPassword(sid, clientId, npass);
                          //Is the update successful?
                          if(res!=0)
                          {
                              //something didn't go wrong
                              //then logg of the client and ask him/her to re-login with the new password
                              session.invalidate();
                              //send a message to the appropriate source
                              if(request.getParameter("source").equals("mobile"))
                              {
                                  obj.put("status", "1");
                                  obj.put("msg", "Password change was successfull");
                                  sendJSON(response, obj.toJSONString());
                              }
                              else
                              {
                                  ct.setAttribute("logout","You have successfully changed your password.<br>Please you have been logged out. You can re-login if you wish.Thank you");
                                 response.sendRedirect(request.getContextPath()+"/index.jsp");
                              }
                          }
                          //something went wrong with theupdate
                           else
                           {
                               //then compose and send the appropriate message error
                               if(request.getParameter("source").equals("mobile"))
                               {
                                   obj.put("status", "3");
                                   obj.put("msg", "Could not change your password.Please Contact the Admin.\nThank you.");
                                   sendJSON(response, obj.toJSONString());
                               }
                               else
                               {
                                   session.setAttribute("errmsg"," Could not change your password.Please Contact the Admin<br>Thank you");
                                   response.sendRedirect(request.getContextPath()+"/change_password.jsp");
                               }
                           }
                      }
                      //then the two passwords are not equal
                      else
                      {
                          //then compose and send the appropriate message error
                          if(request.getParameter("source").equals("mobile"))
                          {
                              obj.put("status", "3");
                              obj.put("msg", "New and Verify New  Password Fields must be the same.\nThank you.");
                              sendJSON(response, obj.toJSONString());
                          }
                          else
                          {
                              session.setAttribute("errmsg"," New and Verify New  Password Fields must be the same.\nThank you");
                              response.sendRedirect(request.getContextPath()+"/change_password.jsp");
                          }
                      }
                  }
                  //Then the inputed password cannot be found in the database
                  else
                  {
                      //then compose and send the appropriate message error
                      if(request.getParameter("source").equals("mobile"))
                      {
                          obj.put("status", "3");
                          obj.put("msg", "Uknown/Invalid  Password,Please enter the correct password in the 'old password' field\nThank you.");
                          sendJSON(response, obj.toJSONString());
                      }
                      else
                      {
                          session.setAttribute("errmsg","Uknown/Invalid  Password,Please enter the correct password in the 'old password' field\nThank you");
                          response.sendRedirect(request.getContextPath()+"/change_password.jsp");
                      }
                  }
              }
              //Then the server side input filtering and validation failed
              else
              {
                  //then compose and send the appropriate message error
                  if(request.getParameter("source").equals("mobile"))
                  {
                      obj.put("status", "3");
                      obj.put("msg", "One of your fields has invalid inputs like numbers,or has too short or long letters.Also verify that you didn't enter special characters(eg:!,','',$,%)\nThank you.");
                      sendJSON(response, obj.toJSONString());
                  }
                  else
                  {
                      session.setAttribute("errmsg","One of your fields has invalid inputs like numbers,or has too short or long letters.Also verify that you didn't enter special characters(eg:!,','',$,%) or any other wrong input(s) and Make sure  that you entered the correct email formats.<br>Thank you");
                      //go to the same page and display the error message stored in the session
                      response.sendRedirect(request.getContextPath()+"/change_password.jsp");
                  }
              }
          }
          //Did the client looses his/her password and thus wants to recover it? 
          else if(par.equals("Recover Password") && request.getParameter("id").equalsIgnoreCase("recover_pass"))
          {
              //get the client's email address
              String mail1=request.getParameter("e-mail");
              //filter or vlaidate the email address
              if(pc.checkEmail(mail1.trim(),"Email Field" , "Bungallow or Duplex or One-Bedroom Flat"))
              {
                  //Does this email address exits in the database
                  if(rs.CheckIfItemExist("clients_tab", "email1", mail1))
                  {
                      //then get the user's name
                      String name=rs.RetrieveItem("clients_tab", "email1", mail1,"name");
                      //call this method to generate a unique token for the user
                      String  token= this.gEnerateUserToken();
                      //then get the user's snos number
                      String sid=  rs.RetrieveItem("clients_tab", "email1", mail1,"snos_type");
                      //compose the email message to be sent containing the TOKEN generated
                      String msg="Hi  ";
                      msg+=name+" \n ";
                      msg+="Thank you for subscribing and  using SNOS services. \n\n ";
                      msg+="We received your Password Recovery Request \n And we want to make sure that you were the one that made the request.\n ";
                      msg+="Please kindly enter the token below to COMFIRM that you made the request.\n\n\n\n ";
                      msg+="TOKEN: "+token+" \n\n\n\n";
                      msg+="Please ignore this email if you did not subscribe for SNOS services \n\n\n\nThank you.\n\n\n\n";
                      msg+="SNOS Team \nwww.snosfortress.com \n\n\n\nPlease do not reply this email.";
                      //Has this user already has either an unconfirmed token generated for him or has he any token attached to his account
                      //since we would not want a scenario where the database will be filled with both uncomfirmed or comfirmed generated tokens  for a particular client.
                      if(rs.CheckIfItemExist("tokentb", "snos_type", sid))
                      {
                          //then do delete his token info and add a new token for him-
                          //to be comfirmed when he visits his email inbox and click the link sent to him.
                          rs.DeleteUserToken(sid);
                          rs.AddUserToken(sid, token);
                      }
                      else
                      {
                          //since the user does not have any token pre-existing, add a new one for him/her-
                          //to be comfirmed when he visits his email inbox and click the link sent to him.
                          rs.AddUserToken(sid, token);
                      }
                      //then try to send the email message containing the token
                      String admin_email=System.getProperty("siteadmin_email");
                      String admin_emailpass=System.getProperty("admin_emailpassword");
                      if(new SnosSendEmail().SNOSemailSendToOne(admin_email,admin_emailpass ,"SNOS PASSWORD RECOVERY TOKEN ",msg ,mail1))
                      {
                          //compose and send the appropriate success message
                          if(request.getParameter("source").equals("mobile"))
                          {
                              obj.put("status", "1");
                              obj.put("msg", "Token email was sent successfully");
                              sendJSON(response, obj.toJSONString());
                          }
                          else
                          {
                              ct.setAttribute("logout","Token email was sent successfully.Please check your mail box to get your token<br>so that you can change your password<br>Thank you");
                              //Take the user to a jsp page where he can enter and verify the token sent to his email address.
                              response.sendRedirect(request.getContextPath()+"/token_verifier.jsp");
                          }
                      }
                      //then the email message could not be sent
                      else
                      {
                          //compose and send the appropriate error message
                          if(request.getParameter("source").equals("mobile"))
                          {
                              obj.put("status", "3");
                              obj.put("msg", "Token email could not be sent.\nThank you.");
                              sendJSON(response, obj.toJSONString());
                          }
                          else
                          {
                              session.setAttribute("errmsg"," An email for your token could not be sent.<br>Please do try again later<br>Thank you");
                              //go to the same page and display the error message stored in the session
                              response.sendRedirect(request.getContextPath()+"/recover_password.jsp");
                          }
                      }
                  }
                  //then the email address does not exists in the database
                  else
                  {
                      //compose and send the appropriate error message
                      if(request.getParameter("source").equals("mobile"))
                      {
                          obj.put("status", "3");
                          obj.put("msg", "Uknown User,Please enter the correct email field in the username field<br>Thank you.");
                          sendJSON(response, obj.toJSONString());
                      }
                      else
                      {
                          session.setAttribute("errmsg","Uknown User,Please enter the correct email field in the username field<br>Thank you.");
                          //go to the same page and display the error message stored in the session
                          response.sendRedirect(request.getContextPath()+"/recover_password.jsp");
                      }
                  }
              }
              //then the server-side validation of input failed
              else
              {
                  //compose and send the appropriate error message
                  if(request.getParameter("source").equals("mobile"))
                  {
                      obj.put("status", "3");
                      obj.put("msg", "Wrong  Email format. Ensure you didn't enter special characters(eg:!,','',$,%)<br>Thank you.");
                      sendJSON(response, obj.toJSONString());
                  }
                  else
                  {
                      session.setAttribute("errmsg","Wrong  Email format. Ensure you didn't enter special characters(eg:!,','',$,%)<br>Thank you.");
                      //go to the same page and display the error message stored in the session
                      response.sendRedirect(request.getContextPath()+"/recover_password.jsp");
                  }
              }
          }
          //Does the client want verify a token sent to his email address.
          else if(par.equals("Verify Token") && request.getParameter("id").equalsIgnoreCase("verify_token"))
          {
              String sid,token= "";
              //get the token that he/she entered in the form
              token=request.getParameter("token").trim();
              if(pc.checkArbitraryField(token,"Token Field" , "Bungallow or Duplex or One-Bedroom Flat"))
              {
                  //Does the token exists in our database of generated token
                  if(rs.CheckIfItemExist("tokentb", "token", token))
                  {
                      //then get the client's SNOS number
                      sid=rs.RetrieveItem("tokentb", "token", token,"snos_type");
                      //get the client's token id-primary key in the token table called 'tokentb' 
                      String TokenId=rs.RetrieveItem("tokentb", "token", token, "id");
                      //then update the user token staus to be '1' meaning that he has been confirmed or verified.
                      int UpdateUserTokenStatus = rs.UpdateUserTokenStatus(sid,TokenId, 1);
                      //then compose and send the appropriate success message
                      if(request.getParameter("source").equals("mobile"))
                      {
                          obj.put("status", "1");
                          obj.put("msg", "Token verification was  successful");
                          // 'snos_type' means client snos  number
                          obj.put("snos_type", sid);
                          sendJSON(response, obj.toJSONString());
                      }
                      else
                      {
                          ct.setAttribute("logout","Token verification was  successful.<br>Thank you");
                          //then take the user to the jsp page where he/she can now create a new password
                          response.sendRedirect(request.getContextPath()+"/create_newpassword.jsp");
                      }
                  }
                  //then the token enterd in the form does not exist in the database
                  else
                  {
                      //compose and send the appropriate error message
                      if(request.getParameter("source").equals("mobile"))
                      {
                          obj.put("status", "3");
                          obj.put("msg", "Uknown Token,Please enter the correct token sent to your email box.\nThank you.");
                          sendJSON(response, obj.toJSONString());
                      }
                      else
                      {
                          session.setAttribute("errmsg","Uknown Token,Please enter the correct token sent to your email box<br>Thank you.");
                          //go to the same page and display the error message stored in the session
                          response.sendRedirect(request.getContextPath()+"/token_verifier.jsp");
                      }
                  }
              }
              //server-side validation failed
              else
              {
                  //compose and send the appropriate error message
                  if(request.getParameter("source").equals("mobile"))
                  {
                      obj.put("status", "3");
                      obj.put("msg", "Wrong Token format. Ensure you didn't enter special characters(eg:!,','',$,%)\nThank you.");
                      sendJSON(response, obj.toJSONString());
                  }
                  else
                  {
                      session.setAttribute("errmsg","Wrong Token format. Ensure you didn't enter special characters(eg:!,','',$,%)\nThank you.");
                      //go to the same page and display the error message stored in the session
                      response.sendRedirect(request.getContextPath()+"/token_verifier.jsp");
                  }
              }
          }
          //Does the user wants to create a new password after verifying a token sent to his email address?
          else if(par.equals("Create Password") && request.getParameter("id").equalsIgnoreCase("create_pass"))
          {
              //get the client's email address, the new password and the verify new password he wants to create
              String mail1=request.getParameter("e-mail").trim();
              String npass=request.getParameter("npass").trim();
              String vnpass=request.getParameter("vnpass").trim();
              //validate his form parameters
              if(pc.checkEmail(mail1,"Email Field" , "Bungallow or Duplex or One-Bedroom Flat")&&pc.checkArbitraryField(npass,"Token Field" , "Bungallow or Duplex or One-Bedroom Flat") &&pc.checkArbitraryField(vnpass,"Token Field" , "Bungallow or Duplex or One-Bedroom Flat"))
              {
                  //does his email address exists in the database?
                  if(rs.CheckIfItemExist("clients_tab", "email1", mail1))
                  {
                      //in that case get the client's snos number and his/her token in the database.
                      String sid=rs.RetrieveItem("clients_tab", "email1", mail1,"snos_type");
                      String token=rs.RetrieveItem("tokentb", "snos_type", sid,"token");
                      //then get the client's token's status to ensure that it has been confirmed or verified.
                      String status=rs.RetrieveItem("tokentb", "token", token,"status");
                      //has the token status been confirmed or verified?
                      if(status.equalsIgnoreCase("1"))
                      {
                          //then it has been verified
                          //does the two passwords field march?
                          if(npass.equalsIgnoreCase(vnpass))
                          {
                              //get the client's id-primary key in the clients' permanent  table called 'clients_tab'
                              String clientId=rs.RetrieveItem("clients_tab", "snos_type", sid, "id");
                              //update his password accordingly with the new password he wants to create
                              int res=rs.UpdateUserPassword(sid, clientId, npass);
                              //was the update successfull?
                              if(res!=0)
                              {
                                  //then compose and send the appropriate success message to the right request source(mobile or PC browser)
                                  if(request.getParameter("source").equals("mobile"))
                                  {
                                      obj.put("status", "1");
                                      obj.put("msg", "Creation of new Password was successfull");
                                      sendJSON(response, obj.toJSONString());
                                  }
                                  else
                                  {
                                      ct.setAttribute("logout","You have successfully created  a new password.<br>Please you may have to login if you want.<br>Thank you");
                                      response.sendRedirect(request.getContextPath()+"/index.jsp");
                                  }
                              }
                              //the update attempt failed
                              else
                              {
                                  //then compose and send the appropriate error message to the right request source(mobile or PC browser)
                                  if(request.getParameter("source").equals("mobile"))
                                  {
                                      obj.put("status", "3");
                                      obj.put("msg", "Could not create your new password.Please Contact the Admin.\nThank you.");
                                      sendJSON(response, obj.toJSONString());
                                  }
                                  else
                                  {
                                      session.setAttribute("errmsg"," Could not create your new password.Please Contact the Admin<br>Thank you");
                                      response.sendRedirect(request.getContextPath()+"/create_newpassword.jsp");
                                  }
                              }
                          }
                          //the two passwords field don not march
                          else
                          {
                              //then compose and send the appropriate error message to the right request source(mobile or PC browser)
                              if(request.getParameter("source").equals("mobile"))
                              {
                                  obj.put("status", "3");
                                  obj.put("msg", "New and Verify New  Password Fields must be the same.\nThank you.");
                                  sendJSON(response, obj.toJSONString());
                              }
                              else
                              {
                                  session.setAttribute("errmsg"," New and Verify New  Password Fields must be the same.\nThank you");
                                  response.sendRedirect(request.getContextPath()+"/create_newpassword.jsp");
                              }
                          }
                      }
                      //there was a problem with verifying token status
                     else
                     {
                         //then compose and send the appropriate error message to the right request source(mobile or PC browser)
                         if(request.getParameter("source").equals("mobile"))
                         {
                             obj.put("status", "3");
                             obj.put("msg", "Sorry but it does not appear that you have been sent token. Please go to a form that will \nallow you to be given a token and  VERIFY IT before you can perform this action \nThank you.");
                             sendJSON(response, obj.toJSONString());
                         }
                         else
                         {
                             session.setAttribute("errmsg","Sorry but it does not appear that you have been sent token. Please go to a form that will <br>allow you to be given a token and  VERIFY IT before you can perform this action<br>Thank you.");
                             response.sendRedirect(request.getContextPath()+"/create_newpassword.jsp");
                         }
                     }
                  }
                  //email address not found in the database
                  else
                  {
                      //then compose and send the appropriate error message to the right request source(mobile or PC browser)
                      if(request.getParameter("source").equals("mobile"))
                      {
                          obj.put("status", "3");
                          obj.put("msg", "Uknown User Email,Please enter the correct email address used as your username<br>Thank you.");
                          sendJSON(response, obj.toJSONString());
                      }
                      else
                      {
                          session.setAttribute("errmsg","Uknown User Email,Please enter the correct email address used as your username<br>Thank you.");
                          response.sendRedirect(request.getContextPath()+"/create_newpassword.jsp");
                      }
                  }
              }
              //then the server-side validation(with PatternCheck class) of form input failed
              else
              {
                  //then compose and send the appropriate error message to the right request source(mobile or PC browser)
                  if(request.getParameter("source").equals("mobile"))
                  {
                      obj.put("status", "3");
                      obj.put("msg", "Wrong Input format. Ensure you entered correct email format: user@hostmail.com .\nRemove invalid characters(eg:!,','',$,%)\nThank you.");
                      sendJSON(response, obj.toJSONString());
                  }
                  else
                  {
                      session.setAttribute("errmsg","Imvalid data entry. Ensure you didn't enter special characters(eg:!,','',$,%)\nThank you.");
                      response.sendRedirect(request.getContextPath()+"/create_newpassword.jsp");
                  }
              }
          }
          //if par contains an action vallue "exit" then invalidate the 
          //session and loggs of the client sending appropriate message to both browser or to mobile through json webservice.
          else if(par.equals("exit") )
          {
              //then the user wants to sign or log out from his account
              //invalidate his/her session
              session.invalidate();
              //then compose and send the appropriate success message to the right request source(mobile or PC browser)
              if(source.equals("mobile"))
              {
                  obj.put("status", "1");
                  obj.put("msg", "You have successfully Logged out.<br>Thank you");
                  sendJSON(response, obj.toJSONString());
              }
             else 
             {
                 //store the message in the application context property called 'logout'
                 ct.setAttribute("logout","You have successfully Logged out.\nThank you");
                 ct.setAttribute("Is_logout","yes");
                 //take the user to the home page to echo the message as jsp expression language (el) statement
                 response.sendRedirect(request.getContextPath()+"/index.jsp");
             }
          }
          //if action contains no value, sends and display error message.
          //I mean the user clicked a button or link that comes to this servlet but no action parameter value
          //(containing a particular command or request to ask the servlet to execute)then report an error message
          //because it should not happen unless this application has been hacked or hijacked.
          else
          {
              //check if the request is coming from a mobile phone
              if(source.equals("mobile"))
              {
                  obj.put("status", "3");
                  obj.put("msg", "An internal error occured.Contact the Administrator.\nThank you");
                  sendJSON(response, obj.toJSONString());
              }
              //else the request must be coming from a desktop browser
              else 
              {
                  session.setAttribute("errmsg","An internal error occured.<br>Thank you");
                  session.setAttribute("admin_error","An unknown or action parameter request has been sent.\nThank you");
                  response.sendRedirect(request.getContextPath()+"/EmailErrorPage.jsp");
              }
          }
      }
      //then The action parameter par is empty         
      else
      {
          System.out.println("The action parameter par is empty");
          session.setAttribute("admin_error","Empty action parameter request-containing null value-  has been sent to the servlet.\nThank you");
          response.sendRedirect(request.getContextPath()+"/EmailErrorPage.jsp");
      }
   }
   finally 
       {
          out.close();
       }
        }
        catch(java.lang.IllegalStateException ex)
 
        {
            //throw new Exception(new ServletException(ex));// io.printStackTrace();
           //response.sendRedirect(request.getContextPath()+"/EmailErrorPage.jsp");
        }
    }//close the servlet method process request
    /***
     * This method simply sends sms to a particular gsm number using Twilo's sms cloud service
     * @param sid String object for TWILO(twilio.com) cloud sms authentication.
     * @param token String object for TWILO(twilio.com) cloud sms authentication.
     * @param messageToSend String object representing the message to send.
     * @param  firstadmin_gsm String object representing the recipient gsm number that would receive the message.
     * @param  HttpSession    session object representing http session object for storing possible error messages.
     * @param  HttpServletResponse response object representing http ServletResponse object for appropriate page redirections.
     * @param  HttpServletRequest request object representing http ServletResponse object for appropriate request contex path.
     * @return Boolean -It returns true if everything went well otherwise false
     */
    public  boolean SendSMS(String messageToSend,String firstadmin_gsm,HttpSession session,HttpServletResponse response,HttpServletRequest request) 
    {
        //initializes a the return variable to be false.
        boolean Issent=false;
        //Now gets the recipient gsm authentication key and token numbers from web.xml initialized 
        //and set by AppListener class and then stored at System Property  at the web application initialization.
        String from_gsm = System.getProperty("from_gsm");
        String  sid=System.getProperty("ACCOUNT_SID");
        String token=System.getProperty("AUTH_TOKEN");
        //check to see if these variables return null values and stores error message in the session object.
        if(firstadmin_gsm==null  ||from_gsm==null)
        {
            session.setAttribute("admin_error", "The SNOC administrators GSM numbers have been tampered with<br>Thus this application cannot send SMS.");
            
        }
        else if(ACCOUNT_SID==null ||AUTH_TOKEN==null)
        {
            session.setAttribute("admin_error", "The ACCOUNT_SID and AUTH_TOKEN have been tampered with<br>Thus this application cannot send SMS.");
            
        }
        //everything went well, I mean there is no null values reported, then prepare and send the message.
        else{
        try
        {
            //get twilo client object from the jar files as imported above, passing the sid and token as parameters
            TwilioRestClient client = new TwilioRestClient(sid, token);
            //Use the client object to construct message factory
            MessageFactory messageFactory = client.getAccount().getMessageFactory();
            //Then build an array list of the body, to and from parameters for the message
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("Body", messageToSend));
            params.add(new BasicNameValuePair("To", firstadmin_gsm));
            params.add(new BasicNameValuePair("From", from_gsm));
            //params.add(new BasicNameValuePair("MediaUrl", "https://static0.twilio.com/packages/foundation/img/twilio-logo-50x50.png"));
            //Message mms = messageFactory.create(params);
            //use the message factory to create and thus send the message
            Message message = messageFactory.create(params);
            //Print get and then print out the message sid
            System.out.println(message.getSid());
            Issent=true;
           
        }
        catch(TwilioRestException tw)
        {
             Issent=false;
        }
        
     }
        return  Issent;
    // Build a filter for the MessageList
    
  }
    /***
     * This methods simply sends an xml string to a remote client-ajax for javascript.
     * @param  response HttpServletResponse response object representing http ServletResponse object for getting a writer object for the sending or outputting.
     * @param  xmlValue String xmlValue object representing the xml string to be sent.
     * @throws Exception
     */
    public void sendXML(HttpServletResponse response,String xmlValue) throws IOException 
    {
        //gets a writer object from response object.
        PrintWriter out = response.getWriter();
        //sets the http status to be 200
        response.setStatus(200);
        //tells the browser or client that this content type is text/xml.
        response.setContentType("text/xml; charset=UTF-8");
        //Builds the message to be sent in xml format.
        String content = "<?xml version='1.0' encoding='UTF-8'?>"+
                         "<response>"+xmlValue+"</response>";
        //Writes out the xmlvalue using writer object's method: write
        out.write(content);
    }
    /***
     * This methods simply sends a json string to a remote client.
     * @param  response HttpServletResponse response object representing http ServletResponse object for getting a writer object for the sending or outputting.
     * @param  jsonValue String jsonValue object representing the json string to be sent.
     * @throws IOException
     */
    public void sendJSON(HttpServletResponse response,String jsonValue) throws IOException 
    {
        //sets the http status to be 200
        response.setStatus(200);
        //sets the header to to have no cache
        response.setHeader("Cache-Control", "no-cache");
        //makes the server to allow remote connnection any server.
        response.setHeader("Access-Control-Allow-Origin", "*");
        //tells the browser or client that this content type is json.
        response.setContentType("application/json; charset=UTF-8");
        //accepts the character encoding to be UTF-8
        response.setCharacterEncoding("UTF-8");
        //gets a writer object from response object
        PrintWriter out = response.getWriter();
        //Writes out the jsonvalue using writer object's method: write
        out.write(jsonValue);
    }
    @SuppressWarnings("empty-statement")
    /***
     * This methods simply builds an array of local government into a json format.
     * @param lga String array object that holds the array of local governements populated from the Database.
     * @return String -It is a string of built local goverment list in json format
     */
public String buildJsonLga(String lga[])
{
    String value="[";// int j=0;
    for(int j=0;j<lga.length;j++)
    {
        value+="'";
        value+=lga[j];
        value+="'";
        value+=",";
    }

    value+="]";
    System.out.println("The Overall jsonlga value of "+value);
    return value;
}
     /**
     * This methods simply generates user token by using a class UUID random method. 
     * It then gets the last 6 digits of the random generated key and returns it as the needed token String.
     * @return String -It is the token generated.
     */
    public String gEnerateUserToken()
    {
        //gets a unique by accessing the static class random method
        UUID uniqueKey = UUID.randomUUID();
        // converts the key to string format
        String token=uniqueKey.toString();
        //gets the last position of the character '-' in the key
        int val=token.lastIndexOf("-");
        //gets the substring of the token starting from the last position of '-' till the last character in the key
        String tokensub=token.substring(val, token.length());
        System.out.println("The substring of token ="+tokensub);
        //gets the last 6 character of the substring.
        token=tokensub.substring(tokensub.length()-6, tokensub.length());
        System.out.println("The final Token value is  ="+token);
        return token;
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}

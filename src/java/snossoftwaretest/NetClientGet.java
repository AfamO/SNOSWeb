/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package snossoftwaretest;

/**
 *
 * @author fupre1
 */
import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Message;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import static snossoftwaretest.RequestProcessing.ACCOUNT_SID;
import static snossoftwaretest.RequestProcessing.AUTH_TOKEN;
public class NetClientGet
{
    static String sEndpushMsgUrl="https://codename-one.appspot.com/sendPushMessage";
    static String deviceId= "4949863060996096"; //"5279573456453632";//"5957281768275968"
    static String packageName="com.teledom.snosapp";
    static String email="afamsimon@gmail.com";
    static String type="1";
    static String gooleAuthKey_ProjectNumber= "AIzaSyBT837SlAr-XzPtHzufXNF0Hbfe6ggaFKU"; //"920181369538";
    static String pushMessage="FRONT DOOR OPEN";
    static String prodEnvironment = "false";
    static String googleServerKey = "AIzaSyBT837SlAr-XzPtHzufXNF0Hbfe6ggaFKU";
    //static String iOSCertURL ="https://codename-one-push-certificates.s3.amazonaws.com/com.teledom.snosapp_ProductionPushafe5f146-0cd9-4108-96d8-d8e4cbda9161.p12";
    static String iOSCertURL = "https://codename-one-push-certificates.s3.amazonaws.com/com.teledom.snosapp_DevelopmentPushea852471-10ae-421d-b886-3db47be86e7f.p12";
    static String iOSCertPassword = "m34N9G9d";
    static String bbPushURL = "http://www.bbpush.com/ios.cert";
    static String bbAppId = "1sandkdkdkd";
    static String bbPassword = "mybbpass.com";
    static String bbPort = "233";
    public static void main(String[] args)
    {
        
            //gooleAuthKey_ProjectNumber="AIzaSyBT837SlAr-XzPtHzufXNF0Hbfe6ggaFKU";
            String purl="http://localhost:8084/SnoSSoftwareTest3/servlets/RequestProcessing"; 
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("device", deviceId));
            params.add(new BasicNameValuePair("packageName", packageName));
            params.add(new BasicNameValuePair("email", email));
            params.add(new BasicNameValuePair("type", type));
            params.add(new BasicNameValuePair("auth", gooleAuthKey_ProjectNumber));
            params.add(new BasicNameValuePair("certPassword", iOSCertPassword));
            params.add(new BasicNameValuePair("body", pushMessage));
            params.add(new BasicNameValuePair("cert", iOSCertURL));
            params.add(new BasicNameValuePair("production", prodEnvironment));
            makePushNotificationRequest(sEndpushMsgUrl,"POST",params);
            
            // send SMS
            //SendSMS("How are yOu?","+2348064500095");
            
             
           /*
    System.out.println("The Local String time is:"+new java.util.Date().toLocaleString());
    System.out.println("The GMT String time is:"+new java.util.Date().toGMTString());
    System.out.println("The date String time is:"+new java.util.Date().toString());

    System.out.println("The path separator is:"+System.getProperty("path.separator"));
    System.out.println("The file separator is:"+System.getProperty("file.separator"));
    System.out.println("The os is: "+System.getProperty("os.name"));
    System.out.println("The Class path is: "+System.getProperty("java.class.path"));
    System.out.println("The  Java full Home is: "+System.getProperty("java.home"));
    System.out.println("The  Java full Home is: "+System.getProperty("java.home")+System.getProperty("file.separator")+"javax.comm.properties");
    System.out.println("The  OS version is: "+System.getProperty("os.version"));
    System.out.println("The  java version is: "+System.getProperty("java.version"));
    System.out.println("The  user name is: "+System.getProperty("user.name")+" and his home is:"+System.getProperty("user.home"));

    */ 
	}
      
        public static void makePushNotificationRequest(String url, String method,
            List<NameValuePair> params) {
            InputStream is = null;
        // Making HTTP request
        try {
 
            // check for request method
            if(method == "POST"){
                // request method is POST
                // defaultHttpClient
                DefaultHttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(url);
                httpPost.setEntity(new UrlEncodedFormEntity(params));
                 System.out.println("log_tag:POST's URL IS: " + url);
                 
                HttpResponse httpResponse = httpClient.execute(httpPost);
                HttpEntity httpEntity = httpResponse.getEntity();
                is = httpEntity.getContent();
                System.out.println("log_tag:Credential Provider= " +httpClient.getCredentialsProvider());
                System.out.println("log_tag:ResponseInterceptorCount= " +httpClient.getResponseInterceptorCount());
                System.out.println("Content lent="+httpEntity.getContentLength());
                
 
            }else if(method == "GET"){
                // request method is GET
                DefaultHttpClient httpClient = new DefaultHttpClient();
                String paramString = URLEncodedUtils.format(params, "utf-8");
                url += "?" + paramString;
                System.out.println("log_tag:GET's URL IS: " + url);
                HttpGet httpGet = new HttpGet(url);
                
                HttpResponse httpResponse = httpClient.execute(httpGet);
                System.out.println("log_tag:Credential Provider= " +httpClient.getCredentialsProvider());
              // System.out.println("log_tag:Credential Provider= " +httpClient.getCredentialsProvider());
                System.out.println("log_tag:ResponseInterceptorCount= " +httpClient.getResponseInterceptorCount());
                //System.out.println("Content lent="+httpEntity.getContentLength());
                
                HttpEntity httpEntity = httpResponse.getEntity();
                is = httpEntity.getContent();
               // Log.e("log_tag", "content is: " +httpEntity.getContent().toString());
            }           
 
        } catch (UnsupportedEncodingException e) {
        	System.out.println("log_tag:Error in http connection " + e.toString());
            //e.printStackTrace();
        } catch (ClientProtocolException e) {
        	System.out.println("log_tag:Error in http connection " + e.toString());
            //e.printStackTrace();
            //e.printStackTrace();
        } catch (IOException e) {
        	
        	System.out.println("log_tag: Error in http connection " + e.toString());
            //e.printStackTrace();
            
        }
 
        try {
            String result="";
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            result = sb.toString();
            System.out.println("The RESULT FROM REMOTE SERVER for http method "+method+" is: "+result);
        } catch (Exception e) {
            System.out.println("BufferedERRORRRRR Error Error converting result " + e.getMessage());
            e.printStackTrace();
        }
 
         
    }
    
  public static boolean SendSMS(String messageToSend,String firstadmin_gsm) 
    {
        //initializes a the return variable to be false.
        boolean Issent=false;
        //Now gets the recipient gsm authentication key and token numbers from web.xml initialized 
        //and set by AppListener class and then stored at System Property  at the web application initialization.
        String from_gsm = "+12015802467";
                //"+18064521296";
        String  sid="AC4c3d5f67d4d33df1b7326c7cf0d77563";
                //System.getProperty("ACCOUNT_SID");
        String token="a37263df0e9ac7f0ab00df2a7ae73d18";
                //System.getProperty("AUTH_TOKEN");
        
        
        //everything went well, I mean there is no null values reported, then prepare and send the message.
       
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
            System.out.println("SMS sent");
            Issent=true;
           
        }
        catch(TwilioRestException tw)
        {
             Issent=false;
             System.out.println("SMS NOT sent Because of\n "+tw.getMessage());
             tw.printStackTrace();
        }
      return  Issent;  
     }
        
    // Build a filter for the MessageList
    
  


}
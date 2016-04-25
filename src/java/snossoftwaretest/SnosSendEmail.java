/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snossoftwaretest;


/**
 *
 * @author Charles
 */

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Address;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.NewsAddress;
import javax.mail.Authenticator;
import javax.swing.JOptionPane;
/**
 * This manages and defines all the methods needed 
 * to setup and send e-mails 
 * in any part of the application.
 * @param uid :String object that tracks the id of each of user's device row or contact data row as located in the database table.
 * This is for update purposes.
 * @param admin_emailpassword String object representing the password of the administrator's sender email address as created in the email host platform 
 * @param sender String object representing the email address that sends the email to clients.
 * @param email_port String object representing the current smtp port that email service provider uses to send email to clients..
 * @param email_host String object representing the current smtp host or IP address that email service provider uses.
 * @author AFAM, Charles;
 * @see RequestProcessing.java,Connector1.java,
 * @version 1.0 
 */

public class SnosSendEmail  extends Object
{
    //declares these variables explained above
    private static String admin_emailpassword;
    private static String sender;
    private  String email_port;
    private  String email_host;
    public SnosSendEmail()
    {
        //gets the needed parameters from System property method as set by Applistener at the Initialization of this web application
        admin_emailpassword =System.getProperty("admin_emailpassword");
        sender = System.getProperty("siteadmin_email");
        email_port = System.getProperty("email_port");
        email_host = System.getProperty("email_host");
        System.out.println("The email parameters are: emailhost="+System.getProperty("email_host")+"sender="+sender+"email_port="+email_port+"adminpassword="+admin_emailpassword);
        //varidate them
        if(sender.equals("")||admin_emailpassword.equals("")||email_host.equals("")||email_port.equals(""))
          {
              System.out.println("The email sending parameters are empty");
          }
    }
    /** 
    * This method sends an email to a particular person.
    * @param sendereEmail String object encapsulating the email address of the sender of the email.
    * @param password String object encapsulating the password of the sender of the email.
    * @param title String object encapsulating the title or header of the email.
    * @param Mess String object encapsulating the message to be sent by email.
    * @param Sendto String object encapsulating the recipient's email address.
    * @return Boolean -It returns true if email sent successfully or false otherwise.
    * @throws IOException if network access failed.
    */
    public boolean SNOSemailSendToOne( final String sendereEmail, final String password,String title,String Mess,String Sendto) 
    {
        //declares this to be false by default
        boolean Isent=false;
        try
        {
            //gets a property object
            Properties props = new Properties();
            // assign the smtp host
            props.put("mail.smtp.host", email_host); // for gmail use smtp.mail.yahoo.com mail.brinkster.net
            //enable smtp authentication
            props.put("mail.smtp.auth", "true");
            //enables debug and outputting of info while sending
            props.put("mail.debug", "true");
            //enables tls
            props.put("mail.smtp.startssl.enable", "true");
            // assign the smtp port
            props.put("mail.smtp.port", email_port);
            //get the needed session to use for the email sending operation
            //to do this access an anonymous inner class.
            Session mailSession = Session.getInstance(props, new javax.mail.Authenticator()
            {
                protected PasswordAuthentication getPasswordAuthentication()
                {
                    return new PasswordAuthentication(sendereEmail, password);
                }
            });
            //enables the debug mode
            mailSession.setDebug(true);
            //get message obj
            Message msg = new MimeMessage( mailSession );
            //sets the content type
            //msg.setHeader("content-type", "text/html");
            msg.setContent(msg, "text/plain");
            //sets the sender
            msg.setFrom( new InternetAddress( sendereEmail ) );
            // get the recipient object
            InternetAddress addressTo;
            addressTo = new InternetAddress(Sendto);
            //sets the recipient
            msg.setRecipient(Message.RecipientType.TO, addressTo);
            //set receiver information
            msg.setSentDate( new Date());
            msg.setSubject( title );
            //sets the email text
            msg.setText( Mess );
            //Ask the Transport class to send our mail message
            Transport.send( msg );
            System.out.println( "Message Sent!");
            Isent=true;
            System.out.println( "CONTENT TYPE: "+msg.getContentType());
            System.out.println( "HEADER TYPE: "+msg.getHeader("content-type"));
        }
        catch(Exception E)
        {
            System.out.println( "Oops something has gone pearshaped!Could not send email");
            Isent=false;
            System.out.println( E );
            E.printStackTrace();
            
        }
        return Isent;
    }//closes the method
}//closes this class
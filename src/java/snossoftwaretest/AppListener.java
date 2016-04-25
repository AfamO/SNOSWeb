/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package snossoftwaretest;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.naming.*;
import javax.sql.DataSource;
import java.util.logging.Logger;
import java.util.Properties;


/**
 * Web application lifecycle listener-this initializes the init(database parameters, host, admin_email,etc) parameters 
 * from the web.xml and then sets them ready to be used in the whole application's context.
 * @author Afam;
 * @see Connector1.java,RequestProcessing.java
 * @version 1.0 
 */

public class AppListener implements ServletContextListener {
    /***
     * This method gets all the application needed init 
     * parameters(like database connection parameters for instance) from web.xml. 
     * @param  ServletContextEvent sce object representing http ServletContextEvent object for accessing the getServletContext() method.
     */
    public void contextInitialized(ServletContextEvent sce) 
    {
        //Get all the context parameters parameters and their values from web.xml 
        String adminemail = sce.getServletContext().getInitParameter("admin_email");
        String dbname = sce.getServletContext().getInitParameter("dbname");
        String dbhost = sce.getServletContext().getInitParameter("host");
        String firstadmin_gsm = sce.getServletContext().getInitParameter("firstadmin_gsm");
        String secondadmin_gsm = sce.getServletContext().getInitParameter("secondadmin_gsm");
        String from_gsm = sce.getServletContext().getInitParameter("from_gsm");
        String ACCOUNT_SID = sce.getServletContext().getInitParameter("ACCOUNT_SID");
        String AUTH_TOKEN = sce.getServletContext().getInitParameter("AUTH_TOKEN");
        String mobile_host=sce.getServletContext().getInitParameter("mobile_host");
        String dbport = sce.getServletContext().getInitParameter("dbport");
        String dbuser = sce.getServletContext().getInitParameter("dbuser");
        String dbpass = sce.getServletContext().getInitParameter("dbpass");
        String sender = sce.getServletContext().getInitParameter("siteadmin_email");
        String admin_emailpassword = sce.getServletContext().getInitParameter("admin_emailpassword");
        String email_port = sce.getServletContext().getInitParameter("emailport");
        String email_host = sce.getServletContext().getInitParameter("emailhost");
        String log_file = sce.getServletContext().getInitParameter("log_file");
        String log_limit = sce.getServletContext().getInitParameter("logLimit");
        //Store or set all the parameters in the System static property method so that you can access
        //them anywhere or in any class  in the whole application.
        System.setProperty("adminemail", adminemail);
        System.setProperty("dbname", dbname);
        System.setProperty("mobile_host", mobile_host);
        System.setProperty("dbhost", dbhost);
        System.setProperty("dbuser", dbuser);
        System.setProperty("dbpass", dbpass);
        System.setProperty("dbname", dbname);
        System.setProperty("dbport", dbport);
        System.setProperty("firstadmin_gsm", firstadmin_gsm);
        System.setProperty("secondadmin_gsm", secondadmin_gsm);
        System.setProperty("from_gsm", from_gsm);
        System.setProperty("ACCOUNT_SID", ACCOUNT_SID);
        System.setProperty("AUTH_TOKEN", AUTH_TOKEN);
        System.setProperty("siteadmin_email", sender);
        System.setProperty("admin_emailpassword", admin_emailpassword);
        System.setProperty("email_port", email_port);
        System.setProperty("email_host", email_host);
        System.setProperty("log_file", log_file);
        System.setProperty("LogLimit", log_limit);
        System.setProperty("connections_counter","0");//Initializes number of db connections
    }
     /***
     * This method gets closes and destroys-at the close of the application- the context intialized-at the start of the application-. 
     * @param  ServletContextEvent sce object representing http ServletContextEvent object for accessing the getServletContext() method.
     */
    public void contextDestroyed(ServletContextEvent sce)
    {
       // throw new UnsupportedOperationException("Not supported yet.");
    }
}
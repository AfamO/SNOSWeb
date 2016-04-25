package snossoftwaretest;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Afam
 */
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.sql.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.dbutils.DbUtils;
/**
 * This defines all the methods needed to perform 
 * all the database access of the application 
 * especially CRUD(create, retrieve, update and delete).
 * @param uid :String object that tracks the id of each of user's device row or contact data row as located in the database table.
 * This is for update purposes.
 * @param statement Statement object that will be used to get the database statement to execute
 * @param dbconnector Connector object that will be used to access the connection class of the whole application.
 * @param getuser UserInforGet object accessing the Get and Set class of this application.
 * @param smscount int variable that stores the current count of sms/alerts that has been retrieved-from database- at each time.
 * @param totalcount int variable that stores the total size or  count of sms/alerts that belongs to a client- at each time.
 * @param record String array object called record which stores each information contained in a database's table column.
 * @author AFAM, Charles;
 * @see RequestProcessing.java,Connector1.java,JsonProperties.java,SnosSendEmail.java,,
 * @version 1.0 
 */
public class Reconsoft
{
    //Declare these objects and these variables-explained above- as global
    private Statement statement;
    private Connector dbconnector;
    private Vector row;
    private UserInforGet getuser;
    private static int sms_current_count=0;
    private static int sms_total_count=0;
    private static int last_Id=0;
    private String[] record;
    public Reconsoft()
    {
        super();
        //get an object of the database connection class
        dbconnector = new Connector ();
    }
    /** 
    * This method generates MD5 hash for password hashing
    * @param original String object representing the original password text.
    * @throws NoSuchAlgorithmException if the MD5 algorithm access operation failed.
    */
    private String getMD5(String original)
    {
		MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Reconsoft.class.getName()).log(Level.SEVERE, null, ex);
        }
		md.update(original.getBytes());
		byte[] digest = md.digest();
		StringBuilder sb = new StringBuilder();
		for (byte b : digest) {
			sb.append(String.format("%02x", b & 0xff));
		}
                return  sb.toString();
    }
    /** 
    * This method inserts client's personal information to database
    * @param uig UserInforGet object accessing the Get and Set class of this application.The class is instantiated before this method is called
    * @param table String object representing the table where the information will be inserted into.
    * @throws SQLException if database access operation failed.
    */
    public void AddUserInfo(UserInforGet uig, String table)
    {
        String password="";
        //Is this client inserting into db for the first time?
        if(table.equals("clients_temp_tab"))
        {
            //then generate an MD5 hash of his/her password
            password=getMD5(uig.getClientPassword());
        }
        else
        {
            //then reuse initially generated MD5 hash of his/her password
            password=uig.getClientPassword();
        }
        //connects to the database
        dbconnector.databaseConnect();
        try
        {
            //creates database statement to be used for database operation's execution.
            statement = dbconnector.dbconn.createStatement();
            //constructs the needed sql query
            String query = "INSERT INTO "+table+
            "(snos_type,fone,name,address,lga,state,email1,password,date1,sessid,link1,status)" +
            "VALUES('" +uig.getSensor()+ "','" + uig.getFone()+ "','" + uig.getClient()+ "','" + uig.getContact() + "','" + uig.getLga()
            + "','" + uig.getState()+ "','" + uig.getClientEmail()+ "','"  + password+ "','" + uig.getDate1() + "','" + uig.getSessionId()+ "','" + uig.getLink1()+ "','" + uig.getStatus() + "')";
            //executes the constructed query 
            int result = statement.executeUpdate( query );
            
            //closes the statement created
            statement.close();
        }
        //catches any sql exception that might occur
        catch( SQLException sqlex )
        {
            //prints the error message-though this should be in a logg file or any log output
            //but is not possible at the moment because of time constraints among other reasons.
            System.out.println("The sql exception message is:\n"+sqlex.toString());
            sqlex.printStackTrace();
        }
        //closes all possible connection to database
        finally
        {
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly( dbconnector.dbconn);
        }
    }
    /** 
    * This method inserts client's personal information to database after his/her registration has been comfirmed
    * @param uig UserInforGet object accessing the Get and Set class of this application.The class is instantiated before this method is called
    * @param table String  object representing the table where the information will be inserted into.
    * @throws SQLException if database access operation failed.
    */
    public void AddConfirmUserInfo(UserInforGet uig, String table)
    {
        //connects to the database
        dbconnector.databaseConnect();
        try
        {
            //creates database statement to be used for database operation's execution.
            statement = dbconnector.dbconn.createStatement();
            //constructs the needed sql query.
            String query = "INSERT INTO "+table+
            "(snos_type,fone,name,address,lga,state,email1,password,date1,status)" +
            "VALUES('" +uig.getSensor()+ "','" + uig.getFone()+ "','" + uig.getClient()+ "','" + uig.getContact() + "','" + uig.getLga()
            + "','" + uig.getState()+ "','" + uig.getClientEmail()+ "','" + uig.getClientPassword()+ "','" + uig.getDate1() + "','" + uig.getStatus() + "')";
            //executes the constructed query. 
            int result = statement.executeUpdate(query);
            //closes the statement created.
            statement.close();
        }
        catch (SQLException sqlex)
        {
            //prints the error message-though this should be in a logg file or any log output
            //but is not possible at the moment because of time constraints among other reasons.
            System.out.println("The sql exception message is:\n"+sqlex.toString());
            sqlex.printStackTrace();
        }
        //closes all possible connection to database
        finally
        {
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly( dbconnector.dbconn);
        }
    }
    /** 
    * This method inserts client's auto mobile device id -as generated for Push message services-to database after his/her registration has been comfirmed
    * @param snos String object representing snos number.
    * @param device_id String  object representing the device id in question.
    * @throws SQLException if database access operation failed.
    */
    public void AddPushDeviceId(String snos, String device_id)
    {
        //connects to the database
        dbconnector.databaseConnect();
        try
        {
            //creates database statement to be used for database operation's execution.
            statement = dbconnector.dbconn.createStatement();
            //constructs the needed sql query.
            String status="registered";
            String query = "INSERT INTO phone_info "+
            "(snos_type,device_id,date1,status)" +
            "VALUES('" +snos+ "','" + device_id+ "','" + new java.util.Date().toGMTString()+ "','" + status+ "')";
            //executes the constructed query. 
            int result = statement.executeUpdate(query);
            //closes the statement created.
            statement.close();
        }
        catch (SQLException sqlex)
        {
            //prints the error message-though this should be in a logg file or any log output
            //but is not possible at the moment because of time constraints among other reasons.
            System.out.println("The sql exception message is:\n"+sqlex.toString());
            sqlex.printStackTrace();
        }
        //closes all possible connection to database
        finally
        {
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly( dbconnector.dbconn);
        }
    }
    /** 
    * This method retrieves all the local government areas belonging to a particular state
    * @param state String  object representing the state of the local governments.
    * @throws SQLException if database access operation failed.
    * @return String -It is the array of local government retrieved.
    */
    public String[] queryListofLga(String state)
    {
        //connects to the database.
        dbconnector.databaseConnect();
        ResultSet rs=null;
        String lgarecord[] = null;
        try
        {
            //creates database statement to be used for database operation's execution.
            statement = dbconnector.dbconn.createStatement();
            //constructs the needed sql query.
            String query = "SELECT * FROM lga_tab WHERE State = '" +state + "'";
            //gets the needed Result Set of the sql query.
            rs = statement.executeQuery(query);
            int num = 0;
            //get the number of the local government in this state
            num = CountLga(state);
            //increments it to 1 since the loop will start from 1 instead of traditional 0
            num = num + 1;
            lgarecord= new String[num];
            lgarecord[0] = "Choose one";
            //loop through the lga
            for(int i = 1; i < num; i++)
            {
                rs.next();
                lgarecord[i] = (rs.getString(3));
                //has the loop exhausted the number of local govt lists?
                if (i == num-1)
                {
                    return lgarecord;
                }
            }
            //closes the statement created.
            statement.close();
        }
        catch (SQLException exc) 
        {
            //prints the error message-though this should be in a logg file or any log output
            //but is not possible at the moment because of time constraints among other reasons.
            System.out.println("The sql exception message is:\n"+exc.toString());
            exc.printStackTrace();
        }
        //closes all possible connection to database
        //closes all possible connection to database
        finally
        {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly( dbconnector.dbconn);
        }
        return lgarecord;
    }
    /** 
    * This method retrieves and COUNT THE NUMBER OF all the local government areas belonging to a particular state.
    * @param state String  object representing the state of the local governments.
    * @throws SQLException if database access operation failed.
    * @return int -It is the number of the local government areas retrieved.
    */
    public int CountLga(String state)
    {
        //connects to the database.
        dbconnector.databaseConnect();
        ResultSet rs1 = null;
        try
        {
            //creates database statement to be used for database operation's execution.
            statement = dbconnector.dbconn.createStatement();
            //constructs the needed sql query.
            String query = "SELECT * FROM lga_tab  WHERE State = '" +
            state + "'";
            //gets the needed Result Set of the sql query.
            rs1 = statement.executeQuery(query);
            int num = 0;
            String ans = "yes";
            // a condition to loop further
            while (ans.equals("yes"))
            {
                num ++;
                // Are  local governments found?
                if(rs1.next())
                {
                    //this means that it should loop further.
                    ans = "yes";
                }
                else
                {
                    //thie means that it should not loop further.
                    ans = "n";
                    return num - 1;
                }
            }
            //closes the statement created.
            statement.close();
            //the default value
            return 0;
            
        }
        catch (SQLException exc)
        {
            //prints the error message-though this should be in a logg file or any log output
            //but is not possible at the moment because of time constraints among other reasons.
            System.out.println("The sql exception message is:\n"+exc.toString());
            exc.printStackTrace();
            return 0;
        }
        //closes all possible connection to database
        finally
        {
            DbUtils.closeQuietly(rs1);
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly( dbconnector.dbconn);
        }
    }
    /** 
    * This method inserts client's device/object information to database.
    * @param uig UserInforGet object accessing the Get and Set class of this application.The class is instantiated before this method is called
    * @param table String object representing the table where the information will be inserted into.
    * @throws SQLException if database access operation failed.
    */
    public void AddDeviceInfo(UserInforGet uig, String table)
    {
        //connects to the database.
        dbconnector.databaseConnect();
        try
        {
            //creates database statement to be used for database operation's execution.
            statement = dbconnector.dbconn.createStatement();
            //constructs the needed sql query.
            String query = "INSERT INTO "+table+
            "(snos_type,name,descrpt,housing_type,housing_location,address,lga,state)" +
            "VALUES('" +uig.getSensor()+ "','" + uig.getClient()+ "','" + uig.getDecrpt()+ "','" + uig.getBuild() + "','" + uig.getLoc()
            + "','" + uig.getContact()+ "','" + uig.getLga()+ "','" + uig.getState()+ "')";
            //executes the constructed query. 
            int result = statement.executeUpdate(query);
            //closes the statement created.
            statement.close();
        }
        catch ( SQLException sqlex )
        {
            //prints the error message-though this should be in a logg file or any log output
            //but is not possible at the moment because of time constraints among other reasons.
            System.out.println("The sql exception message is:\n"+sqlex.toString());
            sqlex.printStackTrace();
        }
        //closes all possible connection to database
        finally
        {
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly( dbconnector.dbconn);
        }
    }
    /** 
    * This method inserts client's Contacts' information to database.
    * @param uig UserInforGet object accessing the Get and Set class of this application.The class is instantiated before this method is called
    * @param table String  object representing the table where the information will be inserted into.
    * @throws SQLException if database access operation failed.
    */
    public void AddClient_Contacts(UserInforGet uig, String table)
    {
        //connects to the database.
        dbconnector.databaseConnect();
        try
        {
            //constructs default sms and email message type. Note in the higher version of
            //this doftware this shpuld be inputed by the client during the 3rd stage of registration.
            String defaultsmstype="Your Attention is Highly Needed";
            String defaultmailtype="Your Attention is Highly Needed ,Pls Check my compound";
            //creates database statement to be used for database operation's execution.
            statement = dbconnector.dbconn.createStatement();
            //constructs the needed sql query.
            String query = "INSERT INTO "+table +
            "(snos_type,name,relation_position,address,email1,gsm,sms_type,mail_type)" +
            "VALUES('" +uig.getSensor() + "','" + uig.getClient()+ "','" + uig.getRelation_Position()+
            "','" + uig.getContact() + "','" + uig.getClientEmail()+ "','" + uig.getFone()+ "','" + defaultsmstype+ "','" + defaultmailtype+ "')";
            //executes the constructed query. 
            int result = statement.executeUpdate( query );
            //closes the statement created.
            statement.close();
        }
        catch ( SQLException sqlex )
        {
            //prints the error message-though this should be in a logg file or any log output
            //but is not possible at the moment because of time constraints among other reasons.
            System.out.println("The sql exception message is:\n"+sqlex.toString());
        }
        //closes all possible connection to database
        finally
        {
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly( dbconnector.dbconn);
        }
        
    }
    /** 
    * This method inserts the web administrators' information to database.
    * @param uig UserInforGet object accessing the Get and Set class of this application.The class is instantiated before this method is called.
    * @throws SQLException if database access operation failed.
    */
    public void AddOprator(UserInforGet uig)
    {
        //connects to the database.
        dbconnector.databaseConnect();
        try
        {
            //creates database statement to be used for database operation's execution.
            statement = dbconnector.dbconn.createStatement();
            //constructs the needed sql query.
            String query = "INSERT INTO user_sensor_table" +
            "(user_name,user_id,password,status)" +
            "VALUES('" +uig.getOperaName() + "','" + uig.getUserid()+ "','" + uig.getPass()+ "','" + uig.getStatus()  + "')";
            //executes the constructed query. 
            int result = statement.executeUpdate( query );
            //closes the statement created.
            statement.close();
        }
        catch ( SQLException sqlex )
        {
            //prints the error message-though this should be in a logg file or any log output
            //but is not possible at the moment because of time constraints among other reasons.
            System.out.println("The sql exception message is:\n"+sqlex.toString());
        }
        //closes all possible connection to database
        finally
        {
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly( dbconnector.dbconn);
        }
    }
    /** 
    * This method inserts a client's sensor to database.
    * @param snostype String object representing the SNOS number of the client that has the sensor.
    * @param sensor String object representing the sensor name in question.
    * @throws SQLException if database access operation failed.
    */
    public void AddSensor(String snostype, String sensor)
    {
       //connects to the database.
        dbconnector.databaseConnect();
        try
        {
            //creates database statement to be used for database operation's execution.
            statement = dbconnector.dbconn.createStatement();
            //constructs the needed sql query.
            String query = "INSERT INTO sensor_table" +
            "(snostype,sensor)" +
            "VALUES('" +snostype +"','" +sensor + "')";
            //executes the constructed query. 
            int result = statement.executeUpdate( query );
            //closes the statement created.
            statement.close();
        }
        catch ( SQLException sqlex )
        {
            //prints the error message-though this should be in a logg file or any log output
            //but is not possible at the moment because of time constraints among other reasons.
            System.out.println("The sql exception message is:\n"+sqlex.toString());
        }
        //closes all possible connection to database
        finally
        {
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly( dbconnector.dbconn);
        }
    }
    /** 
    * This method inserts a client's SNOS Number(Client Identification Key) to database.
    * @param uig UserInforGet object accessing the Get and Set class of this application.The class is instantiated before this method is called
    * @param status String object representing the table where the information will be inserted into.
    * @throws SQLException if database access operation failed.
    */
    public void AddSnos(UserInforGet d,String status)
    {
        //connects to the database.
        dbconnector.databaseConnect();
        try
        {
            //creates database statement to be used for database operation's execution.
            statement = dbconnector.dbconn.createStatement();
            //constructs the needed sql query.
            String query = "INSERT INTO snos_table" +
            "(snos_type,fone,status)" +
            "VALUES('" +d.getSensor() + "','" +d.getFone() + "','" +status + "')";
            //executes the constructed query.
            int result = statement.executeUpdate( query );
            //closes the statement created.
            statement.close();
        }
        catch ( SQLException sqlex )
        {
            //prints the error message-though this should be in a logg file or any log output
            //but is not possible at the moment because of time constraints among other reasons.
            System.out.println("The sql exception message is:\n"+sqlex.toString());
        }
        //closes all possible connection to database
        finally
        {
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly( dbconnector.dbconn);
        }
    }
    /** 
    * This method inserts a client's generated token to database.
    * @param snostype String object representing the client's SNOS number.
    * @param token String object representing the token in question.
    * @throws SQLException if database access operation failed.
    */
    public void AddUserToken(String snostype,String token)
    {
        //connects to the database.
        dbconnector.databaseConnect();
        try
        {
            //set the default value of token status to 0 untill comfirmed and it changes to 1.
            int status=0;
            //creates database statement to be used for database operation's execution.
            statement = dbconnector.dbconn.createStatement();
            //constructs the needed sql query.
            String query = "INSERT INTO tokentb" +
            "(snos_type,token,status)" +
            "VALUES('" +snostype + "','" +token + "','" +status + "')";
            //executes the constructed query.
            int result = statement.executeUpdate(query);
            //closes the statement created.
            statement.close();
        }
        catch ( SQLException sqlex )
        {
            //prints the error message-though this should be in a logg file or any log output
            //but is not possible at the moment because of time constraints among other reasons.
            System.out.println("The sql exception message is:\n"+sqlex.toString());
        }
        //closes all possible connection to database
        finally
        {
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly(dbconnector.dbconn);
        }
    }
    /** 
    * This method inserts gets the total number of SNOS numbers registered at each point in time..
    * @return int  -It returns a number representing the SNOS number count.
    * @throws SQLException if database access operation failed.
    */
    public int SNOSCounter()
    {
        //connects to the database.
        dbconnector.databaseConnect();
        //intializes the Result set
        ResultSet rs=null;
        //this represent the snos number count in question.
        int snos_counter=0;
        try
        {
            //creates database statement to be used for database operation's execution.
            statement = dbconnector.dbconn.createStatement();
            //constructs the needed sql query.
            String query = "SELECT COUNT( * ) AS  'Rows' ,'tid' FROM  snos_table GROUP BY  tid ORDER BY  tid" ;
            //gets the Result set
            rs = statement.executeQuery(query);
            while (rs.next())
            {
                //increments since this is true.
                snos_counter++;
            }
        }
        catch ( SQLException sqlex )
        {
            //prints the error message-though this should be in a logg file or any log output
            //but is not possible at the moment because of time constraints among other reasons.
            System.out.println("The sql exception message is:\n"+sqlex.toString());
        }
        //closes all possible connection to database
        finally
        {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly(dbconnector.dbconn);
        }
        return snos_counter;
    }
    /** 
    * This method checks whether or not a particular info exits in a database table.
    * @param table String object representing the table to search the item for.
    * @param field String object representing the name of the field or column in the table.
    * @param item String object  representing the search item.
    * @return Boolean  -It returns true if it exists and false otherwise.
    * @throws SQLException if database access operation failed.
    */
    public Boolean CheckIfItemExist(String table,String field,String item)
    {
        //the value to return, defaults it to be false
        boolean IsItemExist=false;
        //connects to the database.
        dbconnector.databaseConnect();
        //intializes the Result set
        ResultSet rs=null;
        try
        {
            //creates database statement to be used for database operation's execution.
            statement = dbconnector.dbconn.createStatement();
            //constructs the needed sql query.
            String query = " select * FROM "+table+" where "+field+" ='"+item+"' " ;
            //gets the Result set
            rs = statement.executeQuery(query);
            //Is the item found?
            if(rs.next())
            {
                //assign true
                IsItemExist=true;
            }
            else
            {
                //assigns false
                IsItemExist=false;
            }
        }
        catch ( SQLException sqlex )
        {
            //prints the error message-though this should be in a logg file or any log output
            //but is not possible at the moment because of time constraints among other reasons.
            System.out.println("The sql exception message is:\n"+sqlex.toString());
        }
        //closes all possible connection to database
        finally
        {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly(dbconnector.dbconn);
        }
        //return the result.
        return IsItemExist;
    }
    /** 
    * This method retrieves a particular info in a database table.
    * @param table String object representing the table to search the item for.
    * @param field String object representing the name of the field or column in the table.
    * @param item String object  representing the search item.
    * @param Col String object  representing the column of the value to be retrieved.
    * @return Boolean  -It returns true if it exists and false otherwise.
    * @throws SQLException if database access operation failed.
    */
    public String RetrieveItem(String table,String field,String item,String Col)
    {
        //the item to return
        String Item="";
        //connects to the database.
        dbconnector.databaseConnect();
        //intializes the Result set
        ResultSet rs=null;
        try
        {
            //creates database statement to be used for database operation's execution.
            statement = dbconnector.dbconn.createStatement();
            //constructs the needed sql query.
            String query = " select * FROM "+table+" where "+field+" ='"+item+"' " ;
            //executes the constructed query and get the ResultSet
            rs = statement.executeQuery(query);
            //Is any item found?
            if(rs.next())
            {
                //then retrieve the item found in column Col.
                Item=rs.getString(Col);
            }
            else
            {
                //do nothing
            }
        }
        catch(SQLException sqlex)
        {
            //prints the error message-though this should be in a logg file or any log output
            //but is not possible at the moment because of time constraints among other reasons.
            System.out.println("The sql exception message is:\n"+sqlex.toString());
        }
        //closes all possible connection to database
        finally
        {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly(dbconnector.dbconn);
        }
        //returns the result
        return Item;
    }
    /** 
    * This method checks whether client's log in info(email and password) exists in a database table.
    * @param table String object representing the table to search the items for.
    * @param mail1 String object representing the username or email address of the client.
    * @param pass String object representing the password of the client.
    * @return Boolean  -It returns true if the client exists and false otherwise.
    * @throws SQLException if database access operation failed.
    */
    public boolean CheckClientsLoginParameters (String mail1,String pass,String table)
    {
        //connects to the database.
        dbconnector.databaseConnect();
        //intializes the Result set
        ResultSet rs=null;
        pass=getMD5(pass); //get the MD5 hash equivalent of the client's password
        //assumes the client does not exists by default.
        boolean IsRegistered=false;
        try
        {
            //creates database statement to be used for database operation's execution.
            statement = dbconnector.dbconn.createStatement();
            //constructs the needed sql query.
            String query = " select name,address from  "+table+" where email1 ='"+mail1+"' and password='"+pass+"'" ;
            //executes the constructed query and get the ResultSet
            rs = statement.executeQuery(query);
            //Is any item found?
            if(rs.next())
            {
                //assign true to default value.
                IsRegistered=true;
            }
        }
        catch (SQLException exc)
        {
            //prints the error message-though this should be in a logg file or any log output
            //but is not possible at the moment because of time constraints among other reasons.
            System.out.println("The sql exception message is:\n"+exc.toString());
            exc.printStackTrace();
        }
        //closes all possible connection to database
        finally
        {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly(dbconnector.dbconn);
        }
        //returns the result
        return IsRegistered;
    }
    /** 
    * This method deletes a client's token in a database table.
    * @param snos_type String object representing the client's SNOS number.
    * @throws SQLException if database access operation failed.
    */
    public void  DeleteUserToken(String snos_type)
    {
        //connects to the database.
        dbconnector.databaseConnect();
        try
        {
            //creates database statement to be used for database operation's execution.
            statement = dbconnector.dbconn.createStatement();
            //constructs the needed sql query.
            String query = " delete FROM tokentb where snos_type='"+snos_type+"' " ;
            //executes the constructed query.
            int executeDelete = statement.executeUpdate(query);
            //Is the delete operation successfull?
            if(executeDelete==1)
            {
                //delete is successfully, print out the result.
                System.out.println("User token Deleted");
            }
        }
        catch (SQLException exc)
        {
            //prints the error message-though this should be in a logg file or any log output
            //but is not possible at the moment because of time constraints among other reasons.
            System.out.println("The sql exception message is:\n"+exc.toString());
            exc.printStackTrace();
        }
        //closes all possible connection to database
        finally
        {
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly(dbconnector.dbconn);
        }
    }
    /** 
    * This method updates a client's registration status after each stage of registration.
    * @param snos_type String object representing the client's SNOS number.
    * @param status String object representing the status to update.
    * @throws SQLException if database access operation failed.
    */
    public void  UpdateUserStatus(String snos_type,String status)
    {
        //connects to the database.
        dbconnector.databaseConnect();
        try
        {
            //creates database statement to be used for database operation's execution.
            statement = dbconnector.dbconn.createStatement();
            //constructs the needed sql query.
            String query = "update clients_temp_tab set status='"+status+"'where snos_type='"+snos_type+"' " ;
            //executes the constructed query
            int executeUpdate = statement.executeUpdate(query);
        }
        catch (SQLException exc)
        {
            //prints the error message-though this should be in a logg file or any log output
            //but is not possible at the moment because of time constraints among other reasons.
            System.out.println("The sql exception message is:\n"+exc.toString());
            exc.printStackTrace();
        }
        //closes all possible connection to database
        finally
        {
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly(dbconnector.dbconn);
        }
    }
    /** 
    * This method updates a client's password.
    * @param snos_type String object representing the client's SNOS number.
    * @param id String object representing the client's primary key in the database table.
    * @param npassword String object representing the password to update.
    * @return Boolean  -It returns a number representing the update result.
    * @throws SQLException if database access operation failed.
    */
    public int  UpdateUserPassword(String snos_type,String id,String npassword)
    {
        //connects to the database.
        dbconnector.databaseConnect();
        //this is the default return value.
        int res=-1;
        npassword=getMD5(npassword); // get the MD5 hash equivalent of the password
        try
        {
            //creates database statement to be used for database operation's execution.
            statement = dbconnector.dbconn.createStatement();
            //constructs the needed sql query.
            String query = "update clients_tab set password='"+npassword+"'where snos_type='"+snos_type+"' and id='"+id+"' " ;
            //executes the constructed query
            int executeUpdate = statement.executeUpdate(query);
            res=executeUpdate;
            System.out.println("change pass, The update result="+res);
        }
        catch (SQLException exc)
        {
            //prints the error message-though this should be in a logg file or any log output
            //but is not possible at the moment because of time constraints among other reasons.
            System.out.println("The sql exception message is:\n"+exc.toString());
            exc.printStackTrace();
        }
        //closes all possible connection to database
        finally
        {
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly(dbconnector.dbconn);
        }
        //gets the result.
        return res;
    }
    /** 
    * This method updates a client's password.
    * @param snos_type String object representing the client's SNOS number.
    * @param tokenId String object representing the client's token id(the primary key in the tokentb table) in the database table.
    * @param npassword String object representing the password to update.
    * @return Boolean  -It returns a number representing the update result.
    * @throws SQLException if database access operation failed.
    */
    public int  UpdateUserTokenStatus(String snos_type,String tokenId,int status)
    {
        //connects to the database.
        dbconnector.databaseConnect();
        //this is the default return value.
        int res=-1;
        try
        {
            //creates database statement to be used for database operation's execution.
            statement = dbconnector.dbconn.createStatement();
            //constructs the needed sql query.
            String query = "update tokentb set status='"+status+"'where snos_type='"+snos_type+"' and id='"+tokenId+"' " ;
            //executes the constructed query
            int executeUpdate = statement.executeUpdate(query);
            res=executeUpdate;
            System.out.println("change Token status, The update result="+res);
        }
        catch (SQLException exc)
        {
            //prints the error message-though this should be in a logg file or any log output
            //but is not possible at the moment because of time constraints among other reasons.
            System.out.println("The sql exception message is:\n"+exc.toString());
            exc.printStackTrace();
        }
        //closes all possible connection to database
        finally
        {
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly(dbconnector.dbconn);
        }
        //returns the result.
        return res;
    }
    /** 
    * This method updates a client's personal registered information.
    * @param snostype String object representing the client's SNOS number.
    * @param uig UserInforGet object accessing the Get and Set class of this application.The class is instantiated before this method is called.
    * @throws SQLException if database access operation failed.
    */
    public void UpdateClientDetails(String snostype,UserInforGet uig)
    {
        //connects to the database.
        dbconnector.databaseConnect();
        try
        {
            //creates database statement to be used for database operation's execution.
            statement = dbconnector.dbconn.createStatement();
            //constructs the needed sql query.
            String query = "UPDATE clients_tab SET name='"+uig.getClient()+"',address='"+uig.getContact()+"',lga='"+uig.getLga()+"',state='"+uig.getState()+"',email1='"+uig.getClientEmail()+"'  WHERE snos_type = '" + snostype + "'" ;
            //executes the constructed query
            int res=statement.executeUpdate(query);
        }
        catch (SQLException exc)
        {
            //prints the error message-though this should be in a logg file or any log output
            //but is not possible at the moment because of time constraints among other reasons.
            System.out.println("The sql exception message is:\n"+exc.toString());
            exc.printStackTrace();
        }
        //closes all possible connections to database
        finally
        {
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly(dbconnector.dbconn);
        }
    }
    /** 
    * This method updates a client's registered device/object information.
    * @param snostype String object representing the client's SNOS number.
    * @param uig UserInforGet object accessing the Get and Set class of this application.The class is instantiated before this method is called.
    * @param id String object representing the device's table id(primary key) to update.
    * @throws SQLException if database access operation failed.
    */
    public void UpdateDeviceDetails(String snostype, UserInforGet uig, String id)
    {
        //connects to the database.
        dbconnector.databaseConnect();
        try
        {
            //creates database statement to be used for database operation's execution.
            statement = dbconnector.dbconn.createStatement();
            //constructs the needed sql query.
            String query = "UPDATE clients_device_tab SET name='"+uig.getClient()+"',descrpt='"+uig.getDecrpt()+"', housing_type='"+uig.getBuild()
            +"', housing_location='"+uig.getLoc()+"',address='"+uig.getContact()+"',lga='"+uig.getLga()+"',state='"+uig.getState()
            +"'  WHERE snos_type = '" + snostype + "' and id='" + id + "'" ;
            //executes the constructed query
            int res=statement.executeUpdate(query);
        }
        catch (SQLException exc)
        {
            //prints the error message-though this should be in a logg file or any log output
            //but is not possible at the moment because of time constraints among other reasons.
            System.out.println("The sql exception message is:\n"+exc.toString());
            exc.printStackTrace();
        }
        //closes all possible connections to database
        finally
        {
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly(dbconnector.dbconn);
        }
    }
    /** 
    * This method updates a client's registered contacts information.
    * @param snostype String object representing the client's SNOS number.
    * @param uig UserInforGet object accessing the Get and Set class of this application.The class is instantiated before this method is called.
    * @param id String object representing the contact's table id(primary key) to update.
    * @throws SQLException if database access operation failed.
    */
    public void UpdateClientsContactsDetails(String snostype, UserInforGet uig, String id)
    {
        //connects to the database.
        dbconnector.databaseConnect();
        try
        {
            //creates database statement to be used for database operation's execution.
            statement = dbconnector.dbconn.createStatement();
            //constructs the needed sql query.
            String query = "UPDATE clients_contacts_tab SET name='"+uig.getClient()+"',relation_position='"+uig.getRelation_Position()+"', email1='"+uig.getClientEmail()
            +"', gsm='"+uig.getFone()+"',address='"+uig.getContact()+"' WHERE snos_type = '" + snostype + "' and contacts_id='" + id + "'" ;
            //executes the constructed query
            int res=statement.executeUpdate(query);
        }
        catch (SQLException exc)
        {
            //prints the error message-though this should be in a logg file or any log output
            //but is not possible at the moment because of time constraints among other reasons.
            System.out.println("The sql exception message is:\n"+exc.toString());
            exc.printStackTrace();
        }
        //closes all possible connections to database
        finally
        {
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly(dbconnector.dbconn);
        }
    }
    /** 
    * This method retrieves a client's registered personal information.
    * @param snostype String object representing the client's SNOS number.
    * @param table String object representing the name of the client's registration(temporal or permanent) table in the database.
    * @param sessionid String object representing the client's session id as stored in client's temporal registration table.
    * @return UserInforGet  -It returns an object of get. and set class
    * @throws SQLException if database access operation failed.
    */
     public UserInforGet getClientDetails(String snostype, String sessionid, String table)
     {
         //get the object of get and set class to store the retrieved values.
         getuser = new UserInforGet();
         ResultSet rs=null;
         //connects to the database.
         dbconnector.databaseConnect();
         try
         {
             //creates database statement to be used for database operation's execution.
             statement = dbconnector.dbconn.createStatement();
             //constructs the needed sql query.
             String query = "SELECT * FROM "+table+" WHERE snos_type = '" + snostype + "' or sessid='" + sessionid + "'" ;
             //gets the ResultSet
             rs = statement.executeQuery(query);
             //Is any item found?
             if(rs.next())
             {
                 //get all the required values from their fields
                 //and store in the get and set object.
                 getuser.SetSensor(rs.getString(2));
                 getuser.SetFone(rs.getString (3));
                 getuser.SetClient(rs.getString (4));
                 getuser.SetContact(rs.getString (5));
                 getuser.SetLga(rs.getString(6));
                 //getuser.SetLoc(rs.getString (7));
                 getuser.SetState(rs.getString (7));
                 getuser.SetClientEmail(rs.getString (8));
                 //Is this the permanent registration table?
                 //this is needed for comfirmation of registration
                 if(table.equals("clients_tab"))
                 {
                     //do nothing
                 }
                 else
                 {
                     //then retrieve the password field from the temporal registration table
                     //this is needed for comfirmation of registration
                     getuser.SetPass(rs.getString (9));
                 }
             }
             
             
         }
         catch (SQLException exc)
         {
             //prints the error message-though this should be in a logg file or any log output
             //but is not possible at the moment because of time constraints among other reasons.
             System.out.println("The sql exception message is:\n"+exc.toString());
             exc.printStackTrace();
         }
         //closes all possible connections to database
         finally
         {
             DbUtils.closeQuietly(rs);
             DbUtils.closeQuietly(statement);
             DbUtils.closeQuietly(dbconnector.dbconn);
         }
         //returns the get and set object.
         return getuser;
     }
     /** 
    * This method retrieves a client's registered personal information.
    * @param snostype String object representing the client's SNOS number.
    * @param table String object representing the name of the client's registration(temporal or permanent) table in the database.
    * @return UserInforGet  -It returns an object of get. and set class
    * @throws SQLException if database access operation failed.
    */
     public UserInforGet getClientDetails1(String snostype, String table)
     {
         //get the object of get and set class to store the retrieved values.
         getuser = new UserInforGet();
         ResultSet rs=null;
         //connects to the database.
         dbconnector.databaseConnect();
         try
         {
             //creates database statement to be used for database operation's execution.
             statement = dbconnector.dbconn.createStatement();
             //constructs the needed sql query.
             String query = "SELECT * FROM "+table+" WHERE snos_type = '" + snostype + "'" ;
             //gets the ResultSet
             rs = statement.executeQuery(query);
             //Is any item found?
             if(rs.next())
             {
                 //get all the required values from their fields
                 //and store them in the get and set object-to be accessed later.
                 getuser.SetSensor(rs.getString(2));
                 getuser.SetFone(rs.getString (3));
                 getuser.SetClient(rs.getString (4));
                 getuser.SetContact(rs.getString (5));
                 getuser.SetLga(rs.getString(6));
                 getuser.SetState(rs.getString (7));
                 getuser.SetClientEmail(rs.getString (8));
             }
         }
         catch (SQLException exc)
         {
             //prints the error message-though this should be in a logg file or any log output
             //but is not possible at the moment because of time constraints among other reasons.
             System.out.println("The sql exception message is:\n"+exc.toString());
             exc.printStackTrace();
         }
         //closes all possible connections to database
         finally
         {
             DbUtils.closeQuietly(rs);
             DbUtils.closeQuietly(statement);
             DbUtils.closeQuietly(dbconnector.dbconn);
         }
         //returns the get and set object.
         return getuser;
     }
     /** 
    * This method retrieves a client's registered contacts' data.
    * @param snostype String object representing the client's SNOS number.
    * @param table String object representing the name of the client's registration(temporal or permanent) table in the database.
    * @return Vector  -It returns an object of Vector called row containing the retrieved contacts' data.
    * @throws SQLException if database access operation failed.
    */
    public Vector getClientContactsDetails(String snostype, String table)
    {
        //get the object of get and set class to store the retrieved values.
        getuser = new UserInforGet();
        ResultSet rs=null;
        //connects to the database.
        dbconnector.databaseConnect();
        //get a Vector object
        row = new Vector ();
        try
        {
            //creates database statement to be used for database operation's execution.
            statement = dbconnector.dbconn.createStatement();
            //constructs the needed sql query.
            String query = "SELECT * FROM "+table+" WHERE snos_type = '" + snostype + "'" ;
            //gets the ResultSet
            rs = statement.executeQuery(query);
            //Is any item found?
            while(rs.next())
            {
                //get all the required values from their fields
                //and store them in the get and set object-to be accessed later.
                getuser.SetClient(rs.getString (3));
                getuser.SetRelation_Position(rs.getString (4));
                getuser.SetContact(rs.getString (5));
                getuser.SetClientEmail(rs.getString (6));
                getuser.SetFone(rs.getString (7));
                //Is this the permanent clients contacts table?
                //this is needed for comfirmation of registration
                if(table.equals("clients_contacts_temp_tab"))
                {
                    //then retrieve the password field from the temporal registration table
                    //this is needed for comfirmation of registration
                    getuser.SetSensor(rs.getString(2));
                    record=new String[] {getuser.getClient(),getuser.getRelation_Position(),getuser.getContact(),getuser.getClientEmail(),getuser.getFone()};
                }
                else
                {
                    record=new String[] {getuser.getClient(),getuser.getRelation_Position(),getuser.getContact(),getuser.getClientEmail(),getuser.getFone(),rs.getString(1)};
                }
                //Add and store the record in a vector object
                row.add(record);
            }
        }
        catch (SQLException exc)
        {
            //prints the error message-though this should be in a logg file or any log output
            //but is not possible at the moment because of time constraints among other reasons.
            System.out.println("The sql exception message is:\n"+exc.toString());
            exc.printStackTrace();
        }
        //closes all possible connections to database.
        finally
        {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly(dbconnector.dbconn);
        }
        //gets the result as row object.
        return row;
    }
    /** 
    * This method retrieves a client's registered personal information.
    * @param snostype String object representing the client's SNOS number.
    * @param table String object representing the name of the client's registration(temporal or permanent) table in the database.
    * @return Vector  -It returns an object of Vector called row containing the retrieved contacts' data.
    * @throws SQLException if database access operation failed.
    */
    public Vector getClientDeviceDetails(String snostype, String table)
    {
        //get the object of get and set class to store the retrieved values.
        getuser = new UserInforGet();
        ResultSet rs=null;
        //connects to the database.
        dbconnector.databaseConnect();
        //get a Vector object
        row = new Vector ();
        try
        {
            //creates database statement to be used for database operation's execution.
            statement = dbconnector.dbconn.createStatement();
            //constructs the needed sql query.
            String query = "SELECT * FROM "+table+" WHERE snos_type = '" + snostype + "'" ;
            //gets the ResultSet
            rs = statement.executeQuery(query);
            //Is any item found?
            while(rs.next())
            {
                //get all the required values from their fields
                //and store them in the get and set object-to be accessed later.
                getuser.SetClient(rs.getString (3));
                getuser.SetDescrpt(rs.getString (4));
                getuser.SetBuild(rs.getString (5));
                getuser.SetLoc(rs.getString (6));
                getuser.SetContact(rs.getString (7));
                getuser.SetLga(rs.getString (8));
                getuser.SetState(rs.getString (9));
                //Access the get and set object store the retrived items in array of record
                record=new String[] {getuser.getClient(),getuser.getDecrpt(),getuser.getBuild(),getuser.getLoc(),getuser.getContact(),getuser.getLga(),getuser.getState(),rs.getString(1)};
                //Add and store the record in a vector object
                row.add(record);
           }
        }
        catch (SQLException exc)
        {
            //prints the error message-though this should be in a logg file or any log output
            //but is not possible at the moment because of time constraints among other reasons.
            System.out.println("The sql exception message is:\n"+exc.toString());
            exc.printStackTrace();
        }
        //closes all possible connections to database.
        finally
        {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly(dbconnector.dbconn);
        }
        //gets the result as row object.
        return row;
    }
    /** 
    * This method counts and retrieves the total number of sms alerts belonging to a client.
    * @param snostype String object representing the client's SNOS number.
    * @return int  -It returns an int representing the total number of sms alerts.
    * @throws SQLException if database access operation failed.
    */
    public int querysmsListTotalCount(String snostype)
    {
        //intialize the counter to 0.
        int num=0;
        //Declare and initialize the ResultSet object
        ResultSet rs=null;
        //connects to the database.
        dbconnector.databaseConnect();
        try
        {
            //creates database statement to be used for database operation's execution.
            statement = dbconnector.dbconn.createStatement();
            //constructs the needed sql query.
            String query = "SELECT *  FROM sms_in where snos_type='"+snostype+"'" ;
            //gets the ResultSet
            rs = statement.executeQuery(query);
            //Is any item found?
            while (rs.next())
            {
                //Then increments the counter
                num ++;
            }
        }
        catch (SQLException exc)
        {
            //prints the error message-though this should be in a logg file or any log output
            //but is not possible at the moment because of time constraints among other reasons.
            System.out.println("The sql exception message is:\n"+exc.toString());
            exc.printStackTrace();
        }
        //closes all possible connections to database.
        finally
        {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly(dbconnector.dbconn);
        }
        //gets the result as the counter.
        return num;
    }
    /** 
    * This method counts and retrieves the total number of sms alerts-AFTER A PARTICULAR PERIOD OF TIME- belonging to a client.
    * @param snostype String object representing the client's SNOS number.
    * @param time String object representing the PARTICULAR PERIOD OF TIME to search from.
    * @return int  -It returns an int representing the total number of sms alerts after that period of time.
    * @throws SQLException if database access operation failed.
    */
    public int getAllsmsListAfterAParticularPeriodTotalCount(String snostype,String time)
    {
        //intialize the counter to 0.
        int num=0;
        //Declare and initialize the ResultSet object
        ResultSet rs=null;
        //connects to the database.
        dbconnector.databaseConnect();
        try
        {
            //creates database statement to be used for database operation's execution.
            statement = dbconnector.dbconn.createStatement();
            //constructs the needed sql query
            String query = "SELECT *  FROM sms_in where snos_type='"+snostype+"' AND re_time >'"+time+"'  ORDER BY re_time  DESC";
            //gets the ResultSet
            rs = statement.executeQuery(query);
            //Is any item found?
            while (rs.next())
            {
                //Then increments the counter
                num ++;
            }
        }
        catch (SQLException exc)
        {
            //prints the error message-though this should be in a logg file or any log output
            //but is not possible at the moment because of time constraints among other reasons.
            System.out.println("The sql exception message is:\n"+exc.toString());
            exc.printStackTrace();
        }
        //closes all possible connections to database.
        finally
        {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly(dbconnector.dbconn);
        }
        //gets the result as the counter.
        return num;
    }
    /** 
    * This method  retrieves the sms alerts-DEPENDING ON A MINIMUM AND MAX LIMIT- belonging to a client.
    * @param snostype String object representing the client's SNOS number.
    * @param min int representing the minimum limit.
    * @param max int representing the maximum limit to return from database table-This is 10 as of the time of writing this javadoc.
    * @return Vector  -It contains the retrieved alerts with their corresponding dates/time..
    * @throws SQLException if database access operation failed.
    */
    public Vector querysmsList(String snostype, int min, int max)
    {
        
        //Declare and initialize the ResultSet object
        ResultSet rs=null;
        //connects to the database.
        dbconnector.databaseConnect();
        //get a Vector object
        row = new Vector ();
        int num=0;
        try
        {
            //creates database statement to be used for database operation's execution.
            statement = dbconnector.dbconn.createStatement();
            //constructs the needed sql query
            String query = "SELECT *  FROM sms_in where snos_type='"+snostype+"' ORDER BY re_time  DESC LIMIT "+min+","+max;
            //gets the ResultSet
            rs = statement.executeQuery(query);
            
            //Is any item found?
            while (rs.next())
            {
                //gets the SNOS number, the sms text and the time of arrival.
                
                String Sender = rs.getString (2); 
                String Tex = rs.getString (3).toUpperCase();
                String date1 =rs.getString ("re_time");
                //put them in array of string called record
                record=new String[] {Sender,Tex,date1};
                //add the record to a Vector object.
                row.addElement(record);
                System.out.println("The alert at "+num+" is "+Tex);
                num ++;
            }
            //assign the alert counter.
            sms_current_count=num;
            //statically set the counter
            SmsCountSetter.setSmscount(sms_current_count);
            System.out.println("The sms count is "+sms_current_count);
            //gets the total sms alerts
            sms_total_count=this.querysmsListTotalCount(snostype);
            System.out.println("The Total sms count is "+sms_total_count);
            System.out.println("The Size of the row is "+row.size());
        }
        catch (SQLException exc)
        {
            //prints the error message-though this should be in a logg file or any log output
            //but is not possible at the moment because of time constraints among other reasons.
            System.out.println("The sql exception message is:\n"+exc.toString());
            exc.printStackTrace();
        }
        //closes all possible connections to database.
        finally
        {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly(dbconnector.dbconn);
        }
        //gets the result as the row.
        return row;
    }
    /** 
    * This method  retrieves the sms alerts-DEPENDING ON A MINIMUM AND MAX LIMIT- belonging to a client.
    * @param snostype String object representing the client's SNOS number.
    * @param id int representing the sms primary key id in the table.
    * @return String  -It contains the array of the retrieved alert with the corresponding date/time..
    * @throws SQLException if database access operation failed.
    */
    public String [] query_One_Single_Sms_Alert(String snostype, int id)
    {
        
        //Declare and initialize the ResultSet object
        ResultSet rs=null;
        //connects to the database.
        dbconnector.databaseConnect();
        int num=0;
        try
        {
            //creates database statement to be used for database operation's execution.
            statement = dbconnector.dbconn.createStatement();
            //constructs the needed sql query
            String query = "SELECT msg, re_time  FROM sms_in where snos_type='"+snostype+"' and id='"+id+"' ";
            //gets the ResultSet
            rs = statement.executeQuery(query);
            
            //Is any item found?
            if(rs.next())
            {
                //gets  the sms text and the time of arrival.
                String Tex = rs.getString ("msg").toUpperCase();
                String date1 =rs.getString ("re_time");
                //put them in array of string called record
                record=new String[] {snostype,Tex,date1};
                //add the record to a Vector object.
                row.addElement(record);
                System.out.println("The alert at "+num+" is "+Tex);
            }
            
        }
        catch (SQLException exc)
        {
            //prints the error message-though this should be in a logg file or any log output
            //but is not possible at the moment because of time constraints among other reasons.
            System.out.println("The sql exception message is:\n"+exc.toString());
            exc.printStackTrace();
        }
        //closes all possible connections to database.
        finally
        {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly(dbconnector.dbconn);
        }
        //gets the result as the row.
        return record;
    }
    /** 
    * This method  retrieves ALL the sms alerts belonging to a client.
    * @param snostype String object representing the client's SNOS number.
    * @return Vector  -It contains the retrieved alerts with their corresponding dates/time..
    * @throws SQLException if database access operation failed.
    */
    public Vector getAllsmsList(String snostype)
    {
        //Declare and initialize the ResultSet object
        ResultSet rs=null;
        //get a Vector object
        row = new Vector ();
        //connects to the database.
        dbconnector.databaseConnect();
        int num=0;
        try
        {
            //creates database statement to be used for database operation's execution.
            statement = dbconnector.dbconn.createStatement();
            //constructs the needed sql query
            String query = "SELECT *  FROM sms_in where snos_type='"+snostype+"' ORDER BY re_time  DESC";
            //gets the ResultSet
            rs = statement.executeQuery(query);
            //Is any item found?
            while (rs.next())
            {
                //gets the SNOS number, the sms text and the time of arrival.
                String Sender = rs.getString (2); 
                String Tex = rs.getString (3).toUpperCase();
                String date1 =rs.getString ("re_time");
                //put them in array of string called record
                record=new String[] {Sender,Tex,date1};
                //add the record to a Vector object.
                row.addElement(record);
                System.out.println("The Size of the row HERE is "+row.size());
                //increments the counter
                num ++;
            }
            //assign the alert counter.
            sms_current_count=num;
            //statically set the counter
            SmsCountSetter.setSmscount(sms_current_count);
            System.out.println("The sms count is "+sms_current_count);
            sms_total_count=this.querysmsListTotalCount(snostype);
            System.out.println("The Total sms count is "+sms_total_count);
        }
        catch (SQLException exc)
        {
            //prints the error message-though this should be in a logg file or any log output
            //but is not possible at the moment because of time constraints among other reasons.
            System.out.println("The sql exception message is:\n"+exc.toString());
            exc.printStackTrace();
        }
        //closes all possible connections to database.
        finally
        {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly(dbconnector.dbconn);
        }
        //gets the result as the row.
        return row;
    }
    
    
    /** 
    * This method  retrieves ALL the sms alerts belonging to a client.
    * @param snostype String object representing the client's SNOS number.
    * @return Vector  -It contains the retrieved alerts with their corresponding dates/time..
    * @throws SQLException if database access operation failed.
    */
    public String [] getAllsmsIDList(String snostype)
    {
        //Declare and initialize the ResultSet object
        ResultSet rs=null;
        sms_total_count=this.querysmsListTotalCount(snostype);
        String[] totalSmsIds=new String[sms_total_count];

        //get a Vector object
        row = new Vector ();
        //connects to the database.
        dbconnector.databaseConnect();
        int num=0;
                try
        {
            //creates database statement to be used for database operation's execution.
            statement = dbconnector.dbconn.createStatement();
            //constructs the needed sql query
            String query = "SELECT *  FROM sms_in where snos_type='"+snostype+"' ORDER BY re_time  DESC";
            //gets the ResultSet
            rs = statement.executeQuery(query);
            //Is any item found?
            while (rs.next())
            {
                //gets the sms id number
                totalSmsIds[num]=rs.getString(1);
                
                System.out.println("The Size of the smsIds is:: "+totalSmsIds.length);
                //increments the counter
                num ++;
            }
            
            System.out.println("The Total sms count is "+sms_total_count);
        }
        catch (SQLException exc)
        {
            //prints the error message-though this should be in a logg file or any log output
            //but is not possible at the moment because of time constraints among other reasons.
            System.out.println("The sql exception message is:\n"+exc.toString());
            exc.printStackTrace();
        }
        //closes all possible connections to database.
        finally
        {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly(dbconnector.dbconn);
        }
        //gets the result as the row.
        return totalSmsIds;
    }
    /** 
    * This method retrieves all the sms alerts-AFTER A PARTICULAR PERIOD OF TIME USING AN ID- belonging to a client.
    * @param snostype String object representing the client's SNOS number.
    * @param last_id String object representing the table column ID to search from.
    * @return Vector  -It returns an Vector object containing all the sms alerts after that period of time.
    * @throws SQLException if database access operation failed.
    */
    public Vector getAllsmsListAfterAParticularPeriod_Id(String snostype,String last_id)
    {
        
        //Declare and initialize the ResultSet object
        ResultSet rs=null;
        //get a Vector object
        row = new Vector ();
        //connects to the database.
        dbconnector.databaseConnect();
        int num=0;
        try
        {
            //creates database statement to be used for database operation's execution.
            if(dbconnector.dbconn.isClosed())
            {
                System.out.println("The CONN IS CLOSED");
            }
            else
            {
                System.out.println("The CONN IS OPENED");
            }
            statement = dbconnector.dbconn.createStatement();
            //constructs the needed sql query
            System.out.println("The last iD before sql query==="+last_id);
            String query = "SELECT *  FROM sms_in where snos_type='"+snostype+"' AND id >'"+last_id+"'  ";//ORDER BY re_time  DESC
            //gets the ResultSet
            rs = statement.executeQuery(query);
            //Is any item found?
            while (rs.next())
            {
                //gets the SNOS number, the sms text and the time of arrival.
                String Sender = rs.getString (2); 
                String Tex = rs.getString (3).toUpperCase();
                String date1 =rs.getString ("re_time");
                //put them in array of string called record
                record=new String[] {Sender,Tex,date1};
                //add the record to a Vector object.
                row.addElement(record);
                //increments the counter
                num ++;
            }
           
            //assign the alert counter.
            sms_current_count=num;
            //statically set the counter
            SmsCountSetter.setSmscount(sms_current_count);
            System.out.println("The sms count is "+sms_current_count);
            //totalcount=this.getAllsmsListAfterAParticularPeriodTotalCount(snos,time);
            //System.out.println("The Total sms count is "+totalcount);
        }
        catch (SQLException exc)
        {
            //prints the error message-though this should be in a logg file or any log output
            //but is not possible at the moment because of time constraints among other reasons.
            System.out.println("The sql exception message is:\n"+exc.toString());
            exc.printStackTrace();
        }
        //closes all possible connections to database.
        finally
        {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly(dbconnector.dbconn);
        }
        //gets the result as the row.
        return row;
    }
    /** 
    * This method retrieves all the sms alerts-AFTER A PARTICULAR PERIOD OF TIME- belonging to a client.
    * @param snostype String object representing the client's SNOS number.
    * @param time String object representing the PARTICULAR PERIOD OF TIME to search from.
    * @return Vector  -It returns an Vector object containing all the sms alerts after that period of time.
    * @throws SQLException if database access operation failed.
    */
    public Vector getAllsmsListAfterAParticularPeriod(String snos,String time)
    {
        //Declare and initialize the ResultSet object
        ResultSet rs=null;
        //get a Vector object
        row = new Vector ();
        //connects to the database.
        dbconnector.databaseConnect();
        int num=0;
        try
        {
            //creates database statement to be used for database operation's execution.
            if(dbconnector.dbconn.isClosed())
            {
                System.out.println("The CONN IS CLOSED");
            }
            else
            {
                System.out.println("The CONN IS OPENED");
            }
            statement = dbconnector.dbconn.createStatement();
            //constructs the needed sql query
            System.out.println("The last TIME before sql query==="+time);
            String query = "SELECT *  FROM sms_in where snos_type='"+snos+"' AND re_time >'"+time+"' ORDER BY re_time  DESC  ";//ORDER BY re_time  DESC
            //gets the ResultSet
            rs = statement.executeQuery(query);
            //Is any item found?
            while (rs.next())
            {
                //gets the SNOS number, the sms text and the time of arrival.
                String Sender = rs.getString (2); 
                String Tex = rs.getString (3).toUpperCase();
                String date1 =rs.getString ("re_time");
                //put them in array of string called record
                record=new String[] {Sender,Tex,date1};
                //add the record to a Vector object.
                row.addElement(record);
                System.out.println("The Size of the row HERE is "+row.size());
                //increments the counter
                num ++;
            }
            //assign the alert counter.
            sms_current_count=num;
            //statically set the counter
            SmsCountSetter.setSmscount(sms_current_count);
            System.out.println("The sms count is "+sms_current_count);
            //totalcount=this.getAllsmsListAfterAParticularPeriodTotalCount(snos,time);
            //System.out.println("The Total sms count is "+totalcount);
        }
        catch (SQLException exc)
        {
            //prints the error message-though this should be in a logg file or any log output
            //but is not possible at the moment because of time constraints among other reasons.
            System.out.println("The sql exception message is:\n"+exc.toString());
            exc.printStackTrace();
        }
        //closes all possible connections to database.
        finally
        {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly(dbconnector.dbconn);
        }
        //gets the result as the row.
        return row;
    }
    /** 
    * This method retrieves the number of alerts search marches found for a particular search item.
    * @param snostype String object representing the client's SNOS number.
    * @param para String object representing the PARTICULAR ITEM to search for.
    * @param type Boolean value indicating whether the client wants to search by sms text/message(true) or by date(false).
    * @return int  -It returns an int indicating the number of items found in the search.
    * @throws SQLException if database access operation failed.
    */
    public int getsearchsmsListNumber(String snostype,String para,boolean type)
    {
        //declares the database field to search in
        String field="";
        //How does the client want to search? true=by text/message while false=by date/time 
        if(type)
        {
            //then search the message field in the db table
            field="msg";
        }
        else
        {
            //then search the date/time field in the db table
            field="re_time";
        }
      //Declare and initialize the ResultSet object
        ResultSet rs=null;
        //connects to the database.
        dbconnector.databaseConnect();
        int num=0;
        try
        {
            //creates database statement to be used for database operation's execution.
            statement = dbconnector.dbconn.createStatement();
            //constructs the needed sql query
            String query = "SELECT * FROM sms_in where snos_type='"+snostype+"' AND "+field+" like'%"+para+"%' ORDER BY re_time  ASC";
            //gets the ResultSet
            rs = statement.executeQuery(query);
            //Is any item found?
            while (rs.next())
            {
                //then increments the counter
                num ++;
            }
        }
        catch (SQLException exc)
        {
            //prints the error message-though this should be in a logg file or any log output
            //but is not possible at the moment because of time constraints among other reasons.
            System.out.println("The sql exception message is:\n"+exc.toString());
            exc.printStackTrace();
        }
        //closes all possible connections to database.
        finally
        {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly(dbconnector.dbconn);
        }
        //gets the result as the counter.
        return num;
    }
    /** 
    * This method  searches and retrieves the sms alerts-DEPENDING ON A MINIMUM AND MAX LIMIT- belonging to a client.
    * @param snostype String object representing the client's SNOS number.
    * @param min int representing the minimum limit.
    * @param max int representing the maximum limit to return from database table-This is 10 as of the time of writing this javadoc.
    * @param para String object representing the PARTICULAR ITEM to search for.
    * @param type Boolean value indicating whether the client wants to search by sms text/message(true) or by date(false).
    * @return Vector  -It contains the marched alerts with their corresponding dates/time..
    * @throws SQLException if database access operation failed.
    */
    public Vector searchsmsList(String snostype,String para,int min,int max,boolean type)
    {
        //Declare and initialize the ResultSet object
        ResultSet rs=null;
        //connects to the database.
        dbconnector.databaseConnect();
        int num=0;
        row = new Vector ();
        //declares the database field to search in
        String field="";
        //How does the client want to search? true=by text/message while false=by date/time 
        if(type)
        {
            //then search the message field in the db table
            field="msg";
        }
        else
        {
            //then search the date/time field in the db table
            field="re_time";
        }
       try
       {
           //creates database statement to be used for database operation's execution.
           statement = dbconnector.dbconn.createStatement();
           //constructs the needed sql query
           String query = "SELECT * FROM sms_in where snos_type='"+snostype+"' AND "+field+" like'%"+para+"%' ORDER BY re_time  ASC LIMIT "+min+","+max;
           //gets the ResultSet
           rs = statement.executeQuery(query);
           //Is any item found?
            while (rs.next())
            {
                //gets the SNOS number, the sms text and the time of arrival.
                String Sender = rs.getString (2); 
                String Tex = rs.getString (3).toUpperCase();
                String date1 =rs.getString ("re_time");
                //put them in array of string called record
                record=new String[] {Sender,Tex,date1};
                //add the record to a Vector object.
                row.addElement(record);
                System.out.println("The Size of the row HERE is "+row.size());
                //increments the counter
                num ++;
            }
            //assigns the alert counter.
            sms_current_count=num;
            //statically set the counter
            SmsCountSetter.setSmscount(sms_current_count);
            System.out.println("The sms count is "+sms_current_count);
            sms_total_count=this.getsearchsmsListNumber(snostype,para,type);
            System.out.println("The Total sms count is "+sms_total_count);
       }
       catch (SQLException exc)
       {
           //prints the error message-though this should be in a logg file or any log output
           //but is not possible at the moment because of time constraints among other reasons.
           System.out.println("The sql exception message is:\n"+exc.toString());
           exc.printStackTrace();
       }
        //closes all possible connections to database.
        finally
        {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly(dbconnector.dbconn);
        }
        //gets the result as a Vector row object.
        return row;
    }
    /** 
    * This method retrieves the lists of all the ACTIONS TAKEN FOR A PARTICULAR CLIENT sms alerts.
    * @param snostype String object representing the client's SNOS number.
    * @return Vector  -It contains the actions taken with their corresponding details..
    * @throws SQLException if database access operation failed.
    */
    public Vector queryActionsTaken(String snostype)
    {
        //Declare and initialize the ResultSet object
        ResultSet rs=null;
        //connects to the database.
        dbconnector.databaseConnect();
        int num=0;
        row = new Vector ();
        try
        {
            //creates database statement to be used for database operation's execution.
            statement = dbconnector.dbconn.createStatement();
            //constructs the needed sql query
            String query = "SELECT a.snos_type,a.action_type,a.sensor,a.text_sent,a.sms_date,b.name,b.relation_position," +
            "b.address,b.email1,b.gsm FROM action_tab a,clients_contacts_tab b " +
            "where a.snos_type='"+snostype+"' and b.snos_type='"+snostype+"'";
            //gets the ResultSet
            rs = statement.executeQuery(query);
            //Is any item found?
            while (rs.next())
            {
                //then get the needed actions taken details
                record=new String[] {rs.getString("sensor"),rs.getString("action_type"),rs.getString("name"),
                rs.getString("relation_position"),rs.getString("address"),rs.getString("gsm"),rs.getString("email1"),rs.getString("text_sent"),
                rs.getString("date1")};
                //add to row as usual
                row.addElement(record);
            }
        }
        catch (SQLException exc)
       {
           //prints the error message-though this should be in a logg file or any log output
           //but is not possible at the moment because of time constraints among other reasons.
           System.out.println("The sql exception message is:\n"+exc.toString());
           exc.printStackTrace();
       }
       //closes all possible connections to database.
       finally
       {
           DbUtils.closeQuietly(rs);
           DbUtils.closeQuietly(statement);
           DbUtils.closeQuietly(dbconnector.dbconn);
       }
       //gets the result as a Vector row object.
       return row;
    }
    /** 
    * This method statically gets the sms current count at each point in time 
    * @return int  -It represents the sms current count in question.
    */
    public static int getSmsCurrentCount()
    {
        System.out.println("The current sms count is "+sms_current_count);
        return sms_current_count;
        
    }
    /** 
    * This method gets the sms total count at each point in time 
    * @return int  -It represents the sms total count in question.
    */
    public  int getSmsTotalCount(String snostype)
    {
        sms_total_count=this.querysmsListTotalCount(snostype);
        System.out.println("The Total sms count is "+sms_total_count);
        return sms_total_count;
    }
    /** 
    * This method statically gets the sms total count at each point in time 
    * @return int  -It represents the sms total count in question.
    */
    public  static int getSmsTotalCount()
    {
        return sms_total_count;
    }
    /** 
    * This method statically gets the sms total count at each point in time 
    * @return int  -It represents the sms total count in question.
    */
    public   int getSmsLast_Id(String snostype)
    {
        //get the array of  all the id columns for sms table.
        String idArrays[]=getAllsmsIDList(snostype);
        System.out.println("The ARRAY IDS LENT==="+idArrays.length);
        //sort the array
        Arrays.sort(idArrays);
        last_Id=Integer.parseInt(idArrays[idArrays.length-1]);
        for(int i=0;i<idArrays.length;i++)
           {
               System.out.println("The sorted id of the idArray at "+i+" is:: "+idArrays[i]);
           }
        System.out.println("THE MAX ID=="+last_Id);
        return last_Id;
    }
    /** 
    * This method statically gets the sms total count at each point in time 
    * @return int  -It represents the sms total count in question.
    */
    public  static int getSmsLast_Id()
    {
        return last_Id;
    }
    //***************************************************************************************
    // error Checking methods for different things are below
    //**************************************************************************************
    /** 
    * This method checks whether a particular SNOS number-belonging to a client- exists.
    * @param snostype String object representing the client's SNOS number.
    * @return Boolean  -It represents true or false depending on whether the SNOS exists or not.
    * @throws SQLException if database access operation failed.
    */
    public boolean CheckExistingSNOS(String snostype)
    {
        //Declare and initialize the ResultSet object
        ResultSet rs=null;
        //connects to the database.
        dbconnector.databaseConnect();
        try
        {
            //creates database statement to be used for database operation's execution.
            statement = dbconnector.dbconn.createStatement();
            //constructs the needed sql query
            String query = "SELECT * FROM clients_tab WHERE snos_type = '" +
            snostype + "'" ;
            //gets the ResultSet
            rs = statement.executeQuery(query);
            //Is any item found?
            if(rs.next())
            {
                return true;
            }
            return false;
        }
        catch (SQLException exc)
        {
           //prints the error message-though this should be in a logg file or any log output
           //but is not possible at the moment because of time constraints among other reasons.
           System.out.println("The sql exception message is:\n"+exc.toString());
           exc.printStackTrace();
           return false;
        }
        //closes all possible connections to database.
       finally
       {
           DbUtils.closeQuietly(rs);
           DbUtils.closeQuietly(statement);
           DbUtils.closeQuietly(dbconnector.dbconn);
       }
    }
    /** 
    * This method checks whether a particular web administrator exists.
    * @param userid String object representing the administrator's user id.
    * @return Boolean  -It represents true or false depending on whether the administrator exists or not.
    * @throws SQLException if database access operation failed.
    */
    public boolean CheckExistingAdministrator(String userid)
    {
        //Declare and initialize the ResultSet object
        ResultSet rs=null;
        //connects to the database.
        dbconnector.databaseConnect();
        try
        {
            //creates database statement to be used for database operation's execution.
            statement = dbconnector.dbconn.createStatement();
            //constructs the needed sql query
            String query = "SELECT fone FROM snos_table WHERE fone = '" +
            userid + "'" ;
            //gets the ResultSet
            rs = statement.executeQuery(query);
            //Is any item found?
            if(rs.next())
            {
                return true;
            }
            return false;
        }
        catch (SQLException exc)
        {
           //prints the error message-though this should be in a logg file or any log output
           //but is not possible at the moment because of time constraints among other reasons.
           System.out.println("The sql exception message is:\n"+exc.toString());
           exc.printStackTrace();
           return false;
        }
        //closes all possible connections to database.
       finally
       {
           DbUtils.closeQuietly(rs);
           DbUtils.closeQuietly(statement);
           DbUtils.closeQuietly(dbconnector.dbconn);
       }
        
    }
    /** 
    * This method statically gets the an object of a Vector called row at each point in time 
    * @return Vector  -It represents the object of the Vector in question.
    */
    public Vector getRows ()
    {
        return row;
    }  
      
     
}
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package snossoftwaretest;

import java.util.Vector;
/**
* This class encapsulates and defines ALL the "gets" and "sets"
* AND the properties needed by the application to BUILD JSON FORMAT-to send to mobile devices via web service.
* @param sms String object that represents the alert/sms message text.
* @param date String object that represents the alert/sms arrival date/time.
* @param id String object that represents the client's SNOS number(client identification key).
* @param name String object that represents the client's name.
* @param client_name String object that represents the client's name.
* @param loc String object that represents the client's location.
* @param descrpt String object that represents the client's description field-found especially in 2nd stage of registration.
* @param build_type String object that represents the client's building type(like bungalow, duplex,etc).
* @param relat_pos String object that represents relationship or position of the client's contact person.
* @param fone String object that represents the client's phone.
* @param Client_email String object that represents the client's email address.
* @param contact String object that represents the client's contact address.
* @param lga String object that represents the client's local government area.
* @param uid String object that represents the particular device id(primary key) in its database table row.
* @param state String object that represents the client's state of origin.
* @param inbox[] JsonProperties.JSONprop object that represents the client's sms inbox(which contains sms text, date).
* @param biodata[] JsonProperties.JSONprop object that represents the client's biodata(which contains name, email address, address, gsm number,etc).
* @param status String object that represents the client's json status(1=success, 3=error in the process of processing input, 2=not found/not available/not processed).
* @param inbox[] String object that represents the lists of local government retrieved for the mobile native client.
* @param did integer variable that represents 'did'(device id) which is a counter-uses by servlet-as an index for the array of devices retrieved from DB.
* @param rs integer variable that represents 'rs'(row size) which is a total size of vector row returned.
* @param min integer variable that represents the starting minimum of sms alerts e.g 1-10 of 30. where 1=starting minimum.
* @param smscount integer variable that represents the the current smscount of sms alerts e.g 1-10 of 30 where 10=current sms count
* @param smstotalcount integer variable that represents  the total size of sms alerts .e.g 1-10 of 30 where 30=total size of sms.
* @author Afam;
* @see RequestProcessing.java,Reconsoft.java
* @version 1.0 
*/
public class JsonProperties 
{
    //declare the variables-expalined above- as global
    private String sms;
    private String date;
    private String  id;
    private String name;
    private String client_name;
    private String loc;
    private String descrpt;
    private String build_type;
    private String relat_pos;
    private String fone;
    private String client_email;
    private String contact;
    private String lga,uid;
    private String state;
    private JSONprop  inbox[];
    private JSONprop  contactinbox[],deviceinbox[];
    private JSONprop  biodata;
    private String status;
    private String nlga[];
    private int did,deviceRs,contactRs, rs;
    private int min,smscount,smstotalcount;
    /**
    * This constructor builds the retrieved sms alerts inbox  and their needed parameters into  json format object.
    * **/
    public JsonProperties(String id,String alert[],String date[],String status,int min,int smscount,int smstotalcount,String name)
    {
        super();
        this.inbox=getBuildJsonSMSAlertsProperties(id,alert,date);
        this.status=status;
        this.min=min;
        this.smscount=smscount;
        this.smstotalcount=smstotalcount;
        this.name=name;
    }
    /**
    * This constructor builds the retrieved client's personal data into json format object.
    * **/
    JsonProperties(String id,String client_name,
    String fone,String client_email,String contact,String lga,String state,String status)
    {
        this.id=id;
        this.client_name=client_name;
        this.fone=fone;
        this.client_email=client_email;
        this.contact=contact;
        this.lga=lga;
        this.state=state;
        this.status=status;
    }
    /**
    * This constructor builds the retrieved client's contacts data into json format object.
    * **/
    JsonProperties(String name,String relat_pos,
    String contact,String client_email,String fone,
    String id,int did,int rs)
    {
        this.name=name;
        this.relat_pos=relat_pos;
        this.contact=contact;
        this.client_email=client_email;
        this.fone=fone;
        this.id=id;
        this.status="1";
        this.did=did;
        this.rs=rs;
    }
    /**
    * This constructor builds the retrieved client's string array data into json format object.
    * **/
    public JsonProperties(String value[],String status)
    {
        this.biodata= getBuildJsonClientDeviceProperties(value,"");
        this.status=status;
    }
    /**
    * This constructor builds the retrieved client's state local government areas-after the lga lists has been pre-formatted into a json-like single string- into json format object.
    **/
    public JsonProperties(String lga)
    {
        this.lga=   lga;
        this.status="1";
        
    }
    /**
    * This constructor builds the retrieved client's state local government areas lists into json format object.
    * **/
    public JsonProperties(String []lga)
    {
        this.nlga=   lga;
        this.status="1";
    }
    /**
    * This constructor builds the retrieved client's device/object data into json format object.
    * **/
    public JsonProperties(Vector devicerow,int did,String uid,int rs)
    {
        this.deviceinbox=getFinalBuildJsonClientDeviceProperties(devicerow);
        this.status="1";
        this.rs=rs;
    }
    public JsonProperties(Vector cOntactrow,int did,String uid,int rs,String empty_string)
    {
        this.contactinbox=getFinalBuildJsonClientContactProperties(cOntactrow);
        this.status="1";
        this.rs=rs;
    }
    public JsonProperties(String id,String alert[],String date[],int min,int smscount,int smstotalcount,String name,String biodata[],Vector devicerow,Vector cOntactrow,int deviceRowsize,int contactRowsize)
    {
        
        this.status="1";
        this.min=min;
        this.smscount=smscount;
        this.smstotalcount=smstotalcount;
        this.name=name;
        this.client_name=biodata[0];
        this.fone=biodata[1];
        this.client_email=biodata[2];
        this.contact=biodata[3];
        this.lga=biodata[4];
        this.state=biodata[5];
        this.deviceRs=deviceRowsize;
        this.contactRs=contactRowsize;
        this.inbox=getBuildJsonSMSAlertsProperties(id,alert,date);
        this.deviceinbox=getFinalBuildJsonClientDeviceProperties(devicerow);
        this.contactinbox=getFinalBuildJsonClientContactProperties(cOntactrow);
    }
    /**
    * This constructor builds the retrieved client's device/object data into json format object.
    * **/
    public JsonProperties(String val[],int did,String uid,int rs)
    {
        String deviceid=Integer.toString(did);
        this.name=val[0];
        this.descrpt=val[1];
        this.build_type=val[2];
        this.loc=val[3];
        this.contact=val[4];
        this.lga=val[5];
        this.state=val[6];
        this.id=val[7];
        this.did=did;
        this.uid=val[7];
        //this.biodata=new JSONprop(val[0],val[1],val[2],val[3],val[4],val[5],val[6],val[7],deviceid);
        this.status="1";
        this.rs=rs;
    }
    /**
    * This method builds the retrieved client's sms inbox String data into json format only.
    * Note: It will be finally used to get the json object at the first constructor tha t returns sms inbox and its parameters as a json object.
    * * @return JSONprop  -It is JSONprop object in json format.
    **/
    public JSONprop[] getBuildJsonSMSAlertsProperties(String id,String alert[],String date[])
    {
        //get an object of JSONprop class defined inside this class. 
        JSONprop inbox[]=new JSONprop[alert.length];
        for(int i=0;i<date.length;i++)
        {
            inbox[i]=new JSONprop(id,alert[i],date[i]);
        }
        return inbox;
       
    }
    /**
    * This method builds the retrieved client's device/object data into json format only.
    * Note: It will be finally used to get the json object at the first constructor tha t returns sms inbox and its parameters as a json object.
    * * @return JSONprop  -It is JSONprop object in json format.
    **/
    public final JSONprop[] getFinalBuildJsonClientDeviceProperties(Vector row)
    {
        //get an object of JSONprop class defined inside this class. 
        JSONprop device_inbox[]=new JSONprop[row.size()];
        String val[];
        for(int i=0;i<row.size();i++)
        {
            val=(String[])row.get(i);
            //gets the device row id or counter , also known as did
            String counter_id=Integer.toString(i);
            device_inbox[i] = new JSONprop(val[0],val[1],val[2],val[3],val[4],val[5],val[6],val[7],counter_id);
            //device_inbox[i]=new JSONprop(id,alert[i],date[i]);
        }
        return device_inbox;
        
    }
    /**
    * This method builds the retrieved client's contacts data into json format only.
    * Note: It will be finally used to get the json object at the first constructor tha t returns sms inbox and its parameters as a json object.
    * * @return JSONprop  -It is JSONprop object in json format.
    **/
    public final JSONprop[] getFinalBuildJsonClientContactProperties(Vector row)
    {
        //get an object of JSONprop class defined inside this class. 
        JSONprop contact_inbox[]=new JSONprop[row.size()];
        String val[];
        for(int i=0;i<row.size();i++)
        {
            val=(String[])row.get(i);
            //gets the device row id or counter , also known as did
            String did=Integer.toString(i);
            contact_inbox[i] = new JSONprop(val[0],val[1],val[2],val[3],val[4],val[5],did);
            //device_inbox[i]=new JSONprop(id,alert[i],date[i]);
        }
        
        return contact_inbox;
        
    }
    
    /**
    * This method builds the retrieved client's device/object data into json format only.
    * Note: It will be finally used to get the json object at the constructor builds client's string array data into json format object
    * * @return JSONprop  -It is JSONprop object in json format.
    **/
    public JSONprop getBuildJsonClientDeviceProperties(String val[],String did)
    {
        
       
        
            biodata=new JSONprop(val[0],val[1],val[2],val[3],val[4],val[5],val[6],val[7],did);
       
        return biodata;
    }
     /**
    * This class is needed to build json format which in turn will be used in the above constructors to get json format object.
    * Note: It will be finally used to get the json object at the above constructors and methods to builds  json format object
    **/
    class JSONprop
    {
        //These properties are almost the same as the ones expalined at the begnning of JsonPropeties.
        private String  id;
        private String sms ;
        private String date;
        private String name;
        private String loc;
        private String descrpt;
        private String area;
        private String build_type;
        private String client_name;
        private String fone;
        private String client_email;
        private String contact;
        private String lga;
        private String state,relat_pos,did,uid;
        private String counter_id,table_id;
        private int rs;
        /**
        * This constructor builds the retrieved client's inbox details(SNOS number/id,alert text and date) data into json format object.
        * This will finally be used in the getBuildJsonPropertiesArray method.
        * **/
        JSONprop(String id,String alert,String date)
        {
            this.date=date;
            this.sms=alert;
            this.id=id;
        }
       
        /**
        * This constructor builds the retrieved client's device/data into json format object.
        * This will finally be used in the getBuildJsonClientPropertiesArray method.
        * **/
        JSONprop( String device_name,String descrpt,String build_type,String loc,String contact,String lga,String state,String id,String counterid)
        {
            this.name=device_name;
            this.descrpt=descrpt;
            this.build_type=build_type;
            this.loc=loc;
            this.contact=contact;
            this.lga=lga;
            this.state=state;
            this.id=id;
            this.counter_id=counterid;
            this.table_id=id;
            this.did=counterid;
            this.uid=id;
        }
        /**
        * This constructor builds the retrieved client's contacts data into json format object.
        * This will finally be used in the getBuildJsonClientPropertiesArray method.
        * **/
        JSONprop(String name,String relat_pos,String contact,String client_email,String fone,String tableid,String counterid)
        {
            this.name=name;
            this.relat_pos=relat_pos;
            this.contact=contact;
            this.client_email=client_email;
            this.fone=fone;
            this.counter_id=counterid;
            this.table_id=tableid;
        }
    }
}

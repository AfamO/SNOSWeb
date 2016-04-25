/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snossoftwaretest;

/**
* This class encapsulates and defines ALL the "gets" and "sets"
* AND the properties needed by the application, especially client properties.
* @param name String object that represents the client's name.
* @param fone String object that represents the client's phone.
* @param contact String object that represents the client's contact address.
* @param loc String object that represents the client's location.
* @param area String object that represents the client's area of residence.
* @param build String object that represents the client's building type(like bungalow, duplex,etc)
* @param lga String object that represents the client's local government area.
* @param state String object that represents the client's state of origin.
* @param ClientPassword String object that represents the client's password.
* @param Client_email String object that represents the client's email address.
* @param Contact_email String object that represents the client's contact person email address.
* @param sensor String object that represents the client's SNOS number(client identification key).
* @param relation_position String object that represents relationship or position of the client's contact person. 
* @param sessid String object that represents the client's session id generated automatically during the first stage of registration.
* @param link1 String object that represents the confirmation link that is sent to client's email inbox at the end of 3r stage of registration.
* @param descrpt String object that represents the client's description field-found especially in 2nd stage of registration.
* @param OperaName String object that represents the web administrator's name.
* @param userid String object that represents the web administrator's user id.
* @param pass String object that represents the web administrator's user password.
* @param status String object that represents the web administrator's user status.
* @param message String object that represents the alert/sms message text.
* @param dat String object that represents the alert/sms arrival date/time.
* @param Client_email String object that represents the client's email address.
* @author Afam,Charles;
* @see RequestProcessing.java,Reconsoft.java
* @version 1.0 
*/
public class UserInforGet
{
    //Declare these objects and these variables-explained above- as global.
    private String name;
    private String fone;
    private String contact;
    private String loc;
    private String area;
    private String bulid;
    private String lga;
    private String state;
    private String ClientPassword;
    private String Client_email;
    private String Contact_email;
    private String sensor;
    private String relation_position;
    private String sessid;
    private String link1;
    private String descrpt;
    //declaration for adding administrators
    private String OperaName;
    private String userid;
    private String pass;
    private String status;
    //declaration for adding sms alert/text Messages
    private String message;
    private String dat;
    //a default non-argument constructor
    public UserInforGet()
    {
    }
    //create a 12-argument constructor for the first stage of client registration.
    UserInforGet(String sensor,String fone,String name,String address,String lga,String state,String email1,String password,String date1,String sessid,String link1,String status)
    {
        SetSensor(sensor);SetFone(fone);SetClient(name);
        SetContact(address);SetLga(lga);SetState(state);
        SetClientEmail(email1);SetClientPassword(password);SetDate1(date1);SetSessId(sessid);SetLink1(link1);SetStatus(status);
    }
    //create an 8-argument constructor for the second stage of client registration.
    UserInforGet(String sensor,String nam,String descrpt,String build,String loc,String add,String lga,String state)
    {
        SetSensor(sensor);SetClient(nam);
        SetDescrpt(descrpt);SetBuild(build);SetLoc(loc);
        SetContact(add);SetLga(lga);SetState(state);

    }
    //create an 6-argument constructor for the second stage of client registration.
    UserInforGet(String sensor,String name,String relat_pos,String add,String email1,String fone)
    {
        SetSensor(sensor);SetClient(name);SetRelation_Position(relat_pos);SetContact(add);
        SetClientEmail(email1);SetFone(fone);
    }
    //create an 2-argument constructor for the SNOS number and its corresponding gsm key pair.
    UserInforGet(String senName,String fon)
    {
        SetSensor(senName);SetFone(fon);
    }
    UserInforGet(String senName)
    {
        SetSensor(senName);
    }
    //create an 4-argument constructor for the registration of web administrator data.
    UserInforGet(String name,String userid,String password,String status)
    {
        SetOperaName(name);
        SetUserid(userid);SetPass(password);SetStatus(status);
    }
    //set all the global variables declared above by creating a 'set' methods for them.
    public void SetClient(String m){name=m;}
    public void SetFone(String fo){fone=fo;}
    public void SetContact(String con){contact=con;}
    public void SetLoc(String lo){loc=lo;}
    public void SetArea(String a){area=a;}
    public void SetBuild(String bu){bulid=bu;}
    public void SetLga(String lg){lga=lg;}
    public void SetState(String sta){state=sta;}
    public void SetClientPassword(String pass){ClientPassword=pass;}
    public void SetClientEmail(String mail){Client_email=mail;}
    public void SetContactEmail(String mail2){Contact_email=mail2;}
    public void SetSensor(String sense){ sensor=sense; }
    public void SetOperaName(String opera){ OperaName=opera; }
    public void SetUserid(String id){ userid=id; }
    public void SetPass(String vas){ pass=vas; }
    public void SetStatus(String stat2){ status=stat2; }
    public void SetMessage(String mes2){ message=mes2; }
    public void SetDate1(String d){dat=d;}
    public void SetRelation_Position(String relatio_pos){this.relation_position=relatio_pos;}
    public void SetSessId(String sessid){this.sessid= sessid;}
    public void SetLink1(String link1){this.link1=link1;}
    public void SetDescrpt(String decr){descrpt=decr;}
    //get all the global variables declared and set above by creating a 'get' methods for them.
    public String getClient(){return name;}
    public String getFone(){return fone;}
    public String getContact(){return contact;}
    public String getLoc(){return loc;}
    public String getArea(){return area;}
    public String getBuild(){return bulid;}
    public String getLga(){return lga;}
    public String getState(){return state;}
    public String getClientPassword(){return ClientPassword;}
    public String getClientEmail(){return Client_email;}
    public String getContactEmail(){return Contact_email;}
    public String getSensor(){return sensor;}
    public String getOperaName(){return OperaName;}
    public String getUserid(){return userid;}
    public String getPass(){return pass;}
    public String getStatus(){return status;}
    public String getMess(){return message;}
    public String getDate1(){return dat;}
    public String getRelation_Position(){return this.relation_position;}
    public String getSessionId(){return sessid;}
    public String getLink1(){return this.link1;}
    public String getDecrpt(){return descrpt;}
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snossoftwaretest;


import javax.swing.*;
import java.util.regex.*;
import java.text.*;
/**
* This defines  all the application's necessary regular expressions and 
* takes care of the total validation,comparison,checking and filtering of inputs 
* entered in a field based on some particular pre-defined and proper patterns.
* @param GSMNO String object representing the gsm valid format pattern.E.g 2348064511195 is valid while 08064511195 is invalid.
* @param USERNAME String object representing the username valid format pattern.E.g 'Okoh Mike John '  is valid. while 'Ok' is invalid.
* @param LGA String object representing the local government valid format pattern.E.g AwGu is valid while 'A2' is invalid.
* @param LOCATION String object representing the address valid format pattern.E.g "6a and 6b Sule Abuka crescent, Off Opebi" is valid while "6a and 6b@ Sule Abuka *crescent, Off Opebi" is invalid.
* @param SNOSLABEL String object representing the client identification number(SNOS number) valid format pattern.E.g "SNOS 1" is valid while 'SNOS    1' is invalid.
* @param ARBITRARYFIELD String object representing arbitrary fields-no special characters and does not have more than 25 or 30 characters- valid format pattern.E.g "This is a very smart watch" 'This is a very smart watch that is all wholesome and good for use' is invalid.
* @param sensorFIELD String object representing the client's sensor valid format pattern.E.g "URGENT ASSISTANCE GRANDMA BEDROOM " is valid  while 'URGENT ASSISTANCE2 !GRANDMA BEDROOM' is invalid.
* @param EMAIL String object representing the client's email address valid format pattern.E.g "admin@snosfortress.com " is valid while 'admin@snosfortress.comm' is invalid.
* @param matcher Matcher object representing the matcher object to be used.
* @param pattern Pattern object representing the pattern object to be used.
* @author Afam
* @see RequestProcessing.java
* @version 1.0 
*/
public class PatternCheck
{
    //Declare these objects and these variables-explained above- as global
    //Make them final since they are not expected to be changed during program execution.
    private static final String GSMNO = "[234]{3}[7-8]{1}[0-9]{9}|[0-9]{11}";
    private static final String USERNAME="([ ]{0,5}[A-Z]{2,50}[ ]{0,5}[A-Z]{0,25}[ ]{0,5}[A-Z,.]{0,25})";
    private static final String LGA="([A-Z,0-9,-,_]{2,35})";
    private static final String LOCATION="[A-Z,0-9,-_,;,.,[ ]{0,5}]{3,60}";
    private static final String SNOSLABEL = "[SNOS]{4}[ ]{0,3}[1-9]{1}[0-9]{0,4}";
    private static final String ARBITRARYFIELD = "[A-Z,0-9,_.,;,-[ ]{0,5}]{4,25}";
    private static final String sensorFIELD = "[A-Z,0-9,.,;,-,[ ]{0,5}]{7,50}";
    private static final String EMAIL="[a-z]{2}[a-z,0-9,_.]{1,15}[@]{1}[a-z,0-9,_.]{3,20}[.]{1}[a-z]{3}";
    private static final String PASSWORD = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
    private Matcher matcher;
    private Pattern pattern;
    public PatternCheck()
    {
        
    }
    /**
    * This method validates the SNOS Number of a client.
    * @param snosid String object representing the SNOS number in question 
    * @param format String object representing the right format it should follow.This is ONLY used for debugging purposes.
    * @param label String object representing the label for error message.This is ONLY used for debugging purposes.
    * @return Boolean -It returns true if validated successfully or false otherwise.
    * **/
    public boolean checkSnosLabel(String snosid,String label,String format)
    {
        //get the pattern object by compiling using a pre-defined pattern format and make it case insensitive.
        pattern=Pattern.compile(SNOSLABEL,Pattern.CASE_INSENSITIVE);
        //get matcher object.
        matcher=pattern.matcher(snosid);
        if(matcher.matches())
        {
            //print out for debugging purposes.
            System.out.println("Valid "+label+" .Your "+label+"\n should be in this format:\n"+format+"\nThank You");
            return true;
        }
        //System.out.println("Invalid "+label+" .Your "+label+"\n should be in this format:\n"+format+"\nThank You");
        return false;
    }
    /**
    * This method validates any arbitrary field like Description fields, etc.
    * @param arbitfield String object representing the arbitrary field input. 
    * @param format String object representing the right format it should follow.This is ONLY used for debugging purposes.
    * @param label String object representing the label for error message.This is ONLY used for debugging purposes.
    * @return Boolean -It returns true if validated successfully or false otherwise.
    **/
    public boolean checkArbitraryField(String arbitfield,String label,String format)
    {
        //get the pattern object by compiling using a pre-defined pattern format and make it case insensitive.
        pattern=Pattern.compile(ARBITRARYFIELD,Pattern.CASE_INSENSITIVE);
        //get matcher object.
        matcher=pattern.matcher(arbitfield);
        if(matcher.matches())
        {
            //print out for debugging purposes.
            System.out.println("Valid "+label+" .Your "+label+"\n should be in this format:\n"+format+"\nThank You");
            return true;
        }
        System.out.println("Invalid "+label+" .Your "+label+"\n should be in this format:\n"+format+"\nThank You");
        return false;
    }
    /**
    * This method validates the client's sensor field input etc.
    * @param sensor String object representing the sensor's input in question. 
    * @param format String object representing the right format it should follow.This is ONLY used for debugging purposes.
    * @param label String object representing the label for error message.This is ONLY used for debugging purposes.
    * @return Boolean -It returns true if validated successfully or false otherwise.
    **/ 
    public boolean checkSensorField(String sensor,String label,String format)
    {
        //get the pattern object by compiling using a pre-defined pattern format and make it case insensitive.
        pattern=Pattern.compile(sensorFIELD,Pattern.CASE_INSENSITIVE);
        //get matcher object.
        matcher=pattern.matcher(sensor);
        if(matcher.matches())
        {
            System.out.println("Valid "+label+" .Your "+label+"\n should be in this format:\n"+format+"\nThank You");
            return true;
        }
        System.out.println("Invalid "+label+" .Your "+label+"\n should be in this format:\n"+format+"\nThank You");
        return false;
    }
    /**
    * This method validates the client's name field input etc.
    * @param username String object representing the client's name field input. 
    * @param format String object representing the right format it should follow.This is ONLY used for debugging purposes.
    * @param label String object representing the label for error message.This is ONLY used for debugging purposes.
    * @return Boolean -It returns true if validated successfully or false otherwise.
    **/ 
    public boolean checkUserName(String username,String label,String format)
    {
        //get the pattern object by compiling using a pre-defined pattern format and make it case insensitive.
        pattern=Pattern.compile(USERNAME,Pattern.CASE_INSENSITIVE);
        //get matcher object.
        matcher=pattern.matcher(username);
        if(matcher.matches())
        {
            //print out for debugging purposes.
            System.out.println("Valid "+label+" .Your "+label+"\n should be in this format:\n"+format+"\nThank You");
            return true;
        }
        System.out.println("Invalid "+label+" .Your "+label+"\n should be in this format:\n"+format+"\nThank You");
        return false;
    }
    /**
    * This method validates the client's local governement field input etc.
    * @param lga String object representing the client's local government field input. 
    * @param format String object representing the right format it should follow.This is ONLY used for debugging purposes.
    * @param label String object representing the label for error message.This is ONLY used for debugging purposes.
    * @return Boolean -It returns true if validated successfully or false otherwise.
    **/ 
    public boolean checklGA(String lga,String label,String format)
    {
        //get the pattern object by compiling using a pre-defined pattern format and make it case insensitive.
        pattern=Pattern.compile(LGA,Pattern.CASE_INSENSITIVE);
        //get matcher object.
        matcher=pattern.matcher(lga);
        if(matcher.matches())
        {
            //print out for debugging purposes.
            System.out.println("Valid "+label+" .Your "+label+"\n should be in this format:\n"+format+"\nThank You");
            return true;
        }
        System.out.println("Invalid "+label+" .Your "+label+"\n should be in this format:\n"+format+"\nThank You");
        return false;
    }
    /**
    * This method validates the client's address or location's field input etc.
    * @param loc String object representing the client's address or location's field input. 
    * @param format String object representing the right format it should follow.This is ONLY used for debugging purposes.
    * @param label String object representing the label for error message.This is ONLY used for debugging purposes.
    * @return Boolean -It returns true if validated successfully or false otherwise.
    **/ 
    public boolean checkLocation(String loc,String label,String format)
    {
        //get the pattern object by compiling using a pre-defined pattern format and make it case insensitive.
        pattern=Pattern.compile(LOCATION,Pattern.CASE_INSENSITIVE);
        //get matcher object.
        matcher=pattern.matcher(loc);
        if(matcher.matches())
        {
            //print out for debugging purposes.
            System.out.println("Valid "+label+". Your "+label+"\n should be in this format:\n"+format+"\nThank You");
            return true;
        }
        System.out.println("Invalid "+label+". Your "+label+"\n should be in this format:\n"+format+"\nThank You");
        return false;
    }
    /**
    * This method validates the client's email address's field input.
    * @param loc String object representing the client's email address's field input. 
    * @param format String object representing the right format it should follow.This is ONLY used for debugging purposes.
    * @param label String object representing the label for error message.This is ONLY used for debugging purposes.
    * @return Boolean -It returns true if validated successfully or false otherwise.
    **/
    public boolean checkEmail(String email,String label,String format)
    {
        //get the pattern object by compiling using a pre-defined pattern format and make it case insensitive.
        pattern=Pattern.compile(EMAIL,Pattern.CASE_INSENSITIVE);
        //get matcher object.
        matcher=pattern.matcher(email);
        if(matcher.matches())
        {
            //print out for debugging purposes.
            System.out.println("Valid "+label+" .Your "+label+"\n should be in this format:\n"+format+"\nThank You");
            return true;
        }
        System.out.println("Invalid "+label+" .Your "+label+"\n should be in this format:\n"+format+"\nThank You");
        return false;
    }
    /**
    * This method validates the client's password's field input.
    * @param loc String object representing the client's email address's field input. 
    * @param format String object representing the right format it should follow.This is ONLY used for debugging purposes.
    * @param label String object representing the label for error message.This is ONLY used for debugging purposes.
    * @return Boolean -It returns true if validated successfully or false otherwise.
    **/
    public boolean validatePassword(String password,String label,String format)
    {
        //get the pattern object by compiling using a pre-defined pattern format and make it case insensitive.
        pattern=Pattern.compile(PASSWORD,Pattern.CASE_INSENSITIVE);
        //get matcher object.
        matcher=pattern.matcher(password);
        if(matcher.matches())
        {
            //print out for debugging purposes.
            System.out.println("Valid "+label+" .Your "+label+"\n should be in this format:\n"+format+"\nThank You");
            return true;
        }
        System.out.println("Invalid "+label+" .Your "+label+"\n should be in this format:\n"+format+"\nThank You");
        return false;
    }
    /**
    * This method validates the client's GSM number's field input.
    * @param gsm String object representing the client's GSM number's field input. 
    * @param format String object representing the right format it should follow.This is ONLY used for debugging purposes.
    * @param label String object representing the label for error message.This is ONLY used for debugging purposes.
    * @return Boolean -It returns true if validated successfully or false otherwise.
    **/
    public boolean checkGsm(String gsm,String label,String format)
    {
        //get the pattern object by compiling using a pre-defined pattern format and make it case insensitive.
        pattern=Pattern.compile(GSMNO);
        //get matcher object.
        matcher=pattern.matcher(gsm);
        if(matcher.matches())
        {
            //print out for debugging purposes.
            System.out.println("Valid "+label+" .Your "+label+"\n should be in this format:\n"+format+"\nThank You");
            return true;
        }
        System.out.println("Invalid "+label+" .Your "+label+"\n should be in this format:\n"+format+"\nThank You");
        return false;
    }
}



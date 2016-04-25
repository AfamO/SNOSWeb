/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package snossoftwaretest;

/**
 *
 * @author Charles
 */
import java.util.*;

public class InformationProcess {

  private String firstName;
  private String lastName;
  private String email;
  private Hashtable errors;

    public boolean validate() {
    boolean bool=true;
    if (firstName.equals("")) {
      errors.put("firstName","Please enter your first name");
      firstName="";
      bool=false;
    }
    if (lastName.equals("")) {
      errors.put("lastName","Please enter your last name");
      lastName="";
      bool=false;
    }
    if (email.equals("") || (email.indexOf('@') == -1)) {
      errors.put("email","Please enter a valid email address");
      email="";
      bool=false;
    }
    
    return bool;
  }
  public String getErrorMsg(String s) {
    String errorMsg =(String)errors.get(s.trim());
    return (errorMsg == null) ? "":errorMsg;
  }

}

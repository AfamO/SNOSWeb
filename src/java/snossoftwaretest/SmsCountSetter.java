/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package snossoftwaretest;

/**
 *
 * @author Admin
 */
import java.util.*;
public class SmsCountSetter {
private static int smscount;
private static int totalcount;
public Vector row=new Vector();
public SmsCountSetter()
{

}

public static void setSmscount(int count)
{
    SmsCountSetter.smscount=count;
}

}

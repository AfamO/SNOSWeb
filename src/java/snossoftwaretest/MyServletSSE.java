package snossoftwaretest;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Queue;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.swing.JOptionPane;
import org.json.simple.JSONObject;

/**
 *
 * @author fupre1
 */
@WebServlet(urlPatterns = {"/mysse"}, asyncSupported = true)
public class MyServletSSE extends HttpServlet {
    
    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ///JOptionPane.showMessageDialog(null,"I am here-destroy");
        //response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        ServletContext servlet_context=request.getSession().getServletContext();
        try {
            /* TODO output your page here. You may use following sample code. */
            ///**
            //JOptionPane.showMessageDialog(null,"I am here-1");
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet MyServletSSE</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MyServletSSE at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
            //*/
            
            
            response.setContentType("text/event-stream;charset=UTF-8");
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("Connection", "keep-alive");
            //get the Vector object
            Vector new_alerts_row=new Vector();//for the newest alerts
            Vector old_alerts_row=new Vector();    //for the old-alerts
            
            //Get the Database class object
            Reconsoft  rs=new Reconsoft();
            //set the logout attribute here to be used later to know whether the client has logged out or not
            servlet_context.setAttribute("Is_logout","no");
            //get the snos type
            String sid = request.getParameter("sid");
            System.out.println("The SNOS ID ==="+sid);
            int old_total_sms_count,new_total_sms_count = 0,old_id,new_alert_counter= 0;
            //get the first and the old total sms count
            old_total_sms_count=Reconsoft.getSmsTotalCount();
            //get last/highest column id of the sms table
            old_id=(Integer)request.getSession().getAttribute("last_Retrieved_Id");
            System.out.println("The last_old iD ==="+old_id);
            //get the current sms count
            int sms_current_count=(Integer)request.getSession().getAttribute("smscount");
            System.out.println("The initial sms current count==="+sms_current_count);
            if(sms_current_count==0)
            {
                sms_current_count++;//increment it to 1=so that the sms page can start displaying from 1 instead of 0
            }
            while (true) {
              
              //Has the client logged out
              System.out.println("LOGOUT MESSAGE==="+servlet_context.getAttribute("Is_logout"));
              if(servlet_context.getAttribute("Is_logout").equals("yes"))
              {
                  System.out.println("You have logged out THUS THERE is NO latest alert data to retrieve");
                  
              }
              else
              {
                  //get the latest and newest sms total count
                  new_total_sms_count=rs.getSmsTotalCount(sid);
              }
                
              //Is old one greater than than new one. I mean has new alerts arrived?
              if(new_total_sms_count>old_total_sms_count)
              {
                  //Make/assign the new one to be old one
                  old_total_sms_count=new_total_sms_count;
                  Integer last_id=  rs.getSmsLast_Id(sid);
                  String lastId=Integer.toString(last_id);
                  //retrieve the oldest alerts
                  old_alerts_row=(Vector)request.getSession().getAttribute("row");
                  //get the alert that just arrived using the id
                  String [] latest_alert= rs.query_One_Single_Sms_Alert(sid, last_id);
                  //add this-latest_alert to the new alerts Vector list
                  new_alerts_row.add(new_alert_counter,latest_alert);
                  new_alert_counter++; //increment the counter
                  System.out.println("The new alert counter==="+new_alert_counter);
                  //re-assign old id to be latest one for future comparisons
                  old_id=last_id;
                  if(new_alerts_row.isEmpty())
                  {
                     System.out.println("There is NO latest alert data to retrieve");
                  }
                  
                else
                {
                     System.out.println("There is/are LATEST alert(s) data to retrieve");
                     // add the two objects to form a single one containing all the old and new alerts for display. The newest one should come on top.
                     Vector total_alerts_row=this.add_old_new_alerts_together(old_alerts_row, new_alerts_row);
                    //get the starting minimum of his alerts e.g 1-10 of 30. where 1=starting minimum
                    Integer minimum=(Integer)request.getSession().getAttribute("min");
                    sms_current_count=(Integer)request.getSession().getAttribute("smscount");
                    
                    System.out.println("The RETRIEVED sms current count==="+sms_current_count);
                    //increment it only when it is not up to 10. Too avoid for instance, 1-10 of 5. I mean the current sms count must always be equal to total count UNLESS totalcount is now greater than 10
                    if(new_total_sms_count<=10)
                    {
                        sms_current_count++;//increment the number of sms current count for each new received alert
                    }
                    
                    //store the current smscount of his alerts in a session too. e.g 1-10 of 30 where 10=current sms count
                    request.getSession().setAttribute("smscount",sms_current_count);
                    System.out.println("The STORED sms current count==="+sms_current_count);
                                        //store the total size of his alerts in a session too.e.g 1-10 of 30 where 30=total size of sms
                    request.getSession().setAttribute("smstotalcount",new_total_sms_count);
                     //converts the row to json and then string format to be sent to the browaser
                     
                     String sse_data=getAlertString(total_alerts_row,new_total_sms_count,sms_current_count,minimum);
                     request.getSession().setAttribute("row1", total_alerts_row);
                     out.print("id: " + "SSE Alert Data" + "\n\n");
                     out.print("data: " + sse_data+ "\n\n");
                     out.flush();
                     // out.close(); //Do not close the writer!
                }
                
              }
              
                try {
                 Thread.sleep(15000); //sets the time to 15 seconds
                } catch (InterruptedException e) {
                }
               }
        } 
       finally {            
            out.close();
        }
    
    }
private String getAlertString(Vector row, int totalcount,int sms_current_count,int minimum)
{
    //Gets the json object 
    JSONObject json = new JSONObject();
    
    String alert="";
    String date1="";//this holds the current alert and date
    for(int i=0;i<row.size();i++){
        String []data=(String[])row.get(i);
        alert+=data[1]+"brk";//get the alert
        //get the date
        date1+=data[2]+"brk";
    }
    json.put("alert",alert);
    json.put("date",date1);
    json.put("size", row.size());
    json.put("total_size", totalcount);
    json.put("sms_current_count", sms_current_count);
    json.put("min", minimum);
    json.put("status",1);
    System.out.println("the sse json alert="+json.toJSONString());
    //convert to json string
    return json.toJSONString();
}
private Vector add_old_new_alerts_together(Vector old_alerts, Vector new_alerts)
{
    Vector total_alerts_row=new Vector(); //for the the above two.
    //iterate over the new alerts and add it to the total_alerts vector
    for(int i=new_alerts.size()-1;i>=0;i--)
    {
        System.out.println("The new alerts counter is:"+i);
        total_alerts_row.addElement(new_alerts.get(i));
    }
    //iterate over the old alerts and add it to the total_alerts vector
    for(int i=0;i<old_alerts.size();i++)
    {
        System.out.println("The old alerts counter is:"+i);
        total_alerts_row.addElement(old_alerts.get(i));
    }
    return total_alerts_row;
}
// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
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
     * Handles the HTTP
     * <code>POST</code> method.
     *
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
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package snossoftwaretest;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;

import java.util.Random;
import javax.imageio.ImageIO;
/**
 *
 * @author fupre1
 */
public class CaptchaServlet extends HttpServlet {


  protected void processRequest(HttpServletRequest request,
                                HttpServletResponse response)
                 throws ServletException, IOException {
HttpSession session=request.getSession();
    int width = 150;
    int height = 50;

    char data[][] = {
        { 'z', 'e', 't', 'c', 'o', 'd', 'e' },
        { 'l', 'v', 'n', 'w', 'x' },
        { 'f', 'r', 'i', 'e', 'b', 'd', 'd' },
        { 'b', 'b', 'u', 'd', 't', 'u' },
        { 'j', 'x', 'q' },
        { 'a', 'f', 't', 'm', 'o', 'd', 'e' },
        { 'w', 'y', 's', 'i', 'y' },
        { 'f', 'f', 'g', 'e', 'h', 'o', 'm' },
        { 'u', 'r', 'b', 'a', 't', 'u' },
        { 'd', 'i', 'm' },
        { 'z', 'n', 't', 'm', 'o', 'f', 'e' },
        { 'l', 'i', 'a', 'c', 'w' },
        { 'f', 'r', 'e', 'e', 'b', 's', 'd' },
        { 'u', 'b', 'u', 'n', 't', 'u' },
        { 'k', 'i', 'e' },
        { 'z', 'e', 'b', 'r', 'a', 'd', 'e' },
        { 'w', 'y', 'o', 'm', 'x' },
        { 'f', 'r', 'e', 't', 'f', 'u', 'l' },
        { 'u', 'n', 'i', 'o', 'n', 'u' },
        { 'j', 'a', 'm', 'b', 'e' },
        { 'k', 'i', 'n' },
        { 'z', 'i', 'd', 'a', 'a', 'n', 'e' },
        { 'm', 'z', 'o', 'm', 'd' },
        { 'f', 'a', 'i', 't', 'f', 'u', 'l' },
        { 'd', 'n', 'i', 'o', 'n', 'u' },
        { 'x', 'y', 'z', 'b', 'w' },
        { 'u', 'z', 'o', 'v', 'd' },
        { 'q', 'a', 'i', 't', 'w', 'u', 'l' },
        { 'b', 'n', 'i', 'o', 'y', 'u' },
        { 'j', 'g', 'z', 'm', 'e' }
    };


    BufferedImage bufferedImage = new BufferedImage(width, height,
                  BufferedImage.TYPE_INT_RGB);

    Graphics2D g2d = bufferedImage.createGraphics();

    Font font = new Font("Georgia", Font.BOLD, 18);
    g2d.setFont(font);

    RenderingHints rh = new RenderingHints(
           RenderingHints.KEY_ANTIALIASING,
           RenderingHints.VALUE_ANTIALIAS_ON);

    rh.put(RenderingHints.KEY_RENDERING,
           RenderingHints.VALUE_RENDER_QUALITY);

   // g2d.setRenderingHints(rh);

    GradientPaint gp = new GradientPaint(0, 0,
    Color.red, 0, height/2, Color.black, true);

    g2d.setPaint(gp);
    g2d.fillRect(0, 0, width, height);

    g2d.setColor(new Color(255, 153, 0));

    Random r = new Random();
    int index = Math.abs(r.nextInt()) % data.length;

    String captcha = String.copyValueOf(data[index]);
    session.setAttribute("captcha", captcha );

    int x = 0; 
    int y = 0;

    for (int i=0; i<data[index].length; i++) {
        x += 10 + (Math.abs(r.nextInt()) % 15);
        y = 20 + Math.abs(r.nextInt()) % 20;
        g2d.drawChars(data[index], i, 1, x, y);
    }

    g2d.dispose();

    response.setContentType("image/png");
    OutputStream os = response.getOutputStream();
    ImageIO.write(bufferedImage, "png", os);
    System.out.println("The length of Captcha data is: "+data.length);
    os.close();
  }


  protected void doGet(HttpServletRequest request,
                       HttpServletResponse response)
                           throws ServletException, IOException {
      processRequest(request, response);
  }


  protected void doPost(HttpServletRequest request,
                        HttpServletResponse response)
                            throws ServletException, IOException {
      processRequest(request, response);
  }
}
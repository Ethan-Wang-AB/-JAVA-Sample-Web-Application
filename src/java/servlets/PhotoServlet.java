/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.User;
import services.AccountService;

/**
 *
 * @author 845593
 */
public class PhotoServlet extends HttpServlet {


   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            ServletContext cntx= request.getServletContext();
            // Get the absolute path of the image
            HttpSession session=request.getSession();
            String email=(String) session.getAttribute("email");
            AccountService accountService=new AccountService();
            User user=accountService.get(email);
                    String filename = user.getPhotoPath();
                    // retrieve mimeType dynamically
                    String mime = cntx.getMimeType(filename);
                    if (mime == null) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        return;
                    }
                    
                    response.setContentType(mime);
                    File file = new File(filename);
                    response.setContentLength((int)file.length());
                    
                    FileInputStream in = new FileInputStream(file);
                    OutputStream out = response.getOutputStream();
                    
                    // Copy the contents of the file to the output stream
                    byte[] buf = new byte[1024];
                    int count = 0;
                    while ((count = in.read(buf)) >= 0) {
                        out.write(buf, 0, count);
                    }
                    out.close();
                    in.close();
                    
        } catch (Exception ex) {
            Logger.getLogger(PhotoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

  

}

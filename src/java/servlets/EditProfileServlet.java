/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.sql.Blob;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import models.User;
import services.AccountService;

/**
 *
 * @author 845593
 */
@MultipartConfig
public class EditProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            AccountService accountService = new AccountService();
            HttpSession session = request.getSession();
            String email = (String) session.getAttribute("email");
           User user= accountService.get(email);
            if(user.getRole().getRoleId()==1){
            request.setAttribute("isAdmin", true);
            }
            if(request.getParameterMap().containsKey("twowayEnable")){
     
               String emailLogin=email;
      
            session.setAttribute("emailTwoWay", emailLogin);
           System.out.println("enable 2Way    "+emailLogin);
           
            user=accountService.get(email);
            int twoWay=(int) (Math.random()*10000/1);
            user.setAuthenUuid(twoWay+"");
            accountService.update(user);
            
            response.sendRedirect("login?twoway");
            return;
            
            }
            
            
            
            
            // String img=request.getParame
            //System.out.println("edit profile  " + email);
         user = accountService.get(email);
              //    System.out.println("edit profile get name " + user);
            request.setAttribute("user", user);
//            if(user.getPhotoPath()!=null){
           String photopath =user.getPhotoPath();            
            request.setAttribute("photopath","profilephoto");
//            }
          // OutputStream os=response.getOutputStream();
       
//            System.out.println("photo path+++"+user.getPhotoPath());
            getServletContext().getRequestDispatcher("/WEB-INF/editprofile.jsp").forward(request, response);

        } catch (Exception ex) {
            Logger.getLogger(EditProfileServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            AccountService accountService = new AccountService();
            HttpSession session = request.getSession();
            String email = (String) session.getAttribute("email");
            User user = accountService.get(email);
            request.setAttribute("user", user);
            String edit = request.getParameter("editAction");
         
            Part filePart = request.getPart("photo"); // Retrieves <input type="file" name="file">
         
            if (filePart != null) {
               String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
               //String extentions[]=fileName.split(".");
              // String extention=extentions[extentions.length-1];
               
              // System.out.println("extention+++"+fileName);
                InputStream fileContent = filePart.getInputStream();
                byte[] image = new byte[fileContent.available()];
               fileContent.read(image);
                 String path = getServletContext().getRealPath("/WEB-INF");
//                      
                String filename = path+"\\photos\\" +user.getEmail()+ fileName;
//                //getServletContext().getRealPath("/") + 
//                 //String filename = "\\WEB-INF\\photos\\" + email;
//                System.out.println("real path ++++" + filename);
//                String contextPath=request.getContextPath()+"\\photos\\"+fileName;
                File file = new File(filename);
               if (!file.exists()) {
                    file.createNewFile();
                }
              FileOutputStream writer = new FileOutputStream(file);
               writer.write(image);
                user.setPhotoPath(filename);
                accountService.update(user);

            }

            if (edit.equals("deactive")) {

                accountService.deactive(user);
                response.sendRedirect("login");

            } else if (edit.equals("edit")) {
                String firstname = request.getParameter("firstname");
                String lastname = request.getParameter("lastname");
                String password = request.getParameter("password");
                 user=accountService.get(email);
                user.setFirstName(firstname);
                user.setLastName(lastname);
                accountService.update(user);

            }

            response.sendRedirect("inventory");
        } catch (Exception ex) {
            Logger.getLogger(EditProfileServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}

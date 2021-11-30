/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.User;
import services.AccountService;
import services.GmailService;

/**
 *
 * @author 845593
 */
public class TwoWayAuthentication extends HttpServlet {

   
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            AccountService accountService=new AccountService();
            HttpSession session=request.getSession();
            String email=(String) session.getAttribute("emailTwoWay");
            User user=accountService.get(email);
            int auth=(int) (Math.random()*10000/1);
            user.setAuthenUuid(auth+"");
            accountService.update(user);
            
              HashMap<String, String> tags = new HashMap<>();
             
                tags.put("firstname", user.getFirstName());
                tags.put("lastname", user.getLastName());
                tags.put("action", "two way authentication login");
                tags.put("code",user.getAuthenUuid());
           
                    
                        String path = getServletContext().getRealPath("/WEB-INF");
                        String template = path + "/emailtemplates/confirmation.html";
                        
                        GmailService.sendMail(email, "Two way authentication", template, tags);
                getServletContext().getRequestDispatcher("/WEB-INF/twoWayAuth.jsp").forward(request, response);
                        return;
            
            
            
            
        } catch (Exception ex) {
            Logger.getLogger(TwoWayAuthentication.class.getName()).log(Level.SEVERE, null, ex);
        }

       
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            AccountService accountService=new AccountService();
            HttpSession session=request.getSession();
            String email=(String) session.getAttribute("emailTwoWay");
            User user=accountService.get(email);
            String auth=request.getParameter("twoWayCode");
            if(auth.equals(user.getAuthenUuid())){
            session.setAttribute("email",email);
            session.setAttribute("twoway","true");
          if (user.getRole().getRoleId()==1) {
        
            response.sendRedirect("admin");
        } else if(user.getRole().getRoleId()==3){
              response.sendRedirect("companyadmin");

        }
        else{
            response.sendRedirect("inventory");
        }

            }
            else{
            request.setAttribute("error", "The auth code is not correct");
            request.setAttribute("errorExist", true);
               getServletContext().getRequestDispatcher("/WEB-INF/twoWayAuth.jsp").forward(request, response);

            }
                    
                    } catch (Exception ex) {
            Logger.getLogger(TwoWayAuthentication.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    
}

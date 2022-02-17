/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.User;
import services.AccountService;
import services.GmailService;

/**
 *
 * @author 845593
 */
public class ResetPasswordServlet extends HttpServlet {

   

      protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String uuid = "";
        if (request.getParameterMap().containsKey("uuid") && !request.getParameter("uuid").trim().equals("")) {
            uuid = request.getParameter("uuid");
            request.setAttribute("uuid", uuid);
            getServletContext().getRequestDispatcher("/WEB-INF/ResetPassword.jsp").forward(request, response);
        } else {
            getServletContext().getRequestDispatcher("/WEB-INF/reset.jsp").forward(request, response);

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AccountService accountService = AccountService.getInstance();
        if (!request.getParameterMap().containsKey("uuid")) {

            try {
                String uuid = UUID.randomUUID().toString();
                String url = request.getRequestURL().toString();
                String link = url + "?uuid=" + uuid;
                String email = request.getParameter("email");
                User user = accountService.getByEmail(email);
               // System.out.println("reset email: "+email);
                HashMap<String, String> tags = new HashMap<>();
                String firstname = user.getName().split(",")[1];
                String lastname = user.getName().split(",")[0];
                tags.put("firstname", firstname);
                tags.put("lastname", lastname);
                tags.put("link", link);
                if (user != null) {
                    try {
                        String path = getServletContext().getRealPath("/WEB-INF");
                        String template = path + "/emailtemplates/resetpassword.html";
                        user.setResetPasswordUuid(uuid);
                        accountService.update(user);
                        
                        GmailService.sendMail(email, "Reset Your Password", template, tags);
                        response.sendRedirect("login");
                        return;
                        
                    } catch (Exception ex) {
                        Logger.getLogger(ResetPasswordServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } catch (Exception ex) {
                Logger.getLogger(ResetPasswordServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            String uuid = request.getParameter("uuid");
            String password = request.getParameter("password");
            System.out.println(password);
            Boolean success=accountService.changePassword(uuid, password);
            System.out.println("change password "+success);
            response.sendRedirect("login");
            return;
        }
    }
   

}

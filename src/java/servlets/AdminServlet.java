/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Users;
import services.AccountService;

/**
 *
 * @author 845593
 */
public class AdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            HttpSession session = request.getSession();
            String usernameAdmin = (String) session.getAttribute("username");
            AccountService accountService = new AccountService();
     

            String adminAction = request.getParameter("action");
           Vector<Users> userList =  (Vector<Users>) accountService.getAll();
            String username = "";
            String email = "";
            String firstName = "";
            String lastName = "";
            String password = "";
           
            if(request.getParameterMap().containsKey("action")){
                        session.setAttribute("adminAction", "Edit User");

            }else{
                        session.setAttribute("adminAction", "Add User");

            }

            if (request.getParameter("username") != null) {
                if (request.getParameter("action").equals("edit")) {

                    username = request.getParameter("username");
                    Users user = accountService.get(username);

                    request.setAttribute("email", user.getEmail());
                    request.setAttribute("username", username);
                    request.setAttribute("fistName", user.getFirstName());
                    request.setAttribute("lastName", user.getLastName());
                    request.setAttribute("password", user.getPassword());
                    session.setAttribute("previousUsername", username);
                    userList = (Vector) accountService.getAll();
                    request.setAttribute("userList", userList);

                    getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);
                    return;

                } else if (request.getParameter("action").equals("delete")) {
                    
                    username = request.getParameter("username");
                    if(usernameAdmin.equals(username)){
                      request.setAttribute("error", "You cannot delete yourself");
                      request.setAttribute("errorExist", true);
                    }else{
                    accountService.delete(username);
                    }
                    userList = (Vector) accountService.getAll();
                   session.setAttribute("adminAction", "Add User");
                     request.setAttribute("userList", userList);
                    getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);
                    return;
                }
            } else {
               
                  userList = (Vector) accountService.getAll();
                  
                    request.setAttribute("userList", userList);

                session.setAttribute("adminAction", "Add User");
            }
            getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String adminAction = (String) session.getAttribute("adminAction");
        System.out.println("adminAction: "+adminAction);
        AccountService accountService = new AccountService();
        try {
             Vector<Users> userList =  (Vector<Users>) accountService.getAll();
            request.setAttribute("userList", userList);
            String username = request.getParameter("username");
            String email = request.getParameter("email");
            String firstname = request.getParameter("firstname");
            String lastname = request.getParameter("lastname");
            String password = request.getParameter("password");
            String previousUsername = (String) session.getAttribute("previousUsername");
          

            if (username != null && !username.trim().equals("")
                    && email != null && !email.trim().equals("")
                    && firstname != null && !firstname.trim().equals("")
                    && lastname != null && !lastname.trim().equals("") && email.contains("@")){
            if (adminAction.equals("Add User")) {

                accountService.insert(username, email, firstname, lastname, password);
                userList =  (Vector<Users>) accountService.getAll();

                    request.setAttribute("userList", userList);
                getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);
                return;
            } else if (adminAction.equals("Edit User") ) {

                if(username.equals(previousUsername)){
                accountService.update(previousUsername, email, firstname, lastname, password);
                userList =  (Vector<Users>) accountService.getAll();

                    request.setAttribute("userList", userList);
                getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);
                return;
}else{
                userList =  (Vector<Users>) accountService.getAll();
                   
                    request.setAttribute("userList", userList);
                       request.setAttribute("error", "username cannot be changed");
            request.setAttribute("errorExist", true);
                getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);
                return;   
                }
            }
            }else{
             request.setAttribute("error", "user info is invalid");
            request.setAttribute("errorExist", true);
             userList =(Vector<Users>) accountService.getAll();
            
                    request.setAttribute("userList", userList);
                                session.setAttribute("adminAction", "Add User");
      request.setAttribute("isAdmin", true);
                getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);
                return;
            }
        } catch (SQLException e) {
            request.setAttribute("error", "the username is existed already");
            request.setAttribute("errorExist", true);
             getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);

        } catch (Exception ex) {
            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}

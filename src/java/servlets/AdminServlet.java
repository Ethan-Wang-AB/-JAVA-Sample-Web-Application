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

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Company;
import models.Role;
import models.User;
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
            String emailAdmin = (String) session.getAttribute("email");
            AccountService accountService = new AccountService();
            List<Role> roles=accountService.getAllRole();
            request.setAttribute("roles", roles);
            String adminAction = request.getParameter("action");
           List<User> userList = accountService.getAll();
  
//            String email = "";
//            String firstName = "";
//            String lastName = "";
//            String password = "";
          
             List<Company> companies=accountService.getAllCompany();
           request.setAttribute("companies",companies);

            if (request.getParameterMap().containsKey("action")) {
                session.setAttribute("adminAction", "Edit User");

            } else {
                session.setAttribute("adminAction", "Add User");

            }

            if (request.getParameter("email") != null) {
                if (request.getParameter("action").equals("edit")) {
                    session.setAttribute("previousEmail",emailAdmin);
                    String email = request.getParameter("email");
                    User user = accountService.get(emailAdmin);

                    request.setAttribute("email", user.getEmail());
              
                    request.setAttribute("fistName", user.getFirstName());
                    request.setAttribute("lastName", user.getLastName());
                    request.setAttribute("password", user.getPassword());
                    userList = accountService.getAll();
                    request.setAttribute("userList", userList);
                    request.setAttribute("isEdit", true);
                    getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);
                    return;

                } else if (request.getParameter("action").equals("delete")) {
                 
                    String email = request.getParameter("email");
                    if (emailAdmin.equals(email)) {
                        request.setAttribute("error", "You cannot delete yourself");
                        request.setAttribute("errorExist", true);
                    } else {
                        accountService.delete(email);
                        request.setAttribute("deletion", true);
                        request.setAttribute("emailDeleted",email);
                                
                    }
                    userList = accountService.getAll();
                    session.setAttribute("adminAction", "Add User");
                    request.setAttribute("userList", userList);
                    request.setAttribute("isEdit", false);
                    getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);
                    return;
                }
            } else {
                if(request.getParameterMap().containsKey("undoDelete")){
                String emailDeleted=request.getParameter("emailDeleted");
                System.out.println("emailEdleted in admin servlet "+ emailDeleted);
                if(emailDeleted!=null){
                    User user=accountService.get(emailDeleted.trim());
                    
                accountService.recovery(user);
                }
                }
                userList =  accountService.getAll();

                request.setAttribute("userList", userList);
                request.setAttribute("isEdit", false);
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
        System.out.println("adminAction: " + adminAction);
        AccountService accountService = new AccountService();
       
      
        try {
           List<User> userList = accountService.getAll();
              List<Role> roles=accountService.getAllRole();
            request.setAttribute("roles", roles);
             List<Company> companies=accountService.getAllCompany();
             for(int i=0;i<companies.size();i++){
             System.out.println("list company: "+companies.get(i).getCompanyName());
             }
             
             
             
             
           request.setAttribute("companies",companies);
            request.setAttribute("userList", userList);
        
            String email = request.getParameter("email");
            String firstname = request.getParameter("firstname");
            String lastname = request.getParameter("lastname");
            String password = request.getParameter("password");
            String previousEmail = (String) session.getAttribute("previousEmail");
            String status=request.getParameter("status");
            
            boolean active=true ? status.equals("true")  : false;

            if (email!= null && !email.trim().equals("")
                   && firstname != null && !firstname.trim().equals("")
                    && lastname != null && !lastname.trim().equals("") && email.contains("@")) {
                if (adminAction.equals("Add User")) {
                    String roleName=request.getParameter("roleName");
                    Role role=accountService.getRole(roleName);
                    
                    String companyName=request.getParameter("companyName");
                            
                    Company company=accountService.getCompany(companyName);
                    accountService.insert( email, firstname, lastname, password,role,company);
                    userList =  accountService.getAll();

                    request.setAttribute("userList", userList);
                    getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);
                    return;
                } else if (adminAction.equals("Edit User")) {

                    if (email.equals(previousEmail)) {
                           String roleName=request.getParameter("roleName");
                    Role role=accountService.getRole(roleName);
                        accountService.update(email, firstname, lastname,role, password,active);
                        userList =  accountService.getAll();

                        request.setAttribute("userList", userList);
                        getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);
                        return;
                    } else {
                        userList =  accountService.getAll();

                        request.setAttribute("userList", userList);
                        request.setAttribute("error", "email cannot be changed");
                        request.setAttribute("errorExist", true);
                        getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);
                        return;
                    }
                }
            } else {
                request.setAttribute("error", "user info is invalid");
                request.setAttribute("errorExist", true);
                userList =  accountService.getAll();

                request.setAttribute("userList", userList);
                session.setAttribute("adminAction", "Add User");
                request.setAttribute("isAdmin", true);
                getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);
                return;
            }
        } catch (SQLException e) {
            request.setAttribute("error", "the email is existed already");
            request.setAttribute("errorExist", true);
            getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);

        } catch (Exception ex) {
            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}

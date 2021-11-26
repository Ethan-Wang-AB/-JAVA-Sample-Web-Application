/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Company;
import models.User;
import services.AccountService;
import services.CompanyService;

/**
 *
 * @author 845593
 */
public class CompanyAdminServlet extends HttpServlet {

   

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         try {
            HttpSession session = request.getSession();
                            session.setAttribute("isCompanyAdmin", true);

            AccountService accountService = new AccountService();

            String adminAction = request.getParameter("action");
            String emailAdmin=(String) session.getAttribute("email");
            System.out.println("Company Admin++" + emailAdmin);
            String email = "";
            String firstName = "";
            String lastName = "";
            String password = "";
            User user=accountService.get(emailAdmin);
            
            CompanyService companyService =new CompanyService();
              Company company=user.getCompanyID();
                   request.setAttribute("companyName",company.getCompanyName());
            List<User> userList=companyService.getUserList(company);
            request.setAttribute("userList", userList);
           request.setAttribute("companyList",company.getCompanyName());

            if (request.getParameterMap().containsKey("action")) {
                session.setAttribute("adminAction", "Edit User");

            } else {
                session.setAttribute("adminAction", "Add User");

            }

            if (request.getParameter("email") != null) {
                if (request.getParameter("action").equals("edit")) {

                    email = request.getParameter("email");
                    user = accountService.get(email);

                    request.setAttribute("email", user.getEmail());
                 
                    request.setAttribute("fistName", user.getFirstName());
                    request.setAttribute("lastName", user.getLastName());
                    request.setAttribute("password", user.getPassword());
                   
                    userList =  companyService.getUserList(company);
                    
                    request.setAttribute("userList", userList);
                    request.setAttribute("isEdit", true);
                    getServletContext().getRequestDispatcher("/WEB-INF/companyadmin.jsp").forward(request, response);
                    return;

                } else if (request.getParameter("action").equals("delete")) {

                    email = request.getParameter("email");
                    if (emailAdmin.equals(email)) {
                        request.setAttribute("error", "You cannot delete yourself");
                        request.setAttribute("errorExist", true);
                    } else {
                        accountService.delete(email);
                    }
                   
                   
                    System.out.println("companyadmin servlet delete from company   +"+company.getUserList());

                    userList = companyService.getUserList(company);
                    session.setAttribute("adminAction", "Add User");
                    request.setAttribute("userList", userList);
                    request.setAttribute("isEdit", false);
                    getServletContext().getRequestDispatcher("/WEB-INF/companyadmin.jsp").forward(request, response);
                    return;
                }
            } else {

                userList = companyService.getUserList(company);

                request.setAttribute("userList", userList);
                request.setAttribute("isEdit", false);
                session.setAttribute("adminAction", "Add User");
            }
       
            getServletContext().getRequestDispatcher("/WEB-INF/companyadmin.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
  
        
        
      
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        
        
    }

    

}

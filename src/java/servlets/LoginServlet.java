/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
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
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate(); // just by going to the login page the user is logged out :-) 
       if(request.getParameterMap().containsKey("logout")){
                   request.setAttribute("error", "You are successfully log out");
            request.setAttribute("errorExist", true);
                    getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);

       
       }else if(request.getParameterMap().containsKey("registration")){
          request.setAttribute("errorExist",true);
            request.setAttribute("error","registration successful");
        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }else if(request.getParameterMap().containsKey("twoway")){
      request.setAttribute("error", "Two way authentication activated");
            request.setAttribute("errorExist", true);
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);

    }
       else{
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
       }
}

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        // System.out.println("doPost in login:::  "+username+"   "+password);
        AccountService as = new AccountService();
        HttpSession session=request.getSession();
        User user = as.login(email, password);

        if (user == null) {
            request.setAttribute("error", "The username or password is invalid");
            request.setAttribute("errorExist", true);
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            return;
        }
        String twoway=(String) session.getAttribute("twoway");
        System.out.println("two way login attribute   "+twoway);
                
        if(user!=null && twoway==null && user.getAuthenUuid()!=null && !user.getAuthenUuid().equals(""))
        {
            session.setAttribute("emailTwoWay",email);
            response.sendRedirect("authentication");
            return;
        }
      
        session.setAttribute("email", email);
        

        if (user.getRole().getRoleId()==1) {
        
            response.sendRedirect("admin");
        } else if(user.getRole().getRoleId()==3){
              response.sendRedirect("companyadmin");

        }
        else{
            response.sendRedirect("inventory");
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filters;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 845593
 */
public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException { }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    
        
        
    HttpSession session = ((HttpServletRequest)request).getSession();
    String username =(String)session.getAttribute("username");
    if(username==null){
        System.out.println("Authentication Null");
  
       request.setAttribute("error", "The username or password is invalid");
       request.setAttribute("errorExist", true);
                
    ((HttpServletResponse)response).sendRedirect("login");
    return;
    }
    /*else{
          ((HttpServletResponse)response).sendRedirect("notes");
          return;
   }*/
    
        
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {}
    
    


}

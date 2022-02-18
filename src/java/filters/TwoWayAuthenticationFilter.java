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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.User;
import services.AccountService;

/**
 *
 * @author 845593
 */
@WebFilter(filterName = "TwoWayAuthenticationFilter", servletNames = {"TwoWayAuthentication"})
public class TwoWayAuthenticationFilter implements Filter {
    
  
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        try {
            HttpSession session=((HttpServletRequest)request).getSession();
            String emailTwoWay=(String) session.getAttribute("emailTwoWay");
            
           // System.out.println("Two Way filter   " +emailTwoWay);
            AccountService accountService=new AccountService();
            User user=accountService.get(emailTwoWay);
            if(user==null || user.getAuthenUuid()==null || user.getAuthenUuid().equals("")){
          ((HttpServletResponse)response).sendRedirect("login");
                return;
            }
                        chain.doFilter(request,response);

        } catch (Exception ex) {
            Logger.getLogger(TwoWayAuthenticationFilter.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
 
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }

  
    
}

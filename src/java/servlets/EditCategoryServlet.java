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
import models.Category;
import models.User;
import services.AccountService;
import services.CategoryService;

/**
 *
 * @author 845593
 */
public class EditCategoryServlet extends HttpServlet {

   

  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session=request.getSession();
            String email=(String) session.getAttribute("email");
            AccountService accountService=new AccountService();
            User user=accountService.get(email);
            if(user.getRole().getRoleId()==1){
            request.setAttribute("isAdmin",true);
            }
            String action=request.getParameter("action");
            String categoryIdS=request.getParameter("categoryId");
            CategoryService categoryService=new CategoryService();
            List<Category> categories=categoryService.getAll();
                  request.setAttribute("categories",categories);
             if(action!=null && action.equals("edit") && categoryIdS!=null ){
             request.setAttribute("action", "edit");
             Integer categoryId=Integer.parseInt(categoryIdS);
             Category category=categoryService.get(categoryId);  
             request.setAttribute("categoryName",category.getCategoryName());
             request.setAttribute("categoryId",categoryId);
             

             
             }else{
              request.setAttribute("action", "add");

             
             }
              getServletContext().getRequestDispatcher("/WEB-INF/editcategory.jsp").forward(request, response);

            
        } catch (Exception ex) {
            Logger.getLogger(EditCategoryServlet.class.getName()).log(Level.SEVERE, null, ex);
                          getServletContext().getRequestDispatcher("/WEB-INF/editcategory.jsp").forward(request, response);

        }
        
        
        
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String action=request.getParameter("action");
            String categoryIdS=request.getParameter("categoryId");
            System.out.println("category Id+++" + categoryIdS);
            CategoryService categoryService=new CategoryService();
            List<Category> categories=categoryService.getAll();
              request.setAttribute("categories", categories);
            if(action.equals("edit")){
                Integer categoryId=Integer.parseInt(request.getParameter("categoryId"));
                Category category=categoryService.get(categoryId);
                String categoryName=request.getParameter("categoryName");
                category.setCategoryName(categoryName);
                categoryService.update(category);
                request.setAttribute("categoryId",categoryId);
                request.setAttribute("categories", categoryService.getAll());
                getServletContext().getRequestDispatcher("/WEB-INF/editcategory.jsp").forward(request, response);

                
            }
            else if(action.equals("add")){
                  
            
                String categoryName=request.getParameter("categoryName");
          
                categoryService.insert(categoryName);
                                request.setAttribute("categories", categoryService.getAll());

                  getServletContext().getRequestDispatcher("/WEB-INF/editcategory.jsp").forward(request, response);
                
                
            }
            
        } catch (Exception ex) {
            Logger.getLogger(EditCategoryServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }

  

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Categories;
import models.Items;
import models.Users;
import services.AccountService;
import services.InventoryService;

/**
 *
 * @author 845593
 */
public class InventoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
        InventoryService inventoryService = new InventoryService();
        AccountService accountService = new AccountService();
          Vector<Items>  inventoryList = new Vector();

        double total;
        try {
            if (request.getParameterMap().containsKey("action") && request.getParameterMap().containsKey("itemID")) {
                String itemID = request.getParameter("itemID");
                Boolean deletion = inventoryService.delete(username, Integer.parseInt(itemID));
                if (!deletion) {
                    request.setAttribute("errorMessage", "You cannot delete the item you do now own");
                }

            }
            if (isAdmin) {

                inventoryList =  (Vector<Items>) inventoryService.getAll();
                
                request.setAttribute("inventoryList", inventoryList);
                total = inventoryService.getTotal();
                request.setAttribute("name", "Administrator Management");
                request.setAttribute("total", total);

            } else if (isAdmin == false) {
                Users owner = accountService.get(username);
                inventoryList = (Vector<Items>) inventoryService.getByOwner(owner);
                
                request.setAttribute("inventoryList", inventoryList);
                 request.setAttribute("name", owner.getFirstName() + " "+owner.getLastName());
                total = inventoryService.getTotal(owner);
                request.setAttribute("total", total);

            }

            getServletContext().getRequestDispatcher("/WEB-INF/inventory.jsp").forward(request, response);

        } catch (Exception ex) {
            Logger.getLogger(InventoryServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("usernameLogin");
        Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
        AccountService accountService = new AccountService();
        InventoryService inventoryService = new InventoryService();
        double total;
       Vector<Items> inventoryList;
        try {
            Users user = accountService.get(username);
            String categoryS = request.getParameter("category");
            String price = request.getParameter("price");
            String name = request.getParameter("itemName");

            if (categoryS != null && !categoryS.trim().equals("")
                    && price != null && !price.trim().equals("")
                    && name != null && !name.trim().equals("")
                    && isNumeric(price)) {
                Categories category = inventoryService.getTheCategory(categoryS);
                Double priceD = Double.parseDouble(price);
                Users owner = accountService.get(username);
                inventoryService.insert(category, name, priceD, owner);
            } else {
                request.setAttribute("errorMessage", "Your input is invalid for adding new item");
            }
            if (!isAdmin) {
                Users owner = accountService.get(username);
                inventoryList = (Vector<Items>) inventoryService.getByOwner(owner);
                total = inventoryService.getTotal(owner);
                request.setAttribute("total", total);

            } else {
                inventoryList = (Vector<Items>) inventoryService.getAll();
                total = inventoryService.getTotal();

            }
                request.setAttribute("inventoryList", inventoryList);

            request.setAttribute("total", total);

            getServletContext().getRequestDispatcher("/WEB-INF/inventory.jsp").forward(request, response);
            return;

        } catch (Exception ex) {
            Logger.getLogger(InventoryServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}

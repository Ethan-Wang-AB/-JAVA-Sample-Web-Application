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
import java.util.List;

import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Category;
import models.Item;
import models.Role;
import models.User;
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
        String email = (String) session.getAttribute("email");

        InventoryService inventoryService = new InventoryService();
        AccountService accountService = new AccountService();
        List<Item> inventoryList = new ArrayList<>();
        List<Category> categories;

        double total;
        try {
            Role role = accountService.get(email).getRole();
            categories = inventoryService.getCategory();
            request.setAttribute("categories", categories);
            if (request.getParameterMap().containsKey("action") && request.getParameterMap().containsKey("itemID")) {
                System.out.println("action in inventory is : " + request.getParameter("action"));
                String itemID = request.getParameter("itemID");
                Boolean deletion = inventoryService.delete(email, Integer.parseInt(itemID));
                System.out.println("deletion boolean : " + deletion);
                if (deletion == true) {
                    request.setAttribute("errorMessage", "You cannot delete the item you do now own");
                } else {
                    inventoryService.deleteItem(Integer.parseInt(request.getParameter("itemID")));
                    System.out.println("deletion in inventory implemented");
                    User owner = accountService.get(email);
                    inventoryList =  inventoryService.getByOwner(owner);

                    request.setAttribute("inventoryList", inventoryList);
                    request.setAttribute("name", owner.getFirstName() + " " + owner.getLastName());
                    total = inventoryService.getTotal(owner);
                    request.setAttribute("total", total);
                    getServletContext().getRequestDispatcher("/WEB-INF/inventory.jsp").forward(request, response);
                    return;

                }

            }
            if (role.getRoleId()==1) {

                inventoryList =  inventoryService.getAll();

                request.setAttribute("inventoryList", inventoryList);
                total = inventoryService.getTotal();
                request.setAttribute("name", "Administrator Management");
                request.setAttribute("isAdmin", true);
                request.setAttribute("total", total);

            } else if (role.getRoleId()==2) {
                User owner = accountService.get(email);
                inventoryList = inventoryService.getByOwner(owner);

                request.setAttribute("inventoryList", inventoryList);
                request.setAttribute("name", owner.getFirstName() + " " + owner.getLastName());
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
        String username = (String) session.getAttribute("username");
        Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
        AccountService accountService = new AccountService();
        InventoryService inventoryService = new InventoryService();
        double total;
      List<Item> inventoryList;
        List<Category> categories;
        try {
            System.out.println("inventory:  " + username);
            User user = accountService.get(username);
            String categoryS = request.getParameter("category");
            String price = request.getParameter("price");
            String name = request.getParameter("itemName");
            categories =  inventoryService.getCategory();

            if (categoryS != null && !categoryS.trim().equals("")
                    && price != null && !price.trim().equals("")
                    && name != null && !name.trim().equals("")
                    && isNumeric(price)) {
                Category category = inventoryService.getTheCategory(categoryS);
                Double priceD = Double.parseDouble(price);
                User owner = accountService.get(username);
                inventoryService.insert(category, name, priceD, owner);
            } else {
                request.setAttribute("errorMessage", "Your input is invalid for adding new item");
            }
            if (!isAdmin) {
                User owner = accountService.get(username);
                inventoryList =  inventoryService.getByOwner(owner);
                total = inventoryService.getTotal(owner);
                request.setAttribute("total", total);

            } else {
                inventoryList = inventoryService.getAll();
                total = inventoryService.getTotal();

            }
            request.setAttribute("inventoryList", inventoryList);

            request.setAttribute("total", total);
            request.setAttribute("categories", categories);
            System.out.println("categories  " + categories.size());
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

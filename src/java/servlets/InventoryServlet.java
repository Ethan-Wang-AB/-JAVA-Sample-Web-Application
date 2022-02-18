/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import models.Category;
import models.Company;
import models.Item;
import models.Role;
import models.User;
import services.AccountService;
import services.CategoryService;
import services.InventoryService;

/**
 *
 * @author 845593
 */
@MultipartConfig
public class InventoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        String action = request.getParameter("action");
        InventoryService inventoryService = new InventoryService();
        String itemId = request.getParameter("itemID");

        if (action != null && action.equals("edit")) {
            request.setAttribute("action", "Edit Item");
            session.setAttribute("action", "edit");
        } else {
            request.setAttribute("action", "Add Item");
            session.setAttribute("action", "add");
        }
        AccountService accountService = new AccountService();
        List<Item> inventoryList = new ArrayList<>();
        List<Category> categories;

        double total;
        try {
            Role role = accountService.get(email).getRole();
            categories = inventoryService.getCategory();
            request.setAttribute("categories", categories);
            if (request.getParameterMap().containsKey("action") && request.getParameterMap().containsKey("itemID")) {
                // System.out.println("action in inventory is : " + request.getParameter("action"));
                if (action.equals("edit")) {
                    User owner = accountService.get(email);
                    inventoryList = inventoryService.getByOwner(owner);
                    Item item = inventoryService.getItemById(Integer.parseInt(itemId));
                    request.setAttribute("itemId", item.getItemId());
                    request.setAttribute("categoryName", item.getCategory().getCategoryName());
                    request.setAttribute("itemName", item.getItemName());
                    request.setAttribute("itemPrice", item.getPrice());
                    request.setAttribute("itemphotopath", item.getPhotoPath());

                    inventoryList = inventoryService.getByOwner(owner);

                    request.setAttribute("inventoryList", inventoryList);
                    total = inventoryService.getTotal(owner);
                    request.setAttribute("total", total);
                    getServletContext().getRequestDispatcher("/WEB-INF/inventory.jsp").forward(request, response);
                    return;

                } else if (action.equals("delete")) {

                    Boolean deletion = inventoryService.delete(email, Integer.parseInt(itemId));
                    // System.out.println("deletion boolean : " + deletion);
                    if (deletion == true) {
                        request.setAttribute("errorMessage", "You cannot delete the item you do not own");
                    } else {
                        request.setAttribute("deletion", true);
                        request.setAttribute("itemId", request.getParameter("itemID"));

                        inventoryService.deleteItem(Integer.parseInt(request.getParameter("itemID")));
                        System.out.println("deletion in inventory implemented");
                 
                    }
                }

            }
            
               if (request.getParameterMap().containsKey("undoDelete")) {
                   
                if (request.getParameter("itemId") != null) {
                   System.out.println("undo implemented in servlets");
                    Integer id = Integer.parseInt(request.getParameter("itemId"));
                    inventoryService.recovery(id);
           
                }
            }
            if (role.getRoleId() == 1) {

                inventoryList = inventoryService.getAll();
                request.setAttribute("isAdmin", true);

                request.setAttribute("name", "Administrator Management");

                request.setAttribute("inventoryList", inventoryList);
                total = inventoryService.getTotal(inventoryList);
                request.setAttribute("total", total);

            } else if (role.getRoleId() == 2) {
                User owner = accountService.get(email);
                inventoryList = inventoryService.getByOwner(owner);

                request.setAttribute("inventoryList", inventoryList);
                request.setAttribute("name", owner.getFirstName() + " " + owner.getLastName());
                total = inventoryService.getTotal(owner);
                request.setAttribute("total", total);

            } else if (role.getRoleId() == 3) {
                request.setAttribute("isCompanyAdmin", true);

                User owner = accountService.get(email);
                Company company = owner.getCompanyID();
                inventoryList = inventoryService.getByCompany(company);

                request.setAttribute("inventoryList", inventoryList);
                request.setAttribute("name", company.getCompanyName());
                total = inventoryService.getTotal(inventoryList);
                request.setAttribute("total", total);

            }
            if (request.getParameterMap().containsKey("keyword")) {
                String filter = request.getParameter("keyword");
                // System.out.println("filter in parameter + : "+filter);
                if (filter != null && !filter.equals("")) {
                    List<Item> filteredList = inventoryService.applyFilter(inventoryList, filter);

                    request.setAttribute("inventoryList", filteredList);
                    total = inventoryService.getTotal(filteredList);
                    request.setAttribute("total", total);

                }
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

        String email = (String) session.getAttribute("email");
        AccountService accountService = new AccountService();
        InventoryService inventoryService = new InventoryService();
        String action = (String) session.getAttribute("action");

        double total;
        List<Item> inventoryList;
        List<Category> categories;
        User user;
        try {
            user = accountService.get(email);
            String categoryS = request.getParameter("category");
            String price = request.getParameter("price");
            String name = request.getParameter("itemName");
            categories = inventoryService.getCategory();
            Part filePart = request.getPart("photo"); // Retrieves <input type="file" name="file">

            if (categoryS != null && !categoryS.trim().equals("")
                    && price != null && !price.trim().equals("")
                    && name != null && !name.trim().equals("")
                    && isNumeric(price)) {
                Category category = inventoryService.getTheCategory(categoryS);
                Double priceD = Double.parseDouble(price);
                if (action.equals("add")) {

                    Item item = new Item();
                    item.setCategory(category);
                    item.setItemName(name);
                    item.setPrice(priceD);

                    if (filePart != null) {
                        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
                        InputStream fileContent = filePart.getInputStream();
                        byte[] image = new byte[fileContent.available()];
                        fileContent.read(image);
                        String path = getServletContext().getRealPath("/WEB-INF");
                        Integer itemId = inventoryService.getMaxItemId() + 1;
                        String filename = path + "\\itemphoto\\" + itemId + fileName;
                        File file = new File(filename);
                        if (!file.exists()) {
                            file.createNewFile();
                        }
                        FileOutputStream writer = new FileOutputStream(file);
                        writer.write(image);
                        item.setPhotoPath(filename);
                    }
                    inventoryService.insert(item);

                } else if (action.equals("edit")) {

                    Integer itemId = Integer.parseInt(request.getParameter("itemId"));
                    Item itemToUpdate = inventoryService.getItemById(itemId);
                    if (filePart != null) {
                        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
                        InputStream fileContent = filePart.getInputStream();
                        byte[] image = new byte[fileContent.available()];
                        fileContent.read(image);
                        String path = getServletContext().getRealPath("/WEB-INF");
                        String filename = path + "\\itemphoto\\" + itemId + fileName;
                        File file = new File(filename);
                        if (!file.exists()) {
                            file.createNewFile();
                        }
                        FileOutputStream writer = new FileOutputStream(file);
                        writer.write(image);
                        itemToUpdate.setPhotoPath(filename);
                    }
                    itemToUpdate.setCategory(category);
                    itemToUpdate.setItemName(name);
                    itemToUpdate.setPrice(priceD);
                    inventoryService.update(itemToUpdate);

                }
            }
        } catch (Exception ex) {
            Logger.getLogger(InventoryServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.setAttribute("errorMessage", "Your input is invalid for adding new item");
        try {
            user = accountService.get(email);
            if (user.getRole().getRoleId() != 1) {
                User owner = accountService.get(email);
                inventoryList = inventoryService.getByOwner(owner);
                total = inventoryService.getTotal(owner);
                request.setAttribute("total", total);

            } else {
                inventoryList = inventoryService.getAll();
                total = inventoryService.getTotal();

            }
            request.setAttribute("inventoryList", inventoryList);

            request.setAttribute("total", total);
            categories = inventoryService.getCategory();
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

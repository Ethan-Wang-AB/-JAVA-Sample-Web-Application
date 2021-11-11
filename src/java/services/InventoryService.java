/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.CategoriesDB;
import dataaccess.ItemsDB;
import java.util.ArrayList;
import java.util.List;
import models.Categories;
import models.Items;
import models.Users;

/**
 *
 * @author 845593
 */
public class InventoryService {
    private ItemsDB items=new ItemsDB();
    private CategoriesDB categories=new CategoriesDB();
    private AccountService accountService=new AccountService();
    public List<Items> getAll() throws Exception{
        return items.getAll();
    
    }
    
       public List<Items> getByOwner(Users owner) throws Exception{
        return (List<Items>) owner.getItemsCollection();
    
    }
       public List<Categories> getCategory() throws Exception{
       return categories.getAll();
       }
       
           public Categories getTheCategory(String categoryName) throws Exception{
       return categories.get(categoryName);
       }
       
       public boolean delete(String username, Integer itemID) throws Exception{
          
           Items item=items.get(itemID);
           if(item.getOwner().equals(username)){
           items.delete(itemID);
           return true;
           }
           else{
           return false;
           }
           
           
       }
       public void insert(Categories category, String itemName, double price,Users owner) throws Exception{
           items.insert(category, itemName, price, owner);
           
       }
       
       public double getTotal() throws Exception{
      
           double total=0; 
           ArrayList<Items>items=(ArrayList<Items>) getAll();
            for(int i=0;i<items.size();i++){
            total += items.get(i).getPrice();
            }
          return total;
       
       }
       
       public double getTotal(Users owner) throws Exception{
          
           double total=0; 
           ArrayList<Items>items=(ArrayList<Items>) getByOwner(owner);
            for(int i=0;i<items.size();i++){
            total += items.get(i).getPrice();
            }
          return total;
       }

}

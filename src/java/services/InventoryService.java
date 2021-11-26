/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.CategoriesDB;
import dataaccess.ItemsDB;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;
import models.Category;
import models.Item;
import models.User;

/**
 *
 * @author 845593
 */
public class InventoryService {
    private ItemsDB items=new ItemsDB();
    private CategoriesDB categories=new CategoriesDB();
    private AccountService accountService=new AccountService();
    public List<Item> getAll() throws Exception{
        return items.getAll();
    
    }
    
       public List<Item> getByOwner(User owner) throws Exception{
        
           return   owner.getItemList();
    
    }
       public List<Category> getCategory() throws Exception{
       return categories.getAll();
       }
       
           public Category getTheCategory(String categoryName) throws Exception{
       return categories.get(categoryName);
       }
       
       public boolean delete(String email, Integer itemID) throws Exception{
          
           Item item=items.get(itemID);
           if(item.getOwner().getEmail().equals(email)){
           items.delete(itemID);
           return true;
           }
           else{
           return false;
           }
           
           
       }
       
       public void deleteItem(Integer itemID) throws Exception{
       items.delete(itemID);
       }
       public void insert(Category category, String itemName, double price,User owner) throws Exception{
           items.insert(category, itemName, price, owner);
           
       }
       
       public double getTotal() throws Exception{
      
           double total=0; 
         List<Item>itemsVector= items.getAll();
           for(int i=0;i<itemsVector.size();i++){
           total=total+itemsVector.get(i).getPrice();
           }
       return Math.round(total*100)/100.0;
       
       }
       
       public double getTotal(User owner) throws Exception{
          
           double total=0; 
          List<Item>itemsArray;
        itemsArray = getByOwner(owner);
            for(int i=0;i<itemsArray.size();i++){
            total += itemsArray.get(i).getPrice();
            }
          return  Math.round(total*100)/100.0;
       }

}

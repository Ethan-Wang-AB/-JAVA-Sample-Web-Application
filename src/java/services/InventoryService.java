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
    
       public Vector<Items> getByOwner(Users owner) throws Exception{
        
           return   (Vector<Items>) owner.getItemsCollection();
    
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
       
       public void deleteItem(Integer itemID) throws Exception{
       items.delete(itemID);
       }
       public void insert(Categories category, String itemName, double price,Users owner) throws Exception{
           items.insert(category, itemName, price, owner);
           
       }
       
       public double getTotal() throws Exception{
      
           double total=0; 
         Vector<Items>itemsVector=(Vector<Items>) items.getAll();
           for(int i=0;i<itemsVector.size();i++){
           total=total+itemsVector.get(i).getPrice();
           }
       return Math.round(total*100)/100.0;
       
       }
       
       public double getTotal(Users owner) throws Exception{
          
           double total=0; 
          Vector<Items>itemsArray;
        itemsArray = getByOwner(owner);
            for(int i=0;i<itemsArray.size();i++){
            total += itemsArray.get(i).getPrice();
            }
          return  Math.round(total*100)/100.0;
       }

}

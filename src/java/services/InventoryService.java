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
import models.Company;
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
        List<Item> itemAll=items.getAll();
          for(int i=itemAll.size()-1;i>=0;i--){
          if(itemAll.get(i).getDisplay()==false){
          itemAll.remove(i);
          }
          }
          return itemAll;
        
    
    }
    
       public List<Item> getByOwner(User owner) throws Exception{
          List <Item> itemOwner=owner.getItemList();
          for(int i=itemOwner.size()-1;i>=0;i--){
          if(itemOwner.get(i).getDisplay()==false){
          itemOwner.remove(i);
          }
          }
          return itemOwner;
           
    
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
       
       public void insert(Item item) throws Exception{
           items.insert(item);
           
       }
       
       public double getTotal() throws Exception{
      
           double total=0; 
         List<Item>itemsVector= getAll();
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
       
       
       public Item getItemById(Integer itemId) throws Exception{
       
       return items.get(itemId);
       
       }
       
       
       public List<Item> getByCompany(Company company){
       List<Item> items=new ArrayList<>();
       List<User> users=company.getUserList();
       for(int i =0;i<users.size();i++){
         List<Item> itemU=users.get(i).getItemList();
         for(int j=0;j<itemU.size();j++){
         items.add(itemU.get(i));
         }
    
       }
       return items;
       
       }
       
       public double getTotal(List<Item> lists){
       double total=0;
       for(int i=0;i<lists.size();i++){
       total=total+lists.get(i).getPrice();
       }
      return Math.round(total*100)/100.0;
       
       
       }
       
       public void update(Item item){
       items.update(item);
       }
       
       public Integer getMaxItemId() throws Exception{
           
          return items.getMaxId();
       
       }
       
       public void recovery(Integer itemId) throws Exception{
       items.recovery(itemId);
       }

       public List<Item> applyFilter(List<Item> itemList,String filter) throws Exception{
    
        List<Item> filteredList=new ArrayList<>();
        for(int i=itemList.size()-1;i>=0;i--){
      
            if(itemList.get(i).getItemName().toLowerCase().contains(filter.toLowerCase())){
        filteredList.add(itemList.get(i));
        }
        
        }
       return filteredList;
       }
}

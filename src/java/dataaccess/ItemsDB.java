/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import models.Category;
import models.Item;
import models.User;

/**
 *
 * @author 845593
 */
public class ItemsDB {
    public List<Item> getAll() throws Exception {
    
         EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try {
            List<Item> lists;
             lists =  em.createNamedQuery("Item.findAll",Item.class).getResultList();
            return lists;
        } finally {
            em.close();
        }
    }   
    
  /*    public List<Items> getByOwner(String owner) throws Exception {
    
         EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try {
            List<Items> lists=new ArrayList<>();
             lists = em.createNamedQuery("User.getByOwner",Items.class).setParameter("owner", owner).getResultList();
            return lists;
        } finally {
            em.close();
        }
    }  
*/
    public Item get(Integer itemID) throws Exception {
    
         EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try {
            Item item;
              item= em.find(Item.class,itemID);
            return item;
        } finally {
            em.close();
        }
    }  
public void insert( Category category, String name, double price, User owner) throws Exception {
                     EntityManager em = DBUtil.getEmFactory().createEntityManager();
             EntityTransaction trans=em.getTransaction();
        try {
            Item item=new Item();            
           item.setCategory(category);
           item.setItemName(name);
           item.setPrice(price);
           item.setOwner(owner);
           owner.getItemList().add(item);
           category.getItemList().add(item);
             trans.begin();
             em.persist(item);
             em.merge(owner);
             em.merge(category);
             trans.commit();
        } catch(Exception ex){
        trans.rollback();
        } finally {
            em.close();
        }
        
   
    }
public void delete(Integer itemID) throws Exception {
                       EntityManager em = DBUtil.getEmFactory().createEntityManager();
             EntityTransaction trans=em.getTransaction();
        try {
      
            Item item;
            
             item = em.find(Item.class,itemID);
            User user=item.getOwner();
            user.getItemList().remove(item);
            Category category=item.getCategory();
            category.getItemList().remove(item);
             trans.begin();
             em.remove(item);
             em.merge(user);
             em.merge(category);
             trans.commit();
          
        }catch(Exception ex){
        trans.rollback();
        } 
        finally {
            em.close();
        }
        
   
    }

}

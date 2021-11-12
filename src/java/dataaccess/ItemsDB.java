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
import models.Categories;
import models.Items;
import models.Users;

/**
 *
 * @author 845593
 */
public class ItemsDB {
    public List<Items> getAll() throws Exception {
    
         EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try {
            List<Items> lists=new ArrayList<>();
             lists =  (List<Items>) em.createNamedQuery("Items.findAll",Items.class).getResultList();
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
    public Items get(Integer itemID) throws Exception {
    
         EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try {
            Items item;
              item= em.find(Items.class,itemID);
            return item;
        } finally {
            em.close();
        }
    }  
public void insert( Categories category, String name, double price, Users owner) throws Exception {
                     EntityManager em = DBUtil.getEmFactory().createEntityManager();
             EntityTransaction trans=em.getTransaction();
        try {
            Items item=new Items();            
           item.setCategory(category);
           item.setItemName(name);
           item.setPrice(price);
           item.setOwner(owner);
           owner.getItemsCollection().add(item);
           category.getItemsCollection().add(item);
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
      
            Items item;
            
             item = em.find(Items.class,itemID);
            Users user=item.getOwner();
            user.getItemsCollection().remove(item);
            Categories category=item.getCategory();
            category.getItemsCollection().remove(item);
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

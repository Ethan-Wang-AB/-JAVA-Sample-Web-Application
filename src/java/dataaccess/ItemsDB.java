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
              item= em.createNamedQuery("Item.findByItemId",Item.class).setParameter("itemId",itemID).getSingleResult();
            return item;
        } finally {
            em.close();
        }
    }
    
    public Integer getMaxId(){
         EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try {
            
              Integer item= em.createNamedQuery("Item.findMaxId",Integer.class).getSingleResult();
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


public void insert( Item item) throws Exception {
                     EntityManager em = DBUtil.getEmFactory().createEntityManager();
             EntityTransaction trans=em.getTransaction();
        try {
      
           item.getOwner().getItemList().add(item);
           item.getCategory().getItemList().add(item);
             trans.begin();
             em.persist(item);
             em.merge(item.getOwner());
             em.merge(item.getCategory());
             trans.commit();
        } catch(Exception ex){
        trans.rollback();
        } finally {
            em.close();
        }
        
   
    }


public void update(Item item){
    EntityManager em = DBUtil.getEmFactory().createEntityManager();
     EntityTransaction trans=em.getTransaction();
 try {
        
             trans.begin();
             em.merge(item);
           
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
             item.setDisplay(false);
          
          
             trans.begin();
             em.merge(item);
          
             trans.commit();
          
        }catch(Exception ex){
        trans.rollback();
        } 
        finally {
            em.close();
        }
        
   
    }


public void recovery(Integer itemID) throws Exception {
                       EntityManager em = DBUtil.getEmFactory().createEntityManager();
             EntityTransaction trans=em.getTransaction();
//        try {
      
            Item item;
            
             item = em.find(Item.class,itemID);
             item.setDisplay(true);
           
          
             trans.begin();
             em.merge(item);
             System.out.println("merge recovery itrem "+item);
             trans.commit();
          
//        }catch(Exception ex){
//        trans.rollback();
//        } 
//        finally {
//            em.close();
//        }
        
   
    }
}

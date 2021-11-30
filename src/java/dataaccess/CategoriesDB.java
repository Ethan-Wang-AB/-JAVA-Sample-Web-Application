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

/**
 *
 * @author 845593
 */
public class CategoriesDB {

    public List<Category> getAll() throws Exception {

        EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try {
            List<Category> lists = new ArrayList<>();
            lists = em.createNamedQuery("Category.findAll", Category.class).getResultList();
            return lists;
        } finally {
            em.close();
        }
    }

    public Category get(String name) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try {
            System.out.println("Category get : "+name);
            Category category = em.createNamedQuery("Category.findByCategoryName", Category.class).setParameter("categoryName", name).getSingleResult();
            return category;
        } finally {
            em.close();
        }
    }
    
    public void update(Category category) throws Exception {
                EntityManager em = DBUtil.getEmFactory().createEntityManager();
             EntityTransaction trans=em.getTransaction();
        try {
            //System.out.println("userDB update       "+ email+"   "+status+"     "+role+"     "+email+"     "+firstName);
            
     
             
             trans.begin();
             em.merge(category);
           
             trans.commit();
          
        } catch(Exception ex){
        trans.rollback();
        } finally {
            em.close();
        }
        
   
    }
    
     public Category get(Integer categoryId) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try {
            System.out.println("Category get : "+categoryId);
            Category category = em.createNamedQuery("Category.findByCategoryId", Category.class).setParameter("categoryId", categoryId).getSingleResult();
            return category;
        } finally {
            em.close();
        }
    }
      
     public void insert(String categoryName){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
             EntityTransaction trans=em.getTransaction();
        try {
            //System.out.println("userDB update       "+ email+"   "+status+"     "+role+"     "+email+"     "+firstName);
            
             
             Category category=new Category(getMaxId()+1,categoryName,true);
             trans.begin();
             em.persist(category);
           
             trans.commit();
          
        } catch(Exception ex){
        trans.rollback();
        } finally {
            em.close();
        }
     }

      public Integer getMaxId(){
         EntityManager em = DBUtil.getEmFactory().createEntityManager();

//        try {
            
              Integer item= em.createNamedQuery("Categjory.Category.findMaxId",Integer.class).getSingleResult();
            return item;
//        } finally {
//            em.close();
//        }
    }
}

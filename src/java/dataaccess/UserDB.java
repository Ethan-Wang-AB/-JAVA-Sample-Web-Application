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
public class UserDB {
     public List<Users> getAll() throws Exception {
    
         EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try {
            List<Users> lists=new ArrayList<>();
             lists = em.createNamedQuery("Users.findAll",Users.class).getResultList();
            return lists;
        } finally {
            em.close();
        }
    }

    public Users get(String username) throws Exception {
           EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try {
            Users user;
             user = em.find(Users.class,username);
            return user;
        } finally {
            em.close();
        }
    }

public void update(String usernameP,String usernameN, String email, String firstName,String lastName,boolean status,boolean isAdmin,String password) throws Exception {
                EntityManager em = DBUtil.getEmFactory().createEntityManager();
             EntityTransaction trans=em.getTransaction();
        try {
            Users user;
             user = em.find(Users.class,usernameP);
             user.setUsername(usernameN);
             user.setFirstName(firstName);
             user.setLastName(lastName);
             user.setActive(status);
             user.setPassword(password);
       
             user.setIsAdmin(isAdmin);
                   trans.begin();
             em.merge(user);
             trans.commit();
          
        } catch(Exception ex){
        trans.rollback();
        } finally {
            em.close();
        }
        
   
    }



public void delete(String username) throws Exception {
                       EntityManager em = DBUtil.getEmFactory().createEntityManager();
             EntityTransaction trans=em.getTransaction();
        try {
      
            Users user;
             user = em.find(Users.class,username);
                   trans.begin();
             em.remove(user);
             trans.commit();
          
        }catch(Exception ex){
        trans.rollback();
        } 
        finally {
            em.close();
        }
        
   
    }

  
  public void insert(  String username,String email,String password,String firstname,String lastname) throws Exception {
                     EntityManager em = DBUtil.getEmFactory().createEntityManager();
             EntityTransaction trans=em.getTransaction();
        try {
            Users user=new Users();            
            user.setUsername(username);
            user.setEmail(email);
            user.setPassword(password);
            user.setFirstName(firstname);
            user.setLastName(lastname);

             trans.begin();
             em.persist(user);
             trans.commit();
        } catch(Exception ex){
        trans.rollback();
        } finally {
            em.close();
        }
        
   
    }


}

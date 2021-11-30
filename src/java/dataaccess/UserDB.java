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
import models.Company;
import models.Item;
import models.Role;
import models.User;

/**
 *
 * @author 845593
 */
public class UserDB {
     public List<User> getAll() throws Exception {
    
         EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try {
            List<User> lists=new ArrayList<>();
             lists = em.createNamedQuery("User.findAll",User.class).getResultList();
            return lists;
        } finally {
            em.close();
        }
    }

    public User get(String email) throws Exception {
           EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try {
            User user;
            //System.out.println("userDB  "+email);
             user = em.createNamedQuery("User.findByEmail",User.class).setParameter("email", email).getSingleResult();
            return user;
        } finally {
            em.close();
        }
    }

public void update(String email, String firstName,String lastName,boolean status,Role role,String password,boolean active) throws Exception {
                EntityManager em = DBUtil.getEmFactory().createEntityManager();
             EntityTransaction trans=em.getTransaction();
        try {
          //  System.out.println("userDB update       "+ email+"   "+status+"     "+role+"     "+email+"     "+firstName);
            
            User user;
             user = em.createNamedQuery("User.findByEmail",User.class).setParameter("email", email).getSingleResult();
             Role rolePrevious=user.getRole();
             role.getUserList().remove(user);
             user.setFirstName(firstName);
             user.setLastName(lastName);
             user.setActive(status);
             user.setPassword(password);
             user.setActive(active);
       
             user.setRole(role);
             role.getUserList().add(user);
             trans.begin();
             em.merge(user);
             em.merge(role);
             em.merge(rolePrevious);
             trans.commit();
          
        } catch(Exception ex){
        trans.rollback();
        } finally {
            em.close();
        }
        
   
    }


public void update(User user) throws Exception {
                EntityManager em = DBUtil.getEmFactory().createEntityManager();
             EntityTransaction trans=em.getTransaction();
        try {
            //System.out.println("userDB update       "+ email+"   "+status+"     "+role+"     "+email+"     "+firstName);
            
     
             
             trans.begin();
             em.merge(user);
           
             trans.commit();
          
        } catch(Exception ex){
        trans.rollback();
        } finally {
            em.close();
       }
//        
   
    }
public void delete(String email) throws Exception {
                       EntityManager em = DBUtil.getEmFactory().createEntityManager();
             EntityTransaction trans=em.getTransaction();
        try {
             User user;
             user = em.createNamedQuery("User.findByEmail",User.class).setParameter("email", email).getSingleResult();
               System.out.println("userDB deleteion"+ user);
             user.setDisplay(false);
             Role role=user.getRole();
             Company company=user.getCompanyID();
             company.getUserList().remove(user);
             ItemsDB itemsDB=new ItemsDB();
             List<Item> list=user.getItemList();
             for(int i=list.size()-1;i>=0;i--){
             list.get(i).setDisplay(false);
             }
           //  System.out.println("item list in user db deletion  "+list);
//             role.getUserList().remove(user);
//             System.out.println("userDB delete from company   +"+company.getUserList());
//            
             trans.begin();
             
             em.merge(user);
            // em.merge(list);
         
             
             trans.commit();
            // System.out.println(company.getUserList());
          
        }catch(Exception ex){
        trans.rollback();
        } 
        finally {
            em.close();
        }
        
   
    }


public void recovery(User user) throws Exception {
             EntityManager em = DBUtil.getEmFactory().createEntityManager();
             EntityTransaction trans=em.getTransaction();
//        try {
         
             user.setDisplay(true);
           
             
             ItemsDB itemsDB=new ItemsDB();
             List<Item> list=user.getItemList();
             for(int i=list.size()-1;i>=0;i--){
             list.get(i).setDisplay(true);
             }
             System.out.println("item list in user db recovery "+list);
//             role.getUserList().remove(user);
//             System.out.println("userDB delete from company   +"+company.getUserList());
//            
             trans.begin();
             
             em.merge(user);
            // em.merge(list);
         
             
             trans.commit();
          
//        }catch(Exception ex){
//        trans.rollback();
//        } 
//        finally {
//            em.close();
//        }
        
   
    }

  
  public void insert(String email,String password,String firstname,String lastname,Role role,Company company) throws Exception {
                     EntityManager em = DBUtil.getEmFactory().createEntityManager();
             EntityTransaction trans=em.getTransaction();
        try {
            User user=new User();            
             user.setEmail(email);
            user.setPassword(password);
            user.setFirstName(firstname);
            user.setLastName(lastname);
            user.setRole(role);
            user.setCompanyID(company);
            user.setActive(true);
            
            
             trans.begin();
             em.persist(user);
             trans.commit();
        } catch(Exception ex){
        trans.rollback();
        } finally {
            em.close();
        }
        
   
    }

public User getByUUID(String uuid){
         EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {
   
            User user = em.createNamedQuery("User.findByResetPasswordUuid",User.class).setParameter("resetPasswordUuid",uuid).getSingleResult();
              //System.out.println("uuid in userDB after"+user.getResetPasswordUuid());
            return user;
        } finally {
            em.close();
        }
   
   }

public List<User> getByCompany(Company company){
         EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {
   
            List<User> list = em.createNamedQuery("User.findByCompanyID",User.class).setParameter("comgpanyID",company).getResultList();
             // System.out.println("uuid in userDB after"+user.getResetPasswordUuid());
            return list;
        } finally {
            em.close();
        }
   
   }
}

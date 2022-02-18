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
import models.Company;
import models.User;


/**
 *
 * @author 845593
 */
public class CompanyDB {
      public List<Company> getAll() throws Exception {

        EntityManager em = DBUtil.getEmFactory().createEntityManager();

      //  try {
            List<Company> lists = new ArrayList<>();
            lists = em.createNamedQuery("Company.findAll", Company.class).getResultList();
            return lists;
       // } finally {
       //     em.close();
       // }
    }

    public Company get(String name) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try {
          
            Company company = em.createNamedQuery("Company.findByCompanyName", Company.class).setParameter("companyName", name).getSingleResult();
            return company;
        } finally {
            em.close();
        }
    }
    
    
    
    public void update(Company company){
            EntityManager em = DBUtil.getEmFactory().createEntityManager();

                EntityTransaction trans=em.getTransaction();
        try {
            //System.out.println("userDB update       "+ email+"   "+status+"     "+role+"     "+email+"     "+firstName);
                        
             trans.begin();
             em.merge(company);
           
             trans.commit();
          
        } catch(Exception ex){
        trans.rollback();
        } finally {
            em.close();
       }
}
    
    public List<User> getUserList(Company company){
         EntityManager em = DBUtil.getEmFactory().createEntityManager();
         
         return company.getUserList();

    
    }
}


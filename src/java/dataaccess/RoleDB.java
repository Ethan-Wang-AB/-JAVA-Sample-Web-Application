/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import models.Role;


/**
 *
 * @author 845593
 */
public class RoleDB {
     public List<Role> getAll() throws Exception {

        EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try {
            List<Role> lists = new ArrayList<>();
            lists = em.createNamedQuery("Categories.findAll", Role.class).getResultList();
            return lists;
        } finally {
            em.close();
        }
    }

    public Role get(String name) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try {
            System.out.println("Category get : "+name);
            Role role = em.createNamedQuery("Role.findByRoleName", Role.class).setParameter("roleName", name).getSingleResult();
            return role;
        } finally {
            em.close();
        }
    }
}



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import models.Categories;

/**
 *
 * @author 845593
 */
public class CategoriesDB {

    public List<Categories> getAll() throws Exception {

        EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try {
            List<Categories> lists = new ArrayList<>();
            lists = em.createNamedQuery("Categories.findAll", Categories.class).getResultList();
            return lists;
        } finally {
            em.close();
        }
    }

    public Categories get(String name) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try {

            Categories category = em.createNamedQuery("User.findByCategoryName", Categories.class).setParameter("categoryName", name).getSingleResult();
            return category;
        } finally {
            em.close();
        }
    }
}

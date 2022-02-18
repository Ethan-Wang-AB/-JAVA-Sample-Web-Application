/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.CategoriesDB;
import java.util.List;
import models.Category;

/**
 *
 * @author 845593
 */
public class CategoryService {
    private CategoriesDB categoryDB = new CategoriesDB();
    
    public void update(Category category) throws Exception{
    categoryDB.update(category);
        
    }
    
    public List<Category> getAll() throws Exception{
    return categoryDB.getAll();
    }
    
    public Category get(Integer categoryId) throws Exception{
     return categoryDB.get(categoryId);
    }
    
    public void insert(String categoryName){
     categoryDB.insert(categoryName);
    }
}

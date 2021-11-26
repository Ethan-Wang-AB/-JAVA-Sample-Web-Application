/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.CompanyDB;
import dataaccess.UserDB;
import java.util.List;
import models.Company;
import models.User;

/**
 *
 * @author 845593
 */
public class CompanyService {
    private CompanyDB companyDB=new CompanyDB();
    private UserDB userDB=new UserDB();
    
    public List<User> getUserList(Company company){
    
    return companyDB.getUserList(company);
    
    }
    

    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.CompanyDB;
import dataaccess.RoleDB;
import dataaccess.UserDB;
import java.util.List;
import models.Company;
import models.Role;
import models.User;

/**
 *
 * @author 845593
 */
public class AccountService {
     private UserDB userDB = new UserDB();
     private RoleDB roleDB=new RoleDB();
     final String REGULAR_USER="regular user";
     private CompanyDB companyDB=new CompanyDB();
     
       public User login(String email, String password) {
        UserDB userDB = new UserDB();
        System.out.println(email+" "+password);
        try {
            User user = userDB.get(email);
            System.out.println(user.getEmail());
            
            if (password.equals(user.getPassword()) && user.getActive()) {
                return user;
            }
        } catch (Exception e) {
        }
        
        return null;
    }

    public List<User> getAll() throws Exception {
        return userDB.getAll();
    }

    public User get(String email) throws Exception {
        return userDB.get(email);
    }

    public void update( String email, String firstname, String lastname, Role role, String password, boolean active) throws Exception {
        User user=get(email);
        userDB.update( email, firstname, lastname, user.getActive(), role, password,active);
    }

    public void insert(String email,String firstname, String lastname, String password) throws Exception {
        Role role=roleDB.get(REGULAR_USER);
        userDB.insert( email, password, firstname, lastname,role);
    }
    
     public void insert(String email,String firstname, String lastname, String password, Role role) throws Exception {
       
        userDB.insert( email, password, firstname, lastname,role);
    }


    public void delete(String email) throws Exception {
      
        userDB.delete(email);
    }


          public boolean checkExist(String email) throws Exception {
        List<models.User> users = userDB.getAll();
      
        for (int i = 0; i < users.size(); i++) {
            if(email.equals(users.get(i).getEmail())){
                return true;            
            }
        }
        return false;
    }
          
          public boolean changePassword(String uuid, String password) {
       UserDB userDB = new UserDB();
        try {
            User user = userDB.getByUUID(uuid);
            //System.out.println(user.getEmail());
            user.setPassword(password);
            user.setResetPasswordUuid(null);
            System.out.println("set new password "+ user.getPassword()+user.getResetPasswordUuid());
            userDB.update(user);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
          public Role getRole(String roleName) throws Exception{
           return roleDB.get(roleName);
          }
          
          public List<Role> getAllRole() throws Exception{
           return roleDB.getAll();
          }
          
          public List<Company> getAllCompany() throws Exception{
          
                 return companyDB.getAll();
          
          }
          
          public void deactive(User user) throws Exception{
          user.setActive(false);
              userDB.update(user);
              
          }
          
            public void update(User user) throws Exception{
          
              userDB.update(user);
              
          }
}

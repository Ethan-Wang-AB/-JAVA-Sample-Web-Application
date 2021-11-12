/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.UserDB;
import java.util.List;
import models.Users;

/**
 *
 * @author 845593
 */
public class AccountService {
     private UserDB userDB = new UserDB();
     
       public Users login(String username, String password) {
        UserDB userDB = new UserDB();
        try {
            Users user = userDB.get(username);
            if (password.equals(user.getPassword()) && user.getActive()) {
                return user;
            }
        } catch (Exception e) {
        }
        
        return null;
    }

    public List<Users> getAll() throws Exception {
        return userDB.getAll();
    }

    public Users get(String username) throws Exception {
        return userDB.get(username);
    }

    public void update(String previousName, String email, String firstname, String lastname, String password) throws Exception {
        Users user=get(previousName);
        userDB.update(previousName, email, firstname, lastname, user.getActive(), user.getIsAdmin(), password);
    }

    public void insert(String username,String email,String firstname, String lastname, String password) throws Exception {
        
        userDB.insert(username, email, password, firstname, lastname);
    }


    public void delete(String username) throws Exception {
      
        userDB.delete(username);
    }


          public boolean checkExist(String email) throws Exception {
        List<Users> users = userDB.getAll();
      
        for (int i = 0; i < users.size(); i++) {
            if(email.equals(users.get(i).getEmail())){
                return true;            
            }
        }
        return false;
    }
}

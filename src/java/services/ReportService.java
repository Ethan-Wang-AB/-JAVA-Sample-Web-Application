/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.UserDB;
import java.util.ArrayList;
import java.util.List;
import models.User;

/**
 *
 * @author 845593
 */
public class ReportService {
    private UserDB userDB=new UserDB();
      private InventoryService inventoryService =new InventoryService();
    
    public List<String[]> getUserReportAll() throws Exception{
    List<String[]> report=new ArrayList<String[]>();
    String [] row=new String[]{"Email","Name","Number of Items","Total value"};
    List<User> users=userDB.getAll();
    report.add(row);
    for(int i=0;i<users.size();i++){
    row=new String[4];
    row[0]=users.get(i).getEmail();
    row[1]=users.get(i).getFirstName()+" "+users.get(i).getLastName();
    row[2]=users.get(i).getItemList().size()+"";
    row[3]=inventoryService.getTotal(users.get(i).getItemList())+"";
    report.add(row);
    }
     for(int i=0;i<report.size();i++){
    System.out.println(report.get(i));}
 
    return report;
    }
    
    
       public List<String[]> getUserReport() throws Exception{
    List<String[]> report=new ArrayList<String[]>();
    String [] row=new String[]{"Email","Name","Number of Items","Total value"};
    report.add(row);
    List<User> users=userDB.getAll();
    for(int i=0;i<users.size();i++){
    row=new String[4];
    if(users.get(i).getActive() && users.get(i).getRole().getRoleId()==2){
    row[0]=users.get(i).getEmail();
    row[1]=users.get(i).getLastName()+", "+users.get(i).getFirstName();
    row[2]=users.get(i).getItemList().size()+"";
    row[3]=inventoryService.getTotal(users.get(i).getItemList())+"";
    report.add(row);
    }
    }
    for(int i=0;i<report.size();i++){
    System.out.println(report.get(i));}
    return report;
    }
    
}

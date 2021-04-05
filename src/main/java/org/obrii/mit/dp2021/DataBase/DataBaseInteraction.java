/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.obrii.mit.dp2021.DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 *
 * @author NEVM PC
 */
public class DataBaseInteraction {
        private String url;
        private String username;
        private String password;
    
   
        
        
        
    
    public DataBaseInteraction(String url,String username,String password){
        this.url = url;
        this.username = username;
        this.password = password;
    }

   
    
    
    
    
    
    public void addData(String data, String table){
        //data=('iPhone X', 76000)
        //table=Products(ProductName, Price)
        try{
             //String url = "jdbc:mysql://localhost/store?serverTimezone=Europe/Moscow&useSSL=false";
             //String username = "root";
             //String password = "password";
             Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
             try (Connection conn = DriverManager.getConnection(url, username, password)){
                Statement statement = conn.createStatement();
                int rows = statement.executeUpdate("INSERT INTO "+ table +" VALUES "+ data +"" );
                System.out.printf("Added %d rows", rows);
                }
            }
         catch(Exception ex){
             System.out.println("Connection failed...");
             System.out.println(ex);
         }
    }
    
     public void updataData(String nameData, String nameRow,int id){
        //nameData=Price - 5000
        //nameRow=Price
         try{
             //String url = "jdbc:mysql://localhost/store?serverTimezone=Europe/Moscow&useSSL=false";
             //String username = "root";
             //String password = "password";
             Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
             try (Connection conn = DriverManager.getConnection(url, username, password)){                  
                Statement statement = conn.createStatement();                
               
                int rows = statement.executeUpdate("UPDATE users SET " + nameRow + " = '"+ nameData + "' WHERE Id = "+id);
                System.out.printf("Updated %d rows", rows);
             }
         }
         catch(Exception ex){
             System.out.println("Connection failed...");              
             System.out.println(ex);
         }        
    }
     
      public void deleteData(int id){
    try{
            //String url = "jdbc:mysql://localhost/store?serverTimezone=Europe/Moscow&useSSL=false";
            // String username = "root";
            //String password = "password";
             Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
              
             try (Connection conn = DriverManager.getConnection(url, username, password)){
                  
                Statement statement = conn.createStatement();
                 
                int rows = statement.executeUpdate("DELETE FROM users WHERE Id = "+id);
                System.out.printf("%d row(s) deleted", rows);
             }
         }
         catch(Exception ex){
             System.out.println("Connection failed...");
              
             System.out.println(ex);
         }    
    }

    

  
    
    
    
    
    
    
    
    
    
    
    
}


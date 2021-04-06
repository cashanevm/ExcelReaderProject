/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.obrii.mit.dp2021.DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author NEVM PC
 */
public class DataBaseConnection {
    
    
    
    public static Connection connect() throws SQLException{
        Connection c = null;
        try{
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://obrii.org:5432/db2021mit21s13","s13","6746");
        }
        catch(Exception e){
            e.printStackTrace();       
        }
        Statement statement = c.createStatement();     
        //int rowsIdd = statement.executeUpdate("DROP TABLE files_info");
        //int rowsIаdd = statement.executeUpdate("DROP TABLE files_id");
        int rowsId = statement.executeUpdate("CREATE TABLE IF NOT EXISTS files_id(id SERIAL PRIMARY KEY, naming VARCHAR(60) NOT NULL)");
        //int rowsInfo = statement.executeUpdate("CREATE TABLE IF NOT EXISTS files_info(id VARCHAR(6), naming VARCHAR(60),tables_id INTEGER , line INTEGER , possition VARCHAR(6) , height VARCHAR(6) , val VARCHAR(300) , sheet VARCHAR(30) )");
         int rowsInfo = statement.executeUpdate("CREATE TABLE IF NOT EXISTS files_info(id VARCHAR(6), naming VARCHAR(60) NOT NULL,tables_id INTEGER NOT NULL, line INTEGER NOT NULL, possition VARCHAR(6) NOT NULL, height VARCHAR(6) NOT NULL, val VARCHAR(300) NOT NULL, sheet VARCHAR(30) NOT NULL)");
        
        return c;
    }
    
    
    
    
    
    
    
    
    
    
}

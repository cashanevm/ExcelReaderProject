package org.obrii.mit.dp2021.DataBase;

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Integer.parseInt;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.obrii.mit.dp2021.Table.CellT;
import org.obrii.mit.dp2021.Table.RowT;
import org.obrii.mit.dp2021.Table.SheetT;
import org.obrii.mit.dp2021.Table.Table;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author NEVM PC
 */
public class DataStore {
//    List<Data> data = new ArrayList<>();
//    
   Statement statementMain;
    public DataStore() throws SQLException{
            Connection connection = DataBaseConnection.connect();
            Statement statement = connection.createStatement();
            statementMain = statement;

        //ResultSet resultSet = statement.executeQuery("select * from users");            
           //while( resultSet.next()){
           //data.add(new Data(parseInt(resultSet.getString(1))  ,resultSet.getString(2),parseInt(resultSet.getString(3))));
            //}
   }
    
    
     public ArrayList<String> GetTablesNames() throws SQLException{
            ArrayList<String> TablesName = new ArrayList<String>();
            
            Statement statement = statementMain;
            ResultSet resultSet = statement.executeQuery("select * from files_id");            
            while( resultSet.next()){
                TablesName.add(resultSet.getString(2));
            }
            return   TablesName ;
   }
    
     public String GetTablesId(String name) throws SQLException{
            Statement statement = statementMain;
            ResultSet resultSet = statement.executeQuery("select * from files_id");            
            while( resultSet.next()){
              if(resultSet.getString(2).equals(name)){
              
              return  resultSet.getString(1);
              }  
            }
            return  null ;
   }
     
     
     
      public Table GetTableFromBase(int Id) throws SQLException{
            int max = 0;
            Table newTable = new Table();
            ArrayList<String> stringSheetsName = new ArrayList<String>();
            ArrayList<Integer> rowsID = new ArrayList<Integer>();
            ArrayList<Integer> cellsID = new ArrayList<Integer>();
            Statement statement = statementMain;
            ResultSet resultSet = statement.executeQuery("select * from files_info");            
            while( resultSet.next()){
                if(parseInt(resultSet.getString(3))== Id){
                    newTable.getSheetList();
                    for(int i=0;i<newTable.getSheetList().size();i++){
                        stringSheetsName.add(newTable.getSheetList().get(i).getName());
                    }
                    if(stringSheetsName.contains(resultSet.getString(8))){
                    }
                    else{
                        SheetT newSheet = new SheetT(resultSet.getString(8));
                        newTable.setSheet(newSheet);
                    }
                    stringSheetsName.clear();
                    for(int i=0;i<newTable.getSheet(resultSet.getString(8)).getRowList().size();i++){
                        rowsID.add(newTable.getSheet(resultSet.getString(8)).getRowList().get(i).getId());
                    }
                    if(rowsID.contains(Integer.parseInt(resultSet.getString(4)))){
                    }
                    else{
                        RowT newRow = new RowT(Integer.parseInt(resultSet.getString(4)));
                        newTable.getSheet(resultSet.getString(8)).setRow(newRow);
                    }
                    rowsID.clear();
                    for(int i=0;i<newTable.getSheet(resultSet.getString(8)).getRow(Integer.parseInt(resultSet.getString(4))).getCellList().size();i++){
                        cellsID.add(newTable.getSheet(resultSet.getString(8)).getRow(Integer.parseInt(resultSet.getString(4))).getCellList().get(i).getId());
                    }
                    if(cellsID.contains(Integer.parseInt(resultSet.getString(1)))){
                    }
                    else{
                        CellT newCell = new CellT (Integer.parseInt(resultSet.getString(1)));
                        newTable.getSheet(resultSet.getString(8)).getRow(Integer.parseInt(resultSet.getString(4))).setCell(newCell);         
                    }
                    cellsID.clear();
                    newTable.getSheet(resultSet.getString(8)).getRow(Integer.parseInt(resultSet.getString(4))).getCell(Integer.parseInt(resultSet.getString(1))).setHieght(resultSet.getString(6));
                    newTable.getSheet(resultSet.getString(8)).getRow(Integer.parseInt(resultSet.getString(4))).getCell(Integer.parseInt(resultSet.getString(1))).setPossion(resultSet.getString(5));
                    newTable.getSheet(resultSet.getString(8)).getRow(Integer.parseInt(resultSet.getString(4))).getCell(Integer.parseInt(resultSet.getString(1))).setValue(resultSet.getString(7));
                    if(max < Integer.parseInt(resultSet.getString(1))){
                    max = Integer.parseInt(resultSet.getString(1));
                    newTable.setMax(max);
                    }

                    // newTable.getSheet(resultSet.getString(8)).getRow(Integer.parseInt(resultSet.getString(4))).getCell(Integer.parseInt(resultSet.getString(1))).;
                }

                //Tables.add(new Data(parseInt(resultSet.getString(1))  ,resultSet.getString(2),parseInt(resultSet.getString(3))));
            }
            return   newTable ;
   }
     
     
     
     
    
    public void addTable(Table table) throws SQLException{
        int TableId = 0; 
        //data=('iPhone X', 76000)
        //table=files_id(naming)
        int CreateTableSRow = statementMain.executeUpdate("INSERT INTO files_id(naming) VALUES ('"+ table.getName() +"')" );
        ResultSet resultSet = statementMain.executeQuery("select * from files_id");      
        while( resultSet.next()){
            if(table.getName().equals(resultSet.getString(2))){}
            TableId = parseInt(resultSet.getString(1));
        }
        //data=('iPhone X', 76000)
        //table="files_info(id , naming ,tables_id , line , possition , height , val , sheet)"     
        for(int i=0; i<table.getSheetList().size();i++){
            for(int k=0; k<table.getSheetList().get(i).getRowList().size();k++){
                for(int h=0; h<table.getSheetList().get(i).getRowList().get(k).getCellList().size();h++){
                    int CellTableSRow = statementMain.executeUpdate("INSERT INTO files_info(id , naming ,tables_id , line , possition , height , val , sheet) VALUES ('"+ table.getSheetList().get(i).getRowList().get(k).getCellList().get(h).getId()  +"' , '"+table.getName()+"' ,"+String.valueOf(TableId)+" , "+table.getSheetList().get(i).getRowList().get(k).getId()+" , '"+table.getSheetList().get(i).getRowList().get(k).getCellList().get(h).getPossion()+"' , '"+table.getSheetList().get(i).getRowList().get(k).getCellList().get(h).getHieght()+"' , '"+table.getSheetList().get(i).getRowList().get(k).getCellList().get(h).getValue()+"' , '"+table.getSheetList().get(i).getName()+"')" );
                }
            }
        }
    }
    
    
//    
//    public List<Data> getData(){
//    return data;
//    }
//    
//    public List<Data> sortingData(List<Data> dataBase, String s){       
//        List<Data> newData = new ArrayList<>();
//        for (Data d : dataBase) {            
//            if(d.getName().contains(s)){
//                newData.add(d);}                           
//        }
//        return newData;
//    }                      
}

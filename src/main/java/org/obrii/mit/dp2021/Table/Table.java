/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.obrii.mit.dp2021.Table;

import java.io.Serializable;
import java.util.ArrayList;



/**
 *
 * @author NEVM PC
 */
public class  Table implements Serializable {
    private ArrayList<SheetT> Sheets = new ArrayList<SheetT>(); 
    private int id;
    private String name;
    private int Max;
    
    public Table() {
    }

    public Table(int id, String name) {
        this.id = id;
        this.name = name;
        
    }
    public ArrayList<SheetT> getSheetList(){
    
    
    return Sheets;
    }
    
    
    
    public SheetT getSheet(int id) {
        for(int i = 0; i<Sheets.size();i++){
            if(id == Sheets.get(i).getId()){
            
                return Sheets.get(i);
            
            
            }
            
        
        }
        return null;
    }

    public void setSheet(SheetT sheet) {
        this.Sheets.add(sheet);
    }
    
    
public int getMax() {
        return Max;
    }

    public void setMax(int max) {
        this.Max = max;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Data{id=").append(id);
        sb.append(", name=").append(name);
        sb.append('}');
        return sb.toString();
    }
    
    
    
}

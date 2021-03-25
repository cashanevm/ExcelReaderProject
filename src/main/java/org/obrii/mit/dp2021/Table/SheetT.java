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
public class  SheetT implements Serializable {
    private ArrayList<RowT> Rows = new ArrayList<RowT>(); 
    private int id;
    private String name;

    public SheetT() {
    }

    public SheetT(int id,String name) {
        this.id = id;
        
        
    }

    public ArrayList<RowT> getRowList(){
    
    
    return Rows;
    }
    
    
    public RowT getRow(int id) {
        for(int i = 0; i<Rows.size();i++){
            if(id == Rows.get(i).getId()){
            
                return Rows.get(i);
            
            
            }
            
        
        }
        return null;
    }

    public void setRow(RowT row) {
        this.Rows.add(row);
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

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
public class  CellT {
    private int id;
    private String Value; 
    private String Possion;
    private String Hieght;

    public CellT() {
    }

    public CellT(int id) {
        this.id = id;
       
        
    }

    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        this.Value = value;
    }
    
    public String getHieght() {
        return Hieght;
    }

    public void setHieght(String hieght) {
        this.Hieght = hieght;
    }
    
    public String getPossion() {
        return Possion;
    }

    public void setPossion(String possion) {
        this.Possion = possion;
    }
    


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

   
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Data{id=").append(id);
        sb.append('}');
        return sb.toString();
    }
    
    
    
}

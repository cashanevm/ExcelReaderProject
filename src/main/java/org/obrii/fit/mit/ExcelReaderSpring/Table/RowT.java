/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.obrii.fit.mit.ExcelReaderSpring.Table;

import java.io.Serializable;
import java.util.ArrayList;



/**
 *
 * @author NEVM PC
 */
public class  RowT implements Serializable {
    private ArrayList<CellT> Cells = new ArrayList<CellT>(); 
    private int id;
    private boolean written = false;
     
    public RowT() {
    }

    public RowT(int id) {
        this.id = id;
       
        
    }

     
public ArrayList<String> getRowString(int id) {
    ArrayList<String> list = new ArrayList<String>();
    
    
    return list;
    }

    public void setWritten() {
        this.written = true;
    }

    public boolean isWritten() {
        return written;
    }
    
 public ArrayList<CellT> getCellList(){
    
    
    return Cells;
    }

 
    public CellT getCell(int id) {
     for(int i = 0; i<Cells.size();i++){
            if(id == Cells.get(i).getId()){
            
                return Cells.get(i);
            
            
            }
            
        
        }
        return null;
    }

    public void setCell(CellT cell) {
        this.Cells.add(cell);
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.obrii.fit.mit.NevmProject.Domain;

import java.io.Serializable;

/**
 *
 * @author NEVM PC
 */
public class Test implements Serializable{
    
    private String name;
private int id;
    
    public Test() {
    }

    public Test(String name, int id) {
        this.name = name;
        this.id = id;
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
        return "Test{" + "name=" + name + ", id=" + id + '}';
    }
    
    
}

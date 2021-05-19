/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.obrii.fit.mit.NevmProject.Domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.springframework.stereotype.Component;


/**
 *
 * @author NEVM PC
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@Component
public class Student implements Serializable{
            
    private long id;
    
    private String name;
    
    private int age;
    
      private ArrayList<String> list = new ArrayList<>();
     private ArrayList<Test> testlist = new ArrayList<>();

    public ArrayList<Test> getTestlist() {
        return testlist;
    }

    public void setTestlist(ArrayList<Test> testlist) {
        this.testlist = testlist;
    }

      
        
        
    
    
    @JsonProperty("_links")
    private Links links;
    
    @JsonIgnore
    private String href;
    
   

    public Student() {
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
            for(int i = 0 ; i<5; i++){
            Test ts = new Test("name",45);
            
            testlist.add(ts);
        
        }
    }
    

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public ArrayList<String> getList() {
        return list;
    }

    public void setList(ArrayList<String> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Student{id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", age=").append(age);
        sb.append(", links=").append(links);
        sb.append(", href=").append(href);
        sb.append('}');
        return sb.toString();
    }

    

    public Links getLinks() {
        return links;
    }

    public String getHref() {
        return links.getSelf().getHref();
    }

  

   
    
    
    
    
}
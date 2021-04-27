/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.obrii.fit.mit.NevmProject.Domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author NEVM PC
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Embedded {
    @JsonProperty("_embedded")
    private StudentList listOfStudents;    
    
      

    public Embedded() {
    }

    public StudentList getListOfStudents() {
        return listOfStudents;
    }

    public void setListOfStudents(StudentList listOfStudents) {
        this.listOfStudents = listOfStudents;
    }
    
    
    
}

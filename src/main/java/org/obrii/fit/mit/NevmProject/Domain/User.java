/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.obrii.fit.mit.NevmProject.Domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

/**
 *
 * @author NEVM PC
 */
@Entity
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @NotBlank(message = "Name is mandatory")
    private String name;
    
    @NotBlank(message = "Email is mandatory")
    private String email;

   @NotBlank(message = "Surname is mandatory")
    private String secname;
   @NotBlank(message = "Password is mandatory")
    private String pass;
   @NotBlank(message = "Sex  is mandatory")
    private String radio;
   @NotBlank(message = "Language is mandatory")
    private String checkbox;
   @NotBlank(message = "Your color is mandatory")
//    private int tell;
//   @NotBlank(message = "Email is mandatory")
    private String color;
   @NotBlank(message = "Date of Birth is mandatory")
    private String date;
   @NotBlank(message = "Preferred time is mandatory")
    private String time;
   @NotBlank(message = "Number of attempts is mandatory")
    private String number;
   @NotBlank(message = "Aspiration is mandatory")
    private String range;

    public void setId(long id) {
        this.id = id;
    }

//    public void setTell(int tell) {
//        this.tell = tell;
//    }
//
//    public String getTell() {
//        return String.valueOf(tell);
//    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSecname(String secname) {
        this.secname = secname;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setRadio(String radio) {
        this.radio = radio;
    }

    public void setCheckbox(String checkbox) {
        this.checkbox = checkbox;
    }

   

    public void setColor(String color) {
        this.color = color;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getSecname() {
        return secname;
    }

    public String getPass() {
        return pass;
    }

    public String getRadio() {
        return radio;
    }

    public String getCheckbox() {
        return checkbox;
    }

  

    public String getColor() {
        return color;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getNumber() {
        return number;
    }

    public String getRange() {
        return range;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name=" + name + ", email=" + email + ", secname=" + secname + ", pass=" + pass + ", radio=" + radio + ", checkbox=" + checkbox  + ", color=" + color + ", date=" + date + ", time=" + time + ", number=" + number + ", range=" + range + '}';
    }
    

    public User() {
    }
}

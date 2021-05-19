/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.obrii.fit.mit.NevmProject.Request;

import java.util.ArrayList;
import javax.persistence.Lob;

/**
 *
 * @author NEVM PC
 */
public class Discipline {
    public int Index;
    public String Name;
    public String Type;
    public String Code;
    public int TotalHours;
    public int TotalCR;
    public int TotalLessons;
    public int StudentsCount;
    public int IndependentWorks;
    public String ControlForms;
    public String Comment;
    @Lob
    private ArrayList<Timetable> Timetable = new ArrayList<>();

     public void addTimetable(Timetable timetable) {
        Timetable.add(timetable);
    }
    
    public Discipline() {
    }

    @Override
    public String toString() {
        return "Discipline{" + "Index=" + Index + ", Name=" + Name + ", Type=" + Type + ", Code=" + Code + ", TotalHours=" + TotalHours + ", TotalCR=" + TotalCR + ", TotalLessons=" + TotalLessons + ", StudentsCount=" + StudentsCount + ", IndependentWorks=" + IndependentWorks + ", ControlForms=" + ControlForms + ", Comment=" + Comment + ", Timetable=" + Timetable + '}';
    }

    public int getIndex() {
        return Index;
    }

    public void setIndex(int Index) {
        this.Index = Index;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String Code) {
        this.Code = Code;
    }

    public int getTotalHours() {
        return TotalHours;
    }

    public void setTotalHours(int TotalHours) {
        this.TotalHours = TotalHours;
    }

    public int getTotalCR() {
        return TotalCR;
    }

    public void setTotalCR(int TotalCR) {
        this.TotalCR = TotalCR;
    }

    public int getTotalLessons() {
        return TotalLessons;
    }

    public void setTotalLessons(int TotalLessons) {
        this.TotalLessons = TotalLessons;
    }

    public int getStudentsCount() {
        return StudentsCount;
    }

    public void setStudentsCount(int StudentsCount) {
        this.StudentsCount = StudentsCount;
    }

    public int getIndependentWorks() {
        return IndependentWorks;
    }

    public void setIndependentWorks(int IndependentWorks) {
        this.IndependentWorks = IndependentWorks;
    }

    public String getControlForms() {
        return ControlForms;
    }

    public void setControlForms(String ControlForms) {
        this.ControlForms = ControlForms;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String Comment) {
        this.Comment = Comment;
    }

    public ArrayList<Timetable> getTimetable() {
        return Timetable;
    }

    public void setTimetable(ArrayList<Timetable> Timetable) {
        this.Timetable = Timetable;
    }
    
}

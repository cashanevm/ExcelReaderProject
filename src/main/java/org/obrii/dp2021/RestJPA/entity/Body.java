/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.obrii.dp2021.RestJPA.entity;

import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

/**
 *
 * @author NEVM PC
 */
@Entity
public class Body implements Serializable{
     @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private String name;
    public String University; 
    public String EducationalDegree; 
    public int Course;
    public String Semester;
    public String Faculty; 
    public int KnowledgeFieldId;
    public String KnowledgeField; 
    public int DirectionId;
    public String Direction; 
    public String Program; 
    public String StudyForm; 
    public int YearFrom;
    public int YearTo;
    public String Director;
    @Lob
    private ArrayList<Discipline> Disciplines = new ArrayList<>();

    public Body() {
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

    public String getUniversity() {
        return University;
    }

    public void setUniversity(String University) {
        this.University = University;
    }

    public String getEducationalDegree() {
        return EducationalDegree;
    }

    public void setEducationalDegree(String EducationalDegree) {
        this.EducationalDegree = EducationalDegree;
    }

    public int getCourse() {
        return Course;
    }

    public void setCourse(int Course) {
        this.Course = Course;
    }

    public String getSemester() {
        return Semester;
    }

    public void setSemester(String Semester) {
        this.Semester = Semester;
    }



    public String getFaculty() {
        return Faculty;
    }

    public void setFaculty(String Faculty) {
        this.Faculty = Faculty;
    }

    public int getKnowledgeFieldId() {
        return KnowledgeFieldId;
    }

    public void setKnowledgeFieldId(int KnowledgeFieldId) {
        this.KnowledgeFieldId = KnowledgeFieldId;
    }

    public String getKnowledgeField() {
        return KnowledgeField;
    }

    public void setKnowledgeField(String KnowledgeField) {
        this.KnowledgeField = KnowledgeField;
    }

    public int getDirectionId() {
        return DirectionId;
    }

    public void setDirectionId(int DirectionId) {
        this.DirectionId = DirectionId;
    }

    public String getDirection() {
        return Direction;
    }

    public void setDirection(String Direction) {
        this.Direction = Direction;
    }

    public String getProgram() {
        return Program;
    }

    public void setProgram(String Program) {
        this.Program = Program;
    }

    public String getStudyForm() {
        return StudyForm;
    }

    public void setStudyForm(String StudyForm) {
        this.StudyForm = StudyForm;
    }

    public int getYearFrom() {
        return YearFrom;
    }

    public void setYearFrom(int YearFrom) {
        this.YearFrom = YearFrom;
    }

    public int getYearTo() {
        return YearTo;
    }

    public void setYearTo(int YearTo) {
        this.YearTo = YearTo;
    }

    public String getDirector() {
        return Director;
    }

    public void setDirector(String Director) {
        this.Director = Director;
    }

    public ArrayList<Discipline> getDisciplines() {
        return Disciplines;
    }

    public void setDisciplines(ArrayList<Discipline> Disciplines) {
        this.Disciplines = Disciplines;
    }

//    @Override
//    public String toString() {
//        return "Body{" + "id=" + id + ", name=" + name + ", University=" + University + ", EducationalDegree=" + EducationalDegree + ", Course=" + Course + ", Semester=" + Semester + ", Faculty=" + Faculty + ", KnowledgeFieldId=" + KnowledgeFieldId + ", KnowledgeField=" + KnowledgeField + ", DirectionId=" + DirectionId + ", Direction=" + Direction + ", Program=" + Program + ", StudyForm=" + StudyForm + ", YearFrom=" + YearFrom + ", YearTo=" + YearTo + ", Director=" + Director + ", Disciplines=" + Disciplines + '}';
//    }
//    
}

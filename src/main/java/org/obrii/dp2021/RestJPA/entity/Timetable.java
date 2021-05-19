/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.obrii.dp2021.RestJPA.entity;

import java.io.Serializable;

/**
 *
 * @author NEVM PC
 */
public class Timetable implements Serializable{
    public String Type;
    public String GroupType;
    public int GroupStudentsCount;
    public int HoursPerGroup;

    public Timetable() {
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public String getGroupType() {
        return GroupType;
    }

    public void setGroupType(String GroupType) {
        this.GroupType = GroupType;
    }

    public int getGroupStudentsCount() {
        return GroupStudentsCount;
    }

    public void setGroupStudentsCount(int GroupStudentsCount) {
        this.GroupStudentsCount = GroupStudentsCount;
    }

    public int getHoursPerGroup() {
        return HoursPerGroup;
    }

    public void setHoursPerGroup(int HoursPerGroup) {
        this.HoursPerGroup = HoursPerGroup;
    }

    @Override
    public String toString() {
        return "Timetable{" + "Type=" + Type + ", GroupType=" + GroupType + ", GroupStudentsCount=" + GroupStudentsCount + ", HoursPerGroup=" + HoursPerGroup + '}';
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.obrii.fit.mit.NevmProject.Request;

/**
 *
 * @author NEVM PC
 */
public class Timetable {
    public String Type;
    public String GroupType;
    public int GroupStudentsCount;
    public int HoursPerGroup;

    public Timetable() {
    }

    @Override
    public String toString() {
        return "Timetable{" + "Type=" + Type + ", GroupType=" + GroupType + ", GroupStudentsCount=" + GroupStudentsCount + ", HoursPerGroup=" + HoursPerGroup + '}';
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
    
}

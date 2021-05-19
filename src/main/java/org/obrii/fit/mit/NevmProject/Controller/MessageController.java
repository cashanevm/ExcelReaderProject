/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.obrii.fit.mit.NevmProject.Controller;

import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.obrii.fit.mit.NevmProject.Domain.Embedded;
import org.obrii.fit.mit.NevmProject.Domain.Student;
import org.obrii.fit.mit.NevmProject.Request.Body;
import org.obrii.fit.mit.NevmProject.Request.Discipline;
import org.obrii.fit.mit.NevmProject.Request.Timetable;
import org.obrii.fit.mit.NevmProject.Table.CellT;
import org.obrii.fit.mit.NevmProject.Table.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author 38068
 */
@Controller
public class MessageController {

    private final String URL = "http://localhost:8080/NevmerzhytskyiProject/Lab7service/body";

    @Autowired
    RestTemplate restTemplate;

//    @GetMapping("/getMessage")
//    public String getFormData(Model model) {
//
//        Embedded messageEntity = restTemplate.getForObject(URL, Embedded.class);
//        
//        List<Student> list = messageEntity.getListOfStudents().getStudentList();        
//        
//        model.addAttribute("students", list);
//
//        return "index";
//    }
//
//    @PostMapping("/update")
//    public String updateData(@RequestParam(name = "name") String name,
//            @RequestParam(name = "age") String age,
//            @RequestParam(name = "url") String url,
//            Model model) {
//
//        restTemplate.put(url, new Student(name,Integer.parseInt(age)));
//
//        Embedded messageEntity = restTemplate.getForObject(URL, Embedded.class);
//        
//        List<Student> list = messageEntity.getListOfStudents().getStudentList();        
//        
//        model.addAttribute("students", list);
//
//        return "index";
//    }
//   @PostMapping("/del")
//    public String delData(
//            @RequestParam(name = "url") String url,
//            Model model) {
//
//        restTemplate.delete(url);
//
//        return getFormData(model);
//    }
    
    @PostMapping("/post")
    public String postData(
            
            Model model ,HttpSession session) {
        int cellN;
        int rowN;
        Table table =  (Table) session.getAttribute("tableInfo");
        Body body = new Body();
        for(int i =0 ; i< table.getSheetList().size();i++){         
                        for(int h =0 ; h< table.getSheetList().get(i).getRowList().size();h++){                        
                            
                                for(int j =0 ; j< table.getSheetList().get(i).getRowList().get(h).getCellList().size();j++){                
                                                                        
                                    if(table.getSheetList().get(i).getRowList().get(h).getCellList().get(j).getValue().contains("«ЗАТВЕРДЖУЮ»")){
                                        //System.out.println(table.getSheetList().get(i).getRowList().get(h).getCellList().get(j).getValue()); 
                                        cellN = table.getSheetList().get(i).getRowList().get(h).getCellList().get(j).getId();
                                        rowN = table.getSheetList().get(i).getRowList().get(h).getId();
                                        body.setName(table.getName());
                                        body.setUniversity(table.getSheetList().get(i).getRowList().get(rowN-1).getCellList().get(cellN).getValue());
                                        
                                        
                                        
                                       break; 
                                    } else if(table.getSheetList().get(i).getRowList().get(h).getCellList().get(j).getValue().contains("РОБОЧИЙ (ОПЕРАТИВНИЙ) НАВЧАЛЬНИЙ ПЛАН")){
                                        cellN = j;
                                        rowN = h;
                                        //System.out.println("WARNING---------------"+table.getSheetList().get(i).getRowList().get(rowN).getCellList().get(cellN).getValue()+"---------------------");  
                                        String[] subStr;
                                        rowN++;
                                        subStr =  table.getSheetList().get(i).getRowList().get(rowN).getCellList().get(cellN).getValue().split(",");
                                        body.setEducationalDegree(subStr[0]);
                                        String[] subStr1;
                                        subStr1 = subStr[1].split(" ");
                                        body.setCourse(Integer.parseInt(subStr1[1]));
                                        String[] subStr2;
                                        subStr2 = subStr[2].split(" "); 
                                        body.setSemester(subStr2[1]);
                                        body.setFaculty(subStr2[3]+" "+subStr2[4]+" "+subStr2[5]);
                                        rowN++;
                                        cellN++;
                                        subStr =  getCellValue(1,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),"з галузі знань").split(" ");
                                       // System.out.println("/1"+subStr[0]);
                                       // System.out.println("/2"+subStr[1]);
                                        body.setKnowledgeFieldId(Integer.parseInt(subStr[1]));
                                        body.setKnowledgeField(subStr[2]+" "+subStr[3]+" "+subStr[4]);
                                        rowN++;
                                        subStr =  getCellValue(1,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),"за напрямом").split(" ");
                                                                            
                                        body.setDirectionId(Integer.parseInt(subStr[1]));
                                        body.setDirection(subStr[2]+" "+subStr[3]+" "+subStr[4]);
                                        rowN++;
                                           
                                        subStr =  getCellValue(1,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),"за програмою").split(" ");
                                        body.setProgram(subStr[1]+" "+subStr[2]+" "+subStr[3]);
                                        rowN++;
                                        cellN--;
                                        subStr =  table.getSheetList().get(i).getRowList().get(rowN).getCellList().get(cellN).getValue().split(" ");
                                        body.setStudyForm(subStr[0]);
                                        rowN++;
                                        subStr =  table.getSheetList().get(i).getRowList().get(rowN).getCellList().get(cellN).getValue().split(" ");
                                        subStr1 = subStr[1].split("-"); 
                                        body.setYearFrom(Integer.parseInt(subStr1[0]));
                                        body.setYearTo(Integer.parseInt(subStr1[1]));
                                    break;
                                    }else if(table.getSheetList().get(i).getRowList().get(h).getCellList().get(j).getValue().contains("Обов'язкові навчальні дисципліни")){
                                        cellN = j;
                                        rowN = h;
                                        rowN++;
                                        while(!isObject("Дисципліни вибору ВНЗ",table.getSheetList().get(i).getRowList().get(rowN).getCellList())){
                                        Discipline disp = new Discipline();
                                        if(table.getSheetList().get(i).getRowList().get(rowN).getCellList().get(cellN).getValue().contains(".0")){
                                        //String[] subStr;
                                        //System.out.println(getCellValue(0,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0"));
                                        //subStr = getCellValue(0,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0").split(".");
                                        String fileName1 =  getCellValue(0,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0");
                                        int dotIndex1 = fileName1.lastIndexOf(".");
                                        String name1 = fileName1.substring(0, dotIndex1);
                                        
                                        
                                        
                                        
                                        disp.setIndex(Integer.parseInt(name1));
                                        disp.setName(getCellValue(1,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0"));
                                        disp.setType("Обов'язкові навчальні дисципліни");
                                        disp.setCode(getCellValue(2,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0"));
                                        //String[] subStr;
                                        String fileName =  getCellValue(3,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0");
                                        int dotIndex = fileName.lastIndexOf(".");
                                        String name = fileName.substring(0, dotIndex);
                                        
                                        //subStr = getCellValue(3,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0").split("."); 
                                        disp.setTotalHours(Integer.parseInt(name));
                                        
                                        fileName =  getCellValue(4,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        disp.setTotalCR(Integer.parseInt(name));
                                        
                                         fileName =  getCellValue(5,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        disp.setTotalLessons(Integer.parseInt(name));
                                         fileName =  getCellValue(6,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        disp.setStudentsCount(Integer.parseInt(name));
                                        
                                    
                                        Timetable titb1 = new Timetable();
                                        
                                        titb1.setType("лекц.");
                                        titb1.setGroupType("потоків");
                                        fileName =  getCellValue(7,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        
                                        titb1.setGroupStudentsCount(Integer.parseInt(name));
                                        
                                        fileName =  getCellValue(8,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        
                                        titb1.setHoursPerGroup(Integer.parseInt(name));
                                        disp.addTimetable(titb1);
                                        
                                        Timetable titb2 = new Timetable();
                                        
                                        titb2.setType("лаборат.");
                                        titb2.setGroupType("підгруп");
                                        fileName =  getCellValue(9,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        titb2.setGroupStudentsCount(Integer.parseInt(name));
                                        
                                        fileName =  getCellValue(10,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        titb2.setHoursPerGroup(Integer.parseInt(name));
                                        
                                        disp.addTimetable(titb2);
                                        Timetable titb3 = new Timetable();
                                         titb3.setType("практ.");
                                        titb3.setGroupType("груп/ підгруп");
                                        fileName =  getCellValue(11,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        titb3.setGroupStudentsCount(Integer.parseInt(name));
                                        fileName =  getCellValue(12,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        titb3.setHoursPerGroup(Integer.parseInt(name));
                                        
                                        disp.addTimetable(titb3);
                                        Timetable titb4 = new Timetable();
                                        titb4.setType("семiн.");
                                        titb4.setGroupType("груп");
                                        
                                         fileName =  getCellValue(13,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        titb4.setGroupStudentsCount(Integer.parseInt(name));
                                        
                                         fileName =  getCellValue(14,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        titb4.setHoursPerGroup(Integer.parseInt(name));
                                        disp.addTimetable(titb4);
                                        Timetable titb5 = new Timetable(); 
                                        titb5.setType("індивід.");
                                        titb5.setGroupType("к-ть студ.");
                                        
                                             fileName =  getCellValue(15,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        titb5.setGroupStudentsCount(Integer.parseInt(name));
                                        
                                             fileName =  getCellValue(16,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        titb5.setHoursPerGroup(Integer.parseInt(name));
                                        disp.addTimetable(titb5);
                                        Timetable titb6 = new Timetable();
                                        titb6.setType("консульт.");
                                        titb6.setGroupType("потоків / груп");
                                        
                                        fileName =  getCellValue(17,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        titb6.setGroupStudentsCount(Integer.parseInt(name));
                                        
                                        fileName =  getCellValue(18,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        titb6.setHoursPerGroup(Integer.parseInt(name));
                                        disp.addTimetable(titb6);
                                         
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                         fileName =  getCellValue(19,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        disp.setIndependentWorks(Integer.parseInt(name));
                                        
                                        disp.setControlForms(getCellValue(20,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0"));
                                        disp.setComment(getCellValue(21,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0"));
                                        }
                                        else if(table.getSheetList().get(i).getRowList().get(rowN).getCellList().get(cellN).getValue().contains("Всього годин")){
                                        
                                            
                                            System.out.println("WARNING---------------/"+getCellValue(0,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),"Всього годин")+"/---------------------");  
                                        
                                        disp.setName("TOTAL");    
                                           
                                        disp.setType("Обов'язкові навчальні дисципліни");
                                                
                                        String fileName =  getCellValue(1,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),"Всього годин");
                                        int dotIndex = fileName.lastIndexOf(".");
                                        String name = fileName.substring(0, dotIndex);
                                        
                                        //subStr = getCellValue(3,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0").split("."); 
                                        disp.setTotalHours(Integer.parseInt(name));
                                        
                                        fileName =  getCellValue(2,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),"Всього годин");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        disp.setTotalCR(Integer.parseInt(name));
                                        
                                         fileName =  getCellValue(3,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),"Всього годин");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        disp.setTotalLessons(Integer.parseInt(name));
                                        
                                        
                                        
                                        
                                        
                                        
                                           Timetable titb1 = new Timetable();
                                        
                                        titb1.setType("лекц.");
                                        titb1.setGroupType("годин на потік");
                                        
                                      
                                        fileName =  getCellValue(6,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),"Всього годин");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        
                                        titb1.setHoursPerGroup(Integer.parseInt(name));
                                        disp.addTimetable(titb1);
                                        
                                        Timetable titb2 = new Timetable();
                                        
                                        titb2.setType("лаборат.");
                                        titb2.setGroupType("годин на підгруп");
                                      
                                        
                                        fileName =  getCellValue(8,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),"Всього годин");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        titb2.setHoursPerGroup(Integer.parseInt(name));
                                        
                                        disp.addTimetable(titb2);
                                        Timetable titb3 = new Timetable();
                                         titb3.setType("практ.");
                                        titb3.setGroupType("годин на групу/ підгрупу");
                                      
                                        fileName =  getCellValue(10,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),"Всього годин");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        titb3.setHoursPerGroup(Integer.parseInt(name));
                                        
                                        disp.addTimetable(titb3);
                                        Timetable titb4 = new Timetable();
                                        titb4.setType("семiн.");
                                        titb4.setGroupType("годин на груп");
                                        
                                        
                                        
                                         fileName =  getCellValue(12,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),"Всього годин");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        titb4.setHoursPerGroup(Integer.parseInt(name));
                                        disp.addTimetable(titb4);
                                        Timetable titb5 = new Timetable(); 
                                        titb5.setType("індивід.");
                                        titb5.setGroupType("годин на студ.");
                                        
                                            
                                        
                                             fileName =  getCellValue(14,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),"Всього годин");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        titb5.setHoursPerGroup(Integer.parseInt(name));
                                        disp.addTimetable(titb5);
                                        Timetable titb6 = new Timetable();
                                        titb6.setType("консульт.");
                                        titb6.setGroupType("годин на потік/ групу");
                                        
                                                                                
                                        fileName =  getCellValue(16,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),"Всього годин");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        titb6.setHoursPerGroup(Integer.parseInt(name));
                                        disp.addTimetable(titb6);
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        fileName =  getCellValue(17,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),"Всього годин");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        
                                        
                                        
                                        
                                        
                                        disp.setIndependentWorks(Integer.parseInt(name));
                                        
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                        }
                                        
                                        else{
                                        
                                        }
                                        body.addDiscipline(disp);
                                            
                                            
                                        //System.out.println(table.getSheetList().get(i).getRowList().get(rowN).getCellList().get(cellN).getValue());
                                        rowN++;
                                        }
                                    break;
                                    }
                                    else if(table.getSheetList().get(i).getRowList().get(h).getCellList().get(j).getValue().contains("Дисципліни вибору ВНЗ")){
                                        cellN = j;
                                        rowN = h;
                                        rowN++;
                                        while(!isObject("Дисципліни вільного вибору студента",table.getSheetList().get(i).getRowList().get(rowN).getCellList())){
                                        Discipline disp = new Discipline();
                                        if(table.getSheetList().get(i).getRowList().get(rowN).getCellList().get(cellN).getValue().contains(".0")){
                                        //String[] subStr;
                                        //System.out.println(getCellValue(0,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0"));
                                        //subStr = getCellValue(0,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0").split(".");
                                       String fileName1 =  getCellValue(0,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0");
                                        int dotIndex1 = fileName1.lastIndexOf(".");
                                        String name1 = fileName1.substring(0, dotIndex1);
                                        
                                        
                                        
                                        
                                        disp.setIndex(Integer.parseInt(name1));
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        disp.setName(getCellValue(1,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0"));
                                        disp.setType("Дисципліни вибору ВНЗ");
                                        disp.setCode(getCellValue(2,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0"));
                                        //String[] subStr;
                                        String fileName =  getCellValue(3,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0");
                                        int dotIndex = fileName.lastIndexOf(".");
                                        String name = fileName.substring(0, dotIndex);
                                        
                                        //subStr = getCellValue(3,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0").split("."); 
                                        disp.setTotalHours(Integer.parseInt(name));
                                        
                                        fileName =  getCellValue(4,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        disp.setTotalCR(Integer.parseInt(name));
                                        
                                         fileName =  getCellValue(5,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        disp.setTotalLessons(Integer.parseInt(name));
                                         fileName =  getCellValue(6,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        disp.setStudentsCount(Integer.parseInt(name));
                                        
                                    
                                        Timetable titb1 = new Timetable();
                                        
                                        titb1.setType("лекц.");
                                        titb1.setGroupType("потоків");
                                        fileName =  getCellValue(7,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        
                                        titb1.setGroupStudentsCount(Integer.parseInt(name));
                                        
                                        fileName =  getCellValue(8,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        
                                        titb1.setHoursPerGroup(Integer.parseInt(name));
                                        disp.addTimetable(titb1);
                                        
                                        Timetable titb2 = new Timetable();
                                        
                                        titb2.setType("лаборат.");
                                        titb2.setGroupType("підгруп");
                                        fileName =  getCellValue(9,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        titb2.setGroupStudentsCount(Integer.parseInt(name));
                                        
                                        fileName =  getCellValue(10,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        titb2.setHoursPerGroup(Integer.parseInt(name));
                                        
                                        disp.addTimetable(titb2);
                                        Timetable titb3 = new Timetable();
                                         titb3.setType("практ.");
                                        titb3.setGroupType("груп/ підгруп");
                                        fileName =  getCellValue(11,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        titb3.setGroupStudentsCount(Integer.parseInt(name));
                                        fileName =  getCellValue(12,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        titb3.setHoursPerGroup(Integer.parseInt(name));
                                        
                                        disp.addTimetable(titb3);
                                        Timetable titb4 = new Timetable();
                                        titb4.setType("семiн.");
                                        titb4.setGroupType("груп");
                                        
                                         fileName =  getCellValue(13,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        titb4.setGroupStudentsCount(Integer.parseInt(name));
                                        
                                         fileName =  getCellValue(14,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        titb4.setHoursPerGroup(Integer.parseInt(name));
                                        disp.addTimetable(titb4);
                                        Timetable titb5 = new Timetable(); 
                                        titb5.setType("індивід.");
                                        titb5.setGroupType("к-ть студ.");
                                        
                                             fileName =  getCellValue(15,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        titb5.setGroupStudentsCount(Integer.parseInt(name));
                                        
                                             fileName =  getCellValue(16,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        titb5.setHoursPerGroup(Integer.parseInt(name));
                                        disp.addTimetable(titb5);
                                        Timetable titb6 = new Timetable();
                                        titb6.setType("консульт.");
                                        titb6.setGroupType("потоків / груп");
                                        
                                        fileName =  getCellValue(17,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        titb6.setGroupStudentsCount(Integer.parseInt(name));
                                        
                                        fileName =  getCellValue(18,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        titb6.setHoursPerGroup(Integer.parseInt(name));
                                        disp.addTimetable(titb6);
                                         
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                         fileName =  getCellValue(19,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        disp.setIndependentWorks(Integer.parseInt(name));
                                        
                                        disp.setControlForms(getCellValue(20,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0"));
                                        disp.setComment(getCellValue(21,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0"));
                                        }
                                        else if(table.getSheetList().get(i).getRowList().get(rowN).getCellList().get(cellN).getValue().contains("Всього годин")){
                                        
                                            
                                            System.out.println("WARNING---------------/"+getCellValue(0,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),"Всього годин")+"/---------------------");  
                                        
                                        disp.setName("TOTAL");    
                                           
                                        disp.setType("Обов'язкові навчальні дисципліни");
                                                
                                        String fileName =  getCellValue(1,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),"Всього годин");
                                        int dotIndex = fileName.lastIndexOf(".");
                                        String name = fileName.substring(0, dotIndex);
                                        
                                        //subStr = getCellValue(3,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0").split("."); 
                                        disp.setTotalHours(Integer.parseInt(name));
                                        
                                        fileName =  getCellValue(2,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),"Всього годин");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        disp.setTotalCR(Integer.parseInt(name));
                                        
                                         fileName =  getCellValue(3,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),"Всього годин");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        disp.setTotalLessons(Integer.parseInt(name));
                                        
                                        
                                        
                                        
                                        
                                        
                                           Timetable titb1 = new Timetable();
                                        
                                        titb1.setType("лекц.");
                                        titb1.setGroupType("годин на потік");
                                        
                                      
                                        fileName =  getCellValue(6,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),"Всього годин");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        
                                        titb1.setHoursPerGroup(Integer.parseInt(name));
                                        disp.addTimetable(titb1);
                                        
                                        Timetable titb2 = new Timetable();
                                        
                                        titb2.setType("лаборат.");
                                        titb2.setGroupType("годин на підгруп");
                                      
                                        
                                        fileName =  getCellValue(8,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),"Всього годин");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        titb2.setHoursPerGroup(Integer.parseInt(name));
                                        
                                        disp.addTimetable(titb2);
                                        Timetable titb3 = new Timetable();
                                         titb3.setType("практ.");
                                        titb3.setGroupType("годин на групу/ підгрупу");
                                      
                                        fileName =  getCellValue(10,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),"Всього годин");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        titb3.setHoursPerGroup(Integer.parseInt(name));
                                        
                                        disp.addTimetable(titb3);
                                        Timetable titb4 = new Timetable();
                                        titb4.setType("семiн.");
                                        titb4.setGroupType("годин на груп");
                                        
                                        
                                        
                                         fileName =  getCellValue(12,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),"Всього годин");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        titb4.setHoursPerGroup(Integer.parseInt(name));
                                        disp.addTimetable(titb4);
                                        Timetable titb5 = new Timetable(); 
                                        titb5.setType("індивід.");
                                        titb5.setGroupType("годин на студ.");
                                        
                                            
                                        
                                             fileName =  getCellValue(14,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),"Всього годин");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        titb5.setHoursPerGroup(Integer.parseInt(name));
                                        disp.addTimetable(titb5);
                                        Timetable titb6 = new Timetable();
                                        titb6.setType("консульт.");
                                        titb6.setGroupType("годин на потік/ групу");
                                        
                                                                                
                                        fileName =  getCellValue(16,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),"Всього годин");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        titb6.setHoursPerGroup(Integer.parseInt(name));
                                        disp.addTimetable(titb6);
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        fileName =  getCellValue(17,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),"Всього годин");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        
                                        
                                        
                                        
                                        
                                        disp.setIndependentWorks(Integer.parseInt(name));
                                        
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                        }
                                        
                                        else{
                                        
                                        }
                                        body.addDiscipline(disp);
                                            
                                            
                                        //System.out.println(table.getSheetList().get(i).getRowList().get(rowN).getCellList().get(cellN).getValue());
                                        rowN++;
                                        }
                                    break;
                                    }
                                    else if(table.getSheetList().get(i).getRowList().get(h).getCellList().get(j).getValue().contains("Вибір з переліку (студент обирає 1 дисципліну з кожного переліку)")){
                                        cellN = j;
                                        rowN = h;
                                        rowN++;
                                        while(!isObject("Вибір з переліку (студент обирає 2 або більше дисципліни з кожного переліку)",table.getSheetList().get(i).getRowList().get(rowN).getCellList())){
                                        Discipline disp = new Discipline();
                                        if(table.getSheetList().get(i).getRowList().get(rowN).getCellList().get(cellN).getValue().contains(".0")){
                                        //String[] subStr;
                                        //System.out.println(getCellValue(0,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0"));
                                        //subStr = getCellValue(0,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0").split(".");
                                        String fileName1 =  getCellValue(0,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0");
                                        int dotIndex1 = fileName1.lastIndexOf(".");
                                        String name1 = fileName1.substring(0, dotIndex1);
                                        
                                        
                                        
                                        
                                        disp.setIndex(Integer.parseInt(name1));
                                        
                                        
                                        if(getCellValue(1,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0").contains("Перелік")){
                                        table.getSheetList().get(i).getRowList().get(rowN+1).getCell(getCellValueNext(table.getSheetList().get(i).getRowList().get(rowN).getCellList(),"Перелік"));
                                        }else{
                                        disp.setName(getCellValue(1,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0"));
                                        }
                                        
                                        disp.setType("Вибір з переліку (студент обирає 1 дисципліну з кожного переліку)");
                                        disp.setCode(getCellValue(2,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0"));
                                        //String[] subStr;
                                        String fileName =  getCellValue(3,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0");
                                        int dotIndex = fileName.lastIndexOf(".");
                                        String name = fileName.substring(0, dotIndex);
                                        
                                        //subStr = getCellValue(3,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0").split("."); 
                                        disp.setTotalHours(Integer.parseInt(name));
                                        
                                        fileName =  getCellValue(4,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        disp.setTotalCR(Integer.parseInt(name));
                                        
                                         fileName =  getCellValue(5,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        disp.setTotalLessons(Integer.parseInt(name));
                                         fileName =  getCellValue(6,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        disp.setStudentsCount(Integer.parseInt(name));
                                        
                                    
                                        Timetable titb1 = new Timetable();
                                        
                                        titb1.setType("лекц.");
                                        titb1.setGroupType("потоків");
                                        fileName =  getCellValue(7,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        
                                        titb1.setGroupStudentsCount(Integer.parseInt(name));
                                        
                                        fileName =  getCellValue(8,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        
                                        titb1.setHoursPerGroup(Integer.parseInt(name));
                                        disp.addTimetable(titb1);
                                        
                                        Timetable titb2 = new Timetable();
                                        
                                        titb2.setType("лаборат.");
                                        titb2.setGroupType("підгруп");
                                        fileName =  getCellValue(9,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        titb2.setGroupStudentsCount(Integer.parseInt(name));
                                        
                                        fileName =  getCellValue(10,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        titb2.setHoursPerGroup(Integer.parseInt(name));
                                        
                                        disp.addTimetable(titb2);
                                        Timetable titb3 = new Timetable();
                                         titb3.setType("практ.");
                                        titb3.setGroupType("груп/ підгруп");
                                        fileName =  getCellValue(11,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        titb3.setGroupStudentsCount(Integer.parseInt(name));
                                        fileName =  getCellValue(12,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        titb3.setHoursPerGroup(Integer.parseInt(name));
                                        
                                        disp.addTimetable(titb3);
                                        Timetable titb4 = new Timetable();
                                        titb4.setType("семiн.");
                                        titb4.setGroupType("груп");
                                        
                                         fileName =  getCellValue(13,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        titb4.setGroupStudentsCount(Integer.parseInt(name));
                                        
                                         fileName =  getCellValue(14,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        titb4.setHoursPerGroup(Integer.parseInt(name));
                                        disp.addTimetable(titb4);
                                        Timetable titb5 = new Timetable(); 
                                        titb5.setType("індивід.");
                                        titb5.setGroupType("к-ть студ.");
                                        
                                             fileName =  getCellValue(15,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        titb5.setGroupStudentsCount(Integer.parseInt(name));
                                        
                                             fileName =  getCellValue(16,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        titb5.setHoursPerGroup(Integer.parseInt(name));
                                        disp.addTimetable(titb5);
                                        Timetable titb6 = new Timetable();
                                        titb6.setType("консульт.");
                                        titb6.setGroupType("потоків / груп");
                                        
                                        fileName =  getCellValue(17,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        titb6.setGroupStudentsCount(Integer.parseInt(name));
                                        
                                        fileName =  getCellValue(18,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        titb6.setHoursPerGroup(Integer.parseInt(name));
                                        disp.addTimetable(titb6);
                                         
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                         fileName =  getCellValue(19,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        disp.setIndependentWorks(Integer.parseInt(name));
                                        
                                        disp.setControlForms(getCellValue(20,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0"));
                                        disp.setComment(getCellValue(21,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0"));
                                        }
                                        else if(table.getSheetList().get(i).getRowList().get(rowN).getCellList().get(cellN).getValue().contains("Всього годин")){
                                        
                                            
                                            System.out.println("WARNING---------------/"+getCellValue(0,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),"Всього годин")+"/---------------------");  
                                        
                                        disp.setName("TOTAL");    
                                           
                                        disp.setType("Обов'язкові навчальні дисципліни");
                                                
                                        String fileName =  getCellValue(1,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),"Всього годин");
                                        int dotIndex = fileName.lastIndexOf(".");
                                        String name = fileName.substring(0, dotIndex);
                                        
                                        //subStr = getCellValue(3,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0").split("."); 
                                        disp.setTotalHours(Integer.parseInt(name));
                                        
                                        fileName =  getCellValue(2,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),"Всього годин");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        disp.setTotalCR(Integer.parseInt(name));
                                        
                                         fileName =  getCellValue(3,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),"Всього годин");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        disp.setTotalLessons(Integer.parseInt(name));
                                        
                                        
                                        
                                        
                                        
                                        
                                           Timetable titb1 = new Timetable();
                                        
                                        titb1.setType("лекц.");
                                        titb1.setGroupType("годин на потік");
                                        
                                      
                                        fileName =  getCellValue(6,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),"Всього годин");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        
                                        titb1.setHoursPerGroup(Integer.parseInt(name));
                                        disp.addTimetable(titb1);
                                        
                                        Timetable titb2 = new Timetable();
                                        
                                        titb2.setType("лаборат.");
                                        titb2.setGroupType("годин на підгруп");
                                      
                                        
                                        fileName =  getCellValue(8,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),"Всього годин");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        titb2.setHoursPerGroup(Integer.parseInt(name));
                                        
                                        disp.addTimetable(titb2);
                                        Timetable titb3 = new Timetable();
                                         titb3.setType("практ.");
                                        titb3.setGroupType("годин на групу/ підгрупу");
                                      
                                        fileName =  getCellValue(10,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),"Всього годин");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        titb3.setHoursPerGroup(Integer.parseInt(name));
                                        
                                        disp.addTimetable(titb3);
                                        Timetable titb4 = new Timetable();
                                        titb4.setType("семiн.");
                                        titb4.setGroupType("годин на груп");
                                        
                                        
                                        
                                         fileName =  getCellValue(12,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),"Всього годин");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        titb4.setHoursPerGroup(Integer.parseInt(name));
                                        disp.addTimetable(titb4);
                                        Timetable titb5 = new Timetable(); 
                                        titb5.setType("індивід.");
                                        titb5.setGroupType("годин на студ.");
                                        
                                            
                                        
                                             fileName =  getCellValue(14,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),"Всього годин");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        titb5.setHoursPerGroup(Integer.parseInt(name));
                                        disp.addTimetable(titb5);
                                        Timetable titb6 = new Timetable();
                                        titb6.setType("консульт.");
                                        titb6.setGroupType("годин на потік/ групу");
                                        
                                                                                
                                        fileName =  getCellValue(16,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),"Всього годин");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        titb6.setHoursPerGroup(Integer.parseInt(name));
                                        disp.addTimetable(titb6);
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        fileName =  getCellValue(17,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),"Всього годин");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        
                                        
                                        
                                        
                                        
                                        disp.setIndependentWorks(Integer.parseInt(name));
                                        
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                        }
                                        
                                        else{
                                        
                                        }
                                        body.addDiscipline(disp);
                                            
                                            
                                        //System.out.println(table.getSheetList().get(i).getRowList().get(rowN).getCellList().get(cellN).getValue());
                                        rowN++;
                                        }
                                    break;
                                    }else if(table.getSheetList().get(i).getRowList().get(h).getCellList().get(j).getValue().contains("Вибір з переліку (студент обирає 2 або більше дисципліни з кожного переліку)")){
                                        cellN = j;
                                        rowN = h;
                                        rowN++;
                                        while(!isObject("Вибір з переліку (студент обирає кілька дисциплін з кожного переліку)",table.getSheetList().get(i).getRowList().get(rowN).getCellList())){
                                        Discipline disp = new Discipline();
                                        if(table.getSheetList().get(i).getRowList().get(rowN).getCellList().get(cellN).getValue().contains(".0")){
                                        //String[] subStr;
                                        //System.out.println(getCellValue(0,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0"));
                                        //subStr = getCellValue(0,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0").split(".");
                                        String fileName1 =  getCellValue(0,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0");
                                        int dotIndex1 = fileName1.lastIndexOf(".");
                                        String name1 = fileName1.substring(0, dotIndex1);
                                        
                                        
                                        
                                        
                                        disp.setIndex(Integer.parseInt(name1));
                                        disp.setName(getCellValue(1,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0"));
                                        disp.setType("Вибір з переліку (студент обирає 2 або більше дисципліни з кожного переліку)");
                                        disp.setCode(getCellValue(2,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0"));
                                        //String[] subStr;
                                        String fileName =  getCellValue(3,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0");
                                        int dotIndex = fileName.lastIndexOf(".");
                                        String name = fileName.substring(0, dotIndex);
                                        
                                        //subStr = getCellValue(3,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0").split("."); 
                                        disp.setTotalHours(Integer.parseInt(name));
                                        
                                        fileName =  getCellValue(4,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        disp.setTotalCR(Integer.parseInt(name));
                                        
                                         fileName =  getCellValue(5,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        disp.setTotalLessons(Integer.parseInt(name));
                                         fileName =  getCellValue(6,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        disp.setStudentsCount(Integer.parseInt(name));
                                        
                                    
                                        Timetable titb1 = new Timetable();
                                        
                                        titb1.setType("лекц.");
                                        titb1.setGroupType("потоків");
                                        fileName =  getCellValue(7,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        
                                        titb1.setGroupStudentsCount(Integer.parseInt(name));
                                        
                                        fileName =  getCellValue(8,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        
                                        titb1.setHoursPerGroup(Integer.parseInt(name));
                                        disp.addTimetable(titb1);
                                        
                                        Timetable titb2 = new Timetable();
                                        
                                        titb2.setType("лаборат.");
                                        titb2.setGroupType("підгруп");
                                        fileName =  getCellValue(9,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        titb2.setGroupStudentsCount(Integer.parseInt(name));
                                        
                                        fileName =  getCellValue(10,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        titb2.setHoursPerGroup(Integer.parseInt(name));
                                        
                                        disp.addTimetable(titb2);
                                        Timetable titb3 = new Timetable();
                                         titb3.setType("практ.");
                                        titb3.setGroupType("груп/ підгруп");
                                        fileName =  getCellValue(11,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        titb3.setGroupStudentsCount(Integer.parseInt(name));
                                        fileName =  getCellValue(12,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        titb3.setHoursPerGroup(Integer.parseInt(name));
                                        
                                        disp.addTimetable(titb3);
                                        Timetable titb4 = new Timetable();
                                        titb4.setType("семiн.");
                                        titb4.setGroupType("груп");
                                        
                                         fileName =  getCellValue(13,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        titb4.setGroupStudentsCount(Integer.parseInt(name));
                                        
                                         fileName =  getCellValue(14,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        titb4.setHoursPerGroup(Integer.parseInt(name));
                                        disp.addTimetable(titb4);
                                        Timetable titb5 = new Timetable(); 
                                        titb5.setType("індивід.");
                                        titb5.setGroupType("к-ть студ.");
                                        
                                             fileName =  getCellValue(15,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        titb5.setGroupStudentsCount(Integer.parseInt(name));
                                        
                                             fileName =  getCellValue(16,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        titb5.setHoursPerGroup(Integer.parseInt(name));
                                        disp.addTimetable(titb5);
                                        Timetable titb6 = new Timetable();
                                        titb6.setType("консульт.");
                                        titb6.setGroupType("потоків / груп");
                                        
                                        fileName =  getCellValue(17,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        titb6.setGroupStudentsCount(Integer.parseInt(name));
                                        
                                        fileName =  getCellValue(18,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        titb6.setHoursPerGroup(Integer.parseInt(name));
                                        disp.addTimetable(titb6);
                                         
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                         fileName =  getCellValue(19,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        disp.setIndependentWorks(Integer.parseInt(name));
                                        
                                        disp.setControlForms(getCellValue(20,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0"));
                                        disp.setComment(getCellValue(21,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0"));
                                        }
                                        else if(table.getSheetList().get(i).getRowList().get(rowN).getCellList().get(cellN).getValue().contains("Всього годин")){
                                        
                                            
                                            System.out.println("WARNING---------------/"+getCellValue(0,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),"Всього годин")+"/---------------------");  
                                        
                                        disp.setName("TOTAL");    
                                           
                                        disp.setType("Обов'язкові навчальні дисципліни");
                                                
                                        String fileName =  getCellValue(1,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),"Всього годин");
                                        int dotIndex = fileName.lastIndexOf(".");
                                        String name = fileName.substring(0, dotIndex);
                                        
                                        //subStr = getCellValue(3,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0").split("."); 
                                        disp.setTotalHours(Integer.parseInt(name));
                                        
                                        fileName =  getCellValue(2,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),"Всього годин");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        disp.setTotalCR(Integer.parseInt(name));
                                        
                                         fileName =  getCellValue(3,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),"Всього годин");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        disp.setTotalLessons(Integer.parseInt(name));
                                        
                                        
                                        
                                        
                                        
                                        
                                           Timetable titb1 = new Timetable();
                                        
                                        titb1.setType("лекц.");
                                        titb1.setGroupType("годин на потік");
                                        
                                      
                                        fileName =  getCellValue(6,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),"Всього годин");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        
                                        titb1.setHoursPerGroup(Integer.parseInt(name));
                                        disp.addTimetable(titb1);
                                        
                                        Timetable titb2 = new Timetable();
                                        
                                        titb2.setType("лаборат.");
                                        titb2.setGroupType("годин на підгруп");
                                      
                                        
                                        fileName =  getCellValue(8,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),"Всього годин");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        titb2.setHoursPerGroup(Integer.parseInt(name));
                                        
                                        disp.addTimetable(titb2);
                                        Timetable titb3 = new Timetable();
                                         titb3.setType("практ.");
                                        titb3.setGroupType("годин на групу/ підгрупу");
                                      
                                        fileName =  getCellValue(10,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),"Всього годин");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        titb3.setHoursPerGroup(Integer.parseInt(name));
                                        
                                        disp.addTimetable(titb3);
                                        Timetable titb4 = new Timetable();
                                        titb4.setType("семiн.");
                                        titb4.setGroupType("годин на груп");
                                        
                                        
                                        
                                         fileName =  getCellValue(12,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),"Всього годин");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        titb4.setHoursPerGroup(Integer.parseInt(name));
                                        disp.addTimetable(titb4);
                                        Timetable titb5 = new Timetable(); 
                                        titb5.setType("індивід.");
                                        titb5.setGroupType("годин на студ.");
                                        
                                            
                                        
                                             fileName =  getCellValue(14,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),"Всього годин");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        titb5.setHoursPerGroup(Integer.parseInt(name));
                                        disp.addTimetable(titb5);
                                        Timetable titb6 = new Timetable();
                                        titb6.setType("консульт.");
                                        titb6.setGroupType("годин на потік/ групу");
                                        
                                                                                
                                        fileName =  getCellValue(16,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),"Всього годин");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        titb6.setHoursPerGroup(Integer.parseInt(name));
                                        disp.addTimetable(titb6);
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        fileName =  getCellValue(17,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),"Всього годин");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        
                                        
                                        
                                        
                                        
                                        disp.setIndependentWorks(Integer.parseInt(name));
                                        
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                        }
                                        
                                        else{
                                        
                                        }
                                        body.addDiscipline(disp);
                                            
                                            
                                        //System.out.println(table.getSheetList().get(i).getRowList().get(rowN).getCellList().get(cellN).getValue());
                                        rowN++;
                                        }
                                    break;
                                    }
                                    else if(table.getSheetList().get(i).getRowList().get(h).getCellList().get(j).getValue().contains("Вибір з переліку (студент обирає кілька дисциплін з кожного переліку)")){
                                        cellN = j;
                                        rowN = h;
                                        rowN++;
                                        while(!isObject("Факультативні та позакредитні дисципліни",table.getSheetList().get(i).getRowList().get(rowN).getCellList())){
                                        Discipline disp = new Discipline();
                                        if(table.getSheetList().get(i).getRowList().get(rowN).getCellList().get(cellN).getValue().contains(".0")){
                                        //String[] subStr;
                                        //System.out.println(getCellValue(0,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0"));
                                        //subStr = getCellValue(0,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0").split(".");
                                       String fileName1 =  getCellValue(0,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0");
                                        int dotIndex1 = fileName1.lastIndexOf(".");
                                        String name1 = fileName1.substring(0, dotIndex1);
                                        
                                        
                                        
                                        
                                        disp.setIndex(Integer.parseInt(name1));
                                        disp.setName(getCellValue(1,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0"));
                                        disp.setType("Вибір з переліку (студент обирає кілька дисциплін з кожного переліку)");
                                        disp.setCode(getCellValue(2,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0"));
                                        //String[] subStr;
                                        String fileName =  getCellValue(3,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0");
                                        int dotIndex = fileName.lastIndexOf(".");
                                        String name = fileName.substring(0, dotIndex);
                                        
                                        //subStr = getCellValue(3,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0").split("."); 
                                        disp.setTotalHours(Integer.parseInt(name));
                                        
                                        fileName =  getCellValue(4,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        disp.setTotalCR(Integer.parseInt(name));
                                        
                                         fileName =  getCellValue(5,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        disp.setTotalLessons(Integer.parseInt(name));
                                         fileName =  getCellValue(6,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        disp.setStudentsCount(Integer.parseInt(name));
                                        
                                    
                                        Timetable titb1 = new Timetable();
                                        
                                        titb1.setType("лекц.");
                                        titb1.setGroupType("потоків");
                                        fileName =  getCellValue(7,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        
                                        titb1.setGroupStudentsCount(Integer.parseInt(name));
                                        
                                        fileName =  getCellValue(8,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        
                                        titb1.setHoursPerGroup(Integer.parseInt(name));
                                        disp.addTimetable(titb1);
                                        
                                        Timetable titb2 = new Timetable();
                                        
                                        titb2.setType("лаборат.");
                                        titb2.setGroupType("підгруп");
                                        fileName =  getCellValue(9,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        titb2.setGroupStudentsCount(Integer.parseInt(name));
                                        
                                        fileName =  getCellValue(10,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        titb2.setHoursPerGroup(Integer.parseInt(name));
                                        
                                        disp.addTimetable(titb2);
                                        Timetable titb3 = new Timetable();
                                         titb3.setType("практ.");
                                        titb3.setGroupType("груп/ підгруп");
                                        fileName =  getCellValue(11,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        titb3.setGroupStudentsCount(Integer.parseInt(name));
                                        fileName =  getCellValue(12,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        titb3.setHoursPerGroup(Integer.parseInt(name));
                                        
                                        disp.addTimetable(titb3);
                                        Timetable titb4 = new Timetable();
                                        titb4.setType("семiн.");
                                        titb4.setGroupType("груп");
                                        
                                         fileName =  getCellValue(13,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        titb4.setGroupStudentsCount(Integer.parseInt(name));
                                        
                                         fileName =  getCellValue(14,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        titb4.setHoursPerGroup(Integer.parseInt(name));
                                        disp.addTimetable(titb4);
                                        Timetable titb5 = new Timetable(); 
                                        titb5.setType("індивід.");
                                        titb5.setGroupType("к-ть студ.");
                                        
                                             fileName =  getCellValue(15,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        titb5.setGroupStudentsCount(Integer.parseInt(name));
                                        
                                             fileName =  getCellValue(16,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        titb5.setHoursPerGroup(Integer.parseInt(name));
                                        disp.addTimetable(titb5);
                                        Timetable titb6 = new Timetable();
                                        titb6.setType("консульт.");
                                        titb6.setGroupType("потоків / груп");
                                        
                                        fileName =  getCellValue(17,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        titb6.setGroupStudentsCount(Integer.parseInt(name));
                                        
                                        fileName =  getCellValue(18,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        titb6.setHoursPerGroup(Integer.parseInt(name));
                                        disp.addTimetable(titb6);
                                         
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                         fileName =  getCellValue(19,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        disp.setIndependentWorks(Integer.parseInt(name));
                                        
                                        disp.setControlForms(getCellValue(20,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0"));
                                        disp.setComment(getCellValue(21,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0"));
                                        }
                                        else if(table.getSheetList().get(i).getRowList().get(rowN).getCellList().get(cellN).getValue().contains("Всього годин")){
                                        
                                            
                                            System.out.println("WARNING---------------/"+getCellValue(0,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),"Всього годин")+"/---------------------");  
                                        
                                        disp.setName("TOTAL");    
                                           
                                        disp.setType("Обов'язкові навчальні дисципліни");
                                                
                                        String fileName =  getCellValue(1,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),"Всього годин");
                                        int dotIndex = fileName.lastIndexOf(".");
                                        String name = fileName.substring(0, dotIndex);
                                        
                                        //subStr = getCellValue(3,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0").split("."); 
                                        disp.setTotalHours(Integer.parseInt(name));
                                        
                                        fileName =  getCellValue(2,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),"Всього годин");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        disp.setTotalCR(Integer.parseInt(name));
                                        
                                         fileName =  getCellValue(3,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),"Всього годин");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        disp.setTotalLessons(Integer.parseInt(name));
                                        
                                        
                                        
                                        
                                        
                                        
                                           Timetable titb1 = new Timetable();
                                        
                                        titb1.setType("лекц.");
                                        titb1.setGroupType("годин на потік");
                                        
                                      
                                        fileName =  getCellValue(6,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),"Всього годин");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        
                                        titb1.setHoursPerGroup(Integer.parseInt(name));
                                        disp.addTimetable(titb1);
                                        
                                        Timetable titb2 = new Timetable();
                                        
                                        titb2.setType("лаборат.");
                                        titb2.setGroupType("годин на підгруп");
                                      
                                        
                                        fileName =  getCellValue(8,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),"Всього годин");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        titb2.setHoursPerGroup(Integer.parseInt(name));
                                        
                                        disp.addTimetable(titb2);
                                        Timetable titb3 = new Timetable();
                                         titb3.setType("практ.");
                                        titb3.setGroupType("годин на групу/ підгрупу");
                                      
                                        fileName =  getCellValue(10,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),"Всього годин");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        titb3.setHoursPerGroup(Integer.parseInt(name));
                                        
                                        disp.addTimetable(titb3);
                                        Timetable titb4 = new Timetable();
                                        titb4.setType("семiн.");
                                        titb4.setGroupType("годин на груп");
                                        
                                        
                                        
                                         fileName =  getCellValue(12,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),"Всього годин");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        titb4.setHoursPerGroup(Integer.parseInt(name));
                                        disp.addTimetable(titb4);
                                        Timetable titb5 = new Timetable(); 
                                        titb5.setType("індивід.");
                                        titb5.setGroupType("годин на студ.");
                                        
                                            
                                        
                                             fileName =  getCellValue(14,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),"Всього годин");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        titb5.setHoursPerGroup(Integer.parseInt(name));
                                        disp.addTimetable(titb5);
                                        Timetable titb6 = new Timetable();
                                        titb6.setType("консульт.");
                                        titb6.setGroupType("годин на потік/ групу");
                                        
                                                                                
                                        fileName =  getCellValue(16,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),"Всього годин");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        titb6.setHoursPerGroup(Integer.parseInt(name));
                                        disp.addTimetable(titb6);
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        fileName =  getCellValue(17,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),"Всього годин");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        
                                        
                                        
                                        
                                        
                                        disp.setIndependentWorks(Integer.parseInt(name));
                                        
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                        }
                                        
                                        else{
                                        
                                        }
                                        body.addDiscipline(disp);
                                            
                                            
                                        //System.out.println(table.getSheetList().get(i).getRowList().get(rowN).getCellList().get(cellN).getValue());
                                        rowN++;
                                        }
                                    break;
                                    }
                                    else if(table.getSheetList().get(i).getRowList().get(h).getCellList().get(j).getValue().contains("Факультативні та позакредитні дисципліни")){
                                        cellN = j;
                                        rowN = h;
                                        rowN++;
                                        while(!isObject("Всього:",table.getSheetList().get(i).getRowList().get(rowN).getCellList())){
                                        Discipline disp = new Discipline();
                                        if(table.getSheetList().get(i).getRowList().get(rowN).getCellList().get(cellN).getValue().contains(".0")){
                                        //String[] subStr;
                                        //System.out.println(getCellValue(0,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0"));
                                        //subStr = getCellValue(0,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0").split(".");
                                        String fileName1 =  getCellValue(0,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0");
                                        int dotIndex1 = fileName1.lastIndexOf(".");
                                        String name1 = fileName1.substring(0, dotIndex1);
                                        
                                        
                                        
                                        
                                        disp.setIndex(Integer.parseInt(name1));
                                        disp.setName(getCellValue(1,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0"));
                                        disp.setType("Факультативні та позакредитні дисципліни");
                                        disp.setCode(getCellValue(2,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0"));
                                        //String[] subStr;
                                        String fileName =  getCellValue(3,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0");
                                        int dotIndex = fileName.lastIndexOf(".");
                                        String name = fileName.substring(0, dotIndex);
                                        
                                        //subStr = getCellValue(3,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0").split("."); 
                                        disp.setTotalHours(Integer.parseInt(name));
                                        
                                        fileName =  getCellValue(4,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        disp.setTotalCR(Integer.parseInt(name));
                                        
                                         fileName =  getCellValue(5,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        disp.setTotalLessons(Integer.parseInt(name));
                                         fileName =  getCellValue(6,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        disp.setStudentsCount(Integer.parseInt(name));
                                        
                                    
                                        Timetable titb1 = new Timetable();
                                        
                                        titb1.setType("лекц.");
                                        titb1.setGroupType("потоків");
                                        fileName =  getCellValue(7,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        
                                        titb1.setGroupStudentsCount(Integer.parseInt(name));
                                        
                                        fileName =  getCellValue(8,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        
                                        titb1.setHoursPerGroup(Integer.parseInt(name));
                                        disp.addTimetable(titb1);
                                        
                                        Timetable titb2 = new Timetable();
                                        
                                        titb2.setType("лаборат.");
                                        titb2.setGroupType("підгруп");
                                        fileName =  getCellValue(9,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        titb2.setGroupStudentsCount(Integer.parseInt(name));
                                        
                                        fileName =  getCellValue(10,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        titb2.setHoursPerGroup(Integer.parseInt(name));
                                        
                                        disp.addTimetable(titb2);
                                        Timetable titb3 = new Timetable();
                                         titb3.setType("практ.");
                                        titb3.setGroupType("груп/ підгруп");
                                        fileName =  getCellValue(11,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        titb3.setGroupStudentsCount(Integer.parseInt(name));
                                        fileName =  getCellValue(12,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        titb3.setHoursPerGroup(Integer.parseInt(name));
                                        
                                        disp.addTimetable(titb3);
                                        Timetable titb4 = new Timetable();
                                        titb4.setType("семiн.");
                                        titb4.setGroupType("груп");
                                        
                                         fileName =  getCellValue(13,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        titb4.setGroupStudentsCount(Integer.parseInt(name));
                                        
                                         fileName =  getCellValue(14,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        titb4.setHoursPerGroup(Integer.parseInt(name));
                                        disp.addTimetable(titb4);
                                        Timetable titb5 = new Timetable(); 
                                        titb5.setType("індивід.");
                                        titb5.setGroupType("к-ть студ.");
                                        
                                             fileName =  getCellValue(15,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        titb5.setGroupStudentsCount(Integer.parseInt(name));
                                        
                                             fileName =  getCellValue(16,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        titb5.setHoursPerGroup(Integer.parseInt(name));
                                        disp.addTimetable(titb5);
                                        Timetable titb6 = new Timetable();
                                        titb6.setType("консульт.");
                                        titb6.setGroupType("потоків / груп");
                                        
                                        fileName =  getCellValue(17,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        titb6.setGroupStudentsCount(Integer.parseInt(name));
                                        
                                        fileName =  getCellValue(18,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        titb6.setHoursPerGroup(Integer.parseInt(name));
                                        disp.addTimetable(titb6);
                                         
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                         fileName =  getCellValue(19,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        disp.setIndependentWorks(Integer.parseInt(name));
                                        
                                        disp.setControlForms(getCellValue(20,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0"));
                                        disp.setComment(getCellValue(21,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0"));
                                        }
                                        else if(table.getSheetList().get(i).getRowList().get(rowN).getCellList().get(cellN).getValue().contains("Всього годин")){
                                        
                                            
                                            System.out.println("WARNING---------------/"+getCellValue(0,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),"Всього годин")+"/---------------------");  
                                        
                                        disp.setName("TOTAL");    
                                           
                                        disp.setType("Обов'язкові навчальні дисципліни");
                                                
                                        String fileName =  getCellValue(1,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),"Всього годин");
                                        int dotIndex = fileName.lastIndexOf(".");
                                        String name = fileName.substring(0, dotIndex);
                                        
                                        //subStr = getCellValue(3,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),".0").split("."); 
                                        disp.setTotalHours(Integer.parseInt(name));
                                        
                                        fileName =  getCellValue(2,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),"Всього годин");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        disp.setTotalCR(Integer.parseInt(name));
                                        
                                         fileName =  getCellValue(3,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),"Всього годин");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        disp.setTotalLessons(Integer.parseInt(name));
                                        
                                        
                                        
                                        
                                        
                                        
                                           Timetable titb1 = new Timetable();
                                        
                                        titb1.setType("лекц.");
                                        titb1.setGroupType("годин на потік");
                                        
                                      
                                        fileName =  getCellValue(6,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),"Всього годин");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        
                                        titb1.setHoursPerGroup(Integer.parseInt(name));
                                        disp.addTimetable(titb1);
                                        
                                        Timetable titb2 = new Timetable();
                                        
                                        titb2.setType("лаборат.");
                                        titb2.setGroupType("годин на підгруп");
                                      
                                        
                                        fileName =  getCellValue(8,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),"Всього годин");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        titb2.setHoursPerGroup(Integer.parseInt(name));
                                        
                                        disp.addTimetable(titb2);
                                        Timetable titb3 = new Timetable();
                                         titb3.setType("практ.");
                                        titb3.setGroupType("годин на групу/ підгрупу");
                                      
                                        fileName =  getCellValue(10,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),"Всього годин");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        titb3.setHoursPerGroup(Integer.parseInt(name));
                                        
                                        disp.addTimetable(titb3);
                                        Timetable titb4 = new Timetable();
                                        titb4.setType("семiн.");
                                        titb4.setGroupType("годин на груп");
                                        
                                        
                                        
                                         fileName =  getCellValue(12,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),"Всього годин");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        titb4.setHoursPerGroup(Integer.parseInt(name));
                                        disp.addTimetable(titb4);
                                        Timetable titb5 = new Timetable(); 
                                        titb5.setType("індивід.");
                                        titb5.setGroupType("годин на студ.");
                                        
                                            
                                        
                                             fileName =  getCellValue(14,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),"Всього годин");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        titb5.setHoursPerGroup(Integer.parseInt(name));
                                        disp.addTimetable(titb5);
                                        Timetable titb6 = new Timetable();
                                        titb6.setType("консульт.");
                                        titb6.setGroupType("годин на потік/ групу");
                                        
                                                                                
                                        fileName =  getCellValue(16,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),"Всього годин");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        titb6.setHoursPerGroup(Integer.parseInt(name));
                                        disp.addTimetable(titb6);
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        fileName =  getCellValue(17,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),"Всього годин");
                                        dotIndex = fileName.lastIndexOf(".");
                                        name = fileName.substring(0, dotIndex);
                                        
                                        
                                        
                                        
                                        
                                        
                                        disp.setIndependentWorks(Integer.parseInt(name));
                                        
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                        }
                                        
                                        else{
                                        
                                        }
                                        body.addDiscipline(disp);
                                            
                                            
                                        //System.out.println(table.getSheetList().get(i).getRowList().get(rowN).getCellList().get(cellN).getValue());
                                        rowN++;
                                        }
                                    break;
                                    }
                                    else if(table.getSheetList().get(i).getRowList().get(h).getCellList().get(j).getValue().contains("Декан")){
                                        cellN = table.getSheetList().get(i).getRowList().get(h).getCellList().get(j).getId();
                                        rowN = table.getSheetList().get(i).getRowList().get(h).getId();
                                        //
                                        cellN++;
                                        body.setDirector(getCellValue(1,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),"Декан"));
                                    break;
                                    }
                                    
                                    //System.out.println(newtable.getSheetList().get(i).getRowList().get(h).getCellList().get(j).getHieght());                                                    
                                }
                            }
        restTemplate.postForObject(URL, body , Body.class);
        body.clearDiscipline();
        }     
                        
                    
        
        
        
        
//        Body body = new Body();
//                                    //body.setUniversity(table.getSheetList().get(i).getRowList().get(h).getCellList().get(j).getValue());
//                                    body.setUniversity(table.getName());
//                                    body.setCourse(1);
//                                    body.setSemester(2);
//                                    restTemplate.postForObject(URL, body , Body.class);
//        
//        
        
        
        
        

         return "result";
    }
    public static boolean isObject(String Object, ArrayList<CellT> list){
        for(int i = 0; i <list.size(); i++){
            if(list.get(i).getValue().contains(Object)){
                return true;
            }
        }
        return false;
    }
    
     public static int getCellValueNext( ArrayList<CellT> list, String key){
          for(int i = 0; i <list.size(); i++){
              if(list.get(i).getValue().contains(key)){
              return list.get(i).getId();
              
              }
          }
     
         return 0;
     }
    
    
    
    public static String getCellValue(int Pos , ArrayList<CellT> list, String key){
        ArrayList<String> ValueP = new ArrayList<String>();
        ArrayList<String> Value = new ArrayList<String>();
        for(int i = 0; i <list.size(); i++){
           if(!ValueP.contains(list.get(i).getPossion())){
           ValueP.add(list.get(i).getPossion());
           }
           else{
               
           }
        
        }
       // System.out.println("'''''''''''''''''''''''''''''''");
        for(int i = 0; i <ValueP.size(); i++){
            //System.out.println(ValueP.get(i)); 
            for(int h = 0; h <list.size(); h++){
                if(ValueP.get(i).equals(list.get(h).getPossion())){
                    //if(!Value.contains(list.get(h).getValue()))
                        Value.add(list.get(h).getValue());
                       
                       // System.out.println(list.get(h).getValue());
                        break;
                    
                }
             }
        }
   // System.out.println("'''''''''''''''''''''''''''''''''");
    
    for(int i = 0; i <Value.size(); i++){
    if(Value.get(i).contains(key)){
    return Value.get(i+Pos);
    }
    }
    return "error";
    }
    
    
    
}

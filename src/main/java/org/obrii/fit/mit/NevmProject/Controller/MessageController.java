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
                                        boolean can = true;
                                        cellN = j;
                                        rowN = h;
                                        rowN++;
                                        while(!isObject("Дисципліни вибору ВНЗ",table.getSheetList().get(i).getRowList().get(rowN).getCellList())){
                                        
                                        System.out.println(table.getSheetList().get(i).getRowList().get(rowN).getCellList().get(cellN).getValue());
                                        rowN++;
                                        }
                                    break;
                                    }
                                    else if(table.getSheetList().get(i).getRowList().get(h).getCellList().get(j).getValue().contains("Декан")){
                                        cellN = table.getSheetList().get(i).getRowList().get(h).getCellList().get(j).getId();
                                        rowN = table.getSheetList().get(i).getRowList().get(h).getId();
                                        //System.out.println("WARNING---------------/"+getCellValue(11,table.getSheetList().get(i).getRowList().get(rowN).getCellList())+"/---------------------");  
                                         
                                        cellN++;
                                        body.setDirector(getCellValue(1,table.getSheetList().get(i).getRowList().get(rowN).getCellList(),"Декан"));
                                    break;
                                    }
                                    
                                    //System.out.println(newtable.getSheetList().get(i).getRowList().get(h).getCellList().get(j).getHieght());                                                    
                                }
                            }
        restTemplate.postForObject(URL, body , Body.class);
        }     
                        
                    
        
        
        
        
//        Body body = new Body();
//                                    //body.setUniversity(table.getSheetList().get(i).getRowList().get(h).getCellList().get(j).getValue());
//                                    body.setUniversity(table.getName());
//                                    body.setCourse(1);
//                                    body.setSemester(2);
//                                    restTemplate.postForObject(URL, body , Body.class);
//        
//        
        
        
        
        

         return "inde";
    }
    public static boolean isObject(String Object, ArrayList<CellT> list){
        for(int i = 0; i <list.size(); i++){
            if(list.get(i).getValue().contains(Object)){
                return true;
            }
        }
        return false;
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
        //System.out.println("'''''''''''''''''''''''''''''''");
        for(int i = 0; i <ValueP.size(); i++){
            //System.out.println(ValueP.get(i)); 
            for(int h = 0; h <list.size(); h++){
                if(ValueP.get(i).equals(list.get(h).getPossion())){
                    //if(!Value.contains(list.get(h).getValue()))
                        Value.add(list.get(h).getValue());
                       
                        //System.out.println(list.get(h).getValue());
                        break;
                    
                }
             }
        }
    //System.out.println("'''''''''''''''''''''''''''''''''");
    
    for(int i = 0; i <Value.size(); i++){
    if(Value.get(i).contains(key)){
    return Value.get(i+Pos);
    }
    }
    return "error";
    }
    
    
    
}

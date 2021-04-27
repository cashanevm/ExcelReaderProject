/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.obrii.fit.mit.NevmProject.Controller;

import java.util.List;
import org.obrii.fit.mit.NevmProject.Domain.Embedded;
import org.obrii.fit.mit.NevmProject.Domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author 38068
 */
@Controller
public class MessageController {

    private final String URL = "http://dp2021.mit.obrii.org/NevmerzhytskyiProject/Lab7service/student";

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/getMessage")
    public String getFormData(Model model) {

        Embedded messageEntity = restTemplate.getForObject(URL, Embedded.class);
        
        List<Student> list = messageEntity.getListOfStudents().getStudentList();        
        
        model.addAttribute("students", list);

        return "index";
    }

    

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.obrii.fit.mit.NevmProject.Controller;

import javax.validation.Valid;
import org.obrii.fit.mit.NevmProject.Domain.User;
import org.obrii.fit.mit.NevmProject.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author NEVM PC
 */
@Controller
        //("/")
public class UserController {
    
    @Autowired
    UserRepository userRepository;
    
    
    
    @GetMapping("/signup")
    public String showSignUpForm(User user) {
        return "add-user";
    }
    
    @PostMapping("/adduser")
    public String addUser(@Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-user";
        }
        
        userRepository.save(user);
        return "redirect:/index";
    }
    @GetMapping("/index")
    public String showUserList(Model model) {
        model.addAttribute("users", userRepository.findAll());
    
        return "index";
    }
    // additional CRUD methods
}
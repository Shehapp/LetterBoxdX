package com.controller;

import com.DTO.UserDTO;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @GetMapping("/login")
    String login(){

        return "login-page";
    }

    @GetMapping("/register")
    String register(@ModelAttribute("userDTO") UserDTO user){
        return "register-page";
    }

    @PostMapping("/register")
    String registerPost(@Valid @ModelAttribute("userDTO") UserDTO user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "register-page";
        }
        userService.addUser(user);
        return "redirect:/login";
    }


    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class,new StringTrimmerEditor(true));
    }
}

package com.controller;

import com.DTO.UserDTO;
import com.service.ConfirmationTokenService;
import com.service.EmailService;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class LoginController {

    @Autowired
    UserService userService;
    @Autowired
    EmailService emailService;
    @Autowired
    ConfirmationTokenService confirmationTokenService;

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
        return "redirect:/login?confirm";
    }

    @GetMapping("/confirm/{token}")
    @ResponseBody
    String confirm(@PathVariable("token") String token){
        confirmationTokenService.confirm(token);
        return "Confirmed";
    }
    @RequestMapping("/access_denied")
    String accessDenied(){
        return "access_denied";
    }



    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class,new StringTrimmerEditor(true));
    }
}

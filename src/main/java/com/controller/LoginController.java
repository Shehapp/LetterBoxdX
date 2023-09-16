package com.controller;

import com.DTO.ConfirmEmail;
import com.DTO.UserDTO;
import com.service.EmailService;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.mail.Session;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class LoginController {

    @Autowired
    UserService userService;
    @Autowired
    EmailService emailService;

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

    @RequestMapping("/access_denied")
    String accessDenied(){
        return "access_denied";
    }


    @GetMapping("/confirm")
    String confirm(@ModelAttribute("confirmEmail")ConfirmEmail confirmEmail){
        return "confirm_email-page";
    }
    @PostMapping("/confirm")
    String confirm(@Valid @ModelAttribute("confirmEmail")ConfirmEmail confirmEmail,
                   BindingResult bindingResult,
                   HttpSession session){
        if(bindingResult.hasErrors()){
            return "redirect:/confirm?error";
        }
        String userName = userService.getUserNameByEmail(confirmEmail.getEmail());
        String code=emailService.sendSMS(confirmEmail.getEmail(),userName);
        session.setAttribute("code",code);
        session.setAttribute("userName",userName);
        session.setMaxInactiveInterval(60);
        return "confirm_code-page";
    }
    @PostMapping("/confirm-code")
    String confirm(@RequestParam("code") String userCode,
                   @SessionAttribute("code") String code,
                     @SessionAttribute("userName") String userName,
                   Model model){
        if(userCode.equals(code)){
            userService.enableUser(userName);
            return "redirect:/login?success";
        }
        model.addAttribute("error","Invalid code");
        return "confirm_code-page";
    }
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class,new StringTrimmerEditor(true));
    }
}

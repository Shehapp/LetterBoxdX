package com.controller;

import com.DTO.ProfileDTO;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    UserService userService;

    @GetMapping("/{name}")
    public String profile(@PathVariable String name,
                          Model model,
                          Authentication authentication) {
        String userName=authentication!=null?authentication.getName():null;
        model.addAttribute("userName",authentication!=null?authentication.getName():null);
        ProfileDTO profileDTO=userService.getProfileDTO(name,userName);
        model.addAttribute("profileData", profileDTO);
        model.addAttribute("movies", userService.getWatchedDTO(name,3));
        model.addAttribute("profile", true);
        return "profile-page";
    }


    @GetMapping("/{name}/films")
    public String watched(@PathVariable String name,
                          Model model,
                          Authentication authentication) {
        String userName=authentication!=null?authentication.getName():null;
        model.addAttribute("userName",authentication!=null?authentication.getName():null);
        ProfileDTO profileDTO=userService.getProfileDTO(name,userName);
        model.addAttribute("profileData", profileDTO);
        model.addAttribute("movies", userService.getWatchedDTO(name,Integer.MAX_VALUE));
        model.addAttribute("films", true);

        return "profile-page";
    }
    @GetMapping("/{name}/watchlist")
    public String watchlist(@PathVariable String name,
                            Model model,
                            Authentication authentication) {
        String userName=authentication!=null?authentication.getName():null;
        model.addAttribute("userName",authentication!=null?authentication.getName():null);
        ProfileDTO profileDTO=userService.getProfileDTO(name,userName);
        model.addAttribute("profileData", profileDTO);
        model.addAttribute("movies", userService.getWatchListDTO(name));
        model.addAttribute("watchlist", true);


        return "profile-page";
    }
    @GetMapping("/{name}/favorite")
    public String favorite(@PathVariable String name,
                           Model model,
                           Authentication authentication) {
        String userName=authentication!=null?authentication.getName():null;
        model.addAttribute("userName",authentication!=null?authentication.getName():null);
        ProfileDTO profileDTO=userService.getProfileDTO(name,userName);
        model.addAttribute("profileData", profileDTO);
        model.addAttribute("movies", userService.getFavoriteDTO(name));
        model.addAttribute("favorite", true);

        return "profile-page";
    }
    @GetMapping("/{name}/reviews")
    public String reviews(@PathVariable String name,
                          Model model,
                          Authentication authentication) {
        String userName=authentication!=null?authentication.getName():null;
        model.addAttribute("userName",authentication!=null?authentication.getName():null);
        ProfileDTO profileDTO=userService.getProfileDTO(name,userName);
        model.addAttribute("profileData", profileDTO);
        model.addAttribute("reviews", userService.getUserReviewsDTO(name,userName));
        model.addAttribute("review", true);

        return "profile-page";
    }
    @GetMapping("/{name}/logs")
    public String logs(@PathVariable String name,
                       Model model,
                       Authentication authentication) {
        String userName=authentication!=null?authentication.getName():null;
        model.addAttribute("userName",authentication!=null?authentication.getName():null);
        ProfileDTO profileDTO = userService.getProfileDTO(name, userName);
        model.addAttribute("profileData", profileDTO);
        model.addAttribute("logs", userService.getUserLogsDTO(name));
        model.addAttribute("log", true);

        return "profile-page";
    }

}

package com.scm.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
    
    // user dashboard page

    @RequestMapping(value = "/dashboard")
    public String userDashboard() {
        return "user/dashboard";
    }

    // User profile page
    @RequestMapping(value = "/profile")
    public String userProfile(Model model, Authentication authentication) {
        return "user/profile";
    }

    // user add contact page

    // user view contact page

    // user edit contact page

    // user delete contact page

}

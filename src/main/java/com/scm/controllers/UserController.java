package com.scm.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scm.helper.Helper;

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
    public String userProfile(Authentication authentication) {

        String username = Helper.getEmailOfLoggedInUser(authentication);
        System.out.println("Logged in user email: " + username);
        return "user/profile";
    }

    // user add contact page

    // user view contact page

    // user edit contact page

    // user delete contact page

}

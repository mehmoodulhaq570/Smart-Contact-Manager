package com.scm.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class PageController {

    // This method will handle the request for the home page
    @RequestMapping(value = "/home")
    public String home(Model model) {
        System.out.println("Home page accessed");
        model.addAttribute("name", "SubStrings Technologies"); // Add an attribute to the model
        model.addAttribute("tagline", "We are the best in the world"); // Add another attribute to the model
        model.addAttribute("githublink", "https://github.com/SubStringsTechnologies"); // Add another attribute to the model
        return "home"; // Return the name of the view (home.html)
    }



}

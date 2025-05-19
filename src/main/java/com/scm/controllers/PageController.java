package com.scm.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.scm.entities.User;
import com.scm.forms.UserForm;
import com.scm.services.UserService;


@Controller
public class PageController {

    @Autowired
    private UserService userService;

    // This method will handle the request for the home page
    @RequestMapping(value = "/home")
    public String home(Model model) {
        System.out.println("Home page accessed");
        model.addAttribute("name", "SubStrings Technologies"); // Add an attribute to the model
        model.addAttribute("tagline", "We are the best in the world"); // Add another attribute to the model
        model.addAttribute("githublink", "https://github.com/SubStringsTechnologies"); // Add another attribute to the model
        return "home"; // Return the name of the view (home.html)
    }

    // about 
    @RequestMapping(value = "/about")
    public String about(Model model) {
        System.out.println("About page accessed");
        // model.addAttribute("title", "About Us");
        // model.addAttribute("content", "We are a leading technology company.");
        return "about"; // Return the name of the view (about.html)
    }


    //services
    @RequestMapping(value = "/services")
    public String services(Model model) {
        System.out.println("Services page accessed");
        model.addAttribute("title", "Our Services");
        model.addAttribute("content", "We offer a wide range of services.");
        return "services"; // Return the name of the view (services.html)
    }

        //contact
    @RequestMapping(value = "/contact")
    public String contact(Model model) {
        System.out.println("Contact page accessed");
        model.addAttribute("title", "Contact Us");
        model.addAttribute("content", "We offer a wide range of contacts.");
        return "contact"; // Return the name of the view (contact.html)
    }

    //login
    @RequestMapping(value = "/login")
    public String login(Model model) {
        System.out.println("Login page accessed");
        model.addAttribute("title", "Login");
        model.addAttribute("content", "Please enter your credentials.");
        return "login"; // Return the name of the view (login.html)
    }

    //Signup
    @RequestMapping(value = "/signup")
    public String signup(Model model) {
        UserForm userForm = new UserForm();
        model.addAttribute("userForm", userForm); // Add an attribute to the model
        return "signup"; // Return the name of the view (signup.html)
    }

    // processing register

    @RequestMapping(value = "/do-register", method = RequestMethod.POST)
    public String processRegister(@ModelAttribute UserForm userForm) {
        System.out.println("Processing registration");
        // fetch form data (UserForm)
        System.out.println(userForm);
        // validate the data

        // save the data to the database (UserService)
        //   Get data form user from and save that into user
        User user = User.builder()
        .name(userForm.getName())
        .email(userForm.getEmail())
        .password(userForm.getPassword())
        .phoneNumber(userForm.getPhoneNumber())
        .about(userForm.getAbout())
        .profilePicture("@static/images/profilepic.png")
        .build(); // Create a new User object

        User saveUser = userService.saveUser(user);
        System.out.println("User saved: " + saveUser);

        // message = successful registration
        
        // redirect to the login page
        return "redirect:/signup"; // Return the name of the view (register.html)
    }



}

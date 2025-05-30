package com.scm.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.scm.forms.ContactForm;


@Controller
@RequestMapping("/user/contacts")
public class ContactController {
    
    // Add contact page handler
    
    @RequestMapping("/add")
    public String addContactView(Model model) {
        ContactForm contactForm = new ContactForm();
        model.addAttribute("contactForm", contactForm);
        return "user/add_contact"; // This should return the name of the contact page view
    }

    // Handle form submission
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addContact(@ModelAttribute ContactForm contactForm) {
        // Process the form data
        System.out.println(contactForm);
        return "redirect:/user/contacts/add"; // Redirect to the contacts list page
    }

}

package com.scm.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.scm.entities.Contact;
import com.scm.entities.User;
import com.scm.forms.ContactForm;
import com.scm.helper.Helper;
import com.scm.services.ContactService;
import com.scm.services.UserService;


@Controller
@RequestMapping("/user/contacts")
public class ContactController {
    
    // Add contact page handler

    @Autowired
    private ContactService contactService;

    @Autowired
    private UserService userService;
    
    @RequestMapping("/add")
    public String addContactView(Model model) {
        ContactForm contactForm = new ContactForm();
        model.addAttribute("contactForm", contactForm);
        return "user/add_contact"; // This should return the name of the contact page view
    }

    // Handle form submission
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addContact(@ModelAttribute ContactForm contactForm, Authentication authentication) {

        // Validate the form data (you can add more validation as needed)
        // TODO: Add validation logic here if necessary



        // Process the form data
        String username = Helper.getEmailOfLoggedInUser(authentication);
        User user = userService.getUserByEmail(username);

        // Process the contact picture

        Contact contact = new Contact();
        contact.setName(contactForm.getName());
        contact.setEmail(contactForm.getEmail());
        contact.setPhoneNumber(contactForm.getPhoneNumber());
        contact.setAddress(contactForm.getAddress());
        contact.setFavorite(contactForm.isFavorite());
        contact.setDescription(contactForm.getDescription());
        contact.setWebsiteLink(contactForm.getWebsiteLink());
        contact.setLinkedInLink(contactForm.getLinkedInLink());
        contact.setUser(user);

        // set the contact picture url

        // set the message to display

        contactService.saveContact(contact);
        System.out.println(contactForm);
        return "redirect:/user/contacts/add"; // Redirect to the contacts list page
    }

}

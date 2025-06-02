package com.scm.controllers;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.scm.entities.Contact;
import com.scm.entities.User;
import com.scm.forms.ContactForm;
import com.scm.helper.Helper;
import com.scm.helper.Message;
import com.scm.helper.MessageType;
import com.scm.services.ContactService;
import com.scm.services.UserService;
import com.scm.services.imageService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;


@Controller
@RequestMapping("/user/contacts")
public class ContactController {

    private Logger logger = LoggerFactory.getLogger(ContactController.class);

    @Autowired
    private imageService imageService;
    
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
    public String addContact(@Valid @ModelAttribute ContactForm contactForm, BindingResult result ,Authentication authentication, HttpSession session) {

        // Validate the form data (you can add more validation as needed)
        // TODO: Add validation logic here if necessary

        if (result.hasErrors()) {
            session.setAttribute("message", Message.builder()
            .content("Please fix the errors in the form.")
            .type(MessageType.red)
            .build());
            // If there are validation errors, return to the form view with errors
            return "user/add_contact"; // This should return the name of the contact page view
        }

        // Process the form data
        String username = Helper.getEmailOfLoggedInUser(authentication);
        User user = userService.getUserByEmail(username);

        // Process the contact picture
        logger.info("file information: {}", contactForm.getContactImage().getOriginalFilename());

        // upload the image
        String fileURL=imageService.uploadImage(contactForm.getContactImage());

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
        contact.setPicture(fileURL);
        

        // set the message to display
        session.setAttribute("message", Message.builder()
            .content("You have successfully added a new contact.")
            .type(MessageType.green)
            .build());

        contactService.saveContact(contact);
        System.out.println(contactForm);
        return "redirect:/user/contacts/add"; // Redirect to the contacts list page
    }

    @RequestMapping
    public String viewContacts(Model model, Authentication authentication){

        // Load all the contacts
        String username = Helper.getEmailOfLoggedInUser(authentication);

        User user = userService.getUserByEmail(username);

        List<Contact> contact = contactService.getContactsByUser(user);

        model.addAttribute("contacts", contact);

        return "user/contacts"; // This should return the name of the contacts list view
    }

}

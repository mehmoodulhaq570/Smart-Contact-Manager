package com.scm.services;

import java.util.List;

import com.scm.entities.Contact;
import com.scm.entities.User;

public interface ContactService {

    // save contact
    Contact saveContact(Contact contact);

    // Update contact
    Contact updateContact(Contact contact);

    // Delete contact
    void deleteContact(String id);

    // Get contact by ID
    Contact getContactById(String id);

    // Get contacts
    List<Contact> getAll();

    // Search contacts by name
    List<Contact> search(String name, String id, String phoneNumber);

    // Get contacts by user ID
    List<Contact> getContactsByUserId(String id);

    List<Contact> getContactsByUser(User user);

}

package com.scm.services;

import java.util.List;

import org.springframework.data.domain.Page;

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
    Page<Contact> searchByName(String nameKeyword, int size, int page, String sortBy, String order, User user);
    Page<Contact> searchByEmail(String emailKeyword, int size, int page, String sortBy, String order, User user);
    Page<Contact> searchByPhoneNumber(String phoneNumberKeyword, int size, int page, String sortBy, String order, User user);

    // Get contacts by user ID
    List<Contact> getContactsByUserId(String id);

    Page<Contact> getContactsByUser(User user, int page, int size, String sortBy, String direction);

}

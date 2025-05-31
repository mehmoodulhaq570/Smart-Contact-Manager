package com.scm.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scm.entities.Contact;
import com.scm.repositories.ContactRepo;
import com.scm.services.ContactService;

@Service
public class ContactServiceImpl implements ContactService{

    @Autowired
    private ContactRepo contactRepo;

    @Override
    public Contact saveContact(Contact contact) {
        String contactid = UUID.randomUUID().toString();
        contact.setId(contactid);
        return contactRepo.save(contact);

    }

    @Override
    public Contact updateContact(Contact contact) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateContact'");
    }

    @Override
    public void deleteContact(String id) {
        var contact = contactRepo.findById(id).orElseThrow(() -> new RuntimeException("Contact not found with id: " + id));
        contactRepo.delete(contact);
    }

    @Override
    public Contact getContactById(String id) {
        return contactRepo.findById(id).orElseThrow(() -> new RuntimeException("Contact not found with id: " + id));
    }

    @Override
    public List<Contact> getAll() {
        return contactRepo.findAll();
    }

    @Override
    public List<Contact> search(String name, String id, String phoneNumber) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'search'");
    }

    @Override
    public List<Contact> getContactsByUserId(String id) {
        return contactRepo.findByUserId(id);
    }

}

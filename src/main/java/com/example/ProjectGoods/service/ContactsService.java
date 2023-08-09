package com.example.ProjectGoods.service;

import com.example.ProjectGoods.model.ContactNumber;

import java.util.List;
import java.util.Optional;

public interface ContactsService {
    ContactNumber create(ContactNumber contactNumber);
    List<ContactNumber> listAllContacts();
    void deleteContactsById(Long id);
    ContactNumber assignUserToContacts(Long contactsId, Long userId);
    Optional<ContactNumber> getById(Long id);
}

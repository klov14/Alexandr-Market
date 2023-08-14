package com.example.ProjectGoods.service.impl;

import com.example.ProjectGoods.model.ContactNumber;
import com.example.ProjectGoods.model.User;
import com.example.ProjectGoods.repository.ContactsRepository;
import com.example.ProjectGoods.repository.UserRepository;
import com.example.ProjectGoods.service.ContactsService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ContactsServiceImpl implements ContactsService {
    private ContactsRepository contactsRepository;
    private UserRepository userRepository;

    @Override
    public void create(ContactNumber contactNumber, Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            contactNumber.setUserMapping(user.get());
            contactNumber = contactsRepository.save(contactNumber);
            List<ContactNumber> contacts = user.get().getContactsSet();
            contacts.add(contactNumber);
            userRepository.save(user.get());
        }
    }

    @Override
    public List<ContactNumber> listAllContacts() {
        return contactsRepository.findAll();
    }

    @Override
    public void deleteContactsById(Long id) {
        contactsRepository.deleteById(id);
    }

    @Override
    public Optional<ContactNumber> getById(Long id) {
        return Optional.ofNullable(contactsRepository.findById(id).orElseThrow(() -> new EntityNotFoundException()));
    }
}

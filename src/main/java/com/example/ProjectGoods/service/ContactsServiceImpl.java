package com.example.ProjectGoods.service;

import com.example.ProjectGoods.model.ContactNumber;
import com.example.ProjectGoods.model.User;
import com.example.ProjectGoods.repository.ContactsRepository;
import com.example.ProjectGoods.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class ContactsServiceImpl implements ContactsService{
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
    public ContactNumber assignUserToContacts(Long contactsId, Long userId) {
        Optional<User> user = userRepository.findById(userId);
        Optional<ContactNumber> contacts = contactsRepository.findById(contactsId);
        if (contacts.isPresent() && user.isPresent()) {
            contacts.get().setUserMapping(user.get());
            user.get().getContactsSet().add(contacts.get());
            return contactsRepository.save(contacts.get());
        }
        else {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public Optional<ContactNumber> getById(Long id) {
        return Optional.ofNullable(contactsRepository.findById(id).orElseThrow(() -> new EntityNotFoundException()));
    }
}

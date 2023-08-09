package com.example.ProjectGoods.controller;

import com.example.ProjectGoods.model.ContactNumber;
import com.example.ProjectGoods.service.ContactsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/contacts")
public class ContactsController {
    private ContactsService contactsService;

    @PostMapping
    public ContactNumber addContact(@RequestBody ContactNumber contactNumber){
        return contactsService.create(contactNumber);
    }

    @GetMapping("/{id}")
    public Optional<ContactNumber> getContact(@PathVariable Long id){
        return contactsService.getById(id);
    }

    @GetMapping
    public List<ContactNumber> listAll(){
        return contactsService.listAllContacts();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        contactsService.deleteContactsById(id);
    }

    @PutMapping("/{contactId}/user/{userId}")
    public ContactNumber assignUserToContacts(@PathVariable Long contactId,
                                              @PathVariable Long userId) {
        return contactsService.assignUserToContacts(contactId, userId);
    }
}
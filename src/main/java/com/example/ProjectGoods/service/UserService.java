package com.example.ProjectGoods.service;

import com.example.ProjectGoods.model.ContactNumber;
import com.example.ProjectGoods.model.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> getById(Long id);
    List<User> getUsers();
    List<ContactNumber> getAllContactsOfUser(Long id);
    User assignAddressToUser(Long addressId, Long userId);
}

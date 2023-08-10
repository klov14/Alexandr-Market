package com.example.ProjectGoods.service;

import com.example.ProjectGoods.dto.ContactNumberDto;
import com.example.ProjectGoods.dto.UserDto;
import com.example.ProjectGoods.dto.UserDto2;
import com.example.ProjectGoods.model.ContactNumber;
import com.example.ProjectGoods.model.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
    UserDto getById(Long id);
    List<UserDto> getUsers();
    List<ContactNumberDto> getAllContactsOfUser(Long id);
    User assignAddressToUser(Long addressId, Long userId);

    UserDto2 printUserDtoWithAddress(Long userid);
}

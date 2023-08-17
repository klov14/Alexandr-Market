package com.example.ProjectGoods.service;

import com.example.ProjectGoods.model.dto.ContactNumberDTO;
import com.example.ProjectGoods.model.dto.UserDTO;
import com.example.ProjectGoods.model.DeliveryAddress;
import com.example.ProjectGoods.model.User;
import java.util.List;
import java.util.Set;

public interface UserService {
    UserDTO getById(Long id);
    List<UserDTO> getUsers();
    List<ContactNumberDTO> getAllContactsOfUser(Long id);
    User assignAddressToUser(Long addressId, Long userId);
    List<DeliveryAddress> getAddressByUserId(Long userid);
}

package com.example.ProjectGoods.controller;

import com.example.ProjectGoods.model.dto.ContactNumberDTO;
import com.example.ProjectGoods.model.dto.UserDTO;
import com.example.ProjectGoods.model.DeliveryAddress;
import com.example.ProjectGoods.model.User;
import com.example.ProjectGoods.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    private UserService userService;

    @GetMapping("/{id}")
    public UserDTO getById(@PathVariable Long id){
        return userService.getById(id);
    }

    @GetMapping
    public List<UserDTO> listAll(){
        return userService.getUsers();
    }

    /**
     * GET userDTO with an address by userId
     * @param userId
     * @return
     */
    @GetMapping("/get-address/{userId}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAnyAuthority('SELLER')")
    public List<DeliveryAddress> printUserDtoWithAddress(@PathVariable Long userId){
        return userService.getAddressByUserId(userId);
    }

    /**
     * GET all contactsDTO information by id of the user
     * @param id
     * @return
     */
    @GetMapping("/get-contacts/{id}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAnyAuthority('SELLER')")
    public List<ContactNumberDTO> getAllNumbersFromUsersId(@PathVariable Long id){
        return userService.getAllContactsOfUser(id);
    }

    /**
     * Assign Address of the user by IDs, many to many mapping
     * @param addressId
     * @param userId
     * @return
     */
    @PutMapping("/{userId}/address/{addressId}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAnyAuthority('USER')")
    public User assignAddressToUser(@PathVariable Long addressId,
                                    @PathVariable Long userId) {
        return userService.assignAddressToUser(addressId, userId);
    }

}

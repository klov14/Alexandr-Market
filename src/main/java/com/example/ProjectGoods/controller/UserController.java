package com.example.ProjectGoods.controller;

import com.example.ProjectGoods.dto.ContactNumberDto;
import com.example.ProjectGoods.dto.UserDto;
import com.example.ProjectGoods.dto.UserDto2;
import com.example.ProjectGoods.model.ContactNumber;
import com.example.ProjectGoods.model.User;
import com.example.ProjectGoods.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    private UserService userService;

    @GetMapping("/{id}")
    public UserDto getById(@PathVariable Long id){
        return userService.getById(id);
    }

    @GetMapping
    public List<UserDto> listAll(){
        return userService.getUsers();
    }

    /**
     * GET userDTO with an address by userId
     * @param userId
     * @return
     */
    @GetMapping("/get-address/{userId}")
    public UserDto2 printUserDtoWithAddress(@PathVariable Long userId){
        return userService.printUserDtoWithAddress(userId);
    }

    /**
     * GET all contactsDTO information by id of the user
     * @param id
     * @return
     */
    @GetMapping("/all/{id}")
    public List<ContactNumberDto> getAllNumbersFromUsersId(@PathVariable Long id){
        return userService.getAllContactsOfUser(id);
    }

    /**
     * Assign Address of the user by IDs, many to many mapping
     * @param addressId
     * @param userId
     * @return
     */
    @PutMapping("/{userId}/address/{addressId}")
    public User assignAddressToUser(@PathVariable Long addressId,
                                    @PathVariable Long userId) {
        return userService.assignAddressToUser(addressId, userId);
    }
}

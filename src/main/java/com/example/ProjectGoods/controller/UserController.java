package com.example.ProjectGoods.controller;

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
    public Optional<User> getById(@PathVariable Long id){
        return userService.getById(id);
    }

    @GetMapping
    public List<User> listAll(){
        return userService.getUsers();
    }

    /**
     * GET all contacts information by id of the user
     * @param id
     * @return
     */
    @GetMapping("/all/{id}")
    public List<ContactNumber> getAllNumbers(@PathVariable Long id){
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

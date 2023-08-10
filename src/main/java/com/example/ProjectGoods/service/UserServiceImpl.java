package com.example.ProjectGoods.service;

import com.example.ProjectGoods.dto.ContactNumberDto;
import com.example.ProjectGoods.dto.UserDto;
import com.example.ProjectGoods.dto.UserDto2;
import com.example.ProjectGoods.model.ContactNumber;
import com.example.ProjectGoods.model.DeliveryAddress;
import com.example.ProjectGoods.model.User;
import com.example.ProjectGoods.repository.DeliveryRepository;
import com.example.ProjectGoods.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private DeliveryRepository deliveryRepository;
    @Override
    public List<UserDto> getUsers() {
        return mappingEntityToDtoListUser(userRepository.findAll());
    }

    @Override
    public UserDto getById(Long id) {
        Optional<User> userCheck= userRepository.findById(id);
        if(userCheck.isPresent()){
            User user = userCheck.get();
            return mappingEntityToDtoUser(user);
        }
        else{
            throw new EntityNotFoundException();
        }
    }

    @Override
    public List<ContactNumberDto> getAllContactsOfUser(Long id) {
        Optional<User> userCheck = userRepository.findById(id);
        if(userCheck.isPresent()){
            return mappingEntityToDtoListContacts(userCheck.get().getContactsSet());
        }
        else{
            throw new EntityNotFoundException();
        }
    }
    @Override
    public User assignAddressToUser(Long addressId, Long userId) {
        Optional<DeliveryAddress> address = deliveryRepository.findById(addressId);
        Optional<User> user = userRepository.findById(userId);
        if (address.isPresent() && user.isPresent()) {
            user.get().getAddressAvailable().add(address.get());
            return userRepository.save(user.get());
        }
        else {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public UserDto2 printUserDtoWithAddress(Long userid) {
        Optional<User> userCheck= userRepository.findById(userid);
        if(userCheck.isPresent()){
            UserDto2 userDto2 = new UserDto2();
            User user = userCheck.get();
            userDto2.setId(user.getId());
            userDto2.setFirstName(user.getFirstName());
            userDto2.setLastName(user.getLastName());
            userDto2.setEmail(user.getEmail());
            userDto2.setAddressAvailable(user.getAddressAvailable());
            return userDto2;
        }
        else{
            throw new EntityNotFoundException();
        }
    }

    private ContactNumberDto mappingEntityToDtoForContacts(ContactNumber contactNumber){
        ContactNumberDto printedDto = new ContactNumberDto();
        printedDto.setId(contactNumber.getId());
        printedDto.setTelephone(contactNumber.getTelephone());
        printedDto.setMobile(contactNumber.getMobile());
        return printedDto;
    }
    private List<ContactNumberDto> mappingEntityToDtoListContacts (List<ContactNumber> listOfContacts){
        List<ContactNumberDto> printedDtoContacts = new ArrayList<>();
        for(ContactNumber i : listOfContacts) {
            printedDtoContacts.add(mappingEntityToDtoForContacts(i));
        }
        return printedDtoContacts;
    }

    private UserDto mappingEntityToDtoUser(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        return userDto;
    }
    private List<UserDto> mappingEntityToDtoListUser (List<User> listOfUsers){
        List<UserDto> printedDtoUsers = new ArrayList<>();
        for(User i : listOfUsers) {
            printedDtoUsers.add(mappingEntityToDtoUser(i));
        }
        return printedDtoUsers;
    }
}

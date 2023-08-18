package com.example.ProjectGoods.service.impl;

import com.example.ProjectGoods.model.dto.ContactNumberDTO;
import com.example.ProjectGoods.model.dto.UserDTO;
import com.example.ProjectGoods.model.ContactNumber;
import com.example.ProjectGoods.model.DeliveryAddress;
import com.example.ProjectGoods.model.User;
import com.example.ProjectGoods.repository.DeliveryRepository;
import com.example.ProjectGoods.repository.UserRepository;
import com.example.ProjectGoods.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private DeliveryRepository deliveryRepository;
    @Override
    public List<UserDTO> getUsers() {
        return mappingEntityToDtoListUser(userRepository.findAll());
    }

    @Override
    public UserDTO getById(Long id) {
        Optional<User> userCheck= userRepository.findById(id);
        if(userCheck.isPresent()){
            User user = userCheck.get();
            return mappingEntityToDtoUser(user);
        }
        else {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public List<ContactNumberDTO> getAllContactsOfUser(Long id) {
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
            int count = 0;
            for(int i=0; i < user.get().getAddressAvailable().size(); i++) {
                user.get().getAddressAvailable().get(i).setActive(false);
                count = i;
            }
            user.get().getAddressAvailable().add(address.get());
            user.get().getAddressAvailable().get(count+1).setActive(true);
            return userRepository.save(user.get());
        }
        else {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public List<DeliveryAddress> getAddressByUserId(Long userId) {
        Optional<User> userCheck= userRepository.findById(userId);
        if(userCheck.isPresent()){
            return userCheck.get().getAddressAvailable();
        }
        else{
            throw new EntityNotFoundException();
        }
    }

    private ContactNumberDTO mappingEntityToDtoForContacts(ContactNumber contactNumber){
        ContactNumberDTO printedDto = new ContactNumberDTO();
        printedDto.setId(contactNumber.getId());
        printedDto.setTelephone(contactNumber.getTelephone());
        printedDto.setMobile(contactNumber.getMobile());
        return printedDto;
    }
    private List<ContactNumberDTO> mappingEntityToDtoListContacts (List<ContactNumber> listOfContacts){
        List<ContactNumberDTO> printedDtoContacts = new ArrayList<>();
        for(ContactNumber i : listOfContacts) {
            printedDtoContacts.add(mappingEntityToDtoForContacts(i));
        }
        return printedDtoContacts;
    }

    private UserDTO mappingEntityToDtoUser(User user){
        UserDTO userDto = new UserDTO();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        return userDto;
    }

    private List<UserDTO> mappingEntityToDtoListUser (List<User> listOfUsers){
        List<UserDTO> printedDtoUsers = new ArrayList<>();
        for(User i : listOfUsers) {
            printedDtoUsers.add(mappingEntityToDtoUser(i));
        }
        return printedDtoUsers;
    }

}

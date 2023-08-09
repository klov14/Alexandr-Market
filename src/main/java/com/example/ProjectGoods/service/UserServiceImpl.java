package com.example.ProjectGoods.service;

import com.example.ProjectGoods.dto.ContactNumberDto;
import com.example.ProjectGoods.model.ContactNumber;
import com.example.ProjectGoods.model.DeliveryAddress;
import com.example.ProjectGoods.model.User;
import com.example.ProjectGoods.repository.ContactsRepository;
import com.example.ProjectGoods.repository.DeliveryRepository;
import com.example.ProjectGoods.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private DeliveryRepository deliveryRepository;
    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getById(Long id) {
        return Optional.ofNullable(userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException()));
    }

    @Override
    public List<ContactNumberDto> getAllContactsOfUser(Long id) {
        Optional<User> userCheck = userRepository.findById(id);
        if(userCheck.isPresent()){
//            return userCheck.get().getContactsSet();
            //todo map to dto list
            return null;
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
}

package com.example.ProjectGoods.service;

import com.example.ProjectGoods.model.DeliveryAddress;
import com.example.ProjectGoods.model.User;
import com.example.ProjectGoods.repository.DeliveryRepository;
import com.example.ProjectGoods.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DeliveryServiceImpl implements DeliveryService{
    private DeliveryRepository deliveryRepository;
    private UserRepository userRepository;

    @Override
    public DeliveryAddress create(DeliveryAddress deliveryAddress) {
        return deliveryRepository.save(deliveryAddress);
    }

    @Override
    public List<DeliveryAddress> listAllAddress() {
        return deliveryRepository.findAll();
    }

    @Override
    public void deleteAddressById(Long id) {
        deliveryRepository.deleteById(id);
    }

    @Override
    public Optional<DeliveryAddress> getById(Long id) {
        return Optional.ofNullable(deliveryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException()));
    }

}

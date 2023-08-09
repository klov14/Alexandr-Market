package com.example.ProjectGoods.service;

import com.example.ProjectGoods.model.DeliveryAddress;
import com.example.ProjectGoods.model.User;

import java.util.List;
import java.util.Optional;

public interface DeliveryService {
    DeliveryAddress create(DeliveryAddress deliveryAddress);
    List<DeliveryAddress> listAllAddress();
    void deleteAddressById(Long id);
    Optional<DeliveryAddress> getById(Long id);

}

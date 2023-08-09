package com.example.ProjectGoods.controller;

import com.example.ProjectGoods.model.DeliveryAddress;
import com.example.ProjectGoods.service.DeliveryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/address")
public class DeliveryController {
    private DeliveryService deliveryService;

    @PostMapping
    public DeliveryAddress createAddress(@RequestBody DeliveryAddress deliveryAddress){
        return deliveryService.create(deliveryAddress);
    }

    @GetMapping("/{id}")
    public Optional<DeliveryAddress> getById(@PathVariable Long id){
        return deliveryService.getById(id);
    }

    @GetMapping
    public List<DeliveryAddress> listAllAddress(){
        return deliveryService.listAllAddress();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        deliveryService.deleteAddressById(id);
    }
}

package com.example.ProjectGoods.controller;

import com.example.ProjectGoods.model.Basket;
import com.example.ProjectGoods.model.Order;
import com.example.ProjectGoods.model.dto.OrderDTO;
import com.example.ProjectGoods.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public Order createOrder(@RequestBody Order order){
        return orderService.createOrder(order);
    }

    @PostMapping("{orderId}/quantity/{quantity}/add-good/{goodId}")
    public OrderDTO addGoodToOrder(@PathVariable Long orderId, @PathVariable double quantity, @PathVariable Long goodId){
        return orderService.addGoodToOrder(orderId, quantity, goodId);}

    @GetMapping("/{orderId}")
    public OrderDTO getOrderById(@PathVariable Long orderId) {
        return orderService.getOrderById(orderId);
    }

    @GetMapping
    public List<OrderDTO> listAllOrders(){
     return orderService.listOrders();
    }

    @PutMapping("/{orderId}/address-for-delivery/{addressId}")
    public OrderDTO assignAddressForDelivery(@PathVariable Long orderId, @PathVariable Long addressId){
     return orderService.assignAddressForDelivery(orderId, addressId);
    }

    @DeleteMapping("/{orderId}")
    public void deleteById(@PathVariable Long orderId){
        orderService.deleteOrderById(orderId);
    }
}

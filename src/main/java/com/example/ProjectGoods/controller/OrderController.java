package com.example.ProjectGoods.controller;

import com.example.ProjectGoods.model.Basket;
import com.example.ProjectGoods.model.Order;
import com.example.ProjectGoods.model.dto.OrderDTO;
import com.example.ProjectGoods.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    @PreAuthorize("hasAuthority('USER')")
    public Order createOrder(@RequestBody Order order){
        return orderService.createOrder(order);
    }

    @PostMapping("{orderId}/quantity/{quantity}/add-good/{goodId}")
    @PreAuthorize("hasAuthority('USER')")
    public Order addGoodToOrder(@PathVariable Long orderId, @PathVariable double quantity, @PathVariable Long goodId){
        return orderService.addGoodToOrder(orderId, quantity, goodId);}

    @GetMapping("/{orderId}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAnyAuthority('USER')")
    public OrderDTO getOrderById(@PathVariable Long orderId) {
        return orderService.getOrderById(orderId);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN') or hasAnyAuthority('USER')")
    public List<OrderDTO> listAllOrders(){
     return orderService.listOrders();
    }

    @PutMapping("/{orderId}/address-for-delivery/{addressId}")
    @PreAuthorize("hasAuthority('USER')")
    public OrderDTO assignAddressForDelivery(@PathVariable Long orderId, @PathVariable Long addressId){
     return orderService.assignAddressForDelivery(orderId, addressId);
    }

    @DeleteMapping("/{orderId}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAnyAuthority('USER')")
    public void deleteById(@PathVariable Long orderId){
        orderService.deleteOrderById(orderId);
    }
}

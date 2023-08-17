package com.example.ProjectGoods.service;

import com.example.ProjectGoods.model.Basket;
import com.example.ProjectGoods.model.Order;
import com.example.ProjectGoods.model.dto.OrderDTO;

import java.util.List;

public interface OrderService {
    Order createOrder(Order order);
    List<OrderDTO> listOrders();
    void deleteOrderById(Long orderId);
    Order addGoodToOrder(Long orderId, double quantity, Long goodId);
    OrderDTO getOrderById(Long orderId);
    OrderDTO assignAddressForDelivery(Long orderId, Long addressId);
}

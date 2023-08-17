package com.example.ProjectGoods.service.impl;
import com.example.ProjectGoods.authenticationController.AuthController;
import com.example.ProjectGoods.model.*;
import com.example.ProjectGoods.model.dto.GoodsForDelivery;
import com.example.ProjectGoods.model.dto.OrderDTO;
import com.example.ProjectGoods.repository.*;
import com.example.ProjectGoods.service.OrderService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;
    private GoodRepository goodRepository;
    private AuthController authController;
    private DeliveryRepository deliveryRepository;
    private UserRepository userRepository;
    private BasketRepository basketRepository;
    @Override
    public Order createOrder(Order order) {
        order.setActiveUser(authController.getUserName());
        return orderRepository.save(order);
    }

    @Override
    public Order addGoodToOrder (Long orderId, double quantity, Long goodId) {
        Optional<Order> orderCheck = orderRepository.findById(orderId);
        Optional<Good> goodCheck = goodRepository.findById(goodId);
        if (orderCheck.isPresent() && goodCheck.isPresent()) {
            Basket newBasket = new Basket();
            newBasket.setGood(goodCheck.get());
            newBasket.setOrder(orderCheck.get());
            newBasket.setQuantity(quantity);
            orderCheck.get().getBasket().add(newBasket);
             basketRepository.save(newBasket);
            mappingEntityToDtoOrder(orderCheck.get()).getTotalPrice();
            return orderRepository.save(orderCheck.get());
        }
        else {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public OrderDTO getOrderById(Long orderId) {
        Optional<Order> orderCheck = orderRepository.findById(orderId);
        if (orderCheck.isPresent()) {
            return mappingEntityToDtoOrder(orderCheck.get());
        }
        else {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public List<OrderDTO> listOrders() {
        return mappingEntityToDtoListOrder(orderRepository.findAll());
    }

    @Override
    public OrderDTO assignAddressForDelivery(Long orderId, Long addressId) {
        Optional<Order> orderCheck = orderRepository.findById(orderId);
        Optional<DeliveryAddress> addressCheck = deliveryRepository.findById(addressId);
        Optional<User> user = userRepository.findByEmail(orderCheck.get().getActiveUser());
        if(orderCheck.isPresent() && addressCheck.isPresent() && user.isPresent()){
                for(int i = 0; i < user.get().getAddressAvailable().size(); i++) {
                    if(user.get().getAddressAvailable().get(i) == addressCheck.get()){
                        orderCheck.get().setAddressMapping(addressCheck.get());
                        return mappingEntityToDtoOrder(orderRepository.save(orderCheck.get()));
                    }
                }
                return null;
        }
        else {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public void deleteOrderById(Long orderId) {
        orderRepository.deleteById(orderId);
    }
//    private Order mappingDtoTOEntity(OrderDTO orderDTO){
//        Order order = new Order();
//        order.setTotalPrice(orderDTO.getTotalPrice());
//        order.setBasket();
//    }

    private OrderDTO mappingEntityToDtoOrder(Order order){
        order.setTotalPrice(calculateTotalPrice(order));
        orderRepository.save(order);
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setAddressMapping(order.getAddressMapping());
        orderDTO.setGoodsInBasket(fromBasketToGoodsForDeliveryList(order.getBasket()));
        orderDTO.setTotalPrice(order.getTotalPrice());

        return orderDTO;
    }

    private List<GoodsForDelivery> fromBasketToGoodsForDeliveryList(List<Basket> basketList) {
        List<GoodsForDelivery> goodsForDeliveryList = new ArrayList<>();
        int i = 0;
        while(i < basketList.size()){
            goodsForDeliveryList.add(fromBasketToGoodsForDelivery(basketList.get(i)));
            i++;
        }
        return goodsForDeliveryList;
    }

    private List<OrderDTO> mappingEntityToDtoListOrder(List<Order> order){
        List<OrderDTO> printedList = new ArrayList<>();
        for(Order i : order) {
            printedList.add(mappingEntityToDtoOrder(i));
        }
        return printedList;
    }

    private GoodsForDelivery fromBasketToGoodsForDelivery(Basket basket){
        GoodsForDelivery goodsNew = new GoodsForDelivery();
        goodsNew.setResell(basket.getGood().getResell());
        goodsNew.setProduct(basket.getGood().getProduct());
        goodsNew.setQuantity(basket.getQuantity());
        goodsNew.setPriceForGoods(goodsNew.getResell()*goodsNew.getQuantity());
        return goodsNew;
    }

    private double calculateTotalPrice(Order order){
        double total = 0;
        for(int i=0; i<order.getBasket().size(); i++){
            total += order.getBasket().get(i).getGood().getResell() * order.getBasket().get(i).getQuantity();
        }
        return total;
    }
}
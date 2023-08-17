package com.example.ProjectGoods.model.dto;

import com.example.ProjectGoods.model.Basket;
import com.example.ProjectGoods.model.DeliveryAddress;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private Long id;
    private List<GoodsForDelivery> goodsInBasket;
    private double totalPrice;
    private DeliveryAddress addressMapping;
}
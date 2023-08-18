package com.example.ProjectGoods.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GoodsForDelivery {
    private String product;
    private double resell;
    private double quantity;
    private double priceForGoods;
}
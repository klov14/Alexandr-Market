package com.example.ProjectGoods.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GoodsDTO {
    private Long id;
    private String product;
    private double buying;
    private double resell;
    private double weight;
}
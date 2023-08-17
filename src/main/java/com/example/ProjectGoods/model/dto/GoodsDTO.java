package com.example.ProjectGoods.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GoodsDTO {
    private Long id;
    private String product;
    private double buying;
    private double resell;
    private double weight;
}
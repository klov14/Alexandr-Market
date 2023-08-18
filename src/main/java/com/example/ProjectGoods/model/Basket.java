package com.example.ProjectGoods.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "basket")
/**
 intermidiate table, where data is added to the order table
 **/
public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    Order order;

    @ManyToOne
    @JoinColumn(name = "good_id")
    Good good;

    @Column(name = "quantity")
    private double quantity;
}

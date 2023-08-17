package com.example.ProjectGoods.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="total_price")
    private double totalPrice;

    @Column(name = "active_user")
    private String activeUser;

    @ManyToOne
    @JoinColumn(name="address_id", referencedColumnName = "id")
    private DeliveryAddress addressMapping;

    @OneToMany(mappedBy = "order")
    @JsonIgnore
    private List<Basket> basket;

}

package com.example.ProjectGoods.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Optional;

@Data
@Entity
@Table(name = "goods1")
public class Good {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="country_id", referencedColumnName = "id")
    private Country country;

    @Column(name="product")
    private String product;

    @Column(name="price")
    private double price;

    @Column(name="weight")
    private double weight;


    public Long getId() {
        return id;
    }

    public Country getCountry() {
        return country;
    }

    public String getProduct() {
        return product;
    }

    public double getPrice() {
        return price;
    }

    public double getWeight() {
        return weight;
    }

    public void assignCountry(Country country){
        this.country = country;
    }
}

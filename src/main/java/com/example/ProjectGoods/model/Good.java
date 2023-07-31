package com.example.ProjectGoods.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "goods1")
public class Good {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="product")
    private String product;

    @Column(name="buying_price")
    private double buying;

    @Column(name="resell_price")
    private double resell;

    @Column(name="weight")
    private double weight;


    @ManyToOne
    @JoinColumn(name="country_id", referencedColumnName = "id")
    private Country country;

    @ManyToOne
    @JoinColumn(name="category_id", referencedColumnName = "id")
    private Category category;
}

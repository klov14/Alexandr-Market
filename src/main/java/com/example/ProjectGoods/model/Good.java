package com.example.ProjectGoods.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "goods1")
public class Good{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="product")
    private String product;

    @Column(name="buying_price")
    private double buying;

    @Column(name="selling_price")
    private double resell;

    @Column(name="weight")
    private double weight;

    @Column(name = "created_user")
    private String createdUser;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "updated_user")
    private String updatedUser;

    @Column(name = "updated_date")
    private Date updatedDate;

    @ManyToOne
    @JoinColumn(name="country_id", referencedColumnName = "id")
    private Country countryMapping;

    @ManyToOne
    @JoinColumn(name="category_id", referencedColumnName = "id")
    private Category categoryMapping;
}
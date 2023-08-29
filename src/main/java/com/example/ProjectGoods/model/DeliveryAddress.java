package com.example.ProjectGoods.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "address")
public class DeliveryAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="house_number")
    private String houseNumber;

    @Column(name="street_name")
    private String streetName;

    @Column(name="postcode")
    private String postcode;

    @Column(name="city")
    private String city;

    @Column(name="active")
    private boolean active;

    @OneToMany(mappedBy = "addressMapping")
    @JsonIgnore
    private List<Order> orderList;

    @JsonIgnore
    @ManyToMany(mappedBy = "addressAvailable")
    private Set<User> users;
}

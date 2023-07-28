package com.example.ProjectGoods.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;


import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name="category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "countries_available",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "country_id"))
    private Set<Country> countriesAvailable;

    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private Set<Good> goods = new HashSet<>();

    @Column(name = "name_category")
    private String name_category;

    public void assignCountryAndCategory(Country country) {
        countriesAvailable.add(country);
    }
}

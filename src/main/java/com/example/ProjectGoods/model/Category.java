package com.example.ProjectGoods.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name="category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_category")
    private String nameCategory;


    @ManyToMany
    @JoinTable(
            name = "countries_available",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "country_id"))
    private Set<Country> countriesAvailable;

    @OneToMany(mappedBy = "categoryMapping")
    @JsonIgnore
    private Set<Good> goods = new HashSet<>();

    public void assignCountryAndCategory(Country country) {
        countriesAvailable.add(country);
    }
}

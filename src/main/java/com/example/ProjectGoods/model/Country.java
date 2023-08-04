package com.example.ProjectGoods.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name="countries")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToMany(mappedBy = "countriesAvailable")
    private Set<Category> categories;

    @OneToMany(mappedBy = "country")
    private Set<Good> goods = new HashSet<>();

    @Column(name="code")
    private String code;

    @Column(name="nameof")
    private String nameOf;
}

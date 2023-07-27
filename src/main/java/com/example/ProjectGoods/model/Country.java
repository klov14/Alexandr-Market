package com.example.ProjectGoods.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name="countries")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(mappedBy = "countriesAvailable")
    private Set<Category> categories;

    @JsonIgnore
    @OneToMany(mappedBy = "country")
    private Set<Good> goods = new HashSet<>();

    @Column(name="code")
    private String code;

    @Column(name="nameof")
    private String nameof;
}

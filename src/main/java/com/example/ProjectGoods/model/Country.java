package com.example.ProjectGoods.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;


@Data
@Entity
@Table(name="Countries")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @OneToMany(mappedBy = "country")
    private Set<Good> goods = new HashSet<>();

    @Column(name="code")
    private String code;

    @Column(name="nameof")
    private String nameof;

    public void addGood(Good goodNew) {
        goods.add(goodNew);
    }

    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getNameOf() {
        return nameof;
    }
}

package com.example.ProjectGoods.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "contacts")
public class ContactNumber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="mobile")
    private String mobile;

    @Column(name="telephone")
    private String telephone;

    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName = "id")
    private  User userMapping;
}

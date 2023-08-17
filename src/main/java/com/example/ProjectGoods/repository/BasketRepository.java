package com.example.ProjectGoods.repository;

import com.example.ProjectGoods.model.Basket;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface BasketRepository extends JpaRepository<Basket, Long> {

}

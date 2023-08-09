package com.example.ProjectGoods.repository;

import com.example.ProjectGoods.model.DeliveryAddress;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface DeliveryRepository extends JpaRepository<DeliveryAddress, Long> {

}

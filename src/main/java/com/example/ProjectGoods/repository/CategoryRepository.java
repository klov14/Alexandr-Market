package com.example.ProjectGoods.repository;

import com.example.ProjectGoods.model.Category;
import com.example.ProjectGoods.model.Country;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface CategoryRepository extends JpaRepository<Category, Long> {
}

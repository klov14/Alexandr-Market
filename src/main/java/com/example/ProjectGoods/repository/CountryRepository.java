package com.example.ProjectGoods.repository;

import com.example.ProjectGoods.model.Category;
import com.example.ProjectGoods.model.Country;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
@Transactional
public interface CountryRepository extends JpaRepository<Country, Long> {
    Set<Country> findCountryByCategories(Category category);

}

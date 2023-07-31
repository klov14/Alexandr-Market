package com.example.ProjectGoods.service;

import com.example.ProjectGoods.model.Category;
import com.example.ProjectGoods.model.Country;
import com.example.ProjectGoods.repository.CategoryRepository;
import com.example.ProjectGoods.repository.CountryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CountryRepository countryRepository;
    @Override
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Optional<Category> getCategoryById(Long id) {
        Optional<Category> fd = categoryRepository.findById(id);
        return Optional.ofNullable(categoryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException()));
    }

    @Override
    public List<Category> listAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public void deleteCategoryById(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Category updateCategory(Long categoryId, Long countryId) {
        Optional<Category> category = categoryRepository.findById(categoryId);
        Optional<Country> country = countryRepository.findById(countryId);
        if (country.isPresent() && category.isPresent()) {
            Country countryAssign = country.get();
            Category category1 = category.get();
            category1.assignCountryAndCategory(countryAssign);
            return categoryRepository.save(category1);
        }
        else {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public Set<Country> printAllByCategory(Long categoryId) {
        Optional<Category> categoryNew = categoryRepository.findById(categoryId);
        if (categoryNew.isPresent()) {
            Category category = categoryNew.get();
            Set<Country> allCountry = new HashSet<>(category.getCountriesAvailable());
            return allCountry;
        }
        else {
            throw new EntityNotFoundException();
      }
    }
}
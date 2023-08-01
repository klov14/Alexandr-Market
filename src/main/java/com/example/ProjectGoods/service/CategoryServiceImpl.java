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
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(EntityNotFoundException::new);
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
            category.get().assignCountryAndCategory(country.get());
            return categoryRepository.save(category.get());
        }
        else {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public Set<Country> printAllByCategory(Long categoryId) {
        Optional<Category> categoryNew = categoryRepository.findById(categoryId);
        if (categoryNew.isPresent()) {
           return categoryNew.get().getCountriesAvailable();
        }
        else {
            throw new EntityNotFoundException();
      }
    }
}
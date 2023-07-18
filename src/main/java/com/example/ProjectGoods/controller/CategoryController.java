package com.example.ProjectGoods.controller;

import com.example.ProjectGoods.model.Category;
import com.example.ProjectGoods.model.Country;
import com.example.ProjectGoods.repository.CategoryRepository;
import com.example.ProjectGoods.repository.CountryRepository;
import com.example.ProjectGoods.service.CategoryService;
import com.example.ProjectGoods.service.CountryService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CountryService countryService;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CountryRepository countryRepository;

    @PostMapping
    public Category create(@RequestBody Category category){
        return categoryService.createCategory(category);
    }

    @GetMapping
    public List<Category> findAllCategory() {
        return categoryService.listAllCategory();
    }

    @GetMapping("/{id}")
    public Optional<Category> findCategoryById(@PathVariable Long id){
        return Optional.ofNullable(categoryService.getCategoryById(id).orElseThrow(() -> new EntityNotFoundException()));
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id){
        categoryService.deleteCategoryById(id);
    }

    @PutMapping("/{categoryId}/country/{countryId}")//set good to the country
    Category assignCategoryToCountry(@PathVariable Long categoryId, @PathVariable Long countryId) {
        Optional<Category> category = categoryService.getCategoryById(categoryId);
        Optional<Country> country = countryService.getCountryById(countryId);
        Country countryAssign;
        Category category1;
        if (country.isPresent() && category.isPresent()) {
            countryAssign = country.get();
            category1 = category.get();
        }
        else {
            throw new EntityNotFoundException();
        }
       category1.assignCountryAndCategory(countryAssign);
        return categoryService.createCategory(category1);
    }
}

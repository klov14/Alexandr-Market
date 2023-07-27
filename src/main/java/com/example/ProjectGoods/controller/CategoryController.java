package com.example.ProjectGoods.controller;

import com.example.ProjectGoods.model.Category;
import com.example.ProjectGoods.model.Country;
import com.example.ProjectGoods.repository.CategoryRepository;
import com.example.ProjectGoods.service.CategoryService;
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
        return  categoryService.getCategoryById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id){
        categoryService.deleteCategoryById(id);
    }

    @PutMapping("/{categoryId}/country/{countryId}")//set category to the country
    Category assignCategoryToCountry(@PathVariable Long categoryId, @PathVariable Long countryId) {
       return categoryService.updateCategory(categoryId, countryId);
    }
    @GetMapping("/all/{categoryId}")
    Set<Country> printAllByCategory(@PathVariable Long categoryId) {
        return categoryService.printAllByCategory(categoryId);
    }
    @GetMapping("/test/{categoryId}")
    Set<Country> test(@PathVariable Long categoryId){
        return categoryService.getCategoryById(categoryId).get().getCountriesAvailable();
    }
}

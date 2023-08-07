package com.example.ProjectGoods.controller;

import com.example.ProjectGoods.model.Category;
import com.example.ProjectGoods.model.CategoryDto;
import com.example.ProjectGoods.model.Country;
import com.example.ProjectGoods.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@RestController
@RequestMapping("/category")
public class CategoryController {
    private CategoryService categoryService;

    @PostMapping
    public Category create(@RequestBody CategoryDto categoryDto){
        return categoryService.dtoTo(categoryDto);
    }

    @GetMapping
    public List<Category> findAllCategory() {
        return categoryService.listAllCategory();
    }

    @GetMapping("/{id}")
    public CategoryDto findCategoryById(@PathVariable Long id){
        return  categoryService.categoryToDto(id);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id){
        categoryService.deleteCategoryById(id);
    }

    /**
     * set category to the country
     * @param categoryId
     * @param countryId
     */
    @PutMapping("/{categoryId}/country/{countryId}")
    Category assignCategoryToCountry(@PathVariable Long categoryId, @PathVariable Long countryId) {
       return categoryService.updateCategory(categoryId, countryId);
    }

    @GetMapping("/all/{categoryId}")
    Set<Country> printAllByCategory(@PathVariable Long categoryId) {
        return categoryService.printAllByCategory(categoryId);
    }
}

package com.example.ProjectGoods.controller;

import com.example.ProjectGoods.model.Category;
import com.example.ProjectGoods.dto.CategoryDto;
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
        return categoryService.create(categoryDto);
    }
    @PostMapping("/update")
    public Category update(@RequestBody CategoryDto categoryNew){
        return categoryService.update(categoryNew);
    }
    @GetMapping
    public List<CategoryDto> findAllCategory() {
        return categoryService.listAllCategory();
    }

    @GetMapping("/{id}")
    public CategoryDto findCategoryById(@PathVariable Long id){
        return  categoryService.getCategoryById(id);
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
       return categoryService.assignCategoryToCountry(categoryId, countryId);
    }

    /**
     * GET all countries that are assigned to the Category
     * @param categoryId
     * @return
     */
    @GetMapping("/all/{categoryId}")
    Set<Country> getAllCountriesByCategoryId(@PathVariable Long categoryId) {
        return categoryService.getAllCountriesByCategoryId(categoryId);
    }
}

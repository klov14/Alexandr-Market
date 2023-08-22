package com.example.ProjectGoods.controller;

import com.example.ProjectGoods.model.Category;
import com.example.ProjectGoods.model.User;
import com.example.ProjectGoods.model.dto.CategoryDTO;
import com.example.ProjectGoods.model.Country;
import com.example.ProjectGoods.service.CategoryService;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Set;


@AllArgsConstructor
@RestController
@RequestMapping("/category")

public class CategoryController {
    private CategoryService categoryService;
    @PostMapping
    @PreAuthorize("hasAnyAuthority('SELLER')")
    public Category create(@RequestBody CategoryDTO categoryDto){
        return categoryService.create(categoryDto);
    }
    @PostMapping("/update")
    @PreAuthorize("hasAuthority('ADMIN') or hasAnyAuthority('SELLER')")
    public Category update(@RequestBody CategoryDTO categoryNew){
        return categoryService.update(categoryNew);
    }
    @GetMapping
    public List<CategoryDTO> findAllCategory() {
        return categoryService.listAllCategory();
    }

    @GetMapping("/{id}")
    public CategoryDTO findCategoryById(@PathVariable Long id){
        return  categoryService.getCategoryById(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAnyAuthority('SELLER')")
    public void deleteCategory(@PathVariable Long id){
        categoryService.deleteCategoryById(id);
    }

    /**
     * set category to the country
     * @param categoryId
     * @param countryId
     */
    @PutMapping("/{categoryId}/country/{countryId}")
    @PreAuthorize("hasAuthority('ADMIN')")
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

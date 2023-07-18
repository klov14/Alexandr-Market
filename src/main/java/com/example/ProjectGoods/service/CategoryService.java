package com.example.ProjectGoods.service;

import com.example.ProjectGoods.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    Category createCategory(Category category);
    Optional<Category> getCategoryById(Long id);
    List<Category> listAllCategory();
    void deleteCategoryById(Long id);
    Category updateCategory(Long categoryId, Long countryId);
}

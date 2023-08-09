package com.example.ProjectGoods.service;

import com.example.ProjectGoods.dto.CategoryDto;
import com.example.ProjectGoods.model.*;

import java.util.List;
import java.util.Set;

public interface CategoryService {
    List<CategoryDto> listAllCategory();
    void deleteCategoryById(Long id);
    Category assignCategoryToCountry(Long categoryId, Long countryId);
    Set<Country> getAllCountriesByCategoryId(Long categoryId);
    CategoryDto getCategoryById(Long categoryId);
    Category create(CategoryDto categoryDto);
    Category update(CategoryDto categoryDto);
}

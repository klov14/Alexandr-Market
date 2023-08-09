package com.example.ProjectGoods.service;

import com.example.ProjectGoods.dto.CategoryDto;
import com.example.ProjectGoods.model.*;

import java.util.List;
import java.util.Set;

public interface CategoryService {
    List<Category> listAllCategory();
    void deleteCategoryById(Long id);
    Category assignCategoryToCountry(Long categoryId, Long countryId);
    Set<Country> getAllCountriesByCategoryId(Long categoryId);
    CategoryDto categoryToDto(Long categoryId);
    Category dtoTo(CategoryDto categoryDto);
}

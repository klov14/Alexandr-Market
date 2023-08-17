package com.example.ProjectGoods.service;

import com.example.ProjectGoods.model.dto.CategoryDTO;
import com.example.ProjectGoods.model.*;

import java.util.List;
import java.util.Set;

public interface CategoryService {
    List<CategoryDTO> listAllCategory();
    void deleteCategoryById(Long id);
    Category assignCategoryToCountry(Long categoryId, Long countryId);
    Set<Country> getAllCountriesByCategoryId(Long categoryId);
    CategoryDTO getCategoryById(Long categoryId);
    Category create(CategoryDTO categoryDto);
    Category update(CategoryDTO categoryDto);
}

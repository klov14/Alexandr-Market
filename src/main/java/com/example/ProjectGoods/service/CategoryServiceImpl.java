package com.example.ProjectGoods.service;

import com.example.ProjectGoods.authenticationController.AuthController;
import com.example.ProjectGoods.dto.CategoryDto;
import com.example.ProjectGoods.model.*;
import com.example.ProjectGoods.repository.CategoryRepository;
import com.example.ProjectGoods.repository.CountryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
@AllArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;
    private CountryRepository countryRepository;
    private AuthController authController;

    @Override
    public List<CategoryDto> listAllCategory() {
        return mappingEntityToDtoList(categoryRepository.findAll());
    }

    @Override
    public void deleteCategoryById(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Category assignCategoryToCountry(Long categoryId, Long countryId) {
        Optional<Category> category = categoryRepository.findById(categoryId);
        Optional<Country> country = countryRepository.findById(countryId);
        if (country.isPresent() && category.isPresent()) {
            category.get().getCountriesAvailable().add(country.get());
            updatingDateAndUser(category.get());
            return categoryRepository.save(category.get());
        }
        else {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public Set<Country> getAllCountriesByCategoryId(Long categoryId) {
        Optional<Category> categoryNew = categoryRepository.findById(categoryId);
        if (categoryNew.isPresent()) {
           return categoryNew.get().getCountriesAvailable();
        }
        else {
            throw new EntityNotFoundException();
      }
    }

    @Override
    public CategoryDto getCategoryById(Long categoryId) {
        Optional<Category> categoryCheck = categoryRepository.findById(categoryId);
        if(categoryCheck.isPresent()){
            Category newCategory = categoryCheck.get();
            return mappingEntityToDto(newCategory);
        }
        else{
            throw new EntityNotFoundException();
        }
    }

    @Override
    public Category create(CategoryDto categoryDto) {
        Category category = mappingDtoToEntity(categoryDto);
        category.setCreatedUser(authController.getUserName());
        category.setCreatedDate(new Date(System.currentTimeMillis()));
        return categoryRepository.save(category);
    }

    @Override
    public Category update(CategoryDto categoryDto) {
        Category category = mappingDtoToEntity(categoryDto);
        Optional<Category> oldCategory = categoryRepository.findById(category.getId());
        if (oldCategory.isPresent()) {
            category.setCreatedUser(oldCategory.get().getCreatedUser());
            category.setCreatedDate(oldCategory.get().getCreatedDate());
            updatingDateAndUser(category);
            return categoryRepository.save(category);
        }
        else {
            throw new EntityNotFoundException();
        }
    }

    private CategoryDto mappingEntityToDto(Category category){
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setNameCategory(category.getNameCategory());
        return categoryDto;
    }
    private Category mappingDtoToEntity(CategoryDto categoryDto){
        Category categoryNew = new Category();
        categoryNew.setId(categoryDto.getId());
        categoryNew.setNameCategory(categoryDto.getNameCategory());
        return categoryNew;
    }
    private List<CategoryDto> mappingEntityToDtoList(List<Category> category){
        List<CategoryDto> printedList = new ArrayList<>();
        int i = 0;
        while(i < category.size()){
            printedList.add(mappingEntityToDto(category.get(i)));
            i++;
        }
        return printedList;
    }
    private Category updatingDateAndUser(Category category) {
        category.setUpdatedUser(authController.getUserName());
        category.setUpdatedDate(new Date(System.currentTimeMillis()));
        return categoryRepository.save(category);
    }
}
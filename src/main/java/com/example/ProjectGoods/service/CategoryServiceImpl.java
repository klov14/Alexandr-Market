package com.example.ProjectGoods.service;

import com.example.ProjectGoods.authenticationController.AuthController;
import com.example.ProjectGoods.model.*;
import com.example.ProjectGoods.repository.CategoryRepository;
import com.example.ProjectGoods.repository.CountryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
@AllArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;
    private CountryRepository countryRepository;
    private AuthController authController;
    @Override
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<Category> listAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public void deleteCategoryById(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Category updateCategory(Long categoryId, Long countryId) {
        Optional<Category> category = categoryRepository.findById(categoryId);
        Optional<Country> country = countryRepository.findById(countryId);
        if (country.isPresent() && category.isPresent()) {
            category.get().assignCountryAndCategory(country.get());
            updatingDateAndUser(category.get());
            return categoryRepository.save(category.get());
        }
        else {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public Set<Country> printAllByCategory(Long categoryId) {
        Optional<Category> categoryNew = categoryRepository.findById(categoryId);
        if (categoryNew.isPresent()) {
           return categoryNew.get().getCountriesAvailable();
        }
        else {
            throw new EntityNotFoundException();
      }
    }

    @Override
    public CategoryDto categoryToDto(Long categoryId) {
        Optional<Category> categoryCheck = categoryRepository.findById(categoryId);
        if(categoryCheck.isPresent()){
            Category newCategory = new Category();
            return mappingEntityToDto(newCategory);
        }
        else{
            throw new EntityNotFoundException();
        }
    }

    @Override
    public Category dtoTo(CategoryDto categoryDto) {
        return DtoToEntity(categoryDto);
    }

    private CategoryDto mappingEntityToDto(Category category){
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setNameCategory(category.getNameCategory());
        return categoryDto;
    }
    private Category DtoToEntity(CategoryDto categoryDto){
        Category categoryNew = new Category();
        categoryNew.setId(categoryDto.getId());
        categoryNew.setNameCategory(categoryDto.getNameCategory());
        categoryNew.setCreatedUser(authController.getUserName());
        categoryNew.setCreatedDate(new Date(System.currentTimeMillis()));
        return categoryRepository.save(categoryNew);
    }
    public Category updatingDateAndUser(Category category) {
        category.setUpdatedUser(authController.getUserName());
        category.setUpdatedDate(new Date(System.currentTimeMillis()));
        return categoryRepository.save(category);
    }
}
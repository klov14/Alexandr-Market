package com.example.ProjectGoods.service.impl;

import com.example.ProjectGoods.authenticationController.AuthController;
import com.example.ProjectGoods.model.dto.CategoryDTO;
import com.example.ProjectGoods.model.*;
import com.example.ProjectGoods.repository.CategoryRepository;
import com.example.ProjectGoods.repository.CountryRepository;
import com.example.ProjectGoods.service.CategoryService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
@AllArgsConstructor
@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;
    private CountryRepository countryRepository;
    private AuthController authController;

    @Override
    public List<CategoryDTO> listAllCategory() {
        return mappingEntityToDtoList(categoryRepository.findAll());
    }

    @Override
    public void deleteCategoryById(Long id) {
        log.info("Category was deleted {}", id);
        categoryRepository.deleteById(id);
    }

    @Override
    public Category assignCategoryToCountry(Long categoryId, Long countryId) {
        Optional<Category> category = categoryRepository.findById(categoryId);
        Optional<Country> country = countryRepository.findById(countryId);
        if (country.isPresent() && category.isPresent()) {
            category.get().getCountriesAvailable().add(country.get());
            updatingDateAndUser(category.get());
            log.info("category {} is assigned to the country {}", category.get().getNameCategory(), country.get().getNameOf());
            return categoryRepository.save(category.get());
        }
        else {
            log.error("Id used does not exist! CountryId = {}, categoryId = {}", countryId, categoryId);
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
            log.error("Id used does not exist CategoryId = {}", categoryId);
            throw new EntityNotFoundException();
      }
    }

    @Override
    public CategoryDTO getCategoryById(Long categoryId) {
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
    public Category create(CategoryDTO categoryDto) {
        Category category = mappingDtoToEntity(categoryDto);
        category.setCreatedUser(authController.getUserName());
        category.setCreatedDate(new Date(System.currentTimeMillis()));
        log.info("A new category was created");
        return categoryRepository.save(category);
    }

    @Override
    public Category update(CategoryDTO categoryDto) {
        Category category = mappingDtoToEntity(categoryDto);
        Optional<Category> oldCategory = categoryRepository.findById(category.getId());
        if (oldCategory.isPresent()) {
            category.setCreatedUser(oldCategory.get().getCreatedUser());
            category.setCreatedDate(oldCategory.get().getCreatedDate());
            updatingDateAndUser(category);
            log.info("A category has been updated {}", category.getId());
            return categoryRepository.save(category);
        }
        else {
            log.error("Id used does not exist CategoryId = {}", category.getId());
            throw new EntityNotFoundException();
        }
    }

    private CategoryDTO mappingEntityToDto(Category category){
        CategoryDTO categoryDto = new CategoryDTO();
        categoryDto.setId(category.getId());
        categoryDto.setNameCategory(category.getNameCategory());
        return categoryDto;
    }
    private Category mappingDtoToEntity(CategoryDTO categoryDto){
        Category categoryNew = new Category();
        categoryNew.setId(categoryDto.getId());
        categoryNew.setNameCategory(categoryDto.getNameCategory());
        return categoryNew;
    }
    private List<CategoryDTO> mappingEntityToDtoList(List<Category> category){
        List<CategoryDTO> printedList = new ArrayList<>();
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
package com.example.ProjectGoods.controller;

import com.example.ProjectGoods.model.Category;
import com.example.ProjectGoods.model.Country;
import com.example.ProjectGoods.model.Good;
import com.example.ProjectGoods.repository.GoodRepository;
import com.example.ProjectGoods.service.CategoryService;
import com.example.ProjectGoods.service.CountryService;
import com.example.ProjectGoods.service.GoodService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/myGoods1")
public class GoodController {
    @Autowired
    private GoodRepository goodRepository;
    @Autowired
    private GoodService goodService;
    @Autowired
    private CountryService countryService;
    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public Good addGood (@RequestBody Good good) {
        return goodService.create(good);
    }

    @GetMapping
    public List<Good> listEveryone (){
        return goodService.listAll();
    }

    @GetMapping("/{id}")
    public Optional<Good> findById(@PathVariable Long id) {
        return Optional.ofNullable(goodService.getById(id).orElseThrow(() -> new EntityNotFoundException()));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        goodService.deleteById(id);
    }

    @PutMapping("/{goodsId}/country/{countryId}")//set good to the country
    Good assignCountryToGood(@PathVariable Long goodsId, @PathVariable Long countryId){
        Optional<Country> country = countryService.getCountryById(countryId);
        Optional<Good> good = goodService.getById(goodsId);
        Good goodNew;
        Country countryNew;
        if (good.isPresent() && country.isPresent()) {
            goodNew = good.get();
            countryNew = country.get();
        }
        else {
            throw new EntityNotFoundException();
        }
        goodNew.setCountry(countryNew);
        return goodRepository.save(goodNew);
    }

    @PutMapping("/{goodsId}/category/{categoryId}")//set good to the country
    Good assignCategoryToGood(@PathVariable Long goodsId, @PathVariable Long categoryId){
        Optional<Category> category = categoryService.getCategoryById(categoryId);
        Optional<Good> good = goodService.getById(goodsId);
        Good goodNew;
        Category category1;
        if (good.isPresent() && category.isPresent()) {
            goodNew = good.get();
            category1 = category.get();
        }
        else {
            throw new EntityNotFoundException();
        }
        goodNew.setCategory(category1);
        return goodRepository.save(goodNew);
    }
}


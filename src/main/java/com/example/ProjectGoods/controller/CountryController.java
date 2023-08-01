package com.example.ProjectGoods.controller;

import com.example.ProjectGoods.model.Category;
import com.example.ProjectGoods.model.Country;
import com.example.ProjectGoods.service.CountryService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/myCountries")
public class CountryController {
    @Autowired
    private CountryService countryService;

    @PostMapping
    public Country addCountry (@RequestBody Country country) {
        return countryService.createCountry(country);
    }

    @GetMapping
    public List<Country> listEveryone (){
        return countryService.listAllCountry();
    }

    @GetMapping("/{id}")
    public Country findById(@PathVariable Long id) {
        return countryService.getCountryById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        countryService.deleteCountryById(id);
    }

    @GetMapping("/all/{countryId}")
    public Set<Category> printAllWithCategories(@PathVariable Long countryId) {
        return countryService.printAllByCountry(countryId);
    }
}



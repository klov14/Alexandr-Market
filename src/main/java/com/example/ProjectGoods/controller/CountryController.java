package com.example.ProjectGoods.controller;

import com.example.ProjectGoods.model.Category;
import com.example.ProjectGoods.model.Country;
import com.example.ProjectGoods.dto.CountryDto;
import com.example.ProjectGoods.service.CountryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/myCountries")
public class CountryController {
    private CountryService countryService;

    @PostMapping
    public Country addCountry (@RequestBody CountryDto countryDto) {
        return countryService.dtoToCountry(countryDto);
    }

    @GetMapping
    public List<Country> listEveryone (){
        return countryService.listAllCountry();
    }

    @GetMapping("/{id}")
    public CountryDto findById(@PathVariable Long id) {
        return countryService.countryToDto(id);
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



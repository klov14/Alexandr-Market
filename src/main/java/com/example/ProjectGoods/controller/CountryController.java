package com.example.ProjectGoods.controller;

import com.example.ProjectGoods.model.Category;
import com.example.ProjectGoods.model.Country;
import com.example.ProjectGoods.model.dto.CountryDTO;
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
    public Country addCountry (@RequestBody CountryDTO countryDto) {
        return countryService.create(countryDto);
    }
    @PostMapping("/update")
    public Country update(@RequestBody CountryDTO countryDto){
        return countryService.update(countryDto);
    }
    @GetMapping("/{id}")
    public CountryDTO findById(@PathVariable Long id) {
        return countryService.getById(id);
    }
    @GetMapping
    public List<CountryDTO> getListCountries (){
        return countryService.listAllCountry();
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        countryService.deleteCountryById(id);
    }

    @GetMapping("/all/{countryId}")
    public Set<Category> printAllCategoriesFromCountry(@PathVariable Long countryId) {
        return countryService.printAllByCountry(countryId);
    }
}



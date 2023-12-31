package com.example.ProjectGoods.service;

import com.example.ProjectGoods.model.Category;
import com.example.ProjectGoods.model.Country;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface CountryService {
    Country createCountry(Country country);
    Country getCountryById(Long id);
    List<Country> listAllCountry();
    void deleteCountryById(Long id);
    Set<Category> printAllByCountry(Long countryId);
}

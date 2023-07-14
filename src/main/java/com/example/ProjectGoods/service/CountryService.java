package com.example.ProjectGoods.service;

import com.example.ProjectGoods.model.Country;
import com.example.ProjectGoods.model.Good;

import java.util.List;
import java.util.Optional;

public interface CountryService {
    Country createCountry(Country country);
    Optional<Country> getCountryById(Long id);
    List<Country> listAllCountry();
    void deleteCountryById(Long id);
}

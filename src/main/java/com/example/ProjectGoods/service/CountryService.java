package com.example.ProjectGoods.service;

import com.example.ProjectGoods.dto.CountryDto;
import com.example.ProjectGoods.model.*;

import java.util.List;
import java.util.Set;

public interface CountryService {
    List<Country> listAllCountry();
    void deleteCountryById(Long id);
    Set<Category> printAllByCountry(Long countryId);
    CountryDto countryToDto(Long countryId);
    Country dtoToCountry(CountryDto countryDto);
}

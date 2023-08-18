package com.example.ProjectGoods.service;

import com.example.ProjectGoods.model.dto.CountryDTO;
import com.example.ProjectGoods.model.*;

import java.util.List;
import java.util.Set;

public interface CountryService {
    List<CountryDTO> listAllCountry();
    void deleteCountryById(Long id);
    Set<Category> printAllByCountry(Long countryId);
    CountryDTO getById(Long countryId);
    Country create(CountryDTO countryDto);
    Country update(CountryDTO countryDto);
}

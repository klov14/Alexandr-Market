package com.example.ProjectGoods.service;

import com.example.ProjectGoods.model.Category;
import com.example.ProjectGoods.model.Country;
import com.example.ProjectGoods.repository.CategoryRepository;
import com.example.ProjectGoods.repository.CountryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CountryServiceImpl implements CountryService{
    @Autowired
    private CountryRepository countryRepository;

    @Override
    public Country createCountry(Country country) {
        return countryRepository.save(country);
    }

    @Override
    public Country getCountryById(Long id) {
        return countryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }

    @Override
    public List<Country> listAllCountry() {
        return countryRepository.findAll();
    }

    @Override
    public void deleteCountryById(Long id) {
        countryRepository.deleteById(id);
    }

    @Override
    public Set<Category> printAllByCountry(Long countryId) {
        Optional<Country> countryNew = countryRepository.findById(countryId);
        if (countryNew.isPresent()) {
            return countryNew.get().getCategories();
        }
        else {
            throw new EntityNotFoundException();
        }
    }

}

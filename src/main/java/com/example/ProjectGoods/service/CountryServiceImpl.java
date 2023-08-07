package com.example.ProjectGoods.service;

import com.example.ProjectGoods.authenticationController.AuthController;
import com.example.ProjectGoods.model.*;
import com.example.ProjectGoods.repository.CategoryRepository;
import com.example.ProjectGoods.repository.CountryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class CountryServiceImpl implements CountryService{

    private CountryRepository countryRepository;
    private AuthController authController;

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

    @Override
    public CountryDto countryToDto(Long countryId) {
        Optional<Country> countryCheck = countryRepository.findById(countryId);
        if(countryCheck.isPresent()){
            Country newCountry = new Country();
            return mappingEntityToDto(newCountry);
        }
        else{
            throw new EntityNotFoundException();
        }
    }

    @Override
    public Country dtoToCountry(CountryDto countryDto) {
        return DtoToEntity(countryDto);
    }

//    public Country updatingDateAndUser(Country country) {
//        country.setUpdatedUser(authController.getUserName());
//        country.setUpdatedDate(new Date(System.currentTimeMillis()));
//        return countryRepository.save(country);
//    }
    private CountryDto mappingEntityToDto(Country newCountry) {
        CountryDto countryDto = new CountryDto();
        countryDto.setId(newCountry.getId());
        countryDto.setCode(newCountry.getCode());
        countryDto.setNameOf(newCountry.getNameOf());
        return countryDto;
    }
    private Country DtoToEntity(CountryDto countryDto){
        Country countryNew = new Country();
        countryNew.setId(countryDto.getId());
        countryNew.setCode(countryDto.getCode());
        countryNew.setNameOf(countryDto.getNameOf());
        countryNew.setCreatedUser(authController.getUserName());
        countryNew.setCreatedDate(new Date(System.currentTimeMillis()));
        return countryRepository.save(countryNew);
    }
}

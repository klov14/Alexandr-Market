package com.example.ProjectGoods.service.impl;

import com.example.ProjectGoods.authenticationController.AuthController;
import com.example.ProjectGoods.model.dto.CountryDTO;
import com.example.ProjectGoods.model.*;
import com.example.ProjectGoods.repository.CountryRepository;
import com.example.ProjectGoods.service.CountryService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
@AllArgsConstructor
public class CountryServiceImpl implements CountryService {
    private CountryRepository countryRepository;
    private AuthController authController;
    @Override
    public CountryDTO getById(Long countryId) {
        Optional<Country> countryCheck = countryRepository.findById(countryId);
        if(countryCheck.isPresent()){
            Country newCountry = countryCheck.get();
            return mappingEntityToDto(newCountry);
        }
        else{
            throw new EntityNotFoundException();
        }
    }
    @Override
    public List<CountryDTO> listAllCountry() {
        return mappingEntityToDtoList(countryRepository.findAll());
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
    public Country create(CountryDTO countryDto) {
        Country country = mappingDtoToEntity(countryDto);
        country.setCreatedUser(authController.getUserName());
        country.setCreatedDate(new Date(System.currentTimeMillis()));
        return countryRepository.save(country);
    }

    @Override
    public Country update(CountryDTO countryDto) {
        Country country = mappingDtoToEntity(countryDto);
        Optional<Country> oldCountry = countryRepository.findById(country.getId());
        if(oldCountry.isPresent()){
            country.setCreatedUser(oldCountry.get().getCreatedUser());
            country.setCreatedDate(oldCountry.get().getCreatedDate());
            country.setUpdatedUser(authController.getUserName());
            country.setUpdatedDate(new Date(System.currentTimeMillis()));
            return countryRepository.save(country);
        }
        else {
            throw new EntityNotFoundException();
        }
    }

    private CountryDTO mappingEntityToDto(Country newCountry) {
        CountryDTO countryDto = new CountryDTO();
        countryDto.setId(newCountry.getId());
        countryDto.setCode(newCountry.getCode());
        countryDto.setNameOf(newCountry.getNameOf());
        return countryDto;
    }
    private List<CountryDTO> mappingEntityToDtoList(List<Country> newCountry){
        List<CountryDTO> printedList = new ArrayList<>();
        int i = 0;
        while(i < newCountry.size()){
            printedList.add(mappingEntityToDto(newCountry.get(i)));
            i++;
        }
        return printedList;
    }
    private Country mappingDtoToEntity(CountryDTO countryDto){
        Country countryNew = new Country();
        countryNew.setId(countryDto.getId());
        countryNew.setCode(countryDto.getCode());
        countryNew.setNameOf(countryDto.getNameOf());
        countryNew.setCreatedUser(authController.getUserName());
        countryNew.setCreatedDate(new Date(System.currentTimeMillis()));
        return countryRepository.save(countryNew);
    }
}

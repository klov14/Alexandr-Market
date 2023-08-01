package com.example.ProjectGoods.service;

import com.example.ProjectGoods.model.Category;
import com.example.ProjectGoods.model.Country;
import com.example.ProjectGoods.model.Good;
import com.example.ProjectGoods.repository.CategoryRepository;
import com.example.ProjectGoods.repository.CountryRepository;
import com.example.ProjectGoods.repository.GoodRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GoodServiceImpl implements GoodService{
    @Autowired
    private GoodRepository goodRepository;
    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Good create(Good good) {
        return goodRepository.save(good);
    }

    @Override
    public Optional<Good> getById(Long id) {
        return Optional.ofNullable(goodRepository.findById(id).orElseThrow(() -> new EntityNotFoundException()));
    }


    @Override
    public List<Good> listAll() {
        return goodRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        goodRepository.deleteById(id);
    }

    @Override
    public Good addCountryToGood(Long goodsId, Long countryId) {
        Optional<Country> country = countryRepository.findById(countryId);
        Optional<Good> good = goodRepository.findById(goodsId);
        if (good.isPresent() && country.isPresent()) {
            Good goodNew = good.get();
            Country countryNew = country.get();
            goodNew.setCountry(countryNew);
            return goodRepository.save(goodNew);
        }
        else {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public Good addCategoryToGood(Long goodsId, Long categoryId) {
        Optional<Category> category = categoryRepository.findById(categoryId);
        Optional<Good> good = goodRepository.findById(goodsId);
        if (good.isPresent() && category.isPresent()) {
            Good goodNew = good.get();
            Category category1 = category.get();
            goodNew.setCategoryMapping(category1);
            return goodRepository.save(goodNew);
        } else {throw new EntityNotFoundException();}
    }
}
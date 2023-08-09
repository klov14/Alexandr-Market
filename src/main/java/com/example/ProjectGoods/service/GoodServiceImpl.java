package com.example.ProjectGoods.service;

import com.example.ProjectGoods.authenticationController.AuthController;
import com.example.ProjectGoods.dto.GoodsDTO;
import com.example.ProjectGoods.model.Category;
import com.example.ProjectGoods.model.Country;
import com.example.ProjectGoods.model.Good;
import com.example.ProjectGoods.repository.CategoryRepository;
import com.example.ProjectGoods.repository.CountryRepository;
import com.example.ProjectGoods.repository.GoodRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
@AllArgsConstructor
@Service
public class GoodServiceImpl implements GoodService{
    private GoodRepository goodRepository;
    private CountryRepository countryRepository;
    private CategoryRepository categoryRepository;
    private AuthController authController;
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
            good.get().setCountryMapping(country.get());
            updatingDateAndUser(good.get());
            return goodRepository.save(good.get());
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
            updatingDateAndUser(goodNew);
            return goodRepository.save(goodNew);
        } else {throw new EntityNotFoundException();}
    }




    //DEALING WITH DTO!
    @Override
    public GoodsDTO goodsToDto(Long goodId) {
        Optional<Good> goodCheck = goodRepository.findById(goodId);
        if(goodCheck.isPresent()){
            Good newGood = new Good();
            return mappingEntityToDto(newGood);
        }
        else{
            throw new EntityNotFoundException();
        }
    }
    @Override
    public Good dtoToGoods(GoodsDTO goodDto) {
        return DtoToEntity(goodDto);
    }

    public Good updatingDateAndUser(Good good) {
        good.setUpdatedUser(authController.getUserName());
        good.setUpdatedDate(new Date(System.currentTimeMillis()));
        return goodRepository.save(good);
    }

    private GoodsDTO mappingEntityToDto(Good goods){
        GoodsDTO goodsDTO = new GoodsDTO();
        goodsDTO.setId(goods.getId());
        goodsDTO.setResell(goods.getResell());
        goodsDTO.setBuying(goods.getBuying());
        goodsDTO.setProduct(goods.getProduct());
        goodsDTO.setWeight(goods.getWeight());
        return goodsDTO;
    }

    private Good DtoToEntity(GoodsDTO goodDto){
        Good goodsNew = new Good();
        goodsNew.setId(goodDto.getId());
        goodsNew.setResell(goodDto.getResell());
        goodsNew.setProduct(goodDto.getProduct());
        goodsNew.setWeight(goodDto.getWeight());
        goodsNew.setBuying(goodDto.getBuying());
        goodsNew.setCreatedUser(authController.getUserName());
        goodsNew.setCreatedDate(new Date(System.currentTimeMillis()));
        return goodRepository.save(goodsNew);
    }
}
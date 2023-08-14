package com.example.ProjectGoods.service.impl;

import com.example.ProjectGoods.authenticationController.AuthController;
import com.example.ProjectGoods.dto.GoodsDTO;
import com.example.ProjectGoods.model.Category;
import com.example.ProjectGoods.model.Country;
import com.example.ProjectGoods.model.Good;
import com.example.ProjectGoods.repository.CategoryRepository;
import com.example.ProjectGoods.repository.CountryRepository;
import com.example.ProjectGoods.repository.GoodRepository;
import com.example.ProjectGoods.service.GoodService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
@AllArgsConstructor
@Service
public class GoodServiceImpl implements GoodService {
    private GoodRepository goodRepository;
    private CountryRepository countryRepository;
    private CategoryRepository categoryRepository;
    private AuthController authController;

    @Override
    public List<GoodsDTO> listAll() {
        return mappingEntityToDtoList(goodRepository.findAll());
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

    @Override
    public GoodsDTO getById(Long goodId) {
        Optional<Good> goodCheck = goodRepository.findById(goodId);
        if(goodCheck.isPresent()){
            Good newGood = goodCheck.get();
            return mappingEntityToDto(newGood);
        }
        else{
            throw new EntityNotFoundException();
        }
    }
    @Override
    public Good createGood(GoodsDTO goodDto) {
        Good goodCreated = mappingDtoToEntity(goodDto);
        goodCreated.setCreatedUser(authController.getUserName());
        goodCreated.setCreatedDate(new Date(System.currentTimeMillis()));
        return goodRepository.save(goodCreated);
    }

    @Override
    public Good update(GoodsDTO goodsDTO) {
        Good good = mappingDtoToEntity(goodsDTO);
        Optional<Good> oldGood= goodRepository.findById(good.getId());
        if(oldGood.isPresent()){
            good.setCreatedUser(oldGood.get().getCreatedUser());
            good.setCreatedDate(oldGood.get().getCreatedDate());
            updatingDateAndUser(good);
            return goodRepository.save(good);
        }
        else{
            throw new EntityNotFoundException();
        }

    }





    //DEALING WITH DTO!
    private Good updatingDateAndUser(Good good) {
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

    private Good mappingDtoToEntity(GoodsDTO goodDto){
        Good goodsNew = new Good();
        goodsNew.setId(goodDto.getId());
        goodsNew.setResell(goodDto.getResell());
        goodsNew.setProduct(goodDto.getProduct());
        goodsNew.setWeight(goodDto.getWeight());
        goodsNew.setBuying(goodDto.getBuying());
        return goodsNew;
    }

    /**
     * Mapping LIST from Entity to Dto!!!
     * @param good
     * @return
     */
    private List<GoodsDTO> mappingEntityToDtoList(List<Good> good){
        List<GoodsDTO> printedList = new ArrayList<>();
        int i = 0;
        while(i < good.size()){
            printedList.add(mappingEntityToDto(good.get(i)));
            i++;
        }
        return printedList;
    }
}
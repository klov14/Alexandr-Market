package com.example.ProjectGoods.controller;

import com.example.ProjectGoods.dto.GoodsDTO;
import com.example.ProjectGoods.model.Good;
import com.example.ProjectGoods.service.GoodService;
import com.example.ProjectGoods.service.GoodsToDto;
import com.example.ProjectGoods.service.GoodsToDtoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/myGoods1")
public class GoodController {
    @Autowired
    private GoodService goodService;
    @Autowired
    private GoodsToDto goodsToDto;

    @PostMapping
    public Good addGood (@RequestBody Good good) {
        return goodService.create(good);
    }

    @GetMapping
    public List<Good> listEveryone (){
        return goodService.listAll();
    }

    @GetMapping("/{id}")
    public Optional<Good> findById(@PathVariable Long id) {
        return goodService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        goodService.deleteById(id);
    }

    @PutMapping("/{goodsId}/country/{countryId}")//set good to the country
    Good assignCountryToGood(@PathVariable Long goodsId, @PathVariable Long countryId){
        return goodService.addCountryToGood(goodsId, countryId);
    }

    @PutMapping("/{goodsId}/category/{categoryId}")//set good to the country
    Good assignCategoryToGood(@PathVariable Long goodsId, @PathVariable Long categoryId){
        return goodService.addCategoryToGood(goodsId, categoryId);
    }

    @GetMapping("/dto/{id}")//GET dto of the good by ID
    GoodsDTO getGoodsDto(@PathVariable Long id){
        return goodsToDto.getGoodsDto(id);
    }
}


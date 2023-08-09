package com.example.ProjectGoods.service;

import com.example.ProjectGoods.dto.GoodsDTO;
import com.example.ProjectGoods.model.Good;

import java.util.List;
import java.util.Optional;

public interface GoodService {
    Good addCountryToGood(Long goodsId, Long countryId);
    Good addCategoryToGood(Long goodsId, Long categoryId);
    Good create(Good good);
    Optional<Good> getById(Long id);
    List<Good> listAll();
    void deleteById(Long id);
    GoodsDTO goodsToDto(Long goodId);
    Good dtoToGoods(GoodsDTO goodDto);

}

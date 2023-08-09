package com.example.ProjectGoods.service;

import com.example.ProjectGoods.dto.GoodsDTO;
import com.example.ProjectGoods.model.Good;

import java.util.List;

public interface GoodService {
    Good addCountryToGood(Long goodsId, Long countryId);
    Good addCategoryToGood(Long goodsId, Long categoryId);
    List<GoodsDTO> listAll();
    void deleteById(Long id);
    GoodsDTO getById(Long goodId);
    Good createGood(GoodsDTO goodDto);
    Good update(GoodsDTO goodsDTO);
}

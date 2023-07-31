package com.example.ProjectGoods.service;

import com.example.ProjectGoods.dto.GoodsDTO;
import com.example.ProjectGoods.model.Good;

public interface GoodsToDto {
    GoodsDTO getGoodsDto(Long goodId);
}

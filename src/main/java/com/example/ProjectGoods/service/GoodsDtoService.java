package com.example.ProjectGoods.service;

import com.example.ProjectGoods.dto.GoodsDTO;
import com.example.ProjectGoods.model.Good;

public interface GoodsDtoService {
    GoodsDTO goodsToDto(Long goodId);
    Good dtoToGoods(GoodsDTO goodDto);
    Good updatingDateAndUser(Good good);
}

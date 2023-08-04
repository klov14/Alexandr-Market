package com.example.ProjectGoods.controller;

import com.example.ProjectGoods.dto.GoodsDTO;
import com.example.ProjectGoods.model.Good;
import com.example.ProjectGoods.service.GoodsDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/goods")
public class GoodsDtoController {
    @Autowired
    private GoodsDtoService goodsDtoService;

    @PostMapping
    public Good fromDtoToGoods(@RequestBody GoodsDTO goodsDTO){
        return goodsDtoService.dtoToGoods(goodsDTO);
    }
}

package com.example.ProjectGoods.service;

import com.example.ProjectGoods.dto.GoodsDTO;
import com.example.ProjectGoods.model.Good;
import com.example.ProjectGoods.repository.GoodRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class GoodsToDtoImpl implements GoodsToDto {
    @Autowired
    private GoodRepository goodRepository;
    @Override
    public GoodsDTO getGoodsDto(Long goodId ) {
        Optional<Good> goodCheck = goodRepository.findById(goodId);
        if(goodCheck.isPresent()){
            Good goodCurrent = goodCheck.get();
            GoodsDTO goodsDTO = new GoodsDTO();
            goodsDTO.setId(goodCurrent.getId());
            goodsDTO.setResell(goodCurrent.getResell());
            goodsDTO.setProduct(goodCurrent.getProduct());
            return goodsDTO;
    }
        else{
            throw new EntityNotFoundException();
        }
    }
}

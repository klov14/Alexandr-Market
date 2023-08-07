package com.example.ProjectGoods.service;

import com.example.ProjectGoods.dto.GoodsDTO;
import com.example.ProjectGoods.model.Good;
import com.example.ProjectGoods.repository.GoodRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class GoodsDtoServiceImpl implements GoodsDtoService {
    @Autowired
    private GoodRepository goodRepository;
    @Override
    public GoodsDTO goodsToDto(Long goodId) {
        Optional<Good> goodCheck = goodRepository.findById(goodId);
        if(goodCheck.isPresent()){
            Good goodCurrent = goodCheck.get();
            GoodsDTO goodsDTO = new GoodsDTO();
            goodsDTO.setId(goodCurrent.getId());
            goodsDTO.setResell(goodCurrent.getResell());
            goodsDTO.setBuying(goodCurrent.getBuying());
            goodsDTO.setProduct(goodCurrent.getProduct());
            goodsDTO.setWeight(goodCurrent.getWeight());
            return goodsDTO;
    }
        else{
            throw new EntityNotFoundException();
        }
    }
    @Override
    public Good dtoToGoods(GoodsDTO goodDto) {
        Good goodsNew = new Good();
        goodsNew.setId(goodDto.getId());
        goodsNew.setResell(goodDto.getResell());
        goodsNew.setProduct(goodDto.getProduct());
        goodsNew.setWeight(goodDto.getWeight());
        goodsNew.setBuying(goodDto.getBuying());
        goodsNew.setCreatedUser(goodDto.getUserName());
        goodsNew.setCreatedDate(goodDto.getCreatedDate());
        return goodRepository.save(goodsNew);
    }

    @Override
    public Good updatingDateAndUser(Good good) {
        GoodsDTO goodsCurrent = new GoodsDTO();
        good.setUpdatedUser(goodsCurrent.getUserName());
        good.setUpdatedDate(goodsCurrent.getCreatedDate());
        return goodRepository.save(good);
    }
}
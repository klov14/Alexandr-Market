package com.example.ProjectGoods.controller;

import com.example.ProjectGoods.model.GoodsDTO;
import com.example.ProjectGoods.model.Good;
import com.example.ProjectGoods.service.GoodService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/goods")
@AllArgsConstructor
public class GoodController {
    private GoodService goodService;
    @PostMapping
    public Good addGood (@RequestBody GoodsDTO goodDto) {
        return goodService.dtoToGoods(goodDto);
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

    /**
     * PUT good to the country
     * @param goodsId
     * @param countryId
     * @return
     */
    @PutMapping("/{goodsId}/country/{countryId}")
    public Good assignCountryToGood(@PathVariable Long goodsId, @PathVariable Long countryId){
        return goodService.addCountryToGood(goodsId, countryId);
    }

    /**
     * PUT good to the country
     * @param goodsId
     * @param categoryId
     * @return
     */
    @PutMapping("/{goodsId}/category/{categoryId}")
    public Good assignCategoryToGood(@PathVariable Long goodsId, @PathVariable Long categoryId){
        return goodService.addCategoryToGood(goodsId, categoryId);
    }

    /**
     * GET dto of the goods by ID
     * @param id
     */
    @GetMapping("/dto/{id}")
    public GoodsDTO getGoodsDto(@PathVariable Long id){
        return goodService.goodsToDto(id);
    }
}
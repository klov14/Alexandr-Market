package com.example.ProjectGoods.controller;

import com.example.ProjectGoods.model.dto.GoodsDTO;
import com.example.ProjectGoods.model.Good;
import com.example.ProjectGoods.service.GoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/goods")
@RequiredArgsConstructor
public class GoodController {
    private final GoodService goodService;
    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN') or hasAnyAuthority('SELLER')")
    public Good createGood(@RequestBody GoodsDTO goodDto) {
        return goodService.createGood(goodDto);
    }

    @PostMapping("/update")
    @PreAuthorize("hasAnyAuthority('SELLER')")
    public Good update(@RequestBody GoodsDTO goodsDTO){
        return goodService.update(goodsDTO);
    }

    /**
     * GET dto of the goods by ID
     *
     * @param id
     */

    @GetMapping("/{id}")
    public ResponseEntity<GoodsDTO> getGoodsDto(@PathVariable Long id) {
        return ResponseEntity.ok(goodService.getById(id));
    }

    @GetMapping
    public List<GoodsDTO> listEveryone() {
        return goodService.listAll();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAnyAuthority('SELLER')")
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
    @PreAuthorize("hasAuthority('ADMIN') or hasAnyAuthority('SELLER')")
    public Good assignCountryToGood(@PathVariable Long goodsId, @PathVariable Long countryId) {
        return goodService.addCountryToGood(goodsId, countryId);
    }

    /**
     * PUT good to the country
     * @param goodsId
     * @param categoryId
     * @return
     */
    @PutMapping("/{goodsId}/category/{categoryId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Good assignCategoryToGood(@PathVariable Long goodsId, @PathVariable Long categoryId) {
        return goodService.addCategoryToGood(goodsId, categoryId);
    }
}
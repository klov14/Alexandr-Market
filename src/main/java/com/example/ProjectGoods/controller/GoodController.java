package com.example.ProjectGoods.controller;

import com.example.ProjectGoods.authenticationController.AuthController;
import com.example.ProjectGoods.exceptions.CustomAccessDeniedException;
import com.example.ProjectGoods.model.dto.GoodsDTO;
import com.example.ProjectGoods.model.Good;
import com.example.ProjectGoods.service.GoodService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/goods")
@RequiredArgsConstructor
@Slf4j
public class GoodController {
    private final GoodService goodService;
    private final AuthController authController;
    @PostMapping
    @PostAuthorize("hasAuthority('ADMIN') or hasAnyAuthority('SELLER')")
    public Good createGood(@RequestBody GoodsDTO goodDto) {
        if(!authController.hasRequiredAuthoritiesAdminAndSeller()) {
            throw new CustomAccessDeniedException("You do not have the access");
        }
        log.info("Goods {} was added", goodDto);
        return goodService.createGood(goodDto);
    }

    @PostMapping("/update")
    @PostAuthorize("hasAuthority('ADMIN') or hasAnyAuthority('SELLER')")
    public Good update(@RequestBody GoodsDTO goodsDTO){
        if(!authController.hasRequiredAuthoritiesAdminAndSeller()) {
            throw new CustomAccessDeniedException("You do not have the access");
        }
        log.info("Goods {} was updated", goodsDTO);
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
    @PostAuthorize("hasAuthority('ADMIN') or hasAuthority('SELLER')")
    public void delete(@PathVariable Long id) {
        if(!authController.hasRequiredAuthoritiesAdminAndSeller()) {
            throw new CustomAccessDeniedException("You do not have the access");
        }
        log.info("Good with id {} was deleted", id);
        goodService.deleteById(id);
    }

    /**
     * PUT good to the country
     * @param goodsId
     * @param countryId
     * @return
     */
    @PutMapping("/{goodsId}/country/{countryId}")
    @PostAuthorize("hasAuthority('ADMIN')")
    public Good assignCountryToGood(@PathVariable Long goodsId, @PathVariable Long countryId) {
        if(!authController.hasRequiredAuthoritiesAdminOnly()) {
            throw new CustomAccessDeniedException("You do not have the access");
        }
        log.info("Good {} was assigned to the category {}", goodsId, countryId);
        return goodService.addCountryToGood(goodsId, countryId);
    }

    /**
     * PUT good to the category
     * @param goodsId
     * @param categoryId
     * @return
     */
    @PutMapping("/{goodsId}/category/{categoryId}")
    @PostAuthorize("hasAuthority('ADMIN')")
    public Good assignCategoryToGood(@PathVariable Long goodsId, @PathVariable Long categoryId) {
        if (!authController.hasRequiredAuthoritiesAdminOnly()) {
            throw new CustomAccessDeniedException("You do not have the access");
        }
        log.info("Good {} was assigned to the category {}", goodsId, categoryId);
        return goodService.addCategoryToGood(goodsId, categoryId);
    }
}
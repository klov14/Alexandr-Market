package com.example.ProjectGoods.service;

import com.example.ProjectGoods.model.Good;

import java.util.List;
import java.util.Optional;

public interface GoodService {
    Good reAssign(Good good);
    Good create(Good good);
    Optional<Good> getById(Long id);
    List<Good> listAll();
    void deleteById(Long id);
}

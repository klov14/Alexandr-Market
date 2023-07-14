package com.example.ProjectGoods.service;

import com.example.ProjectGoods.model.Good;
import com.example.ProjectGoods.repository.GoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GoodServiceImpl implements GoodService{
    @Autowired
    private GoodRepository goodRepository;

    @Override
    public Good create(Good good) {
        return goodRepository.save(good);
    }

    @Override
    public Optional<Good> getById(Long id) {
        return goodRepository.findById(id);
    }

    @Override
    public List<Good> listAll() {
        return goodRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        goodRepository.deleteById(id);
    }
}
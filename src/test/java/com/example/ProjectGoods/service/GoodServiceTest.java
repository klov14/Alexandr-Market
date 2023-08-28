package com.example.ProjectGoods.service;

import com.example.ProjectGoods.authenticationController.AuthController;
import com.example.ProjectGoods.repository.CategoryRepository;
import com.example.ProjectGoods.repository.CountryRepository;
import com.example.ProjectGoods.repository.GoodRepository;
import com.example.ProjectGoods.service.impl.GoodServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class GoodServiceTest {
    @Mock
    private GoodRepository goodRepository;
    private GoodService underTest;
    @Mock
    private CountryRepository countryRepository;
    @Mock
    private CategoryRepository categoryRepository;
    private AuthController authController;


    @BeforeEach
    void setUp() {
        underTest = new GoodServiceImpl(goodRepository, countryRepository, categoryRepository, authController);
    }

    @Test
    void canListAll() {
        //when
        underTest.listAll();
        //then
        verify(goodRepository).findAll();
    }

    @Test
    void createGood() {

    }

    @Test
    @Disabled
    void canAddCountryToGood() {

    }

    @Test
    @Disabled
    void addCategoryToGood() {
    }

    @Test
    @Disabled
    void deleteById() {
    }

    @Test
    @Disabled
    void getById() {
    }



    @Test
    @Disabled
    void update() {
    }
}
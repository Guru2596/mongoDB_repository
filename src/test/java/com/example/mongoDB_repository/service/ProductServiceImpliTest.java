package com.example.mongoDB_repository.service;


import com.example.mongoDB_repository.model.Product;
import com.example.mongoDB_repository.model.ProductDiscription;
import com.example.mongoDB_repository.repository.ProductRepository;
import com.example.mongoDB_repository.exception.ProductAlreadyExiistException;
import com.example.mongoDB_repository.exception.ProductNotFoundException;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImpliTest {

    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private ProductServiceImpli productServiceImpli;
    private Product product1,product2;
    List<Product> productList;
    ProductDiscription productDiscription1,productDiscription2;

    @BeforeEach
    public void setUp(){
        productDiscription1 = new ProductDiscription(35000,"SONY");
        product1 = new Product(1001,"SONY max",productDiscription1);
        productDiscription2 = new ProductDiscription(45000,"BLACKBERRY");
        product2 = new Product(1002,"Micro Max",productDiscription2);
        productList = Arrays.asList(product1,product2);
    }

    @AfterEach
    public void tearDown(){
        product1 = null;
        product2 = null;
    }

    @Test
    public void givenProductToSaveReturnSavedProductSuccess() throws ProductAlreadyExiistException {
        when(productRepository.findById(product1.get_id())).thenReturn(Optional.ofNullable(null));
        when(productRepository.save(any())).thenReturn(product1);
        assertEquals(product1,productServiceImpli.saveProduct(product1));
        verify(productRepository,times(1)).save(any());
        verify(productRepository,times(1)).findById(any());
    }

//    @Test
//    public void givenProductToDeleteShouldDeleteSuccess() throws ProductNotFoundException {
//        when(productRepository.findById(product1.get_id())).thenReturn(Optional.ofNullable(null));
//        boolean flag = productServiceImpli.deleteByProductId(product1.get_id());
//        assertEquals(true,flag);
//        verify(productRepository,times(1)).deleteById(any());
//        verify(productRepository,times(1)).findById(any());
//    }

    @Test
    public void givenProductToSaveReturnProductFailure() {
        when(productRepository.findById(product1.get_id())).thenReturn(Optional.ofNullable(product1));
        assertThrows(ProductAlreadyExiistException.class,()->productServiceImpli.saveProduct(product1));
        verify(productRepository,times(0)).save(any());
        verify(productRepository,times(1)).findById(any());
    }
}

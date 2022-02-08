package com.example.mongoDB_repository.repository;

import com.example.mongoDB_repository.model.Product;
import com.example.mongoDB_repository.model.ProductDiscription;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataMongoTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;
    private Product product;
    private ProductDiscription productDiscription;

    @BeforeEach
    public void setUp(){
        productDiscription = new ProductDiscription(50000,"brand");
        product = new Product(1001,"Phone",productDiscription);
    }

    @AfterEach
    public void tearDown(){
        productDiscription = null;
        product = null;
        productRepository.deleteAll();
    }
    @Test
    public void givenProductToSaveShouldReturnProduct() {
        productRepository.insert(product);
        Product product1 = productRepository.findById(product.get_id()).get();
        assertNotNull(product1);
        assertEquals(product.get_id(),product1.get_id());
    }

    @Test
    public void givenProductToDeleteShouldDeleteProduct(){
        productRepository.insert(product);
        Product product1 = productRepository.findById(product.get_id()).get();
        productRepository.delete(product1);
        Assertions.assertEquals(Optional.empty(), productRepository.findById(product.get_id()));
    }
    @Test
    public void givenProductToSaveShouldReturnProduct1() {
        productRepository.insert(product);
        Product product1 = productRepository.findById(product.get_id()).get();
        assertNotNull(product1);
        assertEquals(product.get_id(),product1.get_id());
    }
    @Test
    public void givenProductToDeleteShouldDeleteProduct1(){
        productRepository.insert(product);
        Product product1 = productRepository.findById(product.get_id()).get();
        productRepository.delete(product1);
        Assertions.assertEquals(Optional.empty(), productRepository.findById(product.get_id()));
    }
}

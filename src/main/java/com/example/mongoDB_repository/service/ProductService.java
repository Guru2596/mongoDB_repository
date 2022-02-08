package com.example.mongoDB_repository.service;

import com.example.mongoDB_repository.exception.ProductAlreadyExiistException;
import com.example.mongoDB_repository.exception.ProductNotFoundException;
import com.example.mongoDB_repository.model.Product;

import java.util.List;

public interface ProductService {
    Product saveProduct(Product product) throws ProductAlreadyExiistException;
    List<Product> getAllProducts();
    boolean deleteByProductId(int id) throws ProductNotFoundException;
    //List<Product> getProductbyid(int p);
}

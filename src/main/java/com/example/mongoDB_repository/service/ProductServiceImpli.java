package com.example.mongoDB_repository.service;

import com.example.mongoDB_repository.exception.ProductAlreadyExiistException;
import com.example.mongoDB_repository.exception.ProductNotFoundException;
import com.example.mongoDB_repository.model.Product;
import com.example.mongoDB_repository.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpli implements ProductService{
    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpli(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }




    @Override
    public Product saveProduct(Product product) throws ProductAlreadyExiistException {
        if(productRepository.findById(product.get_id()).isPresent()){
            throw new ProductAlreadyExiistException();
        }
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return (List<Product>) productRepository.findAll();
    }

    @Override
    public boolean deleteByProductId(int id) throws ProductNotFoundException {
        boolean flag = false;
        if(productRepository.findById(id).isEmpty()){
            throw new ProductNotFoundException();
        }
        else {
            productRepository.deleteById(id);
            flag = true;
        }

        return flag;
    }

//    @Override
//    public List<Product> getProductbyid(int p) {
//        return (List<Product>) productRepository.findByProductId(p);
//    }
}

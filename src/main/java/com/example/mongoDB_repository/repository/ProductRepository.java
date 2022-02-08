package com.example.mongoDB_repository.repository;

import com.example.mongoDB_repository.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product,Integer> {
    //List<Product> findByProductId(int id);
}

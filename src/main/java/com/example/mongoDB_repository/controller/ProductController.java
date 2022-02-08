package com.example.mongoDB_repository.controller;

import com.example.mongoDB_repository.exception.ProductAlreadyExiistException;
import com.example.mongoDB_repository.exception.ProductNotFoundException;
import com.example.mongoDB_repository.model.Product;
import com.example.mongoDB_repository.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/p1")
public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/product")
    public ResponseEntity<?>saveProduct(@RequestBody Product product) throws ProductAlreadyExiistException {
        return new ResponseEntity<>(productService.saveProduct(product), HttpStatus.CREATED);
    }

    @GetMapping("/products")
    public ResponseEntity<?>updateProduct(){
    return  new ResponseEntity<>(productService.getAllProducts(),HttpStatus.OK);
    }

    @DeleteMapping("/deleteproduct/{id}")
    public ResponseEntity<?>deleteProduct(@RequestBody Product product,@PathVariable int id) throws ProductNotFoundException {
        return new ResponseEntity<>(productService.deleteByProductId(id),HttpStatus.OK);
    }
}

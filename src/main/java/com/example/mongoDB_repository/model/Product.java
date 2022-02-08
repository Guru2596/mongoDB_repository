package com.example.mongoDB_repository.model;
import org.bson.types.ObjectId;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Product {
    @Id
    private int _id;
    private String product_name;
    private ProductDiscription productDiscription;

    public Product() {
    }

    public Product(int _id, String product_name, ProductDiscription productDiscription) {
        this._id = _id;
        this.product_name = product_name;
        this.productDiscription = productDiscription;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public ProductDiscription getProductDiscription() {
        return productDiscription;
    }

    public void setProductDiscription(ProductDiscription productDiscription) {
        this.productDiscription = productDiscription;
    }

    @Override
    public String toString() {
        return "Product{" +
                "_id=" + _id +
                ", product_name='" + product_name + '\'' +
                ", productDiscription=" + productDiscription +
                '}';
    }
}

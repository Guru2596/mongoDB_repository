package com.example.mongoDB_repository.model;

public class ProductDiscription {
    private int product_price;
    private String product_brand;

    public ProductDiscription() {
    }

    public ProductDiscription(int product_price, String product_brand) {
        this.product_price = product_price;
        this.product_brand = product_brand;
    }

    public int getProduct_price() {
        return product_price;
    }

    public void setProduct_price(int product_price) {
        this.product_price = product_price;
    }

    public String getProduct_brand() {
        return product_brand;
    }

    public void setProduct_brand(String product_brand) {
        this.product_brand = product_brand;
    }

    @Override
    public String toString() {
        return "ProductDiscription{" +
                "product_price=" + product_price +
                ", product_brand='" + product_brand + '\'' +
                '}';
    }
}

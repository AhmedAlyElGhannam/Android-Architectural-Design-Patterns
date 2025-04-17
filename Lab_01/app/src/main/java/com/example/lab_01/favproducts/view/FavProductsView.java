package com.example.lab_01.favproducts.view;

import com.example.lab_01.model.pojo.Product;

public interface FavProductsView {
    public void removeProduct(Product product);
    public void showErr(String err);
}

package com.example.lab_01.allproducts.view;

import com.example.lab_01.model.pojo.Product;

import java.util.List;

public interface AllProductsView {
    public void showData(List<Product> products);
    public void showErrMsg(String error);
}

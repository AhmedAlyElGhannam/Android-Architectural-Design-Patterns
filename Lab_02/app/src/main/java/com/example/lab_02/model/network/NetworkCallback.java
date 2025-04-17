package com.example.lab_02.model.network;

import com.example.lab_02.model.pojo.Product;

import java.util.List;

public interface NetworkCallback {
    public void onSuccessResult(List<Product> list);
    public void onFailureResult(String errMsg);
}
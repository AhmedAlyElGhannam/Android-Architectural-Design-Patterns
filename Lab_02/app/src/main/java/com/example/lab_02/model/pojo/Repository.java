package com.example.lab_02.model.pojo;

import android.content.Context;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Repository {
    private static Repository repository = null;
    @SerializedName("products")
    private List<Product> products;
    Context context;

    private Repository(Context _context) {
        this.context = _context;
//        ProductDataBase db = ProductDataBase

    }

    public List<Product> getProducts() {
        return products;
    }

//    public static ProductResponse getInstance() {
//
//    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

    public void delete(Product product) {
        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();
    }

    public void insert(Product product) {
        new Thread(new Runnable() {
            @Override
            public void run() {
//                ProductDataBase.getInstance(context).getProductDAO().deleteProduct();
            }
        }).start();
    }
}
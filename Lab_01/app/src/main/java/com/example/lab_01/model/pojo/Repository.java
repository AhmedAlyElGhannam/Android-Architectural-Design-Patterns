package com.example.lab_01.model.pojo;

import androidx.lifecycle.LiveData;

import com.example.lab_01.model.db.ProductDAO;
import com.example.lab_01.model.network.NetworkCallback;
import com.example.lab_01.model.network.ProductClient;

import java.util.List;

public class Repository {
    ProductClient productClient;
    ProductDAO productDAO;

    private static Repository repo = null;

    public static Repository getInstance(ProductClient _productClient, ProductDAO _productDAO) {
        if (repo == null) {
            repo = new Repository(_productClient, _productDAO);
        }

        return repo;
    }

    private Repository(ProductClient _productClient, ProductDAO _productDAO) {
        this.productClient = _productClient;
        this.productDAO = _productDAO;
    }

    public void getAllProducts(NetworkCallback networkCallback) {
        productClient.makeNetworkCall(networkCallback);
    }

    public void insertProduct(Product product) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                productDAO.insertProduct(product);
            }
        }).start();
    }

    public void deleteProduct(Product product) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                productDAO.deleteProduct(product);
            }
        }).start();
    }

    public LiveData<List<Product>> getStoredProducts() {
        return productDAO.getAllProducts();
    }
}


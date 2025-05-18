package com.example.mvvm.model.local

import com.example.mvvm.model.pojo.Product

interface IProductLocalDataSource {
    suspend fun insertProduct(product : Product)
    suspend fun removeProduct(product : Product)
    suspend fun getAllProducts() : List<Product>
}
package com.example.mvvm.model.remote

import com.example.mvvm.model.pojo.Product

interface IProductRemoteDataSource {
    suspend fun makeNetworkCall() :List<Product>?
}
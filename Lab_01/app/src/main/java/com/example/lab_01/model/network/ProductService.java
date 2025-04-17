package com.example.lab_01.model.network;

import com.example.lab_01.model.pojo.ProductResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductService {
    @GET("products")
    Call<ProductResponse> getProductsList();
}

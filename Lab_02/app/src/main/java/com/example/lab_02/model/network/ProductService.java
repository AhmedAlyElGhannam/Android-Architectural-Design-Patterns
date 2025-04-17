package com.example.lab_02.model.network;

import com.example.lab_02.model.pojo.Repository;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductService {
    @GET("products")
    Call<Repository> getProductsList();
}

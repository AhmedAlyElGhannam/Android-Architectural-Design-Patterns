package com.example.lab_02.model.network;

import android.util.Log;

import com.example.lab_02.model.pojo.ProductResponse;
import com.example.lab_02.model.pojo.Repository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// singleton
public class ProductClient {
    public static final String BASE_URL = "https://dummyjson.com/";
    public final String MAIN_ACTIVITY = "MAIN_ACTIVITY";
    private static ProductClient productClient = null;
    ProductService productService;

    private ProductClient() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        productService = retrofit.create(ProductService.class);
    }

    public static ProductClient getInstance() {
        if (productClient == null) {
            productClient = new ProductClient();
        }

        return productClient;
    }

    public void makeNetworkCall(NetworkCallback networkCallback) {
        productService.getProductsList().enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    networkCallback.onSuccessResult(response.body().getProducts());
                    Log.i(MAIN_ACTIVITY, "onResponse: CallBack" + response.body().getProducts());
                }
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                Log.i(MAIN_ACTIVITY, "onFailure: CallBack");
                networkCallback.onFailureResult(t.getMessage());
            }
        });
    }


}
package com.example.lab_01.allproducts.view;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab_01.R;
import com.example.lab_01.favproducts.view.OnDeleteClickListener;
import com.example.lab_01.model.db.ProductDAO;
import com.example.lab_01.model.db.ProductDataBase;
import com.example.lab_01.model.network.NetworkCallback;
import com.example.lab_01.model.network.ProductClient;
import com.example.lab_01.model.pojo.Product;

import java.util.List;

public class AllProductsActivity extends AppCompatActivity implements NetworkCallback, OnFavouriteClickListener, OnDeleteClickListener {

    RecyclerView recyclerView;
    public final String MAIN_ACTIVITY = "MAIN_ACTIVITY";
    RProductAdapter rProductAdapter;

    ProductDataBase productDataBase;
    ProductDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_products);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);

        ProductClient productClient = ProductClient.getInstance();
        productClient.makeNetworkCall(this);

        productDataBase = ProductDataBase.getInstance(this);
        dao = productDataBase.getProductDAO();


    }

    @Override
    public void onSuccessResult(List<Product> list) {
        Log.i(MAIN_ACTIVITY, "onSuccessResult: ");
        rProductAdapter = new RProductAdapter(AllProductsActivity.this, list, this, this);
        recyclerView.setAdapter(rProductAdapter);
    }

    @Override
    public void onFailureResult(String errMsg) {
        Log.i(MAIN_ACTIVITY, "onFailureResult: ");
    }

    @Override
    public void onFavouriteProductClick(Product product) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                dao.insertProduct(product);
            }
        }).start();
    }

    @Override
    public void onDeleteProductClick(Product product) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                dao.deleteProduct(product);
            }
        }).start();
    }
}
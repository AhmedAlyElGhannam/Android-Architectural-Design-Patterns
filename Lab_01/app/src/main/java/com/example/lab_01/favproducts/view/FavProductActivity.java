package com.example.lab_01.favproducts.view;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab_01.R;
import com.example.lab_01.allproducts.view.OnFavouriteClickListener;
import com.example.lab_01.allproducts.view.RProductAdapter;
import com.example.lab_01.model.db.ProductDAO;
import com.example.lab_01.model.db.ProductDataBase;
import com.example.lab_01.model.network.NetworkCallback;
import com.example.lab_01.model.network.ProductClient;
import com.example.lab_01.model.pojo.Product;

import java.util.List;

public class FavProductActivity extends AppCompatActivity implements OnDeleteClickListener {

    RecyclerView recyclerView;
    public final String MAIN_ACTIVITY = "MAIN_ACTIVITY";
    RFavAdapter rProductAdapter;

    ProductDataBase productDataBase;
    ProductDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fav_products);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);

        productDataBase = ProductDataBase.getInstance(this);
        dao = productDataBase.getProductDAO();

        LiveData<List<Product>> productList = dao.getAllProducts();
        productList.observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                rProductAdapter = new RFavAdapter(FavProductActivity.this, products, FavProductActivity.this);
                recyclerView.setAdapter(rProductAdapter);
            }
        });
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
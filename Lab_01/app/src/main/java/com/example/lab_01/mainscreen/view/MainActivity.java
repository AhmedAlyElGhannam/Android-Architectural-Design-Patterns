package com.example.lab_01.mainscreen.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lab_01.R;
import com.example.lab_01.allproducts.view.AllProductsActivity;
import com.example.lab_01.favproducts.view.FavProductActivity;

public class MainActivity extends AppCompatActivity {

    public final String MAIN_ACTIVITY = "MAIN_ACTIVITY";
    Button favBtn;
    Button allBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        favBtn = findViewById(R.id.listOfFavBtn);
        allBtn = findViewById(R.id.mainUIBtn);

        favBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FavProductActivity.class);
                startActivity(intent);
            }
        });

        allBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AllProductsActivity.class);
                startActivity(intent);
            }
        });
    }

}
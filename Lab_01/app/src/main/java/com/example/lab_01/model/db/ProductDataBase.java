package com.example.lab_01.model.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.lab_01.model.pojo.Product;

@Database(entities = {Product.class}, version = 1)
public abstract class ProductDataBase extends RoomDatabase {
    public abstract ProductDAO getProductDAO();
    public static ProductDataBase getInstance(Context context) {
        return Room.databaseBuilder(context, ProductDataBase.class, "products_db").build();
    }
}

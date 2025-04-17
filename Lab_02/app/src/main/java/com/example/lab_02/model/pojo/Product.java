package com.example.lab_02.model.pojo;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "products_table")
public class Product {
    @NonNull
    public long getId() {
        return id;
    }

    public void setId(@NonNull long id) {
        this.id = id;
    }

    @PrimaryKey
    @NonNull
    private long id;

    @ColumnInfo(name = "title")
    @SerializedName("title")
    private String title;

    @ColumnInfo(name = "private")
    @SerializedName("price")
    private String price;

    @ColumnInfo(name = "description")
    @SerializedName("description")
    private String description;

    @ColumnInfo(name = "rating")
    @SerializedName("rating")
    private float rating;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @ColumnInfo(name = "thumbnail")
    @SerializedName("thumbnail")
    private String url;

    public Product(@NonNull long id, String title, String price, String description, float rating, String url) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.description = description;
        this.rating = rating;
        this.url = url;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getThumbnail() {
        return url;
    }

    public void setThumbnail(String url) {
        this.url = url;
    }
}


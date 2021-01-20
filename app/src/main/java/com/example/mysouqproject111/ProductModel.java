package com.example.mysouqproject111;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;



@Entity(tableName = "products")


public class ProductModel implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @ColumnInfo(name = "title")
   @SerializedName("title")
    private String title;



    @ColumnInfo(name = "name")
   @SerializedName("name")
    private String details;


    @ColumnInfo(name = "price")
   @SerializedName("price_final_text")
    private String price;



    @ColumnInfo(name = "Image")
   @SerializedName("avatar")
    private String image;

    @ColumnInfo(name = "description")
    @SerializedName("description")
    private String description;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @ColumnInfo(name = "quantity")
    private int quantity;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public ProductModel(String title, String details, String price, String description) {
        this.title = title;
        this.details = details;
        this.price = price;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }





    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

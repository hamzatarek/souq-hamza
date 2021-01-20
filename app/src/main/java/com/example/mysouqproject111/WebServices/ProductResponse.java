package com.example.mysouqproject111.WebServices;

import com.example.mysouqproject111.ProductModel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductResponse {
    @SerializedName("products")
    private List<ProductModel> productList;

    public List<ProductModel> getProductList() {
        return productList;
    }

    public void setProductList(List<ProductModel> productList) {
        this.productList = productList;
    }
}

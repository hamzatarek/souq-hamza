package com.example.mysouqproject111.WebServices;

import retrofit2.Call;
import retrofit2.http.GET;

public interface WebServices {

    @GET("products")
    Call<ProductResponse> getProducts();


}

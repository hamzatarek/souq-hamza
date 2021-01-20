package com.example.mysouqproject111.Room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mysouqproject111.ProductModel;

import java.util.List;

@Dao
public interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertProduct(ProductModel productModel);
    @Query("SELECT * FROM products")
    List<ProductModel> getAllProducts();

    @Query("DELETE FROM products")
    void deleteAllProducts();

    @Update
    void updateProduct(ProductModel productModel);

}

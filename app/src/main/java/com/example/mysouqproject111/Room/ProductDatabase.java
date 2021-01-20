package com.example.mysouqproject111.Room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.mysouqproject111.ProductModel;

@Database(entities = {ProductModel.class }, version = 1 , exportSchema = false)

public abstract class ProductDatabase extends RoomDatabase {

    public abstract ProductDao getProductDao();
}

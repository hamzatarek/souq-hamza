package com.example.mysouqproject111.Room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;

import com.example.mysouqproject111.Room.ProductDatabase;

public class RoomFactory {
    private static ProductDatabase database;
    public static ProductDatabase getRoomDatabase(Context context){
        if (database == null){
            database = Room.databaseBuilder(context,ProductDatabase.class,"product_database")
                    .build();
        }
        return database;
    }
}

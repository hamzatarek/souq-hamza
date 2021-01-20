package com.example.mysouqproject111.InsertAsyncTask;

import android.os.AsyncTask;

import com.example.mysouqproject111.ProductModel;
import com.example.mysouqproject111.Room.ProductDao;

import java.util.List;

public class GetProductAsyncTask extends AsyncTask<Void,Void, List<ProductModel>> {
    private ProductDao productDao;

    public GetProductAsyncTask(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    protected List<ProductModel> doInBackground(Void... voids) {
        return productDao.getAllProducts();
    }
}

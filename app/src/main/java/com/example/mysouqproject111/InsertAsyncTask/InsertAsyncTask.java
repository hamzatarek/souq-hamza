package com.example.mysouqproject111.InsertAsyncTask;

import android.os.AsyncTask;

import com.example.mysouqproject111.ProductModel;
import com.example.mysouqproject111.Room.ProductDao;

public class InsertAsyncTask extends AsyncTask<ProductModel,Void ,Void > {

    private ProductDao productDao;

    public InsertAsyncTask(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    protected Void doInBackground(ProductModel... productModels) {
        productDao.insertProduct(productModels[0]);
        return null;
    }
}

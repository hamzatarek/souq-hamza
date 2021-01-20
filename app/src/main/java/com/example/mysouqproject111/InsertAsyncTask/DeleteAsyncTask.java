package com.example.mysouqproject111.InsertAsyncTask;

import android.os.AsyncTask;

import com.example.mysouqproject111.Room.ProductDao;

public class DeleteAsyncTask extends AsyncTask<Void,Void,Void> {
    ProductDao productDao;

    public DeleteAsyncTask(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        productDao.deleteAllProducts();
        return null;
    }
}

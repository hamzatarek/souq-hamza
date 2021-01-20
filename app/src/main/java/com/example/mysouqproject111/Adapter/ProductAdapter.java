package com.example.mysouqproject111.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mysouqproject111.ProductModel;
import com.example.mysouqproject111.R;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {



    private List<ProductModel> productList;
    private Context context;
    private OnProductClickListener onProductClickListener;
    private onAddProductClickListener onAddProductClickListener;

    public interface OnProductClickListener{
        void onProductClick(View view , int position);
    }

    public interface onAddProductClickListener {
        void onAddProductClick(View view, int position);
    }

    public ProductAdapter(List<ProductModel> productList, Context context, OnProductClickListener onProductClickListener, ProductAdapter.onAddProductClickListener onAddProductClickListener) {
        this.productList = productList;
        this.context = context;
        this.onProductClickListener = onProductClickListener;
        this.onAddProductClickListener = onAddProductClickListener;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.product_rv_item,parent,false);


        return new ProductViewHolder(view);
    }



    @Override
    public int getItemCount() {
        return productList.size();
    }









    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {

        ProductModel productModel = productList.get(position);
        holder.productTitleTv.setText(productModel.getTitle());
        holder.productDetailsTv.setText(productModel.getDetails());
        holder.productPriceTv.setText(productModel.getPrice() );

        Glide.with(context).load(productModel.getImage()).into(holder.productIv);

     /*   ImageView imageView = (ImageView) findViewById(R.id.my_image_view);

        Glide.with(this).load("http://goo.gl/gEgYUd").into(imageView);
*/


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onProductClickListener.onProductClick(v, holder.getAdapterPosition());



            }
        });

        holder.addProductIb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAddProductClickListener.onAddProductClick(v,holder.getAdapterPosition());


            }
        });


    }






class ProductViewHolder extends RecyclerView.ViewHolder {



    ImageView productIv;
    TextView productTitleTv;
    TextView productDetailsTv;
    TextView productPriceTv;
    ImageButton addProductIb;


    public ProductViewHolder(@NonNull View itemView) {
        super(itemView);


       productIv = itemView.findViewById(R.id.image_test);
        productTitleTv = itemView.findViewById(R.id.product_title_tv);
        productDetailsTv = itemView.findViewById(R.id.product_details_tv);
        productPriceTv = itemView.findViewById(R.id.product_price_tv);
        addProductIb = itemView.findViewById(R.id.add_product_ib);




    }

}

}

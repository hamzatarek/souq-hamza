package com.example.mysouqproject111.fragments;

import android.app.ProgressDialog;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mysouqproject111.Adapter.ProductAdapter;
import com.example.mysouqproject111.InsertAsyncTask.InsertAsyncTask;
import com.example.mysouqproject111.ProductModel;
import com.example.mysouqproject111.R;
import com.example.mysouqproject111.Room.ProductDao;
import com.example.mysouqproject111.Room.RoomFactory;
import com.example.mysouqproject111.WebServices.ProductResponse;
import com.example.mysouqproject111.WebServices.RetrofitFactory;
import com.example.mysouqproject111.WebServices.WebServices;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    RecyclerView productRv;
    private List<ProductModel> productList = new ArrayList<>();
    private ProductAdapter productAdapter;

    private WebServices webServices;
    private ProgressDialog dialog;







    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_home, container, false);

        productRv = view.findViewById(R.id.home_rv);



        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpProgressDialog();

        setUpRecyclerView();

        dialog.show();

        callProductsAPI();



    }

    private void setUpProgressDialog() {

        dialog = new ProgressDialog(requireContext());
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
    }

    private void callProductsAPI() {
        webServices = RetrofitFactory.getRetrofit().create(WebServices.class);

        Call<ProductResponse> getProducts = webServices.getProducts();

        getProducts.enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {

                dialog.dismiss();
                productList.clear();
                productList.addAll(response.body().getProductList());
                productAdapter.notifyDataSetChanged();

                
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(requireContext(), "Error happened", Toast.LENGTH_SHORT).show();




            }
        });

    }

    private void setUpRecyclerView() {

        //volley
        //Retrofit


        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(requireContext(),2);
        productRv.setLayoutManager(layoutManager);
        productRv.addItemDecoration(new GridSpacingItemDecoration( 2,dpToPx ( 12) ,true ));
        productRv.setItemAnimator(new DefaultItemAnimator());
        productAdapter = new ProductAdapter(productList, requireContext(), new ProductAdapter.OnProductClickListener() {
            @Override
            public void onProductClick(View view, int position) {
                ProductModel selectedModel = productList.get(position);
                Bundle bundle = new Bundle();
                bundle.putSerializable("current_product", selectedModel);


                Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_productDetailsFragment, bundle);

            }
        }, new ProductAdapter.onAddProductClickListener() {
            @Override
            public void onAddProductClick(View view, int position) {

                ProductModel productModel = productList.get(position);
                productModel.setQuantity(1);
                new InsertAsyncTask(RoomFactory.getRoomDatabase(requireContext()).getProductDao()).execute(productModel);

                Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_cartFragment);

                Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show();



            }
        });
        productRv.setAdapter( productAdapter) ;
        productAdapter.notifyDataSetChanged();


    }
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
              }
            }
        }
    }
    /**
     *   Converting dp to pixel
     */
    private int dpToPx( int   dp  )  {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp ,r.getDisplayMetrics()));
    }
}
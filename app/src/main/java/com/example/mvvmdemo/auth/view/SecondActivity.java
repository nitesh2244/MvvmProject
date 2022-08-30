package com.example.mvvmdemo.auth.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;

import com.example.mvvmdemo.R;
import com.example.mvvmdemo.SharedPreferences.AppSession;
import com.example.mvvmdemo.adapter.ProductAdapter;
import com.example.mvvmdemo.auth.model.MProduct;
import com.example.mvvmdemo.auth.viewmodel.ProductViewModel;
import com.example.mvvmdemo.databinding.ActivitySecondBinding;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {
    ActivitySecondBinding binding;
    ProductViewModel productViewModel;
    ArrayList<MProduct.Data> mProducts = new ArrayList<>();
    ProductAdapter productAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_second);
        productViewModel = new ProductViewModel();
        productViewModel.init(this);
        String accessToken = AppSession.getInstance(this).getUser().getToken();
        productViewModel.getAllProducts(accessToken);

        productViewModel.observeViewAllProductsResponse().observe(this, new Observer<MProduct>() {
            @Override
            public void onChanged(MProduct mProduct) {
                mProducts.addAll(mProduct.getData());
               initRecyclerView();
               Log.d("Response",mProduct.getData().toString());
            }
        });

    }


    private void initRecyclerView() {
        binding.rvProduct.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        binding.rvProduct.setHasFixedSize(true);
        binding.rvProduct.setItemAnimator(new DefaultItemAnimator());
        productAdapter = new ProductAdapter(this,mProducts);
        binding.rvProduct.setAdapter(productAdapter);


    }



}
package com.example.mvvmdemo.auth.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.mvvmdemo.R;
import com.example.mvvmdemo.auth.model.GetUserOrder;
import com.example.mvvmdemo.auth.model.MProduct;
import com.example.mvvmdemo.auth.viewmodel.GetOrderViewModel;
import com.example.mvvmdemo.databinding.ActivityOderDetailsBinding;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collection;

public class OderDetailsActivity extends AppCompatActivity {
   ActivityOderDetailsBinding binding;
   GetOrderViewModel getOrderViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_oder_details);
        getOrderViewModel = new GetOrderViewModel();
        getOrderViewModel.init(this);
        getOrderViewModel.getOrderDetails(1);
        getOrderViewModel.observeGetOrderDetailsResponse().observe(this, new Observer<GetUserOrder>() {
            @Override
            public void onChanged(GetUserOrder getUserOrder) {
                binding.txtData.setText(new Gson().toJson(getUserOrder));
                //Log.d("orderData", getUserOrder.getData().getRestaurantDetail().toString());
            }
        });
        binding.btnNested.setOnClickListener(view -> {
            Intent intent = new Intent(OderDetailsActivity.this, NestedRecyclerViewActivity.class);
            startActivity(intent);
        });
    }
}
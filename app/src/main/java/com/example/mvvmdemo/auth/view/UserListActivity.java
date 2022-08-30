package com.example.mvvmdemo.auth.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import com.example.mvvmdemo.R;
import com.example.mvvmdemo.adapter.ProductAdapter;
import com.example.mvvmdemo.adapter.UserAdapter;
import com.example.mvvmdemo.auth.model.MProduct;
import com.example.mvvmdemo.auth.model.UserData;
import com.example.mvvmdemo.auth.viewmodel.UserDataViewModel;
import com.example.mvvmdemo.databinding.ActivityUserListImageBinding;

import java.util.ArrayList;
import java.util.Collection;

public class UserListActivity extends AppCompatActivity {
   ActivityUserListImageBinding binding;
   UserAdapter userAdapter;
   UserDataViewModel userDataViewModel;
   ArrayList<UserData> userDataArrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_list_image);
        userDataViewModel = new UserDataViewModel();
        userDataViewModel.init(this);
        userDataViewModel.getProduct();

        userDataViewModel.observeUserResponse().observe(this, new Observer<ArrayList<UserData>>() {
            @Override
            public void onChanged(ArrayList<UserData> userData) {
                userDataArrayList.addAll(userData);
                Log.d("userResponse",userData.toString());
                initRecyclerView();
            }
        });

    }

    private void initRecyclerView() {
        binding.rvList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        binding.rvList.setHasFixedSize(true);
        binding.rvList.setItemAnimator(new DefaultItemAnimator());
        userAdapter = new UserAdapter(this,userDataArrayList);
        binding.rvList.setAdapter(userAdapter);
        userAdapter.notifyDataSetChanged();


    }

}
package com.example.mvvmdemo.auth.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.database.DatabaseUtils;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.example.mvvmdemo.R;
import com.example.mvvmdemo.auth.model.LResponse;
import com.example.mvvmdemo.auth.viewmodel.UserLoginViewModel;
import com.example.mvvmdemo.databinding.ActivityUserLoginBinding;
import com.google.gson.Gson;

public class UserLoginActivity extends AppCompatActivity {
     ActivityUserLoginBinding binding;
     UserLoginViewModel userLoginViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       binding = DataBindingUtil.setContentView(this,R.layout.activity_user_login);
       userLoginViewModel = new ViewModelProvider(this).get(UserLoginViewModel.class);
       userLoginViewModel.init(this);
       binding.setViewmodel(userLoginViewModel);
       initClick();
       initResponse();


    }

    public void initClick(){
        binding.btnLogin.setOnClickListener(view -> {
            if(isValidate()) {
                userLoginViewModel. loginUser(binding.getRoot());
            }
        });
    }

    public void initResponse(){
        userLoginViewModel.observeUserLoginResponse().observe(this, new Observer<LResponse>() {
            @Override
            public void onChanged(LResponse lResponse) {
                Log.d("UserLoginResponse",lResponse.getData().getFirstName());
                binding.txtResult.setText(new Gson().toJson(lResponse));
            }
        });

        userLoginViewModel.getIsFailed().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public boolean isValidate() {
        if (TextUtils.isEmpty(binding.edtName.getText().toString().trim())) {
            binding.edtName.setError(getResources().getString(R.string.please_enter_email));
            return false;
        } else if (TextUtils.isEmpty(binding.edtPassword.getText().toString().trim())) {
            binding.edtPassword.setError(getResources().getString(R.string.please_enter_password));
            return false;
        } else {
            return true;
        }
    }
}
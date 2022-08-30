package com.example.mvvmdemo.auth.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.mvvmdemo.R;
import com.example.mvvmdemo.SharedPreferences.AppSession;
import com.example.mvvmdemo.SharedPreferences.Constant;
import com.example.mvvmdemo.auth.model.UserLoginResponse;
import com.example.mvvmdemo.databinding.ActivityLoginBinding;
import com.google.gson.Gson;

public class LoginActivity extends AppCompatActivity {

    UserLoginResponse userLoginResponse;
    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_login);
        Intent intent = getIntent();
        userLoginResponse = (UserLoginResponse) intent.getExtras().get("Data");
        binding.textView.setText(userLoginResponse.toString());
        sendReturnData();
    }

    private void sendReturnData(){

        binding.btnSave.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            userLoginResponse.setMessage("Welcome to the android");
            bundle.putSerializable("modification",userLoginResponse);
            Intent intent = new Intent();
            intent.putExtras(bundle);
            setResult(Activity.RESULT_OK,intent);
            finish();
        });

    }


}
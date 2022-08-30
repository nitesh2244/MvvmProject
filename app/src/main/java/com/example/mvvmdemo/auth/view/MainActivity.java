package com.example.mvvmdemo.auth.view;
import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationManagerCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.example.mvvmdemo.R;
import com.example.mvvmdemo.SharedPreferences.AppSession;
import com.example.mvvmdemo.SharedPreferences.Constant;
import com.example.mvvmdemo.auth.model.UserLoginResponse;
import com.example.mvvmdemo.auth.viewmodel.LoginViewModel;
import com.example.mvvmdemo.databinding.ActivityMainBinding;
import com.firebase.ui.auth.data.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    LoginViewModel loginViewModelClass;
    public static final int REQUEST_CODE_MESSAGE = 2126;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        loginViewModelClass = new LoginViewModel();
       // loginViewModelClass = new ViewModelProvider(this).get(LoginViewModel.class);
        loginViewModelClass.init(this);
        binding.setViewmodel(loginViewModelClass);
        //AppSession.getInstance(MainActivity.this).setValue(Constant.IS_USER_LOGIN, "false");
//            loginViewModelClass.getLoginResult().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(String s) {
//               binding.tvResult.setText(s);
//
//            }
//        });

        if(AppSession.getInstance(MainActivity.this).getValue(Constant.IS_USER_LOGIN).equalsIgnoreCase("true")){
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);
        }

        loginViewModelClass.observeLoginResponse().observe(this, new Observer<UserLoginResponse>() {
            @Override
            public void onChanged(UserLoginResponse userLoginResponse) {
                Log.d("setData",userLoginResponse.getData().toString());
                if( userLoginResponse.getData().getUserEmail() != null) {
                    AppSession.getInstance(MainActivity.this).putObject(Constant.USER, userLoginResponse);
                    AppSession.getInstance(MainActivity.this).setValue(Constant.IS_USER_LOGIN, "true");
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("Data",userLoginResponse);
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    intent.putExtras(bundle);
                    overridePendingTransition(0, 0);
                    startActivityForResult(intent,REQUEST_CODE_MESSAGE);
                }

            }
        });
        binding.btnLogin.setOnClickListener(view -> {
            if(isValidate()){
                Log.d("Validate", "onCreate: ");
                loginViewModelClass.login(binding.getRoot());

            }

        });
        binding.btnUser.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, UserListActivity.class);
            startActivity(intent);
        });

        binding.btnOrder.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, OderDetailsActivity.class);
            startActivity(intent);
        });

        binding.btnUpload.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, PostActivity.class);
            startActivity(intent);
        });

        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete(@NonNull Task<String> task) {
                Log.d("FirebaseToken", "onComplete: "+task.getResult());
            }
        });
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//            AppSession.getInstance(MainActivity.this).setValue(Constant.IS_USER_LOGIN, "true");
//            startActivity(new Intent(MainActivity.this, LoginActivity.class));
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE_MESSAGE:
            if (resultCode == Activity.RESULT_OK) {
                UserLoginResponse returnData = (UserLoginResponse) data.getExtras().get("modification");
                binding.txtResult.setText(returnData.getMessage());
                Log.d(TAG, "ReturnData:"+returnData);
            }
        }
    }


    public boolean isValidate() {
        if (TextUtils.isEmpty(binding.edtName.getText().toString().trim())) {
            binding.edtName.setError(getResources().getString(R.string.please_enter_name));
            return false;
        } else if (TextUtils.isEmpty(binding.edtPassword.getText().toString().trim())) {
            binding.edtPassword.setError(getResources().getString(R.string.please_enter_password));
            return false;
        } else {
            return true;
        }
    }


}
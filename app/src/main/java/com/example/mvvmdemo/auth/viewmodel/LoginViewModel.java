package com.example.mvvmdemo.auth.viewmodel;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvmdemo.SharedPreferences.AppSession;
import com.example.mvvmdemo.SharedPreferences.Constant;
import com.example.mvvmdemo.auth.model.LoginBody;
import com.example.mvvmdemo.auth.model.UserLoginResponse;
import com.example.mvvmdemo.auth.repository.LoginRepository;
import com.example.mvvmdemo.callback.LoginResponseListener;
import com.google.firebase.messaging.FirebaseMessaging;

public class LoginViewModel extends ViewModel {
  LoginRepository loginRepository;
  public String username = "", password = "";
  private Context context;
  private MutableLiveData<UserLoginResponse> customerLoginResponseModel;
  MutableLiveData<Boolean> isConnecting = new MutableLiveData<>();
  MutableLiveData<String>  isFailed = new MutableLiveData<>();

   public LiveData<String> getIsFailed() {
        return isFailed;
    }

    public LiveData<Boolean> getIsConnecting() {
        return isConnecting;
    }

    public LiveData<UserLoginResponse> observeLoginResponse() {
        if (customerLoginResponseModel == null) {
            customerLoginResponseModel = new MutableLiveData<>();
        }
        return customerLoginResponseModel;
    }

    public void init(Context context) {
        this.context = context;
        if (customerLoginResponseModel != null) {
            return;
        }
        loginRepository = LoginRepository.getInstance();
    }

    public void login(View view){
        isConnecting.setValue(true);
        LoginBody loginBody = new LoginBody();
        loginBody.setUsername(username);
        loginBody.setPassword(password);

        loginRepository.loginRemote(loginBody , loginResponseListener);

    }
    LoginResponseListener loginResponseListener = new LoginResponseListener() {


        @Override
        public void onSuccessResponse(UserLoginResponse userLoginResponse) {
            customerLoginResponseModel.postValue(userLoginResponse);

        }

        @Override
        public void onError(String error) {
            isFailed.setValue(error);
        }

    };
}



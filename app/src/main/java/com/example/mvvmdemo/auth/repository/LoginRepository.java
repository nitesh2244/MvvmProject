package com.example.mvvmdemo.auth.repository;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.example.mvvmdemo.auth.model.LoginBody;
import com.example.mvvmdemo.auth.model.UserLoginResponse;
import com.example.mvvmdemo.callback.LoginResponseListener;
import com.example.mvvmdemo.network_remote.Apiinterface;
import com.example.mvvmdemo.network_remote.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginRepository {

    private static LoginRepository loginRepository;
    private final Apiinterface apiInterface;
    private final MutableLiveData<UserLoginResponse> userLoginResponseMutableLiveData = new MutableLiveData<>();

    public LoginRepository() {
        apiInterface = RetrofitService.getInstance().create(Apiinterface.class);

    }

    public MutableLiveData<UserLoginResponse> loginRemote(LoginBody loginBody, LoginResponseListener loginResponseListener){
    Call<UserLoginResponse> initiateLogin = apiInterface.loginApi(loginBody);

    initiateLogin.enqueue(new Callback<UserLoginResponse>() {
        @Override
        public void onResponse(Call<UserLoginResponse> call, Response<UserLoginResponse> response) {
            if(response.isSuccessful() && response.body() != null){
                loginResponseListener.onSuccessResponse(response.body());
            } else {
                loginResponseListener.onError("Cannot get User data Key.");
            }
        }

        @Override
        public void onFailure(Call<UserLoginResponse> call, Throwable t) {
            loginResponseListener.onError(t.getMessage());
            userLoginResponseMutableLiveData.postValue(null);
        }

    });
   return userLoginResponseMutableLiveData;

  }

    public static LoginRepository getInstance() {

        if (loginRepository == null) {

            loginRepository = new LoginRepository();
        }
        return loginRepository;
    }

}



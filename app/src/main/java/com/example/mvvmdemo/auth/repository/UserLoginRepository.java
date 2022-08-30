package com.example.mvvmdemo.auth.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.mvvmdemo.auth.model.LBody;
import com.example.mvvmdemo.auth.model.LResponse;
import com.example.mvvmdemo.auth.model.LoginBody;
import com.example.mvvmdemo.auth.model.UserLoginResponse;
import com.example.mvvmdemo.callback.LoginResponseListener;
import com.example.mvvmdemo.callback.UserLoginResponseListener;
import com.example.mvvmdemo.network_remote.Apiinterface;
import com.example.mvvmdemo.network_remote.RetrofitService;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserLoginRepository {

    private static UserLoginRepository userLoginRepository;
    private final Apiinterface apiInterface;
    private final MutableLiveData<LResponse> lResponseMutableLiveData = new MutableLiveData<>();

    public UserLoginRepository() {
        apiInterface = RetrofitService.getUser().create(Apiinterface.class);
    }

    public MutableLiveData<LResponse> loginLocal(LBody lBody, UserLoginResponseListener userLoginResponseListener){
         Log.d("UserBody",new Gson().toJson(lBody));
        Call<LResponse> call = apiInterface.loginCall(lBody);

        call.enqueue(new Callback<LResponse>() {
            @Override
            public void onResponse(Call<LResponse> call, Response<LResponse> response) {
                if(response.isSuccessful() && response.body() != null){
                    userLoginResponseListener.onSuccessResponse(response.body());
                    Log.d("UserRepo",response.toString());
                } else {
                    userLoginResponseListener.onError("Cannot get User data Key.");
                }
            }

            @Override
            public void onFailure(Call<LResponse> call, Throwable t) {
                userLoginResponseListener.onError(t.getMessage());
                lResponseMutableLiveData.postValue(null);
            }
        });
        return lResponseMutableLiveData;
    }

    public static UserLoginRepository getInstance() {

        if (userLoginRepository == null) {

            userLoginRepository = new UserLoginRepository();
        }
        return userLoginRepository;
    }


}

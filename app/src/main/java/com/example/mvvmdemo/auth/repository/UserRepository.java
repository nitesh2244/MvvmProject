package com.example.mvvmdemo.auth.repository;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.mvvmdemo.auth.model.MProduct;
import com.example.mvvmdemo.auth.model.UserData;
import com.example.mvvmdemo.callback.ProductResponseListener;
import com.example.mvvmdemo.callback.UserResponseListener;
import com.example.mvvmdemo.network_remote.Apiinterface;
import com.example.mvvmdemo.network_remote.RetrofitService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {

    private static UserRepository userRepository;
    private final Apiinterface apiinterface;
    private final MutableLiveData<ArrayList<UserData>> userDataMutableLiveData = new MutableLiveData<>();


    public UserRepository() {
        apiinterface = RetrofitService.getRetrofit().create(Apiinterface.class);
    }

    public  MutableLiveData<ArrayList<UserData>> getProduct(UserResponseListener userResponseListener) {

        Call<ArrayList<UserData>> call = apiinterface.getUserData();
        call.enqueue(new Callback <ArrayList<UserData>>() {
            @Override
            public void onResponse(Call<ArrayList<UserData>> call, Response<ArrayList<UserData>> response) {
                Log.d(TAG, "UserData: "+response);
                if(response.isSuccessful()){
                    userResponseListener.onSuccessResponse(response.body());
                } else {
                    userResponseListener.onError("Cannot get User data Key.");
                }
            }

            @Override
            public void onFailure(Call<ArrayList<UserData>> call, Throwable t) {
                Log.d(TAG, "UserData: "+t.getMessage());
                userResponseListener.onError(t.getMessage());
                userDataMutableLiveData.postValue(null);
            }
        });

     return userDataMutableLiveData;
    }

    public static UserRepository getInstance(){

        if (userRepository == null){

            userRepository = new UserRepository();
        }
        return userRepository;
    }
}

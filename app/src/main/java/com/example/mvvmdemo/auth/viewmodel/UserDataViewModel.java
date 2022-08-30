package com.example.mvvmdemo.auth.viewmodel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mvvmdemo.auth.model.UserData;
import com.example.mvvmdemo.auth.repository.UserRepository;
import com.example.mvvmdemo.callback.UserResponseListener;

import java.util.ArrayList;

public class UserDataViewModel {

    UserRepository userRepository;
    private Context context;
    private MutableLiveData<ArrayList<UserData>> userDataMutableLiveData;
    MutableLiveData<Boolean> isConnecting = new MutableLiveData<>();
    MutableLiveData<String>  isFailed = new MutableLiveData<>();

    public LiveData<String> getIsFailed() {
        return isFailed;
    }

    public LiveData<Boolean> getIsConnecting() {
        return isConnecting;
    }

    public MutableLiveData<ArrayList<UserData>> observeUserResponse(){
        if(userDataMutableLiveData==null){
            userDataMutableLiveData = new MutableLiveData<>();
        }

        return  userDataMutableLiveData;
    }

    public void init(Context context) {
        this.context = context;
        if (userDataMutableLiveData != null) {
            return;
        }
        userRepository = userRepository.getInstance();
    }

    public void getProduct(){
        userRepository.getProduct(this.userResponseListener);

    }

    UserResponseListener userResponseListener = new UserResponseListener() {
        @Override
        public void onSuccessResponse(ArrayList<UserData> userData) {
            userDataMutableLiveData.postValue(userData);
        }

        @Override
        public void onError(String error) {
            isFailed.setValue(error);
        }
    };

}

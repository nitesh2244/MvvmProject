package com.example.mvvmdemo.auth.viewmodel;

import android.content.Context;
import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvmdemo.auth.model.LBody;
import com.example.mvvmdemo.auth.model.LResponse;
import com.example.mvvmdemo.auth.model.LoginBody;
import com.example.mvvmdemo.auth.model.UserLoginResponse;
import com.example.mvvmdemo.auth.repository.LoginRepository;
import com.example.mvvmdemo.auth.repository.UserLoginRepository;
import com.example.mvvmdemo.callback.UserLoginResponseListener;

public class UserLoginViewModel extends ViewModel {

    UserLoginRepository userLoginRepository;
    public String email = "", password = "";
    private Context context;
    private MutableLiveData<LResponse> lResponseMutableLiveData;
    MutableLiveData<Boolean> isConnecting = new MutableLiveData<>();
    MutableLiveData<String>  isFailed = new MutableLiveData<>();

    public LiveData<String> getIsFailed() {
        return isFailed;
    }

    public LiveData<Boolean> getIsConnecting() {
        return isConnecting;
    }


    public LiveData<LResponse> observeUserLoginResponse() {
        if (lResponseMutableLiveData == null) {
            lResponseMutableLiveData = new MutableLiveData<>();
        }
        return lResponseMutableLiveData;
    }

    public void init(Context context) {
        this.context = context;
        if (lResponseMutableLiveData != null) {
            return;
        }
        userLoginRepository = UserLoginRepository.getInstance();
    }


    public void loginUser (View view){
        isConnecting.setValue(true);
        LBody lBody =  new LBody();
        lBody.setEmail(email);
        lBody.setPassword(password);

        userLoginRepository.loginLocal(lBody , userLoginResponseListener);

    }

    UserLoginResponseListener userLoginResponseListener = new UserLoginResponseListener() {
        @Override
        public void onSuccessResponse(LResponse lResponse) {
            lResponseMutableLiveData.postValue(lResponse);

        }

        @Override
        public void onError(String error) {
           isFailed.setValue(error);
        }
    };
}

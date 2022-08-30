package com.example.mvvmdemo.callback;

import com.example.mvvmdemo.auth.model.UserLoginResponse;

public interface LoginResponseListener {
    void onSuccessResponse(UserLoginResponse userLoginResponse);
    void onError(String error);
}

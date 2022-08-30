package com.example.mvvmdemo.callback;

import com.example.mvvmdemo.auth.model.LResponse;
import com.example.mvvmdemo.auth.model.UserLoginResponse;

public interface UserLoginResponseListener {
    void onSuccessResponse(LResponse lResponse);
    void onError(String error);
}

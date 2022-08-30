package com.example.mvvmdemo.callback;

import com.example.mvvmdemo.auth.model.UserData;

import java.util.ArrayList;

public interface UserResponseListener {

    void onSuccessResponse(ArrayList<UserData> userData);
    void onError(String error);
}

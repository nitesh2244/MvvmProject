package com.example.mvvmdemo.callback;

import com.example.mvvmdemo.auth.model.MProduct;
import com.example.mvvmdemo.auth.model.UserLoginResponse;

public interface ProductResponseListener {
    void onSuccessResponse(MProduct mProduct);
    void onError(String error);
}

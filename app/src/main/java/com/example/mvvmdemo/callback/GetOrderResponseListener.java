package com.example.mvvmdemo.callback;

import com.example.mvvmdemo.auth.model.GetUserOrder;
import com.example.mvvmdemo.auth.model.MProduct;

public interface GetOrderResponseListener {
    void onSuccessResponse(GetUserOrder getUserOrder);
    void onError(String error);
}

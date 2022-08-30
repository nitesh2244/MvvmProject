package com.example.mvvmdemo.callback;

import com.example.mvvmdemo.auth.model.MUpload;
import com.example.mvvmdemo.auth.model.UserData;

import java.util.ArrayList;

public interface UploadImageResponseListener {

    void onSuccessResponse(MUpload mUpload);
    void onError(String error);
}

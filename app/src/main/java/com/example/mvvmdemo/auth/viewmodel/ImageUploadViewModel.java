package com.example.mvvmdemo.auth.viewmodel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvmdemo.auth.model.MUpload;
import com.example.mvvmdemo.auth.model.UserLoginResponse;
import com.example.mvvmdemo.auth.repository.ImageUploadRepository;
import com.example.mvvmdemo.auth.repository.LoginRepository;
import com.example.mvvmdemo.callback.UploadImageResponseListener;

import java.io.File;

public class ImageUploadViewModel extends ViewModel {

    private Context context;
    private ImageUploadRepository imageUploadRepository;
    private final MutableLiveData<String> isFailed = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isConnecting = new MutableLiveData<>();
    MutableLiveData<MUpload> mUploadMutableLiveData = new MutableLiveData<>();

    public LiveData<String> getIsFailed() {
        return isFailed;
    }

    public LiveData<Boolean> getIsConnecting() {
        return isConnecting;
    }

    public LiveData<MUpload> observeUploadResponse() {
        if (mUploadMutableLiveData == null) {
            mUploadMutableLiveData = new MutableLiveData<>();
        }
        return mUploadMutableLiveData;
    }

    public void init(Context context) {
        this.context = context;
        if (mUploadMutableLiveData != null) {
            return;
        }
        imageUploadRepository = imageUploadRepository.getInstance();
    }

    public void callUploadImage(File file){
        ImageUploadRepository imageUploadRepository =  new ImageUploadRepository();
        imageUploadRepository.getImage(file, uploadImageResponseListener);
    }

    UploadImageResponseListener uploadImageResponseListener = new UploadImageResponseListener() {
        @Override
        public void onSuccessResponse(MUpload mUpload) {
            mUploadMutableLiveData.postValue(mUpload);
        }

        @Override
        public void onError(String error) {
          isFailed.setValue(error);
        }
    };
}

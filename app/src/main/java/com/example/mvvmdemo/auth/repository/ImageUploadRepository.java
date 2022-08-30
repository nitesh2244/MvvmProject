package com.example.mvvmdemo.auth.repository;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.mvvmdemo.auth.model.MProduct;
import com.example.mvvmdemo.auth.model.MUpload;
import com.example.mvvmdemo.auth.model.UserData;
import com.example.mvvmdemo.callback.UploadImageResponseListener;
import com.example.mvvmdemo.network_remote.Apiinterface;
import com.example.mvvmdemo.network_remote.RetrofitService;
import com.google.gson.Gson;

import java.io.File;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Multipart;

public class ImageUploadRepository {

     private static ImageUploadRepository imageUploadRepository;
     private final Apiinterface apiinterface;
     private final MutableLiveData<MUpload> mUploadMutableLiveData = new MutableLiveData<>();


     public ImageUploadRepository() {
         apiinterface = RetrofitService.getRetro().create(Apiinterface.class);
     }

    public MutableLiveData<MUpload> getImage( File file, UploadImageResponseListener uploadImageResponseListener){
        RequestBody requestBody =RequestBody.create(MediaType.parse("image/jpeg"),file);
        MultipartBody.Part part = MultipartBody.Part.createFormData("image",file.getName(),requestBody);
        Call<MUpload> call = apiinterface.uploadImage(part);
        call.enqueue(new Callback<MUpload>() {
            @Override
            public void onResponse(Call<MUpload> call, Response<MUpload> response) {
                if(response.isSuccessful()){
                    uploadImageResponseListener.onSuccessResponse(response.body());
                    Log.d(TAG, "onActivityResult: "+new Gson().toJson(response.body()));
                } else {
                    uploadImageResponseListener.onError("There some issue...!");
                }
            }

            @Override
            public void onFailure(Call<MUpload> call, Throwable t) {
                uploadImageResponseListener.onError(t.getMessage());
                mUploadMutableLiveData.postValue(null);
            }

        });
        return mUploadMutableLiveData;
    }
    public static ImageUploadRepository getInstance() {

        if (imageUploadRepository == null) {

            imageUploadRepository = new ImageUploadRepository();
        }
        return imageUploadRepository;
    }
}

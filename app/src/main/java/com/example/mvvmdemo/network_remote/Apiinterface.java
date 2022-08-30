package com.example.mvvmdemo.network_remote;

import com.example.mvvmdemo.auth.model.GetUserOrder;
import com.example.mvvmdemo.auth.model.LBody;
import com.example.mvvmdemo.auth.model.LResponse;
import com.example.mvvmdemo.auth.model.LoginBody;
import com.example.mvvmdemo.auth.model.MProduct;
import com.example.mvvmdemo.auth.model.MUpload;
import com.example.mvvmdemo.auth.model.UserData;
import com.example.mvvmdemo.auth.model.UserLoginResponse;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface Apiinterface {

    @POST("swiggy/authJWT")
    Call<UserLoginResponse> loginApi(@Body LoginBody loginBody);

    @POST("demo/api/login/index_post")
    Call<LResponse> loginCall(@Body LBody lBody);

    @GET("swiggy/getAllProducts")
    Call<MProduct> getProductList();

    @GET("swiggy/getOrderDetail?")
    Call<GetUserOrder> getOrderDetails(@Query("orderId") int orderId);

    @GET("photos")
    Call<ArrayList<UserData>> getUserData();

    @Multipart
    @POST("api/v1/uploadToEC2")
    Call<MUpload> uploadImage(@Part MultipartBody.Part image);
}

package com.example.mvvmdemo.auth.repository;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.example.mvvmdemo.auth.model.MProduct;
import com.example.mvvmdemo.auth.model.UserLoginResponse;
import com.example.mvvmdemo.callback.ProductResponseListener;
import com.example.mvvmdemo.network_remote.Apiinterface;
import com.example.mvvmdemo.network_remote.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductRepository {
    private static ProductRepository productRepository;
    private final Apiinterface apiInterface;
    private final MutableLiveData<MProduct> mProductMutableLiveData = new MutableLiveData<>();


    public ProductRepository() {
        apiInterface = RetrofitService.getInstance().create(Apiinterface.class);

    }


    public MutableLiveData<MProduct> getProduct( String accessToken, ProductResponseListener productResponseListener){

        Call<MProduct> call = apiInterface.getProductList();

        call.enqueue(new Callback<MProduct>() {
            @Override
            public void onResponse(Call<MProduct> call, Response<MProduct> response) {
                if(response.isSuccessful()){
                    productResponseListener.onSuccessResponse(response.body());
                } else {
                    productResponseListener.onError("Cannot get User data Key.");
                }
            }

            @Override
            public void onFailure(Call<MProduct> call, Throwable t) {
                productResponseListener.onError(t.getMessage());
                mProductMutableLiveData.postValue(null);
            }
        });
        return mProductMutableLiveData;
    }

    public static ProductRepository getInstance() {

        if (productRepository == null) {

            productRepository = new ProductRepository();
        }
        return productRepository;
    }
}

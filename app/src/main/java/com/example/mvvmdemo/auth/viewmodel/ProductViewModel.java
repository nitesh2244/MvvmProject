package com.example.mvvmdemo.auth.viewmodel;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mvvmdemo.auth.model.MProduct;
import com.example.mvvmdemo.auth.repository.ProductRepository;
import com.example.mvvmdemo.callback.ProductResponseListener;

public class ProductViewModel {
    private String accessToken;
    ProductRepository productRepository;
    private Context context;
    private MutableLiveData<MProduct> mProductMutableLiveData;
    MutableLiveData<Boolean> isConnecting = new MutableLiveData<>();
    MutableLiveData<String>  isFailed = new MutableLiveData<>();

    public LiveData<String> getIsFailed() {
        return isFailed;
    }

    public LiveData<Boolean> getIsConnecting() {
        return isConnecting;
    }

    public MutableLiveData<MProduct> observeViewAllProductsResponse(){
        if(mProductMutableLiveData==null){
            mProductMutableLiveData = new MutableLiveData<>();
        }

        return  mProductMutableLiveData;
    }

    public void init(Context context) {
        this.context = context;
        if (mProductMutableLiveData != null) {
            return;
        }
        productRepository = productRepository.getInstance();
    }

    public void getAllProducts(String accessToken){
        productRepository.getProduct(this.accessToken,productResponseListener);

    }

    ProductResponseListener productResponseListener = new ProductResponseListener() {
        @Override
        public void onSuccessResponse(MProduct mProduct) {
         mProductMutableLiveData.postValue(mProduct);
        }

        @Override
        public void onError(String error) {
            isFailed.setValue(error);
        }
    };

}

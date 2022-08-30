package com.example.mvvmdemo.auth.viewmodel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mvvmdemo.auth.model.GetUserOrder;
import com.example.mvvmdemo.auth.repository.OrderDetailRepository;
import com.example.mvvmdemo.callback.GetOrderResponseListener;

public class GetOrderViewModel {
    OrderDetailRepository  orderDetailRepository;
    private Context context;
    private MutableLiveData <GetUserOrder> getUserOrderMutableLiveData;
    MutableLiveData<Boolean> isConnecting = new MutableLiveData<>();
    MutableLiveData<String> isFailed = new MutableLiveData<>();

    public LiveData<String> getIsFailed(){
        return getIsFailed();
    }
    public LiveData<Boolean> getIsConnecting(){
        return getIsConnecting();
    }

    public MutableLiveData<GetUserOrder> observeGetOrderDetailsResponse(){

        if(getUserOrderMutableLiveData==null){
            getUserOrderMutableLiveData = new MutableLiveData<>();
        }
        return getUserOrderMutableLiveData;
    }
    public  void init(Context context){
        this.context = context;
        if(getUserOrderMutableLiveData != null){
            return;
        }
       orderDetailRepository = orderDetailRepository.getInstance();
    }
    public void getOrderDetails(int orderId){
        orderDetailRepository.getOrderDetails(1,getOrderResponseListener);
    }

    GetOrderResponseListener getOrderResponseListener = new GetOrderResponseListener() {
        @Override
        public void onSuccessResponse(GetUserOrder getUserOrder) {
            getUserOrderMutableLiveData.postValue(getUserOrder);
        }

        @Override
        public void onError(String error) {
            isFailed.setValue(error);
        }
    };
}

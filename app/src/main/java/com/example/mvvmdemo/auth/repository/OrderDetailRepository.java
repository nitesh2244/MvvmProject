package com.example.mvvmdemo.auth.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.mvvmdemo.auth.model.GetUserOrder;
import com.example.mvvmdemo.callback.GetOrderResponseListener;
import com.example.mvvmdemo.network_remote.Apiinterface;
import com.example.mvvmdemo.network_remote.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderDetailRepository {

    private static OrderDetailRepository orderDetailRepository;
    private final Apiinterface apiInterface;
    private final MutableLiveData<GetUserOrder> getUserOrderMutableLiveData = new MutableLiveData<>();


    public OrderDetailRepository() {
        apiInterface = RetrofitService.getInstance().create(Apiinterface.class);
    }

    public MutableLiveData<GetUserOrder> getOrderDetails (int orderId, GetOrderResponseListener getOrderResponseListener){

        Call<GetUserOrder> call = apiInterface.getOrderDetails(orderId);

        call.enqueue(new Callback<GetUserOrder>() {
            @Override
            public void onResponse(Call<GetUserOrder> call, Response<GetUserOrder> response) {
                if(response.isSuccessful()){
                    getOrderResponseListener.onSuccessResponse(response.body());
                } else {
                    getOrderResponseListener.onError("Cannot get Order data Key.");
                }
            }

            @Override
            public void onFailure(Call<GetUserOrder> call, Throwable t) {
                getOrderResponseListener.onError(t.getMessage());
                getUserOrderMutableLiveData.postValue(null);
            }
        });
        return getUserOrderMutableLiveData;
    }

    public static OrderDetailRepository getInstance() {

        if (orderDetailRepository == null) {

            orderDetailRepository = new OrderDetailRepository();
        }
        return orderDetailRepository;
    }
}

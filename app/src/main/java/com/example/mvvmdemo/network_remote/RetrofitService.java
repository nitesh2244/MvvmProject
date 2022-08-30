package com.example.mvvmdemo.network_remote;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {

    private static final String url = "http://43.205.18.211:9090/";

    public static Retrofit retrofit;
    public static Retrofit retrofit2;
    public static Retrofit retrofit3;
    public static Retrofit retrofit4;
    private static final Gson gson = new GsonBuilder().setLenient().create();

    public static Retrofit getInstance(){

        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }

    public static Retrofit getRetrofit(){


        if(retrofit2 == null){
            retrofit2 = new Retrofit.Builder()
                    .baseUrl("https://jsonplaceholder.typicode.com/")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit2;
    }

    public static Retrofit getRetro(){


        if(retrofit3 == null){
            retrofit3 = new Retrofit.Builder()
                    .baseUrl("http://43.205.18.211:8080/")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit3;
    }

    public static Retrofit getUser(){

        if(retrofit4 == null){
            retrofit4 = new Retrofit.Builder()
                    .baseUrl("http://192.168.29.90:80/")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit4;
    }
//    private static final OkHttpClient httpClient = new OkHttpClient.Builder()
//            .addInterceptor(chain -> {
//                Request original = chain.request();
//                Request request = original.newBuilder()
//                        .addHeader("deviceType", "Android")
//                        .build();
//                return chain.proceed(request);
//            })
//            .connectTimeout(60, TimeUnit.SECONDS)
//            .readTimeout(60, TimeUnit.SECONDS)
//            .writeTimeout(60, TimeUnit.SECONDS).build();
//
//    private static final Gson gson = new GsonBuilder().setLenient().create();
//
//    private static final Retrofit.Builder builder = new Retrofit.Builder().baseUrl(url);
//
//
//    public static <S> S createService(Class<S> serviceClass) {
//        Retrofit retrofit = builder.client(httpClient)
//                .addConverterFactory(GsonConverterFactory.create(gson))
//                .build();
//
//        return retrofit.create(serviceClass);
//    }
}

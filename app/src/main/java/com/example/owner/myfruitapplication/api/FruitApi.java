package com.example.owner.myfruitapplication.api;

import android.arch.lifecycle.LiveData;

import com.example.owner.myfruitapplication.dto.ApiResponse;
import com.example.owner.myfruitapplication.dto.FruitResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FruitApi {

    // fetch the list of fruit available
    @GET("/fmtvp/recruit-test-data/master/data.json")
    LiveData<ApiResponse<FruitResponse>> browseRepoLiveData(@Query("page") int page, @Query("per_page") int limit);

    // record statistics as users interact with the app – any network request
    @GET("/fmtvp/recruit-test-data/master/stats")
    Call<ApiResponse<FruitResponse>> auditNetworkRequest(@Query("event") String event, @Query("data") String data);

    // record statistics as users interact with the app –  whenever a screen is shown
    @GET("/fmtvp/recruit-test-data/master/stats")
    Call<ApiResponse<FruitResponse>> auditScreenShown(@Query("display") String display, @Query("data") String data);

    // record statistics as users interact with the app – whenever there is a raised exception or application crash
    @GET("/fmtvp/recruit-test-data/master/stats")
    Call<ApiResponse<FruitResponse>> auditExceptionCrash(@Query("error") String error, @Query("data") String data);
}

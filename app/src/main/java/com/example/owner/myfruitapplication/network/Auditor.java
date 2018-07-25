package com.example.owner.myfruitapplication.network;

import android.util.Log;

import com.example.owner.myfruitapplication.api.FruitApi;
import com.example.owner.myfruitapplication.dto.ApiResponse;
import com.example.owner.myfruitapplication.dto.FruitResponse;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Auditor {

    @Inject
    FruitApi api;

    private static final String TAG = "Auditor";
    private long startTime;
    private long elapsedTime;

    public Auditor() {
    }

    public void startTimer(){
        startTime = System.currentTimeMillis();
    }

    public void stopTimer(){
        elapsedTime = System.currentTimeMillis() - startTime;
    }

    public void auditNetworkRequest() {
        api.auditNetworkRequest("load", elapsedTime + "").enqueue(new Callback<ApiResponse<FruitResponse>>() {
            @Override
            public void onResponse(Call<ApiResponse<FruitResponse>> call, Response<ApiResponse<FruitResponse>> response) {
                if(response.isSuccessful()) {
                    auditNetworkRequestResponse(response.body().toString());
                    Log.i(TAG, "post submitted to API." + response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<FruitResponse>> call, Throwable t) {
                Log.e(TAG, "Unable to submit post to API.");
            }

        });
    }

    private void auditNetworkRequestResponse(String response) {
        //TODO
    }

    public void auditScreenShown() {
        api.auditScreenShown("display", elapsedTime + "").enqueue(new Callback<ApiResponse<FruitResponse>>() {
            @Override
            public void onResponse(Call<ApiResponse<FruitResponse>> call, Response<ApiResponse<FruitResponse>> response) {
                if(response.isSuccessful()) {
                    auditScreenShownResponse(response.body().toString());
                    Log.i(TAG, "post submitted to API." + response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<FruitResponse>> call, Throwable t) {
                Log.e(TAG, "Unable to submit post to API.");
            }

        });
    }

    private void auditScreenShownResponse(String response) {
        //TODO
    }

    public void auditExceptionCrash(String title, String body) {
        api.auditExceptionCrash(title, body).enqueue(new Callback<ApiResponse<FruitResponse>>() {
            @Override
            public void onResponse(Call<ApiResponse<FruitResponse>> call, Response<ApiResponse<FruitResponse>> response) {
                if(response.isSuccessful()) {
                    auditExceptionCrashResponse(response.body().toString());
                    Log.i(TAG, "post submitted to API." + response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<FruitResponse>> call, Throwable t) {
                Log.e(TAG, "Unable to submit post to API.");
            }

        });
    }

    private void auditExceptionCrashResponse(String response) {
        //TODO
    }
}

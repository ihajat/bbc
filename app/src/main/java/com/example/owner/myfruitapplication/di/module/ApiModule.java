package com.example.owner.myfruitapplication.di.module;


import com.example.owner.myfruitapplication.api.FruitApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class ApiModule {
    @Provides
    @Singleton
    public FruitApi providesCatalogApi(Retrofit retrofit) {
        return retrofit.create(FruitApi.class);
    }
}

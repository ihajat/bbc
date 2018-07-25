package com.example.owner.myfruitapplication.di.module;

import com.example.owner.myfruitapplication.api.FruitApi;
import com.example.owner.myfruitapplication.network.Auditor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AuditorModule {
    @Provides
    @Singleton
    public Auditor providesAuditor(FruitApi fruitApi) {
        return new Auditor();
    }
}

package com.example.owner.myfruitapplication.application;

import android.app.Application;

import com.example.owner.myfruitapplication.di.AppComponent;
import com.example.owner.myfruitapplication.di.DaggerAppComponent;
import com.example.owner.myfruitapplication.di.module.ApiModule;
import com.example.owner.myfruitapplication.di.module.AppModule;
import com.example.owner.myfruitapplication.di.module.DaoModule;
import com.example.owner.myfruitapplication.di.module.NetModule;
import com.example.owner.myfruitapplication.di.module.RepositoryModule;
import com.example.owner.myfruitapplication.exceptions.LoggingExceptionHandler;


public class MyApplication extends Application {

    private static AppComponent appComponent;

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    public void onCreate(){
        super.onCreate();
        new LoggingExceptionHandler(this);

        appComponent = DaggerAppComponent.builder().
                appModule(new AppModule(this)).
                apiModule(new ApiModule()).
                netModule(new NetModule("https://raw.githubusercontent.com")).
                daoModule(new DaoModule()).
                repositoryModule(new RepositoryModule()).
                build();

    }
}

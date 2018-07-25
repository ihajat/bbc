package com.example.owner.myfruitapplication.di;

import android.content.Context;

import com.example.owner.myfruitapplication.di.module.ApiModule;
import com.example.owner.myfruitapplication.di.module.AppModule;
import com.example.owner.myfruitapplication.di.module.AuditorModule;
import com.example.owner.myfruitapplication.di.module.DaoModule;
import com.example.owner.myfruitapplication.di.module.NetModule;
import com.example.owner.myfruitapplication.di.module.RepositoryModule;
import com.example.owner.myfruitapplication.viewmodel.MainActivityViewModel;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(
        modules = {AppModule.class, NetModule.class, RepositoryModule.class, ApiModule.class, DaoModule.class, AuditorModule.class}
)
public interface AppComponent {

    public void inject(MainActivityViewModel viewModelModule);

    public void inject(Context content);


}

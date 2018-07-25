package com.example.owner.myfruitapplication.di.module;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.example.owner.myfruitapplication.dao.FruitDao;
import com.example.owner.myfruitapplication.dao.FruitDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DaoModule {
    @Provides
    @Singleton
    public FruitDao provideFruitDao(Application app) {
        FruitDatabase db = Room.databaseBuilder(app,
                FruitDatabase.class, "fruit-db").build();
        return db.fruitDao();
    }
}

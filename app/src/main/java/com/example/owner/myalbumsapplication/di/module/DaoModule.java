package com.example.owner.myalbumsapplication.di.module;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.example.owner.myalbumsapplication.dao.AlbumDao;
import com.example.owner.myalbumsapplication.dao.AlbumDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DaoModule {
    @Provides
    @Singleton
    public AlbumDao provideFruitDao(Application app) {
        AlbumDatabase db = Room.databaseBuilder(app,
                AlbumDatabase.class, "fruit-db").build();
        return db.fruitDao();
    }
}

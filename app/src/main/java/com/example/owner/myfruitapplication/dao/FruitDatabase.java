package com.example.owner.myfruitapplication.dao;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.owner.myfruitapplication.dto.FruitDto;


@Database(entities = {FruitDto.class}, version = 1, exportSchema = false)
public abstract class FruitDatabase extends RoomDatabase {
    public abstract FruitDao fruitDao();
}


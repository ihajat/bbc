package com.example.owner.myfruitapplication.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.owner.myfruitapplication.dto.FruitDto;

import java.util.List;

@Dao
public interface FruitDao {

    @Query("SELECT * FROM FruitDto LIMIT :limit OFFSET :offset")
    LiveData<List<FruitDto>> get(int offset, int limit);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertAll(FruitDto... FruitDtos);

    @Delete
    void delete(FruitDto FruitDto);

    @Query("SELECT * FROM FruitDto LIMIT :limit OFFSET :offset")
    List<FruitDto> hasData(int offset, int limit);

    @Query("DELETE FROM FruitDto")
    void deleteAll();
}

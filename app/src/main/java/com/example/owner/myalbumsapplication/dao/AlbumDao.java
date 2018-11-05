package com.example.owner.myalbumsapplication.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.owner.myalbumsapplication.dto.AlbumDto;

import java.util.List;

@Dao
public interface AlbumDao {

    @Query("SELECT * FROM AlbumDto  ORDER BY title LIMIT :limit OFFSET :offset")
    LiveData<List<AlbumDto>> get(int offset, int limit);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertAll(AlbumDto... albumDtos);

    @Delete
    void delete(AlbumDto AlbumDto);

    @Query("DELETE FROM AlbumDto")
    void deleteAll();
}

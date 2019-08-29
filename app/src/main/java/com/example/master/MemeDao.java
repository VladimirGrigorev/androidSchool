package com.example.master;

import android.arch.persistence.room.*;

import java.util.List;

@Dao
public interface MemeDao {

    @Query("SELECT * FROM memeentity")
    List<MemeEntity> getAll();

    @Query("SELECT * FROM memeentity WHERE id = :id")
    MemeEntity getById(long id);

    @Insert
    void insert(MemeEntity employee);

    @Update
    void update(MemeEntity employee);

    @Delete
    void delete(MemeEntity employee);

}

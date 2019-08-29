package com.example.master;

import android.arch.persistence.room.*;

import java.util.List;

@Dao
public interface MemeDao {

    @Query("SELECT * FROM meme")
    List<Meme> getAll();

    @Query("SELECT * FROM meme WHERE id = :id")
    Meme getById(long id);

    @Insert
    void insert(Meme employee);

    @Update
    void update(Meme employee);

    @Delete
    void delete(Meme employee);

}

package com.example.master.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {Meme.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract MemeDao memeDao();
}

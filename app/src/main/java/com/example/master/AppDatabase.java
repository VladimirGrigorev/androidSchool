package com.example.master;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {MemeEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract MemeEntity employeeDao();
}

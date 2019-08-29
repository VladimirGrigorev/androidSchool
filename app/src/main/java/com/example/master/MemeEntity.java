package com.example.master;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class MemeEntity {

    @PrimaryKey
    public long id;

    public String title;

    public String description;

    public boolean isFavorite;

    public int createdDate;

    public String photoUtl;
}

package com.example.sample.data.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {ItemEntity.class}, version = 1)
public abstract class ItemDatabase extends RoomDatabase {
    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "Items";

    private static ItemDatabase INSTANCE;

    public static ItemDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (LOCK) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        ItemDatabase.class, ItemDatabase.DATABASE_NAME)
                        .build();
            }
        }

        return INSTANCE;
    }

    public abstract ItemDao itemDao();
}

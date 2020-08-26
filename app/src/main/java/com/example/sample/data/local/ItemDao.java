package com.example.sample.data.local;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import io.reactivex.Observable;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface ItemDao {
    @Insert(onConflict = REPLACE)
    void addItem(ItemEntity itemEntity);

    @Query("SELECT * FROM MyItem")
    Observable<List<ItemEntity>> getAll();

    @Query("SELECT * FROM MyItem WHERE title = :title LIMIT 1")
    Observable<ItemEntity> getItem(String title);
}

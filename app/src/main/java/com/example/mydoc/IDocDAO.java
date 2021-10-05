package com.example.mydoc;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface IDocDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Docs docs);

    @Delete
    void delete(Docs docs);

    @Update
    void update(Docs docs);

    @Query("DELETE FROM All_Docs")
    void deleteAll();

    @Query("SELECT * FROM All_Docs ORDER BY id ASC")
    LiveData<List<Docs>> getAllDocs();

}

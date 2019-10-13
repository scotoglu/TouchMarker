/*
* Database operations
* */

package com.example.touchmarker.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.touchmarker.Model.Bus;

import java.util.List;

@Dao
public interface BusesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void addBus(Bus...buses);

    @Delete
    public void deleteAll(Bus buses);

    @Query("SELECT * FROM Buses")
    public List<Bus> getAll();

    @Query("SELECT * FROM Buses WHERE Id=:busId")
    public List<Bus> getByID(int busId);
}

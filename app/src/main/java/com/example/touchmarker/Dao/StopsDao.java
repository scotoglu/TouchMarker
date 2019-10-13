package com.example.touchmarker.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Ignore;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.RoomWarnings;

import com.example.touchmarker.Model.Stop;

import java.util.List;

@Dao
public interface StopsDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void addStops(Stop...stops);

    @Query("SELECT * FROM Stops")
    public List<Stop> getAll();


    @Ignore
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT name,lat,lon FROM Stops WHERE Id=:id")
    public List<Stop> getStopById(int id);

    @Delete
    public void deleteAll(Stop stop);

}

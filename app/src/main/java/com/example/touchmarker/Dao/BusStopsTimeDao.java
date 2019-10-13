package com.example.touchmarker.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.RoomWarnings;

import com.example.touchmarker.Model.BusStopTime;
import com.example.touchmarker.Model.ModelBusStopTime;

import java.util.List;

@Dao
public interface BusStopsTimeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void addBusStopTime(BusStopTime...busStopTimes);

    @Delete
    public void deleteAll(BusStopTime busStopTime);


    @Query("SELECT BusStopTime.Id,BusStopTime.BusId,BusStopTime.PassTime,BusStopTime.StopId " +
            "FROM BusStopTime " +
            "LEFT JOIN Buses " +
            "ON " +
            "BusStopTime.BusId = Buses.Id " +
            "WHERE Buses.Id=:busId")
    public List<BusStopTime> getPassTime(int  busId);

    @Query("SELECT BusStopTime.Id,BusStopTime.BusId,BusStopTime.PassTime,BusStopTime.StopId " +
            "FROM BusStopTime " +
            "LEFT JOIN Buses " +
            "ON " +
            "BusStopTime.BusId = Buses.Id " +
            "WHERE Buses.RouteCode=:routeCode AND Buses.Id=BusStopTime.BusId")
    public List<BusStopTime> getPassTimeByRouteCode(String routeCode);

    @Query("SELECT * FROM BusStopTime")
    public List<BusStopTime> getAll();

}

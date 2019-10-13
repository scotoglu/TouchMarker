package com.example.touchmarker.Dao;

import android.view.Display;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;


import com.example.touchmarker.Model.FavoriteBus;
import com.example.touchmarker.Model.ModelBus;
import com.example.touchmarker.Model.ModelFavoriteBus;


import java.util.ArrayList;
import java.util.List;

@Dao
public interface FavBusDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void addFavBus(FavoriteBus...favoriteBuses);

    @Query("SELECT routeCode FROM FavBus WHERE routeCode=:routeCode")
    public String  insertedBefore(String routeCode);

    @Query("DELETE FROM FavBus WHERE routeCode=:routeCode")
    public void delByRouteCode(String routeCode);


    @Query("SELECT * FROM FavBus")
    public List<ModelFavoriteBus> getAll();

    @Query("DELETE FROM FavBus")
    public void delAll();

    @Delete
    public void deleteFavBus(FavoriteBus favoriteBus);
}

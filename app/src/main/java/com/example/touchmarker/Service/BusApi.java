package com.example.touchmarker.Service;

import com.example.touchmarker.Model.Bus;
import com.example.touchmarker.Model.BusStopTime;
import com.example.touchmarker.Model.Stop;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface BusApi {

    @POST("api/buses")
    Call<List<Bus>> getBus(@Header("Authorization") String  Auth);

    @POST("api/stops")
    Call<List<Stop>> getStops(@Header("Authorization")String Auth);

    @POST("api/stopBusTime")
    Call<List<BusStopTime>> getBusStopTime(@Header("Authorization")String Auth);
}

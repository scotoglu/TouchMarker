package com.example.touchmarker.RetrofitOperations;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.touchmarker.Database.AppDatabase;
import com.example.touchmarker.Model.Bus;
import com.example.touchmarker.Model.BusStopTime;
import com.example.touchmarker.Model.Stop;
import com.example.touchmarker.Service.BusApi;
import com.example.touchmarker.Util.Util;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitOps {

    private static String TAG = "RetrofitOps";
    private Context context;

    private AppDatabase appDatabase;

    public RetrofitOps(Context context) {
        this.context = context;
    }

    private Retrofit creteRetrofit() {
        Retrofit retrofit = null;
        Log.d(TAG, "creteRetrofit: ");
        if (retrofit == null) {
            Log.d(TAG, "creteRetrofit: if bloğu");
            retrofit = new Retrofit.Builder()
                    .baseUrl(Util.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            return retrofit;
        }
        if (retrofit == null){
            Log.d(TAG, "creteRetrofit: null");
        }
        return retrofit;
    }


    private AppDatabase getAppDatabase() {
        if (appDatabase == null) {
            appDatabase = AppDatabase.getDatabase(context);
            return appDatabase;
        }
        return appDatabase;
    }
    private BusApi getService(){
        BusApi busApi = null;
        Retrofit retrofit = creteRetrofit();

        if (busApi==null){
            busApi = retrofit.create(BusApi.class);
            return busApi;
        }
        return busApi;
    }

    public void getBusesFromApi() {
        creteRetrofit();

        BusApi service = getService();

        appDatabase = getAppDatabase();
        service.getBus(Util.TOKEN).enqueue(new Callback<List<Bus>>() {
            @Override
            public void onResponse(Call<List<Bus>> call, Response<List<Bus>> response) {
                List<Bus> responseBus = response.body();
                for (int i = 0; i < responseBus.size(); i++) {
                    Bus bus = new Bus(
                            responseBus.get(i).getId(),
                            responseBus.get(i).getRouteCode(),
                            responseBus.get(i).getName(),
                            responseBus.get(i).getFirstStopId(),
                            responseBus.get(i).getLastStopId());

                    appDatabase.busesDao().addBus(bus);
                }
            }

            @Override
            public void onFailure(Call<List<Bus>> call, Throwable t) {
                Toast.makeText(context, "Retrofit Bağlantı Hatası", Toast.LENGTH_SHORT).show();
            }
        });


    }

    public void getStopsFromApi() {
        creteRetrofit();
        BusApi service = getService();

        appDatabase = getAppDatabase();
        service.getStops(Util.TOKEN).enqueue(new Callback<List<Stop>>() {
            @Override
            public void onResponse(Call<List<Stop>> call, Response<List<Stop>> response) {
                List<Stop> responseStops = response.body();
                for (int i = 0; i < responseStops.size(); i++) {
                    Stop stop = new Stop(responseStops.get(i).getId(),
                            responseStops.get(i).getName(),
                            responseStops.get(i).getLat(),
                            responseStops.get(i).getLon());

                    appDatabase.stopsDao().addStops(stop);
                }
            }

            @Override
            public void onFailure(Call<List<Stop>> call, Throwable t) {

                Toast.makeText(context, "Retrofit Bağlantı Hatası", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getBusStopTimeFromApi() {
        creteRetrofit();
        BusApi service = getService();

        appDatabase = getAppDatabase();

        service.getBusStopTime(Util.TOKEN).enqueue(new Callback<List<BusStopTime>>() {
            @Override
            public void onResponse(Call<List<BusStopTime>> call, Response<List<BusStopTime>> response) {
                List<BusStopTime> responseBusStopTime = response.body();
                for (int i = 0; i < responseBusStopTime.size(); i++) {
                    BusStopTime busStopTime = new BusStopTime(
                            responseBusStopTime.get(i).getId(),
                            responseBusStopTime.get(i).getStopId(),
                            responseBusStopTime.get(i).getBusId(),
                            responseBusStopTime.get(i).getPassTime()
                    );
                    appDatabase.busStopsTimeDao().addBusStopTime(busStopTime);
                }
            }

            @Override
            public void onFailure(Call<List<BusStopTime>> call, Throwable t) {
                Toast.makeText(context, "Retrofit Bağlantı Hatası", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

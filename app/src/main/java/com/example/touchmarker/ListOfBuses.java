package com.example.touchmarker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.touchmarker.Adapter.CustomAdapter;
import com.example.touchmarker.Database.AppDatabase;
import com.example.touchmarker.Model.Bus;
import com.example.touchmarker.Model.BusStopTime;
import com.example.touchmarker.Model.FavoriteBus;
import com.example.touchmarker.Model.ModelBus;
import com.example.touchmarker.Model.Stop;
import com.example.touchmarker.RetrofitOperations.RetrofitOps;
import com.example.touchmarker.Service.BusApi;
import com.example.touchmarker.Util.Util;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListOfBuses extends AppCompatActivity implements CustomAdapter.OnItemClickListener {

    final ArrayList<ModelBus> tempBus = new ArrayList<>();

    CustomAdapter mAdapter;
    private final String TAG = "App";
    AppDatabase appDatabase;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_bus);

        RetrofitOps retrofitOps = new RetrofitOps(this);
        retrofitOps.getStopsFromApi();
        retrofitOps.getBusStopTimeFromApi();

        getBus();
      /*  getStops();
        getBusStopTime();
*/
        recyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        linearLayoutManager.scrollToPosition(0);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        enableSwipeToAdd();

    }

    /*---------------------------------------Buses-------------------------------------*/
    public void getBus() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Util.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        BusApi service = retrofit.create(BusApi.class);
        service.getBus(Util.TOKEN).enqueue(new Callback<List<Bus>>() {
            @Override
            public void onResponse(Call<List<Bus>> call, Response<List<Bus>> response) {
                List<Bus> response_bus = response.body();
                //Write to database
                /* for (int i = 0;i<response_bus.size();i++){
                         Bus bus = new Bus(
                                 response_bus.get(i).getId(),
                                 response_bus.get(i).getRouteCode(),
                                 response_bus.get(i).getName(),
                                 response_bus.get(i).getFirstStopId(),
                                 response_bus.get(i).getLastStopId());
                         appDatabase = AppDatabase.getDatabase(ListOfBuses.this);
                         appDatabase.busesDao().addBus(bus);
                 }*/
                 appDatabase = AppDatabase.getDatabase(ListOfBuses.this);
                 List<Bus> entityBus = appDatabase.busesDao().getAll();
                //using response_bus instead of entityBus, displays buses without storing on database.
               /* for (int i = 0; i < response_bus.size(); i++) {

                    tempBus.add(new ModelBus(response_bus.get(i).getName(), response_bus.get(i).getRouteCode(), response_bus.get(i).getId()));

                }*/

                //Using entityBus, reads from database
                for (int i=0;i<entityBus.size();i++){
                    tempBus.add(new ModelBus(entityBus.get(i).getName(),entityBus.get(i).getRouteCode(),
                            entityBus.get(i).getId()));
                }
                writeRecyclerView(tempBus);
            }

            @Override
            public void onFailure(Call<List<Bus>> call, Throwable t) {
                Toast.makeText(ListOfBuses.this, "Retrofit Bağlantı Hatası", Toast.LENGTH_SHORT).show();
            }
        });
    }
    /*------------------------------------Buses End---------------------------------------------*/

    /*-------------------------------------Stops-----------------------------------------------*/
    /*public void getStops() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Util.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        BusApi service = retrofit.create(BusApi.class);
        appDatabase = AppDatabase.getDatabase(getApplicationContext());
        service.getStops(Util.TOKEN).enqueue(new Callback<List<Stop>>() {
            @Override
            public void onResponse(Call<List<Stop>> call, Response<List<Stop>> response) {
                List<Stop> response_stops = response.body();
               *//* for (int i =0;i<response_stops.size();i++){
                    Stop stop = new Stop(response_stops.get(i).getId(),
                            response_stops.get(i).getName(),
                            response_stops.get(i).getLat(),
                            response_stops.get(i).getLon());

                    appDatabase.stopsDao().addStops(stop);
                }*//*
            }

            @Override
            public void onFailure(Call<List<Stop>> call, Throwable t) {
                Toast.makeText(ListOfBuses.this, "Retrofit Bağlantı Hatası", Toast.LENGTH_SHORT).show();
            }
        });
    }*/

    /*-------------------------------------Stops End-----------------------------------------------*/
    /*-------------------------------------Write to db all BusStopTime-----------------------------*/
    /*public void getBusStopTime() {
        appDatabase = AppDatabase.getDatabase(getApplicationContext());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Util.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        BusApi service = retrofit.create(BusApi.class);
        service.getBusStopTime(Util.TOKEN).enqueue(new Callback<List<BusStopTime>>() {
            @Override
            public void onResponse(Call<List<BusStopTime>> call, Response<List<BusStopTime>> response) {
                List<BusStopTime> response_busStopTime = response.body();

               *//* for (int i = 0;i<response_busStopTime.size();i++){
                    BusStopTime busStopTime = new BusStopTime(
                            response_busStopTime.get(i).getId(),
                            response_busStopTime.get(i).getStopId(),
                            response_busStopTime.get(i).getBusId(),
                            response_busStopTime.get(i).getPassTime()
                    );
                    appDatabase.busStopsTimeDao().addBusStopTime(busStopTime);
                }*//*
            }

            @Override
            public void onFailure(Call<List<BusStopTime>> call, Throwable t) {
                Toast.makeText(ListOfBuses.this, "Retrofit Bağlantı Hatası", Toast.LENGTH_SHORT).show();
            }
        });
    }*/

    /*-------------------------------------BusStopTime End-----------------------------------------*/
    private void writeRecyclerView(ArrayList<ModelBus> tempBus) {
        CustomAdapter customAdapter = new CustomAdapter(tempBus, getApplicationContext());
        recyclerView.setAdapter(customAdapter);
        customAdapter.setOnItemClickListener(this);
        mAdapter = customAdapter;
    }
    /*-------------------------------------onItemClickListener-----------------------------------------*/
    @Override
    public void onItemClick(int position) {

        appDatabase = AppDatabase.getDatabase(getApplicationContext());
        List<Bus> busModel = new ArrayList<>();
        busModel = appDatabase.busesDao().getAll();

        Intent busTimeIntent = new Intent(this, BusTimes.class);
        busTimeIntent.putExtra("BUS_ID", busModel.get(position).getId().toString());
        busTimeIntent.putExtra("WhereIntentComeFrom","ListOfBuses");
        startActivity(busTimeIntent);
    }
    /*-------------------------------------enableSwipeToAdd----------------------------------------*/
    private void enableSwipeToAdd(){
        SwipeController swipeController = new SwipeController(this){
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {



                final int position = viewHolder.getAdapterPosition();

                FavoriteBus favoriteBus = new FavoriteBus(appDatabase.busesDao().getAll().get(position).getName(),
                        appDatabase.busesDao().getAll().get(position).getRouteCode(),"1");
                mAdapter.addBusToFavorite(favoriteBus);

            }
        };

        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeController);
        itemTouchhelper.attachToRecyclerView(recyclerView);
    }

}


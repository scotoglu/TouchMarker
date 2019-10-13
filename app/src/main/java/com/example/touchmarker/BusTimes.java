package com.example.touchmarker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;

import com.example.touchmarker.Adapter.BusTimeAdapter;
import com.example.touchmarker.Database.AppDatabase;
import com.example.touchmarker.Model.BusStopTime;
import com.example.touchmarker.Model.ModelBusStopTime;

import java.util.ArrayList;
import java.util.List;

public class BusTimes extends AppCompatActivity {

    public static final String TAG = "DENEME";
    private AppDatabase appDatabase;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_times);


        recyclerView = findViewById(R.id.bustime_recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        linearLayoutManager.scrollToPosition(0);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        if (getIntent().getStringExtra("WhereIntentComeFrom").equals("ListOfBuses")){
            String BusId = getIntent().getStringExtra("BUS_ID");
            appDatabase = AppDatabase.getDatabase(getApplicationContext());
            List<BusStopTime> busStopTimes = appDatabase.busStopsTimeDao().getPassTime(Integer.parseInt(BusId));
            writeRecyclerBusTime(busStopTimes);
        }
        if (getIntent().getStringExtra("WhereIntentComeFrom").equals("FavoriteBusesAdapter")){
            String routeCode = getIntent().getStringExtra("RouteCode");
            appDatabase = AppDatabase.getDatabase(getApplicationContext());
            List<BusStopTime> busStopTimes = appDatabase.busStopsTimeDao().getPassTimeByRouteCode(routeCode);
            writeRecyclerBusTime(busStopTimes);
        }



    }
    private void writeRecyclerBusTime(List<BusStopTime> times){
        BusTimeAdapter busTimeAdapter = new BusTimeAdapter(times,getApplicationContext());
        recyclerView.setAdapter(busTimeAdapter);
    }

}

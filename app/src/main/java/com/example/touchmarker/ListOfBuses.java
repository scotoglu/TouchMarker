package com.example.touchmarker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.touchmarker.Adapter.CustomAdapter;
import com.example.touchmarker.Database.AppDatabase;
import com.example.touchmarker.Model.Bus;
import com.example.touchmarker.Model.FavoriteBus;
import com.example.touchmarker.Model.ModelBus;
import com.example.touchmarker.RetrofitOperations.RetrofitOps;

import java.util.ArrayList;
import java.util.List;


public class ListOfBuses extends AppCompatActivity implements CustomAdapter.OnItemClickListener {

    final ArrayList<ModelBus> tempBus = new ArrayList<>();

    private CustomAdapter mAdapter;
    private AppDatabase appDatabase;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_bus);

        RetrofitOps retrofitOps = new RetrofitOps(this);
       /* retrofitOps.getStopsFromApi();
        retrofitOps.getBusStopTimeFromApi();
        retrofitOps.getBusesFromApi();*/

        recyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        linearLayoutManager.scrollToPosition(0);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        enableSwipeToAdd();

        fetchBusForRecycler();
    }

    private void fetchBusForRecycler() {
        appDatabase = AppDatabase.getDatabase(this);
        List<Bus> buses = appDatabase.busesDao().getAll();
        for (int i = 0; i < buses.size(); i++) {
            tempBus.add(new ModelBus(buses.get(i).getName(), buses.get(i).getRouteCode(),
                    buses.get(i).getId()));
        }
        writeRecyclerView(tempBus);
    }


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
        busTimeIntent.putExtra("WhereIntentComeFrom", "ListOfBuses");
        startActivity(busTimeIntent);
    }

    /*-------------------------------------enableSwipeToAdd----------------------------------------*/
    private void enableSwipeToAdd() {
        SwipeController swipeController = new SwipeController(this) {
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {


                final int position = viewHolder.getAdapterPosition();

                FavoriteBus favoriteBus = new FavoriteBus(appDatabase.busesDao().getAll().get(position).getName(),
                        appDatabase.busesDao().getAll().get(position).getRouteCode(), "1");
                mAdapter.addBusToFavorite(favoriteBus);

            }
        };

        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeController);
        itemTouchhelper.attachToRecyclerView(recyclerView);
    }

}


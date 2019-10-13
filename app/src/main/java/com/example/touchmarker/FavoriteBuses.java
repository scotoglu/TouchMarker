package com.example.touchmarker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.touchmarker.Adapter.CustomAdapter;
import com.example.touchmarker.Adapter.FavoriteBusesAdapter;
import com.example.touchmarker.Database.AppDatabase;
import com.example.touchmarker.Model.Bus;
import com.example.touchmarker.Model.FavoriteBus;
import com.example.touchmarker.Model.ModelBus;
import com.example.touchmarker.Model.ModelFavoriteBus;

import java.util.ArrayList;
import java.util.List;

public class FavoriteBuses extends AppCompatActivity implements FavoriteBusesAdapter.OnItemClickListener {

    final String TAG = "FavoriteBuses";
    AppDatabase appDatabase;
    RecyclerView recyclerView;

    private FavoriteBusesAdapter mFavoriteBusesAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_buses);

        recyclerView = findViewById(R.id.favoriteBuses_recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        linearLayoutManager.scrollToPosition(0);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        appDatabase = AppDatabase.getDatabase(getApplicationContext());
        List<ModelFavoriteBus> favoriteBuses = appDatabase.favBusDao().getAll();
        writeRecyclerView((ArrayList<ModelFavoriteBus>)favoriteBuses);


    }

    private void writeRecyclerView(ArrayList<ModelFavoriteBus> favoriteBuses){
        FavoriteBusesAdapter favoriteBusesAdapter  =new FavoriteBusesAdapter(favoriteBuses,getApplicationContext(),this);
        recyclerView.setAdapter(favoriteBusesAdapter);
        favoriteBusesAdapter.setOnItemClickListener(this);
        favoriteBusesAdapter.notifyDataSetChanged();

        mFavoriteBusesAdapter = favoriteBusesAdapter;

    }


    @Override
    public void onItemClick(int position) {

    }

}

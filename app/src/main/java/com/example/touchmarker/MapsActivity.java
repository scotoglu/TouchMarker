package com.example.touchmarker;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.example.touchmarker.Database.AppDatabase;
import com.example.touchmarker.Model.Stop;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, View.OnClickListener {
    private boolean secondMarker  = false;

    private int REQUEST_CODE = 1;
    private GoogleMap mMap;
    private FusedLocationProviderClient mFusedLocationClient;
    private TextView txtStartPoint = null;
    private TextView txtEndPoint= null;
    private Location currentLocation = null;
    private Button userLocationBtn,list_bus,favBusBtn;
    private AppDatabase appDatabase;
    private List<Integer> testUserLoc = new ArrayList<>();
    private Location arrive_loc=null;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        userLocationBtn = findViewById(R.id.gps_btn);
        list_bus = findViewById(R.id.bus_list_btn);
        favBusBtn = findViewById(R.id.favBusBtn);

        userLocationBtn.setOnClickListener(this);
        list_bus.setOnClickListener(this);
        favBusBtn.setOnClickListener(this);

        new DialogWindows(MapsActivity.this).gpsWarning();

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.gps_btn:
                getCurrentLocation();
                break;

            case R.id.bus_list_btn:
                Intent intent = new Intent(MapsActivity.this, ListOfBuses.class);
                startActivity(intent);
                break;

            case R.id.favBusBtn:

                Intent intent1 = new Intent(MapsActivity.this, FavoriteBuses.class);
                startActivity(intent1);
                break;
        }
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        mMap = googleMap;

        /*When long click screen on the maps,adds marker and gets address,if touch the marker deletes the marker.*/
        /*mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {

                if(!secondMarker){

                    mMap.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("Something here."));
                    secondMarker = true;

                    String lat = Double.toString(latLng.latitude);
                    String lon = Double.toString(latLng.longitude);

                  //Convert  LatLng to Location
                   Location userLoc = new Location(LocationManager.GPS_PROVIDER);
                   userLoc.setLatitude(latLng.latitude);
                   userLoc.setLongitude(latLng.longitude);
                   arrive_loc = userLoc;

                   txtEndPoint = (TextView) findViewById(R.id.arrive_point);
                   txtEndPoint.setText(getLocName(userLoc));



                    String name = getLocName(userLoc);

                    Toast.makeText(MapsActivity.this, "Position Ok." + " ->" + name, Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MapsActivity.this, "Already choosen.", Toast.LENGTH_SHORT).show();
            }
            }
        });*/

        /*When click the marker will be removed from maps*/
         /*mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
             @Override
             public boolean onMarkerClick(Marker marker) {
                 if (secondMarker == true){
                     marker.remove();
                     txtEndPoint.setText("");
                     secondMarker = false;
                     arrive_loc = null;
                 }
                 return true;
             }
         });*/


        List<Stop> stopList = new ArrayList<>();

        for (Integer id:testUserLoc){
            appDatabase = AppDatabase.getDatabase(getApplicationContext());
            stopList.addAll(appDatabase.stopsDao().getStopById(id));
        }

        for (int i = 0;i<stopList.size();i++){
            LatLng latLng = new LatLng(Double.parseDouble(stopList.get(i).getLat()),Double.parseDouble(stopList.get(i).getLon()));
            MarkerOptions markerOptions = new MarkerOptions().position(latLng).title(stopList.get(i).getName()).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
            mMap.addMarker(markerOptions);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,15));
        }
    }
 /*------------------------------------------------Gets Location Name or etc----------------------------------------------*/
    public String getLocName(Location location){
        String addressType ="";
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(),
                    location.getLongitude(),1);
            String adress  =addresses.get(0).getThoroughfare()+"," +
                    ""+addresses.get(0).getSubLocality() +", " +
                    ""+ addresses.get(0).getSubAdminArea();
            addressType = adress;
        }catch(IOException e){
            e.printStackTrace();
        }
        return addressType;
    }
/*------------------------------------------------------------------------------------------------------------------------*/
/*------------------------------------------------Gets User Current Location----------------------------------------------*/
    public void getCurrentLocation(){

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
            return;
        }
        Task<Location> task = mFusedLocationClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    currentLocation  = location;
                    testUserLoc = getNearestStops(currentLocation);
                    if (testUserLoc == null){
                        new DialogWindows(MapsActivity.this).notFoundNearestStopsWarning();
                    }

                    SupportMapFragment supportMapFragment = (SupportMapFragment)
                            getSupportFragmentManager().findFragmentById(R.id.map);
                    supportMapFragment.getMapAsync(MapsActivity.this);

                    txtStartPoint = (TextView) findViewById(R.id.start_point);
                    txtStartPoint.setText(getLocName(currentLocation));
                    //Adds a marker which is current location marker,Red one
                    LatLng userLococation = new LatLng(currentLocation.getLatitude(),currentLocation.getLongitude());
                    mMap.addMarker(new MarkerOptions().position(userLococation).title("Konumum"));
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLococation,14));
                    new DialogWindows(MapsActivity.this).nearestStopsToLocations();
                }else{
                    Log.d("TouchMarker else","Location not Ok.");
                }

            }
        });
    }
/*----------------------------------------------------------------------------------------------------------------------------*/
/*---------------------------------------Gets Nearest Stops to Location-------------------------------------------------------*/
    public List<Integer> getNearestStops(Location location){
        List<Integer> stopsId = new ArrayList<>();//Keep nearest stops Id's
        AppDatabase appDatabase = AppDatabase.getDatabase(this);//
        List<Stop> stops =  appDatabase.stopsDao().getAll();//Reading all stops from db

        for (int i = 0;i<stops.size();i++){
            if (Haversine.distance(
                    location.getLatitude(),
                    location.getLongitude(),
                    Double.parseDouble(stops.get(i).getLat()),
                    Double.parseDouble(stops.get(i).getLon())) <= 0.8){//Return distance as km
                stopsId.add(stops.get(i).getId());
            }
        }
        return stopsId;
    }
 /*----------------------------------------------------------------------------------------------------------------------------*/

}

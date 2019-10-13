package com.example.touchmarker.Model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "FavBus")
public class FavoriteBus {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Id")
    @SerializedName("Id")
    @Expose
    private Integer Id;

    @ColumnInfo(name = "Name")
    @SerializedName("Name")
    @Expose
    private String name;

    @SerializedName("routeCode")
    @Expose
    @ColumnInfo(name = "routeCode")
    private String routeCode;

    @SerializedName("isChecked")
    @Expose
    @ColumnInfo(name = "isChecked")
    private String  isChecked;

    public FavoriteBus(String name,String routeCode, String isChecked) {

        this.name = name;
        this.routeCode = routeCode;
        this.isChecked = isChecked;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NonNull
    public Integer getId() {
        return Id;
    }

    public void setId(@NonNull Integer id) {
        Id = id;
    }

    public String getRouteCode() {
        return routeCode;
    }

    public void setRouteCode(String routeCode) {
        this.routeCode = routeCode;
    }

    public String getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(String isChecked) {
        this.isChecked = isChecked;
    }
}

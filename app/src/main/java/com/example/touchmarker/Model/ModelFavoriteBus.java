package com.example.touchmarker.Model;

import androidx.room.ColumnInfo;

public class ModelFavoriteBus {

    @ColumnInfo(name = "Id")
    private Integer Id;

    @ColumnInfo(name = "Name")
    private String busName;

    @ColumnInfo(name = "routeCode")
    private String routeCode;
    private String isChecked;

    public ModelFavoriteBus(Integer Id,String busName, String routeCode) {
        this.busName = busName;
        this.routeCode = routeCode;
        this.Id = Id;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(String isChecked) {
        this.isChecked = isChecked;
    }

    public String getBusName() {
        return busName;
    }

    public void setBusName(String busName) {
        this.busName = busName;
    }

    public String getRouteCode() {
        return routeCode;
    }

    public void setRouteCode(String routeCode) {
        this.routeCode = routeCode;
    }
}

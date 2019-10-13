package com.example.touchmarker.Model;
/*
*
* */

import androidx.room.ColumnInfo;

public class ModelBus {

    @ColumnInfo(name = "Id")
    private Integer busID;

    @ColumnInfo(name = "routeCode")
    private String routeCode;

    @ColumnInfo(name = "Name")
    private String name;

    public ModelBus( String name, String routeCode,int busID) {

        this.name = name;
        this.routeCode = routeCode;
        this.busID = busID;
    }

    public Integer getBusID() {
        return busID;
    }

    public void setBusID(Integer busID) {
        this.busID = busID;
    }

    public String getRouteCode() {
        return routeCode;
    }

    public void setRouteCode(String routeCode) {
        this.routeCode = routeCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
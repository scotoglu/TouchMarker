package com.example.touchmarker.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelBusStopTime {

    @SerializedName("Id")
    @Expose
    private Integer Id;
    @SerializedName("busId")
    @Expose
    private String busId;

    @SerializedName("passTime")
    @Expose
    private String passTime;

    @SerializedName("stopId")
    @Expose
    private String stopId;
    @SerializedName("routeCode")
    @Expose
    private String routeCode;

    public ModelBusStopTime(Integer Id, String busId, String passTime, String stopId) {
        this.Id = Id;
        this.busId = busId;
        this.passTime = passTime;
        this.stopId = stopId;

    }

    public String getRouteCode() {
        return routeCode;
    }

    public void setRouteCode(String routeCode) {
        this.routeCode = routeCode;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getBusId() {
        return busId;
    }

    public void setBusId(String busId) {
        this.busId = busId;
    }

    public String getPassTime() {
        return passTime;
    }

    public void setPassTime(String passTime) {
        this.passTime = passTime;
    }

    public String getStopId() {
        return stopId;
    }

    public void setStopId(String stopId) {
        this.stopId = stopId;
    }
}

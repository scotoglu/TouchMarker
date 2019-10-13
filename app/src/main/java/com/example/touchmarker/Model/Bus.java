/*
* This class can be use for fetching data and storing fetched data to db.
* Model for both Retroift and Android Room
* As shown in below codes.
* */

package com.example.touchmarker.Model;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "Buses")
public class Bus {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Id")
    @SerializedName("Id")
    @Expose
    private Integer id;

    @ColumnInfo(name = "IsDeleted")
    @SerializedName("IsDeleted")
    @Expose
    private String isDeleted;

    @ColumnInfo(name = "DisplayRouteCode")
    @SerializedName("DisplayRouteCode")
    @Expose
    private String displayRouteCode;

    @ColumnInfo(name = "RouteCode")
    @SerializedName("RouteCode")
    @Expose
    private String routeCode;

    @ColumnInfo(name = "Name")
    @SerializedName("Name")
    @Expose
    private String name;

    @ColumnInfo(name = "FirstStopId")
    @SerializedName("FirstStopId")
    @Expose
    private String firstStopId;

    @ColumnInfo(name = "LastStopId")
    @SerializedName("LastStopId")
    @Expose
    private String lastStopId;

    @ColumnInfo(name = "Direction")
    @SerializedName("Direction")
    @Expose
    private String direction;

    @ColumnInfo(name = "ChangeDate")
    @SerializedName("ChangeDate")
    @Expose
    private String changeDate;

    @ColumnInfo(name = "UpdateDate")
    @SerializedName("UpdateDate")
    @Expose
    private String updateDate;

    @ColumnInfo(name = "Active")
    @SerializedName("Active")
    @Expose
    private String active;


    @ColumnInfo(name = "InsertDate")
    @SerializedName("InsertDate")
    @Expose
    private String insertDate;

    public Bus(Integer id, String routeCode, String name,String firstStopId,String lastStopId) {
        this.id = id;
        this.routeCode = routeCode;
        this.name = name;
        this.lastStopId = lastStopId;
        this.firstStopId = firstStopId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getDisplayRouteCode() {
        return displayRouteCode;
    }

    public void setDisplayRouteCode(String displayRouteCode) {
        this.displayRouteCode = displayRouteCode;
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

    public String getFirstStopId() {
        return firstStopId;
    }

    public void setFirstStopId(String firstStopId) {
        this.firstStopId = firstStopId;
    }

    public String getLastStopId() {
        return lastStopId;
    }

    public void setLastStopId(String lastStopId) {
        this.lastStopId = lastStopId;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(String changeDate) {
        this.changeDate = changeDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(String insertDate) {
        this.insertDate = insertDate;
    }

}

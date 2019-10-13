package com.example.touchmarker.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "BusStopTime")
public class BusStopTime {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Id")
    @SerializedName("Id")
    @Expose
    private Integer id;

    @ColumnInfo(name = "IsDeleted")
    @SerializedName("IsDeleted")
    @Expose
    private String isDeleted;

    @ColumnInfo(name = "StopId")
    @SerializedName("StopId")
    @Expose
    private String stopId;

    @ColumnInfo(name = "BusId")
    @SerializedName("BusId")
    @Expose
    private String busId;

    @ColumnInfo(name = "PassTime")
    @SerializedName("PassTime")
    @Expose
    private String passTime;

    @ColumnInfo(name = "DayType")
    @SerializedName("DayType")
    @Expose
    private String dayType;

    @ColumnInfo(name = "ChangeDate")
    @SerializedName("ChangeDate")
    @Expose
    private String changeDate;

    @ColumnInfo(name = "UpdateDate")
    @SerializedName("UpdateDate")
    @Expose
    private String updateDate;

    @ColumnInfo(name = "InsertDate")
    @SerializedName("InsertDate")
    @Expose
    private String insertDate;

    public BusStopTime(Integer id, String stopId, String busId, String passTime) {
        this.id = id;
        this.stopId = stopId;
        this.busId = busId;
        this.passTime = passTime;
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

    public String getStopId() {
        return stopId;
    }

    public void setStopId(String stopId) {
        this.stopId = stopId;
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

    public String getDayType() {
        return dayType;
    }

    public void setDayType(String dayType) {
        this.dayType = dayType;
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

    public String getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(String insertDate) {
        this.insertDate = insertDate;
    }

}
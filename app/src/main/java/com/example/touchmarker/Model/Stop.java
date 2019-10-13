
package com.example.touchmarker.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "Stops")
public class Stop {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Id")
    @SerializedName("Id")
    @Expose
    private Integer id;

    @ColumnInfo(name ="IsDeleted" )
    @SerializedName("IsDeleted")
    @Expose
    private String isDeleted;

    @ColumnInfo(name = "Name")
    @SerializedName("Name")
    @Expose
    private String name;

    @ColumnInfo(name = "Lat")
    @SerializedName("Lat")
    @Expose
    private String lat;

    @ColumnInfo(name = "Lon")
    @SerializedName("Lon")
    @Expose
    private String lon;

    @ColumnInfo(name = "Description")
    @SerializedName("Description")
    @Expose
    private String description;

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

    public Stop(Integer id, String name, String lat, String lon) {
        this.id = id;
        this.name = name;
        this.lat = lat;
        this.lon = lon;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

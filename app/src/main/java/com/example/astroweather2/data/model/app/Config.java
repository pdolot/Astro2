package com.example.astroweather2.data.model.app;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "config")
public class Config {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private Long id;
    private Integer synchronizationInterval;
    private Double currentLatitude;
    private Double currentLongitude;
    private String weatherUnit;
    private String city;

    public Config(Integer synchronizationInterval, Double currentLatitude, Double currentLongitude, String weatherUnit, String city) {
        this.synchronizationInterval = synchronizationInterval;
        this.currentLatitude = currentLatitude;
        this.currentLongitude = currentLongitude;
        this.weatherUnit = weatherUnit;
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setId(@NonNull Long id) {
        this.id = id;
    }

    public void setSynchronizationInterval(Integer synchronizationInterval) {
        this.synchronizationInterval = synchronizationInterval;
    }

    public void setCurrentLatitude(Double currentLatitude) {
        this.currentLatitude = currentLatitude;
    }

    public void setCurrentLongitude(Double currentLongitude) {
        this.currentLongitude = currentLongitude;
    }

    public void setWeatherUnit(String weatherUnit) {
        this.weatherUnit = weatherUnit;
    }

    @NonNull
    public Long getId() {
        return id;
    }

    public Integer getSynchronizationInterval() {
        return synchronizationInterval;
    }

    public Double getCurrentLatitude() {
        return currentLatitude;
    }

    public Double getCurrentLongitude() {
        return currentLongitude;
    }

    public String getWeatherUnit() {
        return weatherUnit;
    }
}

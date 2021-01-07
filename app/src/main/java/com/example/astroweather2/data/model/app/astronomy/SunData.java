package com.example.astroweather2.data.model.app.astronomy;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "sun")
public class SunData {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private Long id;
    private String sunrise;
    private String sunset;
    private String solarNoon;
    private String dayLength;
    private String sunAltitude;
    private String sunDistance;
    private String sunAzimuth;
    private Long updateTime;

    public SunData(String sunrise, String sunset, String solarNoon, String dayLength, String sunAltitude, String sunDistance, String sunAzimuth) {
        this.sunrise = sunrise;
        this.sunset = sunset;
        this.solarNoon = solarNoon;
        this.dayLength = dayLength;
        this.sunAltitude = sunAltitude;
        this.sunDistance = sunDistance;
        this.sunAzimuth = sunAzimuth;
        updateTime = System.currentTimeMillis();
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    @NonNull
    public Long getId() {
        return id;
    }

    public void setId(@NonNull Long id) {
        this.id = id;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

    public void setSolarNoon(String solarNoon) {
        this.solarNoon = solarNoon;
    }

    public void setDayLength(String dayLength) {
        this.dayLength = dayLength;
    }

    public void setSunAltitude(String sunAltitude) {
        this.sunAltitude = sunAltitude;
    }

    public void setSunDistance(String sunDistance) {
        this.sunDistance = sunDistance;
    }

    public void setSunAzimuth(String sunAzimuth) {
        this.sunAzimuth = sunAzimuth;
    }

    public String getSunrise() {
        return sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public String getSolarNoon() {
        return solarNoon;
    }

    public String getDayLength() {
        return dayLength;
    }

    public String getSunAltitude() {
        return sunAltitude;
    }

    public String getSunDistance() {
        return sunDistance;
    }

    public String getSunAzimuth() {
        return sunAzimuth;
    }
}

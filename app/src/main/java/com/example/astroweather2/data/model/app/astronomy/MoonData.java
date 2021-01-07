package com.example.astroweather2.data.model.app.astronomy;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "moon")
public class MoonData {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private Long id;
    private String moonrise;
    private String moonAltitude;
    private String moonDistance;
    private String moonAzimuth;
    private String moonParallacticAngle;
    private Long updateTime;

    public MoonData(String moonrise, String moonAltitude, String moonDistance, String moonAzimuth, String moonParallacticAngle) {
        this.moonrise = moonrise;
        this.moonAltitude = moonAltitude;
        this.moonDistance = moonDistance;
        this.moonAzimuth = moonAzimuth;
        this.moonParallacticAngle = moonParallacticAngle;
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

    public void setMoonrise(String moonrise) {
        this.moonrise = moonrise;
    }

    public void setMoonAltitude(String moonAltitude) {
        this.moonAltitude = moonAltitude;
    }

    public void setMoonDistance(String moonDistance) {
        this.moonDistance = moonDistance;
    }

    public void setMoonAzimuth(String moonAzimuth) {
        this.moonAzimuth = moonAzimuth;
    }

    public void setMoonParallacticAngle(String moonParallacticAngle) {
        this.moonParallacticAngle = moonParallacticAngle;
    }

    public String getMoonrise() {
        return moonrise;
    }

    public String getMoonAltitude() {
        return moonAltitude;
    }

    public String getMoonDistance() {
        return moonDistance;
    }

    public String getMoonAzimuth() {
        return moonAzimuth;
    }

    public String getMoonParallacticAngle() {
        return moonParallacticAngle;
    }
}

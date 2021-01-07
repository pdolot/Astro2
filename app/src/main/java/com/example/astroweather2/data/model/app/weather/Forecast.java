package com.example.astroweather2.data.model.app.weather;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity(tableName = "forecast")
public class Forecast {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private Long id;
    private String city;
    @SerializedName("dt")
    private Long date;
    private Long sunrise;
    private Long sunset;
    @Ignore
    private Temperature temp;
    private Double temp_day;
    private Double temp_min;
    private Double temp_max;
    private Double temp_night;
    private Double temp_eve;
    private Double temp_morn;
    @Ignore
    @SerializedName("feels_like")
    private Temperature feelsTemp;
    private Double feelsTemp_day;
    private Double feelsTemp_night;
    private Double feelsTemp_eve;
    private Double feelsTemp_morn;
    private Integer pressure;
    private Integer humidity;
    private Integer clouds;
    @SerializedName("wind_speed")
    private Double windSpeed;
    @SerializedName("wind_deg")
    private Double windDeg;
    @Ignore
    private List<WeatherDescription> weather;
    private String icon;
    private String description;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Temperature getTemp() {
        return temp;
    }

    public void setTemp(Temperature temp) {
        this.temp = temp;
    }

    public Double getTemp_day() {
        return temp_day;
    }

    public void setTemp_day(Double temp_day) {
        this.temp_day = temp_day;
    }

    public Double getTemp_min() {
        return temp_min;
    }

    public void setTemp_min(Double temp_min) {
        this.temp_min = temp_min;
    }

    public Double getTemp_max() {
        return temp_max;
    }

    public void setTemp_max(Double temp_max) {
        this.temp_max = temp_max;
    }

    public Double getTemp_night() {
        return temp_night;
    }

    public void setTemp_night(Double temp_night) {
        this.temp_night = temp_night;
    }

    public Double getTemp_eve() {
        return temp_eve;
    }

    public void setTemp_eve(Double temp_eve) {
        this.temp_eve = temp_eve;
    }

    public Double getTemp_morn() {
        return temp_morn;
    }

    public void setTemp_morn(Double temp_morn) {
        this.temp_morn = temp_morn;
    }

    public Temperature getFeelsTemp() {
        return feelsTemp;
    }

    public void setFeelsTemp(Temperature feelsTemp) {
        this.feelsTemp = feelsTemp;
    }

    public Double getFeelsTemp_day() {
        return feelsTemp_day;
    }

    public void setFeelsTemp_day(Double feelsTemp_day) {
        this.feelsTemp_day = feelsTemp_day;
    }

    public Double getFeelsTemp_night() {
        return feelsTemp_night;
    }

    public void setFeelsTemp_night(Double feelsTemp_night) {
        this.feelsTemp_night = feelsTemp_night;
    }

    public Double getFeelsTemp_eve() {
        return feelsTemp_eve;
    }

    public void setFeelsTemp_eve(Double feelsTemp_eve) {
        this.feelsTemp_eve = feelsTemp_eve;
    }

    public Double getFeelsTemp_morn() {
        return feelsTemp_morn;
    }

    public void setFeelsTemp_morn(Double feelsTemp_morn) {
        this.feelsTemp_morn = feelsTemp_morn;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public Long getSunrise() {
        return sunrise;
    }

    public void setSunrise(Long sunrise) {
        this.sunrise = sunrise;
    }

    public Long getSunset() {
        return sunset;
    }

    public void setSunset(Long sunset) {
        this.sunset = sunset;
    }

    public Integer getPressure() {
        return pressure;
    }

    public void setPressure(Integer pressure) {
        this.pressure = pressure;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public Integer getClouds() {
        return clouds;
    }

    public void setClouds(Integer clouds) {
        this.clouds = clouds;
    }

    public Double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(Double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public Double getWindDeg() {
        return windDeg;
    }

    public void setWindDeg(Double windDeg) {
        this.windDeg = windDeg;
    }

    public List<WeatherDescription> getWeather() {
        return weather;
    }

    public void setWeather(List<WeatherDescription> weather) {
        this.weather = weather;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
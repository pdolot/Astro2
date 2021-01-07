package com.example.astroweather2.data.model.api;

import com.example.astroweather2.data.model.app.weather.Forecast;
import com.example.astroweather2.data.model.app.weather.Weather;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherResponse {
    private Weather current;
    @SerializedName("daily")
    private List<Forecast> forecasts;

    public Weather getCurrent() {
        return current;
    }

    public void setCurrent(Weather current) {
        this.current = current;
    }

    public List<Forecast> getForecasts() {
        return forecasts;
    }

    public void setForecasts(List<Forecast> forecasts) {
        this.forecasts = forecasts;
    }
}

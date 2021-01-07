package com.example.astroweather2.constant;

import com.example.astroweather2.R;
import com.example.astroweather2.data.model.app.WeatherInfo;
import com.example.astroweather2.data.model.app.astronomy.MoonData;
import com.example.astroweather2.data.model.app.astronomy.SunData;

import java.util.ArrayList;
import java.util.List;

public class AstronomyConstants {
    // Astronomy API constants
    public static final String ASTRONOMY_API_KEY = "5563c9d28a9241b7ac90a41ae11de174";
    public static final String ASTRONOMY_API_BASE_URL = "https://api.ipgeolocation.io/astronomy";

    public static List<WeatherInfo> getSunData(SunData sunData) {
        List<WeatherInfo> data = new ArrayList<>();
        data.add(new WeatherInfo(null, "Wschód słońca", sunData.getSunrise()));
        data.add(new WeatherInfo(null, "Zachód słońca", sunData.getSunset()));
        data.add(new WeatherInfo(null, "Wysokość słońca", sunData.getSunAltitude()));
        data.add(new WeatherInfo(null, "Azymut słońca", sunData.getSunAzimuth()));
        data.add(new WeatherInfo(null, "Odległość słońca", sunData.getSunDistance()));
        data.add(new WeatherInfo(null, "Południe słoneczne", sunData.getSolarNoon()));
        data.add(new WeatherInfo(null, "Długość dnia", sunData.getDayLength()));
        return data;
    }

    public static List<WeatherInfo> getMoonData(MoonData moonData) {
        List<WeatherInfo> data = new ArrayList<>();
        data.add(new WeatherInfo(null, "Wschód księżyca", moonData.getMoonrise()));
        data.add(new WeatherInfo(null, "Wysokość księżyca", moonData.getMoonAltitude()));
        data.add(new WeatherInfo(null, "Azymut księżyca", moonData.getMoonAzimuth()));
        data.add(new WeatherInfo(null, "Odległość księżyca", moonData.getMoonDistance()));
        data.add(new WeatherInfo(null, "Kąt paralaktyczny", moonData.getMoonParallacticAngle()));
        return data;
    }
}

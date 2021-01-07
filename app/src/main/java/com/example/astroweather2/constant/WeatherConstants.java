package com.example.astroweather2.constant;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import androidx.core.content.res.ResourcesCompat;

import com.example.astroweather2.R;
import com.example.astroweather2.data.model.app.WeatherInfo;
import com.example.astroweather2.data.model.app.WeatherUnit;
import com.example.astroweather2.data.model.app.weather.Forecast;
import com.example.astroweather2.data.model.app.weather.Weather;

import org.joda.time.LocalDateTime;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WeatherConstants {

    // OpenWeather API constants
    public static final String OPEN_WEATHER_API_KEY = "3bf993b129060a4cb85b2d8ab6020708";
    public static final String OPEN_WEATHER_LOCATION = "https://api.openweathermap.org/data/2.5/weather";
    public static final String OPEN_WEATHER_ONE_CALL = "https://api.openweathermap.org/data/2.5/onecall";
    public static String QUERY_PARAM_LANG = "pl";
    public static String QUERY_PARAM_EXCLUDE = "hourly,minutely";

    public static Drawable getWeatherIconUrl(Context context, String iconId) {
        Resources resources = context.getResources();
        final int resourceId = resources.getIdentifier("_" + iconId, "drawable", context.getPackageName());
        return ResourcesCompat.getDrawable(resources, resourceId, null);
    }

    public static List<WeatherInfo> getWeatherData(Weather weather, WeatherUnit weatherUnit, Context context) {
        List<WeatherInfo> data = new ArrayList<>();
        data.add(new WeatherInfo(R.drawable.ic_sunrise, "Wschód słońca", new LocalDateTime(new Date(weather.getSunrise())).toString("HH:mm:ss")));
        data.add(new WeatherInfo(R.drawable.ic_sunset, "Zachód słońca", new LocalDateTime(new Date(weather.getSunset())).toString("HH:mm:ss")));
        data.add(new WeatherInfo(R.drawable.ic_pressure, "Ciśnienie", weather.getPressure() + " hPa"));
        data.add(new WeatherInfo(R.drawable.ic_humidity, "Wilgotność", weather.getHumidity() + " %"));
        data.add(new WeatherInfo(R.drawable.ic_clouds, "Zachmurzenie", weather.getClouds() + " %"));
        data.add(new WeatherInfo(R.drawable.ic_visibility, "Widoczność", weather.getVisibility() + " m"));
        data.add(new WeatherInfo(R.drawable.ic_wind, "Prędkość wiatru", context.getString(R.string.speed, weather.getWindSpeed(), weatherUnit.getWindSpeedUnit())));
        data.add(new WeatherInfo(R.drawable.ic_wind_degree, "Kierunek wiatru", weather.getWindDeg() + " °"));
        return data;
    }

    public static List<WeatherInfo> getWeatherData(Forecast weather, WeatherUnit weatherUnit, Context context) {
        List<WeatherInfo> data = new ArrayList<>();
        data.add(new WeatherInfo(R.drawable.ic_temperature, "Temperatura*", context.getString(R.string.temperatureDay,
                weather.getTemp_morn(), weather.getTemp_day(), weather.getTemp_eve(), weather.getTemp_night(), weatherUnit.getTemperatureUnit())));
        data.add(new WeatherInfo(R.drawable.ic_feel_temperature, "Odczuwalna temperatura*", context.getString(R.string.temperatureDay,
                weather.getFeelsTemp_morn(), weather.getFeelsTemp_day(), weather.getFeelsTemp_eve(), weather.getFeelsTemp_night(), weatherUnit.getTemperatureUnit())));
        data.add(new WeatherInfo(R.drawable.ic_sunrise, "Wschód słońca", new LocalDateTime(new Date(weather.getSunrise())).toString("HH:mm:ss")));
        data.add(new WeatherInfo(R.drawable.ic_sunset, "Zachód słońca", new LocalDateTime(new Date(weather.getSunset())).toString("HH:mm:ss")));
        data.add(new WeatherInfo(R.drawable.ic_pressure, "Ciśnienie", weather.getPressure() + " hPa"));
        data.add(new WeatherInfo(R.drawable.ic_humidity, "Wilgotność", weather.getHumidity() + " %"));
        data.add(new WeatherInfo(R.drawable.ic_clouds, "Zachmurzenie", weather.getClouds() + " %"));
        data.add(new WeatherInfo(R.drawable.ic_wind, "Prędkość wiatru", context.getString(R.string.speed, weather.getWindSpeed(), weatherUnit.getWindSpeedUnit())));
        data.add(new WeatherInfo(R.drawable.ic_wind_degree, "Kierunek wiatru", weather.getWindDeg() + " °"));
        return data;
    }
}

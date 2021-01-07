package com.example.astroweather2.data.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.astroweather2.data.model.app.Config;
import com.example.astroweather2.data.model.app.astronomy.MoonData;
import com.example.astroweather2.data.model.app.astronomy.SunData;
import com.example.astroweather2.data.model.app.weather.Forecast;
import com.example.astroweather2.data.model.app.weather.Location;
import com.example.astroweather2.data.model.app.weather.Weather;

import static com.example.astroweather2.constant.DbConstants.*;

@Database(entities = {SunData.class, MoonData.class, Forecast.class, Weather.class, Config.class, Location.class}, version = VERSION, exportSchema = false)
public abstract class LocalDatabase extends RoomDatabase {
    public abstract DbDao dataDao();
}

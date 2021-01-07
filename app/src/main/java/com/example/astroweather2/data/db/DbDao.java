package com.example.astroweather2.data.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.astroweather2.data.model.app.Config;
import com.example.astroweather2.data.model.app.WeatherUnit;
import com.example.astroweather2.data.model.app.astronomy.MoonData;
import com.example.astroweather2.data.model.app.astronomy.SunData;
import com.example.astroweather2.data.model.app.weather.Forecast;
import com.example.astroweather2.data.model.app.weather.Location;
import com.example.astroweather2.data.model.app.weather.Weather;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

@Dao
public abstract class DbDao {

    // inserts
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract Single<Long> insertSingle(Location location);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract Long insert(Location location);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract Long insert(MoonData moonData);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract Long insert(SunData sunData);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract Long insert(Weather weather);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract List<Long> insert(List<Forecast> forecasts);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract Long insert(Config config);

    // delete

    @Query("DELETE FROM location WHERE id= :id")
    public abstract int deleteLocation(long id);

    @Query("DELETE FROM moon")
    public abstract int deleteMoonData();

    @Query("DELETE FROM sun")
    public abstract int deleteSunData();

    @Query("DELETE FROM weather WHERE city =:city")
    public abstract int deleteWeather(String city);

    @Query("DELETE FROM forecast WHERE city =:city")
    public abstract int deleteForecast(String city);

    // getAll
    @Query("SELECT * FROM location")
    public abstract LiveData<List<Location>> getAllLocations();

    @Query("SELECT * FROM moon")
    public abstract LiveData<MoonData> getMoonData();

    @Query("SELECT * FROM sun")
    public abstract LiveData<SunData> getSunData();

    @Query("SELECT * FROM weather WHERE city =:city")
    public abstract Single<Weather> getWeather(String city);

    @Query("SELECT COUNT(*) FROM weather")
    public abstract LiveData<Integer> observeWeatherChange();

    @Query("SELECT * FROM forecast WHERE city =:city ORDER BY date DESC LIMIT 7")
    public abstract Single<List<Forecast>> getForecast(String city);

    @Query("SELECT COUNT(*) FROM forecast")
    public abstract LiveData<Integer> observeForecastChange();

    @Query("SELECT * FROM config")
    public abstract Config getConfig();

    @Query("SELECT * FROM config")
    public abstract LiveData<Config> getConfigLiveData();

    // transactions
    @Transaction
    public void initConfig() {
        Config config = getConfig();
        if (config == null) {
            Location location = new Location();
            location.setLatitude(51.760258163743494);
            location.setLongitude(19.451415785681505);
            location.setCity("Łódź");
            insert(location);
            insert(new Config(5, 51.760258163743494, 19.451415785681505, WeatherUnit.METRIC.name(), "Łódź"));
        }
    }

    @Transaction
    public void insertAstronomyData(SunData sunData, MoonData moonData) {
        deleteSunData();
        insert(sunData);
        deleteMoonData();
        insert(moonData);
    }

    @Transaction
    public void insertWeather(Weather weather, List<Forecast> forecasts) {
        deleteWeather(weather.getCity());
        insert(weather);
        deleteForecast(forecasts.get(0).getCity());
        insert(forecasts);
    }

    // update

    @Query("UPDATE config SET currentLatitude=:lat, currentLongitude =:lng, synchronizationInterval =:synchronizationInterval, weatherUnit =:weatherUnit WHERE id=:id")
    public abstract Single<Integer> updateConfig(Long id, Double lat, Double lng, Integer synchronizationInterval, String weatherUnit);

    @Query("UPDATE config SET city=:city, currentLatitude=:lat, currentLongitude=:lng WHERE id=:id")
    public abstract Single<Integer> updateConfig(Long id, String city, Double lat, Double lng);
}

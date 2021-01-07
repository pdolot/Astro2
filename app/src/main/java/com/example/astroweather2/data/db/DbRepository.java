package com.example.astroweather2.data.db;

import androidx.lifecycle.LiveData;

import com.example.astroweather2.data.model.api.AstronomyResponse;
import com.example.astroweather2.data.model.api.WeatherResponse;
import com.example.astroweather2.data.model.app.Config;
import com.example.astroweather2.data.model.app.astronomy.MoonData;
import com.example.astroweather2.data.model.app.astronomy.SunData;
import com.example.astroweather2.data.model.app.weather.Forecast;
import com.example.astroweather2.data.model.app.weather.Location;
import com.example.astroweather2.data.model.app.weather.Weather;

import java.util.Collections;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class DbRepository {
    private final DbDao dbDao;

    public DbRepository(DbDao dbDao) {
        this.dbDao = dbDao;
    }

    public Single<Long> insert(Location location) {
        return dbDao.insertSingle(location)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    public Disposable deleteLocation(long id) {
        return Completable.fromAction(() ->  dbDao.deleteLocation(id))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    public LiveData<List<Location>> getAllLocations() {
        return dbDao.getAllLocations();
    }

    public LiveData<MoonData> getMoonData() {
        return dbDao.getMoonData();
    }

    public LiveData<SunData> getSunData() {
        return dbDao.getSunData();
    }

    public Single<Weather> getWeather(String city) {
        return dbDao.getWeather(city)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<List<Forecast>> getForecast(String city) {
        return dbDao.getForecast(city)
                .map(forecasts -> {
                    Collections.reverse(forecasts);
                    return forecasts;
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public LiveData<Config> getConfig() {
        return dbDao.getConfigLiveData();
    }

    public Disposable initConfig() {
        return Completable.fromAction(dbDao::initConfig)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    public Completable insertAstronomyData(AstronomyResponse response) {
        SunData sunData = new SunData(response.getSunrise(), response.getSunset(), response.getSolarNoon(), response.getDayLength(),
                response.getSunAltitude(), response.getSunDistance(), response.getSunAzimuth());
        MoonData moonData = new MoonData(response.getMoonrise(), response.getMoonAltitude(),
                response.getMoonDistance(), response.getMoonAzimuth(), response.getMoonParallacticAngle());

        return Completable.fromAction(() -> dbDao.insertAstronomyData(sunData, moonData))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Completable insertWeather(WeatherResponse weatherResponse) {
        return Completable.fromAction(() -> dbDao.insertWeather(weatherResponse.getCurrent(), weatherResponse.getForecasts()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public LiveData<Integer> observeForecastChange() {
        return dbDao.observeForecastChange();
    }

    public LiveData<Integer> observeWeatherChange() {
        return dbDao.observeWeatherChange();
    }

    public Single<Integer> updateConfig(Long id, Double lat, Double lng, Integer synchronizationInterval, String weatherUnit) {
        return dbDao.updateConfig(id, lat, lng, synchronizationInterval, weatherUnit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<Integer> updateConfig(Long id, String city, Double lat, Double lng) {
        return dbDao.updateConfig(id, city, lat, lng)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}

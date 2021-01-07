package com.example.astroweather2.data.rest;

import com.example.astroweather2.data.model.api.AstronomyResponse;
import com.example.astroweather2.data.model.api.WeatherResponse;
import com.example.astroweather2.data.model.app.weather.Location;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface RestService {

    @GET
    Single<AstronomyResponse> getAstronomyData(@Url String url, @Query("apiKey") String key, @Query("lat") String lat, @Query("long") String lng);

    @GET
    Single<Location> getLocation(@Url String url, @Query("q") String city, @Query("appid") String apiKey);

    @GET
    Single<WeatherResponse> getWeather(@Url String url, @Query("lat") String lat, @Query("lon") String lon,
                                       @Query("units") String units, @Query("exclude") String exclude,
                                       @Query("lang") String lang, @Query("appid") String apiKey);
}

package com.example.astroweather2.data.rest;

import com.example.astroweather2.constant.AstronomyConstants;
import com.example.astroweather2.constant.WeatherConstants;
import com.example.astroweather2.data.model.api.AstronomyResponse;
import com.example.astroweather2.data.model.api.WeatherResponse;
import com.example.astroweather2.data.model.app.weather.Forecast;
import com.example.astroweather2.data.model.app.weather.Location;

import org.apache.commons.lang3.StringUtils;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RestRepository {
    private final RestService restService;

    public RestRepository(RestService restService) {
        this.restService = restService;
    }

    public Single<AstronomyResponse> fetchAstronomyData(String lat, String lng) {
        String url = AstronomyConstants.ASTRONOMY_API_BASE_URL;
        String key = AstronomyConstants.ASTRONOMY_API_KEY;
        return restService.getAstronomyData(url, key, lat, lng)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<Location> fetchLocation(String city) {
        String url = WeatherConstants.OPEN_WEATHER_LOCATION;
        String key = WeatherConstants.OPEN_WEATHER_API_KEY;
        return restService.getLocation(url, city, key)
                .map(location -> {
                    location.setCity(StringUtils.capitalize(city.toLowerCase()));
                    location.setLatitude(location.getCoordinates().getLatitude());
                    location.setLongitude(location.getCoordinates().getLongitude());
                    return location;
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<WeatherResponse> fetchWeather(String lat, String lng, String units, String city) {
        String url = WeatherConstants.OPEN_WEATHER_ONE_CALL;
        String key = WeatherConstants.OPEN_WEATHER_API_KEY;
        String exclude = WeatherConstants.QUERY_PARAM_EXCLUDE;
        String language = WeatherConstants.QUERY_PARAM_LANG;
        return restService.getWeather(url, lat, lng, units, exclude, language, key)
                .map(weatherResponse -> {
                    weatherResponse.getCurrent().setIcon(weatherResponse.getCurrent().getWeather().get(0).getIcon());
                    weatherResponse.getCurrent().setDescription(weatherResponse.getCurrent().getWeather().get(0).getDescription());
                    weatherResponse.getCurrent().setCity(city);
                    weatherResponse.getCurrent().setDate(weatherResponse.getCurrent().getDate() * 1000);
                    weatherResponse.getCurrent().setSunrise(weatherResponse.getCurrent().getSunrise() * 1000);
                    weatherResponse.getCurrent().setSunset(weatherResponse.getCurrent().getSunset() * 1000);

                    for (Forecast forecast : weatherResponse.getForecasts()) {

                        forecast.setCity(city);
                        forecast.setIcon(forecast.getWeather().get(0).getIcon());
                        forecast.setDescription(forecast.getWeather().get(0).getDescription());

                        forecast.setTemp_day(forecast.getTemp().getDay());
                        forecast.setTemp_max(forecast.getTemp().getMax());
                        forecast.setTemp_min(forecast.getTemp().getMin());
                        forecast.setTemp_night(forecast.getTemp().getNight());
                        forecast.setTemp_eve(forecast.getTemp().getEve());
                        forecast.setTemp_morn(forecast.getTemp().getMorn());

                        forecast.setFeelsTemp_day(forecast.getFeelsTemp().getDay());
                        forecast.setFeelsTemp_night(forecast.getFeelsTemp().getNight());
                        forecast.setFeelsTemp_eve(forecast.getFeelsTemp().getEve());
                        forecast.setFeelsTemp_morn(forecast.getFeelsTemp().getMorn());

                        forecast.setDate(forecast.getDate() * 1000);
                        forecast.setSunrise(forecast.getSunrise() * 1000);
                        forecast.setSunset(forecast.getSunset() * 1000);
                    }
                    return weatherResponse;
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}

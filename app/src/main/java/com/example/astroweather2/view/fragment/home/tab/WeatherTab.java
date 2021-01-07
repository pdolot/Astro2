package com.example.astroweather2.view.fragment.home.tab;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.astroweather2.R;
import com.example.astroweather2.constant.WeatherConstants;
import com.example.astroweather2.core.CoreTab;
import com.example.astroweather2.data.model.app.WeatherUnit;
import com.example.astroweather2.data.model.app.weather.Weather;
import com.example.astroweather2.view.adapter.WeatherInfoAdapter;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.LocalDateTime;

import java.util.Date;

public class WeatherTab extends CoreTab {

    private Weather weather;
    private WeatherUnit weatherUnit;

    private TextView averageTemperature;
    private TextView temperature;
    private TextView feelTemperature;
    private ImageView weatherIcon;
    private TextView weatherDescription;
    private TextView city;
    private TextView updateDate;
    private RecyclerView weatherData;
    private View container;
    private final WeatherInfoAdapter weatherInfoAdapter = new WeatherInfoAdapter();

    public void setWeather(Weather weather, WeatherUnit weatherUnit) {
        this.weather = weather;
        this.weatherUnit = weatherUnit;
    }

    @Override
    public void bindView(View container) {
        this.container = container.findViewById(R.id.container);
        Context context = container.getContext();
        container.scrollTo(0,0);
        averageTemperature = container.findViewById(R.id.averageTemperature);
        temperature = container.findViewById(R.id.temperature);
        feelTemperature = container.findViewById(R.id.feelTemperature);
        weatherIcon = container.findViewById(R.id.weatherIcon);
        weatherDescription = container.findViewById(R.id.weatherDescription);
        city = container.findViewById(R.id.city);
        updateDate = container.findViewById(R.id.updateDate);
        weatherData = container.findViewById(R.id.recyclerView);
        weatherData.setLayoutManager(new LinearLayoutManager(context));
        weatherData.setAdapter(weatherInfoAdapter);

        bindData(context);
    }

    private void bindData(Context context) {
        if (weather != null) {
            container.setVisibility(View.VISIBLE);
            temperature.setText(context.getString(R.string.temperature, weather.getTemp(), weatherUnit.getTemperatureUnit()));
            feelTemperature.setText(" / " + context.getString(R.string.temperature, weather.getFeelsTemp(), weatherUnit.getTemperatureUnit()));
            averageTemperature.setText(context.getString(R.string.temperature, (weather.getFeelsTemp() + weather.getTemp()) / 2.0, weatherUnit.getTemperatureUnit()));
            Glide.with(context).load(WeatherConstants.getWeatherIconUrl(context, weather.getIcon())).into(weatherIcon);
            weatherDescription.setText(StringUtils.capitalize(weather.getDescription()));
            city.setText(weather.getCity());
            updateDate.setText(context.getString(R.string.updateDate, new LocalDateTime(new Date(weather.getDate())).toString("dd.MM.yyyy HH:mm:ss")));
            weatherInfoAdapter.setData(WeatherConstants.getWeatherData(weather, weatherUnit, context));
        }
    }
}

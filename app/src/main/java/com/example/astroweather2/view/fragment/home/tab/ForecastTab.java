package com.example.astroweather2.view.fragment.home.tab;

import android.content.Context;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.astroweather2.R;
import com.example.astroweather2.constant.WeatherConstants;
import com.example.astroweather2.core.CoreTab;
import com.example.astroweather2.data.model.app.WeatherUnit;
import com.example.astroweather2.data.model.app.weather.Forecast;
import com.example.astroweather2.view.adapter.DayAdapter;
import com.example.astroweather2.view.adapter.WeatherInfoAdapter;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.LocalDateTime;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ForecastTab extends CoreTab {

    private TextView info;
    private List<Forecast> forecast;

    private WeatherUnit weatherUnit;

    private TextView averageTemperature;
    private TextView minTemperature;
    private TextView maxTemperature;
    private ImageView weatherIcon;
    private TextView weatherDescription;
    private TextView city;
    private TextView updateDate;
    private RecyclerView weatherData;
    private RecyclerView daysRecyclerView;

    private View container;

    private final DayAdapter dayAdapter = new DayAdapter();
    private final WeatherInfoAdapter weatherInfoAdapter = new WeatherInfoAdapter();

    public void setForecast(List<Forecast> forecast, WeatherUnit weatherUnit) {
        this.forecast = forecast;
        this.weatherUnit = weatherUnit;
    }

    @Override
    public void bindView(View container) {
        Context context = container.getContext();
        container.scrollTo(0,0);
        this.container = container.findViewById(R.id.container);

        averageTemperature = container.findViewById(R.id.averageTemperature);
        minTemperature = container.findViewById(R.id.minTemperature);
        maxTemperature = container.findViewById(R.id.maxTemperature);
        weatherIcon = container.findViewById(R.id.weatherIcon);
        weatherDescription = container.findViewById(R.id.weatherDescription);
        city = container.findViewById(R.id.city);
        updateDate = container.findViewById(R.id.updateDate);
        weatherData = container.findViewById(R.id.recyclerView);
        daysRecyclerView = container.findViewById(R.id.daysRecyclerView);

        weatherData.setLayoutManager(new LinearLayoutManager(context));
        weatherData.setAdapter(weatherInfoAdapter);

        int spanCount = 1;

        if (forecast != null && forecast.size() > 0){
            spanCount = forecast.size();
        }

        daysRecyclerView.setLayoutManager(new GridLayoutManager(context, spanCount));
        daysRecyclerView.setAdapter(dayAdapter);
        if (forecast != null)
            dayAdapter.setData(getDays());
        dayAdapter.setDayListener(position -> {
            bindData(forecast.get(position), context);
        });

        if (forecast != null && forecast.size() > 0)
            bindData(forecast.get(0), context);
    }

    private List<Pair<String, String>> getDays() {
        List<Pair<String, String>> days = new ArrayList<>();
        for (Forecast f : forecast) {
            days.add(new Pair<>(new LocalDateTime(new Date(f.getDate())).toString("dd"), new LocalDateTime(new Date(f.getDate())).toString("MM")));
        }
        return days;
    }

    private void bindData(Forecast weather, Context context) {
        if (weather != null) {
            container.setVisibility(View.VISIBLE);
            maxTemperature.setText(context.getString(R.string.temperature, weather.getTemp_max(), weatherUnit.getTemperatureUnit()));
            minTemperature.setText(" / " + context.getString(R.string.temperature, weather.getTemp_min(), weatherUnit.getTemperatureUnit()));
            averageTemperature.setText(context.getString(R.string.temperature, (weather.getTemp_max() + weather.getTemp_min()) / 2.0, weatherUnit.getTemperatureUnit()));
            Glide.with(context).load(WeatherConstants.getWeatherIconUrl(context, weather.getIcon())).into(weatherIcon);
            weatherDescription.setText(StringUtils.capitalize(weather.getDescription()));
            city.setText(weather.getCity());
            updateDate.setText(context.getString(R.string.updateDate, new LocalDateTime(new Date(weather.getDate())).toString("dd.MM.yyyy HH:mm:ss")));
            weatherInfoAdapter.setData(WeatherConstants.getWeatherData(weather, weatherUnit, context));
        }
    }
}

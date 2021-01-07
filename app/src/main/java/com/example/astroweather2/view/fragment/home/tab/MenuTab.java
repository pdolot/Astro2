package com.example.astroweather2.view.fragment.home.tab;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.core.content.ContextCompat;

import com.example.astroweather2.R;
import com.example.astroweather2.core.CoreTab;
import com.example.astroweather2.data.model.app.Config;
import com.example.astroweather2.data.model.app.WeatherUnit;

public class MenuTab extends CoreTab {

    private Config config;
    private EditText latitude;
    private EditText longitude;
    private EditText synchronizationInterval;
    private Button metric;
    private Button standard;
    private Button imperial;
    private Button save;
    private Button refreshData;
    private View container;
    private String units;
    private Double lat;
    private Double lng;
    private Integer syncInterval;

    private MenuListener menuListener;

    public void setMenuListener(MenuListener menuListener) {
        this.menuListener = menuListener;
    }

    public void setConfig(Config config) {
        this.config = config;
    }

    @Override
    public void bindView(View container) {
        this.container = container.findViewById(R.id.container);
        container.scrollTo(0, 0);
        Context context = container.getContext();
        latitude = container.findViewById(R.id.latitude);
        longitude = container.findViewById(R.id.longitude);
        synchronizationInterval = container.findViewById(R.id.synchronizationInterval);
        metric = container.findViewById(R.id.metric);
        standard = container.findViewById(R.id.standard);
        imperial = container.findViewById(R.id.imperial);
        save = container.findViewById(R.id.save);
        refreshData = container.findViewById(R.id.refreshData);

        metric.setOnClickListener(v -> {
            units = WeatherUnit.METRIC.name();
            setCurrentUnits(context);
        });

        standard.setOnClickListener(v -> {
            units = WeatherUnit.STANDARD.name();
            setCurrentUnits(context);
        });

        imperial.setOnClickListener(v -> {
            units = WeatherUnit.IMPERIAL.name();
            setCurrentUnits(context);
        });

        save.setOnClickListener(v -> {
            if (menuListener != null && checkDataCoherency())
                menuListener.onSave(lat, lng, syncInterval, units);
        });

        refreshData.setOnClickListener(v -> {
            if (menuListener != null)
                menuListener.onRefreshData();
        });

        if (config != null){
            units = config.getWeatherUnit();
            lat = config.getCurrentLatitude();
            lng = config.getCurrentLongitude();
            syncInterval = config.getSynchronizationInterval();
        }

        bindData(context);
    }

    private boolean checkDataCoherency() {
        lat = Double.valueOf(latitude.getText().toString().replace(",", "."));
        lng = Double.valueOf(longitude.getText().toString().replace(",", "."));
        syncInterval = Integer.valueOf(synchronizationInterval.getText().toString());

        return (lat != 0.0 && lng != 0.0 && syncInterval != 0);
    }

    private void bindData(Context context) {
        if (config != null) {
            container.setVisibility(View.VISIBLE);
            latitude.setText(context.getString(R.string.doubleFormat, config.getCurrentLatitude()));
            longitude.setText(context.getString(R.string.doubleFormat, config.getCurrentLongitude()));
            synchronizationInterval.setText(context.getString(R.string.integerFormat, config.getSynchronizationInterval()));
            setCurrentUnits(context);
        }
    }

    private void setCurrentUnits(Context context) {
        if (units.equals(WeatherUnit.METRIC.name())) {
            metric.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(context, R.color.buttonColor)));
            standard.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(context, R.color.buttonTransparent)));
            imperial.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(context, R.color.buttonTransparent)));
        } else if (units.equals(WeatherUnit.STANDARD.name())) {
            metric.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(context, R.color.buttonTransparent)));
            standard.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(context, R.color.buttonColor)));
            imperial.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(context, R.color.buttonTransparent)));
        } else {
            metric.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(context, R.color.buttonTransparent)));
            standard.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(context, R.color.buttonTransparent)));
            imperial.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(context, R.color.buttonColor)));
        }
    }

    public interface MenuListener {
        void onRefreshData();

        void onSave(Double lat, Double lng, Integer synchronizationInterval, String units);
    }
}

package com.example.astroweather2.view.fragment.home.tab;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.astroweather2.R;
import com.example.astroweather2.constant.AstronomyConstants;
import com.example.astroweather2.core.CoreTab;
import com.example.astroweather2.data.model.app.astronomy.SunData;
import com.example.astroweather2.view.adapter.WeatherInfoAdapter;

import org.joda.time.LocalDateTime;

import java.util.Date;

public class SunTab extends CoreTab {

    private SunData sunData;
    private TextView updateDate;
    private RecyclerView recyclerView;
    private View container;
    private final WeatherInfoAdapter weatherInfoAdapter = new WeatherInfoAdapter();

    public void setSunData(SunData sunData) {
        this.sunData = sunData;
    }

    @Override
    public void bindView(View container) {
        this.container = container.findViewById(R.id.container);
        container.scrollTo(0,0);
        updateDate = container.findViewById(R.id.updateDate);
        recyclerView = container.findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));
        recyclerView.setAdapter(weatherInfoAdapter);

        bindData(container.getContext());
    }

    private void bindData(Context context){
        if (sunData != null) {
            container.setVisibility(View.VISIBLE);
            updateDate.setText(context.getString(R.string.updateDate, new LocalDateTime(new Date(sunData.getUpdateTime())).toString("dd.MM.yyyy HH:mm:ss")));
            weatherInfoAdapter.setData(AstronomyConstants.getSunData(sunData));
        }
    }
}

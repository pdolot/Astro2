package com.example.astroweather2.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.astroweather2.R;
import com.example.astroweather2.core.CoreTab;
import com.example.astroweather2.view.fragment.home.tab.ForecastTab;
import com.example.astroweather2.view.fragment.home.tab.LocationsTab;
import com.example.astroweather2.view.fragment.home.tab.MenuTab;
import com.example.astroweather2.view.fragment.home.tab.MoonTab;
import com.example.astroweather2.view.fragment.home.tab.SunTab;
import com.example.astroweather2.view.fragment.home.tab.WeatherTab;

import java.util.List;

public class TabAdapter extends RecyclerView.Adapter<TabAdapter.ViewHolder> {

    private List<CoreTab> tabs;

    private int viewWidth = 0;

    public void setViewWidth(int viewWidth) {
        this.viewWidth = viewWidth;
    }

    public void setTabs(List<CoreTab> tabs) {
        this.tabs = tabs;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
        layoutParams.width = viewWidth;
        holder.itemView.setLayoutParams(layoutParams);

        CoreTab coreTab = tabs.get(position);
        coreTab.bindView(holder.itemView);
    }

    @Override
    public int getItemCount() {
        if (tabs != null) {
            return tabs.size();
        } else {
            return 0;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (tabs != null) {
            if (tabs.get(position) instanceof WeatherTab) {
                return R.layout.tab_weather;
            }
            if (tabs.get(position) instanceof ForecastTab) {
                return R.layout.tab_forecast;
            }
            if (tabs.get(position) instanceof SunTab) {
                return R.layout.tab_sun;
            }
            if (tabs.get(position) instanceof MoonTab) {
                return R.layout.tab_moon;
            }
            if (tabs.get(position) instanceof LocationsTab) {
                return R.layout.tab_locations;
            }
            if (tabs.get(position) instanceof MenuTab) {
                return R.layout.tab_menu;
            }
        }
        return R.layout.tab_empty;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}

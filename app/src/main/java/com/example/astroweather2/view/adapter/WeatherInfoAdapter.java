package com.example.astroweather2.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.astroweather2.R;
import com.example.astroweather2.data.model.app.WeatherInfo;

import java.util.List;

public class WeatherInfoAdapter extends RecyclerView.Adapter<WeatherInfoAdapter.ViewHolder> {

    private List<WeatherInfo> data;

    public void setData(List<WeatherInfo> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_weather, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TextView label = holder.itemView.findViewById(R.id.label);
        TextView value = holder.itemView.findViewById(R.id.value);
        ImageView icon = holder.itemView.findViewById(R.id.icon);
        View separator = holder.itemView.findViewById(R.id.separator);

        WeatherInfo weatherInfo = data.get(position);

        if (weatherInfo.getIcon() != null) {
            icon.setVisibility(View.VISIBLE);
            icon.setImageResource(weatherInfo.getIcon());
        } else {
            icon.setVisibility(View.GONE);
        }

        label.setText(weatherInfo.getLabel());
        value.setText(weatherInfo.getValue());

        if (position == getItemCount() - 1) {
            separator.setVisibility(View.GONE);
        } else {
            separator.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        if (data != null) {
            return data.size();
        } else {
            return 0;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}

package com.example.astroweather2.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.astroweather2.R;
import com.example.astroweather2.data.model.app.WeatherInfo;
import com.example.astroweather2.data.model.app.weather.Location;

import java.util.List;

public class LocationsAdapter extends RecyclerView.Adapter<LocationsAdapter.ViewHolder> {

    private List<Location> data;
    private String currentCity;
    private LocationAdapterListener locationAdapterListener;

    public void setLocationAdapterListener(LocationAdapterListener locationAdapterListener) {
        this.locationAdapterListener = locationAdapterListener;
    }

    public void setData(List<Location> data, String currentCity) {
        this.data = data;
        this.currentCity = currentCity;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_location, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        TextView city = holder.itemView.findViewById(R.id.city);
        CheckBox currentSelector = holder.itemView.findViewById(R.id.currentSelector);
        ImageView removeLocation = holder.itemView.findViewById(R.id.removeLocation);
        View separator = holder.itemView.findViewById(R.id.separator);

        Location location = data.get(position);

        Boolean isCurrent = currentCity.equals(location.getCity());
        city.setText(location.getCity());
        currentSelector.setChecked(isCurrent);

        currentSelector.setClickable(!isCurrent);

        currentSelector.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (locationAdapterListener != null)
                locationAdapterListener.onChangeCurrentLocation(location.getCity(), location.getLatitude(), location.getLongitude());
        });

        removeLocation.setOnClickListener(v -> {
            if (!isCurrent){
                locationAdapterListener.onRemoveLocation(location.getId());
            }else{
                Toast.makeText(holder.itemView.getContext(), "Nie można usunąć. Zmień obecną lokalizacje", Toast.LENGTH_SHORT).show();
            }

        });

        if (position == getItemCount() -1 ){
            separator.setVisibility(View.GONE);
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

    public interface LocationAdapterListener{
        void onRemoveLocation(Long id);
        void onChangeCurrentLocation(String city, Double lat, Double lng);
    }
}

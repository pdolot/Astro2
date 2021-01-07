package com.example.astroweather2.view.fragment.home.tab;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.astroweather2.R;
import com.example.astroweather2.core.CoreTab;
import com.example.astroweather2.data.model.app.weather.Location;
import com.example.astroweather2.view.adapter.LocationsAdapter;

import java.util.List;

public class LocationsTab extends CoreTab {

    private String city;
    private List<Location> locations;

    private RecyclerView recyclerView;
    private LocationsAdapter.LocationAdapterListener locationAdapterListener;
    private LocationTabListener locationTabListener;
    private Button addLocation;
    private EditText cityInput;
    private View container;
    private final LocationsAdapter locationsAdapter = new LocationsAdapter();

    public void setLocations(List<Location> locations, String city) {
        this.locations = locations;
        this.city = city;
    }

    public void setLocationAdapterListener(LocationsAdapter.LocationAdapterListener locationAdapterListener) {
        this.locationAdapterListener = locationAdapterListener;
    }

    public void setLocationTabListener(LocationTabListener locationTabListener) {
        this.locationTabListener = locationTabListener;
    }

    @Override
    public void bindView(View container) {
        container.scrollTo(0,0);
        this.container = container.findViewById(R.id.container);
        recyclerView = container.findViewById(R.id.recyclerView);
        addLocation = container.findViewById(R.id.addLocation);
        cityInput = container.findViewById(R.id.city);

        recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));
        recyclerView.setAdapter(locationsAdapter);

        locationsAdapter.setLocationAdapterListener(locationAdapterListener);

        addLocation.setOnClickListener(v -> {
            if (locationTabListener != null && !cityInput.getText().toString().equals(""))
                locationTabListener.onAddLocation(cityInput.getText().toString().trim());
        });

        bindData();
    }

    private void bindData() {
        if (locations != null)
            container.setVisibility(View.VISIBLE);
            locationsAdapter.setData(locations, city);
    }

    public interface LocationTabListener{
        void onAddLocation(String city);
    }
}

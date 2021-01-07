package com.example.astroweather2.view.fragment.home;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.example.astroweather2.R;
import com.example.astroweather2.view.adapter.TabAdapter;
import com.example.astroweather2.view.adapter.TabNameAdapter;
import com.example.astroweather2.viewModel.HomeViewModel;

public class HomeFragment extends Fragment {

    private HomeViewModel viewModel;

    private final TabNameAdapter tabNameAdapter = new TabNameAdapter();
    private final TabNameAdapter additionalTabNameAdapter = new TabNameAdapter();
    private final TabAdapter tabAdapter = new TabAdapter();

    private RecyclerView tabsNamesRecyclerView;
    private RecyclerView additionalTabsNamesRecyclerView;
    private RecyclerView tabsRecyclerView;

    private TextView city;
    private TextView latitude;
    private TextView longitude;

    private SnapHelper snapHelper = new LinearSnapHelper();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        prepareRecyclerViews();
        prepareAdapters();
        observeTabsChange();
        observerDbData();
    }

    private void observeTabsChange() {
        viewModel.tabsNames.observe(getViewLifecycleOwner(), tabs -> {
            if (tabs.size() > 0) {
                tabsNamesRecyclerView.setVisibility(View.VISIBLE);
                tabNameAdapter.setTabsNames(tabs);
                setLayoutManagerSpanCount(tabsNamesRecyclerView, tabs.size());
            } else {
                tabsNamesRecyclerView.setVisibility(View.GONE);
            }
        });

        viewModel.additionalTabNames.observe(getViewLifecycleOwner(), tabs -> {
            if (tabs.size() > 0) {
                additionalTabsNamesRecyclerView.setVisibility(View.VISIBLE);
                additionalTabNameAdapter.setCurrentPosition(viewModel.getAdditionalTabNamesAdapterPosition());
                additionalTabNameAdapter.setTabsNames(tabs);
                setLayoutManagerSpanCount(additionalTabsNamesRecyclerView, tabs.size());

                tabsRecyclerView.scrollToPosition(viewModel.getTabsAdapterPosition());
            } else {
                additionalTabsNamesRecyclerView.setVisibility(View.GONE);
            }
        });

        viewModel.tabs.observe(getViewLifecycleOwner(), tabs -> {
            if (tabs.size() > 0) {
                tabAdapter.setTabs(tabs);
                tabsRecyclerView.scrollToPosition(viewModel.getTabsAdapterPosition());
            }
        });
    }

    private void observerDbData() {

        viewModel.dbRepository.getConfig().observe(getViewLifecycleOwner(), config -> {
            viewModel.setCurrentConfig(config);
            city.setText(config.getCity());
            latitude.setText(String.valueOf(config.getCurrentLatitude()));
            longitude.setText(String.valueOf(config.getCurrentLongitude()));
        });

        viewModel.dbRepository.getAllLocations().observe(getViewLifecycleOwner(), locations -> {
            viewModel.setLocations(locations);
        });

        viewModel.dbRepository.getMoonData().observe(getViewLifecycleOwner(), moonData -> {
            viewModel.setCurrentMoonData(moonData);
        });

        viewModel.dbRepository.getSunData().observe(getViewLifecycleOwner(), sunData -> {
            viewModel.setCurrentSunData(sunData);
        });

        viewModel.dbRepository.observeWeatherChange().observe(getViewLifecycleOwner(), count -> {
            if (count > 0)
                viewModel.getWeather();
        });

        viewModel.dbRepository.observeForecastChange().observe(getViewLifecycleOwner(), count -> {
            if (count > 0)
                viewModel.getForecast();
        });

        viewModel.error.observe(getViewLifecycleOwner(), error -> {
            Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
        });


    }

    private void findViews(View view) {
        tabsNamesRecyclerView = view.findViewById(R.id.tabsNamesRecyclerView);
        additionalTabsNamesRecyclerView = view.findViewById(R.id.additionalTabsNamesRecyclerView);
        tabsRecyclerView = view.findViewById(R.id.tabsRecyclerView);
        city = view.findViewById(R.id.city);
        latitude = view.findViewById(R.id.latitude);
        longitude = view.findViewById(R.id.longitude);
    }

    private void prepareAdapters() {
        tabNameAdapter.setCurrentPosition(viewModel.getTabNamesAdapterPosition());
        additionalTabNameAdapter.setCurrentPosition(viewModel.getAdditionalTabNamesAdapterPosition());

        tabNameAdapter.setTabNameListener(position -> {
            viewModel.setTabNamesAdapterPosition(position);
            viewModel.setAdditionalTabNamesAdapterPosition(0);
            viewModel.setTabsAdapterPosition(0);
        });

        additionalTabNameAdapter.setTabNameListener(position -> {
            viewModel.setAdditionalTabNamesAdapterPosition(position);
            tabsRecyclerView.smoothScrollToPosition(position);
        });
    }

    private void prepareRecyclerViews() {
        tabsNamesRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1));
        tabsNamesRecyclerView.setAdapter(tabNameAdapter);

        additionalTabsNamesRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1));
        additionalTabsNamesRecyclerView.setAdapter(additionalTabNameAdapter);

        tabsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        tabsRecyclerView.setAdapter(tabAdapter);
        snapHelper.attachToRecyclerView(tabsRecyclerView);

        int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;

        if (getResources().getBoolean(R.bool.isTablet) || getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            additionalTabNameAdapter.disableChangePosition();
            tabAdapter.setViewWidth(screenWidth / 2);
        } else {
            tabAdapter.setViewWidth(screenWidth);
        }

        addScrollListener();
    }

    private void addScrollListener() {
        tabsRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                if (layoutManager != null) {
                    View snapView = snapHelper.findSnapView(layoutManager);
                    if (snapView != null) {
                        int position = layoutManager.getPosition(snapView);
                        additionalTabNameAdapter.setCurrentPosition(position);
                        viewModel.setAdditionalTabNamesAdapterPosition(position);
                        viewModel.setTabsAdapterPosition(position);
                    }
                }
            }
        });
    }

    private void setLayoutManagerSpanCount(RecyclerView recyclerView, int spanCount) {
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            ((GridLayoutManager) layoutManager).setSpanCount(spanCount);
        }
    }
}

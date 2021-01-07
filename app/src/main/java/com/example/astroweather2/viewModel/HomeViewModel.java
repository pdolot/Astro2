package com.example.astroweather2.viewModel;

import androidx.lifecycle.MutableLiveData;

import com.example.astroweather2.constant.TabConstants;
import com.example.astroweather2.core.CoreTab;
import com.example.astroweather2.core.CoreViewModel;
import com.example.astroweather2.data.db.DbRepository;
import com.example.astroweather2.data.model.app.Config;
import com.example.astroweather2.data.model.app.WeatherUnit;
import com.example.astroweather2.data.model.app.astronomy.MoonData;
import com.example.astroweather2.data.model.app.astronomy.SunData;
import com.example.astroweather2.data.model.app.weather.Forecast;
import com.example.astroweather2.data.model.app.weather.Location;
import com.example.astroweather2.data.model.app.weather.Weather;
import com.example.astroweather2.data.model.view.AdditionalTab;
import com.example.astroweather2.data.model.view.Tab;
import com.example.astroweather2.data.rest.RestRepository;
import com.example.astroweather2.dependencyInjection.DependencyInjector;
import com.example.astroweather2.view.adapter.LocationsAdapter;
import com.example.astroweather2.view.fragment.home.tab.ForecastTab;
import com.example.astroweather2.view.fragment.home.tab.LocationsTab;
import com.example.astroweather2.view.fragment.home.tab.MenuTab;
import com.example.astroweather2.view.fragment.home.tab.MoonTab;
import com.example.astroweather2.view.fragment.home.tab.SunTab;
import com.example.astroweather2.view.fragment.home.tab.WeatherTab;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomeViewModel extends CoreViewModel implements LocationsAdapter.LocationAdapterListener, LocationsTab.LocationTabListener, MenuTab.MenuListener {

    @Inject
    public DbRepository dbRepository;

    @Inject
    public RestRepository restRepository;

    public MutableLiveData<List<String>> tabsNames = new MutableLiveData<>();
    public MutableLiveData<List<String>> additionalTabNames = new MutableLiveData<>();
    public MutableLiveData<List<CoreTab>> tabs = new MutableLiveData<>();
    public MutableLiveData<String> error = new MutableLiveData<>();

    private Integer tabNamesAdapterPosition = 0;
    private Integer additionalTabNamesAdapterPosition = 0;
    private Integer tabsAdapterPosition = 0;

    private WeatherTab weatherTab = new WeatherTab();
    private ForecastTab forecastTab = new ForecastTab();

    private SunTab sunTab = new SunTab();
    private MoonTab moonTab = new MoonTab();

    private LocationsTab locationsTab = new LocationsTab();
    private MenuTab menuTab = new MenuTab();

    private Weather currentWeather;
    private List<Forecast> currentForecast;
    private Config currentConfig;
    private List<Location> locations;
    private SunData currentSunData;
    private MoonData currentMoonData;

    public Disposable astronomySynchronizationWorker;
    public Disposable weatherSynchronizationWorker;

    private int syncInterval = 0;

    public HomeViewModel() {
        DependencyInjector.applicationComponent.inject(this);
        tabsNames.postValue(getTabsNames());
        additionalTabNames.postValue(getAdditionalTabsNames());
        tabs.postValue(getTabs());
    }

    private List<Tab> getAllTabs() {
        return TabConstants.getTabs(weatherTab, forecastTab, sunTab, moonTab, locationsTab, menuTab);
    }

    public void setCurrentSunData(SunData currentSunData) {
        this.currentSunData = currentSunData;
        invalidateTabs();
    }

    public void setCurrentMoonData(MoonData currentMoonData) {
        this.currentMoonData = currentMoonData;
        invalidateTabs();
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
        invalidateTabs();
    }

    public void setCurrentConfig(Config currentConfig) {
        this.currentConfig = currentConfig;
        if (currentConfig != null) {
            if (currentConfig.getCity() != null)
                fetchWeatherData();


            if (syncInterval == 0 || currentConfig.getSynchronizationInterval() != syncInterval || !currentConfig.getWeatherUnit().equals(this.currentConfig.getWeatherUnit())) {
                syncInterval = currentConfig.getSynchronizationInterval();
                fetchAstronomyData();
                fetchWeatherData();
            }

            invalidateTabs();
        }
        getWeather();
        getForecast();
    }

    public Integer getTabNamesAdapterPosition() {
        return tabNamesAdapterPosition;
    }

    public void setTabNamesAdapterPosition(Integer tabNamesAdapterPosition) {
        this.tabNamesAdapterPosition = tabNamesAdapterPosition;
        additionalTabNamesAdapterPosition = 0;
        additionalTabNames.postValue(getAdditionalTabsNames());
        tabsAdapterPosition = 0;
        tabs.postValue(getTabs());
    }

    public Integer getAdditionalTabNamesAdapterPosition() {
        return additionalTabNamesAdapterPosition;
    }

    public void setAdditionalTabNamesAdapterPosition(Integer additionalTabNamesAdapterPosition) {
        this.additionalTabNamesAdapterPosition = additionalTabNamesAdapterPosition;
    }

    public Integer getTabsAdapterPosition() {
        return tabsAdapterPosition;
    }

    public void setTabsAdapterPosition(Integer tabsAdapterPosition) {
        this.tabsAdapterPosition = tabsAdapterPosition;
    }

    private List<String> getTabsNames() {
        List<String> tabNames = new ArrayList<>();
        for (Tab tab : getAllTabs()) {
            tabNames.add(tab.getTabName());
        }
        return tabNames;
    }

    private List<String> getAdditionalTabsNames() {
        List<String> tabNames = new ArrayList<>();
        for (AdditionalTab tab : getAllTabs().get(tabNamesAdapterPosition).getTabs()) {
            tabNames.add(tab.getTabName());
        }
        return tabNames;
    }

    private List<CoreTab> getTabs() {
        List<CoreTab> tabs = new ArrayList<>();
        for (AdditionalTab tab : getAllTabs().get(tabNamesAdapterPosition).getTabs()) {
            tabs.add(tab.getTab());
        }
        return tabs;
    }

    public void getWeather() {
        if (currentConfig != null) {
            if (currentConfig.getCity() != null) {
                rxDisposer.add(dbRepository.getWeather(currentConfig.getCity())
                        .subscribe(weather -> {
                            this.currentWeather = weather;
                            invalidateTabs();
                        }, error -> {
                            this.currentWeather = null;
                            invalidateTabs();
                        }));
            }
        }
    }

    public void getForecast() {
        if (currentConfig != null) {
            if (currentConfig.getCity() != null) {
                rxDisposer.add(dbRepository.getForecast(currentConfig.getCity())
                        .subscribe(forecasts -> {
                            this.currentForecast = forecasts;
                            invalidateTabs();
                        }, error -> {
                            this.currentForecast = null;
                            invalidateTabs();
                        }));
            }
        }
    }

    public void fetchAstronomyData() {
        if (currentConfig != null) {
            if (astronomySynchronizationWorker != null) {
                rxDisposer.remove(astronomySynchronizationWorker);
            }

            astronomySynchronizationWorker = restRepository
                    .fetchAstronomyData(currentConfig.getCurrentLatitude().toString(), currentConfig.getCurrentLongitude().toString())
                    .repeatWhen(s -> s.delay(syncInterval, TimeUnit.MINUTES))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(astronomyResponse -> {
                        rxDisposer.add(dbRepository.insertAstronomyData(astronomyResponse).subscribe());
                    }, e -> {
                        this.error.postValue("Brak internetu");
                    });

            rxDisposer.add(astronomySynchronizationWorker);
        }
    }

    public void fetchWeatherData() {
        if (currentConfig != null) {
            if (currentConfig.getCity() != null) {
                if (weatherSynchronizationWorker != null) {
                    rxDisposer.remove(weatherSynchronizationWorker);
                }

                weatherSynchronizationWorker = restRepository
                        .fetchWeather(currentConfig.getCurrentLatitude().toString(), currentConfig.getCurrentLongitude().toString(), currentConfig.getWeatherUnit(), currentConfig.getCity())
                        .repeatWhen(s -> s.delay(syncInterval, TimeUnit.MINUTES))
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(weatherResponse -> {
                            rxDisposer.add(dbRepository.insertWeather(weatherResponse).subscribe());
                        }, e -> {
                            this.error.postValue("Brak internetu");
                        });

                rxDisposer.add(weatherSynchronizationWorker);
            }
        }

    }

    private void invalidateTabs() {
        if (currentWeather != null) {
            weatherTab.setWeather(currentWeather, WeatherUnit.valueOf(currentConfig.getWeatherUnit()));
        }

        if (currentForecast != null) {
            forecastTab.setForecast(currentForecast, WeatherUnit.valueOf(currentConfig.getWeatherUnit()));
        }

        if (currentSunData != null) {
            sunTab.setSunData(currentSunData);
        }

        if (currentMoonData != null) {
            moonTab.setMoonData(currentMoonData);
        }

        if (locations != null) {
            if (currentConfig != null) {
                locationsTab.setLocations(locations, currentConfig.getCity());
                locationsTab.setLocationAdapterListener(this);
                locationsTab.setLocationTabListener(this);
            }
        }

        if (currentConfig != null) {
            menuTab.setConfig(currentConfig);
            menuTab.setMenuListener(this);
        }

        tabs.postValue(getTabs());
    }

    @Override
    public void onRemoveLocation(Long id) {
        rxDisposer.add(dbRepository.deleteLocation(id));
    }

    @Override
    public void onChangeCurrentLocation(String city, Double lat, Double lng) {
        rxDisposer.add(
                dbRepository.updateConfig(currentConfig.getId(), city, lat, lng)
                        .subscribe()
        );
    }

    @Override
    public void onAddLocation(String city) {
        rxDisposer.add(restRepository.fetchLocation(city)
                .subscribe(location -> {
                    rxDisposer.add(dbRepository.insert(location).subscribe(
                            success -> {

                            },
                            e -> {
                                error.postValue("Błąd podczas dodawania lokalizacji");
                            }
                    ));
                }, e -> {
                    error.postValue("Lokalizacja nie istnieje");
                }));
    }

    @Override
    public void onRefreshData() {
        fetchAstronomyData();
        fetchWeatherData();
    }

    @Override
    public void onSave(Double lat, Double lng, Integer synchronizationInterval, String units) {
        rxDisposer.add(
                dbRepository.updateConfig(currentConfig.getId(), lat, lng, synchronizationInterval, units)
                        .subscribe()
        );
    }
}

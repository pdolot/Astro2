package com.example.astroweather2.constant;

import com.example.astroweather2.data.model.view.AdditionalTab;
import com.example.astroweather2.data.model.view.Tab;
import com.example.astroweather2.view.fragment.home.tab.ForecastTab;
import com.example.astroweather2.view.fragment.home.tab.LocationsTab;
import com.example.astroweather2.view.fragment.home.tab.MenuTab;
import com.example.astroweather2.view.fragment.home.tab.MoonTab;
import com.example.astroweather2.view.fragment.home.tab.SunTab;
import com.example.astroweather2.view.fragment.home.tab.WeatherTab;

import java.util.ArrayList;
import java.util.List;

public class TabConstants {

    public static List<Tab> getTabs(WeatherTab weatherTab, ForecastTab forecastTab, SunTab sunTab, MoonTab moonTab, LocationsTab locationsTab, MenuTab menuTab) {
        List<Tab> tabs = new ArrayList<>();
        tabs.add(new Tab("Pogoda", getWeatherTabs(weatherTab, forecastTab)));
        tabs.add(new Tab("Astronomia", getAstronomyTabs(sunTab, moonTab)));
        tabs.add(new Tab("Menu", getMenuTabs(locationsTab, menuTab)));
        return tabs;
    }

    private static List<AdditionalTab> getWeatherTabs(WeatherTab weatherTab, ForecastTab forecastTab) {
        List<AdditionalTab> tabs = new ArrayList<>();
        tabs.add(new AdditionalTab("Aktualna", weatherTab));
        tabs.add(new AdditionalTab("Prognoza", forecastTab));
        return tabs;
    }

    private static List<AdditionalTab> getAstronomyTabs(SunTab sunTab, MoonTab moonTab) {
        List<AdditionalTab> tabs = new ArrayList<>();
        tabs.add(new AdditionalTab("Słońce", sunTab));
        tabs.add(new AdditionalTab("Księżyc", moonTab));
        return tabs;
    }

    private static List<AdditionalTab> getMenuTabs(LocationsTab locationsTab, MenuTab menuTab) {
        List<AdditionalTab> tabs = new ArrayList<>();
        tabs.add(new AdditionalTab("Lokalizacje", locationsTab));
        tabs.add(new AdditionalTab("Ustawienia", menuTab));
        return tabs;
    }
}

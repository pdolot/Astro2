package com.example.astroweather2.data.model.app;

public enum WeatherUnit {

    STANDARD("K", "m/s"),
    METRIC("°C", "m/s"),
    IMPERIAL("°F", "mile/s");

    private final String temperatureUnit;
    private final String windSpeedUnit;

    WeatherUnit(String temperatureUnit, String windSpeedUnit) {
        this.temperatureUnit = temperatureUnit;
        this.windSpeedUnit = windSpeedUnit;
    }

    public String getTemperatureUnit() {
        return temperatureUnit;
    }

    public String getWindSpeedUnit() {
        return windSpeedUnit;
    }
}

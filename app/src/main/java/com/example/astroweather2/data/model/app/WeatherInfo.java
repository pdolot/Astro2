package com.example.astroweather2.data.model.app;

public class WeatherInfo {
    private Integer icon;
    private String label;
    private String value;

    public WeatherInfo(Integer icon, String label, String value) {
        this.icon = icon;
        this.label = label;
        this.value = value;
    }

    public Integer getIcon() {
        return icon;
    }

    public void setIcon(Integer icon) {
        this.icon = icon;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

package com.example.astroweather2.data.model.view;

import java.util.List;

public class Tab {
    private String tabName;
    private List<AdditionalTab> tabs;

    public Tab(String tabName, List<AdditionalTab> tabs) {
        this.tabName = tabName;
        this.tabs = tabs;
    }

    public String getTabName() {
        return tabName;
    }

    public void setTabName(String tabName) {
        this.tabName = tabName;
    }

    public List<AdditionalTab> getTabs() {
        return tabs;
    }

    public void setTabs(List<AdditionalTab> tabs) {
        this.tabs = tabs;
    }
}

package com.example.astroweather2.data.model.view;

import com.example.astroweather2.core.CoreTab;

public class AdditionalTab {
    private String tabName;
    private CoreTab tab;

    public AdditionalTab(String tabName, CoreTab tab) {
        this.tabName = tabName;
        this.tab = tab;
    }

    public String getTabName() {
        return tabName;
    }

    public void setTabName(String tabName) {
        this.tabName = tabName;
    }

    public CoreTab getTab() {
        return tab;
    }

    public void setTab(CoreTab tab) {
        this.tab = tab;
    }
}

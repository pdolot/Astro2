package com.example.astroweather2;

import android.app.Application;

import com.example.astroweather2.dependencyInjection.DependencyInjector;

public class CoreApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DependencyInjector.init(this);
    }
}

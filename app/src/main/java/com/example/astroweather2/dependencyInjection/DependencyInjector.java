package com.example.astroweather2.dependencyInjection;

import com.example.astroweather2.CoreApp;

public class DependencyInjector {
    public static ApplicationComponent applicationComponent;

    public static void init(CoreApp coreApp){
        applicationComponent = DaggerApplicationComponent.builder()
                .restModule(new RestModule())
                .dbModule(new DbModule(coreApp))
                .build();
    }
}

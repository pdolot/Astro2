package com.example.astroweather2.dependencyInjection;

import com.example.astroweather2.view.MainActivity;
import com.example.astroweather2.viewModel.HomeViewModel;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
        modules = {
                DbModule.class,
                RestModule.class
        }
)
public interface ApplicationComponent {
    void inject(MainActivity mainActivity);

    void inject(HomeViewModel homeViewModel);
}

package com.example.astroweather2.core;

import androidx.lifecycle.ViewModel;

import io.reactivex.disposables.CompositeDisposable;

public class CoreViewModel extends ViewModel {
    public CompositeDisposable rxDisposer = new CompositeDisposable();

    @Override
    protected void onCleared() {
        rxDisposer.dispose();
        rxDisposer = null;
        super.onCleared();
    }
}

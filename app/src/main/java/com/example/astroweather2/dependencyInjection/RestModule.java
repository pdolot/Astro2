package com.example.astroweather2.dependencyInjection;

import com.example.astroweather2.data.rest.RestRepository;
import com.example.astroweather2.data.rest.RestService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class RestModule {

    @Provides
    @Singleton
    public Retrofit provideWeatherRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("https://api.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(new OkHttpClient.Builder().build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    public RestService provideRestService(Retrofit retrofit){
        return retrofit.create(RestService.class);
    }

    @Provides
    @Singleton
    public RestRepository provideRestRepository(RestService restService){
        return new RestRepository(restService);
    }
}

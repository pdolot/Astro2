package com.example.astroweather2.dependencyInjection;

import androidx.room.Room;

import com.example.astroweather2.CoreApp;
import com.example.astroweather2.constant.DbConstants;
import com.example.astroweather2.data.db.DbDao;
import com.example.astroweather2.data.db.DbRepository;
import com.example.astroweather2.data.db.LocalDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DbModule {

    private CoreApp app;

    public DbModule(CoreApp app) {
        this.app = app;
    }

    @Singleton
    @Provides
    public LocalDatabase provideDatabase() {
        return Room.databaseBuilder(app.getApplicationContext(), LocalDatabase.class, DbConstants.DB_NAME)
                .fallbackToDestructiveMigration()
                .build();
    }

    @Singleton
    @Provides
    public DbDao provideDao(LocalDatabase db) {
        return db.dataDao();
    }

    @Singleton
    @Provides
    public DbRepository provideRepository(DbDao dao) {
        return new DbRepository(dao);
    }
}

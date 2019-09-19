package com.example.test.di;

import android.app.Application;

public class BaseApplication extends Application {

    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder().build();
    }

    public static AppComponent getAppComponent(){
        return appComponent;
    }
}

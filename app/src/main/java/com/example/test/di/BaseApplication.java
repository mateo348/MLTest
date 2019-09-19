package com.example.test.di;

import android.app.Application;

public class BaseApplication extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent
                .builder()
                .application(this)
                .build();


    }

    public AppComponent getAppComponent(){
        return appComponent;
    }
}

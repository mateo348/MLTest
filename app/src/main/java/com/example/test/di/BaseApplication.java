package com.example.test.di;

import android.app.Application;

/**
 * Se extiene Applicacion para poder injectar el componente base utilizado por Dagger2
 * y tener acceso Application desde los viewModel, por ejemplo, sin tener dependencia de una avtivity que los provea
 */
public class BaseApplication extends Application {

    private static BaseApplication instance;
    private static AppComponent appComponent;


    public static BaseApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        appComponent = DaggerAppComponent.builder().build();
    }

    public static AppComponent getAppComponent(){
        return appComponent;
    }
}

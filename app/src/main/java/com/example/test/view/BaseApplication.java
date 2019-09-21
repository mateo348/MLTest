package com.example.test.view;

import android.app.Application;
import com.example.test.di.base.BaseComponent;
import com.example.test.di.base.DaggerBaseComponent;

/**
 * Se extiene Applicacion para poder injectar el componente base utilizado por Dagger2
 * y tener acceso Application desde los viewModel, por ejemplo, sin tener dependencia de una avtivity que los provea
 */
public class BaseApplication extends Application {

    private static BaseApplication instance;
    private static BaseComponent baseComponent;


    public static BaseApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        baseComponent = DaggerBaseComponent.builder().build();
    }

    public static BaseComponent getBaseComponent(){
        return baseComponent;
    }
}

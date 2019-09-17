package com.example.test.di;

import android.app.Application;
import com.example.test.service.IItemService;
import com.example.test.service.ItemService;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;

@Module
public class ServiceModule {

    @Singleton
    @Provides
    IItemService provideIItemService(Application application){
        return new ItemService(application);
    }
}

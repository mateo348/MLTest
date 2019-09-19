package com.example.test;

import android.app.Application;

import com.example.test.service.IItemService;
import com.example.test.service.ItemService;

import org.mockito.Mockito;

import java.time.Clock;

import javax.inject.Singleton;
import dagger.Provides;

public class MockServiceModule {

    @Singleton
    @Provides
    IItemService provideIItemService(Application application){
         return Mockito.mock(ItemService.class);
    }
}

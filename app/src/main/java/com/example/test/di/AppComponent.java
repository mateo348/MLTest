package com.example.test.di;

import com.example.test.service.IItemService;
import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    IItemService provideIItemService();
}

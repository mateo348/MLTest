package com.example.test;

import com.example.test.di.AppComponent;
import com.example.test.di.AppModule;
import com.example.test.di.ItemsListComponent;
import com.example.test.di.ItemsListModule;
import com.example.test.di.ItemsListScope;

import javax.inject.Singleton;

import dagger.Component;

@ItemsListScope
@Component(dependencies = AppComponent.class, modules = ItemsListModule.class)
public interface TestComponent extends ItemsListComponent {
    void inject(ItemServiceTest mainActivityTest);


}
package com.example.test;

import com.example.test.di.base.BaseComponent;
import com.example.test.di.ItemsList.ItemsListComponent;
import com.example.test.di.ItemsList.ItemsListModule;
import com.example.test.di.ItemsList.ItemsListScope;

import dagger.Component;

@ItemsListScope
@Component(dependencies = BaseComponent.class, modules = ItemsListModule.class)
public interface TestComponent extends ItemsListComponent {
    void inject(ItemServiceImplTest mainActivityTest);


}
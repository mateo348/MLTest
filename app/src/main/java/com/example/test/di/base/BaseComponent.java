package com.example.test.di.base;

import com.example.test.service.ItemService;
import javax.inject.Singleton;

import dagger.Component;

/**
 * Dagger2: Componente de clases que se desean utilizar desde toda la aplicacion.
 * En este caso solo se expone ItemService
 */
@Singleton
@Component(modules = BaseModule.class)
public interface BaseComponent {
    ItemService provideIItemService();
}

package com.example.test.di;

import com.example.test.service.IItemService;
import javax.inject.Singleton;

import dagger.Component;

/**
 * Dagger2: Componente de clases que se desean utilizar desde toda la aplicacion.
 * En este caso solo se expone IItemService
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    IItemService provideIItemService();
}

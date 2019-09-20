package com.example.test.di;

import com.example.test.view.itemList.ItemListActivity;
import dagger.Component;

/**
 * Dagger2: Componente de DI utilizado dentro del scope de la activity ItemDetail.
 * Ademas tiene acceso a los que expone AppComponent
 */
@ItemsListScope
@Component(dependencies = AppComponent.class, modules = ItemsListModule.class)
public interface ItemsListComponent {
    void inject(ItemListActivity itemListActivity);
}

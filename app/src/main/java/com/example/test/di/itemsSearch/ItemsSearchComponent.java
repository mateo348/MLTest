package com.example.test.di.itemsSearch;

import com.example.test.di.base.BaseComponent;
import com.example.test.view.itemsSearch.ItemsSearchActivity;
import dagger.Component;

/**
 * Dagger2: Componente de DI utilizado dentro del scope de la activity ItemDetail.
 * Ademas tiene acceso a los que expone BaseComponent
 */
@ItemsSearchScope
@Component(dependencies = BaseComponent.class, modules = ItemsSearchModule.class)
public interface ItemsSearchComponent {
    void inject(ItemsSearchActivity itemsSearchActivity);
}

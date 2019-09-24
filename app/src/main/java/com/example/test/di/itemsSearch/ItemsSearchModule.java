package com.example.test.di.itemsSearch;

import com.example.test.service.ItemService;
import com.example.test.view.itemsSearch.ItemsSearchViewModelFactory;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger2: Modulo para DI de ItemDetails, a la cual se le pasa por parametro el ID del Item seleccionado
 */
@Module
public class ItemsSearchModule {

    @ItemsSearchScope
    @Provides
    ItemsSearchViewModelFactory provideItemsListViewModelFactory(ItemService itemService) {
        return new ItemsSearchViewModelFactory(itemService);
    }
}

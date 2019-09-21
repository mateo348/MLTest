package com.example.test.di.ItemsList;

import com.example.test.service.ItemService;
import com.example.test.view.itemList.ItemsListViewModelFactory;
import dagger.Module;
import dagger.Provides;

/**
 * Dagger2: Modulo para DI de ItemDetails, a la cual se le pasa por parametro el ID del Item seleccionado
 */
@Module
public class ItemsListModule {

    @ItemsListScope
    @Provides
    ItemsListViewModelFactory provideItemsListViewModelFactory(ItemService itemService) {
        return new ItemsListViewModelFactory(itemService);
    }
}

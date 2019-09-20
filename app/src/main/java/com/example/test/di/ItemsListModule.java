package com.example.test.di;

import com.example.test.service.IItemService;
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
    ItemsListViewModelFactory provideItemsListViewModelFactory(IItemService itemService) {
        return new ItemsListViewModelFactory(itemService);
    }
}

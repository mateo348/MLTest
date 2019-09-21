package com.example.test.di.ItemDetails;

import com.example.test.service.ItemService;
import com.example.test.view.itemDetails.ItemDetailsViewModelFactory;
import dagger.Module;
import dagger.Provides;

/**
 * Dagger2: Modulo para la de ItemDetails, a la cual se le pasa por parametro el ID del Item seleccionado
 */
@Module
public class ItemDetailsModule {
    String selectedItemId;


    public ItemDetailsModule(String selectedItemId) {
        this.selectedItemId = selectedItemId;
    }

    @ItemDetailScope
    @Provides
    ItemDetailsViewModelFactory provideItemDetailsViewModelFactory(ItemService itemService) {
        return new ItemDetailsViewModelFactory(itemService,selectedItemId);
    }
}
package com.example.test.di.ItemDetails;

import com.example.test.model.search.Result;
import com.example.test.service.ItemService;
import com.example.test.view.itemDetails.ItemDetailsViewModelFactory;
import dagger.Module;
import dagger.Provides;

/**
 * Dagger2: Modulo para la de ItemDetails, a la cual se le pasa por parametro el ID del Item seleccionado
 */
@Module
public class ItemDetailsModule {
    Result selectdResult;


    public ItemDetailsModule(Result selectdResult) {
        this.selectdResult = selectdResult;
    }

    @ItemDetailScope
    @Provides
    ItemDetailsViewModelFactory provideItemDetailsViewModelFactory(ItemService itemService) {
        return new ItemDetailsViewModelFactory(itemService, selectdResult);
    }
}
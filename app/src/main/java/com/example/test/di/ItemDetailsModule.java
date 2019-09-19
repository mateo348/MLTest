package com.example.test.di;

import com.example.test.service.IItemService;
import com.example.test.view.itemDetails.ItemDetailsViewModelFactory;
import dagger.Module;
import dagger.Provides;

@Module
public class ItemDetailsModule {
    String selectedItemId;


    public ItemDetailsModule(String selectedItemId) {
        this.selectedItemId = selectedItemId;
    }

    @ItemDetailScope
    @Provides
    ItemDetailsViewModelFactory provideItemDetailsViewModelFactory(IItemService itemService) {
        return new ItemDetailsViewModelFactory(itemService,selectedItemId);
    }
}
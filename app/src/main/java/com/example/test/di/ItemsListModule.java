package com.example.test.di;

import com.example.test.service.IItemService;
import com.example.test.view.itemList.ItemsListViewModelFactory;
import dagger.Module;
import dagger.Provides;

@Module
public class ItemsListModule {

    @ItemsListScope
    @Provides
    ItemsListViewModelFactory provideItemsListViewModelFactory(IItemService itemService) {
        return new ItemsListViewModelFactory(itemService);
    }
}

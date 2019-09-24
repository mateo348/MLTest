package com.example.test.view.itemsSearch;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.example.test.service.ItemService;

public class ItemsSearchViewModelFactory implements ViewModelProvider.Factory {

    ItemService itemService;

    public ItemsSearchViewModelFactory(ItemService itemService) {
        this.itemService = itemService;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass == ItemsSearchViewModel.class) {
            return (T) new ItemsSearchViewModel(itemService);
        }
        return null;
    }
}

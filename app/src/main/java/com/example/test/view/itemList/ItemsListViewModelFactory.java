package com.example.test.view.itemList;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.example.test.service.IItemService;

public class ItemsListViewModelFactory implements ViewModelProvider.Factory {

    IItemService itemService;

    public ItemsListViewModelFactory(IItemService itemService) {
        this.itemService = itemService;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass == ItemListViewModel.class) {
            return (T) new ItemListViewModel(itemService);
        }
        return null;
    }
}

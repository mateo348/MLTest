package com.example.test.view.itemDetails;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.test.service.IItemService;

public class ItemDetailsViewModelFactory implements ViewModelProvider.Factory {

    IItemService itemService;
    String selectedItemId;

    public ItemDetailsViewModelFactory(IItemService itemService, String selectedItemId) {
        this.itemService = itemService;
        this.selectedItemId = selectedItemId;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass == ItemDetailsViewModel.class) {
            return (T) new ItemDetailsViewModel(itemService, selectedItemId);
        }
        return null;
    }
}

package com.example.test.view.itemDetails;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.example.test.service.ItemService;

public class ItemDetailsViewModelFactory implements ViewModelProvider.Factory {

    ItemService itemService;
    String selectedItemId;

    public ItemDetailsViewModelFactory(ItemService itemService, String selectedItemId) {
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

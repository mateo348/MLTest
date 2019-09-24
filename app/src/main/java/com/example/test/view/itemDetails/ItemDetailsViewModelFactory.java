package com.example.test.view.itemDetails;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.test.model.search.Result;
import com.example.test.service.ItemService;

public class ItemDetailsViewModelFactory implements ViewModelProvider.Factory {

    ItemService itemService;
    Result selectdResult;

    public ItemDetailsViewModelFactory(ItemService itemService, Result selectdResult) {
        this.itemService = itemService;
        this.selectdResult = selectdResult;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass == ItemDetailsViewModel.class) {
            return (T) new ItemDetailsViewModel(itemService, selectdResult);
        }
        return null;
    }
}

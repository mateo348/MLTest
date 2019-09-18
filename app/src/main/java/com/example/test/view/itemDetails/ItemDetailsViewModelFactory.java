package com.example.test.view.itemDetails;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

class ItemDetailsViewModelFactory implements ViewModelProvider.Factory {

    Application application;
    String selectedItemId;

    public ItemDetailsViewModelFactory(Application application, String selectedItemId) {
        this.application = application;
        this.selectedItemId = selectedItemId;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass == ItemDetailsViewModel.class) {
            return (T) new ItemDetailsViewModel(application, selectedItemId);
        }
        return null;
    }
}

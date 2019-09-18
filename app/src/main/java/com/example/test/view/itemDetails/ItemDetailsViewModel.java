package com.example.test.view.itemDetails;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.databinding.Bindable;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.test.di.BaseApplication;
import com.example.test.model.Item;
import com.example.test.model.Result;
import com.example.test.model.Search;
import com.example.test.service.IItemService;

import java.util.Currency;
import java.util.Locale;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemDetailsViewModel extends ViewModel {

    @Inject
    IItemService itemService;

    MutableLiveData<Item> selectedItem = new MutableLiveData<>();

    public ItemDetailsViewModel(Application application, String selectedItemId) {
        ((BaseApplication)application).getAppComponent().inject(this);
        setSelectedItem(selectedItemId);


    }

    public LiveData<Item> getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(String selectedItemID)
    {
        Callback callbackResult = new Callback<Item>() {
            @Override
            public void onResponse(Call<Item> call, Response<Item> response) {
                selectedItem.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Item> call, Throwable t) {
                t.getCause();
            }
        };

        itemService.getItem(selectedItemID, callbackResult);
    }

    public String getItemTitle(){
        if (selectedItem.getValue() != null)
            return selectedItem.getValue().getTitle();
        else
            return "";
    }

    public String getItemPrice(){
        if (selectedItem.getValue() != null)
            return Currency.getInstance(Locale.getDefault()).getSymbol() +  Double.valueOf(getSelectedItem().getValue().getPrice());
        else
            return "";
    }

    public String getItemDescription(){
        if (selectedItem != null)
            return "";
        else
            return "";
    }



}



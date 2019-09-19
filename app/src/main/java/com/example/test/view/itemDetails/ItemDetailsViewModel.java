package com.example.test.view.itemDetails;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.test.model.Item;
import com.example.test.service.IItemService;
import java.util.Currency;
import java.util.Locale;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemDetailsViewModel extends ViewModel {

    IItemService itemService;

    MutableLiveData<Item> selectedItem = new MutableLiveData<>();

    public ItemDetailsViewModel(IItemService itemService, String selectedItemId) {
        this.itemService = itemService;
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


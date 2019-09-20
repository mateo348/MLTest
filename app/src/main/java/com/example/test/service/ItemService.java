package com.example.test.service;

import com.example.test.apiconnection.ApiService;
import com.example.test.model.Item;
import com.example.test.model.ItemDescription;
import com.example.test.model.Search;
import retrofit2.Call;
import retrofit2.Callback;

public class ItemService implements IItemService {

    ApiService apiService;

    public ItemService(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public void searchItems(String query, Callback<Search> callback){
        Call<Search> result = apiService.getSearch(query);
        result.enqueue(callback);
    }
    @Override
    public void setSelectedItem(String id, Callback<Item> callback){
        Call<Item> call = apiService.getItem(id, Item.ATTRIBUTES);
                call.enqueue(callback);
    }
    @Override
    public void setSelectedItemDescription(String id, Callback<ItemDescription> callback){
        apiService.getItemDescription(id).enqueue(callback);
    }
}

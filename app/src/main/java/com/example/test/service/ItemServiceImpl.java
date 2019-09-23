package com.example.test.service;

import android.util.Log;

import com.example.test.apiconnection.ApiService;
import com.example.test.model.Item;
import com.example.test.model.ItemDescription;
import com.example.test.model.Search;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Implementacion de {@linkplain ItemService}
 */
public class ItemServiceImpl implements ItemService {

    private static final String TAG = "ItemServiceImpl";
    ApiService apiService;

    /**
     * @param apiService Recibe por DI la implementacion de las llamadas a la API
     */
    public ItemServiceImpl(ApiService apiService) {
        this.apiService = apiService;
    }

    /**
     * Ver definición en {@linkplain ItemService#searchItems(String, Callback)}
     */
    @Override
    public void searchItems(String query, Callback<Search> callback) throws Exception{
        Log.i(TAG, "searchItems: query=" + query);
        apiService.getSearch(query).enqueue(callback);
    }

    /**
     * Ver definición en {@linkplain ItemService#setSelectedItem(String, Callback)}
     */
    @Override
    public void setSelectedItem(String id, Callback<Item> callback) throws Exception{
        Log.i(TAG, "setSelectedItem: id=" + id);
        String attr = Item.ATTRIBUTES;
        apiService.getItem(id , attr).enqueue(callback);
    }

    /**
     * Ver definición en {@linkplain ItemService#setSelectedItemDescription(String, Callback)}
     */
    @Override
    public void setSelectedItemDescription(String id, Callback<ItemDescription> callback) throws Exception{
        Log.i(TAG, "setSelectedItemDescription: id=" + id);
        apiService.getItemDescription(id).enqueue(callback);
    }
}

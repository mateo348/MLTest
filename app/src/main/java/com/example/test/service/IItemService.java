package com.example.test.service;

import com.example.test.model.Item;
import com.example.test.model.ItemDescription;
import com.example.test.model.Search;

import retrofit2.Callback;

public interface IItemService {
    void getSearch(String query, Callback<Search> callback);
    void getItem(String id, Callback<Item> callback);
    void getItemDescription(String id, Callback<ItemDescription> callback);
}

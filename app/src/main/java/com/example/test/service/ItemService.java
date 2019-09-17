package com.example.test.service;

import android.app.Application;

import com.example.test.apiconnection.ApiService;
import com.example.test.di.BaseApplication;
import com.example.test.model.Item;
import com.example.test.model.ItemDescription;
import com.example.test.model.Search;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;

public class ItemService implements IItemService {

    @Inject
    ApiService apiService;

    /*private static Retrofit retrofit;
    private static ApiService client;
    private static final String BASE_URL = "https://api.mercadolibre.com/";

    public static ApiService getRestClientInstance(){
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        if(client == null){
            client = retrofit.create(ApiService.class);
        }
        return client;
    }*/

    public ItemService(Application application) {
        setUpDagger(application);
    }

    private void setUpDagger(Application application){
        ((BaseApplication)application).getAppComponent().inject(this);
    }


    @Override
    public void getSearch(String query, Callback<Search> callback){
        Call<Search> result = apiService.getSearch(query);
        result.enqueue(callback);
    }
    @Override
    public void getItem(String id, Callback<Item> callback){
        apiService.getItem(id).enqueue(callback);
    }
    @Override
    public void getItemDescription(String id, Callback<ItemDescription> callback){
        apiService.getItemDescription(id).enqueue(callback);
    }
}

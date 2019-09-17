package com.example.test.apiconnection;

import com.example.test.model.Item;
import com.example.test.model.ItemDescription;
import com.example.test.model.Search;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET("items/{id}")
    Call<Item> getItem(@Path("id") String id);

    @GET("sites/MLA/search")
    Call<Search> getSearch(@Query("q") String query);

    @GET("items/{id}/description")
    Call<ItemDescription> getItemDescription(@Path("id") String id);
}

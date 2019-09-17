package com.example.test.view.itemList;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.test.di.BaseApplication;
import com.example.test.model.Result;
import com.example.test.model.Search;
import com.example.test.service.IItemService;
import com.example.test.service.ItemService;

import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemListViewModel extends AndroidViewModel {


    MutableLiveData<List<Result>> items;

    @Inject
    IItemService itemService;


    public ItemListViewModel(@NonNull Application application) {
        super(application);
        ((BaseApplication)application).getAppComponent().inject(this);
    }


    public MutableLiveData<List<Result>> getItems(){
        if (items == null)
            items = new MutableLiveData<>();
        return items;
    }

    public void searchItems(String valueToSearch)
    {
        Callback callbackResult = new Callback<Search>() {
            @Override
            public void onResponse(Call<Search> call, Response<Search> response) {
                items.setValue(response.body().getResults());
            }

            @Override
            public void onFailure(Call<Search> call, Throwable t) {

                t.getCause();
            }
        };

        itemService.getSearch(valueToSearch, callbackResult);
    }
}

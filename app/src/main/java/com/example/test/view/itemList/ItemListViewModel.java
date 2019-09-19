package com.example.test.view.itemList;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.test.model.Result;
import com.example.test.model.Search;
import com.example.test.service.IItemService;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemListViewModel extends ViewModel {


    MutableLiveData<List<Result>> items;

    IItemService itemService;

    public ItemListViewModel(IItemService itemService) {
        this.itemService = itemService;
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

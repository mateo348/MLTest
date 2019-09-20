package com.example.test.view.itemList;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.test.model.Result;
import com.example.test.model.Search;
import com.example.test.service.IItemService;
import com.example.test.util.Utils;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class ItemListViewModel extends ViewModel {


    MutableLiveData<List<Result>> items = new MutableLiveData<>();

    MutableLiveData<Integer> errorCode = new MutableLiveData<>();

    IItemService itemService;

    public ItemListViewModel(IItemService itemService) {
        this.itemService = itemService;
    }



    public MutableLiveData<List<Result>> getItems(){ return items; }

    public MutableLiveData<Integer> getErrorCode() { return errorCode; }

    public void searchItems(String valueToSearch)
    {
        Callback callbackResult = new Callback<Search>() {
            @Override
            public void onResponse(Call<Search> call, Response<Search> response) {
                onResponseSearchItems(response);
            }

            @Override
            public void onFailure(Call<Search> call, Throwable t) {
                onFailureSearchItems(t);
            }
        };
        itemService.searchItems(valueToSearch, callbackResult);
    }


    public boolean canSearchItems()
    {
        boolean retval = Utils.isInternetAvailable();
        if (!retval)
            errorCode.setValue(Utils.NOT_INTERNET_ERROR_CODE);
        return retval;
    }

    private void onResponseSearchItems(Response<Search> response) {
        if(response.body() == null)
            errorCode.setValue(Utils.NOT_SEARCH_RESULT_ERROR_CODE);
        else
            items.setValue(response.body().getResults());
    }

    private void onFailureSearchItems(Throwable t) {
        errorCode.setValue(Utils.SERVER_CONECCTION_ERROR_CODE);
    }
}

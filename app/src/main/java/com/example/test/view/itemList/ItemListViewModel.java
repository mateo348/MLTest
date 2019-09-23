package com.example.test.view.itemList;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.test.model.Result;
import com.example.test.model.Search;
import com.example.test.service.ItemService;
import com.example.test.util.Utils;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemListViewModel extends ViewModel {
    private static final String TAG = "ItemListViewModel";

    public static final int NOT_INTERNET_ERROR_CODE = 100;
    public static final int SERVER_CONECCTION_ERROR_CODE = 200;
    public static final int NOT_SEARCH_RESULT_ERROR_CODE = 300;

    /**
     * Lista de resultados de busqueda
     */
    MutableLiveData<List<Result>> items = new MutableLiveData<>();

    /**
     * Codigo de error, utilizado en caso de no poder realizar la busqueda o que ésta falle
     */
    MutableLiveData<Integer> errorCode = new MutableLiveData<>();

    ItemService itemService;

    public ItemListViewModel(ItemService itemService) {
        this.itemService = itemService;
    }



    public MutableLiveData<List<Result>> getItems(){ return items; }

    public MutableLiveData<Integer> getErrorCode() { return errorCode; }


    /**
     * Se llama al servicio que buscará los resultados con un criterio determinado y se establecera
     * en items a travez del Callback proporcionado
     * @param valueToSearch criterio de busqueda
     */
    public void searchItems(String valueToSearch)
    {
        try {
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
        }catch (Exception ex) {
            Log.e(TAG, "searchItems exception: ", ex);
        }
    }


    public boolean canSearchItems(boolean isInternetAvailable)
    {
        if (!isInternetAvailable) {
            errorCode.setValue(NOT_INTERNET_ERROR_CODE);
        }
        return isInternetAvailable;
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    protected boolean isInternetAvailable() {
        return Utils.isInternetAvailable();
    }

    /**
     * Si la respuesta del servidor trajo resultados, se establece en items, de lo contrario se
     * especifica codigo de error (se deberia evaluar por codigo de response y de esa manera determinar
     * el errorCode, pero se hizo de esta manera por desconocimiento de dichos codigos)
     * @param response respuesta de la llamada a la API
     */
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    protected  void onResponseSearchItems(Response<Search> response) {
        if(response.body() == null ||
                response.body().getResults()== null ||
                response.body().getResults().size() == 0) {
            errorCode.setValue(NOT_SEARCH_RESULT_ERROR_CODE);
        }
        if (response.body() != null && response.body().getResults() != null){
            items.setValue(response.body().getResults());
        }
    }

    /**
     * Si la llamada a la API falló, se establece el codigo de error
     * @param t error de la llamada a la API
     */
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    protected void onFailureSearchItems(Throwable t) {
        errorCode.setValue(SERVER_CONECCTION_ERROR_CODE);
    }
}

package com.example.test.view.itemsSearch;

import android.util.Log;
import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ViewModel;
import com.example.test.model.search.Result;
import com.example.test.model.search.Search;
import com.example.test.service.ItemService;
import com.example.test.util.AppUtils;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemsSearchViewModel extends ViewModel implements LifecycleObserver {
    private static final String TAG = "ItemsSearchViewModel";

    public static final int NOT_INTERNET_ERROR_CODE = 100;
    public static final int NOT_FOUND_RESULT_ERROR_CODE = 300;
    public static final int SERVER_ERROR_CODE = 404;
    public static final int SERVER_CONECCTION_ERROR_CODE = 400;

    /**
     * Lista de resultados de busqueda
     */
    private MutableLiveData<List<Result>> items = new MutableLiveData<>();
    /**
     * Codigo de error, utilizado en caso de no poder realizar la busqueda o que ésta falle
     */
    private MutableLiveData<Integer> errorCode;

    public LiveData<List<Result>> getItems(){
        if (items == null)
            items = new MutableLiveData<>();
        return items; }

    public LiveData<Integer> getErrorCode() {
        if (errorCode == null)
            errorCode = new MutableLiveData<>();
        return errorCode; }

    ItemService itemService;

    public ItemsSearchViewModel(ItemService itemService) {
        this.itemService = itemService;
    }


    /**
     * Por si hubo algun error antes de rotar la pantalla , se reinicia el errorCode
     */
    @OnLifecycleEvent (Lifecycle.Event.ON_CREATE)
    public void onCreate()
    {
        errorCode.setValue(0);
    }


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
                    onResponseSearchItems(response);}
                @Override
                public void onFailure(Call<Search> call, Throwable t) {
                    onFailureApiCall(t);
                }
            };

            itemService.searchItems(valueToSearch, callbackResult);
        }catch (Exception ex) {
            Log.e(TAG, "searchItems exception: ", ex);
        }
    }


    /**
     * Evalua si existe conexion a internet para poder realizar la busqueda
     * @param appUtils AppUtils obtiene el estado de la conexion a internet
     * @return
     */
    public boolean canSearchItems(AppUtils appUtils)
    {
        if (!appUtils.isInternetAvailable()) {
            errorCode.setValue(NOT_INTERNET_ERROR_CODE);
            Log.w(TAG, "canSearchItems: No internet Connection"); }
        return appUtils.isInternetAvailable();
    }

    /**
     * Si la respuesta del servidor trajo resultados, se establece en items, de lo contrario se
     * especifica codigo de error
     * @param response respuesta de la llamada a la API
     */
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    protected  void onResponseSearchItems(Response<Search> response) {
        switch (response.code()) {
            case ItemService.SERVER_OK_CODE:
                items.setValue(response.body().getResults());
                if(response.body().getResults().size() == 0){
                    errorCode.setValue(NOT_FOUND_RESULT_ERROR_CODE);
                    Log.w(TAG, "onResponseSearchItems: Not found items"); }
                else
                    Log.i(TAG, "onResponseSearchItems: Items Found" );
                break;
            case ItemService.SERVER_ERROR_CODE:
                errorCode.setValue(SERVER_ERROR_CODE);
                Log.w(TAG, "onResponseSearchItems ServerError: " + response.message());
                break;
        }
    }

    /**
     * Si la llamada a la API falló, se establece el codigo de error
     * @param t error de la llamada a la API
     */
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    protected void onFailureApiCall(Throwable t) {
        errorCode.setValue(SERVER_CONECCTION_ERROR_CODE);
        Log.w(TAG, "onFailureApiCall: ", t.getCause());
    }
}

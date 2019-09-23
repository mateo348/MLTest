package com.example.test.view.itemDetails;

import android.util.Log;

import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.test.model.Item;
import com.example.test.model.ItemDescription;
import com.example.test.service.ItemService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemDetailsViewModel extends ViewModel {

    private static final String TAG = "ItemDetailsViewModel";
    public static final int SERVER_CONECCTION_ERROR_CODE = 200;
    public static final int SERVER_ERROR_CODE = 404;

    ItemService itemService;

    MutableLiveData<Item> selectedItem = new MutableLiveData<>();
    MutableLiveData<ItemDescription> itemDescription = new MutableLiveData<>();
    MutableLiveData<Integer> errorCode = new MutableLiveData<>();

    /**
     * En el constructor se establece el item selecconado
     * @param itemService Servicio que va a llamar a la API para buscar el item seleccionado
     * @param selectedItemId ID del item seleccionado
     */
    public ItemDetailsViewModel(ItemService itemService, String selectedItemId) {
        this.itemService = itemService;
        setSelectedItem(selectedItemId);
        setSelectedItemDescription(selectedItemId);
    }

    public LiveData<Item> getSelectedItem() {
        return selectedItem;
    }
    public LiveData<Integer> getErrorCode() { return errorCode; }
    public LiveData<ItemDescription> getItemDescription() { return itemDescription; }

    /**
     * Se llama al servicio enviandole el ID del item a buscar y el callback con las actions luego de ejecurarce
     * la llamada a la API
     */
    public void setSelectedItem(String selectedItemID)
    {
        try {
            Callback callbackResult = new Callback<Item>() {
                @Override
                public void onResponse(Call<Item> call, Response<Item> response) {
                    onResponseSetSeletedItem(response);
                }

                @Override
                public void onFailure(Call<Item> call, Throwable t) {
                    onFailureApiCall(t);
                }
            };

            itemService.setSelectedItem(selectedItemID, callbackResult);
        }catch (Exception ex){
            Log.e(TAG, "setSelectedItemDescription: ", ex);
        }
    }

    public void setSelectedItemDescription(String selectedItemID)
    {
        try {
            Callback callbackResult = new Callback<ItemDescription>() {
                @Override
                public void onResponse(Call<ItemDescription> call, Response<ItemDescription> response) {
                    onResponseSetSeletedItemDescription(response);
                }

                @Override
                public void onFailure(Call<ItemDescription> call, Throwable t) {
                    onFailureApiCall(t);
                }
            };

            itemService.setSelectedItemDescription(selectedItemID, callbackResult);
        }catch (Exception ex){
            Log.e(TAG, "setSelectedItem: ", ex);
        }
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    protected void onResponseSetSeletedItemDescription(Response<ItemDescription> response) {
        switch (response.code()) {
            case ItemService.SERVER_OK_CODE:
                itemDescription.setValue(response.body());
                break;
            case ItemService.SERVER_ERROR_CODE:
                errorCode.setValue(SERVER_ERROR_CODE);
                Log.w(TAG, "onResponseSetSeletedItemDescription ServerError: " + response.message());
                break;
        }
    }
    /**
     * Si el Api invicada en el servicio encontró el item, se establece en selectedItem
     * @param response
     */
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    protected void onResponseSetSeletedItem(Response<Item> response) {

        switch (response.code()) {
            case ItemService.SERVER_OK_CODE:
                selectedItem.setValue(response.body());
                break;
            case ItemService.SERVER_ERROR_CODE:
                errorCode.setValue(ItemService.SERVER_ERROR_CODE);
                Log.w(TAG, "onResponseSetSeletedItem ServerError: " + response.message());
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

    public String getItemTitle(){
        if (selectedItem.getValue() != null)
            return selectedItem.getValue().getTitle();
        else
            return "";
    }

    public String getItemPrice(){
        if (selectedItem.getValue() != null)
            return selectedItem.getValue().getFormatedPrice();
        else
            return "";
    }

    public String getLongDescription() {
        if (itemDescription.getValue() != null)
            return itemDescription.getValue().getPlainText();
        else
            return "";
    }




}



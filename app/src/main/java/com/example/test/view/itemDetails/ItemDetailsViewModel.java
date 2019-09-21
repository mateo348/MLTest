package com.example.test.view.itemDetails;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.test.model.Item;
import com.example.test.model.ItemDescription;
import com.example.test.service.ItemService;
import java.util.Currency;
import java.util.Locale;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemDetailsViewModel extends ViewModel {

    private static final String TAG = "ItemDetailsViewModel";
    public static final int SERVER_CONECCTION_ERROR_CODE = 200;

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
                    t.getCause();
                }
            };

            itemService.setSelectedItemDescription(selectedItemID, callbackResult);
        }catch (Exception ex){
            Log.e(TAG, "setSelectedItem: ", ex);
        }
    }

    private void onResponseSetSeletedItemDescription(Response<ItemDescription> response) {
        if (response.body() != null)
            itemDescription.setValue(response.body());
        else
            errorCode.setValue(SERVER_CONECCTION_ERROR_CODE);
    }


    /**
     * Si el Api invicada en el servicio encontró el item, se establece en selectedItem
     * @param response
     */
    private void onResponseSetSeletedItem(Response<Item> response) {
        if (response.body() != null)
            selectedItem.setValue(response.body());
        else
            errorCode.setValue(SERVER_CONECCTION_ERROR_CODE);

    }

    /**
     * Si la llamada a la API falló, se establece el codigo de error
     * @param t error de la llamada a la API
     */
    private void onFailureSearchItems(Throwable t) {
        errorCode.setValue(SERVER_CONECCTION_ERROR_CODE);
    }

    public String getItemTitle(){
        if (selectedItem.getValue() != null)
            return selectedItem.getValue().getTitle();
        else
            return "";
    }

    public String getItemPrice(){
        if (selectedItem.getValue() != null)
            return Currency.getInstance(Locale.getDefault()).getSymbol() +  Double.valueOf(getSelectedItem().getValue().getPrice());
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



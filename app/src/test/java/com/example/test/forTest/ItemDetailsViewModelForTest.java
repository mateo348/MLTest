package com.example.test.forTest;

import com.example.test.model.Item;
import com.example.test.model.ItemDescription;
import com.example.test.service.ItemService;
import com.example.test.view.itemDetails.ItemDetailsViewModel;
import retrofit2.Response;

/**
 * En la necesidad de probar metodos internos, se crea esta extencion de ItemDetailsViewModel para hacerlos publicos
 */
public class ItemDetailsViewModelForTest extends ItemDetailsViewModel {

    public ItemDetailsViewModelForTest(ItemService itemService, String selectedItemId) {
        super(itemService, selectedItemId);
    }

    public void onResponseSetSeletedItemDescription(Response<ItemDescription> response){
        super.onResponseSetSeletedItemDescription(response);
    }

    public void onResponseSetSeletedItem(Response<Item> response) { super.onResponseSetSeletedItem(response); }

    public void onFailureApiCall(Throwable t) { super.onFailureApiCall(t); }

}

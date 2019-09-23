package com.example.test.forTest;

import com.example.test.model.Search;
import com.example.test.service.ItemService;
import com.example.test.view.itemList.ItemListViewModel;

import retrofit2.Response;

/**
 * En la necesidad de probar metodos internos, se crea esta extencion de ItemListViewModel
 */
public class ItemListViewModelForTest extends ItemListViewModel {

    public ItemListViewModelForTest(ItemService itemService) {
        super(itemService);
    }

    public void onResponseSearchItems(Response<Search> response) {
        super.onResponseSearchItems(response);
    }

    public void onFailureApiCall(Throwable t)
    {
        super.onFailureApiCall(t);
    }
}

package com.example.test.forTest;

import com.example.test.model.search.Search;
import com.example.test.service.ItemService;
import com.example.test.view.itemsSearch.ItemsSearchViewModel;

import retrofit2.Response;

/**
 * En la necesidad de probar metodos internos, se crea esta extencion de ItemsSearchViewModel
 */
public class ItemsSearchViewModelForTest extends ItemsSearchViewModel {

    public ItemsSearchViewModelForTest(ItemService itemService) {
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

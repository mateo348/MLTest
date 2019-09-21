package com.example.test;

import com.example.test.apiconnection.ApiService;
import com.example.test.model.Search;
import com.example.test.service.ItemService;
import com.example.test.service.ItemServiceImpl;
import com.example.test.util.Utils;
import com.example.test.view.itemList.ItemListActivity;
import com.example.test.view.itemList.ItemListViewModel;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;


import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.mock.MockRetrofit;
import retrofit2.mock.NetworkBehavior;

import static org.mockito.Matchers.any;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ItemServiceImplTest {

    private MockRetrofit mockRetrofit;

    private Retrofit retrofit;

    private ItemService itemService;

    private ItemListViewModel viewModel;

    MockWebServer server  = new MockWebServer();

    @Before
    public void setUp() {
        retrofit = new Retrofit.Builder().baseUrl(Utils.BASE_URL)
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        NetworkBehavior behavior = NetworkBehavior.create();

        mockRetrofit = new MockRetrofit.Builder(retrofit)
                .networkBehavior(behavior)
                .build();
        itemService = new ItemServiceImpl(retrofit.create(ApiService.class));

        viewModel = new ItemListViewModel(itemService);

    }




    /*void searchItems(String query, Callback<Search> callback);
    void setSelectedItem(String id, Callback<Item> callback);
    void setSelectedItemDescription(String id, Callback<ItemDescription> callback);*/

    @Mock
    Callback<Search> callback;


    @Test
    public void shouldSuccessWithValidUserAndPasswordTest() throws Exception{

        viewModel.searchItems("#$%&%/&%#&");

        int errorCode = viewModel.getErrorCode().getValue();
        Assert.assertEquals(errorCode, ItemListViewModel.NOT_SEARCH_RESULT_ERROR_CODE);


       /* callback = Mockito.mock(Callback.class);

        itemService.searchItems("alberto", callback);*/
        //4.- Verificamos si han realizado las llamadas correctas (en este caso si se ha llamado a usuarioValido() en el presenter)
        //al ejecutar el metodo validaUser del modelo.
        //Es decir estamos enviado el usuario y password correcto, por lo tanto tiene que llamar a este metodo.
        //verify(apiService).searchItems("alberto");
        //Assert.assertEquals("alberto", "alberto");
    }

}

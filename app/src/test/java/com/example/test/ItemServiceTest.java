package com.example.test;

import com.example.test.apiconnection.ApiService;
import com.example.test.di.AppComponent;
import com.example.test.di.AppModule;
import com.example.test.di.BaseApplication;
import com.example.test.di.ItemsListModule;
import com.example.test.model.Search;
import com.example.test.service.IItemService;
import com.example.test.service.ItemService;
import com.example.test.util.Utils;
import com.example.test.view.itemList.ItemListViewModel;
import com.example.test.view.itemList.ItemsListViewModelFactory;


import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;


import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.lang.annotation.Annotation;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.mock.MockRetrofit;
import retrofit2.mock.NetworkBehavior;

import static org.mockito.Matchers.any;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ItemServiceTest {

    private MockRetrofit mockRetrofit;

    private Retrofit retrofit;

    private IItemService itemService;

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
        itemService = new ItemService(retrofit.create(ApiService.class));

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
        Assert.assertEquals(errorCode, Utils.NOT_SEARCH_RESULT_ERROR_CODE);


       /* callback = Mockito.mock(Callback.class);

        itemService.searchItems("alberto", callback);*/
        //4.- Verificamos si han realizado las llamadas correctas (en este caso si se ha llamado a usuarioValido() en el presenter)
        //al ejecutar el metodo validaUser del modelo.
        //Es decir estamos enviado el usuario y password correcto, por lo tanto tiene que llamar a este metodo.
        //verify(apiService).searchItems("alberto");
        //Assert.assertEquals("alberto", "alberto");
    }

}

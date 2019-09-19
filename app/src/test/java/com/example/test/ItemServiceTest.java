package com.example.test;

import android.app.Application;

import androidx.lifecycle.ViewModel;

import com.example.test.apiconnection.ApiService;
import com.example.test.di.AppComponent;
import com.example.test.di.BaseApplication;
import com.example.test.model.Item;
import com.example.test.model.ItemDescription;
import com.example.test.model.Search;
import com.example.test.service.ItemService;
import com.example.test.view.itemList.ItemListActivity;
import com.example.test.view.itemList.ItemListViewModel;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.matchers.Any;
import org.mockito.junit.MockitoJUnitRunner;

import javax.inject.Inject;
import javax.inject.Singleton;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import dagger.Component;
import retrofit2.Callback;
import static org.mockito.Matchers.any;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ItemServiceTest {

    @Inject
    ApiService apiService;

    @Singleton
    @Component(modules = MockServiceModule.class)
    public interface TestComponent extends AppComponent {
        void inject(ItemServiceTest mainActivityTest);
    }

    /*void getSearch(String query, Callback<Search> callback);
    void getItem(String id, Callback<Item> callback);
    void getItemDescription(String id, Callback<ItemDescription> callback);*/

    @Mock
    BaseApplication application;

    @Mock
    Callback<Search> callback;

    @InjectMocks
    ItemService itemService;

    @Test
    public void shouldSuccessWithValidUserAndPasswordTest() throws Exception{
        doNothing().when(application).getAppComponent().inject(itemService);
        //3.- Nos permite ejecutar un metodo
        itemService.getSearch("alberto", callback);
        //4.- Verificamos si han realizado las llamadas correctas (en este caso si se ha llamado a usuarioValido() en el presenter)
        //al ejecutar el metodo validaUser del modelo.
        //Es decir estamos enviado el usuario y password correcto, por lo tanto tiene que llamar a este metodo.
        verify(apiService).getSearch("alberto");
    }

}

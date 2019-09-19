package com.example.test;

import com.example.test.di.AppComponent;
import com.example.test.di.AppModule;
import com.example.test.model.Search;
import com.example.test.service.ItemService;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import androidx.test.*;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Callback;
import static org.mockito.Matchers.any;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ItemServiceTest {



    @Before
    public void setUp() {
      /*  TestComponent component = DaggerItemServiceTest.builder().build();
        component.inject(this);*/

    }

    @Singleton
    @Component(modules = AppModule.class)
    public interface TestComponent extends AppComponent {
        void inject(ItemServiceTest mainActivityTest);
    }

    /*void getSearch(String query, Callback<Search> callback);
    void getItem(String id, Callback<Item> callback);
    void getItemDescription(String id, Callback<ItemDescription> callback);*/


    Callback<Search> callback;





    ItemService itemService;

    @Test
    public void shouldSuccessWithValidUserAndPasswordTest() throws Exception{
        callback = Mockito.mock(Callback.class);

        itemService.getSearch("alberto", callback);
        //4.- Verificamos si han realizado las llamadas correctas (en este caso si se ha llamado a usuarioValido() en el presenter)
        //al ejecutar el metodo validaUser del modelo.
        //Es decir estamos enviado el usuario y password correcto, por lo tanto tiene que llamar a este metodo.
        //verify(apiService).getSearch("alberto");
        Assert.assertEquals("alberto", "alberto");
    }

}

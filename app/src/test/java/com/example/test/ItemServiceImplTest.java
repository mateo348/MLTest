package com.example.test;

import android.util.Log;

import com.example.test.apiconnection.ApiService;
import com.example.test.model.Search;
import com.example.test.service.ItemService;
import com.example.test.service.ItemServiceImpl;
import com.example.test.testObjects.ByPassObject;
import com.example.test.testObjects.ItemListViewModelForTest;
import com.example.test.util.Utils;
import com.example.test.view.itemList.ItemListViewModel;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.mockito.verification.VerificationMode;


import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.mock.MockRetrofit;
import retrofit2.mock.NetworkBehavior;

import static org.mockito.Matchers.any;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ItemServiceImplTest {

    private Retrofit retrofit;

    @Mock
    ApiService apiService;

    @Mock
    ItemServiceImpl itemService;

    @InjectMocks
    ItemListViewModel viewModel;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);


        retrofit = new Retrofit.Builder().baseUrl(Utils.BASE_URL)
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }




    /*void searchItems(String query, Callback<Search> callback);
    void setSelectedItem(String id, Callback<Item> callback);
    void setSelectedItemDescription(String id, Callback<ItemDescription> callback);*/

    //@Mock
    //Call<Search> mockedCall;

    @Test
    public void itemTest() {
        try {

        final Call<Search> mockedCall = Mockito.mock(Call.class);
        when(apiService.getSearch("1")).thenReturn(mockedCall);
        final Response<Search> response = Response.success(new Search());
        Mockito.doAnswer(new Answer() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                Callback<Search> callback = invocation.getArgument(0);
                callback.onResponse(mockedCall, response);
                return null;
            }
        }).when(mockedCall).enqueue(any(Callback.class));


        }
        catch (Exception ex)
        {
            Log.e("Test1", "itemTest: ",ex );
        }


    }

    @Test
    public void test2()
    {

        ApiService apiEndpoints = retrofit.create(ApiService.class);

        final Call<Search> call = apiEndpoints.getSearch("1");

        try {
            final Response<Search> response = call.execute();
            Search authResponse = response.body();

            final Call<Search> mockedCall = Mockito.mock(Call.class);
            Mockito.doAnswer(new Answer() {
                @Override
                public Void answer(InvocationOnMock invocation) throws Throwable {
                    Callback<Search> callback = invocation.getArgument(0);
                    callback.onResponse(call, response);
                    return null;
                }
            }).when(mockedCall).enqueue(any(Callback.class));

            when(apiService.getSearch("1")).thenReturn(mockedCall);

            viewModel.searchItems("1");

            verify(itemService).searchItems("1",any(Callback.class));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

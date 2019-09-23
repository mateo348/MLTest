package com.example.test;

import android.util.Log;

import androidx.annotation.VisibleForTesting;
import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.example.test.apiconnection.ApiService;
import com.example.test.forTest.ItemListViewModelForTest;
import com.example.test.model.Search;
import com.example.test.service.ItemService;
import com.example.test.service.ItemServiceImpl;
import com.example.test.util.Utils;
import com.example.test.view.itemList.ItemListViewModel;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
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

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule =
            new InstantTaskExecutorRule();

    @Mock
    ApiService apiService;

    @Mock
    ItemServiceImpl itemService;

    @InjectMocks
    ItemListViewModelForTest viewModel;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);


        retrofit = new Retrofit.Builder().baseUrl(Utils.BASE_URL)
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Test
    public void searchItemsNotResults()
    {
        ApiService apiEndpoints = retrofit.create(ApiService.class);

        final Call<Search> call = apiEndpoints.getSearch("Casa");

        try {
            final Response<Search> response = call.execute();

            viewModel.onResponseSearchItems(response);

            Assert.assertNotNull(viewModel.getItems().getValue());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void searchItemsNotFoundResults()
    {

        ApiService apiEndpoints = retrofit.create(ApiService.class);

        final Call<Search> call = apiEndpoints.getSearch("#%$#%$%&");

        try {
            final Response<Search> response = call.execute();

            viewModel.onResponseSearchItems(response);

            Assert.assertNotNull(viewModel.getItems().getValue());
            Assert.assertEquals(viewModel.getItems().getValue().size(), 0);
            Assert.assertEquals(viewModel.getErrorCode().getValue(), Integer.valueOf(ItemListViewModel.NOT_SEARCH_RESULT_ERROR_CODE));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

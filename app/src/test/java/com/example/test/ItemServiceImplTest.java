package com.example.test;

import android.util.Log;

import androidx.annotation.VisibleForTesting;
import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.example.test.apiconnection.ApiService;
import com.example.test.forTest.ItemListViewModelForTest;
import com.example.test.model.Result;
import com.example.test.model.Search;
import com.example.test.service.ItemService;
import com.example.test.service.ItemServiceImpl;
import com.example.test.util.Utils;
import com.example.test.view.itemList.ItemListViewModel;


import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;
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


import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.mock.MockRetrofit;
import retrofit2.mock.NetworkBehavior;

import static org.mockito.Matchers.any;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ItemServiceImplTest {

    private static Retrofit retrofit;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule =
            new InstantTaskExecutorRule();



    @InjectMocks
    ItemListViewModelForTest viewModel;


    @BeforeClass
    public static void setUp() {
        retrofit = new Retrofit.Builder().baseUrl(Utils.BASE_URL)
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Test
    public void searchItemsFoundResultsTest()
    {
        try {
                ArrayList<Result> mockResults = new ArrayList<Result>();
                for (int i = 0; i < 1; i++) {
                    mockResults.add(new Result());
                }
                Search search = new Search(mockResults);

                Response<Search> response = Response.success(search);

                viewModel.onResponseSearchItems(response);

                Assert.assertNotNull(viewModel.getItems().getValue());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void searchItemsNotFoundResultsTest()
    {
        try {
            ArrayList<Result> mockResults = new ArrayList<Result>();

            Search search = new Search(mockResults);

            Response<Search> response = Response.success(search);

            viewModel.onResponseSearchItems(response);


            Assert.assertEquals(viewModel.getErrorCode().getValue(), Integer.valueOf(ItemListViewModel.NOT_SEARCH_RESULT_ERROR_CODE));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void searchItemsServerErrorTest()
    {
        try {
            ResponseBody responseBody = Mockito.mock(ResponseBody.class);

            Response<Search> response = Response.error(404, responseBody);

            viewModel.onResponseSearchItems(response);


            Assert.assertEquals(viewModel.getErrorCode().getValue(), Integer.valueOf(ItemListViewModel.NOT_SEARCH_RESULT_ERROR_CODE));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void searchItemsWhenErrorServerRespondTest() {
        ResponseBody responseBody = Mockito.mock(ResponseBody.class);
        Response<Search> response = Response.error(400,responseBody);


        viewModel.onResponseSearchItems(response);

        Assert.assertEquals(viewModel.getErrorCode().getValue(), Integer.valueOf(ItemListViewModel.NOT_SEARCH_RESULT_ERROR_CODE));
    }

    @Test
    public void canSearchItemsInternetDiseabledTest() {

        boolean internetEnabled = false;
        boolean retval = viewModel.canSearchItems(internetEnabled);
        Assert.assertFalse(retval);
        Assert.assertEquals(viewModel.getErrorCode().getValue(), Integer.valueOf(ItemListViewModel.NOT_INTERNET_ERROR_CODE));
    }

    @Test
    public void canSearchItemsInternetEnabledTest() {

        boolean internetEnabled = true;
        boolean retval = viewModel.canSearchItems(internetEnabled);
        Assert.assertTrue(retval);
    }


}

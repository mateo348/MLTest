package com.example.test;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import com.example.test.forTest.ItemsSearchViewModelForTest;
import com.example.test.model.search.Result;
import com.example.test.model.search.Search;
import com.example.test.util.AppUtils;
import com.example.test.view.itemsSearch.ItemsSearchViewModel;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.ArrayList;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ItemsSearchViewModelTest {

    private static Retrofit retrofit;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule =
            new InstantTaskExecutorRule();

    @InjectMocks
    ItemsSearchViewModelForTest viewModel;


    @BeforeClass
    public static void setUp() {
        retrofit = new Retrofit.Builder().baseUrl(AppUtils.BASE_URL)
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    /**
     *Busqueda de items con resultados encontrados
     */
    @Test
    public void searchItemsFoundResultsTest()
    {
        try {
                ArrayList<Result> mockResults = new ArrayList<>();
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

    /**
     * busqueda de items sin resultados obtenidos
     */
    @Test
    public void searchItemsNotFoundResultsTest()
    {
        try {
            ArrayList<Result> mockResults = new ArrayList<Result>();
            Search search = new Search(mockResults);
            Response<Search> response = Response.success(search);

            viewModel.onResponseSearchItems(response);
            int realValue = viewModel.getErrorCode().getValue();
            int expectedValue = Integer.valueOf(ItemsSearchViewModel.NOT_FOUND_RESULT_ERROR_CODE);

            Assert.assertEquals(expectedValue, realValue);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Busqueda de items cuando el servidor responde con codigo de error
     */
    @Test
    public void searchItemsWhenErrorServerRespondTest() {
        ResponseBody responseBody = Mockito.mock(ResponseBody.class);
        Response<Search> response = Response.error(404,responseBody);

        viewModel.onResponseSearchItems(response);
        int realValue = viewModel.getErrorCode().getValue();
        int expectedValue = Integer.valueOf(ItemsSearchViewModel.SERVER_ERROR_CODE);

        Assert.assertEquals(expectedValue, realValue);
    }

    @Test
    public void onFailureApiServiceTest(){
        viewModel.onFailureApiCall(new Throwable());
        int realValue = viewModel.getErrorCode().getValue();
        int expectedValue = Integer.valueOf(ItemsSearchViewModel.SERVER_CONECCTION_ERROR_CODE);

        Assert.assertEquals(expectedValue, realValue);
    }

    /**
     * Verifica si puede realizar la busqueda cuando no hay interent
     */
    @Test
    public void canSearchItemsInternetDiseabledTest() {

        AppUtils appUtils = Mockito.mock(AppUtils.class);
        when(appUtils.isInternetAvailable()).thenReturn(false);
        boolean canSearchItems = viewModel.canSearchItems(appUtils);

        int realValue = viewModel.getErrorCode().getValue();
        int expectedValue = Integer.valueOf(ItemsSearchViewModel.NOT_INTERNET_ERROR_CODE);

        Assert.assertFalse(canSearchItems);
        Assert.assertEquals(realValue, expectedValue);
    }

    /**
     * Verifica si puede realizar la busqueda cuando hay interent
     */
    @Test
    public void canSearchItemsInternetEnabledTest() {
        AppUtils appUtils = Mockito.mock(AppUtils.class);
        when(appUtils.isInternetAvailable()).thenReturn(true);
        boolean canSearchItems = viewModel.canSearchItems(appUtils);
        Assert.assertTrue(canSearchItems);
    }


}

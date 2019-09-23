package com.example.test;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import com.example.test.forTest.ItemDetailsViewModelForTest;
import com.example.test.model.Item;
import com.example.test.model.ItemDescription;
import com.example.test.util.AppUtils;
import com.example.test.view.itemDetails.ItemDetailsViewModel;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@RunWith(MockitoJUnitRunner.class)
public class ItemDetailsViewModelTest {
    private static Retrofit retrofit;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule =
            new InstantTaskExecutorRule();

    @InjectMocks
    ItemDetailsViewModelForTest viewModel;


    @BeforeClass
    public static void setUp() {
        retrofit = new Retrofit.Builder().baseUrl(AppUtils.BASE_URL)
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    /**
     *Respuesta del servidor al encontrar un item
     */
    @Test
    public void getItemFoundTest()
    {
        try {
                Response<Item> response = Response.success(new Item());
                viewModel.onResponseSetSeletedItem(response);
                Assert.assertNotNull(viewModel.getSelectedItem().getValue());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Busqueda de item cuando el servidor responde con codigo de error
     */
    @Test
    public void getItemServerErrorTest() {
        ResponseBody responseBody = Mockito.mock(ResponseBody.class);
        Response<Item> response = Response.error(404,responseBody);

        viewModel.onResponseSetSeletedItem(response);
        int realValue = viewModel.getErrorCode().getValue();
        int expectedValue = Integer.valueOf(ItemDetailsViewModel.SERVER_ERROR_CODE);

        Assert.assertEquals(expectedValue, realValue);
    }

    /**
     *Respuesta del servidor al encontrar un itemDescription
     */
    @Test
    public void getItemDescriptioFoundTest()
    {
        try {
            Response<ItemDescription> response = Response.success(new ItemDescription());
            viewModel.onResponseSetSeletedItemDescription(response);
            Assert.assertNotNull(viewModel.getItemDescription().getValue());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Busqueda de itemDescription cuando el servidor responde con codigo de error
     */
    @Test
    public void getItemDescriptionServerErrorTest() {
        ResponseBody responseBody = Mockito.mock(ResponseBody.class);
        Response<ItemDescription> response = Response.error(404,responseBody);

        viewModel.onResponseSetSeletedItemDescription(response);
        int realValue = viewModel.getErrorCode().getValue();
        int expectedValue = Integer.valueOf(ItemDetailsViewModel.SERVER_ERROR_CODE);

        Assert.assertEquals(expectedValue, realValue);
    }

    /**
     * Se espera que al producirse un error en la llamada a la api se setee un determinado codigo de error
     */
    @Test
    public void onFailureApiServiceTest(){
        viewModel.onFailureApiCall(new Throwable());
        int realValue = viewModel.getErrorCode().getValue();
        int expectedValue = Integer.valueOf(ItemDetailsViewModel.SERVER_CONECCTION_ERROR_CODE);

        Assert.assertEquals(expectedValue, realValue);
    }
}

package com.example.test;

import com.example.test.apiconnection.ApiService;
import com.example.test.service.ItemService;

import org.mockito.Mockito;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApplicationModuleTest {

    private static final String BASE_URL = "https://api.mercadolibre.com/";

    @Singleton
    @Provides
    public GsonConverterFactory provideGsonConverterFactory(){
        return GsonConverterFactory.create();
    }

    @Singleton
    @Provides
    public Retrofit provideRetrofit(GsonConverterFactory gsonConverterFactory){
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(gsonConverterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    public ApiService provideApiClient(Retrofit retrofit){
        return retrofit.create(ApiService.class);
    }

    @Singleton
    @Provides
    ItemService provideIItemService(ApiService apiService){
        return Mockito.mock(ItemService.class);
    }
}
